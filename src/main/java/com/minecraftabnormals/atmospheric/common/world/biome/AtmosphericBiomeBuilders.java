package com.minecraftabnormals.atmospheric.common.world.biome;

import com.minecraftabnormals.atmospheric.core.registry.AtmosphericFeatures;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.MoodSoundAmbience;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;

public class AtmosphericBiomeBuilders {
    public static final Biome.Builder RAINFOREST            = createRainforestBiome(0.1F, 0.2F);
    public static final Biome.Builder RAINFOREST_MOUNTAINS  = createRainforestBiome(0.2825F, 1.225F);
    public static final Biome.Builder RAINFOREST_PLATEAU    = createRainforestBiome(1.5F, 0.025F);
    public static final Biome.Builder RAINFOREST_BASIN      = createRainforestBiome(-0.33F, 0.01F);
    
    public static final Biome.Builder DUNES             = createDunesBiome(0.45F, 0.15F);
    public static final Biome.Builder DUNES_HILLS       = createDunesBiome(0.45F, 0.20F);
    public static final Biome.Builder ROCKY_DUNES       = createDunesBiome(0.45F, 0.30F);
    public static final Biome.Builder ROCKY_DUNES_HILLS = createDunesBiome(0.45F, 0.45F);
    
    private static Biome.Builder createRainforestBiome(float depth, float scale) {
        return (new Biome.Builder())
        .surfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_DIRT_GRAVEL_CONFIG)
        .precipitation(Biome.RainType.RAIN)
        .category(Biome.Category.FOREST)
        .depth(depth)
        .scale(scale)
        .temperature(0.9F)
        .downfall(0.95F)
        .func_235097_a_((
                new BiomeAmbience.Builder())
                .setWaterColor(6675400)
                .setWaterFogColor(408635)
                .setFogColor(12638463)
                .setMoodSound(MoodSoundAmbience.field_235027_b_)
                .build())
        .parent(null);
    }
    
    private static Biome.Builder createDunesBiome(float depth, float scale) {
        return (new Biome.Builder())
        .surfaceBuilder(AtmosphericFeatures.WAVEY_DUNES, AtmosphericFeatureConfigs.DUNES)
        .precipitation(Biome.RainType.NONE)
        .category(Biome.Category.DESERT)
        .depth(depth)
        .scale(scale)
        .temperature(2.0F)
        .downfall(0.0F)
        .func_235097_a_((
                new BiomeAmbience.Builder())
                .setWaterColor(4159204)
                .setWaterFogColor(329011)
                .setFogColor(14988944)
                .setMoodSound(MoodSoundAmbience.field_235027_b_)
                .build())
        .parent(null);
    }
}
