package com.teamabnormals.atmospheric.core.other;

import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.atmospheric.core.registry.AtmosphericBiomes;
import com.teamabnormals.atmospheric.core.registry.AtmosphericFeatures.AtmosphericPlacedFeatures;
import com.teamabnormals.blueprint.core.util.DataUtil;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.biome.OverworldBiomes;
import net.minecraft.data.worldgen.placement.AquaticPlacements;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.common.world.MobSpawnSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = Atmospheric.MOD_ID)
public class AtmosphericGeneration {

	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public static void onEarlyBiomeLoad(BiomeLoadingEvent event) {
		ResourceLocation biome = event.getName();
		BiomeGenerationSettingsBuilder generation = event.getGeneration();
		MobSpawnSettingsBuilder spawns = event.getSpawns();

		if (biome == null) return;

		if (DataUtil.matchesKeys(biome, AtmosphericBiomes.RAINFOREST.getKey()))
			rainforest(generation, spawns);
		if (DataUtil.matchesKeys(biome, AtmosphericBiomes.SPARSE_RAINFOREST.getKey()))
			sparseRainforest(generation, spawns);
		if (DataUtil.matchesKeys(biome, AtmosphericBiomes.RAINFOREST_BASIN.getKey()))
			rainforestBasin(generation, spawns);
		if (DataUtil.matchesKeys(biome, AtmosphericBiomes.SPARSE_RAINFOREST_BASIN.getKey()))
			sparseRainforestBasin(generation, spawns);

		if (DataUtil.matchesKeys(biome, AtmosphericBiomes.DUNES.getKey()))
			dunes(generation, spawns);
		if (DataUtil.matchesKeys(biome, AtmosphericBiomes.ROCKY_DUNES.getKey()))
			withRockyDunesFeatures(generation, spawns);
		if (DataUtil.matchesKeys(biome, AtmosphericBiomes.PETRIFIED_DUNES.getKey()))
			withPetrifiedDunesFeatures(generation, spawns);
		if (DataUtil.matchesKeys(biome, AtmosphericBiomes.FLOURISHING_DUNES.getKey()))
			flourishingDunes(generation, spawns);

		if (DataUtil.matchesKeys(biome, AtmosphericBiomes.HOT_SPRINGS.getKey()))
			withHotSpringsFeatures(generation, spawns);
	}

	@SubscribeEvent
	public static void onBiomeLoad(BiomeLoadingEvent event) {
		ResourceLocation biome = event.getName();
		BiomeGenerationSettingsBuilder generation = event.getGeneration();

		if (biome == null) return;

		if (DataUtil.matchesKeys(biome, Biomes.DESERT)) {
			generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericPlacedFeatures.DESERT_YUCCA_TREES.getHolder().get());
		}

