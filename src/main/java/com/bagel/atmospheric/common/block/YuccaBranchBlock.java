package com.bagel.atmospheric.common.block;

import java.util.Random;

import com.bagel.atmospheric.core.other.AtmosphericCriteriaTriggers;
import com.bagel.atmospheric.core.other.AtmosphericDamageSources;
import com.bagel.atmospheric.core.other.AtmosphericTags;
import com.bagel.atmospheric.core.registry.AtmosphericBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.BushBlock;
import net.minecraft.block.IGrowable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.Items;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class YuccaBranchBlock extends BushBlock implements IGrowable {
	protected static final VoxelShape SHAPE = Block.makeCuboidShape(5.0D, 0.0D, 5.0D, 11.0D, 16.0D, 11.0D);
	protected static final VoxelShape SHAPE_SNAPPED = Block.makeCuboidShape(5.0D, 6.0D, 5.0D, 11.0D, 16.0D, 11.0D);
	public static final BooleanProperty SNAPPED = BooleanProperty.create("snapped");

	public YuccaBranchBlock(Properties properties) {
		super(properties);
		this.setDefaultState(this.getDefaultState().with(SNAPPED, true));
	}

	@Override
	protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return state.getBlock().isIn(AtmosphericTags.YUCCA_LOGS);
	}
	
	public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
		return this.isValidGround(worldIn.getBlockState(pos.up()), worldIn, pos.up());
	}
	
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return state.get(SNAPPED) ? SHAPE_SNAPPED : SHAPE;
	}
	
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(SNAPPED);
	}
	
	public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
		worldIn.setBlockState(currentPos, stateIn.with(SNAPPED, !(worldIn.getBlockState(currentPos.down()).getBlock() instanceof YuccaBundleBlock)), 2);
		return !stateIn.isValidPosition(worldIn, currentPos) ? Blocks.AIR.getDefaultState() : super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
		return state.get(SNAPPED) && worldIn.getBlockState(pos.down()).isAir();
	}

	@Override
	public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state) {
		return state.get(SNAPPED);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void grow(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state) {
		 if (state.get(SNAPPED) && net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state, rand.nextInt(6) == 0) && worldIn.getBlockState(pos.down()).isAir()) {
			   worldIn.setBlockState(pos, state.with(SNAPPED, false));
			   worldIn.setBlockState(pos.down(), AtmosphericBlocks.YUCCA_BUNDLE.get().getDefaultState());
			   net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state);	
		 }		
	}
	
	@SuppressWarnings("deprecation")
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
		if (!state.isValidPosition(worldIn, pos)) {
			worldIn.destroyBlock(pos, true);
		} else {
			if(state.get(SNAPPED) && net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state, random.nextInt(5) == 0) && worldIn.getBlockState(pos.down()).isAir()) {
				worldIn.setBlockState(pos, state.with(SNAPPED, false));
				worldIn.setBlockState(pos.down(), AtmosphericBlocks.YUCCA_BUNDLE.get().getDefaultState());
			} 
			net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state);
		}
	}
	
	@SuppressWarnings("deprecation")
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
		if (!state.get(SNAPPED) && (player.getHeldItem(handIn).getItem() == Items.SHEARS)) {
			player.getHeldItem(handIn).damageItem(1, player, (p_213442_1_) -> {
	  	    p_213442_1_.sendBreakAnimation(handIn);
	  	    });
	  	    worldIn.playSound((PlayerEntity)null, pos, SoundEvents.ENTITY_SHEEP_SHEAR, SoundCategory.BLOCKS, 1.0F, 0.8F + worldIn.rand.nextFloat() * 0.4F);
	  	    worldIn.setBlockState(pos, state.with(SNAPPED, true), 2);
	  	    return ActionResultType.SUCCESS;    
		} else {
			return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);	
		}	
	}
	
	public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
		if (entityIn instanceof LivingEntity && !(entityIn instanceof BeeEntity)) {
			if (!worldIn.isRemote && (entityIn.lastTickPosX != entityIn.getPosX() || entityIn.lastTickPosZ != entityIn.getPosZ())) {
				double d0 = Math.abs(entityIn.getPosX() - entityIn.lastTickPosX);
				double d1 = Math.abs(entityIn.getPosZ() - entityIn.lastTickPosZ);
	            if (d0 >= (double)0.003F || d1 >= (double)0.003F) {
	            	if (!entityIn.isCrouching()) {
	            		entityIn.addVelocity(MathHelper.sin((float) (entityIn.rotationYaw * Math.PI / 180.0F)) * 2F * 0.125F, 0.075F, -MathHelper.cos((float) (entityIn.rotationYaw * Math.PI / 180.0F)) * 2F * 0.125F);
	            	}
	            	entityIn.attackEntityFrom(AtmosphericDamageSources.YUCCA_BRANCH, 1.0F);	
	            	if (entityIn instanceof ServerPlayerEntity) {
	            		ServerPlayerEntity serverplayerentity = (ServerPlayerEntity) entityIn;
	            		if(!entityIn.getEntityWorld().isRemote() && !serverplayerentity.isCreative()) {
	            			AtmosphericCriteriaTriggers.YUCCA_BRANCH_PRICK.trigger(serverplayerentity); 
	            		}
	            	}
	            }
			}
		} else if (entityIn instanceof ProjectileEntity && !state.get(SNAPPED)) {
			worldIn.setBlockState(pos, state.with(SNAPPED, true));
		}
	}
}
