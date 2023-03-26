package com.teamabnormals.atmospheric.core.other;

import com.teamabnormals.atmospheric.core.Atmospheric;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.biome.OverworldBiomes;
import net.minecraft.data.worldgen.placement.AquaticPlacements;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.levelgen.GenerationStep.Decoration;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

import static com.teamabnormals.atmospheric.core.registry.AtmosphericFeatures.AtmosphericPlacedFeatures.*;

@EventBusSubscriber(modid = Atmospheric.MOD_ID)
public class AtmosphericGeneration {

	public static void baseRainforest(BiomeGenerationSettings.Builder generation, boolean basin, boolean sparse) {
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
		generation.addFeature(Decoration.VEGETAL_DECORATION, PODZOL.getHolder().get());
		generation.addFeature(Decoration.VEGETAL_DECORATION, PASSION_VINES.getHolder().get());
		generation.addFeature(Decoration.VEGETAL_DECORATION, MONKEY_BRUSH.getHolder().get());

		if (!basin) {
			if (!sparse) {
				generation.addFeature(Decoration.VEGETAL_DECORATION, TREES_RAINFOREST.getHolder().get());
				generation.addFeature(Decoration.VEGETAL_DECORATION, BUSHES_RAINFOREST.getHolder().get());
			} else {
				generation.addFeature(Decoration.VEGETAL_DECORATION, TREES_SPARSE_RAINFOREST.getHolder().get());
				generation.addFeature(Decoration.VEGETAL_DECORATION, BUSHES_SPARSE_RAINFOREST.getHolder().get());
			}

			generation.addFeature(Decoration.VEGETAL_DECORATION, PATCH_WATER_HYACINTH.getHolder().get());
			generation.addFeature(Decoration.VEGETAL_DECORATION, PATCH_WATERLILY_RAINFOREST.getHolder().get());
			generation.addFeature(Decoration.VEGETAL_DECORATION, AquaticPlacements.SEAGRASS_SWAMP);
		} else {
			generation.addFeature(Decoration.VEGETAL_DECORATION, BUSHES_RAINFOREST.getHolder().get());
			generation.addFeature(Decoration.VEGETAL_DECORATION, AquaticPlacements.SEAGRASS_DEEP_WARM);
			generation.addFeature(Decoration.VEGETAL_DECORATION, PATCH_WATERLILY_RAINFOREST_BASIN.getHolder().get());
			if (!sparse) {
				generation.addFeature(Decoration.LOCAL_MODIFICATIONS, OCEAN_FLOOR_RAISER.getHolder().get());
				generation.addFeature(Decoration.VEGETAL_DECORATION, TREES_RAINFOREST_BASIN.getHolder().get());
				generation.addFeature(Decoration.VEGETAL_DECORATION, PATCH_WATER_HYACINTH.getHolder().get());
			} else {
				generation.addFeature(Decoration.VEGETAL_DECORATION, TREES_SPARSE_RAINFOREST_BASIN.getHolder().get());
				generation.addFeature(Decoration.VEGETAL_DECORATION, PATCH_WATER_HYACINTH_SPARSE.getHolder().get());
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
		generation.addFeature(Decoration.VEGETAL_DECORATION, PATCH_ARID_SPROUTS.getHolder().get());

		if (!rocky) {
			if (!variant) {
				generation.addFeature(Decoration.LOCAL_MODIFICATIONS, DUNE_ROCK.getHolder().get());
				generation.addFeature(Decoration.VEGETAL_DECORATION, TREES_DUNES.getHolder().get());
				generation.addFeature(Decoration.VEGETAL_DECORATION, PATCH_YUCCA_FLOWER.getHolder().get());
				generation.addFeature(Decoration.VEGETAL_DECORATION, PATCH_ALOE_VERA.getHolder().get());
				generation.addFeature(Decoration.VEGETAL_DECORATION, PATCH_BARREL_CACTUS_DUNES.getHolder().get());
			} else {
				generation.addFeature(Decoration.LOCAL_MODIFICATIONS, DUNE_ROCK_EXTRA.getHolder().get());
				generation.addFeature(Decoration.VEGETAL_DECORATION, PATCH_MELON_DUNES.getHolder().get());
				generation.addFeature(Decoration.VEGETAL_DECORATION, FLOWER_FLOURISHING_DUNES.getHolder().get());
				generation.addFeature(Decoration.VEGETAL_DECORATION, PATCH_DUNE_GRASS.getHolder().get());
				generation.addFeature(Decoration.VEGETAL_DECORATION, FLOURISHING_DUNES_YUCCA_TREES.getHolder().get());
				generation.addFeature(Decoration.VEGETAL_DECORATION, TREES_FLOURISHING_DUNES.getHolder().get());
				generation.addFeature(Decoration.VEGETAL_DECORATION, PATCH_YUCCA_FLOWER_EXTRA.getHolder().get());
				generation.addFeature(Decoration.VEGETAL_DECORATION, PATCH_ALOE_VERA_EXTRA.getHolder().get());
				generation.addFeature(Decoration.VEGETAL_DECORATION, PATCH_BARREL_CACTUS_FLOURISHING_DUNES.getHolder().get());
				generation.addFeature(Decoration.VEGETAL_DECORATION, PATCH_AGAVE_LARGE.getHolder().get());
			}
		} else {
			if (!variant) {
				generation.addFeature(Decoration.LOCAL_MODIFICATIONS, DUNE_ROCK_MANY.getHolder().get());
				generation.addFeature(Decoration.VEGETAL_DECORATION, TREES_ROCKY_DUNES.getHolder().get());
				generation.addFeature(Decoration.VEGETAL_DECORATION, PATCH_ALOE_VERA.getHolder().get());
			} else {
				generation.addFeature(Decoration.LOCAL_MODIFICATIONS, DUNE_ROCK.getHolder().get());
				generation.addFeature(Decoration.UNDERGROUND_DECORATION, FOSSIL_DUNES.getHolder().get());
				generation.addFeature(Decoration.VEGETAL_DECORATION, TREES_PETRIFIED_DUNES.getHolder().get());
			}
			generation.addFeature(Decoration.VEGETAL_DECORATION, PATCH_BARREL_CACTUS_ROCKY_DUNES.getHolder().get());
		}
	}

	public static void spinyThicket(BiomeGenerationSettings.Builder generation) {
		OverworldBiomes.globalOverworldGeneration(generation);
		BiomeDefaultFeatures.addDefaultOres(generation);
		BiomeDefaultFeatures.addDefaultSoftDisks(generation);
		generation.addFeature(Decoration.VEGETAL_DECORATION, COARSE_DIRT.getHolder().get());
		generation.addFeature(Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_DEAD_BUSH_BADLANDS);
		BiomeDefaultFeatures.addSavannaExtraGrass(generation);
		BiomeDefaultFeatures.addDefaultMushrooms(generation);
		BiomeDefaultFeatures.addDefaultExtraVegetation(generation);

		generation.addFeature(Decoration.VEGETAL_DECORATION, PATCH_CACTUS_SPINY_THICKET.getHolder().get());
		generation.addFeature(Decoration.VEGETAL_DECORATION, PATCH_CACTUS_SPINIER_THICKET.getHolder().get());
		generation.addFeature(Decoration.VEGETAL_DECORATION, TREES_SPINY_THICKET.getHolder().get());
		generation.addFeature(Decoration.VEGETAL_DECORATION, TREES_FLOURISHING_DUNES.getHolder().get());
		generation.addFeature(Decoration.VEGETAL_DECORATION, PATCH_BARREL_CACTUS_SPINY_THICKET.getHolder().get());
		generation.addFeature(Decoration.VEGETAL_DECORATION, SINGLE_YUCCA_FLOWER.getHolder().get());
		generation.addFeature(Decoration.VEGETAL_DECORATION, PATCH_AGAVE_LARGE.getHolder().get());
	}

	public static void shrubland(BiomeGenerationSettings.Builder generation) {
		OverworldBiomes.globalOverworldGeneration(generation);
		BiomeDefaultFeatures.addDefaultOres(generation);
		BiomeDefaultFeatures.addDefaultSoftDisks(generation);
		BiomeDefaultFeatures.addDefaultMushrooms(generation);
		BiomeDefaultFeatures.addDefaultExtraVegetation(generation);

		generation.addFeature(Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_DEAD_BUSH_BADLANDS);
		generation.addFeature(Decoration.VEGETAL_DECORATION, TREES_SHRUBLAND.getHolder().get());
		generation.addFeature(Decoration.VEGETAL_DECORATION, FLOWER_SHRUBLAND.getHolder().get());
		generation.addFeature(Decoration.VEGETAL_DECORATION, PATCH_CACTUS_SHRUBLAND.getHolder().get());
		generation.addFeature(Decoration.VEGETAL_DECORATION, PATCH_BARREL_CACTUS_SHRUBLAND.getHolder().get());
	}

	public static void aspenParkland(BiomeGenerationSettings.Builder generation) {
		OverworldBiomes.globalOverworldGeneration(generation);
		BiomeDefaultFeatures.addDefaultOres(generation);
		BiomeDefaultFeatures.addDefaultSoftDisks(generation);
		BiomeDefaultFeatures.addDefaultFlowers(generation);
		BiomeDefaultFeatures.addShatteredSavannaGrass(generation);
		BiomeDefaultFeatures.addDefaultMushrooms(generation);
		BiomeDefaultFeatures.addDefaultExtraVegetation(generation);

		generation.addFeature(Decoration.VEGETAL_DECORATION, CRUSTOSE.getHolder().get());
		generation.addFeature(Decoration.VEGETAL_DECORATION, TREES_ASPEN_PARKLAND.getHolder().get());
		generation.addFeature(Decoration.VEGETAL_DECORATION, FALLEN_CRUSTOSE_LOG.getHolder().get());
		generation.addFeature(Decoration.VEGETAL_DECORATION, SINGLE_AGAVE.getHolder().get());
		generation.addFeature(Decoration.VEGETAL_DECORATION, PATCH_AGAVE_LARGE.getHolder().get());
		generation.addFeature(Decoration.VEGETAL_DECORATION, PATCH_GOLDEN_GROWTHS.getHolder().get());
	}

	public static void kousaJungle(BiomeGenerationSettings.Builder generation) {
		OverworldBiomes.globalOverworldGeneration(generation);
		BiomeDefaultFeatures.addMossyStoneBlock(generation);
		BiomeDefaultFeatures.addDefaultOres(generation);
		BiomeDefaultFeatures.addDefaultSoftDisks(generation);
		BiomeDefaultFeatures.addDefaultFlowers(generation);
		BiomeDefaultFeatures.addTaigaGrass(generation);
		BiomeDefaultFeatures.addDefaultExtraVegetation(generation);

		generation.addFeature(Decoration.VEGETAL_DECORATION, PATCH_LARGE_FERN_KOUSA.getHolder().get());
		generation.addFeature(Decoration.VEGETAL_DECORATION, CURRANT.getHolder().get());
		generation.addFeature(Decoration.VEGETAL_DECORATION, SNOWY_BAMBOO.getHolder().get());
		generation.addFeature(Decoration.VEGETAL_DECORATION, BIRCH_BUSH.getHolder().get());
		generation.addFeature(Decoration.VEGETAL_DECORATION, TREES_KOUSA_JUNGLE.getHolder().get());
		generation.addFeature(Decoration.VEGETAL_DECORATION, PASSION_VINES.getHolder().get());
	}

	public static void grimwoods(BiomeGenerationSettings.Builder generation) {
		OverworldBiomes.globalOverworldGeneration(generation);
		BiomeDefaultFeatures.addDefaultOres(generation);
		BiomeDefaultFeatures.addDefaultSoftDisks(generation);
		BiomeDefaultFeatures.addSavannaExtraGrass(generation);
		BiomeDefaultFeatures.addDefaultMushrooms(generation);
		BiomeDefaultFeatures.addDefaultExtraVegetation(generation);

		generation.addFeature(Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_DEAD_BUSH_BADLANDS);
		generation.addFeature(Decoration.VEGETAL_DECORATION, COARSE_DIRT.getHolder().get());
		generation.addFeature(Decoration.VEGETAL_DECORATION, TREES_GRIMWOODS.getHolder().get());
		generation.addFeature(Decoration.VEGETAL_DECORATION, OMINOUS_BLOCK.getHolder().get());
		generation.addFeature(Decoration.VEGETAL_DECORATION, DEAD_CURRANT.getHolder().get());
	}

	public static void laurelForest(BiomeGenerationSettings.Builder generation) {
		OverworldBiomes.globalOverworldGeneration(generation);
		BiomeDefaultFeatures.addDefaultOres(generation);
		BiomeDefaultFeatures.addDefaultSoftDisks(generation);
		BiomeDefaultFeatures.addDefaultFlowers(generation);
		BiomeDefaultFeatures.addSavannaGrass(generation);
		BiomeDefaultFeatures.addForestGrass(generation);
		BiomeDefaultFeatures.addDefaultMushrooms(generation);
		BiomeDefaultFeatures.addDefaultExtraVegetation(generation);

		generation.addFeature(Decoration.VEGETAL_DECORATION, PATCH_DEAD_BUSH_LAUREL_FOREST.getHolder().get());
		generation.addFeature(Decoration.VEGETAL_DECORATION, COARSE_DIRT_LAUREL_FOREST.getHolder().get());
		generation.addFeature(Decoration.VEGETAL_DECORATION, TREES_LAUREL_FOREST.getHolder().get());
		generation.addFeature(Decoration.VEGETAL_DECORATION, PATCH_AGAVE_LARGE.getHolder().get());
		generation.addFeature(Decoration.VEGETAL_DECORATION, PATCH_LARGE_FERN_KOUSA.getHolder().get());
		generation.addFeature(Decoration.VEGETAL_DECORATION, PATCH_GRASS_LAUREL_FOREST.getHolder().get());
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
		generation.addFeature(Decoration.VEGETAL_DECORATION, HOT_SPRINGS_ROCK.getHolder().get());
	}
}
