package com.minecraftabnormals.atmospheric.common.world.gen.surfacebuilders;

import com.minecraftabnormals.atmospheric.core.registry.AtmosphericBlocks;
import com.minecraftabnormals.atmospheric.core.registry.AtmosphericSurfaceBuilders;
import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

import java.util.Random;

public class DunesWavesSurfaceBuilder extends SurfaceBuilder<SurfaceBuilderConfig> {

	public DunesWavesSurfaceBuilder(Codec<SurfaceBuilderConfig> function) {
		super(function);
	}

	@Override
	public void apply(Random random, IChunk chunk, Biome biome, int x, int z, int height, double noise, BlockState defaultBlock, BlockState defaultFluid, int seaLevel, long seed, SurfaceBuilderConfig surfaceBlocks) {
		if (noise > 0.3 && noise < 2.5) {
			BlockState state = AtmosphericBlocks.RED_ARID_SAND.get().defaultBlockState();
			AtmosphericSurfaceBuilders.DUNES.apply(random, chunk, biome, x, z, height, noise, defaultBlock, defaultFluid, seaLevel, seed, new SurfaceBuilderConfig(state, state, state));
		} else {
			BlockState state = AtmosphericBlocks.ARID_SAND.get().defaultBlockState();
			AtmosphericSurfaceBuilders.DUNES.apply(random, chunk, biome, x, z, height, noise, defaultBlock, defaultFluid, seaLevel, seed, new SurfaceBuilderConfig(state, state, state));
		}
	}
}
