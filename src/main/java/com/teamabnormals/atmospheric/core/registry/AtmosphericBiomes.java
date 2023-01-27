package com.teamabnormals.atmospheric.core.registry;

import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.atmospheric.core.other.AtmosphericGeneration;
import com.teamabnormals.blueprint.core.util.registry.BiomeSubRegistryHelper;
import com.teamabnormals.blueprint.core.util.registry.BiomeSubRegistryHelper.KeyedBiome;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = Atmospheric.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class AtmosphericBiomes {
	public static final BiomeSubRegistryHelper HELPER = Atmospheric.REGISTRY_HELPER.getBiomeSubHelper();

	public static final KeyedBiome RAINFOREST = HELPER.createBiome("rainforest", AtmosphericBiomes::rainforest);
	public static final KeyedBiome SPARSE_RAINFOREST = HELPER.createBiome("sparse_rainforest", AtmosphericBiomes::sparseRainforest);
	public static final KeyedBiome RAINFOREST_BASIN = HELPER.createBiome("rainforest_basin", AtmosphericBiomes::rainforestBasin);
	public static final KeyedBiome SPARSE_RAINFOREST_BASIN = HELPER.createBiome("sparse_rainforest_basin", AtmosphericBiomes::sparseRainforestBasin);

	public static final KeyedBiome DUNES = HELPER.createBiome("dunes", AtmosphericBiomes::dunes);
	public static final KeyedBiome FLOURISHING_DUNES = HELPER.createBiome("flourishing_dunes", AtmosphericBiomes::flourishingDunes);
	public static final KeyedBiome ROCKY_DUNES = HELPER.createBiome("rocky_dunes", AtmosphericBiomes::rockyDunes);
	public static final KeyedBiome PETRIFIED_DUNES = HELPER.createBiome("petrified_dunes", AtmosphericBiomes::petrifiedDunes);

	public static final KeyedBiome HOT_SPRINGS = HELPER.createBiome("hot_springs", AtmosphericBiomes::hotsprings);

	private static Biome rainforest() {
		MobSpawnSettings.Builder spawns = baseRainforestSpawns();
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		AtmosphericGeneration.rainforest(generation);
		return rainforestBase(spawns, generation);
	}

	private static Biome sparseRainforest() {
		MobSpawnSettings.Builder spawns = baseRainforestSpawns();
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		AtmosphericGeneration.sparseRainforest(generation);
		return rainforestBase(spawns, generation);
	}

	private static Biome rainforestBasin() {
		MobSpawnSettings.Builder spawns = baseRainforestSpawns();
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		AtmosphericGeneration.rainforestBasin(generation);
		return rainforestBase(spawns, generation);
	}

	private static Biome sparseRainforestBasin() {
		MobSpawnSettings.Builder spawns = baseRainforestSpawns();
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		AtmosphericGeneration.sparseRainforestBasin(generation);
		return rainforestBase(spawns, generation);
	}

	private static MobSpawnSettings.Builder baseRainforestSpawns() {
		MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();
		BiomeDefaultFeatures.commonSpawns(spawns);
		BiomeDefaultFeatures.farmAnimals(spawns);
		spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.PARROT, 40, 1, 2));
		spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.WOLF, 8, 4, 4));
		return spawns;
	}

	private static Biome dunes() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		AtmosphericGeneration.dunes(generation);
		return dunesBase(generation);
	}

	private static Biome rockyDunes() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		AtmosphericGeneration.withRockyDunesFeatures(generation);
		return dunesBase(generation);
	}

	private static Biome petrifiedDunes() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		AtmosphericGeneration.withPetrifiedDunesFeatures(generation);
		return dunesBase(generation);
	}

	private static Biome flourishingDunes() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		AtmosphericGeneration.flourishingDunes(generation);
		return dunesBase(generation);
	}

	private static Biome rainforestBase(MobSpawnSettings.Builder spawns, BiomeGenerationSettings.Builder generation) {
		return (new Biome.BiomeBuilder()).precipitation(Biome.Precipitation.RAIN).temperature(0.9F).downfall(0.95F).specialEffects((new BiomeSpecialEffects.Builder()).waterColor(6675400).waterFogColor(408635).fogColor(12638463).skyColor(getSkyColorWithTemperatureModifier(0.9F)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(spawns.build()).generationSettings(generation.build()).build();
	}

	private static Biome dunesBase(BiomeGenerationSettings.Builder generation) {
		MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();
		BiomeDefaultFeatures.desertSpawns(spawns);
		return (new Biome.BiomeBuilder()).precipitation(Biome.Precipitation.NONE).temperature(2.0F).downfall(0.0F).specialEffects((new BiomeSpecialEffects.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(14988944).skyColor(getSkyColorWithTemperatureModifier(2.0F)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(spawns.build()).generationSettings(generation.build()).build();
	}

	private static Biome hotsprings() {
		MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		AtmosphericGeneration.withHotSpringsFeatures(generation, spawns);
		return (new Biome.BiomeBuilder()).precipitation(Biome.Precipitation.NONE).temperature(0.25F).downfall(0.4F).specialEffects((new BiomeSpecialEffects.Builder()).waterColor(4445678).waterFogColor(270131).fogColor(12638463).skyColor(getSkyColorWithTemperatureModifier(0.25F)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(spawns.build()).generationSettings(generation.build()).build();
	}

	private static int getSkyColorWithTemperatureModifier(float temperature) {
		float lvt_1_1_ = temperature / 3.0F;
		lvt_1_1_ = Mth.clamp(lvt_1_1_, -1.0F, 1.0F);
		return Mth.hsvToRgb(0.62222224F - lvt_1_1_ * 0.05F, 0.5F + lvt_1_1_ * 0.1F, 1.0F);
	}
}
