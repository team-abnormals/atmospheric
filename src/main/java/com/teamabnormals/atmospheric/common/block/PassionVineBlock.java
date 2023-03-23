package com.teamabnormals.atmospheric.common.block;

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
import net.minecraft.world.entity.item.ItemEntity;
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
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.ForgeHooks;

import javax.annotation.Nullable;

public class PassionVineBlock extends Block implements BonemealableBlock {
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
	public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 4);
	//public static final EnumProperty<PassionVineAttachment> ATTACHMENT = EnumProperty.create("attachment", PassionVineAttachment.class);

	protected static final VoxelShape EAST_AABB = Block.box(0.0D, 0.0D, 0.0D, 1.0D, 16.0D, 16.0D);
	protected static final VoxelShape WEST_AABB = Block.box(15.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	protected static final VoxelShape SOUTH_AABB = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 1.0D);
	protected static final VoxelShape NORTH_AABB = Block.box(0.0D, 0.0D, 15.0D, 16.0D, 16.0D, 16.0D);

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		switch (state.getValue(FACING)) {
			case NORTH:
				return NORTH_AABB;
			case SOUTH:
				return SOUTH_AABB;
			case WEST:
				return WEST_AABB;
			case EAST:
			default:
				return EAST_AABB;
		}
	}

	public PassionVineBlock(Properties properties) {
		super(properties);
	}

	@Override
	public boolean isLadder(BlockState state, LevelReader world, BlockPos pos, net.minecraft.world.entity.LivingEntity entity) {
		return true;
	}

	@Override
	public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource random) {
		super.tick(state, worldIn, pos, random);
		int i = state.getValue(AGE);

		boolean light = worldIn.getRawBrightness(pos.above(), 0) >= 5;
		boolean notOld = i < 4;
		boolean canFlower = i >= 1;
		boolean canFruit = notOld && light;
		boolean randomNum = worldIn.random.nextInt(2) == 1;

		if (canFlower) {
			if (canFruit) {
				if (randomNum) {
					attemptGrowDown(state, worldIn, pos, random);
				} else {
					attemptGrowFruit(state, worldIn, pos, random);
				}
			} else {
				attemptGrowDown(state, worldIn, pos, random);
			}
		} else {
			if (canFruit) {
				attemptGrowFruit(state, worldIn, pos, random);
			}
		}
	}
	
	/*
	public BlockState determineState(BlockState state, World world, BlockPos pos) {
		BlockState below = world.getBlockState(pos.down());
		BlockState above = world.getBlockState(pos.up());
		boolean vineAbove = (above.getBlock() == this && above.get(FACING) == state.get(FACING));
		boolean vineBelow = (below.getBlock() == this && below.get(FACING) == state.get(FACING));		
		
		if (vineAbove) {
			BlockState attachmentType = vineBelow ? state.with(ATTACHMENT, PassionVineAttachment.MIDDLE) : state.with(ATTACHMENT, PassionVineAttachment.BOTTOM);
			return attachmentType;
		} else {
			BlockState attachmentType = vineBelow ? state.with(ATTACHMENT, PassionVineAttachment.TOP) : state.with(ATTACHMENT, PassionVineAttachment.NONE);
			return attachmentType;	    
		}
		
	}
	*/

	public void attemptGrowFruit(BlockState state, Level worldIn, BlockPos pos, RandomSource random) {
		int i = state.getValue(AGE);
		Direction direction = state.getValue(FACING);
		BlockState hanging = worldIn.getBlockState(pos.relative(direction.getOpposite()));
		if (hanging.is(AtmosphericBlockTags.PASSION_VINE_GROWABLE_ON)) {
			if (i < 4 && ForgeHooks.onCropsGrowPre(worldIn, pos, state, random.nextInt(7) == 0)) {
				worldIn.setBlock(pos, state.setValue(AGE, i + 1), 2);
				ForgeHooks.onCropsGrowPost(worldIn, pos, state);
			}
		} else {
			if (i < 1 && ForgeHooks.onCropsGrowPre(worldIn, pos, state, random.nextInt(7) == 0)) {
				worldIn.setBlock(pos, state.setValue(AGE, i + 1), 2);
				ForgeHooks.onCropsGrowPost(worldIn, pos, state);
			}
		}
	}

	public void attemptGrowDown(BlockState state, Level worldIn, BlockPos pos, RandomSource random) {
		if (ForgeHooks.onCropsGrowPre(worldIn, pos, state, random.nextInt(7) == 0)) {
			BlockState below = worldIn.getBlockState(pos.below());
			if (below.getBlock() == Blocks.AIR) {
				worldIn.setBlockAndUpdate(pos.below(), AtmosphericBlocks.PASSION_VINE.get().defaultBlockState().setValue(AGE, Integer.valueOf(0)).setValue(FACING, state.getValue(FACING)));
			}
		}
	}

	@Override
	public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
		int i = state.getValue(AGE);
		boolean flag = i == 4;
		if (!flag && player.getItemInHand(handIn).getItem() == Items.BONE_MEAL) {
			return InteractionResult.PASS;
		} else if (i == 4) {
			popResource(worldIn, pos, new ItemStack(AtmosphericItems.PASSION_FRUIT.get(), 1 + worldIn.random.nextInt(2) + worldIn.random.nextInt(2) + worldIn.random.nextInt(3)));
			worldIn.playSound(null, pos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, 0.8F + worldIn.random.nextFloat() * 0.4F);
			worldIn.setBlock(pos, state.setValue(AGE, Integer.valueOf(1)), 2);
			return InteractionResult.SUCCESS;
		} else if (i == 1 && (player.getItemInHand(handIn).getItem() == Items.SHEARS)) {
			Direction direction = hit.getDirection();
			Direction direction1 = direction.getAxis() == Direction.Axis.Y ? player.getDirection().getOpposite() : direction;
			ItemEntity itementity = new ItemEntity(worldIn, (double) pos.getX() + 0.5D + (double) direction1.getStepX() * 0.65D, (double) pos.getY() + 0.1D, (double) pos.getZ() + 0.5D + (double) direction1.getStepZ() * 0.65D, new ItemStack(Items.WHITE_DYE, 1));
			itementity.setDeltaMovement(0.05D * (double) direction1.getStepX() + worldIn.random.nextDouble() * 0.02D, 0.05D, 0.05D * (double) direction1.getStepZ() + worldIn.random.nextDouble() * 0.02D);
			worldIn.addFreshEntity(itementity);
			player.getItemInHand(handIn).hurtAndBreak(1, player, (p_213442_1_) -> {
				p_213442_1_.broadcastBreakEvent(handIn);
			});
			worldIn.playSound(null, pos, SoundEvents.SHEEP_SHEAR, SoundSource.BLOCKS, 1.0F, 0.8F + worldIn.random.nextFloat() * 0.4F);
			worldIn.setBlock(pos, state.setValue(AGE, Integer.valueOf(0)), 2);
			return InteractionResult.SUCCESS;
		} else {
			return super.use(state, worldIn, pos, player, handIn, hit);
		}
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(AGE, FACING);
	}

	private boolean canAttachTo(BlockGetter p_196471_1_, BlockPos p_196471_2_, Direction p_196471_3_) {
		BlockState blockstate = p_196471_1_.getBlockState(p_196471_2_);
		return !blockstate.isSignalSource() && (blockstate.isFaceSturdy(p_196471_1_, p_196471_2_, p_196471_3_) || blockstate.is(BlockTags.LEAVES));
	}

	@Override
	public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos) {
		Direction direction = state.getValue(FACING);
		return this.canAttachTo(worldIn, pos.relative(direction.getOpposite()), direction) || (worldIn.getBlockState(pos.above()).getBlock() == AtmosphericBlocks.PASSION_VINE.get() && worldIn.getBlockState(pos.above()).getValue(FACING) == state.getValue(FACING));
	}

	@Override
	public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn, BlockPos currentPos, BlockPos facingPos) {
		if (!stateIn.canSurvive(worldIn, currentPos)) {
			return Blocks.AIR.defaultBlockState();
		} else {
			return super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos);
		}
	}

	@Override
	@Nullable
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		if (!context.replacingClickedOnBlock()) {
			BlockState blockstate = context.getLevel().getBlockState(context.getClickedPos().relative(context.getClickedFace().getOpposite()));
			if (blockstate.getBlock() == this && blockstate.getValue(FACING) == context.getClickedFace()) {
				return null;
			}
		}

		BlockState blockstate1 = this.defaultBlockState();
		LevelReader iworldreader = context.getLevel();
		BlockPos blockpos = context.getClickedPos();
		for (Direction direction : context.getNearestLookingDirections()) {
			if (direction.getAxis().isHorizontal()) {
				blockstate1 = blockstate1.setValue(FACING, direction.getOpposite());
				if (blockstate1.canSurvive(iworldreader, blockpos)) {
					return blockstate1;
				}
			}
		}

		return null;
	}

	@Override
	public boolean isValidBonemealTarget(BlockGetter worldIn, BlockPos pos, BlockState state, boolean isClient) {
		return state.getValue(AGE) < 4;
	}

	@Override
	public boolean isBonemealSuccess(Level worldIn, RandomSource rand, BlockPos pos, BlockState state) {
		return true;
	}

	@Override
	public void performBonemeal(ServerLevel worldIn, RandomSource rand, BlockPos pos, BlockState state) {
		int i = Math.min(4, state.getValue(AGE) + 1);
		worldIn.setBlock(pos, state.setValue(AGE, Integer.valueOf(i)), 2);
	}
}
