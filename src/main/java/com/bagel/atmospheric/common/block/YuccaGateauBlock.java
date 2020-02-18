package com.bagel.atmospheric.common.block;

import com.bagel.atmospheric.core.util.StateUtils;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.stats.Stats;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class YuccaGateauBlock extends Block {
	   public static final IntegerProperty BITES = StateUtils.BITES_0_9;
	   protected static final VoxelShape[] SHAPES = new VoxelShape[]{
			   Block.makeCuboidShape(3.0D, 0.0D, 3.0D, 13.0D, 6.0D, 13.0D), 
			   Block.makeCuboidShape(4.0D, 0.0D, 3.0D, 13.0D, 6.0D, 13.0D), 
			   Block.makeCuboidShape(5.0D, 0.0D, 3.0D, 13.0D, 6.0D, 13.0D), 
			   Block.makeCuboidShape(6.0D, 0.0D, 3.0D, 13.0D, 6.0D, 13.0D), 
			   Block.makeCuboidShape(7.0D, 0.0D, 3.0D, 13.0D, 6.0D, 13.0D), 
			   Block.makeCuboidShape(8.0D, 0.0D, 3.0D, 13.0D, 6.0D, 13.0D), 
			   Block.makeCuboidShape(9.0D, 0.0D, 3.0D, 13.0D, 6.0D, 13.0D), 
			   Block.makeCuboidShape(10.0D, 0.0D, 3.0D, 13.0D, 6.0D, 13.0D), 
			   Block.makeCuboidShape(11.0D, 0.0D, 3.0D, 13.0D, 6.0D, 13.0D), 
			   Block.makeCuboidShape(12.0D, 0.0D, 3.0D, 13.0D, 6.0D, 13.0D)};

	   public YuccaGateauBlock(Block.Properties properties) {
	      super(properties);
	      this.setDefaultState(this.stateContainer.getBaseState().with(BITES, Integer.valueOf(0)));
	   }

	   public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
	      return SHAPES[state.get(BITES)];
	   }

	   public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
	      if (!worldIn.isRemote) {
	         return this.eatCake(worldIn, pos, state, player);
	      } else {
	         ItemStack itemstack = player.getHeldItem(handIn);
	         return this.eatCake(worldIn, pos, state, player) || itemstack.isEmpty();
	      }
	   }

	   private boolean eatCake(IWorld worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
	      if (!player.canEat(false)) {
	         return false;
	      } else {
	         player.addStat(Stats.EAT_CAKE_SLICE);
	         player.getFoodStats().addStats(1, 0.0F);
	         int i = state.get(BITES);
	         if (i < 9) {
	            worldIn.setBlockState(pos, state.with(BITES, Integer.valueOf(i + 1)), 3);
	         } else {
	            worldIn.removeBlock(pos, false);
	         }

	         return true;
	      }
	   }

	   @SuppressWarnings("deprecation")
	   public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
	      return facing == Direction.DOWN && !stateIn.isValidPosition(worldIn, currentPos) ? Blocks.AIR.getDefaultState() : super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
	   }

	   public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
	      return worldIn.getBlockState(pos.down()).getMaterial().isSolid();
	   }

	   protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
	      builder.add(BITES);
	   }

	   public int getComparatorInputOverride(BlockState blockState, World worldIn, BlockPos pos) {
	      return (10 - blockState.get(BITES)) * 2;
	   }

	   public boolean hasComparatorInputOverride(BlockState state) {
	      return true;
	   }

	   public boolean allowsMovement(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type) {
	      return false;
	   }
	}
