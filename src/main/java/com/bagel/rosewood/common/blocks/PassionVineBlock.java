package com.bagel.rosewood.common.blocks;

import java.util.Random;

import javax.annotation.Nullable;

import com.bagel.rosewood.core.registry.RosewoodItems;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.IGrowable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class PassionVineBlock extends Block implements IGrowable {
	public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
	public static final IntegerProperty AGE = BlockStateUtils.AGE_1_5; 
	   
	protected static final VoxelShape EAST_AABB = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 1.0D, 16.0D, 16.0D);
	protected static final VoxelShape WEST_AABB = Block.makeCuboidShape(15.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	protected static final VoxelShape SOUTH_AABB = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 1.0D);
	protected static final VoxelShape NORTH_AABB = Block.makeCuboidShape(0.0D, 0.0D, 15.0D, 16.0D, 16.0D, 16.0D);
	   
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		switch((Direction)state.get(FACING)) {
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
	
	@SuppressWarnings("deprecation")
	public void tick(BlockState state, World worldIn, BlockPos pos, Random random) {
	      super.tick(state, worldIn, pos, random);
	      int i = state.get(AGE);
	      Direction direction = state.get(FACING);
	      BlockState hanging = worldIn.getBlockState(pos.offset(direction.getOpposite()));
	      if (hanging.getBlock().isIn(BlockTags.LOGS) || hanging.getBlock().isIn(BlockTags.LEAVES)) {
	    	  if (i < 5 && worldIn.getLightSubtracted(pos.up(), 0) >= 9 && net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state, random.nextInt(10) == 0)) {
	    		  worldIn.setBlockState(pos, state.with(AGE, Integer.valueOf(i + 1)), 2);
	    		  net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state);
	    	  }
	      } else {
	    	  if (i < 2 && worldIn.getLightSubtracted(pos.up(), 0) >= 9 && net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state, random.nextInt(10) == 0)) {
	    		  worldIn.setBlockState(pos, state.with(AGE, Integer.valueOf(i + 1)), 2);
	    		  net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state);
	    	  }
	      }

	   }
	
	@SuppressWarnings("deprecation")
	public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
	      int i = state.get(AGE);
	      boolean flag = i == 5;
	      if (!flag && player.getHeldItem(handIn).getItem() == Items.BONE_MEAL) {
	         return false;
	      } else if (i == 5) {
	         int j = 1 + worldIn.rand.nextInt(4);
	         spawnAsEntity(worldIn, pos, new ItemStack(RosewoodItems.PASSIONFRUIT.get(), j));
	         worldIn.playSound((PlayerEntity)null, pos, SoundEvents.ITEM_SWEET_BERRIES_PICK_FROM_BUSH, SoundCategory.BLOCKS, 1.0F, 0.8F + worldIn.rand.nextFloat() * 0.4F);
	         worldIn.setBlockState(pos, state.with(AGE, Integer.valueOf(2)), 2);
	         return true;
	      } else {
	         return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
	      }
	   }
	
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(FACING, AGE);
	}
	
	private boolean canAttachTo(IBlockReader block, BlockPos pos, Direction direction) {
	      BlockState blockstate = block.getBlockState(pos);
	      return !blockstate.canProvidePower() && blockstate.func_224755_d(block, pos, direction);
	   }

	   public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
	      Direction direction = state.get(FACING);
	      return this.canAttachTo(worldIn, pos.offset(direction.getOpposite()), direction);
	   }
	   
	   @SuppressWarnings("deprecation")
	public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
		      if (facing.getOpposite() == stateIn.get(FACING) && !stateIn.isValidPosition(worldIn, currentPos)) {
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

	      for(Direction direction : context.getNearestLookingDirections()) {
	         if (direction.getAxis().isHorizontal()) {
	            blockstate1 = blockstate1.with(FACING, direction.getOpposite());
	            if (blockstate1.isValidPosition(iworldreader, blockpos)) {
	               return blockstate1;
	            }
	         }
	      }

	      return null;
	   }
	
	@Override
    public BlockRenderLayer getRenderLayer() {
	    return BlockRenderLayer.CUTOUT;
	}
	
	public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
		Direction direction = state.get(FACING);
		BlockState hanging = worldIn.getBlockState(pos.offset(direction.getOpposite()));
	    if (hanging.getBlock().isIn(BlockTags.LOGS) || hanging.getBlock().isIn(BlockTags.LEAVES)) {
	    	return state.get(AGE) < 5;
	    }
	      return state.get(AGE) < 2;
	   }

	public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state) {
		return true;
	}

	public void grow(World worldIn, Random rand, BlockPos pos, BlockState state) {
	    int i = Math.min(5, state.get(AGE) + 1);
	    worldIn.setBlockState(pos, state.with(AGE, Integer.valueOf(i)), 2);
	}
}
