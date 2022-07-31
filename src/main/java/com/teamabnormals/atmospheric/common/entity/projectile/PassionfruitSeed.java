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

public class PassionfruitSeed extends ThrowableProjectile {
	private int amplifier = 0;

	public PassionfruitSeed(EntityType<? extends PassionfruitSeed> type, Level worldIn) {
		super(type, worldIn);
	}

	public PassionfruitSeed(PlayMessages.SpawnEntity spawnEntity, Level world) {
		this(AtmosphericEntityTypes.PASSIONFRUIT_SEED.get(), world);
	}

	public PassionfruitSeed(Level worldIn, LivingEntity throwerIn, int amplifier) {
		super(AtmosphericEntityTypes.PASSIONFRUIT_SEED.get(), throwerIn, worldIn);
		this.amplifier = amplifier;
	}

	public PassionfruitSeed(Level worldIn, double x, double y, double z, int amplifier) {
		super(AtmosphericEntityTypes.PASSIONFRUIT_SEED.get(), x, y, z, worldIn);
		this.amplifier = amplifier;
	}

	protected void onHit(HitResult result) {
		super.onHit(result);
		if (result.getType() == HitResult.Type.ENTITY) {
			Entity entity = ((EntityHitResult) result).getEntity();
			entity.hurt(AtmosphericDamageSources.causePassionfruitSeedDamage(this, this.getOwner()), 0.5F + amplifier);
			if (this.getOwner() instanceof ServerPlayer serverplayerentity) {
				if (!entity.getCommandSenderWorld().isClientSide()) {
					AtmosphericCriteriaTriggers.SPIT_PASSIONFRUIT.trigger(serverplayerentity);
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
