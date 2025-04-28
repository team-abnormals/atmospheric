package com.teamabnormals.atmospheric.common.block;

import com.google.common.collect.Lists;
import com.teamabnormals.atmospheric.core.other.tags.AtmosphericBlockTags;
import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import com.teamabnormals.atmospheric.core.registry.AtmosphericMobEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.ArrayList;
import java.util.Collections;

public class MonkeyBrushBlock extends FlowerBlock implements BonemealableBlock {
	public static final DirectionProperty FACING = DirectionProperty.create("facing", Direction.Plane.VERTICAL);

	protected static final VoxelShape SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D);

	public MonkeyBrushBlock(Properties properties) {
		super(AtmosphericMobEffects.RELIEF, 6, properties);
		if (!(this instanceof WallMonkeyBrushBlock)) {
			this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.UP));
		}
	}

	@Override
	protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
		return state.is(AtmosphericBlockTags.MONKEY_BRUSH_PLACEABLE);
	}

	@Override
	public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
		BlockPos offsetPos = pos.relative(state.getValue(FACING).getOpposite());
		return this.mayPlaceOn(level.getBlockState(offsetPos), level, offsetPos);
	}

	@Override
	public BlockState rotate(BlockState state, Rotation rot) {
		return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return context.getClickedFace().getAxis().isVertical() ? this.defaultBlockState().setValue(FACING, context.getClickedFace()) : this.defaultBlockState();
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		Vec3 vec3d = state.getOffset(level, pos);
		return SHAPE.move(vec3d.x, vec3d.y, vec3d.z);
	}

	@Override
	public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
		return true;
	}

	@Override
	public boolean isBonemealSuccess(Level world, RandomSource random, BlockPos blockPos, BlockState blockState) {
		return true;
	}

	@Override
	public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
		for (int i = 0; i < 64; ++i) {
			BlockPos newPos = pos.offset(random.nextInt(5) - 2, (random.nextInt(5) - 2), random.nextInt(5) - 2);
			if (attemptBrush(level, newPos, state)) {
				return;
			}
		}
	}

	public static boolean attemptBrush(LevelAccessor level, BlockPos pos, BlockState state) {
		if (level.isEmptyBlock(pos) && pos.getY() < level.getMaxBuildHeight()) {
			ArrayList<Direction> directions = Lists.newArrayList(Direction.values());
			Collections.shuffle(directions);
			for (Direction direction : directions) {
				BlockState newState = monkeyBrushState(state, direction);
				if (newState.canSurvive(level, pos)) {
					level.setBlock(pos, newState, 2);
					return true;
				}
			}
		}

		return false;
	}

	public static BlockState monkeyBrushState(BlockState state, Direction direction) {
		boolean isWarm = state.is(AtmosphericBlocks.WARM_MONKEY_BRUSH) || state.is(AtmosphericBlocks.WARM_WALL_MONKEY_BRUSH);
		boolean isHot = state.is(AtmosphericBlocks.HOT_MONKEY_BRUSH) || state.is(AtmosphericBlocks.HOT_WALL_MONKEY_BRUSH);
		boolean isScalding = state.is(AtmosphericBlocks.SCALDING_MONKEY_BRUSH) || state.is(AtmosphericBlocks.SCALDING_WALL_MONKEY_BRUSH);

		if (isWarm) state = AtmosphericBlocks.WARM_MONKEY_BRUSH.get().defaultBlockState();
		if (isHot) state = AtmosphericBlocks.HOT_MONKEY_BRUSH.get().defaultBlockState();
		if (isScalding) state = AtmosphericBlocks.SCALDING_MONKEY_BRUSH.get().defaultBlockState();

		if (direction.getAxis().isVertical() && !(state.getBlock() instanceof WallMonkeyBrushBlock)) {
			return state.setValue(MonkeyBrushBlock.FACING, direction);
		} else {
			if (isWarm) state = AtmosphericBlocks.WARM_WALL_MONKEY_BRUSH.get().defaultBlockState();
			if (isHot) state = AtmosphericBlocks.HOT_WALL_MONKEY_BRUSH.get().defaultBlockState();
			if (isScalding) state = AtmosphericBlocks.SCALDING_WALL_MONKEY_BRUSH.get().defaultBlockState();
			return state.setValue(WallMonkeyBrushBlock.FACING, direction);
		}
	}
}
