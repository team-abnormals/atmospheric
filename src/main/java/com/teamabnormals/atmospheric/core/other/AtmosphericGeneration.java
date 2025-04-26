package com.teamabnormals.atmospheric.core.other;

import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.biome.OverworldBiomes;
import net.minecraft.data.worldgen.placement.AquaticPlacements;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.levelgen.GenerationStep.Decoration;

import static com.teamabnormals.atmospheric.core.registry.AtmosphericFeatures.AtmosphericPlacedFeatures.*;

public class AtmosphericGeneration {

	public static void rainforest(BiomeGenerationSettings.Builder generation, boolean basin, boolean sparse) {
		OverworldBiomes.globalOverworldGeneration(generation);
		BiomeDefaultFeatures.addMossyStoneBlock(generation);
		BiomeDefaultFeatures.addSavannaGrass(generation);
		BiomeDefaultFeatures.addForestFlowers(generation);
		BiomeDefaultFeatures.addFerns(generation);
		BiomeDefaultFeatures.addDefaultOres(generation);
		BiomeDefaultFeatures.addDefaultSoftDisks(generation);
		BiomeDefaultFeatures.addJungleGrass(generation);
		BiomeDefaultFeatures.addDefaultMushrooms(generation);
		BiomeDefaultFeatures.addDefaultExtraVegetation(generation);
		generation.addFeature(Decoration.VEGETAL_DECORATION, PODZOL);
		generation.addFeature(Decoration.VEGETAL_DECORATION, PASSION_VINES);
		generation.addFeature(Decoration.VEGETAL_DECORATION, MONKEY_BRUSH);

		if (!basin) {
			if (!sparse) {
				generation.addFeature(Decoration.VEGETAL_DECORATION, TREES_RAINFOREST);
				generation.addFeature(Decoration.VEGETAL_DECORATION, BUSHES_RAINFOREST);
			} else {
				generation.addFeature(Decoration.VEGETAL_DECORATION, TREES_SPARSE_RAINFOREST);
				generation.addFeature(Decoration.VEGETAL_DECORATION, BUSHES_SPARSE_RAINFOREST);
			}

			generation.addFeature(Decoration.VEGETAL_DECORATION, PATCH_WATER_HYACINTH);
			generation.addFeature(Decoration.VEGETAL_DECORATION, PATCH_WATERLILY_RAINFOREST);
			generation.addFeature(Decoration.VEGETAL_DECORATION, AquaticPlacements.SEAGRASS_SWAMP);
		} else {
			generation.addFeature(Decoration.VEGETAL_DECORATION, BUSHES_RAINFOREST);
			generation.addFeature(Decoration.VEGETAL_DECORATION, AquaticPlacements.SEAGRASS_DEEP_WARM);
			generation.addFeature(Decoration.VEGETAL_DECORATION, PATCH_WATERLILY_RAINFOREST_BASIN);
			if (!sparse) {
				generation.addFeature(Decoration.LOCAL_MODIFICATIONS, OCEAN_FLOOR_RAISER);
				generation.addFeature(Decoration.VEGETAL_DECORATION, TREES_RAINFOREST_BASIN);
				generation.addFeature(Decoration.VEGETAL_DECORATION, PATCH_WATER_HYACINTH);
			} else {
				generation.addFeature(Decoration.VEGETAL_DECORATION, TREES_SPARSE_RAINFOREST_BASIN);
				generation.addFeature(Decoration.VEGETAL_DECORATION, PATCH_WATER_HYACINTH_SPARSE);
			}
		}
	}

