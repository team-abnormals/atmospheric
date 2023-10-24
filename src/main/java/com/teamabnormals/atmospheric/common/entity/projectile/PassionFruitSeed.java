package com.teamabnormals.atmospheric.common.entity.projectile;

import com.teamabnormals.atmospheric.core.other.AtmosphericDamageSources;
import com.teamabnormals.atmospheric.core.registry.AtmosphericEntityTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.network.PlayMessages;

public class PassionFruitSeed extends ThrowableProjectile {
	private int amplifier = 0;

	public PassionFruitSeed(EntityType<? extends PassionFruitSeed> type, Level level) {
		super(type, level);
	}

	public PassionFruitSeed(PlayMessages.SpawnEntity message, Level level) {
		this(AtmosphericEntityTypes.PASSION_FRUIT_SEED.get(), level);
	}

	public PassionFruitSeed(Level level, LivingEntity shooter, int amplifier) {
		super(AtmosphericEntityTypes.PASSION_FRUIT_SEED.get(), shooter, level);
		this.amplifier = amplifier;
	}

	@Override
	protected void onHitEntity(EntityHitResult result) {
		super.onHitEntity(result);
		Entity entity = this.getOwner();
		if (entity instanceof LivingEntity living) {
			result.getEntity().hurt(AtmosphericDamageSources.passionFruitSeedAttack(this, living).setProjectile(), 0.5F + amplifier);
			if (!level.isClientSide()) {
				this.discard();
			}
		}
	}

	@Override
	protected void onHitBlock(BlockHitResult result) {
		super.onHitBlock(result);
		if (!this.level.isClientSide) {
			this.discard();
		}
	}

	@Override
	protected void defineSynchedData() {
	}
}
