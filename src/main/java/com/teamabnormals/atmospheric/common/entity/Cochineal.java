package com.teamabnormals.atmospheric.common.entity;

import com.teamabnormals.atmospheric.common.entity.ai.goal.CochinealBreedGoal;
import com.teamabnormals.atmospheric.common.entity.ai.goal.CochinealRandomStrollGoal;
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
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.PlayMessages;
import org.jetbrains.annotations.Nullable;

public class Cochineal extends Animal implements Saddleable {
	private static final EntityDataAccessor<Boolean> DATA_SADDLE_ID = SynchedEntityData.defineId(Cochineal.class, EntityDataSerializers.BOOLEAN);

	private int jumpTicks;
	private int jumpDuration;
	private boolean wasOnGround;
	private int jumpDelayTicks;

	public Cochineal(EntityType<? extends Cochineal> entity, Level level) {
		super(entity, level);
		this.jumpControl = new CochinealJumpControl(this);
		this.moveControl = new CochinealMoveControl(this);
		this.setSpeedModifier(0.0D);
	}

	public Cochineal(PlayMessages.SpawnEntity message, Level level) {
		this(AtmosphericEntityTypes.COCHINEAL.get(), level);
	}

	protected void registerGoals() {
		this.goalSelector.addGoal(1, new FloatGoal(this));
		this.goalSelector.addGoal(1, new CochinealPanicGoal(this, 2.2D));
		this.goalSelector.addGoal(2, new CochinealBreedGoal(this, 0.8D));
		this.goalSelector.addGoal(3, new TemptGoal(this, 1.0D, Ingredient.of(AtmosphericItemTags.COCHINEAL_FOOD), false));

		this.goalSelector.addGoal(6, new CochinealRandomStrollGoal(this, 3.25D));

		this.goalSelector.addGoal(11, new LookAtPlayerGoal(this, Player.class, 10.0F));
	}

	@Override
	protected float getJumpPower() {
		if (!this.horizontalCollision && (!this.moveControl.hasWanted() || !(this.moveControl.getWantedY() > this.getY() + 0.5D))) {
			Path path = this.navigation.getPath();
			if (path != null && !path.isDone()) {
				Vec3 vec3 = path.getNextEntityPos(this);
				if (vec3.y > this.getY() + 0.5D) {
					return 1.0F;
				}
			}

			return this.moveControl.getSpeedModifier() <= 0.6D ? 0.7F : 0.8F;
		} else {
			return 1.0F;
		}
	}

