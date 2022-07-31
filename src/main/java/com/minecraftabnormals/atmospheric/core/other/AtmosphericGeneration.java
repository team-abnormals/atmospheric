package com.minecraftabnormals.atmospheric.core.other;

import com.minecraftabnormals.atmospheric.core.Atmospheric;
import com.minecraftabnormals.atmospheric.core.registry.AtmosphericBiomes;
import com.teamabnormals.blueprint.core.util.DataUtil;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.common.world.MobSpawnSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Atmospheric.MOD_ID)
public class AtmosphericGeneration {

	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public static void onEarlyBiomeLoad(BiomeLoadingEvent event) {
		ResourceLocation biome = event.getName();
		BiomeGenerationSettingsBuilder generation = event.getGeneration();
		MobSpawnSettingsBuilder spawns = event.getSpawns();

		if (biome == null) return;

		if (DataUtil.matchesKeys(biome, AtmosphericBiomes.RAINFOREST.getKey(), AtmosphericBiomes.RAINFOREST_PLATEAU.getKey()))
			withRainforestFeatures(generation, spawns);
		if (DataUtil.matchesKeys(biome, AtmosphericBiomes.RAINFOREST_MOUNTAINS.getKey()))
			withRainforestMountainsFeatures(generation, spawns);
		if (DataUtil.matchesKeys(biome, AtmosphericBiomes.SPARSE_RAINFOREST_PLATEAU.getKey()))
			withSparseRainforestPlateauFeatures(generation, spawns);
		if (DataUtil.matchesKeys(biome, AtmosphericBiomes.RAINFOREST_BASIN.getKey()))
			withRainforestBasinFeatures(generation, spawns);
		if (DataUtil.matchesKeys(biome, AtmosphericBiomes.SPARSE_RAINFOREST_BASIN.getKey()))
			withSparseRainforestBasinFeatures(generation, spawns);

		if (DataUtil.matchesKeys(biome, AtmosphericBiomes.DUNES.getKey(), AtmosphericBiomes.DUNES_HILLS.getKey()))
			withDunesFeatures(generation, spawns);
		if (DataUtil.matchesKeys(biome, AtmosphericBiomes.ROCKY_DUNES.getKey(), AtmosphericBiomes.ROCKY_DUNES_HILLS.getKey()))
			withRockyDunesFeatures(generation, spawns);
		if (DataUtil.matchesKeys(biome, AtmosphericBiomes.PETRIFIED_DUNES.getKey()))
			withPetrifiedDunesFeatures(generation, spawns);
		if (DataUtil.matchesKeys(biome, AtmosphericBiomes.FLOURISHING_DUNES.getKey()))
			withFlourishingDunesFeatures(generation, spawns);

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

	public static void withBaseRainforestFeatures(BiomeGenerationSettingsBuilder builder, MobSpawnSettingsBuilder spawns) {
		//BiomeDefaultFeatures.addDefaultOverworldLandStructures(builder);
		//BiomeDefaultFeatures.addDefaultCarvers(builder);
		//BiomeDefaultFeatures.addDefaultLakes(builder);
		BiomeDefaultFeatures.addDefaultMonsterRoom(builder);
		BiomeDefaultFeatures.addDefaultUndergroundVariety(builder);
		BiomeDefaultFeatures.addDefaultOres(builder);
		BiomeDefaultFeatures.addDefaultSoftDisks(builder);
		BiomeDefaultFeatures.addDefaultMushrooms(builder);
		BiomeDefaultFeatures.addDefaultExtraVegetation(builder);
		BiomeDefaultFeatures.addDefaultSprings(builder);
		BiomeDefaultFeatures.addSurfaceFreezing(builder);
		BiomeDefaultFeatures.addMossyStoneBlock(builder);
		BiomeDefaultFeatures.addFerns(builder);
		AtmosphericGeneration.withRainforestFoliage(builder);
		//builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericFeatures.Configured.PODZOL);
		BiomeDefaultFeatures.commonSpawns(spawns);
		BiomeDefaultFeatures.farmAnimals(spawns);
		spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.PARROT, 40, 1, 2));
		spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.WOLF, 8, 4, 4));
	}

	public static void withRainforestFeatures(BiomeGenerationSettingsBuilder builder, MobSpawnSettingsBuilder spawns) {
		AtmosphericGeneration.withBaseRainforestFeatures(builder, spawns);
		AtmosphericGeneration.withRainforestWaterFoliage(builder);
		AtmosphericGeneration.withRainforestTrees(builder);
		//builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericFeatures.Configured.ROSEWOOD_TREE);
	}

	public static void withRainforestMountainsFeatures(BiomeGenerationSettingsBuilder builder, MobSpawnSettingsBuilder spawns) {
		AtmosphericGeneration.withBaseRainforestFeatures(builder, spawns);
		AtmosphericGeneration.withRainforestWaterFoliage(builder);
		AtmosphericGeneration.withRainforestTrees(builder);
		//builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericFeatures.Configured.ROSEWOOD_TREE_EXTRA);
	}

	public static void withSparseRainforestPlateauFeatures(BiomeGenerationSettingsBuilder builder, MobSpawnSettingsBuilder spawns) {
		AtmosphericGeneration.withBaseRainforestFeatures(builder, spawns);
		AtmosphericGeneration.withRainforestWaterFoliage(builder);
		AtmosphericGeneration.withSparseRainforestPlateauTrees(builder);
	}

	public static void withRainforestBasinFeatures(BiomeGenerationSettingsBuilder builder, MobSpawnSettingsBuilder spawns) {
		AtmosphericGeneration.withBaseRainforestFeatures(builder, spawns);
		AtmosphericGeneration.withRainforestBasinWaterFoliage(builder);
		AtmosphericGeneration.withRainforestBasinTrees(builder);
	}

	public static void withSparseRainforestBasinFeatures(BiomeGenerationSettingsBuilder builder, MobSpawnSettingsBuilder spawns) {
		AtmosphericGeneration.withBaseRainforestFeatures(builder, spawns);
		AtmosphericGeneration.withSparseRainforestBasinWaterFoliage(builder);
		AtmosphericGeneration.withSparseRainforestBasinTrees(builder);
	}

	public static void withBaseDunesFeatures(BiomeGenerationSettingsBuilder builder, MobSpawnSettingsBuilder spawns) {
		//BiomeDefaultFeatures.addDefaultOverworldLandStructures(builder);
		//BiomeDefaultFeatures.addDefaultCarvers(builder);
		BiomeDefaultFeatures.addDefaultMonsterRoom(builder);
		BiomeDefaultFeatures.addDefaultUndergroundVariety(builder);
		BiomeDefaultFeatures.addDefaultOres(builder);
		BiomeDefaultFeatures.addDefaultSoftDisks(builder);
		BiomeDefaultFeatures.addDefaultMushrooms(builder);
		BiomeDefaultFeatures.addSurfaceFreezing(builder);
		BiomeDefaultFeatures.addFossilDecoration(builder);
		//builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Features.PATCH_DEAD_BUSH);
		//builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericFeatures.Configured.PATCH_ARID_SPROUTS);
		//builder.addStructureStart(AtmosphericStructures.Configured.ARID_SHRINE);
		BiomeDefaultFeatures.desertSpawns(spawns);
	}

	public static void withDunesFeatures(BiomeGenerationSettingsBuilder builder, MobSpawnSettingsBuilder spawns) {
		withBaseDunesFeatures(builder, spawns);
		//builder.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, AtmosphericFeatures.Configured.DUNE_ROCK);
		//builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericFeatures.Configured.YUCCA_TREE);
		//builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericFeatures.Configured.PATCH_YUCCA_FLOWER);
		//builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericFeatures.Configured.PATCH_ALOE_VERA);
		//builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericFeatures.Configured.PATCH_BARREL_CACTUS_DUNES);
	}

	public static void withFlourishingDunesFeatures(BiomeGenerationSettingsBuilder builder, MobSpawnSettingsBuilder spawns) {
		withBaseDunesFeatures(builder, spawns);
		//builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Features.PATCH_MELON);
		//builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericFeatures.Configured.PATCH_GILIA);
		//builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericFeatures.Configured.PATCH_DUNE_GRASS);
		//builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericFeatures.Configured.YUCCA_TREE_BEEHIVE);
		//builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericFeatures.Configured.YUCCA_TREE_BABY);
		//builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericFeatures.Configured.PATCH_YUCCA_FLOWER_EXTRA);
		//builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericFeatures.Configured.PATCH_ALOE_VERA_EXTRA);
		//builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericFeatures.Configured.PATCH_BARREL_CACTUS_FLOURISHING_DUNES);
		//builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericFeatures.Configured.DUNE_ROCK_EXTRA);
	}

	public static void withRockyDunesFeatures(BiomeGenerationSettingsBuilder builder, MobSpawnSettingsBuilder spawns) {
		withBaseDunesFeatures(builder, spawns);
		//builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericFeatures.Configured.YUCCA_TREE_ROCKY_DUNES);
		//builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericFeatures.Configured.PATCH_ALOE_VERA);
		//builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericFeatures.Configured.PATCH_BARREL_CACTUS_ROCKY_DUNES);
		//builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericFeatures.Configured.DUNE_ROCK_MANY);
	}

	public static void withPetrifiedDunesFeatures(BiomeGenerationSettingsBuilder builder, MobSpawnSettingsBuilder spawns) {
		withBaseDunesFeatures(builder, spawns);
		//builder.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, AtmosphericFeatures.Configured.DUNE_ROCK);
		//builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, AtmosphericFeatures.Configured.FOSSIL_SURFACE);
		//builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericFeatures.Configured.YUCCA_TREE_PETRIFIED);
		//builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericFeatures.Configured.PATCH_BARREL_CACTUS_PETRIFIED_DUNES);
	}

	public static void withHotSpringsFeatures(BiomeGenerationSettingsBuilder builder, MobSpawnSettingsBuilder spawns) {
		//BiomeDefaultFeatures.addDefaultOverworldLandStructures(builder);
		//BiomeDefaultFeatures.addDefaultCarvers(builder);
		//BiomeDefaultFeatures.addDefaultLakes(builder);
		BiomeDefaultFeatures.addDefaultMonsterRoom(builder);
		BiomeDefaultFeatures.addDefaultUndergroundVariety(builder);
		BiomeDefaultFeatures.addDefaultOres(builder);
		BiomeDefaultFeatures.addDefaultSoftDisks(builder);
		BiomeDefaultFeatures.addDefaultMushrooms(builder);
		BiomeDefaultFeatures.addDefaultSprings(builder);
		BiomeDefaultFeatures.addSurfaceFreezing(builder);
		BiomeDefaultFeatures.addMossyStoneBlock(builder);
		BiomeDefaultFeatures.addFerns(builder);
		BiomeDefaultFeatures.addTaigaTrees(builder);
		BiomeDefaultFeatures.addGiantTaigaVegetation(builder);
		//BiomeDefaultFeatures.addSparseBerryBushes(builder);
		//builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Features.TREES_GIANT_SPRUCE);

		BiomeDefaultFeatures.farmAnimals(spawns);
		spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.WOLF, 8, 4, 4));
		spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.RABBIT, 4, 2, 3));
		spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.FOX, 8, 2, 4));
		BiomeDefaultFeatures.commonSpawns(spawns);
	}

	public static void withRainforestFoliage(BiomeGenerationSettings.Builder builder) {
		//builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Features.PATCH_TALL_GRASS);
		//builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Features.PATCH_GRASS_JUNGLE);
		//builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Features.VINES);
		//builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericFeatures.Configured.PASSION_VINES);
		//builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Features.FOREST_FLOWER_VEGETATION_COMMON);
		//builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericFeatures.Configured.PATCH_WARM_MONKEY_BRUSH);
		//builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericFeatures.Configured.PATCH_HOT_MONKEY_BRUSH);
		//builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericFeatures.Configured.PATCH_SCALDING_MONKEY_BRUSH);
	}

	public static void withRainforestWaterFoliage(BiomeGenerationSettings.Builder builder) {
		//builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericFeatures.Configured.PATCH_WATER_HYACINTH);
		//builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericFeatures.Configured.PATCH_WATERLILLY_RAINFOREST);
		//builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Features.SEAGRASS_SWAMP);
	}

	public static void withRainforestTrees(BiomeGenerationSettings.Builder builder) {
		//builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericFeatures.Configured.OAK_TREE_RAINFOREST);
		//builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericFeatures.Configured.MORADO_TREE);
		//builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericFeatures.Configured.RAINFOREST_BUSH);
	}

	public static void withRainforestBasinTrees(BiomeGenerationSettings.Builder builder) {
		//builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericFeatures.Configured.OAK_TREE_RAINFOREST);
		//builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericFeatures.Configured.RAINFOREST_BUSH);
		//builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericFeatures.Configured.ROSEWOOD_WATER_TREE);
		//builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericFeatures.Configured.MORADO_WATER_TREE);
	}

	public static void withSparseRainforestBasinTrees(BiomeGenerationSettings.Builder builder) {
		//builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericFeatures.Configured.OAK_TREE_RAINFOREST);
		//builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericFeatures.Configured.RAINFOREST_BUSH);
		//builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericFeatures.Configured.ROSEWOOD_WATER_TREE_SPARSE);
		//builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericFeatures.Configured.MORADO_WATER_TREE_SPARSE);
	}

	public static void withSparseRainforestPlateauTrees(BiomeGenerationSettings.Builder builder) {
		//builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericFeatures.Configured.ROSEWOOD_TREE_SPARSE);
		//builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericFeatures.Configured.MORADO_TREE_SPARSE);
		//builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericFeatures.Configured.RAINFOREST_BUSH);
	}

	public static void withRainforestBasinWaterFoliage(BiomeGenerationSettings.Builder builder) {
		//builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericFeatures.Configured.PATCH_WATER_HYACINTH);
		//builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Features.PATCH_WATERLILLY);
		//builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Features.SEAGRASS_DEEP_WARM);
	}

	public static void withSparseRainforestBasinWaterFoliage(BiomeGenerationSettings.Builder builder) {
		//builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AtmosphericFeatures.Configured.PATCH_WATER_HYACINTH_SPARSE);
		//builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Features.PATCH_WATERLILLY);
		//builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Features.SEAGRASS_DEEP_WARM);
	}
}
