package com.teamabnormals.atmospheric.core.data.server.modifiers;

import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import com.teamabnormals.atmospheric.core.registry.AtmosphericEntityTypes;
import com.teamabnormals.atmospheric.core.registry.AtmosphericItems;
import com.teamabnormals.atmospheric.core.registry.AtmosphericMobEffects;
import com.teamabnormals.atmospheric.core.registry.datapack.AtmosphericBiomes;
import com.teamabnormals.blueprint.common.advancement.modification.AdvancementModifierProvider;
import com.teamabnormals.blueprint.common.advancement.modification.modifiers.CriteriaModifier;
import com.teamabnormals.blueprint.common.advancement.modification.modifiers.EffectsChangedModifier;
import net.minecraft.advancements.AdvancementRequirements.Strategy;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.HolderLookup.RegistryLookup;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.WolfVariant;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.Collection;
import java.util.Comparator;
import java.util.concurrent.CompletableFuture;

public class AtmosphericAdvancementModifierProvider extends AdvancementModifierProvider {
	private static final EntityType<?>[] BREEDABLE_ANIMALS = new EntityType[]{AtmosphericEntityTypes.COCHINEAL.get()};

	public AtmosphericAdvancementModifierProvider(PackOutput output, CompletableFuture<Provider> provider) {
		super(Atmospheric.MOD_ID, output, provider);
	}

	@Override
	protected void registerEntries(Provider provider) {
		this.entry("nether/all_potions").selects("nether/all_potions").addModifier(new EffectsChangedModifier("all_effects", false, MobEffectsPredicate.Builder.effects().and(AtmosphericMobEffects.RELIEF).and(AtmosphericMobEffects.WORSENING).build().get()));
		this.entry("nether/all_effects").selects("nether/all_effects").addModifier(new EffectsChangedModifier("all_effects", false, MobEffectsPredicate.Builder.effects().and(AtmosphericMobEffects.RELIEF).and(AtmosphericMobEffects.WORSENING).and(AtmosphericMobEffects.PERSISTENCE).and(AtmosphericMobEffects.SPITTING).build().get()));

		CriteriaModifier.Builder balancedDiet = CriteriaModifier.builder(this.modId);
		Collection<DeferredHolder<Item, ? extends Item>> items = AtmosphericItems.ITEMS.getDeferredRegister().getEntries().stream().filter(i -> i.get().getDefaultInstance().getFoodProperties(null) != null).toList();
		items.forEach(item -> {
			if (item != AtmosphericItems.ENDER_DRAGON_FRUIT) {
				balancedDiet.addCriterion(BuiltInRegistries.ITEM.getKey(item.get()).getPath(), ConsumeItemTrigger.TriggerInstance.usedItem(item.get()));
			}
		});
		this.entry("husbandry/balanced_diet").selects("husbandry/balanced_diet").addModifier(balancedDiet.requirements(Strategy.AND).build());

		CriteriaModifier.Builder breedAllAnimals = CriteriaModifier.builder(this.modId);
		for (EntityType<?> entityType : BREEDABLE_ANIMALS) {
			breedAllAnimals.addCriterion(BuiltInRegistries.ENTITY_TYPE.getKey(entityType).getPath(), BredAnimalsTrigger.TriggerInstance.bredAnimals(EntityPredicate.Builder.entity().of(entityType)));
		}
		this.entry("husbandry/bred_all_animals").selects("husbandry/bred_all_animals").addModifier(breedAllAnimals.requirements(Strategy.AND).build());

		this.entry("husbandry/whole_pack").selects("husbandry/whole_pack").addModifier(addTamedWolfVariants(provider).requirements(Strategy.AND).build());

		CriteriaModifier.Builder adventuringTime = CriteriaModifier.builder(this.modId);
		RegistryLookup<Biome> biomes = provider.lookupOrThrow(Registries.BIOME);
		for (ResourceKey<Biome> biome : AtmosphericBiomes.NATURAL_BIOMES) {
			adventuringTime.addCriterion(biome.location().toString(), PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inBiome(biomes.getOrThrow(biome))));
		}
		this.entry("adventure/adventuring_time").selects("adventure/adventuring_time").addModifier(adventuringTime.requirements(Strategy.AND).build());

		this.entry("husbandry/plant_seed").selects("husbandry/plant_seed").addModifier(CriteriaModifier.builder(this.modId)
				.addCriterion("aloe_vera", ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(AtmosphericBlocks.ALOE_VERA.get()))
				.addIndexedRequirements(0, false, "aloe_vera").build());
	}

	private CriteriaModifier.Builder addTamedWolfVariants(HolderLookup.Provider registries) {
		CriteriaModifier.Builder builder = CriteriaModifier.builder(this.modId);
		HolderLookup.RegistryLookup<WolfVariant> registrylookup = registries.lookupOrThrow(Registries.WOLF_VARIANT);
		registrylookup.listElementIds()
				.filter(key -> key.location().getNamespace().equals(Atmospheric.MOD_ID))
				.sorted(Comparator.comparing(ResourceKey::location))
				.forEach(variant -> {
							Holder<WolfVariant> holder = registrylookup.getOrThrow(variant);
							builder.addCriterion(variant.location().toString(), TameAnimalTrigger.TriggerInstance.tamedAnimal(EntityPredicate.Builder.entity().subPredicate(EntitySubPredicates.wolfVariant(HolderSet.direct(holder)))));
						}
				);
		return builder;
	}
}