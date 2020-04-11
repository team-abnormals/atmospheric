package com.bagel.atmospheric.common.block;

import com.bagel.atmospheric.core.data.AtmosphericDamageSources;

import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class YuccaLeavesBlock extends LeavesBlock {
    private static final VoxelShape HITBOX = makeCuboidShape(1.0F, 1.0F, 1.0F, 15.0F, 15.0F, 15.0F);

	public YuccaLeavesBlock(Properties properties) {
		super(properties);
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
	     return VoxelShapes.fullCube();
	}
	
	@Override
	public VoxelShape getRaytraceShape(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return VoxelShapes.fullCube();
	}
	
	@Override
	public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
	      return HITBOX;
	}

	public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
		if (entityIn instanceof LivingEntity) {
			if (!worldIn.isRemote && (entityIn.lastTickPosX != entityIn.getPosX() || entityIn.lastTickPosZ != entityIn.getPosZ())) {
				double d0 = Math.abs(entityIn.getPosX() - entityIn.lastTickPosX);
				double d1 = Math.abs(entityIn.getPosZ() - entityIn.lastTickPosZ);
	            if (d0 >= (double)0.003F || d1 >= (double)0.003F) {
	            	if (!entityIn.isCrouching()) {
		            	entityIn.addVelocity(MathHelper.sin((float) (entityIn.rotationYaw * Math.PI / 180.0F)) * 2F * 0.05F, 0.005F, -MathHelper.cos((float) (entityIn.rotationYaw * Math.PI / 180.0F)) * 2F * 0.25F);
	            	}
	            	entityIn.attackEntityFrom(AtmosphericDamageSources.YUCCA_BRANCH, 1.0F);	
	            }
			}
		}	
	}
}
