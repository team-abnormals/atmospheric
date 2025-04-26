package com.teamabnormals.atmospheric.common.block;

import com.teamabnormals.atmospheric.core.registry.AtmosphericCriteriaTriggers;
import com.teamabnormals.atmospheric.core.other.tags.AtmosphericEntityTypeTags;
import com.teamabnormals.atmospheric.core.registry.AtmosphericItems;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public interface YuccaPlant {
	float getKnockbackForce();

	ResourceKey<DamageType> getDamageTypeKey();

	default void onYuccaCollision(BlockState state, Level level, BlockPos pos, Entity entity) {
		VoxelShape collision = state.getShape(level, pos, CollisionContext.empty()).move(pos.getX(), pos.getY(), pos.getZ());
		boolean colliding = entity.getBoundingBox().intersects(collision.bounds().inflate(1.0E-7D));

		if (colliding && entity instanceof LivingEntity living && !entity.getType().is(AtmosphericEntityTypeTags.YUCCA_IMMUNE)) {
			if (!level.isClientSide && (entity.xOld != entity.getX() || entity.zOld != entity.getZ())) {
				double d0 = Math.abs(entity.getX() - entity.xOld);
				double d1 = Math.abs(entity.getZ() - entity.zOld);
				if (d0 >= (double) 0.003F || d1 >= (double) 0.003F) {
					if (!entity.isCrouching()) {
						living.knockback(this.getKnockbackForce(), -Mth.sin((float) (entity.getYRot() * Math.PI / 180.0F)), Mth.cos((float) (entity.getYRot() * Math.PI / 180.0F)));
					}

					DamageSource source = level.damageSources().source(this.getDamageTypeKey());
					if (living.getItemBySlot(EquipmentSlot.HEAD).is(AtmosphericItems.BARREL_CACTUS.get())) {
						entity.hurt(source, 0.01F);
					} else {
						entity.hurt(source, 1.0F);
					}

					if (entity instanceof ServerPlayer serverPlayer) {
						if (!entity.getCommandSenderWorld().isClientSide() && !serverPlayer.isCreative()) {
							AtmosphericCriteriaTriggers.YUCCA_PRICK.get().trigger(serverPlayer);
						}
					}
				}
			}
		}
	}

	default PathType getYuccaPathType(@Nullable Mob entity) {
		return entity != null && entity.getType().is(AtmosphericEntityTypeTags.YUCCA_IMMUNE) ? null : PathType.DAMAGE_OTHER;
	}
}
