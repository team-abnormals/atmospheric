package com.teamabnormals.atmospheric.core.data.server.modifiers;

import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.atmospheric.core.registry.*;
import com.teamabnormals.blueprint.common.advancement.modification.AdvancementModifierProvider;
import com.teamabnormals.blueprint.common.advancement.modification.modifiers.CriteriaModifier;
import com.teamabnormals.blueprint.common.advancement.modification.modifiers.EffectsChangedModifier;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ForgeRegistries;

public class AtmosphericAdvancementModifierProvider extends AdvancementModifierProvider {
	private static final EntityType<?>[] BREEDABLE_ANIMALS = new EntityType[]{AtmosphericEntityTypes.COCHINEAL.get()};

	public AtmosphericAdvancementModifierProvider(DataGenerator generator) {
		super(generator, Atmospheric.MOD_ID);
	}

	@Override
	protected void registerEntries() {
		this.entry("nether/all_potions").selects("nether/all_potions").addModifier(new EffectsChangedModifier("all_effects", false, MobEffectsPredicate.effects().and(AtmosphericMobEffects.RELIEF.get()).and(AtmosphericMobEffects.WORSENING.get())));
		this.entry("nether/all_effects").selects("nether/all_effects").addModifier(new EffectsChangedModifier("all_effects", false, MobEffectsPredicate.effects().and(AtmosphericMobEffects.RELIEF.get()).and(AtmosphericMobEffects.WORSENING.get()).and(AtmosphericMobEffects.PERSISTENCE.get()).and(AtmosphericMobEffects.SPITTING.get())));

		CriteriaModifier.Builder balancedDiet = CriteriaModifier.builder(this.modId);
		AtmosphericItems.HELPER.getDeferredRegister().getEntries().forEach(registryObject -> {
			Item item = registryObject.get();
			if (item.isEdible() && item != AtmosphericItems.ENDER_DRAGON_FRUIT.get()) {
				balancedDiet.addCriterion(ForgeRegistries.ITEMS.getKey(item).getPath(), ConsumeItemTrigger.TriggerInstance.usedItem(item));
			}
		});
		this.entry("husbandry/balanced_diet").selects("husbandry/balanced_diet").addModifier(balancedDiet.requirements(RequirementsStrategy.AND).build());

		CriteriaModifier.Builder breedAllAnimals = CriteriaModifier.builder(this.modId);
		for (EntityType<?> entityType : BREEDABLE_ANIMALS) {
			breedAllAnimals.addCriterion(ForgeRegistries.ENTITY_TYPES.getKey(entityType).getPath(), BredAnimalsTrigger.TriggerInstance.bredAnimals(EntityPredicate.Builder.entity().of(entityType)));
		}
		this.entry("husbandry/bred_all_animals").selects("husbandry/bred_all_animals").addModifier(breedAllAnimals.requirements(RequirementsStrategy.AND).build());


		CriteriaModifier.Builder adventuringTime = CriteriaModifier.builder(this.modId);
		AtmosphericBiomes.HELPER.getDeferredRegister().getEntries().forEach(biome -> {
			ResourceLocation key = ForgeRegistries.BIOMES.getKey(biome.get());
			if (biome.get() != AtmosphericBiomes.HOT_SPRINGS.get() && biome.get() != AtmosphericBiomes.GRIMWOODS.get())
				adventuringTime.addCriterion(key.getPath(), PlayerTrigger.TriggerInstance.located(LocationPredicate.inBiome(ResourceKey.create(Registry.BIOME_REGISTRY, key))));
		});
		this.entry("adventure/adventuring_time").selects("adventure/adventuring_time").addModifier(adventuringTime.requirements(RequirementsStrategy.AND).build());

		this.entry("husbandry/plant_seed").selects("husbandry/plant_seed").addModifier(CriteriaModifier.builder(this.modId)
				.addCriterion("aloe_vera", PlacedBlockTrigger.TriggerInstance.placedBlock(AtmosphericBlocks.ALOE_VERA.get()))
				.addIndexedRequirements(0, false, "aloe_vera").build());
	}
}