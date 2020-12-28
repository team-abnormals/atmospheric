package com.minecraftabnormals.atmospheric.common.block;

import com.minecraftabnormals.atmospheric.core.other.AtmosphericTags;
import com.minecraftabnormals.atmospheric.core.registry.AtmosphericBlocks;
import com.minecraftabnormals.atmospheric.core.registry.AtmosphericItems;
import net.minecraft.block.*;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.Random;

public class PassionVineBlock extends Block implements IGrowable {
	public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
	public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 4);
	//public static final EnumProperty<PassionVineAttachment> ATTACHMENT = EnumProperty.create("attachment", PassionVineAttachment.class);

	protected static final VoxelShape EAST_AABB = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 1.0D, 16.0D, 16.0D);
	protected static final VoxelShape WEST_AABB = Block.makeCuboidShape(15.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	protected static final VoxelShape SOUTH_AABB = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 1.0D);
	protected static final VoxelShape NORTH_AABB = Block.makeCuboidShape(0.0D, 0.0D, 15.0D, 16.0D, 16.0D, 16.0D);

	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		switch ((Direction) state.get(FACING)) {
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
	public boolean isLadder(BlockState state, IWorldReader world, BlockPos pos, net.minecraft.entity.LivingEntity entity) {
		return true;
	}

	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
		super.tick(state, worldIn, pos, random);
		int i = state.get(AGE);

		boolean light = worldIn.getLightSubtracted(pos.up(), 0) >= 5;
		boolean notOld = i < 4;
		boolean canFlower = i >= 1;
		boolean canFruit = notOld && light;
		boolean randomNum = worldIn.rand.nextInt(2) == 1;

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

	public void attemptGrowFruit(BlockState state, World worldIn, BlockPos pos, Random random) {
		int i = state.get(AGE);
		Direction direction = state.get(FACING);
		BlockState hanging = worldIn.getBlockState(pos.offset(direction.getOpposite()));
		if (hanging.getBlock().isIn(AtmosphericTags.PASSION_VINE_GROWABLE_ON)) {
			if (i < 4 && net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state, random.nextInt(7) == 0)) {
				worldIn.setBlockState(pos, state.with(AGE, Integer.valueOf(i + 1)), 2);
				net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state);
			}
		} else {
			if (i < 1 && net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state, random.nextInt(7) == 0)) {
				worldIn.setBlockState(pos, state.with(AGE, Integer.valueOf(i + 1)), 2);
				net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state);
			}
		}
	}

	public void attemptGrowDown(BlockState state, World worldIn, BlockPos pos, Random random) {
		if (net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state, random.nextInt(7) == 0)) {
			BlockState below = worldIn.getBlockState(pos.down());
			if (below.getBlock() == Blocks.AIR) {
				worldIn.setBlockState(pos.down(), AtmosphericBlocks.PASSION_VINE.get().getDefaultState().with(AGE, Integer.valueOf(0)).with(FACING, state.get(FACING)));
			}
		}
	}

	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
		int i = state.get(AGE);
		boolean flag = i == 4;
		if (!flag && player.getHeldItem(handIn).getItem() == Items.BONE_MEAL) {
			return ActionResultType.PASS;
		} else if (i == 4) {
			spawnAsEntity(worldIn, pos, new ItemStack(AtmosphericItems.PASSIONFRUIT.get(), 1 + worldIn.rand.nextInt(2) + worldIn.rand.nextInt(2) + worldIn.rand.nextInt(3)));
			worldIn.playSound((PlayerEntity) null, pos, SoundEvents.ITEM_SWEET_BERRIES_PICK_FROM_BUSH, SoundCategory.BLOCKS, 1.0F, 0.8F + worldIn.rand.nextFloat() * 0.4F);
			worldIn.setBlockState(pos, state.with(AGE, Integer.valueOf(1)), 2);
			return ActionResultType.SUCCESS;
		} else if (i == 1 && (player.getHeldItem(handIn).getItem() == Items.SHEARS)) {
			Direction direction = hit.getFace();
			Direction direction1 = direction.getAxis() == Direction.Axis.Y ? player.getHorizontalFacing().getOpposite() : direction;
			ItemEntity itementity = new ItemEntity(worldIn, (double) pos.getX() + 0.5D + (double) direction1.getXOffset() * 0.65D, (double) pos.getY() + 0.1D, (double) pos.getZ() + 0.5D + (double) direction1.getZOffset() * 0.65D, new ItemStack(Items.WHITE_DYE, 1));
			itementity.setMotion(0.05D * (double) direction1.getXOffset() + worldIn.rand.nextDouble() * 0.02D, 0.05D, 0.05D * (double) direction1.getZOffset() + worldIn.rand.nextDouble() * 0.02D);
			worldIn.addEntity(itementity);
			player.getHeldItem(handIn).damageItem(1, player, (p_213442_1_) -> {
				p_213442_1_.sendBreakAnimation(handIn);
			});
			worldIn.playSound((PlayerEntity) null, pos, SoundEvents.ENTITY_SHEEP_SHEAR, SoundCategory.BLOCKS, 1.0F, 0.8F + worldIn.rand.nextFloat() * 0.4F);
			worldIn.setBlockState(pos, state.with(AGE, Integer.valueOf(0)), 2);
			return ActionResultType.SUCCESS;
		} else {
			return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
		}
	}

	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(AGE, FACING);
	}

	private boolean canAttachTo(IBlockReader p_196471_1_, BlockPos p_196471_2_, Direction p_196471_3_) {
		BlockState blockstate = p_196471_1_.getBlockState(p_196471_2_);
		return !blockstate.canProvidePower() && (blockstate.isSolidSide(p_196471_1_, p_196471_2_, p_196471_3_) || blockstate.isIn(BlockTags.LEAVES));
	}

	@Override
	public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
		Direction direction = state.get(FACING);
		if (this.canAttachTo(worldIn, pos.offset(direction.getOpposite()), direction)
				|| (worldIn.getBlockState(pos.up()).getBlock() == AtmosphericBlocks.PASSION_VINE.get()
				&& worldIn.getBlockState(pos.up()).get(FACING) == state.get(FACING))) {
			return true;
		} else {
			return false;
		}
	}

	public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
		if (!stateIn.isValidPosition(worldIn, currentPos)) {
			return Blocks.AIR.getDefaultState();
		} else {
			return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
		}
	}

	@Nullable
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		if (!context.replacingClickedOnBlock()) {
			BlockState blockstate = context.getWorld().getBlockState(context.getPos().offset(context.getFace().getOpposite()));
			if (blockstate.getBlock() == this && blockstate.get(FACING) == context.getFace()) {
				return null;
			}
		}

		BlockState blockstate1 = this.getDefaultState();
		IWorldReader iworldreader = context.getWorld();
		BlockPos blockpos = context.getPos();
		for (Direction direction : context.getNearestLookingDirections()) {
			if (direction.getAxis().isHorizontal()) {
				blockstate1 = blockstate1.with(FACING, direction.getOpposite());
				if (blockstate1.isValidPosition(iworldreader, blockpos)) {
					return blockstate1;
				}
			}
		}

		return null;
	}

	public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
		return state.get(AGE) < 4;
	}

	public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state) {
		return true;
	}

	public void grow(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state) {
		int i = Math.min(4, state.get(AGE) + 1);
		worldIn.setBlockState(pos, state.with(AGE, Integer.valueOf(i)), 2);
	}
}
