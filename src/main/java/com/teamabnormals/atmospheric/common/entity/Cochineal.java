package com.teamabnormals.atmospheric.common.entity;

import com.teamabnormals.atmospheric.common.entity.ai.goal.CochinealBreedGoal;
import com.teamabnormals.atmospheric.common.entity.ai.goal.CochinealRandomHopGoal;
import com.teamabnormals.atmospheric.core.other.tags.AtmosphericItemTags;
import com.teamabnormals.atmospheric.core.registry.AtmosphericEntityTypes;
import com.teamabnormals.atmospheric.core.registry.AtmosphericParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.JumpControl;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.network.PlayMessages;
import org.jetbrains.annotations.Nullable;

public class Cochineal extends Animal implements Saddleable {
	private static final EntityDataAccessor<Boolean> DATA_SADDLE_ID = SynchedEntityData.defineId(Cochineal.class, EntityDataSerializers.BOOLEAN);

	private boolean wasOnGround;
	private int jumpDelayTicks;
	private boolean superInLove = false;

	private float jumpAmount;
	private float jumpAmount0;

	public Cochineal(EntityType<? extends Cochineal> entity, Level level) {
		super(entity, level);
		this.jumpControl = new CochinealJumpControl(this);
		this.moveControl = new CochinealMoveControl(this);
	}

	public Cochineal(PlayMessages.SpawnEntity message, Level level) {
		this(AtmosphericEntityTypes.COCHINEAL.get(), level);
	}

