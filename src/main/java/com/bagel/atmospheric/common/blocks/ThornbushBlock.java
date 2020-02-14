package com.bagel.atmospheric.common.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.DeadBushBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

@SuppressWarnings("deprecation")
public class ThornbushBlock extends DeadBushBlock implements net.minecraftforge.common.IShearable {

   public ThornbushBlock(Properties properties) {
      super(properties);
   }
   
   public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
	      if (entityIn instanceof LivingEntity) {
	         entityIn.setMotionMultiplier(state, new Vec3d((double)0.8F, 0.75D, (double)0.8F));
	         if (!worldIn.isRemote && (entityIn.lastTickPosX != entityIn.posX || entityIn.lastTickPosZ != entityIn.posZ)) {
	            double d0 = Math.abs(entityIn.posX - entityIn.lastTickPosX);
	            double d1 = Math.abs(entityIn.posZ - entityIn.lastTickPosZ);
	            if (d0 >= (double)0.003F || d1 >= (double)0.003F) {
	               entityIn.attackEntityFrom(DamageSource.SWEET_BERRY_BUSH, 1.0F);
	            }
	         }

	      }
	   }
}
