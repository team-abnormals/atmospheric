package com.teamabnormals.atmospheric.core.data.server;

import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.atmospheric.core.registry.builtin.AtmosphericBiomeSlices;
import com.teamabnormals.atmospheric.core.registry.AtmosphericBiomes;
import com.teamabnormals.atmospheric.core.registry.AtmosphericFeatures.AtmosphericConfiguredFeatures;
import com.teamabnormals.atmospheric.core.registry.AtmosphericFeatures.AtmosphericPlacedFeatures;
import com.teamabnormals.atmospheric.core.registry.builtin.*;
import com.teamabnormals.blueprint.core.registry.BlueprintDataPackRegistries;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class AtmosphericDatapackBuiltinEntriesProvider extends DatapackBuiltinEntriesProvider {

	public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
			.add(Registries.CONFIGURED_FEATURE, AtmosphericConfiguredFeatures::bootstrap)
			.add(Registries.PLACED_FEATURE, AtmosphericPlacedFeatures::bootstrap)
			.add(Registries.NOISE, AtmosphericNoiseParameters::bootstrap)
			.add(Registries.BIOME, AtmosphericBiomes::bootstrap)
			.add(Registries.DAMAGE_TYPE, AtmosphericDamageTypes::bootstrap)
			.add(Registries.TRIM_PATTERN, AtmosphericTrimPatterns::bootstrap)
			.add(Registries.PROCESSOR_LIST, AtmosphericProcessorLists::bootstrap)
			.add(Registries.TEMPLATE_POOL, AtmosphericTemplatePools::bootstrap)
			.add(Registries.TRIM_MATERIAL, AtmosphericTrimMaterials::bootstrap)
			.add(BlueprintDataPackRegistries.STRUCTURE_REPALETTERS, AtmosphericStructureRepaletters::bootstrap)
			.add(BlueprintDataPackRegistries.MODDED_BIOME_SLICES, AtmosphericBiomeSlices::bootstrap)
			.add(ForgeRegistries.Keys.BIOME_MODIFIERS, AtmosphericBiomeModifiers::bootstrap);

	public AtmosphericDatapackBuiltinEntriesProvider(PackOutput output, CompletableFuture<Provider> provider) {
		super(output, provider, BUILDER, Set.of(Atmospheric.MOD_ID));
	}
}