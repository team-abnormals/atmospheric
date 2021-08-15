package com.minecraftabnormals.atmospheric.common.world.gen.surfacebuilders;

import com.minecraftabnormals.atmospheric.core.registry.AtmosphericBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

import java.util.Random;

public class HotSpringsSurfaceBuilder extends SurfaceBuilder<SurfaceBuilderConfig> {

	public HotSpringsSurfaceBuilder(Codec<SurfaceBuilderConfig> function) {
		super(function);
	}

	@Override
	public void apply(Random random, IChunk chunk, Biome biome, int x, int z, int height, double noise, BlockState defaultBlock, BlockState defaultFluid, int seaLevel, long seed, SurfaceBuilderConfig surfaceBlocks) {
		if (height > 80) {
			SurfaceBuilder.DEFAULT.apply(random, chunk, biome, x, z, height, noise, defaultBlock, defaultFluid, seaLevel, seed, new SurfaceBuilderConfig(Blocks.COARSE_DIRT.defaultBlockState(), Blocks.COARSE_DIRT.defaultBlockState(), Blocks.GRAVEL.defaultBlockState()));
			return;
		}

		BlockState state = AtmosphericBlocks.IVORY_TRAVERTINE.get().defaultBlockState();
		if (noise > -0.1 && noise < 2.9) {
			state = AtmosphericBlocks.PEACH_TRAVERTINE.get().defaultBlockState();
			if (noise > 0.4 && noise < 2.4) {
				state = AtmosphericBlocks.PERSIMMON_TRAVERTINE.get().defaultBlockState();
				if (noise > 0.9 && noise < 1.9) {
					state = AtmosphericBlocks.SAFFRON_TRAVERTINE.get().defaultBlockState();
				}
			}
		}

		SurfaceBuilder.DEFAULT.apply(random, chunk, biome, x, z, height, noise, defaultBlock, defaultFluid, seaLevel, seed, new SurfaceBuilderConfig(state, state, state));

	}
}
