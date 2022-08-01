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
			//generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericFeatures.Configured.YUCCA_TREE_DESERT);
		}

		if (DataUtil.matchesKeys(biome, Biomes.WOODED_BADLANDS)) {
			//generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericFeatures.Configured.YUCCA_TREE);
		}

		if (DataUtil.matchesKeys(biome, Biomes.WINDSWEPT_SAVANNA)) {
			//generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericFeatures.Configured.YUCCA_TREE_SAVANNA);
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
	}

	public static void rainforestBasin(BiomeGenerationSettingsBuilder generation, MobSpawnSettingsBuilder spawns) {
		AtmosphericGeneration.baseRainforest(generation, spawns);
		AtmosphericGeneration.withRainforestBasinWaterFoliage(generation);
		generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericPlacedFeatures.TREES_RAINFOREST_BASIN.getHolder().get());
	}

	public static void sparseRainforestBasin(BiomeGenerationSettingsBuilder generation, MobSpawnSettingsBuilder spawns) {
		AtmosphericGeneration.baseRainforest(generation, spawns);
		AtmosphericGeneration.withSparseRainforestBasinWaterFoliage(generation);
		generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericPlacedFeatures.TREES_SPARSE_RAINFOREST_BASIN.getHolder().get());
	}

	public static void baseDunes(BiomeGenerationSettingsBuilder generation, MobSpawnSettingsBuilder spawns) {
		BiomeDefaultFeatures.desertSpawns(spawns);

		OverworldBiomes.globalOverworldGeneration(generation);
		BiomeDefaultFeatures.addDefaultOres(generation);
		BiomeDefaultFeatures.addDefaultSoftDisks(generation);
		BiomeDefaultFeatures.addDefaultMushrooms(generation);
		BiomeDefaultFeatures.addDefaultExtraVegetation(generation);
		BiomeDefaultFeatures.addFossilDecoration(generation);
		generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_DEAD_BUSH);
		//generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericFeatures.Configured.PATCH_ARID_SPROUTS);
	}

	public static void dunes(BiomeGenerationSettingsBuilder generation, MobSpawnSettingsBuilder spawns) {
		baseDunes(generation, spawns);
		//generation.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, AtmosphericFeatures.Configured.DUNE_ROCK);
		//generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericFeatures.Configured.YUCCA_TREE);
		//generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericFeatures.Configured.PATCH_YUCCA_FLOWER);
		//generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericFeatures.Configured.PATCH_ALOE_VERA);
		//generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericFeatures.Configured.PATCH_BARREL_CACTUS_DUNES);
	}

	public static void flourishingDunes(BiomeGenerationSettingsBuilder generation, MobSpawnSettingsBuilder spawns) {
		baseDunes(generation, spawns);
		BiomeDefaultFeatures.addJungleMelons(generation);
		//generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericFeatures.Configured.PATCH_GILIA);
		//generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericFeatures.Configured.PATCH_DUNE_GRASS);
		//generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericFeatures.Configured.YUCCA_TREE_BEEHIVE);
		//generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericFeatures.Configured.YUCCA_TREE_BABY);
		//generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericFeatures.Configured.PATCH_YUCCA_FLOWER_EXTRA);
		//generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericFeatures.Configured.PATCH_ALOE_VERA_EXTRA);
		//generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericFeatures.Configured.PATCH_BARREL_CACTUS_FLOURISHING_DUNES);
		//generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericFeatures.Configured.DUNE_ROCK_EXTRA);
	}

	public static void withRockyDunesFeatures(BiomeGenerationSettingsBuilder generation, MobSpawnSettingsBuilder spawns) {
		baseDunes(generation, spawns);
		//generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericFeatures.Configured.YUCCA_TREE_ROCKY_DUNES);
		//generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericFeatures.Configured.PATCH_ALOE_VERA);
		//generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericFeatures.Configured.PATCH_BARREL_CACTUS_ROCKY_DUNES);
		//generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericFeatures.Configured.DUNE_ROCK_MANY);
	}

	public static void withPetrifiedDunesFeatures(BiomeGenerationSettingsBuilder generation, MobSpawnSettingsBuilder spawns) {
		baseDunes(generation, spawns);
		//generation.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, AtmosphericFeatures.Configured.DUNE_ROCK);
		//generation.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, AtmosphericFeatures.Configured.FOSSIL_SURFACE);
		//generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericFeatures.Configured.YUCCA_TREE_PETRIFIED);
		//generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericFeatures.Configured.PATCH_BARREL_CACTUS_PETRIFIED_DUNES);
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
		BiomeDefaultFeatures.addMossyStoneBlock(generation);
		BiomeDefaultFeatures.addFerns(generation);
		BiomeDefaultFeatures.addTaigaTrees(generation);
		BiomeDefaultFeatures.addGiantTaigaVegetation(generation);
		BiomeDefaultFeatures.addCommonBerryBushes(generation);
		generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.TREES_OLD_GROWTH_SPRUCE_TAIGA);
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
