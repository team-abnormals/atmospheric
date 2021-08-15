package com.minecraftabnormals.atmospheric.common.world.gen.surfacebuilders;

import com.minecraftabnormals.atmospheric.core.registry.AtmosphericBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

import java.util.Random;

public class DunesSurfaceBuilder extends SurfaceBuilder<SurfaceBuilderConfig> {
	public DunesSurfaceBuilder(Codec<SurfaceBuilderConfig> p_i51315_1_) {
		super(p_i51315_1_);
	}

	public void apply(Random random, IChunk chunkIn, Biome biomeIn, int x, int z, int startHeight, double noise, BlockState defaultBlock, BlockState defaultFluid, int seaLevel, long seed, SurfaceBuilderConfig config) {
		this.buildSurface(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, config.getTopMaterial(), config.getUnderMaterial(), config.getUnderwaterMaterial(), seaLevel);
	}

	protected void buildSurface(Random random, IChunk chunkIn, Biome biomeIn, int x, int z, int startHeight, double noise, BlockState defaultBlock, BlockState defaultFluid, BlockState top, BlockState middle, BlockState bottom, int sealevel) {
		BlockState blockstate = top;
		BlockState blockstate1 = middle;
		BlockPos.Mutable blockpos$mutableblockpos = new BlockPos.Mutable();
		int i = -1;
		int j = (int) (noise / 3.0D + 3.0D + random.nextDouble() * 0.25D);
		int k = x & 15;
		int l = z & 15;

		for (int i1 = startHeight; i1 >= 0; --i1) {
			blockpos$mutableblockpos.set(k, i1, l);
			BlockState blockstate2 = chunkIn.getBlockState(blockpos$mutableblockpos);
			if (blockstate2.isAir()) {
				i = -1;
			} else if (blockstate2.getBlock() == defaultBlock.getBlock()) {
				if (i == -1) {
					if (j <= 0) {
						blockstate = Blocks.AIR.defaultBlockState();
						blockstate1 = defaultBlock;
					} else if (i1 >= sealevel - 4 && i1 <= sealevel + 1) {
						blockstate = top;
						blockstate1 = middle;
					}

					if (i1 < sealevel && (blockstate == null || blockstate.isAir())) {
						if (biomeIn.getTemperature(blockpos$mutableblockpos.set(x, i1, z)) < 0.15F) {
							blockstate = Blocks.ICE.defaultBlockState();
						} else {
							blockstate = defaultFluid;
						}

						blockpos$mutableblockpos.set(k, i1, l);
					}

					i = j;
					if (i1 >= sealevel - 1) {
						chunkIn.setBlockState(blockpos$mutableblockpos, blockstate, false);
					} else if (i1 < sealevel - 7 - j) {
						blockstate = Blocks.AIR.defaultBlockState();
						blockstate1 = defaultBlock;
						chunkIn.setBlockState(blockpos$mutableblockpos, bottom, false);
					} else {
						chunkIn.setBlockState(blockpos$mutableblockpos, blockstate1, false);
					}
				} else if (i > 0) {
					--i;
					chunkIn.setBlockState(blockpos$mutableblockpos, blockstate1, false);
					if (i == 0 && blockstate1.getBlock() == AtmosphericBlocks.ARID_SAND.get() && j > 1) {
						i = random.nextInt(4) + Math.max(0, i1 - 63);
						blockstate1 = blockstate1.getBlock() == AtmosphericBlocks.RED_ARID_SAND.get() ? AtmosphericBlocks.RED_ARID_SANDSTONE.get().defaultBlockState() : AtmosphericBlocks.ARID_SANDSTONE.get().defaultBlockState();
					}
					if (i == 0 && blockstate1.getBlock() == AtmosphericBlocks.RED_ARID_SAND.get() && j > 1) {
						i = random.nextInt(4) + Math.max(0, i1 - 63);
						blockstate1 = blockstate1.getBlock() == AtmosphericBlocks.ARID_SAND.get() ? AtmosphericBlocks.ARID_SANDSTONE.get().defaultBlockState() : AtmosphericBlocks.RED_ARID_SANDSTONE.get().defaultBlockState();
					}
				}
			}
		}

	}
}
