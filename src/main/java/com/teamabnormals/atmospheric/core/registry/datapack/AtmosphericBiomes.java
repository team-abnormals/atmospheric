package com.teamabnormals.atmospheric.core.registry.datapack;

import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.atmospheric.core.other.AtmosphericGeneration;
import com.teamabnormals.atmospheric.core.registry.AtmosphericEntityTypes;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.Musics;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import javax.annotation.Nullable;
import java.util.List;

public class AtmosphericBiomes {
	public static final ResourceKey<Biome> RAINFOREST = createKey("rainforest");
	public static final ResourceKey<Biome> SPARSE_RAINFOREST = createKey("sparse_rainforest");
	public static final ResourceKey<Biome> RAINFOREST_BASIN = createKey("rainforest_basin");
	public static final ResourceKey<Biome> SPARSE_RAINFOREST_BASIN = createKey("sparse_rainforest_basin");
	public static final ResourceKey<Biome> DUNES = createKey("dunes");
	public static final ResourceKey<Biome> FLOURISHING_DUNES = createKey("flourishing_dunes");
	public static final ResourceKey<Biome> ROCKY_DUNES = createKey("rocky_dunes");
	public static final ResourceKey<Biome> PETRIFIED_DUNES = createKey("petrified_dunes");
	public static final ResourceKey<Biome> SPINY_THICKET = createKey("spiny_thicket");
	public static final ResourceKey<Biome> SCRUBLAND = createKey("scrubland");
	public static final ResourceKey<Biome> SNOWY_SCRUBLAND = createKey("snowy_scrubland");
	public static final ResourceKey<Biome> ASPEN_PARKLAND = createKey("aspen_parkland");
	public static final ResourceKey<Biome> LAUREL_FOREST = createKey("laurel_forest");
	public static final ResourceKey<Biome> KOUSA_JUNGLE = createKey("kousa_jungle");
	public static final ResourceKey<Biome> GRIMWOODS = createKey("grimwoods");
	public static final ResourceKey<Biome> HOT_SPRINGS = createKey("hot_springs");

	public static final List<ResourceKey<Biome>> NATURAL_BIOMES = List.of(
			RAINFOREST, SPARSE_RAINFOREST, RAINFOREST_BASIN, SPARSE_RAINFOREST_BASIN,
			DUNES, FLOURISHING_DUNES, ROCKY_DUNES, PETRIFIED_DUNES,
			SPINY_THICKET, SCRUBLAND, SNOWY_SCRUBLAND,
			ASPEN_PARKLAND, LAUREL_FOREST, KOUSA_JUNGLE
	);

	public static void bootstrap(BootstrapContext<Biome> context) {
		HolderGetter<PlacedFeature> features = context.lookup(Registries.PLACED_FEATURE);
		HolderGetter<ConfiguredWorldCarver<?>> carvers = context.lookup(Registries.CONFIGURED_CARVER);

		context.register(RAINFOREST, rainforest(features, carvers, false, false));
		context.register(SPARSE_RAINFOREST, rainforest(features, carvers, false, true));
		context.register(RAINFOREST_BASIN, rainforest(features, carvers, true, false));
		context.register(SPARSE_RAINFOREST_BASIN, rainforest(features, carvers, true, true));

		context.register(DUNES, dunes(features, carvers, false, false));
		context.register(FLOURISHING_DUNES, dunes(features, carvers, false, true));
		context.register(ROCKY_DUNES, dunes(features, carvers, true, false));
		context.register(PETRIFIED_DUNES, dunes(features, carvers, true, true));

		context.register(SPINY_THICKET, spinyThicket(features, carvers));
		context.register(SCRUBLAND, scrubland(features, carvers, false));
		context.register(SNOWY_SCRUBLAND, scrubland(features, carvers, true));

		context.register(ASPEN_PARKLAND, aspenParkland(features, carvers));
		context.register(LAUREL_FOREST, laurelForest(features, carvers));
		context.register(KOUSA_JUNGLE, kousaJungle(features, carvers));

		context.register(GRIMWOODS, grimwoods(features, carvers));
		context.register(HOT_SPRINGS, hotSprings(features, carvers));
	}

	public static ResourceKey<Biome> createKey(String name) {
		return ResourceKey.create(Registries.BIOME, Atmospheric.location(name));
	}

