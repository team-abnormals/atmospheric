package com.teamabnormals.atmospheric.common.block;

import com.teamabnormals.atmospheric.core.registry.AtmosphericItems;
import com.teamabnormals.blueprint.common.advancement.EmptyTrigger;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public interface YuccaPlant {
	float getKnockbackForce();

	DamageSource getDamageSource();

	EmptyTrigger getCriteriaTrigger();

	default void onYuccaCollision(BlockState state, Level level, BlockPos pos, Entity entity) {
		if (entity instanceof LivingEntity livingEntity && !(entity instanceof Bee)) {
			if (!level.isClientSide && (entity.xOld != entity.getX() || entity.zOld != entity.getZ())) {
				double d0 = Math.abs(entity.getX() - entity.xOld);
				double d1 = Math.abs(entity.getZ() - entity.zOld);
				if (d0 >= (double) 0.003F || d1 >= (double) 0.003F) {
					if (!entity.isCrouching()) {
						livingEntity.knockback(this.getKnockbackForce(), -Mth.sin((float) (entity.getYRot() * Math.PI / 180.0F)), Mth.cos((float) (entity.getYRot() * Math.PI / 180.0F)));
					}

					entity.hurt(this.getDamageSource(), !livingEntity.getItemBySlot(EquipmentSlot.HEAD).is(AtmosphericItems.BARREL_CACTUS.get()) ? 1.0F : 0.0F);
					if (entity instanceof ServerPlayer serverPlayer) {
						if (!entity.getCommandSenderWorld().isClientSide() && !serverPlayer.isCreative() && this.getCriteriaTrigger() != null) {
							this.getCriteriaTrigger().trigger(serverPlayer);
						}
					}
				}
			}
		}
	}
}
