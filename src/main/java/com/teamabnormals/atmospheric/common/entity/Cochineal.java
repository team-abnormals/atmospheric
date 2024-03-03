package com.teamabnormals.atmospheric.common.entity;

import com.teamabnormals.atmospheric.common.entity.ai.goal.*;
import com.teamabnormals.atmospheric.core.other.tags.AtmosphericBlockTags;
import com.teamabnormals.atmospheric.core.other.tags.AtmosphericItemTags;
import com.teamabnormals.atmospheric.core.registry.AtmosphericEntityTypes;
import com.teamabnormals.atmospheric.core.registry.AtmosphericParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.core.Direction.Plane;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
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
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.network.PlayMessages;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class Cochineal extends Animal implements Saddleable {
	private static final EntityDimensions SUCKLING_DIMENSIONS = EntityDimensions.scalable(0.8F, 1.25F);

	private static final EntityDataAccessor<Boolean> IS_SADDLED = SynchedEntityData.defineId(Cochineal.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Boolean> IS_LEAPING = SynchedEntityData.defineId(Cochineal.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Optional<BlockPos>> CACTUS_POS = SynchedEntityData.defineId(Cochineal.class, EntityDataSerializers.OPTIONAL_BLOCK_POS);
	private static final EntityDataAccessor<Direction> CACTUS_SIDE = SynchedEntityData.defineId(Cochineal.class, EntityDataSerializers.DIRECTION);
	private static final EntityDataAccessor<ItemStack> EATING_STACK = SynchedEntityData.defineId(Cochineal.class, EntityDataSerializers.ITEM_STACK);

	private boolean wasOnGroundOrFluid;
	private boolean jumpingQuickly;
	private boolean superInLove;
	private int jumpDelayTicks;
	private int suckleCooldown;

	private boolean jumpAnim;
	private float jumpAmount;
	private float jumpAmount0;

	public Cochineal(EntityType<? extends Cochineal> entity, Level level) {
		super(entity, level);
		this.moveControl = new CochinealMoveControl(this);
	}

	public Cochineal(PlayMessages.SpawnEntity message, Level level) {
		this(AtmosphericEntityTypes.COCHINEAL.get(), level);
	}

	protected void registerGoals() {
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(1, new CochinealPanicGoal(this));
		this.goalSelector.addGoal(2, new CochinealBreedGoal(this, 1.2D));
		this.goalSelector.addGoal(3, new CochinealTemptGoal(this, 1.2D, Ingredient.of(AtmosphericItemTags.COCHINEAL_FOOD)));
		this.goalSelector.addGoal(4, new CochinealEatDragonFruitGoal(this, 1.2D));
		this.goalSelector.addGoal(5, new CochinealDetachFromCactusGoal(this));
		this.goalSelector.addGoal(6, new CochinealAttachToCactusGoal(this));
		this.goalSelector.addGoal(7, new CochinealRandomSwimGoal(this, 1.2D));
		this.goalSelector.addGoal(8, new CochinealRandomHopGoal(this));
		this.goalSelector.addGoal(9, new LookAtPlayerGoal(this, Player.class, 10.0F));
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

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(IS_SADDLED, false);
		this.entityData.define(IS_LEAPING, false);
		this.entityData.define(CACTUS_POS, Optional.empty());
		this.entityData.define(CACTUS_SIDE, Direction.SOUTH);
		this.entityData.define(EATING_STACK, ItemStack.EMPTY);
	}

	@Override
	public void addAdditionalSaveData(CompoundTag tag) {
		super.addAdditionalSaveData(tag);
		tag.putBoolean("SuperInLove", this.superInLove);
		tag.putBoolean("Saddle", this.isSaddled());
		if (this.isAttachedToCactus()) {
			tag.put("CactusPos", NbtUtils.writeBlockPos(this.getCactusPos()));
			tag.putString("CactusSide", this.getCactusSide().getName());
		}
	}

	@Override
	public void readAdditionalSaveData(CompoundTag tag) {
		super.readAdditionalSaveData(tag);
		this.superInLove = tag.getBoolean("SuperInLove");
		this.setSaddle(tag.getBoolean("Saddle"));
		if (tag.contains("CactusPos")) {
			this.setCactusPos(NbtUtils.readBlockPos(tag.getCompound("CactusPos")));
			this.setCactusSide(Direction.byName(tag.getString("CactusSide")));
		}
	}

	@Override
	public boolean isSaddled() {
		return this.entityData.get(IS_SADDLED);
	}

	public void setSaddle(boolean saddle) {
		this.entityData.set(IS_SADDLED, saddle);
	}

	public boolean isLeaping() {
		return this.entityData.get(IS_LEAPING);
	}

	public void setLeaping(boolean leaping) {
		this.entityData.set(IS_LEAPING, leaping);
	}

	public void setJumpingQuickly(boolean jumpingQuickly) {
		this.jumpingQuickly = jumpingQuickly;
	}

	public boolean isSuperInLove() {
		return this.superInLove;
	}

	public void setSuperInLove(boolean superInLove) {
		this.superInLove = superInLove;
	}

	public BlockPos getCactusPos() {
		return this.entityData.get(CACTUS_POS).orElse(null);
	}

	public void setCactusPos(BlockPos pos) {
		this.entityData.set(CACTUS_POS, Optional.ofNullable(pos));
	}

	public Direction getCactusSide() {
		return this.entityData.get(CACTUS_SIDE);
	}

	public void setCactusSide(Direction side) {
		this.entityData.set(CACTUS_SIDE, side);
	}

	public ItemStack getEatingStack() {
		return this.entityData.get(EATING_STACK);
	}

	public void setEatingStack(ItemStack stack) {
		this.entityData.set(EATING_STACK, stack);
	}

	public boolean isOnSuckleCooldown() {
		return this.suckleCooldown > 0;
	}

	public boolean isAttachedToCactus() {
		return this.getCactusPos() != null;
	}

	public void attachToCactus(BlockPos cactusPos, Direction cactusSide) {
		this.setCactusPos(cactusPos);
		this.setCactusSide(cactusSide);
		this.setDeltaMovement(Vec3.ZERO);

		this.setPos(this.getCactusAttachPoint(cactusPos, cactusSide));
		this.ejectPassengers();

		this.setDiscardFriction(false);
		this.setLeaping(false);
		this.suckleCooldown = 200;
	}

	public void detachFromCactus() {
		this.setCactusPos(null);
		this.suckleCooldown = 100;
	}

	private Vec3 getCactusAttachPoint(BlockPos cactusPos, Direction cactusSide) {
		VoxelShape shape = this.level.getBlockState(cactusPos).getCollisionShape(this.level, cactusPos);
		double x = cactusPos.getX();
		double z = cactusPos.getZ();
		if (cactusSide.getAxis() == Axis.X) {
			x += cactusSide == Direction.WEST ? shape.min(Axis.X) : shape.max(Axis.X);
			z += 0.5D;
			x += (this.getBbWidth() * 0.5D) * cactusSide.getStepX();
		} else {
			x += 0.5D;
			z += cactusSide == Direction.NORTH ? shape.min(Axis.Z) : shape.max(Axis.Z);
			z += (this.getBbWidth() * 0.5D) * cactusSide.getStepZ();
		}
		return new Vec3(x, cactusPos.getY() + 0.5D - this.getBbHeight() * 0.5D, z);
	}

	public boolean isSuckleable(BlockPos pos) {
		return this.level.getBlockState(pos).is(AtmosphericBlockTags.COCHINEALS_CAN_FEED_ON);
	}

	public Direction getClosestVisibleCactusFace(BlockPos cactusPos) {
		Direction closestdir = null;
		double closestdist = Double.MAX_VALUE;

		for (Direction direction : Plane.HORIZONTAL) {
			BlockPos sidepos = cactusPos.relative(direction);
			Vec3 vec3 = new Vec3(this.getX(), this.getEyeY(), this.getZ());
			Vec3 vec31 = new Vec3(sidepos.getX() + 0.5D, sidepos.getY() + 0.5D, sidepos.getZ() + 0.5D);
			if (this.hasSpaceOnCactusSide(cactusPos, direction) && this.level.clip(new ClipContext(vec3, vec31, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, this)).getType() == HitResult.Type.MISS) {
				double distance = this.distanceToSqr(sidepos.getX(), sidepos.getY(), sidepos.getZ());
				if (distance < closestdist) {
					closestdir = direction;
					closestdist = distance;
				}
			}
		}

		return closestdir;
	}

	public boolean hasSpaceOnCactusSide(BlockPos cactusPos, Direction cactusSide) {
		AABB aabb = new AABB(0.0D, 0.5D - this.getBbHeight() * 0.5D, 0.0D, 1.0D, 0.5D + this.getBbHeight() * 0.5D, 1.0D).deflate(1.0E-6D);
		VoxelShape shape = this.level.getBlockState(cactusPos).getCollisionShape(this.level, cactusPos);

		double x = cactusSide == Direction.WEST ? shape.min(Axis.X) - 1.0D : cactusSide == Direction.EAST ? shape.max(Axis.X) : 0.0D;
		double z = cactusSide == Direction.NORTH ? shape.min(Axis.Z) - 1.0D : cactusSide == Direction.SOUTH ? shape.max(Axis.Z) : 0.0D;
		aabb = aabb.move(cactusPos.getX() + x, cactusPos.getY(), cactusPos.getZ() + z);

		for (VoxelShape voxelshape : this.level.getBlockCollisions(this, aabb))
			if (!voxelshape.isEmpty())
				return false;

		for (Cochineal cochineal : this.level.getEntitiesOfClass(Cochineal.class, aabb))
			if (cochineal != this && cochineal.isAttachedToCactus())
				return false;

		return true;
	}

	@Override
	public InteractionResult mobInteract(Player player, InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);
		if (this.isFood(stack)) {
			int i = this.getAge();
			if (!this.level.isClientSide && i == 0 && this.canFallInLove()) {
				this.usePlayerItem(player, hand, stack);
				this.setInLove(player);
				if (stack.is(AtmosphericItemTags.COCHINEAL_SUPER_LOVE_FOOD))
					this.setSuperInLove(true);
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
		} else if (this.isSaddled() && !this.isVehicle() && !player.isSecondaryUseActive() && !this.isAttachedToCactus()) {
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
	public void tick() {
		super.tick();

		if (this.jumpDelayTicks > 0) {
			--this.jumpDelayTicks;
		}

		if ((this.onGround || this.isInFluidType()) && !this.wasOnGroundOrFluid) {
			this.setDiscardFriction(false);
			this.setLeaping(false);
			this.jumpDelayTicks = this.jumpingQuickly ? 2 : 10;
		}

		this.wasOnGroundOrFluid = this.onGround || this.isInFluidType();

		if (this.isAttachedToCactus()) {
			if (this.isSuckleable(this.getCactusPos()) && this.distanceToSqr(this.getCactusAttachPoint(this.getCactusPos(), this.getCactusSide())) < 0.2D) {
				this.setDeltaMovement(Vec3.ZERO);
				this.setYRot(this.getCactusSide().getOpposite().toYRot());
				this.yHeadRot = this.getYRot();
				this.yBodyRot = this.getYRot();
			} else if (!this.level.isClientSide) {
				this.detachFromCactus();
			}
		}
	}

	@Override
	public void aiStep() {
		if (!this.level.isClientSide && this.isAlive() && this.getAge() < 0 && !this.isAttachedToCactus())
			this.setAge(this.age - 1);

		super.aiStep();

		if (this.getInLoveTime() == 0)
			this.setSuperInLove(false);

		if (!this.level.isClientSide && this.isAlive() && this.getHealth() < this.getMaxHealth() && this.tickCount % 120 == 0 && this.isAttachedToCactus())
			this.heal(1.0F);

		if (this.suckleCooldown > 0)
			this.suckleCooldown--;

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
			for (int i = 0; i < 3; i++) {
				double x = -this.getLookAngle().x * 0.7D + (this.random.nextDouble() - 0.5D) * 0.6D;
				double y = 0.6D + (this.random.nextDouble() - 0.5D) * 0.6D;
				double z = -this.getLookAngle().z * 0.7D + (this.random.nextDouble() - 0.5D) * 0.6D;

				if (this.isBaby()) {
					x *= 0.4D;
					y *= 0.4D;
					z *= 0.4D;
				}

				this.level.addParticle(cold ? AtmosphericParticleTypes.COLD_COCHINEAL_TRAIL.get() : AtmosphericParticleTypes.COCHINEAL_TRAIL.get(), this.getX() + x, this.getY() + y, this.getZ() + z, 0.0D, 0.0D, 0.0D);
			}
		}

		if (!this.getEatingStack().isEmpty() && this.tickCount % 20 == 0) {
			this.playSound(SoundEvents.GENERIC_EAT, 0.5F + 0.5F * (float) this.random.nextInt(2), (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
			if (this.level.isClientSide) {
				for (int i = 0; i < 6; ++i) {
					Vec3 vec3 = new Vec3((this.random.nextFloat() - 0.5D) * 0.1D, Math.random() * 0.1D + 0.1D, (this.random.nextFloat() - 0.5D) * 0.1D);
					vec3 = vec3.xRot(-this.getXRot() * Mth.DEG_TO_RAD);
					vec3 = vec3.yRot(-this.getYRot() * Mth.DEG_TO_RAD);
					double d0 = (double) (-this.random.nextFloat()) * 0.6D - 0.3D;
					Vec3 vec31 = new Vec3((this.random.nextFloat() - 0.5D) * 0.8D, d0, 0.8D + (this.random.nextFloat() - 0.5D) * 0.4D);
					vec31 = vec31.yRot(-this.yBodyRot * Mth.DEG_TO_RAD);
					vec31 = vec31.add(this.getX(), this.getY() + 1.0F, this.getZ());
					this.level.addParticle(new ItemParticleOption(ParticleTypes.ITEM, this.getEatingStack()), vec31.x, vec31.y, vec31.z, vec3.x, vec3.y + 0.05D, vec3.z);
				}
			}
		}
	}

	@Override
	public void knockback(double force, double x, double z) {
		super.knockback(force, x, z);
		this.detachFromCactus();
	}

	protected void leap(double jumpPower) {
		Vec3 vec3 = this.getDeltaMovement();
		this.setDeltaMovement(vec3.x, jumpPower + this.getJumpBoostPower(), vec3.z);
		double speed = ((CochinealMoveControl) this.moveControl).leapSpeed;
		this.moveRelative((float) speed, new Vec3(0.0D, 0.0D, 1.0D));
		this.hasImpulse = true;
		this.level.broadcastEntityEvent(this, (byte) 1);
		this.setLeaping(true);
		net.minecraftforge.common.ForgeHooks.onLivingJump(this);
	}

	public boolean canLeap() {
		return this.onGround && !this.isLeaping() && !((CochinealMoveControl) this.moveControl).wantsToLeap && this.jumpDelayTicks == 0;
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
	public float getScale() {
		return this.isBaby() ? 0.4F : 1.0F;
	}

	@Override
	public EntityDimensions getDimensions(Pose pose) {
		if (this.isAttachedToCactus()) {
			return SUCKLING_DIMENSIONS.scale(this.getScale());
		} else {
			return super.getDimensions(pose);
		}
	}

	@Override
	protected void ageBoundaryReached() {
		if (!this.isBaby() && this.isAttachedToCactus() && !this.hasSpaceOnCactusSide(this.getCactusPos(), this.getCactusSide()))
			this.detachFromCactus();
	}

	@Override
	public boolean isPushable() {
		return !this.isAttachedToCactus() && super.isPushable();
	}

	@Override
	protected void doPush(Entity entity) {
		if (!this.isAttachedToCactus())
			super.doPush(entity);
	}

	@Override
	protected void pushEntities() {
		if (!this.isAttachedToCactus())
			super.pushEntities();
	}

	@Override
	public boolean canSpawnSprintParticle() {
		return false;
	}

	@Override
	public boolean causeFallDamage(float distance, float damageMultiplier, DamageSource source) {
		return false;
	}

	@Override
	public void onSyncedDataUpdated(EntityDataAccessor<?> key) {
		if (CACTUS_POS.equals(key))
			this.refreshDimensions();
		super.onSyncedDataUpdated(key);
	}

	@Override
	public MoveControl getMoveControl() {
		return this.moveControl;
	}

	@Override
	protected PathNavigation createNavigation(Level level) {
		return new CochinealNavigation(this, level);
	}

	static class CochinealNavigation extends GroundPathNavigation {
		private final Cochineal cochineal;

		CochinealNavigation(Cochineal cochineal, Level level) {
			super(cochineal, level);
			this.cochineal = cochineal;
		}

		@Override
		public boolean isStableDestination(BlockPos pos) {
			return !this.cochineal.isInFluidType() ? this.level.getBlockState(pos.below()).entityCanStandOn(this.level, pos.below(), this.mob) : super.isStableDestination(pos);
		}
	}

	public static class CochinealMoveControl extends MoveControl {
		private final Cochineal cochineal;
		private double jumpX;
		private double jumpY;
		private double jumpZ;
		private boolean wantsToLeap;
		private int justLeapedTime;
		private double leapSpeed;

		public CochinealMoveControl(Cochineal cochineal) {
			super(cochineal);
			this.cochineal = cochineal;
		}

		@Override
		public void tick() {
			if (this.cochineal.isInFluidType()) {
				super.tick();
				this.wantsToLeap = false;
				return;
			}

			if (this.cochineal.isLeaping()) {
				Vec3 vec3 = new Vec3(this.jumpX - this.mob.getX(), 0.0D, this.jumpZ - this.mob.getZ()).normalize();
				double d0 = this.cochineal.getDeltaMovement().horizontalDistance();

				if (this.justLeapedTime > 0) {
					--this.justLeapedTime;
					if (d0 < this.leapSpeed) {
						Vec3 vec31 = vec3.scale(Math.min(0.2D, this.leapSpeed - d0));
						this.cochineal.setDeltaMovement(this.cochineal.getDeltaMovement().add(vec31));
					}
				} else if (d0 < Math.min(0.1D, this.leapSpeed)) {
					Vec3 vec31 = vec3.scale(Math.min(0.04D, Math.min(0.1D, this.leapSpeed) - d0));
					this.cochineal.setDeltaMovement(this.cochineal.getDeltaMovement().add(vec31));
				}

				this.cochineal.setDiscardFriction(true);
				this.stopSwimmingNaviation();
			} else if (this.cochineal.onGround) {
				if (this.wantsToLeap && this.cochineal.jumpDelayTicks == 0) {
					if (this.canReach(this.jumpX, this.jumpY, this.jumpZ)) {
						double dx = this.jumpX - this.mob.getX();
						double dy = this.jumpY - this.mob.getY();
						double dz = this.jumpZ - this.mob.getZ();
						double distance = Math.sqrt(dx * dx + dz * dz);

						this.faceJumpPoint();

						double jumppower = this.cochineal.jumpingQuickly ? Mth.clamp(0.4D + dy * 0.05D, 0.4D, 0.8D) : Mth.clamp(0.5D + dy * 0.05D, 0.4D, 0.8D);
						jumppower += this.cochineal.getJumpBoostPower();

						this.leapSpeed = this.calculateJumpSpeed(distance, dy, jumppower);
						this.cochineal.leap(jumppower);

						this.justLeapedTime = 3;
					}
					this.wantsToLeap = false;
				}
				this.stopSwimmingNaviation();
			}
		}

		public void leapTo(double x, double y, double z) {
			this.jumpX = x;
			this.jumpY = y;
			this.jumpZ = z;
			this.wantsToLeap = true;
			this.faceJumpPoint();
		}

		private void stopSwimmingNaviation() {
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

		private void faceJumpPoint() {
			this.cochineal.setYRot((float) (Mth.atan2(this.jumpZ - this.cochineal.getZ(), this.jumpX - this.cochineal.getX()) * Mth.RAD_TO_DEG) - 90.0F);
			this.cochineal.yBodyRot = this.cochineal.getYRot();
			this.cochineal.yHeadRot = this.cochineal.getYRot();
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