	public static void dunes(BiomeGenerationSettings.Builder generation, boolean rocky, boolean variant) {
		BiomeDefaultFeatures.addFossilDecoration(generation);
		OverworldBiomes.globalOverworldGeneration(generation);
		BiomeDefaultFeatures.addDefaultOres(generation);
		BiomeDefaultFeatures.addDefaultSoftDisks(generation);
		generation.addFeature(Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_DEAD_BUSH_2);
		BiomeDefaultFeatures.addDefaultMushrooms(generation);
		BiomeDefaultFeatures.addDefaultExtraVegetation(generation);
		generation.addFeature(Decoration.VEGETAL_DECORATION, PATCH_ARID_SPROUTS);

		if (!rocky) {
			if (!variant) {
				generation.addFeature(Decoration.LOCAL_MODIFICATIONS, DUNE_ROCK);
				generation.addFeature(Decoration.VEGETAL_DECORATION, TREES_DUNES);
				generation.addFeature(Decoration.VEGETAL_DECORATION, PATCH_YUCCA_FLOWER);
				generation.addFeature(Decoration.VEGETAL_DECORATION, PATCH_ALOE_VERA);
				generation.addFeature(Decoration.VEGETAL_DECORATION, PATCH_BARREL_CACTUS_DUNES);
			} else {
				generation.addFeature(Decoration.LOCAL_MODIFICATIONS, DUNE_ROCK_EXTRA);
				generation.addFeature(Decoration.VEGETAL_DECORATION, PATCH_MELON_DUNES);
				generation.addFeature(Decoration.VEGETAL_DECORATION, FLOWER_FLOURISHING_DUNES);
				generation.addFeature(Decoration.VEGETAL_DECORATION, PATCH_DUNE_GRASS);
				generation.addFeature(Decoration.VEGETAL_DECORATION, FLOURISHING_DUNES_YUCCA_TREES);
				generation.addFeature(Decoration.VEGETAL_DECORATION, TREES_FLOURISHING_DUNES);
				generation.addFeature(Decoration.VEGETAL_DECORATION, PATCH_YUCCA_FLOWER_EXTRA);
				generation.addFeature(Decoration.VEGETAL_DECORATION, PATCH_ALOE_VERA_EXTRA);
				generation.addFeature(Decoration.VEGETAL_DECORATION, PATCH_BARREL_CACTUS_FLOURISHING_DUNES);
				generation.addFeature(Decoration.VEGETAL_DECORATION, PATCH_AGAVE_LARGE);
			}
		} else {
			if (!variant) {
				generation.addFeature(Decoration.LOCAL_MODIFICATIONS, DUNE_ROCK_MANY);
				generation.addFeature(Decoration.VEGETAL_DECORATION, TREES_ROCKY_DUNES);
				generation.addFeature(Decoration.VEGETAL_DECORATION, PATCH_ALOE_VERA);
			} else {
				generation.addFeature(Decoration.LOCAL_MODIFICATIONS, DUNE_ROCK);
				generation.addFeature(Decoration.UNDERGROUND_DECORATION, FOSSIL_DUNES);
				generation.addFeature(Decoration.VEGETAL_DECORATION, TREES_PETRIFIED_DUNES);
			}
			generation.addFeature(Decoration.VEGETAL_DECORATION, PATCH_BARREL_CACTUS_ROCKY_DUNES);
		}
	}

	public static void spinyThicket(BiomeGenerationSettings.Builder generation) {
		OverworldBiomes.globalOverworldGeneration(generation);
		BiomeDefaultFeatures.addDefaultOres(generation);
		BiomeDefaultFeatures.addDefaultSoftDisks(generation);
		generation.addFeature(Decoration.VEGETAL_DECORATION, COARSE_DIRT);
		generation.addFeature(Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_DEAD_BUSH_BADLANDS);
		BiomeDefaultFeatures.addSavannaExtraGrass(generation);
		BiomeDefaultFeatures.addDefaultMushrooms(generation);
		BiomeDefaultFeatures.addDefaultExtraVegetation(generation);

		generation.addFeature(Decoration.VEGETAL_DECORATION, PATCH_CACTUS_SPINY_THICKET);
		generation.addFeature(Decoration.VEGETAL_DECORATION, PATCH_CACTUS_SPINIER_THICKET);
		generation.addFeature(Decoration.VEGETAL_DECORATION, TREES_SPINY_THICKET);
		generation.addFeature(Decoration.VEGETAL_DECORATION, TREES_FLOURISHING_DUNES);
		generation.addFeature(Decoration.VEGETAL_DECORATION, PATCH_BARREL_CACTUS_SPINY_THICKET);
		generation.addFeature(Decoration.VEGETAL_DECORATION, SINGLE_YUCCA_FLOWER);
		generation.addFeature(Decoration.VEGETAL_DECORATION, PATCH_AGAVE_LARGE);
	}

