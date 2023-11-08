package com.teamabnormals.atmospheric.common.block;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.teamabnormals.atmospheric.common.block.state.properties.DragonRootsStage;
import com.teamabnormals.atmospheric.common.entity.projectile.DragonFruit;
import com.teamabnormals.atmospheric.core.registry.AtmosphericEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.AxisDirection;
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
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.ForgeHooks;

import java.util.Map;

public class DragonRootsBlock extends BushBlock implements BonemealableBlock {
	public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
	public static final EnumProperty<DragonRootsStage> TOP_STAGE = EnumProperty.create("top", DragonRootsStage.class);
	public static final EnumProperty<DragonRootsStage> BOTTOM_STAGE = EnumProperty.create("bottom", DragonRootsStage.class);

	private static final Map<Direction, VoxelShape> SHAPES = Maps.newEnumMap(ImmutableMap.of(
			Direction.NORTH, Block.box(0.0D, 6.0D, 5.0D, 16.0D, 14.0D, 16.0D),
			Direction.SOUTH, Block.box(0.0D, 6.0D, 0.0D, 16.0D, 14.0D, 11.0D),
			Direction.WEST, Block.box(5.0D, 6.0D, 0.0D, 16.0D, 14.0D, 16.0D),
			Direction.EAST, Block.box(0.0D, 6.0D, 0.0D, 11.0D, 14.0D, 16.0D)));

