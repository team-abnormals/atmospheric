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
	public boolean generate(ISeedReader worldIn, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config) {
		int i = 0;
		for (int j = 0; j < 400; ++j) {
			Direction direction = Direction.Plane.HORIZONTAL.random(rand);
			BlockState blockstate = AtmosphericBlocks.PASSION_VINE.get().getDefaultState().with(PassionVineBlock.FACING, direction);
			BlockPos blockpos = pos.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));

			int lengthA = 3 + rand.nextInt(2) + rand.nextInt(2) + rand.nextInt(2) - rand.nextInt(3);

			if (worldIn.isAirBlock(blockpos) && blockpos.getY() > 50 && blockpos.getY() < 255 && blockstate.isValidPosition(worldIn, blockpos)) {
				worldIn.setBlockState(blockpos, getVineState(worldIn, blockstate, blockpos, rand), 6);
				for (int length = 0; length < lengthA; ++length) {
					blockpos = blockpos.down();
					if (worldIn.isAirBlock(blockpos) && blockpos.getY() < 255 && blockstate.isValidPosition(worldIn, blockpos)) {
						worldIn.setBlockState(blockpos, getVineState(worldIn, blockstate, blockpos, rand), 6);
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
		if (world.getBlockState(pos.offset(state.get(PassionVineBlock.FACING).getOpposite())).isIn(AtmosphericTags.PASSION_VINE_GROWABLE_ON)) {
			return state.with(PassionVineBlock.AGE, 4);
		} else {
			return state.with(PassionVineBlock.AGE, 1);
		}
	}
}
