package com.teamabnormals.atmospheric.common.block;

import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import com.teamabnormals.blueprint.common.block.wood.BlueprintLeavesBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;

public class CurrantLeavesBlock extends BlueprintLeavesBlock {

	public CurrantLeavesBlock(Properties properties) {
		super(properties);
	}

	@Override
	public boolean isRandomlyTicking(BlockState state) {
		return false;
	}

	@Override
	public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
//		level.setBlock(pos, updateDistance(state, level, pos), 3);
		CurrantStalkBlock.breakLeaves(level, pos);
		level.destroyBlock(pos, true);
	}

	public static int getDistanceAt(BlockState state) {
		if (state.is(AtmosphericBlocks.CURRANT_STALK.get()) || state.is(AtmosphericBlocks.CURRANT_STALK_BUNDLE.get())) {
			return 0;
		} else {
			return state.getBlock() instanceof LeavesBlock ? state.getValue(DISTANCE) : 7;
		}
	}

	@Override
	public BlockState updateShape(BlockState state, Direction direction, BlockState offsetState, LevelAccessor level, BlockPos pos, BlockPos offsetPos) {
		if (state.getValue(WATERLOGGED)) {
			level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
		}

//		int i = getDistanceAt(offsetState) + 1;
//		if (i != 1 || state.getValue(DISTANCE) != i) {
//			level.scheduleTick(pos, this, 1);
//		}

		return state;
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
//		BlockState state = this.defaultBlockState().setValue(PERSISTENT, true).setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
//		return updateDistance(state, context.getLevel(), context.getClickedPos());
		return this.defaultBlockState().setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
	}

	private static BlockState updateDistance(BlockState state, LevelAccessor level, BlockPos pos) {
		int i = 7;
		BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();

		for (Direction direction : Direction.values()) {
			mutablePos.setWithOffset(pos, direction);
			i = Math.min(i, getDistanceAt(level.getBlockState(mutablePos)) + 1);
			if (i == 1) {
				break;
			}
		}

		return state.setValue(DISTANCE, i);
	}
}
