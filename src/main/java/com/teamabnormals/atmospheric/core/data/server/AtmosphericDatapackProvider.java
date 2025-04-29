package com.teamabnormals.atmospheric.core.data.server;

import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.atmospheric.core.registry.AtmosphericFeatures.AtmosphericConfiguredFeatures;
import com.teamabnormals.atmospheric.core.registry.AtmosphericFeatures.AtmosphericPlacedFeatures;
import com.teamabnormals.atmospheric.core.registry.AtmosphericRegistries;
import com.teamabnormals.atmospheric.core.registry.datapack.*;
import com.teamabnormals.atmospheric.core.registry.datapack.AtmosphericStructures.AtmosphericStructureSets;
import com.teamabnormals.blueprint.core.registry.BlueprintDataPackRegistries;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.registries.NeoForgeRegistries.Keys;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class AtmosphericDatapackProvider extends DatapackBuiltinEntriesProvider {

	public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
			.add(Registries.PAINTING_VARIANT, AtmosphericPaintingVariants::bootstrap)
			.add(Registries.BANNER_PATTERN, AtmosphericBannerPatterns::bootstrap)
			.add(Registries.CONFIGURED_FEATURE, AtmosphericConfiguredFeatures::bootstrap)
			.add(Registries.PLACED_FEATURE, AtmosphericPlacedFeatures::bootstrap)
			.add(Registries.NOISE, AtmosphericNoiseParameters::bootstrap)
			.add(Registries.BIOME, AtmosphericBiomes::bootstrap)
			.add(Registries.DAMAGE_TYPE, AtmosphericDamageTypes::bootstrap)
			.add(Registries.TRIM_PATTERN, AtmosphericTrimPatterns::bootstrap)
			.add(Registries.STRUCTURE, AtmosphericStructures::bootstrap)
			.add(Registries.STRUCTURE_SET, AtmosphericStructureSets::bootstrap)
			.add(Registries.PROCESSOR_LIST, AtmosphericProcessorLists::bootstrap)
			.add(Registries.TEMPLATE_POOL, AtmosphericTemplatePools::bootstrap)
			.add(Registries.TRIM_MATERIAL, AtmosphericTrimMaterials::bootstrap)
			.add(Registries.WOLF_VARIANT, AtmosphericWolfVariants::bootstrap)
			.add(Keys.BIOME_MODIFIERS, AtmosphericBiomeModifiers::bootstrap)
			.add(BlueprintDataPackRegistries.STRUCTURE_REPALETTERS, AtmosphericStructureRepaletters::bootstrap)
			.add(BlueprintDataPackRegistries.MODDED_BIOME_SLICES, AtmosphericBiomeSlices::bootstrap)
			.add(AtmosphericRegistries.TETRA_VARIANT, AtmosphericTetraVariants::bootstrap)
			.add(AtmosphericRegistries.CAMEL_VARIANT, AtmosphericCamelVariants::bootstrap);

	public AtmosphericDatapackProvider(PackOutput output, CompletableFuture<Provider> provider) {
		super(output, provider, BUILDER, AtmosphericStructureRepaletters::applyConditions, Set.of(Atmospheric.MOD_ID));
	}
}