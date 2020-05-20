package com.bagel.atmospheric.common.world.gen.surfacebuilders;

import java.util.Random;
import java.util.function.Function;

import com.bagel.atmospheric.common.block.AridSandBlock;
import com.bagel.atmospheric.core.registry.AtmosphericBlocks;
import com.bagel.atmospheric.core.registry.AtmosphericFeatures;
import com.mojang.datafixers.Dynamic;

import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
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
        Direction direction = Direction.NORTH;

        if (noise < 0.25) direction = Direction.NORTH;
        if (noise >= 0.5) direction = Direction.EAST;
        if (noise > 0.75) direction = Direction.SOUTH;
        if (noise > 1.0) direction = Direction.WEST;
        
        
    	if (noise > 0.3 && noise < 2.5) {
    		BlockState state = AtmosphericBlocks.RED_ARID_SAND.get().getDefaultState().with(AridSandBlock.FACING, direction);
            AtmosphericFeatures.DUNES.buildSurface(random, chunk, biome, x, z, height, noise, defaultBlock, defaultFluid, seaLevel, seed, new SurfaceBuilderConfig(state, state, state));
        } else {
        	BlockState state = AtmosphericBlocks.ARID_SAND.get().getDefaultState().with(AridSandBlock.FACING, direction);
        	AtmosphericFeatures.DUNES.buildSurface(random, chunk, biome, x, z, height, noise, defaultBlock, defaultFluid, seaLevel, seed, new SurfaceBuilderConfig(state, state, state));
        }
    }
}
