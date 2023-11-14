package com.teamabnormals.atmospheric.common.entity;

import com.teamabnormals.atmospheric.core.registry.AtmosphericEntityTypes;
import com.teamabnormals.atmospheric.core.registry.AtmosphericParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.network.NetworkHooks;

import java.util.List;

public class OrangeVaporCloud extends Entity {
	private static final EntityDataAccessor<Float> DATA_RADIUS = SynchedEntityData.defineId(OrangeVaporCloud.class, EntityDataSerializers.FLOAT);
	private static final EntityDataAccessor<Boolean> IS_BLOOD_ORANGE = SynchedEntityData.defineId(OrangeVaporCloud.class, EntityDataSerializers.BOOLEAN);
	private int duration = 600;
	private float radiusPerTick;

	public OrangeVaporCloud(EntityType<? extends OrangeVaporCloud> p_19704_, Level p_19705_) {
		super(p_19704_, p_19705_);
		this.noPhysics = true;
		this.setRadius(1.5F);
	}

	public OrangeVaporCloud(Level level, double x, double y, double z) {
		this(AtmosphericEntityTypes.ORANGE_VAPOR_CLOUD.get(), level);
		this.setPos(x, y, z);
	}

	protected void defineSynchedData() {
		this.getEntityData().define(DATA_RADIUS, 0.5F);
		this.getEntityData().define(IS_BLOOD_ORANGE, false);
	}

	public void setRadius(float radius) {
		if (!this.level.isClientSide) {
			this.getEntityData().set(DATA_RADIUS, Mth.clamp(radius, 0.0F, 32.0F));
		}
	}

	public void refreshDimensions() {
		double d0 = this.getX();
		double d1 = this.getY();
		double d2 = this.getZ();
		super.refreshDimensions();
		this.setPos(d0, d1, d2);
	}

	public float getRadius() {
		return this.getEntityData().get(DATA_RADIUS);
	}

	public boolean isBloodOrange() {
		return this.getEntityData().get(IS_BLOOD_ORANGE);
	}

	public void setBloodOrange(boolean bloodOrange) {
		this.getEntityData().set(IS_BLOOD_ORANGE, bloodOrange);
	}

	public int getDuration() {
		return this.duration;
	}

	public void setDuration(int p_19735_) {
		this.duration = p_19735_;
	}

	public void tick() {
		super.tick();
		float f = this.getRadius();
		if (this.level.isClientSide) {
			int i = Mth.ceil((float) Math.PI * f * f * 0.05F);

			boolean fresh = this.tickCount < 4;
			if (fresh) {
				i *= 15;
			}

			for (int j = 0; j < i; ++j) {
				if (fresh || random.nextInt(8) == 0) {
					float f2 = this.random.nextFloat() * ((float) Math.PI * 2F);
					float f3 = Mth.sqrt(this.random.nextFloat()) * f;
					double d0 = this.getX() + (double) (Mth.cos(f2) * f3);
					double d2 = this.getY() + random.nextFloat() * this.getHeight();
					double d4 = this.getZ() + (double) (Mth.sin(f2) * f3);

					double d5 = (0.5D - this.random.nextDouble()) * 0.15D;
					double d6 = 0.15;
					double d7 = (0.5D - this.random.nextDouble()) * 0.15D;

					this.level.addAlwaysVisibleParticle(this.isBloodOrange() ? AtmosphericParticleTypes.BLOOD_ORANGE_VAPOR.get() : AtmosphericParticleTypes.ORANGE_VAPOR.get(), d0, d2, d4, d5, d6, d7);
				}
			}
		} else {
			if (this.tickCount >= this.duration) {
				this.discard();
				return;
			}

			if (this.radiusPerTick != 0.0F) {
				f += this.radiusPerTick;

				if (this.level.dimensionType().ultraWarm() && !this.isBloodOrange()) {
					f += this.radiusPerTick * 4.0F;
				}

				if (f < 0.5F) {
					this.discard();
					return;
				}

				this.setRadius(f);
			}

			if (this.tickCount % 5 == 0) {
				List<LivingEntity> list1 = this.level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox());
				if (!list1.isEmpty()) {
					for (LivingEntity livingentity : list1) {
						if (livingentity instanceof Wolf wolf) {
							wolf.isWet = true;
						}
					}
				}
			}
		}

	}

	public void setRadiusPerTick(float p_19739_) {
		this.radiusPerTick = p_19739_;
	}

	protected void readAdditionalSaveData(CompoundTag tag) {
		this.tickCount = tag.getInt("Age");
		this.duration = tag.getInt("Duration");
		this.radiusPerTick = tag.getFloat("RadiusPerTick");
		this.setRadius(tag.getFloat("Radius"));
	}

	protected void addAdditionalSaveData(CompoundTag tag) {
		tag.putInt("Age", this.tickCount);
		tag.putInt("Duration", this.duration);
		tag.putFloat("RadiusPerTick", this.radiusPerTick);
		tag.putFloat("Radius", this.getRadius());
	}

	public void onSyncedDataUpdated(EntityDataAccessor<?> p_19729_) {
		if (DATA_RADIUS.equals(p_19729_)) {
			this.refreshDimensions();
		}

		super.onSyncedDataUpdated(p_19729_);
	}

	public PushReaction getPistonPushReaction() {
		return PushReaction.IGNORE;
	}

	public Packet<?> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	public float getHeight() {
		return this.getRadius() * 2.0F;
	}

	public EntityDimensions getDimensions(Pose p_19721_) {
		return EntityDimensions.scalable(this.getRadius() * 2.0F, this.getHeight());
	}
}