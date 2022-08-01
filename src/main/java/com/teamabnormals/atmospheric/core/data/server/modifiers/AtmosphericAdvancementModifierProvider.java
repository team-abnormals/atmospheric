package com.teamabnormals.atmospheric.core.data.server.modifiers;

import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.atmospheric.core.registry.AtmosphericBiomes;
import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import com.teamabnormals.atmospheric.core.registry.AtmosphericItems;
import com.teamabnormals.atmospheric.core.registry.AtmosphericMobEffects;
import com.teamabnormals.blueprint.common.advancement.modification.AdvancementModifierProvider;
import com.teamabnormals.blueprint.common.advancement.modification.modifiers.CriteriaModifier;
import com.teamabnormals.blueprint.common.advancement.modification.modifiers.EffectsChangedModifier;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Collection;

public class AtmosphericAdvancementModifierProvider extends AdvancementModifierProvider {

	public AtmosphericAdvancementModifierProvider(DataGenerator generator) {
		super(generator, Atmospheric.MOD_ID);
	}

	@Override
	protected void registerEntries() {
		this.entry("nether/all_potions").selects("nether/all_potions").addModifier(new EffectsChangedModifier("all_effects", false, MobEffectsPredicate.effects().and(AtmosphericMobEffects.RELIEF.get()).and(AtmosphericMobEffects.WORSENING.get())));
		this.entry("nether/all_effects").selects("nether/all_effects").addModifier(new EffectsChangedModifier("all_effects", false, MobEffectsPredicate.effects().and(AtmosphericMobEffects.RELIEF.get()).and(AtmosphericMobEffects.WORSENING.get()).and(AtmosphericMobEffects.PERSISTENCE.get()).and(AtmosphericMobEffects.SPITTING.get())));

		CriteriaModifier.Builder balancedDiet = CriteriaModifier.builder(this.modId);
		Collection<RegistryObject<Item>> items = AtmosphericItems.HELPER.getDeferredRegister().getEntries();
		items.forEach(item -> {
			if (item.get().isEdible()) {
				balancedDiet.addCriterion(ForgeRegistries.ITEMS.getKey(item.get()).getPath(), ConsumeItemTrigger.TriggerInstance.usedItem(item.get()));
			}
		});
		this.entry("husbandry/balanced_diet").selects("husbandry/balanced_diet").addModifier(balancedDiet.requirements(RequirementsStrategy.AND).build());

		CriteriaModifier.Builder adventuringTime = CriteriaModifier.builder(this.modId);
		Collection<RegistryObject<Biome>> biomes = AtmosphericBiomes.HELPER.getDeferredRegister().getEntries();
		biomes.forEach(biome -> {
			ResourceLocation key = ForgeRegistries.BIOMES.getKey(biome.get());
			adventuringTime.addCriterion(key.getPath(), LocationTrigger.TriggerInstance.located(LocationPredicate.inBiome(ResourceKey.create(Registry.BIOME_REGISTRY, key))));
		});
		this.entry("adventure/adventuring_time").selects("adventure/adventuring_time").addModifier(adventuringTime.requirements(RequirementsStrategy.AND).build());

		this.entry("husbandry/plant_seed").selects("husbandry/plant_seed").addModifier(CriteriaModifier.builder(this.modId)
				.addCriterion("aloe_vera", PlacedBlockTrigger.TriggerInstance.placedBlock(AtmosphericBlocks.ALOE_VERA.get()))
				.addIndexedRequirements(0, false, "aloe_vera").build());
	}
}