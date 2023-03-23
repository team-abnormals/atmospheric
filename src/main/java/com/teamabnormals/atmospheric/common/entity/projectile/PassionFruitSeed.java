package com.teamabnormals.atmospheric.common.entity.projectile;

import com.teamabnormals.atmospheric.core.other.AtmosphericCriteriaTriggers;
import com.teamabnormals.atmospheric.core.other.AtmosphericDamageSources;
import com.teamabnormals.atmospheric.core.registry.AtmosphericEntityTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PlayMessages;

public class PassionFruitSeed extends ThrowableProjectile {
	private int amplifier = 0;

	public PassionFruitSeed(EntityType<? extends PassionFruitSeed> type, Level worldIn) {
		super(type, worldIn);
	}

	public PassionFruitSeed(PlayMessages.SpawnEntity spawnEntity, Level world) {
		this(AtmosphericEntityTypes.PASSION_FRUIT_SEED.get(), world);
	}

	public PassionFruitSeed(Level worldIn, LivingEntity throwerIn, int amplifier) {
		super(AtmosphericEntityTypes.PASSION_FRUIT_SEED.get(), throwerIn, worldIn);
		this.amplifier = amplifier;
	}

	public PassionFruitSeed(Level worldIn, double x, double y, double z, int amplifier) {
		super(AtmosphericEntityTypes.PASSION_FRUIT_SEED.get(), x, y, z, worldIn);
		this.amplifier = amplifier;
	}

	@Override
	protected void onHit(HitResult result) {
		super.onHit(result);
		if (result.getType() == HitResult.Type.ENTITY) {
			Entity entity = ((EntityHitResult) result).getEntity();
			entity.hurt(AtmosphericDamageSources.causePassionFruitSeedDamage(this, this.getOwner()), 0.5F + amplifier);
			if (this.getOwner() instanceof ServerPlayer serverplayerentity) {
				if (!entity.getCommandSenderWorld().isClientSide()) {
					AtmosphericCriteriaTriggers.SPIT_PASSION_FRUIT.trigger(serverplayerentity);
				}
			}
		}

		if (!this.level.isClientSide) {
			this.discard();
		}

	}

	@Override
	protected void defineSynchedData() {
	}

	@Override
	public Packet<?> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}
