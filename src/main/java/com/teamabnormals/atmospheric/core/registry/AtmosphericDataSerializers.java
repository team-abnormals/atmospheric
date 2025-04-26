package com.teamabnormals.atmospheric.core.registry;

import com.teamabnormals.atmospheric.common.entity.CamelVariant;
import com.teamabnormals.atmospheric.common.entity.TetraVariant;
import com.teamabnormals.atmospheric.core.Atmospheric;
import net.minecraft.core.Holder;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public final class AtmosphericDataSerializers {
	public static final DeferredRegister<EntityDataSerializer<?>> SERIALIZERS = DeferredRegister.create(NeoForgeRegistries.ENTITY_DATA_SERIALIZERS, Atmospheric.MOD_ID);

	public static final DeferredHolder<EntityDataSerializer<?>, EntityDataSerializer<Holder<TetraVariant>>> TETRA_VARIANT = SERIALIZERS.register("tetra_variant", () -> EntityDataSerializer.forValueType(TetraVariant.STREAM_CODEC));
	public static final DeferredHolder<EntityDataSerializer<?>, EntityDataSerializer<Holder<CamelVariant>>> CAMEL_VARIANT = SERIALIZERS.register("camel_variant", () -> EntityDataSerializer.forValueType(CamelVariant.STREAM_CODEC));
}