	private static Biome rainforest(HolderGetter<PlacedFeature> features, HolderGetter<ConfiguredWorldCarver<?>> carvers, boolean basin, boolean sparse) {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder(features, carvers);
		AtmosphericGeneration.rainforest(generation, basin, sparse);

		MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();
		BiomeDefaultFeatures.commonSpawns(spawns);
		BiomeDefaultFeatures.farmAnimals(spawns);
		spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.PARROT, 40, 1, 2));
		spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.WOLF, 8, 4, 4));

		spawns.addSpawn(MobCategory.WATER_AMBIENT, new MobSpawnSettings.SpawnerData(EntityType.SQUID, 1, 1, 1));
		spawns.addSpawn(MobCategory.WATER_AMBIENT, new MobSpawnSettings.SpawnerData(AtmosphericEntityTypes.TETRA.get(), 20, 3, 12));

		return biome(true, 0.9F, 0.95F, 6675400, 408635, spawns, generation, null);
	}

	private static Biome dunes(HolderGetter<PlacedFeature> features, HolderGetter<ConfiguredWorldCarver<?>> carvers, boolean rocky, boolean variant) {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder(features, carvers);
		AtmosphericGeneration.dunes(generation, rocky, variant);

		MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();
		BiomeDefaultFeatures.caveSpawns(spawns);
		BiomeDefaultFeatures.monsters(spawns, 19, 1, 100, false);
		spawns.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.HUSK, 80, 4, 4));

		return biome(false, 2.0F, 0.0F, 14988944, spawns, generation, null);
	}

	private static Biome spinyThicket(HolderGetter<PlacedFeature> features, HolderGetter<ConfiguredWorldCarver<?>> carvers) {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder(features, carvers);
		AtmosphericGeneration.spinyThicket(generation);

		MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();
		BiomeDefaultFeatures.caveSpawns(spawns);
		BiomeDefaultFeatures.monsters(spawns, 19, 1, 100, false);
		spawns.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.HUSK, 80, 4, 4));

		return biome(false, 2.0F, 0.85F, 14988944, spawns, generation, null);
	}

	private static Biome scrubland(HolderGetter<PlacedFeature> features, HolderGetter<ConfiguredWorldCarver<?>> carvers, boolean snowy) {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder(features, carvers);
		AtmosphericGeneration.scrubland(generation, snowy);

		MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();
		spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.HORSE, 2, 1, 1));
		spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.DONKEY, 1, 1, 1));
		spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.CHICKEN, 2, 2, 4));
		spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(AtmosphericEntityTypes.COCHINEAL.get(), 4, 1, 4));
		BiomeDefaultFeatures.caveSpawns(spawns);
		BiomeDefaultFeatures.monsters(spawns, 19, 1, !snowy ? 100 : 20, false);
		spawns.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.HUSK, 80, 4, 4));
		if (snowy) {
			spawns.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.STRAY, 80, 4, 4));
		}

		return biome(snowy, !snowy ? 2.0F : 0.0F, 0.0F, 13021599, spawns, generation, null);
	}

	private static Biome aspenParkland(HolderGetter<PlacedFeature> features, HolderGetter<ConfiguredWorldCarver<?>> carvers) {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder(features, carvers);
		AtmosphericGeneration.aspenParkland(generation);

		MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();
		BiomeDefaultFeatures.commonSpawns(spawns);
		spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.WOLF, 10, 1, 1));
		spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.RABBIT, 15, 1, 1));

		return biome(true, 2.0F, 0.0F, spawns, generation, Musics.createGameMusic(SoundEvents.MUSIC_BIOME_FOREST));
	}

	private static Biome kousaJungle(HolderGetter<PlacedFeature> features, HolderGetter<ConfiguredWorldCarver<?>> carvers) {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder(features, carvers);
		AtmosphericGeneration.kousaJungle(generation);

		MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();
		BiomeDefaultFeatures.farmAnimals(spawns);
		spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.WOLF, 8, 4, 4));
		spawns.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.OCELOT, 2, 1, 3));
		BiomeDefaultFeatures.commonSpawns(spawns);

		return biome(true, -0.5F, 0.5F, spawns, generation, null);
	}

	private static Biome grimwoods(HolderGetter<PlacedFeature> features, HolderGetter<ConfiguredWorldCarver<?>> carvers) {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder(features, carvers);
		AtmosphericGeneration.grimwoods(generation);

		MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();
		BiomeDefaultFeatures.commonSpawns(spawns);

		return biome(true, 0.25F, 0.0F, 9539946, 8882547, 5403055, 1250099, 9866116, spawns, generation, null);
	}

	private static Biome laurelForest(HolderGetter<PlacedFeature> features, HolderGetter<ConfiguredWorldCarver<?>> carvers) {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder(features, carvers);
		AtmosphericGeneration.laurelForest(generation);

		MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();
		BiomeDefaultFeatures.commonSpawns(spawns);
		BiomeDefaultFeatures.farmAnimals(spawns);
		spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.WOLF, 5, 4, 4));

		return biomeWithGrass(true, 0.85F, 0.5F, 12249691, 11392595, spawns, generation, Musics.createGameMusic(SoundEvents.MUSIC_BIOME_FOREST));
	}

	private static Biome hotSprings(HolderGetter<PlacedFeature> features, HolderGetter<ConfiguredWorldCarver<?>> carvers) {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder(features, carvers);
		AtmosphericGeneration.hotSprings(generation);

		MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();
		BiomeDefaultFeatures.commonSpawns(spawns);
		BiomeDefaultFeatures.farmAnimals(spawns);
		spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.WOLF, 8, 4, 4));
		spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.RABBIT, 4, 2, 3));
		spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.FOX, 8, 2, 4));

		return biome(false, 0.25F, 0.4F, 4445678, 270131, spawns, generation, null);
	}

	private static Biome biome(boolean precipitation, float temperature, float downfall, MobSpawnSettings.Builder spawns, BiomeGenerationSettings.Builder generation, @Nullable Music music) {
		return biome(precipitation, temperature, downfall, 12638463, spawns, generation, music);
	}

	private static Biome biome(boolean precipitation, float temperature, float downfall, int waterColor, int waterFogColor, MobSpawnSettings.Builder spawns, BiomeGenerationSettings.Builder generation, @Nullable Music music) {
		return biome(precipitation, temperature, downfall, waterColor, waterFogColor, 12638463, spawns, generation, music);
	}

	private static Biome biome(boolean precipitation, float temperature, float downfall, int fogColor, MobSpawnSettings.Builder spawns, BiomeGenerationSettings.Builder generation, @Nullable Music music) {
		return biome(precipitation, temperature, downfall, 4159204, 329011, fogColor, spawns, generation, music);
	}

	private static Biome biome(boolean precipitation, float temperature, float downfall, int waterColor, int waterFogColor, int fogColor, MobSpawnSettings.Builder spawns, BiomeGenerationSettings.Builder generation, @Nullable Music music) {
		return (new Biome.BiomeBuilder()).hasPrecipitation(precipitation).temperature(temperature).downfall(downfall).specialEffects((new BiomeSpecialEffects.Builder()).waterColor(waterColor).waterFogColor(waterFogColor).fogColor(fogColor).skyColor(calculateSkyColor(temperature)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).backgroundMusic(music).build()).mobSpawnSettings(spawns.build()).generationSettings(generation.build()).build();
	}

	private static Biome biomeWithGrass(boolean precipitation, float temperature, float downfall, int grassColor, int foliageColor, MobSpawnSettings.Builder spawns, BiomeGenerationSettings.Builder generation, @Nullable Music music) {
		return (new Biome.BiomeBuilder()).hasPrecipitation(precipitation).temperature(temperature).downfall(downfall).specialEffects((new BiomeSpecialEffects.Builder()).grassColorOverride(grassColor).foliageColorOverride(foliageColor).waterColor(4159204).waterFogColor(329011).fogColor(12638463).skyColor(calculateSkyColor(temperature)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).backgroundMusic(music).build()).mobSpawnSettings(spawns.build()).generationSettings(generation.build()).build();
	}

	private static Biome biome(boolean precipitation, float temperature, float downfall, int grassColor, int foliageColor, int waterColor, int waterFogColor, int fogColor, MobSpawnSettings.Builder spawns, BiomeGenerationSettings.Builder generation, @Nullable Music music) {
		return (new Biome.BiomeBuilder()).hasPrecipitation(precipitation).temperature(temperature).downfall(downfall).specialEffects((new BiomeSpecialEffects.Builder()).grassColorOverride(grassColor).foliageColorOverride(foliageColor).waterColor(waterColor).waterFogColor(waterFogColor).fogColor(fogColor).skyColor(calculateSkyColor(temperature)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).backgroundMusic(music).build()).mobSpawnSettings(spawns.build()).generationSettings(generation.build()).build();
	}

	private static int calculateSkyColor(float temperature) {
		float clampedTemp = Mth.clamp(temperature / 3.0F, -1.0F, 1.0F);
		return Mth.hsvToRgb(0.62222224F - clampedTemp * 0.05F, 0.5F + clampedTemp * 0.1F, 1.0F);
	}
}
