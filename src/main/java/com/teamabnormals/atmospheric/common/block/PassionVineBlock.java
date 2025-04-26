package com.teamabnormals.atmospheric.common.block;

import com.teamabnormals.atmospheric.common.block.state.properties.PassionVineAttachment;
import com.teamabnormals.atmospheric.core.other.tags.AtmosphericBlockTags;
import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import com.teamabnormals.atmospheric.core.registry.AtmosphericItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.CommonHooks;
import net.neoforged.neoforge.common.Tags;

import javax.annotation.Nullable;

public class PassionVineBlock extends Block implements BonemealableBlock {
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
	public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 4);
	public static final EnumProperty<PassionVineAttachment> ATTACHMENT = EnumProperty.create("attachment", PassionVineAttachment.class);

	protected static final VoxelShape EAST_AABB = Block.box(0.0D, 0.0D, 0.0D, 1.0D, 16.0D, 16.0D);
	protected static final VoxelShape WEST_AABB = Block.box(15.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	protected static final VoxelShape SOUTH_AABB = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 1.0D);
	protected static final VoxelShape NORTH_AABB = Block.box(0.0D, 0.0D, 15.0D, 16.0D, 16.0D, 16.0D);

	public PassionVineBlock(Properties properties) {
		super(properties);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return switch (state.getValue(FACING)) {
			case NORTH -> NORTH_AABB;
			case SOUTH -> SOUTH_AABB;
			case WEST -> WEST_AABB;
			case EAST -> EAST_AABB;
			default -> null;
		};
	}

	@Override
	public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		int age = state.getValue(AGE);

		boolean canFlower = age >= 1;
		boolean canFruit = age < 4 && level.getRawBrightness(pos, 0) >= 5;

		if (canFlower) {
			if (canFruit) {
				if (level.random.nextBoolean()) {
					attemptGrowDown(state, level, pos, random);
				} else {
					attemptGrowFruit(state, level, pos, random);
				}
			} else {
				attemptGrowDown(state, level, pos, random);
			}
		} else if (canFruit) {
			attemptGrowFruit(state, level, pos, random);
		}
	}

	public BlockState determineState(BlockState state, LevelReader level, BlockPos pos) {
		BlockState below = level.getBlockState(pos.below());
		BlockState above = level.getBlockState(pos.above());
		boolean vineAbove = (above.is(this) && above.getValue(FACING) == state.getValue(FACING));
		boolean vineBelow = (below.is(this) && below.getValue(FACING) == state.getValue(FACING));

		if (vineAbove) {
			return vineBelow ? state.setValue(ATTACHMENT, PassionVineAttachment.MIDDLE) : state.setValue(ATTACHMENT, PassionVineAttachment.BOTTOM);
		} else {
			return vineBelow ? state.setValue(ATTACHMENT, PassionVineAttachment.TOP) : state.setValue(ATTACHMENT, PassionVineAttachment.NONE);
		}
	}

	public void attemptGrowFruit(BlockState state, Level level, BlockPos pos, RandomSource random) {
		int i = state.getValue(AGE);
		state = this.determineState(state, level, pos);
		Direction direction = state.getValue(FACING);
		BlockState hanging = level.getBlockState(pos.relative(direction.getOpposite()));

		if (CommonHooks.canCropGrow(level, pos, state, random.nextInt(7) == 0)) {
			boolean isHanging = hanging.is(AtmosphericBlockTags.PASSION_VINE_GROWABLE_ON);
			if ((isHanging && i < 4) || (!isHanging && i < 1)) {
				level.setBlock(pos, state.setValue(AGE, i + 1), 2);
				CommonHooks.fireCropGrowPost(level, pos, state);
			}
		}

	}

	public void attemptGrowDown(BlockState state, Level level, BlockPos pos, RandomSource random) {
		if (CommonHooks.canCropGrow(level, pos, state, random.nextInt(7) == 0)) {
			if (level.getBlockState(pos.below()).isAir()) {
				level.setBlockAndUpdate(pos.below(), this.determineState(AtmosphericBlocks.PASSION_VINE.get().defaultBlockState(), level, pos.below()).setValue(AGE, 0).setValue(FACING, state.getValue(FACING)));
			}
		}
	}

	@Override
	protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
		int age = state.getValue(AGE);
		if (age != 4 && stack.is(Items.BONE_MEAL)) {
			return ItemInteractionResult.SKIP_DEFAULT_BLOCK_INTERACTION;
		} else if (age == 1 && (stack.is(Tags.Items.TOOLS_SHEAR))) {
			Direction direction = hitResult.getDirection();
			Direction direction1 = direction.getAxis() == Direction.Axis.Y ? player.getDirection().getOpposite() : direction;
			popResourceFromFace(level, pos, direction1, new ItemStack(Items.WHITE_DYE));
			stack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(hand));
			level.playSound(null, pos, SoundEvents.SHEEP_SHEAR, SoundSource.BLOCKS, 1.0F, 0.8F + level.random.nextFloat() * 0.4F);
			level.setBlock(pos, state.setValue(AGE, 0), 2);
			return ItemInteractionResult.sidedSuccess(level.isClientSide);
		} else {
			return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
		}
	}

	@Override
	public InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hit) {
		int age = state.getValue(AGE);
		if (age == 4) {
			popResource(level, pos, new ItemStack(AtmosphericItems.PASSION_FRUIT.get(), 1 + level.random.nextInt(2) + level.random.nextInt(2) + level.random.nextInt(3)));
			level.playSound(null, pos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, 0.8F + level.random.nextFloat() * 0.4F);
			level.setBlock(pos, state.setValue(AGE, 1), 2);
			return InteractionResult.sidedSuccess(level.isClientSide);
		} else {
			return super.useWithoutItem(state, level, pos, player, hit);
		}
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(AGE, ATTACHMENT, FACING);
	}

	private boolean canAttachTo(BlockGetter level, BlockPos pos, Direction direction) {
		BlockState blockstate = level.getBlockState(pos);
		return !blockstate.isSignalSource() && (blockstate.isFaceSturdy(level, pos, direction) || blockstate.is(BlockTags.LEAVES));
	}

	@Override
	public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
		Direction direction = state.getValue(FACING);
		return this.canAttachTo(level, pos.relative(direction.getOpposite()), direction) || (level.getBlockState(pos.above()).is(this) && level.getBlockState(pos.above()).getValue(FACING) == state.getValue(FACING));
	}

	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos pos, BlockPos facingPos) {
		if (!state.canSurvive(level, pos)) {
			return Blocks.AIR.defaultBlockState();
		} else {
			return super.updateShape(this.determineState(state, level, pos), facing, facingState, level, pos, facingPos);
		}
	}

	@Override
	@Nullable
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		if (!context.replacingClickedOnBlock()) {
			BlockState oppositeState = context.getLevel().getBlockState(context.getClickedPos().relative(context.getClickedFace().getOpposite()));
			if (oppositeState.getBlock() == this && oppositeState.getValue(FACING) == context.getClickedFace()) {
				return null;
			}
		}

		BlockState state = this.defaultBlockState();
		LevelReader level = context.getLevel();
		BlockPos pos = context.getClickedPos();
		for (Direction direction : context.getNearestLookingDirections()) {
			if (direction.getAxis().isHorizontal()) {
				state = state.setValue(FACING, direction.getOpposite());
				if (state.canSurvive(level, pos)) {
					return this.determineState(state, level, pos);
				}
			}
		}

		return null;
	}

	@Override
	public boolean isValidBonemealTarget(LevelReader worldIn, BlockPos pos, BlockState state) {
		return state.getValue(AGE) < 4;
	}

	@Override
	public boolean isBonemealSuccess(Level worldIn, RandomSource rand, BlockPos pos, BlockState state) {
		return true;
	}

	@Override
	public void performBonemeal(ServerLevel worldIn, RandomSource rand, BlockPos pos, BlockState state) {
		int i = Math.min(4, state.getValue(AGE) + 1);
		worldIn.setBlock(pos, state.setValue(AGE, i), 2);
	}
}
