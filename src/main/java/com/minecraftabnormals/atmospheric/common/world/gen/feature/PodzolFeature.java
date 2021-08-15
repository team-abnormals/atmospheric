package com.minecraftabnormals.atmospheric.common.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.ProbabilityConfig;

import java.util.Random;

public class PodzolFeature extends Feature<ProbabilityConfig> {

	public PodzolFeature(Codec<ProbabilityConfig> p_i49919_1_) {
		super(p_i49919_1_);
	}

	@Override
	public boolean place(ISeedReader worldIn, ChunkGenerator generator, Random rand, BlockPos pos, ProbabilityConfig config) {
		int i = 0;
		BlockPos.Mutable blockpos$mutable = pos.mutable();
		if (rand.nextFloat() < config.probability) {
			int k = rand.nextInt(4) + 1;
			for (int l = pos.getX() - k; l <= pos.getX() + k; ++l) {
				for (int i1 = pos.getZ() - k; i1 <= pos.getZ() + k; ++i1) {
					int j1 = l - pos.getX();
					int k1 = i1 - pos.getZ();
					if (j1 * j1 + k1 * k1 <= k * k) {
						blockpos$mutable.set(l, worldIn.getHeight(Heightmap.Type.WORLD_SURFACE, l, i1) - 1, i1);
						if (isDirt(worldIn.getBlockState(blockpos$mutable).getBlock())) {
							worldIn.setBlock(blockpos$mutable, Blocks.PODZOL.defaultBlockState(), 2);
						}
					}
				}

			}

			++i;
		}

		return i > 0;
	}
}
