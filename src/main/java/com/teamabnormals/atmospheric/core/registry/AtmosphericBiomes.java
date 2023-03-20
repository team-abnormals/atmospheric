package com.teamabnormals.atmospheric.core.registry;

import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.atmospheric.core.other.AtmosphericGeneration;
import com.teamabnormals.blueprint.core.util.registry.BiomeSubRegistryHelper;
import com.teamabnormals.blueprint.core.util.registry.BiomeSubRegistryHelper.KeyedBiome;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.Musics;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.biome.Biome.Precipitation;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

import javax.annotation.Nullable;

@EventBusSubscriber(modid = Atmospheric.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class AtmosphericBiomes {
	public static final BiomeSubRegistryHelper HELPER = Atmospheric.REGISTRY_HELPER.getBiomeSubHelper();

	public static final KeyedBiome RAINFOREST = HELPER.createBiome("rainforest", () -> rainforest(false, false));
	public static final KeyedBiome SPARSE_RAINFOREST = HELPER.createBiome("sparse_rainforest", () -> rainforest(false, true));
	public static final KeyedBiome RAINFOREST_BASIN = HELPER.createBiome("rainforest_basin", () -> rainforest(true, false));
	public static final KeyedBiome SPARSE_RAINFOREST_BASIN = HELPER.createBiome("sparse_rainforest_basin", () -> rainforest(true, true));

	public static final KeyedBiome DUNES = HELPER.createBiome("dunes", () -> dunes(false, false));
	public static final KeyedBiome FLOURISHING_DUNES = HELPER.createBiome("flourishing_dunes", () -> dunes(false, true));
	public static final KeyedBiome ROCKY_DUNES = HELPER.createBiome("rocky_dunes", () -> dunes(true, false));
	public static final KeyedBiome PETRIFIED_DUNES = HELPER.createBiome("petrified_dunes", () -> dunes(true, true));

	public static final KeyedBiome ASPEN_PARKLAND = HELPER.createBiome("aspen_parkland", AtmosphericBiomes::aspenParkland);
	public static final KeyedBiome KOUSA_JUNGLE = HELPER.createBiome("kousa_jungle", AtmosphericBiomes::kousaJungle);
	public static final KeyedBiome GRIMWOODS = HELPER.createBiome("grimwoods", AtmosphericBiomes::grimwoods);

	public static final KeyedBiome HOT_SPRINGS = HELPER.createBiome("hot_springs", AtmosphericBiomes::hotSprings);

	private static Biome rainforest(boolean basin, boolean sparse) {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		AtmosphericGeneration.baseRainforest(generation, basin, sparse);

		MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();
		BiomeDefaultFeatures.commonSpawns(spawns);
		BiomeDefaultFeatures.farmAnimals(spawns);
		spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.PARROT, 40, 1, 2));
		spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.WOLF, 8, 4, 4));

		return biome(Precipitation.RAIN, 0.9F, 0.95F, 6675400, 408635, spawns, generation, null);
	}

	private static Biome dunes(boolean rocky, boolean variant) {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		AtmosphericGeneration.dunes(generation, rocky, variant);

		MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();
		BiomeDefaultFeatures.desertSpawns(spawns);

		return biome(Precipitation.NONE, 2.0F, 0.0F, 14988944, spawns, generation, null);
	}

	private static Biome aspenParkland() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		AtmosphericGeneration.aspenParkland(generation);

		MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();
		BiomeDefaultFeatures.commonSpawns(spawns);
		spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.WOLF, 10, 1, 1));
		spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.RABBIT, 15, 1, 1));

		return biome(Biome.Precipitation.RAIN, 2.0F, 0.0F, spawns, generation, Musics.createGameMusic(SoundEvents.MUSIC_BIOME_JUNGLE_AND_FOREST));
	}

	private static Biome kousaJungle() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		AtmosphericGeneration.kousaJungle(generation);

		MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();
		BiomeDefaultFeatures.farmAnimals(spawns);
		spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.WOLF, 8, 4, 4));
		spawns.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.OCELOT, 2, 1, 3));
		BiomeDefaultFeatures.commonSpawns(spawns);

		return biome(Biome.Precipitation.SNOW, -0.25F, 0.5F, spawns, generation, null);
	}

	private static Biome grimwoods() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		AtmosphericGeneration.grimwoods(generation);

		MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();
		BiomeDefaultFeatures.commonSpawns(spawns);
		spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.WOLF, 5, 4, 4));

		return biome(Biome.Precipitation.RAIN, 0.25F, 0.0F, 9539946, 7040342, 5403055, 1250099, 8022879, spawns, generation, null);
	}

	private static Biome hotSprings() {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
		AtmosphericGeneration.hotSprings(generation);

		MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();
		BiomeDefaultFeatures.commonSpawns(spawns);
		BiomeDefaultFeatures.farmAnimals(spawns);
		spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.WOLF, 8, 4, 4));
		spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.RABBIT, 4, 2, 3));
		spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.FOX, 8, 2, 4));

		return biome(Precipitation.NONE, 0.25F, 0.4F, 4445678, 270131, spawns, generation, null);
	}

	private static Biome biome(Precipitation precipitation, float temperature, float downfall, MobSpawnSettings.Builder spawns, BiomeGenerationSettings.Builder generation, @Nullable Music music) {
		return biome(precipitation, temperature, downfall, 12638463, spawns, generation, music);
	}

	private static Biome biome(Precipitation precipitation, float temperature, float downfall, int waterColor, int waterFogColor, MobSpawnSettings.Builder spawns, BiomeGenerationSettings.Builder generation, @Nullable Music music) {
		return biome(precipitation, temperature, downfall, waterColor, waterFogColor, 12638463, spawns, generation, music);
	}

	private static Biome biome(Precipitation precipitation, float temperature, float downfall, int fogColor, MobSpawnSettings.Builder spawns, BiomeGenerationSettings.Builder generation, @Nullable Music music) {
		return biome(precipitation, temperature, downfall, 4159204, 329011, fogColor, spawns, generation, music);
	}

	private static Biome biome(Precipitation precipitation, float temperature, float downfall, int waterColor, int waterFogColor, int fogColor, MobSpawnSettings.Builder spawns, BiomeGenerationSettings.Builder generation, @Nullable Music music) {
		return (new Biome.BiomeBuilder()).precipitation(precipitation).temperature(temperature).downfall(downfall).specialEffects((new BiomeSpecialEffects.Builder()).waterColor(waterColor).waterFogColor(waterFogColor).fogColor(fogColor).skyColor(calculateSkyColor(temperature)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).backgroundMusic(music).build()).mobSpawnSettings(spawns.build()).generationSettings(generation.build()).build();
	}

	private static Biome biome(Precipitation precipitation, float temperature, float downfall, int grassColor, int foliageColor, int waterColor, int waterFogColor, int fogColor, MobSpawnSettings.Builder spawns, BiomeGenerationSettings.Builder generation, @Nullable Music music) {
		return (new Biome.BiomeBuilder()).precipitation(precipitation).temperature(temperature).downfall(downfall).specialEffects((new BiomeSpecialEffects.Builder()).grassColorOverride(grassColor).foliageColorOverride(foliageColor).waterColor(waterColor).waterFogColor(waterFogColor).fogColor(fogColor).skyColor(calculateSkyColor(temperature)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).backgroundMusic(music).build()).mobSpawnSettings(spawns.build()).generationSettings(generation.build()).build();
	}

	private static int calculateSkyColor(float temperature) {
		float clampedTemp = Mth.clamp(temperature / 3.0F, -1.0F, 1.0F);
		return Mth.hsvToRgb(0.62222224F - clampedTemp * 0.05F, 0.5F + clampedTemp * 0.1F, 1.0F);
	}
}
