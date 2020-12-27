package com.minecraftabnormals.atmospheric.core.registry;

import com.minecraftabnormals.abnormals_core.core.util.BiomeUtil;
import com.minecraftabnormals.abnormals_core.core.util.registry.BiomeSubRegistryHelper;
import com.minecraftabnormals.atmospheric.core.Atmospheric;
import com.minecraftabnormals.atmospheric.core.AtmosphericConfig;
import com.mojang.datafixers.util.Pair;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilders;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(modid = Atmospheric.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class AtmosphericBiomes {
	private static final BiomeSubRegistryHelper HELPER = Atmospheric.REGISTRY_HELPER.getBiomeSubHelper();

	public static final BiomeSubRegistryHelper.KeyedBiome RAINFOREST = HELPER.createBiome("rainforest", () -> createRainforestBiome(0.1F, 0.2F));
	public static final BiomeSubRegistryHelper.KeyedBiome RAINFOREST_MOUNTAINS = HELPER.createBiome("rainforest_mountains", () -> createRainforestBiome(0.2825F, 1.225F));
	public static final BiomeSubRegistryHelper.KeyedBiome RAINFOREST_PLATEAU = HELPER.createBiome("rainforest_plateau", () -> createRainforestBiome(1.5F, 0.025F));
	public static final BiomeSubRegistryHelper.KeyedBiome SPARSE_RAINFOREST_PLATEAU = HELPER.createBiome("sparse_rainforest_plateau", () -> createRainforestBiome(1.5F, 0.025F));
	public static final BiomeSubRegistryHelper.KeyedBiome RAINFOREST_BASIN = HELPER.createBiome("rainforest_basin", () -> createRainforestBiome(-0.33F, 0.01F));
	public static final BiomeSubRegistryHelper.KeyedBiome SPARSE_RAINFOREST_BASIN = HELPER.createBiome("sparse_rainforest_basin", () -> createRainforestBiome(-0.33F, 0.01F));

	public static final BiomeSubRegistryHelper.KeyedBiome DUNES = HELPER.createBiome("dunes", () -> createDunesBiome(0.45F, 0.15F));
	public static final BiomeSubRegistryHelper.KeyedBiome DUNES_HILLS = HELPER.createBiome("dunes_hills", () -> createDunesBiome(0.45F, 0.20F));
	public static final BiomeSubRegistryHelper.KeyedBiome FLOURISHING_DUNES = HELPER.createBiome("flourishing_dunes", () -> createDunesBiome(0.45F, 0.15F));
	public static final BiomeSubRegistryHelper.KeyedBiome ROCKY_DUNES = HELPER.createBiome("rocky_dunes", () -> createDunesBiome(0.45F, 0.30F));
	public static final BiomeSubRegistryHelper.KeyedBiome ROCKY_DUNES_HILLS = HELPER.createBiome("rocky_dunes_hills", () -> createDunesBiome(0.45F, 0.45F));
	public static final BiomeSubRegistryHelper.KeyedBiome PETRIFIED_DUNES = HELPER.createBiome("petrified_dunes", () -> createDunesBiome(0.45F, 0.20F));

	public static void addBiomeVariants() {
		BiomeUtil.addHillBiome(RAINFOREST.getKey(), Pair.of(RAINFOREST_BASIN.getKey(), 1), Pair.of(RAINFOREST_PLATEAU.getKey(), 1), Pair.of(SPARSE_RAINFOREST_PLATEAU.getKey(), 1));
		BiomeUtil.addHillBiome(RAINFOREST_BASIN.getKey(), Pair.of(SPARSE_RAINFOREST_BASIN.getKey(), 1), Pair.of(RAINFOREST_PLATEAU.getKey(), 1));
		BiomeUtil.addHillBiome(DUNES.getKey(), Pair.of(DUNES_HILLS.getKey(), 3), Pair.of(FLOURISHING_DUNES.getKey(), 2));
		BiomeUtil.addHillBiome(ROCKY_DUNES.getKey(), Pair.of(ROCKY_DUNES_HILLS.getKey(), 3), Pair.of(PETRIFIED_DUNES.getKey(), 2));
	}

	public static void registerBiomesToDictionary() {
		BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(RAINFOREST.getKey(), AtmosphericConfig.COMMON.rainforestWeight.get()));
		BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(RAINFOREST_MOUNTAINS.getKey(), AtmosphericConfig.COMMON.rainforestMountainsWeight.get()));
		BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(RAINFOREST_PLATEAU.getKey(), AtmosphericConfig.COMMON.rainforestPlateauWeight.get()));
		BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(SPARSE_RAINFOREST_PLATEAU.getKey(), AtmosphericConfig.COMMON.sparseRainforestPlateauWeight.get()));
		BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(RAINFOREST_BASIN.getKey(), AtmosphericConfig.COMMON.rainforestBasinWeight.get()));
		BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(SPARSE_RAINFOREST_BASIN.getKey(), AtmosphericConfig.COMMON.sparseRainforestBasinWeight.get()));

		BiomeManager.addBiome(BiomeManager.BiomeType.DESERT, new BiomeManager.BiomeEntry(DUNES.getKey(), AtmosphericConfig.COMMON.dunesWeight.get()));
		BiomeManager.addBiome(BiomeManager.BiomeType.DESERT, new BiomeManager.BiomeEntry(DUNES_HILLS.getKey(), AtmosphericConfig.COMMON.dunesHillsWeight.get()));
		BiomeManager.addBiome(BiomeManager.BiomeType.DESERT, new BiomeManager.BiomeEntry(FLOURISHING_DUNES.getKey(), AtmosphericConfig.COMMON.flourishingDunesWeight.get()));
		BiomeManager.addBiome(BiomeManager.BiomeType.DESERT, new BiomeManager.BiomeEntry(ROCKY_DUNES.getKey(), AtmosphericConfig.COMMON.rockyDunesWeight.get()));
		BiomeManager.addBiome(BiomeManager.BiomeType.DESERT, new BiomeManager.BiomeEntry(ROCKY_DUNES_HILLS.getKey(), AtmosphericConfig.COMMON.rockyDunesHillsWeight.get()));
		BiomeManager.addBiome(BiomeManager.BiomeType.DESERT, new BiomeManager.BiomeEntry(PETRIFIED_DUNES.getKey(), AtmosphericConfig.COMMON.petrifiedDunesWeight.get()));
	}

	public static void addBiomeTypes() {
		BiomeDictionary.addTypes(RAINFOREST.getKey(), BiomeDictionary.Type.JUNGLE, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.OVERWORLD);
		BiomeDictionary.addTypes(RAINFOREST_MOUNTAINS.getKey(), BiomeDictionary.Type.JUNGLE, BiomeDictionary.Type.RARE, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.OVERWORLD);
		BiomeDictionary.addTypes(SPARSE_RAINFOREST_PLATEAU.getKey(), BiomeDictionary.Type.JUNGLE, BiomeDictionary.Type.PLATEAU, BiomeDictionary.Type.OVERWORLD);
		BiomeDictionary.addTypes(RAINFOREST_PLATEAU.getKey(), BiomeDictionary.Type.JUNGLE, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.PLATEAU, BiomeDictionary.Type.OVERWORLD);
		BiomeDictionary.addTypes(RAINFOREST_BASIN.getKey(), BiomeDictionary.Type.JUNGLE, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.WET, BiomeDictionary.Type.OVERWORLD);
		BiomeDictionary.addTypes(SPARSE_RAINFOREST_BASIN.getKey(), BiomeDictionary.Type.JUNGLE, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.WET, BiomeDictionary.Type.OVERWORLD);

		BiomeDictionary.addTypes(DUNES.getKey(), BiomeDictionary.Type.DRY, BiomeDictionary.Type.HOT, BiomeDictionary.Type.OVERWORLD);
		BiomeDictionary.addTypes(DUNES_HILLS.getKey(), BiomeDictionary.Type.DRY, BiomeDictionary.Type.HOT, BiomeDictionary.Type.OVERWORLD);
		BiomeDictionary.addTypes(FLOURISHING_DUNES.getKey(), BiomeDictionary.Type.RARE, BiomeDictionary.Type.DRY, BiomeDictionary.Type.HOT, BiomeDictionary.Type.LUSH, BiomeDictionary.Type.OVERWORLD);
		BiomeDictionary.addTypes(ROCKY_DUNES.getKey(), BiomeDictionary.Type.DRY, BiomeDictionary.Type.HOT, BiomeDictionary.Type.WASTELAND, BiomeDictionary.Type.OVERWORLD);
		BiomeDictionary.addTypes(ROCKY_DUNES_HILLS.getKey(), BiomeDictionary.Type.DRY, BiomeDictionary.Type.HOT, BiomeDictionary.Type.WASTELAND, BiomeDictionary.Type.OVERWORLD);
		BiomeDictionary.addTypes(PETRIFIED_DUNES.getKey(), BiomeDictionary.Type.RARE, BiomeDictionary.Type.DRY, BiomeDictionary.Type.HOT, BiomeDictionary.Type.WASTELAND, BiomeDictionary.Type.OVERWORLD);
	}

	private static Biome createRainforestBiome(float depth, float scale) {
		BiomeGenerationSettings.Builder builder = (new BiomeGenerationSettings.Builder()).withSurfaceBuilder(ConfiguredSurfaceBuilders.field_244178_j);
		return (new Biome.Builder()).precipitation(Biome.RainType.RAIN).category(Biome.Category.FOREST).depth(depth).scale(scale).temperature(0.9F).downfall(0.95F).setEffects((new BiomeAmbience.Builder()).setWaterColor(6675400).setWaterFogColor(408635).setFogColor(12638463).withSkyColor(getSkyColorWithTemperatureModifier(0.9F)).build()).withMobSpawnSettings(new MobSpawnInfo.Builder().copy()).withGenerationSettings((builder).build()).build();
	}

	private static Biome createDunesBiome(float depth, float scale) {
		BiomeGenerationSettings.Builder builder = (new BiomeGenerationSettings.Builder()).withSurfaceBuilder(AtmosphericSurfaceBuilders.Configured.DUNES_WAVES);
		return (new Biome.Builder()).precipitation(Biome.RainType.NONE).category(Biome.Category.DESERT).depth(depth).scale(scale).temperature(2.0F).downfall(0.0F).setEffects((new BiomeAmbience.Builder()).setWaterColor(4159204).setWaterFogColor(329011).setFogColor(14988944).withSkyColor(getSkyColorWithTemperatureModifier(2.0F)).build()).withMobSpawnSettings(new MobSpawnInfo.Builder().copy()).withGenerationSettings(builder.build()).build();
	}

	private static int getSkyColorWithTemperatureModifier(float temperature) {
		float lvt_1_1_ = temperature / 3.0F;
		lvt_1_1_ = MathHelper.clamp(lvt_1_1_, -1.0F, 1.0F);
		return MathHelper.hsvToRGB(0.62222224F - lvt_1_1_ * 0.05F, 0.5F + lvt_1_1_ * 0.1F, 1.0F);
	}
}
