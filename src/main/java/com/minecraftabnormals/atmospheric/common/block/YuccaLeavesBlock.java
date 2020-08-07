package com.minecraftabnormals.atmospheric.common.block;

import javax.annotation.Nullable;

import com.minecraftabnormals.atmospheric.core.other.AtmosphericCriteriaTriggers;
import com.minecraftabnormals.atmospheric.core.other.AtmosphericDamageSources;
import com.teamabnormals.abnormals_core.common.blocks.wood.AbnormalsLeavesBlock;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class YuccaLeavesBlock extends AbnormalsLeavesBlock {
    private static final VoxelShape HITBOX = makeCuboidShape(1.0F, 1.0F, 1.0F, 15.0F, 15.0F, 15.0F);

	public YuccaLeavesBlock(Properties properties) {
		super(properties);
	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
	      return HITBOX;
	}
	
	@Override
	public int getOpacity(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return 1;
	}
	
	@Deprecated
	@OnlyIn(Dist.CLIENT)
	public float getAmbientOcclusionLightValue(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return 0.2F;
	}
	
	@Nullable
    @Override
    public PathNodeType getAiPathNodeType(BlockState state, IBlockReader world, BlockPos pos, @Nullable MobEntity entity) {
        return  PathNodeType.DAMAGE_CACTUS;
    }
		
	public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
		if (entityIn instanceof LivingEntity && !(entityIn instanceof BeeEntity)) {
			if (!worldIn.isRemote && (entityIn.lastTickPosX != entityIn.getPosX() || entityIn.lastTickPosZ != entityIn.getPosZ())) {
				double d0 = Math.abs(entityIn.getPosX() - entityIn.lastTickPosX);
				double d1 = Math.abs(entityIn.getPosZ() - entityIn.lastTickPosZ);
	            if (d0 >= (double)0.003F || d1 >= (double)0.003F) {
	            	if (!entityIn.isCrouching()) {
		            	entityIn.addVelocity(MathHelper.sin((float) (entityIn.rotationYaw * Math.PI / 180.0F)) * 2F * 0.05F, 0.005F, -MathHelper.cos((float) (entityIn.rotationYaw * Math.PI / 180.0F)) * 2F * 0.25F);
	            	}
	            	entityIn.attackEntityFrom(AtmosphericDamageSources.YUCCA_LEAVES, 1.0F);
	            	if (entityIn instanceof ServerPlayerEntity) {
	            		ServerPlayerEntity serverplayerentity = (ServerPlayerEntity) entityIn;
	            		if(!entityIn.getEntityWorld().isRemote() && !serverplayerentity.isCreative()) {
	            			AtmosphericCriteriaTriggers.YUCCA_LEAVES_PRICK.trigger(serverplayerentity); 
	            		}
	            	}
	            }
			}
		}	
	}
}
