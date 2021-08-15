package com.minecraftabnormals.atmospheric.common.world.gen.feature;

import com.minecraftabnormals.atmospheric.common.world.gen.feature.config.LargeSphereReplaceConfig;
import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.DoublePlantBlock;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;

public class CoarseDirtPatchFeature extends Feature<LargeSphereReplaceConfig> {
	public CoarseDirtPatchFeature(Codec<LargeSphereReplaceConfig> codec) {
		super(codec);
	}

	@Override
	public boolean place(ISeedReader worldIn, ChunkGenerator generator, Random rand, BlockPos pos, LargeSphereReplaceConfig config) {
		if (worldIn.getFluidState(pos).is(FluidTags.WATER)) {
			return false;
		} else {
			int i = 0;
			int j = rand.nextInt(config.radius.sample(rand) - 2) + 2;

			for (int k = pos.getX() - j; k <= pos.getX() + j; ++k) {
				for (int l = pos.getZ() - j; l <= pos.getZ() + j; ++l) {
					int i1 = k - pos.getX();
					int j1 = l - pos.getZ();
					if (i1 * i1 + j1 * j1 <= j * j) {
						for (int k1 = pos.getY() - config.halfHeight; k1 <= pos.getY() + config.halfHeight; ++k1) {
							BlockPos blockpos = new BlockPos(k, k1, l);
							BlockState blockstate = worldIn.getBlockState(blockpos);

							for (BlockState blockstate1 : config.targets) {
								if (blockstate1.getBlock() == blockstate.getBlock()) {
									worldIn.setBlock(blockpos, config.state, 2);
									if (worldIn.isEmptyBlock(blockpos.above())) {
										if (rand.nextInt(2) == 0) {
											if (worldIn.isEmptyBlock(blockpos.above(2)) && rand.nextInt(4) != 0) {
												worldIn.setBlock(blockpos.above(), Blocks.TALL_GRASS.defaultBlockState().setValue(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER), 2);
												worldIn.setBlock(blockpos.above(2), Blocks.TALL_GRASS.defaultBlockState().setValue(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER), 2);
											} else {
												worldIn.setBlock(blockpos.above(), Blocks.GRASS.defaultBlockState(), 2);
											}
										}
									}
									++i;
									break;
								}
							}
						}
					}
				}
			}
			return i > 0;
		}
	}
}