	@Override
	public boolean isFood(ItemStack stack) {
		return stack.is(AtmosphericItemTags.COCHINEAL_FOOD);
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 10.0D).add(Attributes.MOVEMENT_SPEED, 0.5D);
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
	public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob mob) {
		return AtmosphericEntityTypes.COCHINEAL.get().create(level);
	}

	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(DATA_SADDLE_ID, false);
	}

	public void addAdditionalSaveData(CompoundTag tag) {
		super.addAdditionalSaveData(tag);
		tag.putBoolean("Saddle", this.isSaddled());
	}

	public void readAdditionalSaveData(CompoundTag tag) {
		super.readAdditionalSaveData(tag);
		this.setSaddle(tag.getBoolean("Saddle"));
	}

	public void setSaddle(boolean saddle) {
		this.entityData.set(DATA_SADDLE_ID, saddle);
	}

	@Override
	public boolean isSaddled() {
		return this.entityData.get(DATA_SADDLE_ID);
	}

	@Override
	public InteractionResult mobInteract(Player player, InteractionHand hand) {
		boolean flag = this.isFood(player.getItemInHand(hand));
		if (!flag && this.isSaddled() && !this.isVehicle() && !player.isSecondaryUseActive()) {
			if (!this.level.isClientSide) {
				player.startRiding(this);
			}

			return InteractionResult.sidedSuccess(this.level.isClientSide);
		} else {
			InteractionResult interactionresult = super.mobInteract(player, hand);
			if (!interactionresult.consumesAction()) {
				ItemStack itemstack = player.getItemInHand(hand);
				return itemstack.is(Items.SADDLE) ? itemstack.interactLivingEntity(player, this, hand) : InteractionResult.PASS;
			} else {
				return interactionresult;
			}
		}
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
			this.jumpDuration = 10;
			this.jumpTicks = 0;
		} else {
			super.handleEntityEvent(id);
		}
	}

	@Override
	protected void jumpFromGround() {
		super.jumpFromGround();
		double speedMod = this.moveControl.getSpeedModifier();
		if (speedMod > 0.0D) {
			double d1 = this.getDeltaMovement().horizontalDistanceSqr();
			if (d1 < 0.01D) {
				this.moveRelative(0.1F, new Vec3(0.0D, 0.0D, 1.0D));
			}
		}

		if (!this.level.isClientSide) {
			this.level.broadcastEntityEvent(this, (byte) 1);
		}
	}

	@Override
	public void travel(Vec3 vec3) {
		super.travel(vec3);
	}

	@Override
	public void aiStep() {
		super.aiStep();
		if (this.jumpTicks != this.jumpDuration) {
			++this.jumpTicks;
		} else if (this.jumpDuration != 0) {
			this.jumpTicks = 0;
			this.jumpDuration = 0;
			this.setJumping(false);
		}

		Vec3 vec3 = this.getDeltaMovement();
		if (!this.onGround && vec3.y < 0.0D) {
			this.setDeltaMovement(vec3.multiply(0.8D, 0.7D, 0.8D));
		}

		if (!this.onGround && this.level.isClientSide) {
			boolean cold = this.level.getBiome(this.blockPosition()).get().coldEnoughToSnow(this.blockPosition());
			this.level.addParticle(cold ? AtmosphericParticleTypes.COLD_COCHINEAL_TRAIL.get() : AtmosphericParticleTypes.COCHINEAL_TRAIL.get(), this.getRandomX(0.5D), this.getRandomY(), this.getRandomZ(0.5D), 0.0D, 0.0D, 0.0D);
		}
	}

	public void customServerAiStep() {
		if (this.jumpDelayTicks > 0) {
			--this.jumpDelayTicks;
		}

		if (this.onGround) {
			if (!this.wasOnGround) {
				this.setJumping(false);
				this.checkLandingDelay();
			}

			CochinealJumpControl jumpControl = (CochinealJumpControl) this.jumpControl;
			if (!jumpControl.wantJump()) {
				if (this.moveControl.hasWanted() && this.jumpDelayTicks == 0) {
					Path path = this.navigation.getPath();
					Vec3 vec3 = new Vec3(this.moveControl.getWantedX(), this.moveControl.getWantedY(), this.moveControl.getWantedZ());
					if (path != null && !path.isDone()) {
						vec3 = path.getNextEntityPos(this);
					}

					this.facePoint(vec3.x, vec3.z);
					this.startJumping();
				}
			} else if (!jumpControl.canJump()) {
				this.enableJumpControl();
			}
		}

		this.wasOnGround = this.onGround;
	}

	public void setSpeedModifier(double modifier) {
		this.getNavigation().setSpeedModifier(modifier);
		this.moveControl.setWantedPosition(this.moveControl.getWantedX(), this.moveControl.getWantedY(), this.moveControl.getWantedZ(), modifier);
	}

	private void facePoint(double p_29687_, double p_29688_) {
		this.setYRot((float) (Mth.atan2(p_29688_ - this.getZ(), p_29687_ - this.getX()) * (double) (180F / (float) Math.PI)) - 90.0F);
	}

	private void enableJumpControl() {
		((CochinealJumpControl) this.jumpControl).setCanJump(true);
	}

	private void disableJumpControl() {
		((CochinealJumpControl) this.jumpControl).setCanJump(false);
	}

	public boolean canSpawnSprintParticle() {
		return false;
	}

	private void setLandingDelay() {
		if (this.moveControl.getSpeedModifier() < 2.2D) {
			this.jumpDelayTicks = 10;
		} else {
			this.jumpDelayTicks = 1;
		}
	}

	private void checkLandingDelay() {
		this.setLandingDelay();
		this.disableJumpControl();
	}

	public void startJumping() {
		this.setJumping(true);
		this.jumpDuration = 10;
		this.jumpTicks = 0;
	}

	@Override
	public boolean causeFallDamage(float p_148875_, float p_148876_, DamageSource p_148877_) {
		return false;
	}

	public static class CochinealJumpControl extends JumpControl {
		private final Cochineal cochineal;
		private boolean canJump;

		public CochinealJumpControl(Cochineal p_186229_) {
			super(p_186229_);
			this.cochineal = p_186229_;
		}

		public boolean wantJump() {
			return this.jump;
		}

		public boolean canJump() {
			return this.canJump;
		}

		public void setCanJump(boolean p_29759_) {
			this.canJump = p_29759_;
		}

		public void tick() {
			if (this.jump) {
				this.cochineal.startJumping();
				this.jump = false;
			}
		}
	}

	static class CochinealMoveControl extends MoveControl {
		private final Cochineal cochineal;
		private double nextJumpSpeed;

		public CochinealMoveControl(Cochineal p_29766_) {
			super(p_29766_);
			this.cochineal = p_29766_;
		}

		public void tick() {
			if (this.cochineal.onGround && !this.cochineal.jumping && !((CochinealJumpControl) this.cochineal.jumpControl).wantJump()) {
				this.cochineal.setSpeedModifier(0.0D);
			} else if (this.hasWanted()) {
				this.cochineal.setSpeedModifier(this.nextJumpSpeed);
			}

			super.tick();
		}

		public void setWantedPosition(double p_29769_, double p_29770_, double p_29771_, double p_29772_) {
			if (this.cochineal.isInWater()) {
				p_29772_ = 1.5D;
			}

			super.setWantedPosition(p_29769_, p_29770_, p_29771_, p_29772_);
			if (p_29772_ > 0.0D) {
				this.nextJumpSpeed = p_29772_;
			}

		}
	}

	static class CochinealPanicGoal extends PanicGoal {
		private final Cochineal cochineal;

		public CochinealPanicGoal(Cochineal cochineal, double p_29776_) {
			super(cochineal, p_29776_);
			this.cochineal = cochineal;
		}

		public void tick() {
			super.tick();
			this.cochineal.setSpeedModifier(this.speedModifier);
		}
	}
}