	protected void registerGoals() {
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(1, new PanicGoal(this, 1.0D));
		this.goalSelector.addGoal(2, new CochinealBreedGoal(this, 1.0D));
		this.goalSelector.addGoal(3, new TemptGoal(this, 1.0D, Ingredient.of(AtmosphericItemTags.COCHINEAL_FOOD), false));
		this.goalSelector.addGoal(4, new CochinealRandomHopGoal(this));
		this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 10.0F));
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 10.0D).add(Attributes.MOVEMENT_SPEED, 0.5D).add(ForgeMod.ENTITY_GRAVITY.get(), 0.04D);
	}

	@Override
	public boolean isFood(ItemStack stack) {
		return stack.is(AtmosphericItemTags.COCHINEAL_FOOD);
	}

	@Override
	public MobType getMobType() {
		return MobType.ARTHROPOD;
	}

	@Override
	public boolean isSaddleable() {
		return this.isAlive() && !this.isBaby();
	}

	@Override
	protected void dropEquipment() {
		super.dropEquipment();
		if (this.isSaddled()) {
			this.spawnAtLocation(Items.SADDLE);
		}
	}

	@Nullable
	@Override
	public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob partner) {
		return AtmosphericEntityTypes.COCHINEAL.get().create(level);
	}

	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(DATA_SADDLE_ID, false);
	}

	public void addAdditionalSaveData(CompoundTag tag) {
		super.addAdditionalSaveData(tag);
		tag.putBoolean("SuperInLove", this.superInLove);
		tag.putBoolean("Saddle", this.isSaddled());
	}

	public void readAdditionalSaveData(CompoundTag tag) {
		super.readAdditionalSaveData(tag);
		this.superInLove = tag.getBoolean("SuperInLove");
		this.setSaddle(tag.getBoolean("Saddle"));
	}

	public void setSaddle(boolean saddle) {
		this.entityData.set(DATA_SADDLE_ID, saddle);
	}

	@Override
	public boolean isSaddled() {
		return this.entityData.get(DATA_SADDLE_ID);
	}

	public boolean isSuperInLove() {
		return this.superInLove;
	}

	public void setSuperInLove(boolean superInLove) {
		this.superInLove = superInLove;
	}

	@Override
	public InteractionResult mobInteract(Player player, InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);
		if (this.isFood(stack)) {
			int i = this.getAge();
			if (!this.level.isClientSide && i == 0 && this.canFallInLove()) {
				this.usePlayerItem(player, hand, stack);
				this.setInLove(player);
				if (stack.is(AtmosphericItemTags.COCHINEAL_BREEDING_FOOD)) {
					this.setSuperInLove(true);
				}
				return InteractionResult.SUCCESS;
			}

			if (this.isBaby()) {
				this.usePlayerItem(player, hand, stack);
				this.ageUp(getSpeedUpSecondsWhenFeeding(-i), true);
				return InteractionResult.sidedSuccess(this.level.isClientSide);
			}

			if (this.level.isClientSide) {
				return InteractionResult.CONSUME;
			}
		} else if (this.isSaddled() && !this.isVehicle() && !player.isSecondaryUseActive()) {
			if (!this.level.isClientSide) {
				player.startRiding(this);
			}

			return InteractionResult.sidedSuccess(this.level.isClientSide);
		} else if (stack.is(Items.SADDLE)) {
			return stack.interactLivingEntity(player, this, hand);
		}

		return InteractionResult.PASS;
	}

	@Override
	public void equipSaddle(@Nullable SoundSource source) {
		this.entityData.set(DATA_SADDLE_ID, true);
		if (source != null) {
			this.level.playSound(null, this, SoundEvents.PIG_SADDLE, source, 0.5F, 1.0F);
		}
	}

	@Override
	public void handleEntityEvent(byte id) {
		if (id == 1) {
			this.spawnSprintParticle();
			this.jumping = true;
		} else {
			super.handleEntityEvent(id);
		}
	}

	@Override
	protected void jumpFromGround() {
		super.jumpFromGround();
		double speed = this.moveControl.getSpeedModifier();
		if (speed > 0.0D) {
			this.moveRelative((float) speed, new Vec3(0.0D, 0.0D, 1.0D));
		}
	}

	@Override
	protected float getJumpPower() {
		return ((CochinealJumpControl) this.jumpControl).jumpPower;
	}

	@Override
	public void tick() {
		super.tick();

		if (this.onGround || this.isInFluidType())
			this.setDiscardFriction(false);
	}

	@Override
	public void aiStep() {
		super.aiStep();

		if (this.getInLoveTime() == 0) {
			this.setSuperInLove(false);
		}

		this.jumpAmount0 = this.jumpAmount;
		if (this.jumping) {
			if (this.jumpAmount < 1.0F)
				this.jumpAmount = Math.min(this.jumpAmount + 0.5F, 1.0F);
			else
				this.setJumping(false);
		} else if (this.jumpAmount > 0.0F) {
			this.jumpAmount = Math.max(this.jumpAmount - 0.1F, 0.0F);
		}

		if (!this.onGround && this.level.isClientSide) {
			boolean cold = this.level.getBiome(this.blockPosition()).get().coldEnoughToSnow(this.blockPosition());
			for (int i = 0; i < 2; i++) {
				this.level.addParticle(cold ? AtmosphericParticleTypes.COLD_COCHINEAL_TRAIL.get() : AtmosphericParticleTypes.COCHINEAL_TRAIL.get(), this.getRandomX(0.5D), this.getRandomY(), this.getRandomZ(0.5D), 0.0D, 0.0D, 0.0D);
			}
		}
	}

	@Override
	public void customServerAiStep() {
		if (this.jumpDelayTicks > 0) {
			--this.jumpDelayTicks;
		}

		if (this.onGround && !this.wasOnGround) {
			this.setJumping(false);
			this.jumpDelayTicks = 10;
		}

		this.wasOnGround = this.onGround;
	}

	public float getJumpAmount(float partialTick) {
		return Mth.lerp(partialTick, this.jumpAmount0, this.jumpAmount);
	}

	@Override
	public int getMaxHeadXRot() {
		return 0;
	}

	@Override
	public int getMaxHeadYRot() {
		return 5;
	}

	@Override
	public boolean canSpawnSprintParticle() {
		return false;
	}

	@Override
	public void setJumping(boolean jumping) {
		super.setJumping(jumping);
		if (jumping) {
			if (!this.level.isClientSide)
				this.level.broadcastEntityEvent(this, (byte) 1);
		}
	}

	private void facePoint(double x, double z) {
		this.setYRot((float) (Mth.atan2(z - this.getZ(), x - this.getX()) * Mth.RAD_TO_DEG) - 90.0F);
		this.yBodyRot = this.getYRot();
		this.yHeadRot = this.yBodyRot;
	}

	@Override
	public boolean causeFallDamage(float distance, float damageMultiplier, DamageSource source) {
		return false;
	}

	static class CochinealJumpControl extends JumpControl {
		private final Cochineal cochineal;
		private float jumpPower;

		public CochinealJumpControl(Cochineal cochineal) {
			super(cochineal);
			this.cochineal = cochineal;
		}

		public boolean wantJump() {
			return this.jump;
		}

		@Override
		public void tick() {
			if (this.jump) {
				this.cochineal.setJumping(true);
				this.jump = false;
			}
		}

		private void calculateJumpPower(double height) {
			this.jumpPower = Mth.clamp(0.5F + (float) height * 0.05F, 0.4F, 0.8F);
		}
	}

	static class CochinealMoveControl extends MoveControl {
		private final Cochineal cochineal;
		private int justJumpedTime;

		public CochinealMoveControl(Cochineal cochineal) {
			super(cochineal);
			this.cochineal = cochineal;
			this.speedModifier = 0.0D;
		}

		@Override
		public void tick() {
			CochinealJumpControl jumpcontrol = (CochinealJumpControl) this.cochineal.jumpControl;
			if (this.cochineal.onGround) {
				if (this.hasWanted() && this.cochineal.jumpDelayTicks == 0) {
					double dx = this.wantedX - this.mob.getX();
					double dy = this.wantedY - this.mob.getY();
					double dz = this.wantedZ - this.mob.getZ();
					double distance = Math.sqrt(dx * dx + dz * dz);
					this.cochineal.facePoint(this.wantedX, this.wantedZ);
					jumpcontrol.calculateJumpPower(dy);
					jumpcontrol.jump();
					this.speedModifier = this.calculateJumpSpeed(distance, dy);
					this.justJumpedTime = 3;
					this.cochineal.setDiscardFriction(true);
					this.operation = Operation.JUMPING;
				} else if (!this.cochineal.jumping && !jumpcontrol.wantJump()) {
					this.speedModifier = 0.0D;
					this.operation = Operation.WAIT;
				}
			} else if (operation == Operation.JUMPING) {
				this.cochineal.setDiscardFriction(true);
				Vec3 vec3 = new Vec3(this.wantedX - this.mob.getX(), 0.0D, this.wantedZ - this.mob.getZ()).normalize();
				double d0 = this.cochineal.getDeltaMovement().horizontalDistance();
				if (this.justJumpedTime > 0) {
					--this.justJumpedTime;
					if (d0 < this.speedModifier) {
						Vec3 vec31 = vec3.scale(Math.min(0.2D, this.speedModifier - d0));
						this.cochineal.setDeltaMovement(this.cochineal.getDeltaMovement().add(vec31));
					}
				} else if (d0 < 0.1D) {
					Vec3 vec31 = vec3.scale(Math.min(0.04D, 0.1D - d0));
					this.cochineal.setDeltaMovement(this.cochineal.getDeltaMovement().add(vec31));
				}
			}

			/*
			if (this.operation != Operation.WAIT)
				for (int i = 0; i < 4; ++i) {
					NetworkUtil.spawnParticle("minecraft:smoke", this.wantedX, this.wantedY, this.wantedZ, 0.0D, 0.0D, 0.0D);
				}

			 */
		}

		@Override
		public void setWantedPosition(double x, double y, double z, double speed) {
			if (this.cochineal.isInWater())
				speed = 1.5D;
			super.setWantedPosition(x, y, z, speed);
		}

		private double calculateJumpSpeed(double distance, double height) {
			double jumppower = ((CochinealJumpControl) this.cochineal.jumpControl).jumpPower;
			double gravity = -this.cochineal.getAttributeValue(ForgeMod.ENTITY_GRAVITY.get());

			// I love L'Hôpital's rule jak
			if (height == 0) {
				return -gravity * distance / Math.abs(jumppower) / 2.0D;
			} else {
				return distance * (jumppower - Math.sqrt(jumppower * jumppower + 2 * height * gravity)) / height / 2.0D;
			}
		}
	}
}