		if (DataUtil.matchesKeys(biome, Biomes.WOODED_BADLANDS)) {
			generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericPlacedFeatures.TREES_WOODED_BADLANDS.getHolder().get());
		}

		if (DataUtil.matchesKeys(biome, Biomes.WINDSWEPT_SAVANNA)) {
			generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericPlacedFeatures.WINDSWEPT_SAVANNA_YUCCA_TREES.getHolder().get());
		}
	}

	public static void baseRainforest(BiomeGenerationSettingsBuilder generation, MobSpawnSettingsBuilder spawns) {
		BiomeDefaultFeatures.commonSpawns(spawns);
		BiomeDefaultFeatures.farmAnimals(spawns);
		spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.PARROT, 40, 1, 2));
		spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.WOLF, 8, 4, 4));

		OverworldBiomes.globalOverworldGeneration(generation);
		BiomeDefaultFeatures.addDefaultOres(generation);
		BiomeDefaultFeatures.addDefaultSoftDisks(generation);
		BiomeDefaultFeatures.addDefaultMushrooms(generation);
		BiomeDefaultFeatures.addDefaultExtraVegetation(generation);

		BiomeDefaultFeatures.addMossyStoneBlock(generation);
		BiomeDefaultFeatures.addFerns(generation);
		AtmosphericGeneration.withRainforestFoliage(generation);
	}

	public static void rainforest(BiomeGenerationSettingsBuilder generation, MobSpawnSettingsBuilder spawns) {
		AtmosphericGeneration.baseRainforest(generation, spawns);
		AtmosphericGeneration.withRainforestWaterFoliage(generation);
		generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericPlacedFeatures.TREES_RAINFOREST.getHolder().get());
	}

	public static void sparseRainforest(BiomeGenerationSettingsBuilder generation, MobSpawnSettingsBuilder spawns) {
		AtmosphericGeneration.baseRainforest(generation, spawns);
		AtmosphericGeneration.withRainforestWaterFoliage(generation);
		generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericPlacedFeatures.TREES_SPARSE_RAINFOREST.getHolder().get());
		generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericPlacedFeatures.BUSHES_SPARSE_RAINFOREST.getHolder().get());
	}

	public static void rainforestBasin(BiomeGenerationSettingsBuilder generation, MobSpawnSettingsBuilder spawns) {
		AtmosphericGeneration.baseRainforest(generation, spawns);
		AtmosphericGeneration.withRainforestBasinWaterFoliage(generation);
		generation.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, AtmosphericPlacedFeatures.OCEAN_FLOOR_RAISER.getHolder().get());
		generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericPlacedFeatures.TREES_RAINFOREST_BASIN.getHolder().get());
		generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericPlacedFeatures.OAK_RAINFOREST_BASIN.getHolder().get());
	}

	public static void sparseRainforestBasin(BiomeGenerationSettingsBuilder generation, MobSpawnSettingsBuilder spawns) {
		AtmosphericGeneration.baseRainforest(generation, spawns);
		AtmosphericGeneration.withSparseRainforestBasinWaterFoliage(generation);
		generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericPlacedFeatures.TREES_SPARSE_RAINFOREST_BASIN.getHolder().get());
		generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericPlacedFeatures.OAK_RAINFOREST_BASIN.getHolder().get());
	}

	public static void baseDunes(BiomeGenerationSettingsBuilder generation, MobSpawnSettingsBuilder spawns) {
		BiomeDefaultFeatures.desertSpawns(spawns);

		OverworldBiomes.globalOverworldGeneration(generation);
		BiomeDefaultFeatures.addDefaultOres(generation);
		BiomeDefaultFeatures.addDefaultSoftDisks(generation);
		BiomeDefaultFeatures.addDefaultMushrooms(generation);
		BiomeDefaultFeatures.addDefaultExtraVegetation(generation);
		BiomeDefaultFeatures.addFossilDecoration(generation);
		generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_DEAD_BUSH_2);
		generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericPlacedFeatures.PATCH_ARID_SPROUTS.getHolder().get());
	}

	public static void dunes(BiomeGenerationSettingsBuilder generation, MobSpawnSettingsBuilder spawns) {
		baseDunes(generation, spawns);
		generation.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, AtmosphericPlacedFeatures.DUNE_ROCK.getHolder().get());
		generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericPlacedFeatures.TREES_DUNES.getHolder().get());
		generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericPlacedFeatures.PATCH_YUCCA_FLOWER.getHolder().get());
		generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericPlacedFeatures.PATCH_ALOE_VERA.getHolder().get());
		generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericPlacedFeatures.PATCH_BARREL_CACTUS_DUNES.getHolder().get());
	}

	public static void flourishingDunes(BiomeGenerationSettingsBuilder generation, MobSpawnSettingsBuilder spawns) {
		baseDunes(generation, spawns);
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

	public static void withRockyDunesFeatures(BiomeGenerationSettingsBuilder generation, MobSpawnSettingsBuilder spawns) {
		baseDunes(generation, spawns);
		generation.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, AtmosphericPlacedFeatures.DUNE_ROCK_MANY.getHolder().get());
		generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericPlacedFeatures.TREES_ROCKY_DUNES.getHolder().get());
		generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericPlacedFeatures.PATCH_ALOE_VERA.getHolder().get());
		generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericPlacedFeatures.PATCH_BARREL_CACTUS_ROCKY_DUNES.getHolder().get());
	}

	public static void withPetrifiedDunesFeatures(BiomeGenerationSettingsBuilder generation, MobSpawnSettingsBuilder spawns) {
		baseDunes(generation, spawns);
		generation.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, AtmosphericPlacedFeatures.DUNE_ROCK.getHolder().get());
		generation.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, AtmosphericPlacedFeatures.FOSSIL_DUNES.getHolder().get());
		generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericPlacedFeatures.TREES_PETRIFIED_DUNES.getHolder().get());
		generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericPlacedFeatures.PATCH_BARREL_CACTUS_PETRIFIED_DUNES.getHolder().get());
	}

	public static void withHotSpringsFeatures(BiomeGenerationSettingsBuilder generation, MobSpawnSettingsBuilder spawns) {
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

	public static void withRainforestFoliage(BiomeGenerationSettings.Builder generation) {
		BiomeDefaultFeatures.addSavannaGrass(generation);
		BiomeDefaultFeatures.addJungleGrass(generation);
		BiomeDefaultFeatures.addJungleVines(generation);
		BiomeDefaultFeatures.addDefaultFlowers(generation);
		BiomeDefaultFeatures.addForestFlowers(generation);
		generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericPlacedFeatures.PODZOL.getHolder().get());
		generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericPlacedFeatures.PASSION_VINES.getHolder().get());
		generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericPlacedFeatures.MONKEY_BRUSH.getHolder().get());
	}

	public static void withRainforestWaterFoliage(BiomeGenerationSettings.Builder generation) {
		generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericPlacedFeatures.PATCH_WATER_HYACINTH.getHolder().get());
		generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericPlacedFeatures.PATCH_WATERLILY_RAINFOREST.getHolder().get());
		generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AquaticPlacements.SEAGRASS_SWAMP);
	}

	public static void withRainforestBasinWaterFoliage(BiomeGenerationSettings.Builder generation) {
		generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_WATERLILY);
		generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AquaticPlacements.SEAGRASS_DEEP_WARM);
		generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericPlacedFeatures.PATCH_WATER_HYACINTH.getHolder().get());
	}

	public static void withSparseRainforestBasinWaterFoliage(BiomeGenerationSettings.Builder generation) {
		generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_WATERLILY);
		generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AquaticPlacements.SEAGRASS_DEEP_WARM);
		generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericPlacedFeatures.PATCH_WATER_HYACINTH_SPARSE.getHolder().get());
	}
}