	public static void scrubland(BiomeGenerationSettings.Builder generation, boolean snowy) {
		OverworldBiomes.globalOverworldGeneration(generation);
		BiomeDefaultFeatures.addDefaultOres(generation);
		BiomeDefaultFeatures.addDefaultSoftDisks(generation);
		generation.addFeature(Decoration.VEGETAL_DECORATION, !snowy ? VegetationPlacements.PATCH_DEAD_BUSH_BADLANDS : PATCH_DEAD_BUSH_LAUREL_FOREST);
		BiomeDefaultFeatures.addDefaultMushrooms(generation);
		BiomeDefaultFeatures.addDefaultExtraVegetation(generation);

		generation.addFeature(Decoration.VEGETAL_DECORATION, !snowy ? PATCH_ARID_SPROUTS : PATCH_ARID_SPROUTS_RARE);
		generation.addFeature(Decoration.VEGETAL_DECORATION, DRAGON_ROOTS);
		generation.addFeature(Decoration.VEGETAL_DECORATION, TREES_SCRUBLAND);
		generation.addFeature(Decoration.VEGETAL_DECORATION, FLOWER_SCRUBLAND);
		generation.addFeature(Decoration.VEGETAL_DECORATION, PATCH_CACTUS_SCRUBLAND);
		generation.addFeature(Decoration.VEGETAL_DECORATION, PATCH_BARREL_CACTUS_SCRUBLAND);
	}

	public static void aspenParkland(BiomeGenerationSettings.Builder generation) {
		OverworldBiomes.globalOverworldGeneration(generation);
		BiomeDefaultFeatures.addDefaultOres(generation);
		BiomeDefaultFeatures.addDefaultSoftDisks(generation);
		BiomeDefaultFeatures.addDefaultFlowers(generation);
		BiomeDefaultFeatures.addShatteredSavannaGrass(generation);
		BiomeDefaultFeatures.addDefaultMushrooms(generation);
		BiomeDefaultFeatures.addDefaultExtraVegetation(generation);

		generation.addFeature(Decoration.VEGETAL_DECORATION, CRUSTOSE);
		generation.addFeature(Decoration.VEGETAL_DECORATION, TREES_ASPEN_PARKLAND);
		generation.addFeature(Decoration.VEGETAL_DECORATION, FALLEN_CRUSTOSE_LOG);
		generation.addFeature(Decoration.VEGETAL_DECORATION, SINGLE_AGAVE);
		generation.addFeature(Decoration.VEGETAL_DECORATION, PATCH_AGAVE_LARGE);
		generation.addFeature(Decoration.VEGETAL_DECORATION, PATCH_GOLDEN_GROWTHS);
	}

	public static void kousaJungle(BiomeGenerationSettings.Builder generation) {
		OverworldBiomes.globalOverworldGeneration(generation);
		BiomeDefaultFeatures.addMossyStoneBlock(generation);
		BiomeDefaultFeatures.addDefaultOres(generation);
		BiomeDefaultFeatures.addDefaultSoftDisks(generation);
		BiomeDefaultFeatures.addDefaultFlowers(generation);
		BiomeDefaultFeatures.addTaigaGrass(generation);
		BiomeDefaultFeatures.addDefaultExtraVegetation(generation);

		generation.addFeature(Decoration.VEGETAL_DECORATION, PATCH_LARGE_FERN_KOUSA);
		generation.addFeature(Decoration.VEGETAL_DECORATION, CURRANT);
		generation.addFeature(Decoration.VEGETAL_DECORATION, SNOWY_BAMBOO);
		generation.addFeature(Decoration.VEGETAL_DECORATION, BIRCH_BUSH);
		generation.addFeature(Decoration.VEGETAL_DECORATION, TREES_KOUSA_JUNGLE);
		generation.addFeature(Decoration.VEGETAL_DECORATION, PASSION_VINES);
	}

