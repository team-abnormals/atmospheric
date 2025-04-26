package com.teamabnormals.atmospheric.core.mixin;

import com.teamabnormals.atmospheric.common.entity.CamelVariant;
import com.teamabnormals.atmospheric.core.registry.AtmosphericDataSerializers;
import com.teamabnormals.atmospheric.core.registry.AtmosphericRegistries;
import com.teamabnormals.atmospheric.core.registry.datapack.AtmosphericCamelVariants;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.network.syncher.SynchedEntityData.Builder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
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

import java.util.Optional;

@Mixin(Camel.class)
public class CamelMixin extends AbstractHorse implements VariantHolder<Holder<CamelVariant>> {
	@Unique
	private static final EntityDataAccessor<Holder<CamelVariant>> DATA_VARIANT_ID = SynchedEntityData.defineId(Camel.class, AtmosphericDataSerializers.CAMEL_VARIANT.get());

	protected CamelMixin(EntityType<? extends AbstractHorse> entity, Level level) {
		super(entity, level);
	}

	@Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
	private void addAdditionalSaveData(CompoundTag tag, CallbackInfo ci) {
		this.getVariant().unwrapKey().ifPresent(key -> tag.putString("variant", key.location().toString()));
	}

	@Inject(method = "defineSynchedData", at = @At("TAIL"))
	private void defineSynchedData(Builder builder, CallbackInfo ci) {
		RegistryAccess access = this.registryAccess();
		Registry<CamelVariant> registry = access.registryOrThrow(AtmosphericRegistries.CAMEL_VARIANT);
		builder.define(DATA_VARIANT_ID, registry.getHolder(AtmosphericCamelVariants.DEFAULT).or(registry::getAny).orElseThrow());
	}

	@Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
	private void readAdditionalSaveData(CompoundTag tag, CallbackInfo ci) {
		Optional.ofNullable(ResourceLocation.tryParse(tag.getString("variant")))
				.map(location -> ResourceKey.create(AtmosphericRegistries.CAMEL_VARIANT, location))
				.flatMap(key -> this.registryAccess().registryOrThrow(AtmosphericRegistries.CAMEL_VARIANT).getHolder(key))
				.ifPresent(this::setVariant);
	}

	@Inject(method = "finalizeSpawn", at = @At("HEAD"))
	private void finalizeSpawn(ServerLevelAccessor level, DifficultyInstance p_251264_, MobSpawnType p_250254_, SpawnGroupData spawnGroupData, CallbackInfoReturnable<SpawnGroupData> cir) {
		this.setVariant(CamelVariant.getSpawnVariant(this.registryAccess(), level.getBiome(this.blockPosition())));
	}

	@Inject(method = "getBreedOffspring(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/AgeableMob;)Lnet/minecraft/world/entity/AgeableMob;", at = @At("RETURN"), cancellable = true)
	private void getBreedOffspring(ServerLevel level, AgeableMob ageableMob, CallbackInfoReturnable<Camel> cir) {
		Camel camel = cir.getReturnValue();

		if (ageableMob instanceof Camel camelParent) {
			VariantHolder<Holder<CamelVariant>> child = ((VariantHolder<Holder<CamelVariant>>) camel);
			VariantHolder<Holder<CamelVariant>> parent = ((VariantHolder<Holder<CamelVariant>>) camelParent);

			Holder<CamelVariant> variant = this.getVariant();
			Holder<CamelVariant> parentVariant = parent.getVariant();

			if ((variant.is(AtmosphericCamelVariants.DESERT) && parentVariant.is(AtmosphericCamelVariants.ARID)) || (variant.is(AtmosphericCamelVariants.ARID) && parentVariant.is(AtmosphericCamelVariants.DESERT))) {
				this.registryAccess().registryOrThrow(AtmosphericRegistries.CAMEL_VARIANT).getHolder(AtmosphericCamelVariants.HYBRID).ifPresent(child::setVariant);
			} else {
				child.setVariant(this.random.nextBoolean() ? variant : parentVariant);
			}

			cir.setReturnValue((Camel) child);
		}
	}

	@Override
	public void setVariant(Holder<CamelVariant> variant) {
		this.entityData.set(DATA_VARIANT_ID, variant);
	}

	@Override
	public Holder<CamelVariant> getVariant() {
		return this.entityData.get(DATA_VARIANT_ID);
	}
}