	public DragonRootsBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(TOP_STAGE, DragonRootsStage.ROOTS).setValue(BOTTOM_STAGE, DragonRootsStage.ROOTS));
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

	public static boolean hasFruit(BlockState state) {
		return hasFruit(TOP_STAGE, state) || hasFruit(BOTTOM_STAGE, state);
	}

	public static boolean hasFruit(EnumProperty<DragonRootsStage> property, BlockState state) {
		DragonRootsStage stage = state.getValue(property);
		return stage == DragonRootsStage.FRUIT || stage == DragonRootsStage.FLOWERING || isEnder(property, state);
	}

	public static boolean isFlowering(BlockState state) {
		return isFlowering(TOP_STAGE, state) || isFlowering(BOTTOM_STAGE, state);
	}

	public static boolean isFlowering(EnumProperty<DragonRootsStage> property, BlockState state) {
		DragonRootsStage stage = state.getValue(property);
		return stage == DragonRootsStage.FLOWERING || stage == DragonRootsStage.FLOWERING_ENDER;
	}

	public static boolean isDouble(BlockState state) {
		return state.getValue(TOP_STAGE) != DragonRootsStage.NONE && state.getValue(BOTTOM_STAGE) != DragonRootsStage.NONE;
	}

	public static boolean isEnder(EnumProperty<DragonRootsStage> property, BlockState state) {
		DragonRootsStage stage = state.getValue(property);
		return stage == DragonRootsStage.ENDER || stage == DragonRootsStage.FLOWERING_ENDER;
	}

	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
		if (!hasFruit(state) && player.getItemInHand(hand).is(Items.BONE_MEAL)) {
			return InteractionResult.PASS;
		} else if (hasFruit(state)) {
			level.playSound(null, pos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, 0.8F + level.random.nextFloat() * 0.4F);
			BlockState newState = state;

			if (hasFruit(TOP_STAGE, state)) {
				newState = newState.setValue(TOP_STAGE, DragonRootsStage.ROOTS);
			}

			if (hasFruit(BOTTOM_STAGE, state)) {
				newState = newState.setValue(BOTTOM_STAGE, DragonRootsStage.ROOTS);
			}

			level.setBlock(pos, newState, 2);
			level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, newState));
			dropFruits(state, level, pos);

			return InteractionResult.sidedSuccess(level.isClientSide);
		} else {
			return super.use(state, level, pos, player, hand, result);
		}
	}

	public static void dropFruits(BlockState state, Level level, BlockPos pos) {
		Direction dir = state.getValue(FACING);

		if (hasFruit(TOP_STAGE, state)) {
			addDragonFruitWithOffset(TOP_STAGE, state, level, pos, dir == Direction.NORTH || dir == Direction.EAST ? 0.25F : 0.75F, 0.75F, dir.getAxisDirection() == AxisDirection.NEGATIVE ? 0.75F : 0.25F);
		}

		if (hasFruit(BOTTOM_STAGE, state)) {
			addDragonFruitWithOffset(BOTTOM_STAGE, state, level, pos, dir.getAxisDirection() == AxisDirection.NEGATIVE ? 0.75F : 0.25F, 0.25F, dir == Direction.NORTH || dir == Direction.EAST ? 0.75F : 0.25F);
		}
	}

	@Override
	public void destroy(LevelAccessor levelAccessor, BlockPos pos, BlockState state) {
		if (!levelAccessor.isClientSide() && hasFruit(state) && levelAccessor instanceof Level level) {
			dropFruits(state, level, pos);
		}
		super.destroy(levelAccessor, pos, state);
	}

	public static void addDragonFruitWithOffset(EnumProperty<DragonRootsStage> property, BlockState state, Level level, BlockPos pos, float x, float y, float z) {
		DragonFruit dragonFruit = AtmosphericEntityTypes.DRAGON_FRUIT.get().create(level);
		dragonFruit.setEnder(isEnder(property, state));
		dragonFruit.setFlowering(isFlowering(property, state));
		dragonFruit.setPos(pos.getX() + x, pos.getY() + y, pos.getZ() + z);
		dragonFruit.setRollingDirection(state.getValue(FACING));
		dragonFruit.setYRot(state.getValue(FACING).toYRot());
		level.addFreshEntity(dragonFruit);
	}

	@Override
	public BlockState rotate(BlockState state, Rotation rot) {
		return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING, TOP_STAGE, BOTTOM_STAGE);
	}

	@Override
	public boolean canBeReplaced(BlockState state, BlockPlaceContext context) {
		return !context.isSecondaryUseActive() && context.getItemInHand().getItem() == this.asItem() && !isDouble(state) || super.canBeReplaced(state, context);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		Level level = context.getLevel();
		Direction direction = context.getClickedFace();
		if (direction.getAxis().isVertical())
			direction = context.getHorizontalDirection().getOpposite();

		BlockPos pos = context.getClickedPos();
		BlockState state = level.getBlockState(pos);

		if (state.is(this) && !isDouble(state)) {
			if (state.getValue(TOP_STAGE) == DragonRootsStage.NONE) {
				return state.setValue(TOP_STAGE, DragonRootsStage.ROOTS);
			} else if (state.getValue(BOTTOM_STAGE) == DragonRootsStage.NONE) {
				return state.setValue(BOTTOM_STAGE, DragonRootsStage.ROOTS);
			}
		}

		boolean top = context.getClickLocation().y - (double) pos.getY() > 0.5D;
		return this.defaultBlockState().setValue(FACING, direction)
				.setValue(top ? TOP_STAGE : BOTTOM_STAGE, DragonRootsStage.ROOTS)
				.setValue(!top ? TOP_STAGE : BOTTOM_STAGE, DragonRootsStage.NONE);
	}

	@Override
	public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		BlockState newState = state;

		boolean fruitingConditions = (level.getRawBrightness(pos, 0) >= 12 && level.isDay()) || level.getRawBrightness(pos, 15) >= 12;
		boolean floweringConditions = level.getRawBrightness(pos, 0) <= 3 || (level.getRawBrightness(pos, 15) <= 3 && level.isNight());
		boolean cropGrew = false;

		if (state.getValue(TOP_STAGE) == DragonRootsStage.ROOTS && fruitingConditions && ForgeHooks.onCropsGrowPre(level, pos, state, random.nextInt(10) == 0)) {
			newState = state.setValue(TOP_STAGE, getFruitStage(level));
			cropGrew = true;
		}

		if (state.getValue(BOTTOM_STAGE) == DragonRootsStage.ROOTS && fruitingConditions && ForgeHooks.onCropsGrowPre(level, pos, state, random.nextInt(10) == 0)) {
			newState = state.setValue(BOTTOM_STAGE, getFruitStage(level));
			cropGrew = true;
		}

		if (hasFruit(TOP_STAGE, state) && isFlowering(TOP_STAGE, state) != floweringConditions) {
			newState = newState.setValue(TOP_STAGE, switchFruitAndFlowering(state.getValue(TOP_STAGE)));
		}

		if (hasFruit(BOTTOM_STAGE, state) && isFlowering(BOTTOM_STAGE, state) != floweringConditions) {
			newState = newState.setValue(BOTTOM_STAGE, switchFruitAndFlowering(state.getValue(BOTTOM_STAGE)));
		}

		if (newState != state) {
			level.setBlock(pos, newState, 2);
			level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(newState));
			if (cropGrew) {
				ForgeHooks.onCropsGrowPost(level, pos, state);
			}
		}
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		return SHAPES.get(state.getValue(FACING));
	}

	@Override
	public boolean isValidBonemealTarget(BlockGetter worldIn, BlockPos pos, BlockState state, boolean isClient) {
		return !hasFruit(state);
	}

	@Override
	public boolean isBonemealSuccess(Level world, RandomSource random, BlockPos blockPos, BlockState blockState) {
		return world.random.nextFloat() < 0.3F;
	}

	@Override
	public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
		boolean top = state.getValue(TOP_STAGE) == DragonRootsStage.ROOTS;
		boolean bottom = state.getValue(BOTTOM_STAGE) == DragonRootsStage.ROOTS;

		if (top || bottom) {
			EnumProperty<DragonRootsStage> property = (top && bottom) ? (random.nextBoolean() ? TOP_STAGE : BOTTOM_STAGE) : top ? TOP_STAGE : BOTTOM_STAGE;
			level.setBlockAndUpdate(pos, state.setValue(property, isFlowering(state) ? getFloweringStage(level) : getFruitStage(level)));
		}
	}

	public static DragonRootsStage getFruitStage(ServerLevel level) {
		return level.dimensionTypeId().equals(BuiltinDimensionTypes.END) ? DragonRootsStage.ENDER : DragonRootsStage.FRUIT;
	}

	public static DragonRootsStage switchFruitAndFlowering(DragonRootsStage stage) {
		return stage == DragonRootsStage.FLOWERING ? DragonRootsStage.FRUIT : stage == DragonRootsStage.FRUIT ? DragonRootsStage.FLOWERING : stage == DragonRootsStage.FLOWERING_ENDER ? DragonRootsStage.ENDER : stage == DragonRootsStage.ENDER ? DragonRootsStage.FLOWERING_ENDER : DragonRootsStage.NONE;
	}

	public static DragonRootsStage getFloweringStage(ServerLevel level) {
		return level.dimensionTypeId().equals(BuiltinDimensionTypes.END) ? DragonRootsStage.FLOWERING_ENDER : DragonRootsStage.FLOWERING;
	}
}
