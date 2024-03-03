package com.teamabnormals.atmospheric.common.block;

import com.teamabnormals.atmospheric.core.other.AtmosphericCriteriaTriggers;
import com.teamabnormals.atmospheric.core.other.tags.AtmosphericEntityTypeTags;
import com.teamabnormals.atmospheric.core.registry.AtmosphericItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public interface YuccaPlant {
	float getKnockbackForce();

	DamageSource getDamageSource();

	default void onYuccaCollision(BlockState state, Level level, BlockPos pos, Entity entity) {
		if (entity instanceof LivingEntity living && !entity.getType().is(AtmosphericEntityTypeTags.YUCCA_IMMUNE)) {
			if (!level.isClientSide && (entity.xOld != entity.getX() || entity.zOld != entity.getZ())) {
				double d0 = Math.abs(entity.getX() - entity.xOld);
				double d1 = Math.abs(entity.getZ() - entity.zOld);
				if (d0 >= (double) 0.003F || d1 >= (double) 0.003F) {
					if (!entity.isCrouching()) {
						living.knockback(this.getKnockbackForce(), -Mth.sin((float) (entity.getYRot() * Math.PI / 180.0F)), Mth.cos((float) (entity.getYRot() * Math.PI / 180.0F)));
					}

					if (living.getItemBySlot(EquipmentSlot.HEAD).is(AtmosphericItems.BARREL_CACTUS.get())) {
						entity.hurt(this.getDamageSource().bypassArmor(), 0.01F);
					} else {
						entity.hurt(this.getDamageSource(), 1.0F);
					}

					if (entity instanceof ServerPlayer serverPlayer) {
						if (!entity.getCommandSenderWorld().isClientSide() && !serverPlayer.isCreative()) {
							AtmosphericCriteriaTriggers.YUCCA_PRICK.trigger(serverPlayer);
						}
					}
				}
			}
		}
	}
}
