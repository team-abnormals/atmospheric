package com.teamabnormals.atmospheric.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CurrantStalkBlock extends CurrantStalkBundleBlock implements SimpleWaterloggedBlock {
	private static final VoxelShape SHAPE_X = box(0.0F, 5.0F, 5.0F, 16.0F, 11.0F, 11.0F);
	private static final VoxelShape SHAPE_Y = box(5.0F, 0.0F, 5.0F, 11.0F, 16.0F, 11.0F);
	private static final VoxelShape SHAPE_Z = box(5.0F, 5.0F, 0.0F, 11.0F, 11.0F, 16.0F);

	private static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

	public CurrantStalkBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(AXIS, Axis.Y).setValue(WATERLOGGED, false));
	}

	@Override
	public boolean isValidDirection(BlockState state, Direction direction) {
		return direction.getAxis() == state.getValue(AXIS);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		return switch (state.getValue(AXIS)) {
			case X -> SHAPE_X;
			case Y -> SHAPE_Y;
			default -> SHAPE_Z;
		};
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
		return super.getStateForPlacement(context).setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
	}

	@Override
	public BlockState updateShape(BlockState state, Direction direction, BlockState offsetState, LevelAccessor level, BlockPos pos, BlockPos offsetPos) {
		if (state.getValue(WATERLOGGED)) {
			level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
		}

		return super.updateShape(state, direction, offsetState, level, pos, offsetPos);
	}

	@Override
	public FluidState getFluidState(BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder.add(WATERLOGGED));
	}
}
