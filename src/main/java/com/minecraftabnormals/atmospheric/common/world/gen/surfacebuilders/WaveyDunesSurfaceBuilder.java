package com.minecraftabnormals.atmospheric.common.world.gen.surfacebuilders;

import java.util.Random;

import com.minecraftabnormals.atmospheric.core.registry.AtmosphericBlocks;
import com.minecraftabnormals.atmospheric.core.registry.AtmosphericFeatures;
import com.mojang.serialization.Codec;

import net.minecraft.block.BlockState;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class WaveyDunesSurfaceBuilder extends SurfaceBuilder<SurfaceBuilderConfig> {
	
	public WaveyDunesSurfaceBuilder(Codec<SurfaceBuilderConfig> function) {
        super(function);
    }

    @Override
    public void buildSurface(Random random, IChunk chunk, Biome biome, int x, int z, int height, double noise, BlockState defaultBlock, BlockState defaultFluid, int seaLevel, long seed, SurfaceBuilderConfig surfaceBlocks) {
    	if (noise > 0.3 && noise < 2.5) {
    		BlockState state = AtmosphericBlocks.RED_ARID_SAND.get().getDefaultState();
            AtmosphericFeatures.DUNES.buildSurface(random, chunk, biome, x, z, height, noise, defaultBlock, defaultFluid, seaLevel, seed, new SurfaceBuilderConfig(state, state, state));
        } else {
        	BlockState state = AtmosphericBlocks.ARID_SAND.get().getDefaultState();
        	AtmosphericFeatures.DUNES.buildSurface(random, chunk, biome, x, z, height, noise, defaultBlock, defaultFluid, seaLevel, seed, new SurfaceBuilderConfig(state, state, state));
        }
    }
}
