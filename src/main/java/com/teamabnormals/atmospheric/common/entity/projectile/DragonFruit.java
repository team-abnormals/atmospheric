package com.teamabnormals.atmospheric.common.entity.projectile;

import com.teamabnormals.atmospheric.core.registry.AtmosphericEntityTypes;
import com.teamabnormals.atmospheric.core.registry.AtmosphericItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PlayMessages;

public class DragonFruit extends Entity {
	private Direction rollingDirection;
	public int rollingTicks = 200;

	public DragonFruit(EntityType<?> entityType, Level level) {
		super(entityType, level);
	}

	public DragonFruit(PlayMessages.SpawnEntity message, Level level) {
		this(AtmosphericEntityTypes.DRAGON_FRUIT.get(), level);
	}

	public boolean isPickable() {
		return true;
	}

	public boolean isPushable() {
		return true;
	}

	public ItemStack getPickResult() {
		return new ItemStack(AtmosphericItems.DRAGON_FRUIT.get());
	}

	@Override
	public void tick() {
		super.tick();

		this.xo = this.getX();
		this.yo = this.getY();
		this.zo = this.getZ();
		Vec3 vec3 = this.getDeltaMovement();
		float f = this.getEyeHeight() - 0.11111111F;
		if (this.isInWater() && this.getFluidHeight(FluidTags.WATER) > (double) f) {
			this.setUnderwaterMovement();
		}
		if (!this.isNoGravity()) {
			this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.04D, 0.0D));
		}

		if (this.level.isClientSide) {
			this.noPhysics = false;
		} else {
			this.noPhysics = !this.level.noCollision(this, this.getBoundingBox().deflate(1.0E-7D));
			if (this.noPhysics) {
				this.moveTowardsClosestSpace(this.getX(), (this.getBoundingBox().minY + this.getBoundingBox().maxY) / 2.0D, this.getZ());
			}
		}

		if (!this.onGround || this.getDeltaMovement().horizontalDistanceSqr() > (double) 1.0E-5F || (this.tickCount + this.getId()) % 4 == 0) {
			this.move(MoverType.SELF, this.getDeltaMovement());
			float f1 = 0.98F;
			if (this.onGround) {
				f1 = this.level.getBlockState(new BlockPos(this.getX(), this.getY() - 1.0D, this.getZ())).getFriction(level, new BlockPos(this.getX(), this.getY() - 1.0D, this.getZ()), this) * 0.98F;
			}

			this.setDeltaMovement(this.getDeltaMovement().multiply(f1, 0.98D, f1));
			if (this.onGround) {
				Vec3 vec31 = this.getDeltaMovement();
				if (vec31.y < 0.0D) {
					this.setDeltaMovement(vec31.multiply(1.0D, -0.5D, 1.0D));
				}
			}
		}

		if (rollingTicks > 0) {
			this.rollingTicks--;
		} else {
			this.showBreakingParticles();
			this.brokenByPlayer();
			this.kill();
		}

		if (this.horizontalCollision) {
			this.showBreakingParticles();
			this.brokenByPlayer();
			this.kill();
		}

		if (this.getRollingDirection() != null && (this.tickCount + this.getId()) % 4 == 0) {
			Vec3i direction = this.getRollingDirection().getNormal();
			double speed = this.rollingTicks / 1000.0D;
			speed = Math.max(speed, 0.1D);
			if (!this.isOnGround()) {
				speed *= 0.1D;
			}


			this.push(direction.getX() * speed, 0.0D, direction.getZ() * speed);
		}

		this.hasImpulse |= this.updateInWaterStateAndDoFluidPushing();
		if (!this.level.isClientSide) {
			double d0 = this.getDeltaMovement().subtract(vec3).lengthSqr();
			if (d0 > 0.01D) {
				this.hasImpulse = true;
			}
		}
	}


	public boolean isAlwaysTicking() {
		return true;
	}

	public Direction getRollingDirection() {
		return this.rollingDirection;
	}

	public void setRollingDirection(Direction direction) {
		this.rollingDirection = direction;
	}

	private void setUnderwaterMovement() {
		Vec3 vec3 = this.getDeltaMovement();
		this.setDeltaMovement(vec3.x * (double) 0.99F, vec3.y + (double) (vec3.y < (double) 0.06F ? 5.0E-4F : 0.0F), vec3.z * (double) 0.99F);
	}

	@Override
	public boolean hurt(DamageSource source, float damage) {
		if (!this.level.isClientSide && !this.isRemoved()) {
			if (DamageSource.OUT_OF_WORLD.equals(source)) {
				this.kill();
				return false;
			} else if (!this.isInvulnerableTo(source)) {
				if (source.isExplosion()) {
					this.brokenByPlayer();
					this.kill();
					return false;
				} else {
					boolean flag = source.getDirectEntity() instanceof AbstractArrow;
					boolean flag1 = flag && ((AbstractArrow) source.getDirectEntity()).getPierceLevel() > 0;
					boolean flag2 = "player".equals(source.getMsgId());
					if (!flag2 && !flag) {
						return false;
					} else if (source.getEntity() instanceof Player && !((Player) source.getEntity()).getAbilities().mayBuild) {
						return false;
					} else if (source.isCreativePlayer()) {
						this.playBrokenSound();
						this.showBreakingParticles();
						this.kill();
						return flag1;
					} else {
						this.brokenByPlayer();
						this.showBreakingParticles();
						this.kill();

						return true;
					}
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	private void brokenByPlayer() {
		Block.popResource(this.level, this.blockPosition(), new ItemStack(AtmosphericItems.DRAGON_FRUIT.get()));
		this.playBrokenSound();
	}

	private void showBreakingParticles() {
		if (this.level instanceof ServerLevel serverLevel) {
			serverLevel.sendParticles(new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(AtmosphericItems.DRAGON_FRUIT.get())), this.getX(), this.getY(0.6666666666666666D), this.getZ(), 10, this.getBbWidth() / 4.0F, (double) (this.getBbHeight() / 4.0F), (double) (this.getBbWidth() / 4.0F), 0.05D);
		}
	}


	private void playBrokenSound() {
		this.level.playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.SWEET_BERRY_BUSH_BREAK, this.getSoundSource(), 1.0F, 1.0F);
	}

	@Override
	protected void defineSynchedData() {

	}

	@Override
	protected void readAdditionalSaveData(CompoundTag p_20052_) {

	}

	@Override
	protected void addAdditionalSaveData(CompoundTag p_20139_) {

	}

	@Override
	public Packet<?> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}
