package com.minecraftabnormals.atmospheric.common.block.api;

import com.minecraftabnormals.abnormals_core.common.advancement.EmptyTrigger;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public interface IYuccaPlant {
	float getKnockbackForce();

	DamageSource getDamageSource();

	EmptyTrigger getCriteriaTrigger();

	default void onYuccaCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
		if (entityIn instanceof LivingEntity && !(entityIn instanceof BeeEntity)) {
			LivingEntity livingEntity = (LivingEntity) entityIn;
			if (!worldIn.isClientSide && (entityIn.xOld != entityIn.getX() || entityIn.zOld != entityIn.getZ())) {
				double d0 = Math.abs(entityIn.getX() - entityIn.xOld);
				double d1 = Math.abs(entityIn.getZ() - entityIn.zOld);
				if (d0 >= (double) 0.003F || d1 >= (double) 0.003F) {
					if (!entityIn.isCrouching()) {
						livingEntity.knockback(this.getKnockbackForce(), -MathHelper.sin((float) (entityIn.yRot * Math.PI / 180.0F)), MathHelper.cos((float) (entityIn.yRot * Math.PI / 180.0F)));
					}
					entityIn.hurt(this.getDamageSource(), 1.0F);
					if (entityIn instanceof ServerPlayerEntity) {
						ServerPlayerEntity serverplayerentity = (ServerPlayerEntity) entityIn;
						if (!entityIn.getCommandSenderWorld().isClientSide() && !serverplayerentity.isCreative() && this.getCriteriaTrigger() != null) {
							this.getCriteriaTrigger().trigger(serverplayerentity);
						}
					}
				}
			}
		}
	}
}
