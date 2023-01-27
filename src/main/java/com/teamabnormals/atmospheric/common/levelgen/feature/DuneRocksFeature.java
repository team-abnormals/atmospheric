package com.teamabnormals.atmospheric.common.levelgen.feature;

import com.mojang.serialization.Codec;
import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.BlockStateConfiguration;

import java.util.Random;

public class DuneRocksFeature extends Feature<BlockStateConfiguration> {
	public DuneRocksFeature(Codec<BlockStateConfiguration> codec) {
		super(codec);
	}

	@Override
	public boolean place(FeaturePlaceContext<BlockStateConfiguration> context) {
		BlockStateConfiguration config = context.config();
		WorldGenLevel reader = context.level();
		RandomSource rand = context.random();
		BlockPos pos = context.origin();

		while (true) {
			label46:
			{
				if (pos.getY() > 3) {
					if (reader.isEmptyBlock(pos.below())) {
						break label46;
					}

					Block block = reader.getBlockState(pos.below()).getBlock();
					if (block != AtmosphericBlocks.ARID_SAND.get() && block != AtmosphericBlocks.RED_ARID_SAND.get()) {
						break label46;
					}
				}

				if (pos.getY() <= 3) {
					return false;
				}

				int i1 = rand.nextInt(2) + rand.nextInt(2);

				for (int l = 0; l < 3; ++l) {
					int i = i1 + rand.nextInt(2);
					int j = i1 + rand.nextInt(2);
					int k = i1 + rand.nextInt(2);
					float f = (float) (i + j + k) * 0.333F + 0.5F;

					for (BlockPos blockpos : BlockPos.betweenClosed(pos.offset(-i, -j, -k), pos.offset(i, j, k))) {
						if (blockpos.distSqr(pos) <= (double) (f * f)) {
							reader.setBlock(blockpos, config.state, 4);
						}
					}

					pos = pos.offset(-(i1 + 1) + rand.nextInt(2 + i1 * 2), -rand.nextInt(2), -(i1 + 1) + rand.nextInt(2 + i1 * 2));
				}

				return true;
			}

			pos = pos.below();
		}
	}
}
