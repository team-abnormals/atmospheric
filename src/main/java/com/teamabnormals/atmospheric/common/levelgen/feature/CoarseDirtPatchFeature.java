package com.teamabnormals.atmospheric.common.levelgen.feature;

import com.mojang.serialization.Codec;
import com.teamabnormals.atmospheric.common.levelgen.feature.configurations.LargeDiskConfiguration;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

public class CoarseDirtPatchFeature extends Feature<LargeDiskConfiguration> {

	public CoarseDirtPatchFeature(Codec<LargeDiskConfiguration> codec) {
		super(codec);
	}

	@Override
	public boolean place(FeaturePlaceContext<LargeDiskConfiguration> context) {
		LargeDiskConfiguration config = context.config();
		WorldGenLevel worldIn = context.level();
		RandomSource rand = context.random();
		BlockPos pos = context.origin();

		if (worldIn.getFluidState(pos).is(FluidTags.WATER)) {
			return false;
		} else {
			int i = 0;
			int j = 2 + rand.nextInt(Math.max(config.radius().sample(rand) - 2, 1));

			for (int k = pos.getX() - j; k <= pos.getX() + j; ++k) {
				for (int l = pos.getZ() - j; l <= pos.getZ() + j; ++l) {
					int i1 = k - pos.getX();
					int j1 = l - pos.getZ();
					if (i1 * i1 + j1 * j1 <= j * j) {
						for (int k1 = pos.getY() - config.halfHeight(); k1 <= pos.getY() + config.halfHeight(); ++k1) {
							BlockPos blockpos = new BlockPos(k, k1, l);
							BlockState blockstate = worldIn.getBlockState(blockpos);

							for (BlockState blockstate1 : config.targets()) {
								if (blockstate1.getBlock() == blockstate.getBlock()) {
									worldIn.setBlock(blockpos, config.state(), 2);
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
