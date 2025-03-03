package com.teamabnormals.atmospheric.common.entity;

import com.google.common.collect.Lists;
import com.teamabnormals.atmospheric.core.other.tags.AtmosphericBiomeTags;
import com.teamabnormals.atmospheric.core.registry.AtmosphericEntityTypes;
import com.teamabnormals.atmospheric.core.registry.AtmosphericItems;
import com.teamabnormals.atmospheric.core.registry.AtmosphericRegistries;
import com.teamabnormals.atmospheric.core.registry.AtmosphericSoundEvents;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
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
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.PlayMessages;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class Tetra extends AbstractSchoolingFish implements VariantHolder<TetraVariant> {
	public static final String BUCKET_VARIANT_TAG = "TetraVariant";
	private static final EntityDataAccessor<String> DATA_ID_TYPE_VARIANT = SynchedEntityData.defineId(Tetra.class, EntityDataSerializers.STRING);

	public Tetra(EntityType<? extends Tetra> p_30015_, Level p_30016_) {
		super(p_30015_, p_30016_);
	}

	public Tetra(PlayMessages.SpawnEntity message, Level level) {
		this(AtmosphericEntityTypes.TETRA.get(), level);
	}

	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(DATA_ID_TYPE_VARIANT, TetraVariant.NEON.location().toString());
	}

	@Override
	public void addAdditionalSaveData(CompoundTag tag) {
		super.addAdditionalSaveData(tag);
		tag.putString("Variant", this.getStringVariant());
	}

	@Override
	public void readAdditionalSaveData(CompoundTag tag) {
		super.readAdditionalSaveData(tag);
		this.setStringVariant(tag.getString("Variant"));
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new PanicGoal(this, 2.25D));
		this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, Player.class, 8.0F, 2.6D, 2.4D, EntitySelector.NO_CREATIVE_OR_SPECTATOR::test));
		this.goalSelector.addGoal(4, new TetraSwimGoal(this));
		this.goalSelector.addGoal(5, new FollowFlockLeaderGoal(this));
	}

	public String getStringVariant() {
		return !this.entityData.get(DATA_ID_TYPE_VARIANT).isEmpty() ? this.entityData.get(DATA_ID_TYPE_VARIANT) : TetraVariant.NEON.location().toString();
	}

	private void setStringVariant(String var) {
		this.entityData.set(DATA_ID_TYPE_VARIANT, var);
	}

	@Override
	public void setVariant(TetraVariant variant) {
		this.setVariant(this.level().registryAccess().registryOrThrow(AtmosphericRegistries.TETRA_VARIANT).getKey(variant));
	}

	@Override
	public TetraVariant getVariant() {
		return this.level().registryAccess().registryOrThrow(AtmosphericRegistries.TETRA_VARIANT).get(new ResourceLocation(this.getStringVariant()));
	}

	public void setVariant(ResourceLocation variant) {
		this.setStringVariant(variant.toString());
	}

	public void saveToBucketTag(ItemStack stack) {
		super.saveToBucketTag(stack);
		CompoundTag tag = stack.getOrCreateTag();
		tag.putString(BUCKET_VARIANT_TAG, this.getStringVariant());
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
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData data, @Nullable CompoundTag tag) {
		data = super.finalizeSpawn(level, difficulty, spawnType, data, tag);
		if (spawnType == MobSpawnType.BUCKET && tag != null && tag.contains(BUCKET_VARIANT_TAG, Tag.TAG_STRING)) {
			this.setStringVariant(tag.getString(BUCKET_VARIANT_TAG));
			return data;
		} else {
			RandomSource random = level.getRandom();
			TetraVariant tetraVariant;
			if (data instanceof TetraGroupData tetraData) {
				tetraVariant = tetraData.variant;
			} else {
				Registry<TetraVariant> registry = level.registryAccess().registryOrThrow(AtmosphericRegistries.TETRA_VARIANT);
				ArrayList<TetraVariant> weightedVariants = Lists.newArrayList();
				for (TetraVariant variant : registry.stream().toList()) {
					for (int i = 0; i < variant.weight(); i++)
						weightedVariants.add(variant);
				}

				tetraVariant = Util.getRandom(weightedVariants, random);
				data = new TetraGroupData(this, tetraVariant);
			}

			this.setVariant(tetraVariant);
			return data;
		}
	}

	public static boolean checkTetraSpawnRules(EntityType<Tetra> tetra, LevelAccessor level, MobSpawnType type, BlockPos pos, RandomSource random) {
		return level.getFluidState(pos.below()).is(FluidTags.WATER) && level.getBlockState(pos.above()).is(Blocks.WATER) && WaterAnimal.checkSurfaceWaterAnimalSpawnRules(tetra, level, type, pos, random);
	}

	static class TetraGroupData extends AbstractSchoolingFish.SchoolSpawnGroupData {
		final TetraVariant variant;

		TetraGroupData(Tetra leader, TetraVariant variant) {
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

			for (int i = 0; vec3 != null && !mob.level().getBlockState(BlockPos.containing(vec3)).isPathfindable(mob.level(), BlockPos.containing(vec3), PathComputationType.WATER) && i++ < 10; vec3 = getPos(mob, xz, y)) {
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