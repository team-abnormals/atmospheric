package com.teamabnormals.atmospheric.common.entity;

import com.teamabnormals.atmospheric.common.entity.ai.goal.*;
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
import net.minecraft.world.entity.ai.control.BodyRotationControl;
import net.minecraft.world.entity.ai.control.LookControl;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
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
	private static final EntityDataAccessor<Boolean> IS_SADDLED = SynchedEntityData.defineId(Cochineal.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Boolean> IS_LEAPING = SynchedEntityData.defineId(Cochineal.class, EntityDataSerializers.BOOLEAN);

	private boolean wasOnGroundOrFluid;
	private boolean jumpingQuickly;
	private int jumpDelayTicks;
	private boolean superInLove = false;

	private boolean jumpAnim;
	private float jumpAmount;
	private float jumpAmount0;

	public Cochineal(EntityType<? extends Cochineal> entity, Level level) {
		super(entity, level);
		this.moveControl = new CochinealMoveControl(this);
		this.lookControl = new CochinealLookControl(this);
	}

	public Cochineal(PlayMessages.SpawnEntity message, Level level) {
		this(AtmosphericEntityTypes.COCHINEAL.get(), level);
	}

	protected void registerGoals() {
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(1, new CochinealFleeGoal(this));
		this.goalSelector.addGoal(2, new CochinealBreedGoal(this, 1.2D));
		this.goalSelector.addGoal(3, new CochinealTemptGoal(this, 1.2D, Ingredient.of(AtmosphericItemTags.COCHINEAL_FOOD)));
		this.goalSelector.addGoal(4, new CochinealRandomSwimGoal(this, 1.2D));
		this.goalSelector.addGoal(5, new CochinealRandomHopGoal(this));
		this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 10.0F));
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
		this.entityData.define(IS_SADDLED, false);
		this.entityData.define(IS_LEAPING, false);
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
		this.entityData.set(IS_SADDLED, saddle);
	}

	@Override
	public boolean isSaddled() {
		return this.entityData.get(IS_SADDLED);
	}

	public void setLeaping(boolean leaping) {
		this.entityData.set(IS_LEAPING, leaping);
	}

	public void setJumpingQuickly(boolean jumpingQuickly) {
		this.jumpingQuickly = jumpingQuickly;
	}

	public boolean isLeaping() {
		return this.entityData.get(IS_LEAPING);
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
		this.entityData.set(IS_SADDLED, true);
		if (source != null) {
			this.level.playSound(null, this, SoundEvents.PIG_SADDLE, source, 0.5F, 1.0F);
		}
	}

	@Override
	public void handleEntityEvent(byte id) {
		if (id == 1) {
			this.spawnSprintParticle();
			this.jumpAnim = true;
		} else {
			super.handleEntityEvent(id);
		}
	}

	@Override
	public void aiStep() {
		super.aiStep();

		if (this.getInLoveTime() == 0) {
			this.setSuperInLove(false);
		}

		this.jumpAmount0 = this.jumpAmount;
		if (this.jumpAnim) {
			if (this.jumpAmount < 1.0F)
				this.jumpAmount = Math.min(this.jumpAmount + 0.5F, 1.0F);
			else
				this.jumpAnim = false;
		} else if (this.jumpAmount > 0.0F) {
			this.jumpAmount = Math.max(this.jumpAmount - 0.1F, 0.0F);
		}

		if ((this.isLeaping() || this.hurtTime > 0) && this.level.isClientSide) {
			boolean cold = this.level.getBiome(this.blockPosition()).get().coldEnoughToSnow(this.blockPosition());
			for (int i = 0; i < 4; i++) {
				double x = this.getX() - this.getLookAngle().x / 2.0D + (this.random.nextDouble() - 0.5D) * 0.8D;
				double y = this.getY() + 0.8D + (this.random.nextDouble() - 0.5D) * 0.8D;
				double z = this.getZ() - this.getLookAngle().z / 2.0D + (this.random.nextDouble() - 0.5D) * 0.8D;
				this.level.addParticle(cold ? AtmosphericParticleTypes.COLD_COCHINEAL_TRAIL.get() : AtmosphericParticleTypes.COCHINEAL_TRAIL.get(), x, y, z, 0.0D, 0.0D, 0.0D);
			}
		}
	}

	@Override
	public void customServerAiStep() {
		if (this.jumpDelayTicks > 0) {
			--this.jumpDelayTicks;
		}

		if ((this.onGround || this.isInFluidType()) && !this.wasOnGroundOrFluid) {
			this.setDiscardFriction(false);
			this.setLeaping(false);
			this.jumpDelayTicks = this.jumpingQuickly ? 2 : 10;
		}

		this.wasOnGroundOrFluid = this.onGround || this.isInFluidType();
	}

	protected void leap(double jumpPower) {
		Vec3 vec3 = this.getDeltaMovement();
		this.setDeltaMovement(vec3.x, jumpPower + this.getJumpBoostPower(), vec3.z);
		double speed = ((CochinealMoveControl) this.moveControl).jumpSpeed;
		this.moveRelative((float) speed, new Vec3(0.0D, 0.0D, 1.0D));
		this.hasImpulse = true;
		this.level.broadcastEntityEvent(this, (byte) 1);
		this.setLeaping(true);
		net.minecraftforge.common.ForgeHooks.onLivingJump(this);
	}

	public boolean canLeap() {
		return this.onGround && !this.isLeaping() && !((CochinealMoveControl) this.moveControl).wantsToJump && this.jumpDelayTicks == 0;
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

	private void facePoint(double x, double z) {
		this.setYRot((float) (Mth.atan2(z - this.getZ(), x - this.getX()) * Mth.RAD_TO_DEG) - 90.0F);
		this.yBodyRot = this.getYRot();
		this.yHeadRot = this.getYRot();
	}

	@Override
	public boolean causeFallDamage(float distance, float damageMultiplier, DamageSource source) {
		return false;
	}

	@Override
	public MoveControl getMoveControl() {
		return this.moveControl;
	}

	@Override
	protected BodyRotationControl createBodyControl() {
		return new CochinealBodyRotationControl(this);
	}

	static class CochinealBodyRotationControl extends BodyRotationControl {
		private final Cochineal cochineal;

		public CochinealBodyRotationControl(Cochineal cochineal) {
			super(cochineal);
			this.cochineal = cochineal;
		}

		@Override
		public void clientTick() {
			if (this.cochineal.isLeaping()) {
				this.cochineal.yHeadRot = this.cochineal.getYRot();
				this.cochineal.yBodyRot = this.cochineal.getYRot();
			}
		}
	}

	static class CochinealLookControl extends LookControl {
		private final Cochineal cochineal;

		public CochinealLookControl(Cochineal cochineal) {
			super(cochineal);
			this.cochineal = cochineal;
		}

		@Override
		public void tick() {
			if (!this.cochineal.isLeaping())
				super.tick();
		}
	}

	public static class CochinealMoveControl extends MoveControl {
		private final Cochineal cochineal;
		private double jumpX;
		private double jumpY;
		private double jumpZ;
		private boolean wantsToJump;
		private int justJumpedTime;
		private double jumpSpeed;

		public CochinealMoveControl(Cochineal cochineal) {
			super(cochineal);
			this.cochineal = cochineal;
		}

		@Override
		public void tick() {
			if (this.cochineal.isInFluidType()) {
				super.tick();
				this.wantsToJump = false;
				return;
			}

			if (this.cochineal.isLeaping()) {
				Vec3 vec3 = new Vec3(this.jumpX - this.mob.getX(), 0.0D, this.jumpZ - this.mob.getZ()).normalize();
				double d0 = this.cochineal.getDeltaMovement().horizontalDistance();
				if (this.justJumpedTime > 0) {
					--this.justJumpedTime;
					if (d0 < this.jumpSpeed) {
						Vec3 vec31 = vec3.scale(Math.min(0.2D, this.jumpSpeed - d0));
						this.cochineal.setDeltaMovement(this.cochineal.getDeltaMovement().add(vec31));
					}
				} else if (d0 < 0.1D) {
					Vec3 vec31 = vec3.scale(Math.min(0.04D, 0.1D - d0));
					this.cochineal.setDeltaMovement(this.cochineal.getDeltaMovement().add(vec31));
				}
				this.cochineal.setDiscardFriction(true);
				this.stopNormalNavigation();
			} else if (this.cochineal.onGround) {
				if (this.wantsToJump && this.cochineal.jumpDelayTicks == 0) {
					if (this.canReach(this.jumpX, this.jumpY, this.jumpZ)) {
						double dx = this.jumpX - this.mob.getX();
						double dy = this.jumpY - this.mob.getY();
						double dz = this.jumpZ - this.mob.getZ();
						double distance = Math.sqrt(dx * dx + dz * dz);
						this.cochineal.facePoint(this.jumpX, this.jumpZ);

						double jumppower = this.cochineal.jumpingQuickly ? Mth.clamp(0.4D + dy * 0.05D, 0.4D, 0.8D) : Mth.clamp(0.5D + dy * 0.05D, 0.4D, 0.8D);
						jumppower += this.cochineal.getJumpBoostPower();

						this.jumpSpeed = this.calculateJumpSpeed(distance, dy, jumppower);
						this.cochineal.leap(jumppower);

						this.justJumpedTime = 3;
					}
					this.wantsToJump = false;
				}
				this.stopNormalNavigation();
			}
		}

		public void leapTo(double x, double y, double z) {
			this.jumpX = x;
			this.jumpY = y;
			this.jumpZ = z;
			this.wantsToJump = true;
		}

		private void stopNormalNavigation() {
			this.operation = Operation.WAIT;
			this.cochineal.getNavigation().stop();
			this.cochineal.setZza(0.0F);
		}

		public boolean canReach(double x, double y, double z) {
			double dx = x - this.mob.getX();
			double dy = y - this.mob.getY();
			double dz = z - this.mob.getZ();
			return dy <= 8.0D && dy >= -8.0D && dx * dx + dz * dz <= 256.0D;
		}

		private double calculateJumpSpeed(double distance, double height, double jumpPower) {
			double gravity = -this.cochineal.getAttributeValue(ForgeMod.ENTITY_GRAVITY.get());

			if (height == 0) {
				return -gravity * distance / Math.abs(jumpPower) / 2.0D;
			} else {
				return distance * (jumpPower - Math.sqrt(jumpPower * jumpPower + 2 * height * gravity)) / height / 2.0D;
			}
		}
	}
}