	public static void grimwoods(BiomeGenerationSettings.Builder generation) {
		OverworldBiomes.globalOverworldGeneration(generation);
		BiomeDefaultFeatures.addDefaultOres(generation);
		BiomeDefaultFeatures.addDefaultSoftDisks(generation);
		generation.addFeature(Decoration.VEGETAL_DECORATION, COARSE_DIRT);
		generation.addFeature(Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_DEAD_BUSH_BADLANDS);
		BiomeDefaultFeatures.addSavannaExtraGrass(generation);
		BiomeDefaultFeatures.addDefaultMushrooms(generation);
		BiomeDefaultFeatures.addDefaultExtraVegetation(generation);

		generation.addFeature(Decoration.VEGETAL_DECORATION, TREES_GRIMWOODS);
		generation.addFeature(Decoration.VEGETAL_DECORATION, OMINOUS_BLOCK);
		generation.addFeature(Decoration.VEGETAL_DECORATION, DEAD_CURRANT);
	}

	public static void laurelForest(BiomeGenerationSettings.Builder generation) {
		OverworldBiomes.globalOverworldGeneration(generation);
		BiomeDefaultFeatures.addSavannaGrass(generation);
		BiomeDefaultFeatures.addDefaultOres(generation);
		BiomeDefaultFeatures.addDefaultSoftDisks(generation);
		BiomeDefaultFeatures.addDefaultFlowers(generation);
		generation.addFeature(Decoration.VEGETAL_DECORATION, COARSE_DIRT_LAUREL_FOREST);
		generation.addFeature(Decoration.VEGETAL_DECORATION, PATCH_DEAD_BUSH_LAUREL_FOREST);
		BiomeDefaultFeatures.addForestGrass(generation);
		BiomeDefaultFeatures.addDefaultMushrooms(generation);
		BiomeDefaultFeatures.addDefaultExtraVegetation(generation);

		generation.addFeature(Decoration.VEGETAL_DECORATION, TREES_LAUREL_FOREST_GIANT);
		generation.addFeature(Decoration.VEGETAL_DECORATION, TREES_LAUREL_FOREST_LARGE);
		generation.addFeature(Decoration.VEGETAL_DECORATION, TREES_LAUREL_FOREST);
		generation.addFeature(Decoration.VEGETAL_DECORATION, PATCH_AGAVE_LARGE);
		generation.addFeature(Decoration.VEGETAL_DECORATION, PATCH_LARGE_FERN_KOUSA);
		generation.addFeature(Decoration.VEGETAL_DECORATION, PATCH_GRASS_LAUREL_FOREST);
	}

	public static void hotSprings(BiomeGenerationSettings.Builder generation) {
		OverworldBiomes.globalOverworldGeneration(generation);
		BiomeDefaultFeatures.addDefaultOres(generation);
		BiomeDefaultFeatures.addDefaultSoftDisks(generation);
		BiomeDefaultFeatures.addDefaultMushrooms(generation);
		BiomeDefaultFeatures.addDefaultExtraVegetation(generation);
		BiomeDefaultFeatures.addFerns(generation);
		BiomeDefaultFeatures.addTaigaTrees(generation);
		BiomeDefaultFeatures.addGiantTaigaVegetation(generation);
		BiomeDefaultFeatures.addCommonBerryBushes(generation);
		generation.addFeature(Decoration.VEGETAL_DECORATION, VegetationPlacements.TREES_OLD_GROWTH_SPRUCE_TAIGA);
		generation.addFeature(Decoration.VEGETAL_DECORATION, HOT_SPRINGS_ROCK);
	}
}
