package com.bagel.rosewood.common.blocks;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;

public class PassionVineBlock extends Block {
	public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
	   
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
	
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(FACING);
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
	
}
