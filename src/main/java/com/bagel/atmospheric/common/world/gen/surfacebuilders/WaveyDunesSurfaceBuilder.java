package com.bagel.atmospheric.common.world.gen.surfacebuilders;

import java.util.Random;
import java.util.function.Function;

import com.bagel.atmospheric.core.registry.AtmosphericBlocks;
import com.bagel.atmospheric.core.registry.AtmosphericFeatures;
import com.mojang.datafixers.Dynamic;

import net.minecraft.block.BlockState;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class WaveyDunesSurfaceBuilder extends SurfaceBuilder<SurfaceBuilderConfig> {
	
	public WaveyDunesSurfaceBuilder(Function<Dynamic<?>, ? extends SurfaceBuilderConfig> function) {
        super(function);
    }

    @Override
    public void buildSurface(Random random, IChunk chunk, Biome biome, int x, int z, int height, double noise, BlockState defaultBlock, BlockState defaultFluid, int seaLevel, long seed, SurfaceBuilderConfig surfaceBlocks) {
        if (noise > 0.3 && noise < 2.5) {
            AtmosphericFeatures.DUNES.buildSurface(random, chunk, biome, x, z, height, noise, defaultBlock, defaultFluid, seaLevel, seed, new SurfaceBuilderConfig(AtmosphericBlocks.RED_ARID_SAND.get().getDefaultState(), AtmosphericBlocks.RED_ARID_SAND.get().getDefaultState(), AtmosphericBlocks.RED_ARID_SAND.get().getDefaultState()));
        } else {
        	AtmosphericFeatures.DUNES.buildSurface(random, chunk, biome, x, z, height, noise, defaultBlock, defaultFluid, seaLevel, seed, new SurfaceBuilderConfig(AtmosphericBlocks.ARID_SAND.get().getDefaultState(), AtmosphericBlocks.ARID_SAND.get().getDefaultState(), AtmosphericBlocks.ARID_SAND.get().getDefaultState()));
        }
    }
}
