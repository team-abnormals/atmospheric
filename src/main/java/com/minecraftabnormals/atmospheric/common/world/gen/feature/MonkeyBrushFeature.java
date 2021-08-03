package com.minecraftabnormals.atmospheric.common.world.gen.feature;

import com.minecraftabnormals.atmospheric.common.block.MonkeyBrushBlock;
import com.minecraftabnormals.atmospheric.common.block.WallMonkeyBrushBlock;
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

public class MonkeyBrushFeature extends Feature<NoFeatureConfig> {
	int temp;

	public MonkeyBrushFeature(Codec<NoFeatureConfig> p_i49876_1_, int temperature) {
		super(p_i49876_1_);
		temp = temperature;
	}

	@Override
	public boolean place(ISeedReader worldIn, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config) {
		BlockState blockstate = AtmosphericBlocks.HOT_MONKEY_BRUSH.get().defaultBlockState();
		if (temp == 3) {
			blockstate = AtmosphericBlocks.SCALDING_MONKEY_BRUSH.get().defaultBlockState();
		} else if (temp == 1) {
			blockstate = AtmosphericBlocks.WARM_MONKEY_BRUSH.get().defaultBlockState();
		}
		int i = 0;

		for (int j = 0; j < 64; ++j) {
			BlockPos blockpos = pos.offset(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));
			if (worldIn.isEmptyBlock(blockpos) && blockpos.getY() < 255 && blockstate.canSurvive(worldIn, blockpos)) {
				Direction randomD = Direction.getRandom(rand);
				while (!monkeyBrushState(blockstate, randomD).canSurvive(worldIn, blockpos)) {
					randomD = Direction.getRandom(rand);
				}
				worldIn.setBlock(blockpos, monkeyBrushState(blockstate, randomD), 2);
				++i;
			}
		}

		return i > 0;
	}

	public static BlockState monkeyBrushState(BlockState state, Direction direction) {
		if (direction.getAxis().isVertical() && state.getBlock() instanceof MonkeyBrushBlock) {
			return state.setValue(MonkeyBrushBlock.FACING, direction);
		} else {
			if (state.getBlock() == AtmosphericBlocks.WARM_MONKEY_BRUSH.get())
				state = AtmosphericBlocks.WARM_WALL_MONKEY_BRUSH.get().defaultBlockState();
			if (state.getBlock() == AtmosphericBlocks.HOT_MONKEY_BRUSH.get())
				state = AtmosphericBlocks.HOT_WALL_MONKEY_BRUSH.get().defaultBlockState();
			if (state.getBlock() == AtmosphericBlocks.SCALDING_MONKEY_BRUSH.get())
				state = AtmosphericBlocks.SCALDING_WALL_MONKEY_BRUSH.get().defaultBlockState();
			return state.setValue(WallMonkeyBrushBlock.FACING, direction);
		}
	}
}
