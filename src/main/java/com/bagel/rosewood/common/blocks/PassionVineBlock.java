package com.bagel.rosewood.common.blocks;

import java.util.Random;

import javax.annotation.Nullable;

import com.bagel.rosewood.core.registry.RosewoodBlocks;
import com.bagel.rosewood.core.registry.RosewoodItems;
import com.bagel.rosewood.core.util.StateUtils;

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
import net.minecraft.state.EnumProperty;
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
	public static final IntegerProperty AGE = StateUtils.AGE_0_4; 
	public static final EnumProperty<PassionVineAttachment> ATTACHMENT = StateUtils.PASSION_VINE_ATTACHMENT; 
	   
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
	
	@Override public boolean isLadder(BlockState state, IWorldReader world, BlockPos pos, net.minecraft.entity.LivingEntity entity) { return true; }
	/*public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
		for(LivingEntity entities : worldIn.getEntitiesWithinAABB(LivingEntity.class, state.getShape(worldIn, pos).getBoundingBox())) {
			entities.setMotionMultiplier(state, new Vec3d(1.0D, (double)0.05F, 1.0D));
		}
	}*/
	
	@SuppressWarnings("deprecation")
	public void tick(BlockState state, World worldIn, BlockPos pos, Random random) {
	      super.tick(state, worldIn, pos, random);
	      determineState(state, worldIn, pos);
	      int i = state.get(AGE);
	      if (i >= 1) {
	    	  if (worldIn.rand.nextInt(2) == 1 ) { 
	    		  attemptGrowFruit(state, worldIn, pos, random);
	    	  } else { 
	    		  attemptGrowDown(state, worldIn, pos, random); }
	      } else {
	    	  attemptGrowFruit(state, worldIn, pos, random);
	      }
	   }
	
	public void determineState(BlockState state, World world, BlockPos pos) {
		BlockState below = world.getBlockState(pos.down());
		BlockState above = world.getBlockState(pos.up());
		
		Boolean vineAbove = (above.getBlock() == state.getBlock());
		Boolean vineBelow = (below.getBlock() == state.getBlock());		
		
		if (vineAbove) {
			Boolean vineFacingAbove = above.getBlockState().get(FACING) == state.get(FACING);
			if (vineFacingAbove) {
				if (vineBelow) { world.setBlockState(pos, state.with(ATTACHMENT, PassionVineAttachment.MIDDLE)); } 
				else { world.setBlockState(pos, state.with(ATTACHMENT, PassionVineAttachment.BOTTOM)); }
			}
		} else if (vineBelow) {
			Boolean vineFacingBelow = below.getBlockState().get(FACING) == state.get(FACING);
			if (vineFacingBelow) {
				 { world.setBlockState(pos, state.with(ATTACHMENT, PassionVineAttachment.TOP)); } 
			}
		} else { world.setBlockState(pos, state.with(ATTACHMENT, PassionVineAttachment.NONE)); }
	}
	
	public void attemptGrowFruit(BlockState state, World worldIn, BlockPos pos, Random random) {
		int i = state.get(AGE);
		Direction direction = state.get(FACING);
		BlockState hanging = worldIn.getBlockState(pos.offset(direction.getOpposite()));
		if (hanging.getBlock().isIn(BlockTags.LOGS) || hanging.getBlock().isIn(BlockTags.LEAVES)) {
			  if (i < 4 && worldIn.getLightSubtracted(pos.up(), 0) >= 5 && net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state, random.nextInt(7) == 0)) {
				  worldIn.setBlockState(pos, state.with(AGE, Integer.valueOf(i + 1)), 2);
				  net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state);
			  }
		  } else {
			  if (i < 1 && worldIn.getLightSubtracted(pos.up(), 0) >= 5 && net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state, random.nextInt(7) == 0)) {
				  worldIn.setBlockState(pos, state.with(AGE, Integer.valueOf(i + 1)), 2);
				  net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state);
			  }
		  }
	}
	
	public void attemptGrowDown(BlockState state, World worldIn, BlockPos pos, Random random) {
		if (net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state, random.nextInt(7) == 0)) {
			  BlockState below = worldIn.getBlockState(pos.down());
			  if (below.getBlock() == Blocks.AIR) {
				  worldIn.setBlockState(pos.down(), RosewoodBlocks.PASSION_VINE.get().getDefaultState().with(AGE, Integer.valueOf(1)).with(FACING, state.get(FACING)));
			  }
		  }
	}
	
	@SuppressWarnings("deprecation")
	public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
	    determineState(state, worldIn, pos);
		int i = state.get(AGE);
		boolean flag = i == 4;
		if (!flag && player.getHeldItem(handIn).getItem() == Items.BONE_MEAL) {
			return false;	
		} else if (i == 4) {
			spawnAsEntity(worldIn, pos, new ItemStack(RosewoodItems.PASSIONFRUIT.get(), 1 + worldIn.rand.nextInt(2) + worldIn.rand.nextInt(2) + worldIn.rand.nextInt(3)));
			worldIn.playSound((PlayerEntity)null, pos, SoundEvents.ITEM_SWEET_BERRIES_PICK_FROM_BUSH, SoundCategory.BLOCKS, 1.0F, 0.8F + worldIn.rand.nextFloat() * 0.4F);
			worldIn.setBlockState(pos, state.with(AGE, Integer.valueOf(1)), 2);
	    	return true;	
		} else if (i == 1 && (player.getHeldItem(handIn).getItem() == Items.SHEARS)) {
			player.getHeldItem(handIn).damageItem(1, player, (p_213442_1_) -> {
	  	    p_213442_1_.sendBreakAnimation(handIn);
	  	    });
	  	    worldIn.playSound((PlayerEntity)null, pos, SoundEvents.ENTITY_SHEEP_SHEAR, SoundCategory.BLOCKS, 1.0F, 0.8F + worldIn.rand.nextFloat() * 0.4F);
	  	    worldIn.setBlockState(pos, state.with(AGE, Integer.valueOf(0)), 2);
	  	    return true;    
		} else {
			return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);	
		}	
	}
	
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(ATTACHMENT, AGE, FACING);
	}
	
	public static boolean canAttachTo(IBlockReader block, BlockPos pos, Direction direction) {
	      BlockState blockstate = block.getBlockState(pos);
	      return !blockstate.canProvidePower() && blockstate.func_224755_d(block, pos, direction);
	   }

	   public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
	      Direction direction = state.get(FACING);
	      return (PassionVineBlock.canAttachTo(worldIn, pos.offset(direction.getOpposite()), direction) 
	    		  || (worldIn.getBlockState(pos.offset(Direction.UP)).getBlock() == RosewoodBlocks.PASSION_VINE.get() 
	    		  && worldIn.getBlockState(pos.offset(Direction.UP)).get(FACING) == state.get(FACING)));
	   }
	   
	   @SuppressWarnings("deprecation")
	public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
		   determineState(stateIn, worldIn.getWorld(), currentPos);
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
		//Direction direction = state.get(FACING);
		//BlockState hanging = worldIn.getBlockState(pos.offset(direction.getOpposite()));
		return state.get(AGE) < 4;

	   }

	public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state) {
		return true;
	}

	public void grow(World worldIn, Random rand, BlockPos pos, BlockState state) {
		determineState(state, worldIn, pos);
	    int i = Math.min(4, state.get(AGE) + 1);
	    worldIn.setBlockState(pos, state.with(AGE, Integer.valueOf(i)), 2);
	}
}

/*
if the vine is on a log or leaves
-> grow to stage 5

if the vine is not
-> grow of stage 2

if the 
*/
