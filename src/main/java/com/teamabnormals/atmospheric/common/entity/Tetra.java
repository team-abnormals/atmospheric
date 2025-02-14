package com.teamabnormals.atmospheric.common.entity;

import com.teamabnormals.atmospheric.core.registry.AtmosphericEntityTypes;
import com.teamabnormals.atmospheric.core.registry.AtmosphericItems;
import com.teamabnormals.atmospheric.core.registry.AtmosphericRegistries;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.VariantHolder;
import net.minecraft.world.entity.animal.AbstractSchoolingFish;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.network.PlayMessages;

import javax.annotation.Nullable;

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

	public void addAdditionalSaveData(CompoundTag tag) {
		super.addAdditionalSaveData(tag);
		tag.putString("Variant", this.getStringVariant());
	}

	public void readAdditionalSaveData(CompoundTag tag) {
		super.readAdditionalSaveData(tag);
		this.setStringVariant(tag.getString("Variant"));
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
		return SoundEvents.TROPICAL_FISH_AMBIENT;
	}

	protected SoundEvent getDeathSound() {
		return SoundEvents.TROPICAL_FISH_DEATH;
	}

	protected SoundEvent getHurtSound(DamageSource p_30039_) {
		return SoundEvents.TROPICAL_FISH_HURT;
	}

	protected SoundEvent getFlopSound() {
		return SoundEvents.TROPICAL_FISH_FLOP;
	}

	public int getMaxSpawnClusterSize() {
		return 12;
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
				tetraVariant = random.nextFloat() < 0.9D ? registry.get(TetraVariant.NEON) : Util.getRandom(registry.stream().toList(), random);
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
}