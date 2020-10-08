package com.minecraftabnormals.atmospheric.common.world.gen.feature;

import java.util.Random;

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
import net.minecraft.world.gen.feature.structure.StructureManager;

public class MonkeyBrushFeature extends Feature<NoFeatureConfig> {
	int temp;

	public MonkeyBrushFeature(Codec<NoFeatureConfig> p_i49876_1_, int temperature) {
		super(p_i49876_1_);
		temp = temperature;
	}

	@Override
	public boolean func_230362_a_(ISeedReader worldIn, StructureManager manager, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config) {
		BlockState blockstate = AtmosphericBlocks.HOT_MONKEY_BRUSH.get().getDefaultState();
		if (temp == 3) {
			blockstate = AtmosphericBlocks.SCALDING_MONKEY_BRUSH.get().getDefaultState();
		} else if (temp == 1) {
			blockstate = AtmosphericBlocks.WARM_MONKEY_BRUSH.get().getDefaultState();
		}
		int i = 0;

		for (int j = 0; j < 64; ++j) {
			BlockPos blockpos = pos.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));
			if (worldIn.isAirBlock(blockpos) && blockpos.getY() < 255 && blockstate.isValidPosition(worldIn, blockpos)) {
				Direction randomD = Direction.func_239631_a_(rand);
				while (!monkeyBrushState(blockstate, randomD).isValidPosition(worldIn, blockpos)) {
					randomD = Direction.func_239631_a_(rand);
				}
				worldIn.setBlockState(blockpos, monkeyBrushState(blockstate, randomD), 2);
				++i;
			}
		}

		return i > 0;
	}

	public static BlockState monkeyBrushState(BlockState state, Direction direction) {
		if (direction.getAxis().isVertical() && state.getBlock() instanceof MonkeyBrushBlock) {
			return state.with(MonkeyBrushBlock.FACING, direction);
		} else {
			if (state.getBlock() == AtmosphericBlocks.WARM_MONKEY_BRUSH.get())
				state = AtmosphericBlocks.WARM_WALL_MONKEY_BRUSH.get().getDefaultState();
			if (state.getBlock() == AtmosphericBlocks.HOT_MONKEY_BRUSH.get())
				state = AtmosphericBlocks.HOT_WALL_MONKEY_BRUSH.get().getDefaultState();
			if (state.getBlock() == AtmosphericBlocks.SCALDING_MONKEY_BRUSH.get())
				state = AtmosphericBlocks.SCALDING_WALL_MONKEY_BRUSH.get().getDefaultState();
			return state.with(WallMonkeyBrushBlock.FACING, direction);
		}
	}
}
