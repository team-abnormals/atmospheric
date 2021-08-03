package com.minecraftabnormals.atmospheric.common.entity;

import com.minecraftabnormals.atmospheric.core.other.AtmosphericCriteriaTriggers;
import com.minecraftabnormals.atmospheric.core.other.AtmosphericDamageSources;
import com.minecraftabnormals.atmospheric.core.registry.AtmosphericEntities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.network.IPacket;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.network.NetworkHooks;

public class PassionfruitSeedEntity extends ThrowableEntity {
	private int amplifier = 0;

	public PassionfruitSeedEntity(EntityType<? extends PassionfruitSeedEntity> type, World worldIn) {
		super(type, worldIn);
	}

	public PassionfruitSeedEntity(FMLPlayMessages.SpawnEntity spawnEntity, World world) {
		this(AtmosphericEntities.PASSIONFRUIT_SEED.get(), world);
	}

	public PassionfruitSeedEntity(World worldIn, LivingEntity throwerIn, int amplifier) {
		super(AtmosphericEntities.PASSIONFRUIT_SEED.get(), throwerIn, worldIn);
		this.amplifier = amplifier;
	}

	public PassionfruitSeedEntity(World worldIn, double x, double y, double z, int amplifier) {
		super(AtmosphericEntities.PASSIONFRUIT_SEED.get(), x, y, z, worldIn);
		this.amplifier = amplifier;
	}

	protected void onHit(RayTraceResult result) {
		super.onHit(result);
		if (result.getType() == RayTraceResult.Type.ENTITY) {
			Entity entity = ((EntityRayTraceResult) result).getEntity();
			entity.hurt(AtmosphericDamageSources.causePassionfruitSeedDamage(this, this.getOwner()), 0.5F + amplifier);
			if (this.getOwner() instanceof ServerPlayerEntity) {
				ServerPlayerEntity serverplayerentity = (ServerPlayerEntity) this.getOwner();
				if (!entity.getCommandSenderWorld().isClientSide()) {
					AtmosphericCriteriaTriggers.SPIT_PASSIONFRUIT.trigger(serverplayerentity);
				}
			}
		}

		if (!this.level.isClientSide) {
			this.remove();
		}

	}

	@Override
	protected void defineSynchedData() {
	}

	@Override
	public IPacket<?> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}
