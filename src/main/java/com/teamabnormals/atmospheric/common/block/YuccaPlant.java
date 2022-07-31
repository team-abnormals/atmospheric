package com.teamabnormals.atmospheric.common.block;

import com.teamabnormals.blueprint.common.advancement.EmptyTrigger;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public interface YuccaPlant {
	float getKnockbackForce();

	DamageSource getDamageSource();

	EmptyTrigger getCriteriaTrigger();

	default void onYuccaCollision(BlockState state, Level worldIn, BlockPos pos, Entity entityIn) {
		if (entityIn instanceof LivingEntity livingEntity && !(entityIn instanceof Bee)) {
			if (!worldIn.isClientSide && (entityIn.xOld != entityIn.getX() || entityIn.zOld != entityIn.getZ())) {
				double d0 = Math.abs(entityIn.getX() - entityIn.xOld);
				double d1 = Math.abs(entityIn.getZ() - entityIn.zOld);
				if (d0 >= (double) 0.003F || d1 >= (double) 0.003F) {
					if (!entityIn.isCrouching()) {
						livingEntity.knockback(this.getKnockbackForce(), -Mth.sin((float) (entityIn.getYRot() * Math.PI / 180.0F)), Mth.cos((float) (entityIn.getYRot() * Math.PI / 180.0F)));
					}
					entityIn.hurt(this.getDamageSource(), 1.0F);
					if (entityIn instanceof ServerPlayer serverplayerentity) {
						if (!entityIn.getCommandSenderWorld().isClientSide() && !serverplayerentity.isCreative() && this.getCriteriaTrigger() != null) {
							this.getCriteriaTrigger().trigger(serverplayerentity);
						}
					}
				}
			}
		}
	}
}
