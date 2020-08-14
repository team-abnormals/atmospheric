package com.minecraftabnormals.atmospheric.common.world.biome;

import com.minecraftabnormals.atmospheric.core.registry.AtmosphericFeatures;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.MoodSoundAmbience;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;

public class AtmosphericBiomeBuilders {
    
    public static final Biome.Builder RAINFOREST            = createRainforestBiome(0.1F, 0.2F, 0.90F, 0.95F);
    public static final Biome.Builder RAINFOREST_MOUNTAINS  = createRainforestBiome(0.2825F, 1.225F, 0.90F, 0.95F);
    public static final Biome.Builder RAINFOREST_PLATEAU    = createRainforestBiome(1.5F, 0.025F, 0.90F, 0.95F);
    public static final Biome.Builder RAINFOREST_BASIN      = createRainforestBiome(-0.3F, 0.01F, 0.90F, 0.95F);
    
    public static final Biome.Builder DUNES             = createDunesBiome(0.45F, 0.15F, 2.0F, 0.0F);
    public static final Biome.Builder DUNES_HILLS       = createDunesBiome(0.45F, 0.20F, 2.0F, 0.0F);
    public static final Biome.Builder ROCKY_DUNES       = createDunesBiome(0.45F, 0.30F, 2.0F, 0.0F);
    public static final Biome.Builder ROCKY_DUNES_HILLS = createDunesBiome(0.45F, 0.45F, 2.0F, 0.0F);
    
    private static Biome.Builder createRainforestBiome(float depth, float scale, float temperature, float downfall) {
        return (new Biome.Builder())
        .surfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_DIRT_GRAVEL_CONFIG)
        .precipitation(Biome.RainType.RAIN)
        .category(Biome.Category.FOREST)
        .depth(depth)
        .scale(scale)
        .temperature(temperature)
        .downfall(downfall)
        .func_235097_a_((
                new BiomeAmbience.Builder())
                .setWaterColor(6675400)
                .setWaterFogColor(408635)
                .setFogColor(12638463)
                .setMoodSound(MoodSoundAmbience.field_235027_b_)
                .build())
        .parent((String)null);
    }
    
    private static Biome.Builder createDunesBiome(float depth, float scale, float temperature, float downfall) {
        return (new Biome.Builder())
        .surfaceBuilder(AtmosphericFeatures.WAVEY_DUNES, AtmosphericFeatureConfigs.DUNES)
        .precipitation(Biome.RainType.NONE)
        .category(Biome.Category.DESERT)
        .depth(depth)
        .scale(scale)
        .temperature(temperature)
        .downfall(downfall)
        .func_235097_a_((
                new BiomeAmbience.Builder())
                .setWaterColor(4159204)
                .setWaterFogColor(329011)
                .setFogColor(12638463)
                .setMoodSound(MoodSoundAmbience.field_235027_b_)
                .build())
        .parent((String)null);
    }
}
