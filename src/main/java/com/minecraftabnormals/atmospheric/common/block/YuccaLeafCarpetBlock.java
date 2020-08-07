package com.minecraftabnormals.atmospheric.common.block;

import javax.annotation.Nullable;

import com.minecraftabnormals.atmospheric.core.other.AtmosphericDamageSources;
import com.teamabnormals.abnormals_core.common.blocks.LeafCarpetBlock;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class YuccaLeafCarpetBlock extends LeafCarpetBlock {

	public YuccaLeafCarpetBlock(Block.Properties properties) {
	    super(properties);
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
	            	entityIn.attackEntityFrom(AtmosphericDamageSources.YUCCA_BRANCH, 1.0F);	
	            }
			}
		}	
	}
}
