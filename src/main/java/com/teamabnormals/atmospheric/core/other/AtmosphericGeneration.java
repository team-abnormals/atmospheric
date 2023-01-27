package com.teamabnormals.atmospheric.core.other;

import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.atmospheric.core.registry.AtmosphericFeatures.AtmosphericPlacedFeatures;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.biome.OverworldBiomes;
import net.minecraft.data.worldgen.placement.AquaticPlacements;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = Atmospheric.MOD_ID)
public class AtmosphericGeneration {

	public static void baseRainforest(BiomeGenerationSettings.Builder generation) {
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
		BiomeDefaultFeatures.addJungleVines(generation);
		generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericPlacedFeatures.PODZOL.getHolder().get());
		generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericPlacedFeatures.PASSION_VINES.getHolder().get());
		generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericPlacedFeatures.MONKEY_BRUSH.getHolder().get());
	}

	public static void rainforest(BiomeGenerationSettings.Builder generation) {
		baseRainforest(generation);
		generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericPlacedFeatures.TREES_RAINFOREST.getHolder().get());
		AtmosphericGeneration.withRainforestWaterFoliage(generation);
	}

	public static void sparseRainforest(BiomeGenerationSettings.Builder generation) {
		baseRainforest(generation);
		generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericPlacedFeatures.TREES_SPARSE_RAINFOREST.getHolder().get());
		generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericPlacedFeatures.BUSHES_SPARSE_RAINFOREST.getHolder().get());
		AtmosphericGeneration.withRainforestWaterFoliage(generation);
	}

	public static void rainforestBasin(BiomeGenerationSettings.Builder generation) {
		baseRainforest(generation);
		generation.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, AtmosphericPlacedFeatures.OCEAN_FLOOR_RAISER.getHolder().get());
		generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericPlacedFeatures.TREES_RAINFOREST_BASIN.getHolder().get());
		generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericPlacedFeatures.OAK_RAINFOREST_BASIN.getHolder().get());
		AtmosphericGeneration.withRainforestBasinWaterFoliage(generation);
	}

	public static void sparseRainforestBasin(BiomeGenerationSettings.Builder generation) {
		baseRainforest(generation);
		generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericPlacedFeatures.TREES_SPARSE_RAINFOREST_BASIN.getHolder().get());
		generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericPlacedFeatures.OAK_RAINFOREST_BASIN.getHolder().get());
		AtmosphericGeneration.withSparseRainforestBasinWaterFoliage(generation);
	}

	public static void baseDunes(BiomeGenerationSettings.Builder generation) {
		BiomeDefaultFeatures.addFossilDecoration(generation);
		OverworldBiomes.globalOverworldGeneration(generation);
		BiomeDefaultFeatures.addDefaultOres(generation);
		BiomeDefaultFeatures.addDefaultSoftDisks(generation);
		generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_DEAD_BUSH_2);
		BiomeDefaultFeatures.addDefaultMushrooms(generation);
		BiomeDefaultFeatures.addDefaultExtraVegetation(generation);
		generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericPlacedFeatures.PATCH_ARID_SPROUTS.getHolder().get());
	}

	public static void dunes(BiomeGenerationSettings.Builder generation) {
		baseDunes(generation);
		generation.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, AtmosphericPlacedFeatures.DUNE_ROCK.getHolder().get());
		generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericPlacedFeatures.TREES_DUNES.getHolder().get());
		generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericPlacedFeatures.PATCH_YUCCA_FLOWER.getHolder().get());
		generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericPlacedFeatures.PATCH_ALOE_VERA.getHolder().get());
		generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericPlacedFeatures.PATCH_BARREL_CACTUS_DUNES.getHolder().get());
	}

	public static void flourishingDunes(BiomeGenerationSettings.Builder generation) {
		baseDunes(generation);
		generation.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, AtmosphericPlacedFeatures.DUNE_ROCK_EXTRA.getHolder().get());
		generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericPlacedFeatures.PATCH_MELON_DUNES.getHolder().get());
		generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericPlacedFeatures.FLOWER_FLOURISHING_DUNES.getHolder().get());
		generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericPlacedFeatures.PATCH_DUNE_GRASS.getHolder().get());
		generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericPlacedFeatures.FLOURISHING_DUNES_YUCCA_TREES.getHolder().get());
		generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericPlacedFeatures.TREES_FLOURISHING_DUNES.getHolder().get());
		generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericPlacedFeatures.PATCH_YUCCA_FLOWER_EXTRA.getHolder().get());
		generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericPlacedFeatures.PATCH_ALOE_VERA_EXTRA.getHolder().get());
		generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericPlacedFeatures.PATCH_BARREL_CACTUS_FLOURISHING_DUNES.getHolder().get());
	}

	public static void withRockyDunesFeatures(BiomeGenerationSettings.Builder generation) {
		baseDunes(generation);
		generation.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, AtmosphericPlacedFeatures.DUNE_ROCK_MANY.getHolder().get());
		generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericPlacedFeatures.TREES_ROCKY_DUNES.getHolder().get());
		generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericPlacedFeatures.PATCH_ALOE_VERA.getHolder().get());
		generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericPlacedFeatures.PATCH_BARREL_CACTUS_ROCKY_DUNES.getHolder().get());
	}

	public static void withPetrifiedDunesFeatures(BiomeGenerationSettings.Builder generation) {
		baseDunes(generation);
		generation.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, AtmosphericPlacedFeatures.DUNE_ROCK.getHolder().get());
		generation.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, AtmosphericPlacedFeatures.FOSSIL_DUNES.getHolder().get());
		generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericPlacedFeatures.TREES_PETRIFIED_DUNES.getHolder().get());
		generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericPlacedFeatures.PATCH_BARREL_CACTUS_PETRIFIED_DUNES.getHolder().get());
	}

	public static void withHotSpringsFeatures(BiomeGenerationSettings.Builder generation, MobSpawnSettings.Builder spawns) {
		BiomeDefaultFeatures.commonSpawns(spawns);
		BiomeDefaultFeatures.farmAnimals(spawns);
		spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.WOLF, 8, 4, 4));
		spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.RABBIT, 4, 2, 3));
		spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.FOX, 8, 2, 4));

		OverworldBiomes.globalOverworldGeneration(generation);
		BiomeDefaultFeatures.addDefaultOres(generation);
		BiomeDefaultFeatures.addDefaultSoftDisks(generation);
		BiomeDefaultFeatures.addDefaultMushrooms(generation);
		BiomeDefaultFeatures.addDefaultExtraVegetation(generation);
		BiomeDefaultFeatures.addFerns(generation);
		BiomeDefaultFeatures.addTaigaTrees(generation);
		BiomeDefaultFeatures.addGiantTaigaVegetation(generation);
		BiomeDefaultFeatures.addCommonBerryBushes(generation);
		generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.TREES_OLD_GROWTH_SPRUCE_TAIGA);
		generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericPlacedFeatures.HOT_SPRINGS_ROCK.getHolder().get());
	}

	public static void withRainforestWaterFoliage(BiomeGenerationSettings.Builder generation) {
		generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericPlacedFeatures.PATCH_WATER_HYACINTH.getHolder().get());
		generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericPlacedFeatures.PATCH_WATERLILY_RAINFOREST.getHolder().get());
		generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AquaticPlacements.SEAGRASS_SWAMP);
	}

	public static void withRainforestBasinWaterFoliage(BiomeGenerationSettings.Builder generation) {
		generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AquaticPlacements.SEAGRASS_DEEP_WARM);
		generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericPlacedFeatures.PATCH_WATERLILY_RAINFOREST_BASIN.getHolder().get());
		generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericPlacedFeatures.PATCH_WATER_HYACINTH.getHolder().get());
	}

	public static void withSparseRainforestBasinWaterFoliage(BiomeGenerationSettings.Builder generation) {
		generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AquaticPlacements.SEAGRASS_DEEP_WARM);
		generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericPlacedFeatures.PATCH_WATERLILY_RAINFOREST_BASIN.getHolder().get());
		generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericPlacedFeatures.PATCH_WATER_HYACINTH_SPARSE.getHolder().get());
	}
}
