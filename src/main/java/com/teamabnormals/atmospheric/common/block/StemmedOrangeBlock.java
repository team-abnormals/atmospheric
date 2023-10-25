package com.teamabnormals.atmospheric.common.block;

import com.teamabnormals.atmospheric.common.item.OrangeBlockItem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class StemmedOrangeBlock extends DirectionalBlock {
	public static final IntegerProperty ORANGES = IntegerProperty.create("oranges", 1, 2);

	private static final VoxelShape SINGLE = Block.box(5.0D, 0.0D, 4.0D, 12.0D, 6.0D, 11.0D);
	private static final VoxelShape SINGLE_CEILING = Block.box(5.0D, 8.0D, 4.0D, 12.0D, 14.0D, 11.0D);
	private static final VoxelShape SINGLE_WALL = Block.box(5.0D, 4.0D, 9.0D, 12.0D, 10.0D, 16.0D);

	private static final VoxelShape DOUBLE = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 6.0D, 15.0D);
	private static final VoxelShape DOUBLE_CEILING = Block.box(1.0D, 8.0D, 1.0D, 15.0D, 14.0D, 15.0D);
	private static final VoxelShape DOUBLE_WALL = Block.box(1.0D, 0.0D, 9.0D, 16.0D, 14.0D, 16.0D);

	private static final VoxelShape DOUBLE_COLLISION = Shapes.or(Block.box(1.0D, 0.0D, 1.0D, 8.0D, 6.0D, 8.0D), Block.box(8.0D, 0.0D, 8.0D, 15.0D, 6.0D, 15.0D));
	private static final VoxelShape DOUBLE_CEILING_COLLISION = Shapes.or(Block.box(1.0D, 8.0D, 1.0D, 8.0D, 14.0D, 8.0D), Block.box(8.0D, 8.0D, 8.0D, 15.0D, 14.0D, 15.0D));
	private static final VoxelShape DOUBLE_WALL_COLLISION = Shapes.or(Block.box(1.0D, 0.0D, 9.0D, 8.0D, 6.0D, 16.0D), Block.box(9.0D, 8.0D, 9.0D, 16.0D, 14.0D, 16.0D));

	public StemmedOrangeBlock(BlockBehaviour.Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(ORANGES, 1));
	}

	@Override
	public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
		return canAttach(level, pos, state.getValue(FACING).getOpposite());
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		boolean single = state.getValue(ORANGES) < 2;
		return switch (state.getValue(FACING)) {
			case UP -> single ? SINGLE : DOUBLE;
			case DOWN -> single ? SINGLE_CEILING : DOUBLE_CEILING;
			default -> rotateShape(Direction.NORTH, state.getValue(FACING), single ? SINGLE_WALL : DOUBLE_WALL);
		};
	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return state.getValue(ORANGES) < 2 ? this.getShape(state, level, pos, context) : switch (state.getValue(FACING)) {
			case UP -> DOUBLE_COLLISION;
			case DOWN -> DOUBLE_CEILING_COLLISION;
			default -> rotateShape(Direction.NORTH, state.getValue(FACING), DOUBLE_WALL_COLLISION);
		};
	}

	public static VoxelShape rotateShape(Direction from, Direction to, VoxelShape shape) {
		VoxelShape[] buffer = new VoxelShape[]{shape, Shapes.empty()};
		int times = (to.get2DDataValue() - from.get2DDataValue() + 4) % 4;
		for (int i = 0; i < times; i++) {
			buffer[0].forAllBoxes((minX, minY, minZ, maxX, maxY, maxZ) -> buffer[1] = Shapes.or(buffer[1], Shapes.create(1 - maxZ, minY, minX, 1 - minZ, maxY, maxX)));
			buffer[0] = buffer[1];
			buffer[1] = Shapes.empty();
		}

		return buffer[0];
	}

	public static boolean canAttach(LevelReader level, BlockPos pos, Direction direction) {
		BlockPos relativePos = pos.relative(direction);
		return level.getBlockState(relativePos).isFaceSturdy(level, relativePos, direction.getOpposite()) || level.getBlockState(relativePos).is(BlockTags.LEAVES);
	}

	@Override
	public BlockState updateShape(BlockState state, Direction direction, BlockState offsetState, LevelAccessor level, BlockPos pos, BlockPos offsetPos) {
		return state.getValue(FACING).getOpposite() == direction && !state.canSurvive(level, pos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, direction, offsetState, level, pos, offsetPos);
	}

	@Nullable
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		BlockState state = context.getLevel().getBlockState(context.getClickedPos());
		if (state.is(this)) {
			return state.cycle(ORANGES);
		} else {
			for (Direction direction : context.getNearestLookingDirections()) {
				BlockState newState = this.defaultBlockState().setValue(FACING, direction.getOpposite());
				if (newState.canSurvive(context.getLevel(), context.getClickedPos())) {
					return newState;
				}
			}

			return null;
		}
	}

	public void fallOn(Level level, BlockState state, BlockPos pos, Entity entity, float distance) {
		if (!level.isClientSide) {
			level.destroyBlock(pos, false);
			OrangeBlockItem.createVaporCloud(level, new Vec3(pos.getX() + 0.5F, pos.getY(), pos.getZ() + 0.5F));
		}

		super.fallOn(level, state, pos, entity, distance);
	}

	@Override
	public boolean canBeReplaced(BlockState state, BlockPlaceContext context) {
		return context.getItemInHand().getItem() == this.asItem() && state.getValue(ORANGES) < 2 || super.canBeReplaced(state, context);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING, ORANGES);
	}
}