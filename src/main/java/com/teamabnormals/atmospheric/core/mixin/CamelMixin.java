package com.teamabnormals.atmospheric.core.mixin;

import com.teamabnormals.atmospheric.common.entity.CamelVariant;
import com.teamabnormals.atmospheric.core.other.tags.AtmosphericBiomeTags;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.network.syncher.SynchedEntityData.Builder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.camel.Camel;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Camel.class)
public class CamelMixin extends AbstractHorse implements VariantHolder<CamelVariant> {
	@Unique
	private static final EntityDataAccessor<Integer> DATA_TYPE_ID = SynchedEntityData.defineId(Camel.class, EntityDataSerializers.INT);

	protected CamelMixin(EntityType<? extends AbstractHorse> entity, Level level) {
		super(entity, level);
	}

	@Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
	private void addAdditionalSaveData(CompoundTag tag, CallbackInfo ci) {
		tag.putInt("CamelType", this.getVariant().id());
	}

	@Inject(method = "defineSynchedData", at = @At("TAIL"))
	private void defineSynchedData(Builder builder, CallbackInfo ci) {
		builder.define(DATA_TYPE_ID, CamelVariant.DESERT.id());
	}

	@Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
	private void readAdditionalSaveData(CompoundTag tag, CallbackInfo ci) {
		this.setVariant(CamelVariant.byId(tag.getInt("CamelType")));
	}

	@Inject(method = "finalizeSpawn", at = @At("HEAD"))
	private void readAdditionalSaveData(ServerLevelAccessor level, DifficultyInstance p_251264_, MobSpawnType p_250254_, SpawnGroupData p_249259_, CallbackInfoReturnable<SpawnGroupData> cir) {
		if (level.getBiome(this.blockPosition()).is(AtmosphericBiomeTags.SPAWNS_ARID_CAMELS)) {
			this.setVariant(CamelVariant.ARID);
		}
	}

	@Inject(method = "getBreedOffspring(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/AgeableMob;)Lnet/minecraft/world/entity/AgeableMob;", at = @At("RETURN"), cancellable = true)
	private void getBreedOffspring(ServerLevel level, AgeableMob ageableMob, CallbackInfoReturnable<Camel> cir) {
		Camel camel = cir.getReturnValue();

		if (ageableMob instanceof Camel camelParent) {
			VariantHolder<CamelVariant> child = ((VariantHolder<CamelVariant>) camel);
			VariantHolder<CamelVariant> parent = ((VariantHolder<CamelVariant>) camelParent);

			CamelVariant variant = this.getVariant();
			CamelVariant parentVariant = parent.getVariant();

			if (variant == parentVariant) {
				child.setVariant(variant);
			} else if (variant.id() + parentVariant.id() == 1) {
				child.setVariant(CamelVariant.HYBRID);
			} else {
				child.setVariant(this.random.nextBoolean() ? variant : parentVariant);
			}

			cir.setReturnValue((Camel) child);
		}
	}

	@Override
	public void setVariant(CamelVariant variant) {
		this.entityData.set(DATA_TYPE_ID, variant.id());
	}

	@Override
	public CamelVariant getVariant() {
		return CamelVariant.byId(this.entityData.get(DATA_TYPE_ID));
	}
}