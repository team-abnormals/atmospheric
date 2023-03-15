package com.teamabnormals.atmospheric.core.data.server.modifiers;

import com.mojang.serialization.JsonOps;
import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.atmospheric.core.other.tags.AtmosphericBiomeTags;
import com.teamabnormals.atmospheric.core.registry.AtmosphericFeatures.AtmosphericPlacedFeatures;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.JsonCodecProvider;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers.AddFeaturesBiomeModifier;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AtmosphericBiomeModifierProvider {

	public static JsonCodecProvider<BiomeModifier> create(DataGenerator generator, ExistingFileHelper existingFileHelper) {
		RegistryAccess access = RegistryAccess.builtinCopy();
		Registry<Biome> biomeRegistry = access.registryOrThrow(Registry.BIOME_REGISTRY);
		Registry<PlacedFeature> placedFeatures = access.registryOrThrow(Registry.PLACED_FEATURE_REGISTRY);
		HashMap<ResourceLocation, BiomeModifier> modifiers = new HashMap<>();

		addModifier(modifiers, "add_feature/yucca_tree/desert", new AddFeaturesBiomeModifier(tag(biomeRegistry, AtmosphericBiomeTags.HAS_DESERT_YUCCA_TREES), of(placedFeatures, AtmosphericPlacedFeatures.DESERT_YUCCA_TREES), GenerationStep.Decoration.VEGETAL_DECORATION));
		addModifier(modifiers, "add_feature/yucca_tree/badlands", new AddFeaturesBiomeModifier(tag(biomeRegistry, AtmosphericBiomeTags.HAS_BADLANDS_YUCCA_TREES), of(placedFeatures, AtmosphericPlacedFeatures.TREES_WOODED_BADLANDS), GenerationStep.Decoration.VEGETAL_DECORATION));
		addModifier(modifiers, "add_feature/yucca_tree/savanna", new AddFeaturesBiomeModifier(tag(biomeRegistry, AtmosphericBiomeTags.HAS_SAVANNA_YUCCA_TREES), of(placedFeatures, AtmosphericPlacedFeatures.WINDSWEPT_SAVANNA_YUCCA_TREES), GenerationStep.Decoration.VEGETAL_DECORATION));

		return JsonCodecProvider.forDatapackRegistry(generator, existingFileHelper, Atmospheric.MOD_ID, RegistryOps.create(JsonOps.INSTANCE, access), ForgeRegistries.Keys.BIOME_MODIFIERS, modifiers);
	}

	private static HolderSet<Biome> tag(Registry<Biome> biomeRegistry, TagKey<Biome> tagKey) {
		return new HolderSet.Named<>(biomeRegistry, tagKey);
	}

	private static void addModifier(HashMap<ResourceLocation, BiomeModifier> modifiers, String name, BiomeModifier modifier) {
		modifiers.put(Atmospheric.location(name), modifier);
	}

	@SafeVarargs
	@SuppressWarnings("ConstantConditions")
	private static HolderSet<PlacedFeature> of(Registry<PlacedFeature> placedFeatures, RegistryObject<PlacedFeature>... features) {
		return HolderSet.direct(Stream.of(features).map(registryObject -> placedFeatures.getOrCreateHolderOrThrow(registryObject.getKey())).collect(Collectors.toList()));
	}
}