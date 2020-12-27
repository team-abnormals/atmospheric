package com.minecraftabnormals.atmospheric.common.world.gen.feature;

import com.minecraftabnormals.atmospheric.core.registry.AtmosphericBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.BlockStateFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;

public class DuneRocksFeature extends Feature<BlockStateFeatureConfig> {
	public DuneRocksFeature(Codec<BlockStateFeatureConfig> codec) {
		super(codec);
	}

	@Override
	public boolean generate(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, BlockStateFeatureConfig config) {
		while (true) {
			label46: {
				if (pos.getY() > 3) {
					if (reader.isAirBlock(pos.down())) {
						break label46;
					}

					Block block = reader.getBlockState(pos.down()).getBlock();
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

					for (BlockPos blockpos : BlockPos.getAllInBoxMutable(pos.add(-i, -j, -k), pos.add(i, j, k))) {
						if (blockpos.distanceSq(pos) <= (double) (f * f)) {
							reader.setBlockState(blockpos, config.state, 4);
						}
					}

					pos = pos.add(-(i1 + 1) + rand.nextInt(2 + i1 * 2), -rand.nextInt(2), -(i1 + 1) + rand.nextInt(2 + i1 * 2));
				}

				return true;
			}

			pos = pos.down();
		}
	}
}
