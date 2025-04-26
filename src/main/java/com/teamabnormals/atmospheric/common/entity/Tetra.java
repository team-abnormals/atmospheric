package com.teamabnormals.atmospheric.common.entity;

import com.teamabnormals.atmospheric.core.other.tags.AtmosphericBiomeTags;
import com.teamabnormals.atmospheric.core.registry.AtmosphericDataSerializers;
import com.teamabnormals.atmospheric.core.registry.AtmosphericItems;
import com.teamabnormals.atmospheric.core.registry.AtmosphericRegistries;
import com.teamabnormals.atmospheric.core.registry.AtmosphericSoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.FollowFlockLeaderGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.entity.animal.AbstractSchoolingFish;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

public class Tetra extends AbstractSchoolingFish implements VariantHolder<Holder<TetraVariant>> {
	public static final String BUCKET_VARIANT_TAG = "BucketVariantTag";
	private static final EntityDataAccessor<Holder<TetraVariant>> VARIANT = SynchedEntityData.defineId(Tetra.class, AtmosphericDataSerializers.TETRA_VARIANT.get());

	public Tetra(EntityType<? extends Tetra> p_30015_, Level p_30016_) {
		super(p_30015_, p_30016_);
	}

	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
		Registry<TetraVariant> registry = this.registryAccess().registryOrThrow(AtmosphericRegistries.TETRA_VARIANT);
		builder.define(VARIANT, registry.getHolder(TetraVariant.DEFAULT).or(registry::getAny).orElseThrow());
	}

	@Override
	public void addAdditionalSaveData(CompoundTag tag) {
		super.addAdditionalSaveData(tag);
		this.getVariant().unwrapKey().ifPresent(variant -> tag.putString(BUCKET_VARIANT_TAG, variant.location().toString()));
	}

	@Override
	public void readAdditionalSaveData(CompoundTag tag) {
		super.readAdditionalSaveData(tag);
		Optional.ofNullable(ResourceLocation.tryParse(tag.getString(BUCKET_VARIANT_TAG)))
				.map(loc -> ResourceKey.create(AtmosphericRegistries.TETRA_VARIANT, loc))
				.flatMap(key -> this.registryAccess().registryOrThrow(AtmosphericRegistries.TETRA_VARIANT).getHolder(key))
				.ifPresent(this::setVariant);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new PanicGoal(this, 2.25D));
		this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, Player.class, 8.0F, 2.6D, 2.4D, EntitySelector.NO_CREATIVE_OR_SPECTATOR::test));
		this.goalSelector.addGoal(4, new TetraSwimGoal(this));
		this.goalSelector.addGoal(5, new FollowFlockLeaderGoal(this));
	}

	@Override
	public void setVariant(Holder<TetraVariant> variant) {
		this.entityData.set(VARIANT, variant);
	}

	@Override
	public Holder<TetraVariant> getVariant() {
		return this.entityData.get(VARIANT);
	}

	public void saveToBucketTag(ItemStack stack) {
		super.saveToBucketTag(stack);
		CustomData.update(DataComponents.BUCKET_ENTITY_DATA, stack, tag -> {
			this.getVariant().unwrapKey().ifPresent(variant -> tag.putString(BUCKET_VARIANT_TAG, variant.location().toString()));
		});
	}

	@Override
	public void loadFromBucketTag(CompoundTag tag) {
		super.loadFromBucketTag(tag);
		Optional.ofNullable(ResourceLocation.tryParse(tag.getString(BUCKET_VARIANT_TAG)))
				.map(loc -> ResourceKey.create(AtmosphericRegistries.TETRA_VARIANT, loc))
				.flatMap(key -> this.registryAccess().registryOrThrow(AtmosphericRegistries.TETRA_VARIANT).getHolder(key))
				.ifPresent(this::setVariant);
	}

	public ItemStack getBucketItemStack() {
		return new ItemStack(AtmosphericItems.TETRA_BUCKET.get());
	}

	protected SoundEvent getAmbientSound() {
		return AtmosphericSoundEvents.TETRA_AMBIENT.get();
	}

	protected SoundEvent getDeathSound() {
		return AtmosphericSoundEvents.TETRA_DEATH.get();
	}

	protected SoundEvent getHurtSound(DamageSource p_30039_) {
		return AtmosphericSoundEvents.TETRA_HURT.get();
	}

	protected SoundEvent getFlopSound() {
		return AtmosphericSoundEvents.TETRA_FLOP.get();
	}

	public int getMaxSchoolSize() {
		return 12;
	}

	public int getMaxSpawnClusterSize() {
		return 12;
	}

	public int getMinSchoolSize() {
		return 6;
	}

	public void tick() {
		super.tick();

		if (this.hasFollowers() && this.isFollower()) {
			if (!tryMergeSchools(this, this.leader)) {
				this.stopFollowing();
			}
		}

		if (!this.isSchoolFull() && this.random.nextInt(50) == 0) {
			List<? extends Tetra> list = this.level().getEntitiesOfClass(this.getClass(), this.getBoundingBox().inflate(8.0D, 8.0D, 8.0D),
					tetra -> tetra != this &&
							((tetra.canBeFollowed() && tetra != this.leader) ||
									(!this.isFollower() && !this.hasFollowers() && tetra.schoolSize < tetra.getMaxSchoolSize() && !tetra.isFollower())));

			for (Tetra tetra : list) {
				if ((this.hasFollowers() || this.isFollower()) && tryMergeSchools(this.isFollower() ? this.leader : this, tetra)) {
					break;
				} else if (!this.isFollower() && !this.hasFollowers()) {
					this.startFollowing(tetra);
					break;
				}

			}
		}
	}

	public boolean isSchoolFull() {
		return this.hasFollowers() ? this.schoolSize <= this.getMinSchoolSize() : this.isFollower() && this.leader.schoolSize <= this.getMinSchoolSize();
	}

	public static boolean tryMergeSchools(AbstractSchoolingFish from, AbstractSchoolingFish to) {
		if (from.schoolSize + to.schoolSize <= to.getMaxSchoolSize()) {
			List<? extends Tetra> fromFollowers = from.level().getEntitiesOfClass(Tetra.class, from.getBoundingBox().inflate(8.0D, 8.0D, 8.0D),
					t -> t.isFollower() && t.leader == from);

			fromFollowers.forEach(follower -> {
				follower.stopFollowing();
				follower.startFollowing(to);
			});

			from.startFollowing(to);
			return true;
		}
		return false;
	}

	@Nullable
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData data) {
		data = super.finalizeSpawn(level, difficulty, spawnType, data);

		RandomSource random = level.getRandom();
		Holder<TetraVariant> tetraVariant;
		if (data instanceof TetraGroupData tetraData) {
			tetraVariant = tetraData.variant;
		} else {
			tetraVariant = TetraVariant.getSpawnVariant(level.registryAccess(), random);
			data = new TetraGroupData(this, tetraVariant);
		}

		this.setVariant(tetraVariant);
		return data;
	}

	public static boolean checkTetraSpawnRules(EntityType<Tetra> tetra, LevelAccessor level, MobSpawnType type, BlockPos pos, RandomSource random) {
		return level.getFluidState(pos.below()).is(FluidTags.WATER) && level.getBlockState(pos.above()).is(Blocks.WATER) && WaterAnimal.checkSurfaceWaterAnimalSpawnRules(tetra, level, type, pos, random);
	}

	static class TetraGroupData extends AbstractSchoolingFish.SchoolSpawnGroupData {
		final Holder<TetraVariant> variant;

		TetraGroupData(Tetra leader, Holder<TetraVariant> variant) {
			super(leader);
			this.variant = variant;
		}
	}

	static class TetraSwimGoal extends RandomStrollGoal {
		private final Tetra fish;

		public TetraSwimGoal(Tetra tetra) {
			super(tetra, 1.0D, 40, false);
			this.fish = tetra;
		}

		public boolean canUse() {
			return this.fish.canRandomSwim() && super.canUse();
		}

		@Nullable
		protected Vec3 getPosition() {
			return getRandomSwimmablePos(this.mob, 10, 7);
		}

		@Nullable
		public static Vec3 getRandomSwimmablePos(PathfinderMob mob, int xz, int y) {
			Vec3 vec3 = getPos(mob, xz, y);

			for (int i = 0; vec3 != null && !mob.level().getBlockState(BlockPos.containing(vec3)).isPathfindable(PathComputationType.WATER) && i++ < 10; vec3 = getPos(mob, xz, y)) {
			}

			return vec3;
		}

		@Nullable
		public static Vec3 getPos(PathfinderMob tetra, int xz, int y) {
			Vec3 vec3 = DefaultRandomPos.getPos(tetra, xz, y);

			if (vec3 != null) {
				Level level = tetra.level();
				RandomSource random = tetra.getRandom();

				BlockPos pos = tetra.getOnPos();
				BlockPos newPos = BlockPos.containing(vec3);

				Holder<Biome> oldBiome = level.getBiome(pos);
				Holder<Biome> newBiome = level.getBiome(newPos);

				int newLight = level.getRawBrightness(newPos, 0);

				boolean tooBright = newLight > 5 && random.nextFloat() < newLight / 16.0F;
				boolean wrongBiome = oldBiome.is(AtmosphericBiomeTags.IS_RAINFOREST) && !newBiome.is(AtmosphericBiomeTags.IS_RAINFOREST) && random.nextFloat() < 0.95F;

				if (tooBright || wrongBiome) {
					return getPos(tetra, xz, y);
				}
			}

			return vec3;
		}
	}

	static class TetraFollowFlockLeader extends FollowFlockLeaderGoal {

		public TetraFollowFlockLeader(AbstractSchoolingFish fish) {
			super(fish);
		}
	}
}