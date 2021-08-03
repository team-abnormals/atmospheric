package com.minecraftabnormals.atmospheric.common.world.gen.feature;

import com.minecraftabnormals.atmospheric.common.block.PassionVineBlock;
import com.minecraftabnormals.atmospheric.core.other.AtmosphericTags;
import com.minecraftabnormals.atmospheric.core.registry.AtmosphericBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

public class PassionVineFeature extends Feature<NoFeatureConfig> {
	public PassionVineFeature(Codec<NoFeatureConfig> p_i49876_1_) {
		super(p_i49876_1_);
	}

	@Override
	public boolean place(ISeedReader worldIn, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config) {
		int i = 0;
		for (int j = 0; j < 400; ++j) {
			Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(rand);
			BlockState blockstate = AtmosphericBlocks.PASSION_VINE.get().defaultBlockState().setValue(PassionVineBlock.FACING, direction);
			BlockPos blockpos = pos.offset(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));

			int lengthA = 3 + rand.nextInt(2) + rand.nextInt(2) + rand.nextInt(2) - rand.nextInt(3);

			if (worldIn.isEmptyBlock(blockpos) && blockpos.getY() > 50 && blockpos.getY() < 255 && blockstate.canSurvive(worldIn, blockpos)) {
				worldIn.setBlock(blockpos, getVineState(worldIn, blockstate, blockpos, rand), 6);
				for (int length = 0; length < lengthA; ++length) {
					blockpos = blockpos.below();
					if (worldIn.isEmptyBlock(blockpos) && blockpos.getY() < 255 && blockstate.canSurvive(worldIn, blockpos)) {
						worldIn.setBlock(blockpos, getVineState(worldIn, blockstate, blockpos, rand), 6);
					} else {
						break;
					}
				}
				++i;
			}
		}
		return i > 0;
	}

	private static BlockState getVineState(ISeedReader world, BlockState state, BlockPos pos, Random rand) {
		if (world.getBlockState(pos.relative(state.getValue(PassionVineBlock.FACING).getOpposite())).is(AtmosphericTags.PASSION_VINE_GROWABLE_ON)) {
			return state.setValue(PassionVineBlock.AGE, 4);
		} else {
			return state.setValue(PassionVineBlock.AGE, 1);
		}
	}
}
