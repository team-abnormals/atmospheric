package com.teamabnormals.atmospheric.common.block;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.teamabnormals.atmospheric.common.entity.projectile.DragonFruit;
import com.teamabnormals.atmospheric.core.registry.AtmosphericEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Map;

public class DragonRootsBlock extends BushBlock implements BonemealableBlock {
	public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
	public static final BooleanProperty TOP = BooleanProperty.create("top");
	public static final BooleanProperty BOTTOM = BooleanProperty.create("bottom");
	public static final BooleanProperty FRUIT = BooleanProperty.create("fruit");

	private static final Map<Direction, VoxelShape> SHAPES = Maps.newEnumMap(ImmutableMap.of(
			Direction.NORTH, Block.box(0.0D, 6.0D, 5.0D, 16.0D, 14.0D, 16.0D),
			Direction.SOUTH, Block.box(0.0D, 6.0D, 0.0D, 16.0D, 14.0D, 11.0D),
			Direction.WEST, Block.box(5.0D, 6.0D, 0.0D, 16.0D, 14.0D, 16.0D),
			Direction.EAST, Block.box(0.0D, 6.0D, 0.0D, 11.0D, 14.0D, 16.0D)));

	public DragonRootsBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(TOP, true).setValue(BOTTOM, true).setValue(FRUIT, false));
	}

	@Override
	protected boolean mayPlaceOn(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return true;
	}

	@Override
	public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
		Direction direction = state.getValue(FACING);
		BlockPos offsetPos = pos.relative(direction.getOpposite());
		return level.getBlockState(offsetPos).isFaceSturdy(level, offsetPos, direction);
	}

	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
		if (!state.getValue(FRUIT) && player.getItemInHand(hand).is(Items.BONE_MEAL)) {
			return InteractionResult.PASS;
		} else if (state.getValue(FRUIT)) {
			level.playSound(null, pos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, 0.8F + level.random.nextFloat() * 0.4F);
			BlockState newState = state.setValue(FRUIT, false);
			level.setBlock(pos, newState, 2);
			level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, newState));

			DragonFruit dragonFruit = AtmosphericEntityTypes.DRAGON_FRUIT.get().create(level);
			dragonFruit.setPos(pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F);
			dragonFruit.setRollingDirection(state.getValue(FACING));
			dragonFruit.setYRot(state.getValue(FACING).toYRot());

			level.addFreshEntity(dragonFruit);

			return InteractionResult.sidedSuccess(level.isClientSide);
		} else {
			return super.use(state, level, pos, player, hand, result);
		}
	}

	@Override
	public BlockState rotate(BlockState state, Rotation rot) {
		return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING, TOP, BOTTOM, FRUIT);
	}

	@Override
	public boolean canBeReplaced(BlockState state, BlockPlaceContext context) {
		return !context.isSecondaryUseActive() && context.getItemInHand().getItem() == this.asItem() && state.getValue(TOP) != state.getValue(BOTTOM) || super.canBeReplaced(state, context);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		Level level = context.getLevel();
		Direction direction = context.getClickedFace();
		if (direction.getAxis().isVertical())
			direction = context.getHorizontalDirection().getOpposite();

		BlockPos pos = context.getClickedPos();
		BlockState state = level.getBlockState(pos);

		if (state.is(this)) {
			if (state.getValue(TOP)) {
				return state.setValue(BOTTOM, true);
			} else if (state.getValue(BOTTOM)) {
				return state.setValue(TOP, true);
			}
		}

		return this.defaultBlockState().setValue(FACING, direction).setValue(TOP, context.getClickLocation().y - (double) pos.getY() > 0.5D).setValue(BOTTOM, context.getClickLocation().y - (double) pos.getY() <= 0.5D);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		return SHAPES.get(state.getValue(FACING));
	}

	@Override
	public boolean isValidBonemealTarget(BlockGetter worldIn, BlockPos pos, BlockState state, boolean isClient) {
		return !state.getValue(FRUIT);
	}

	@Override
	public boolean isBonemealSuccess(Level world, RandomSource random, BlockPos blockPos, BlockState blockState) {
		return true;
	}

	@Override
	public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
		level.setBlockAndUpdate(pos, state.setValue(FRUIT, true));
	}
}
