package com.teamabnormals.atmospheric.core.registry;

import com.teamabnormals.atmospheric.common.entity.CamelVariant;
import com.teamabnormals.atmospheric.common.entity.TetraVariant;
import com.teamabnormals.atmospheric.core.Atmospheric;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.neoforged.neoforge.registries.DataPackRegistryEvent;

public final class AtmosphericRegistries {
	public static final ResourceKey<Registry<TetraVariant>> TETRA_VARIANT = key("tetra_variant");
	public static final ResourceKey<Registry<CamelVariant>> CAMEL_VARIANT = key("camel_variant");

	public static void registerRegistries(DataPackRegistryEvent.NewRegistry event) {
		event.dataPackRegistry(TETRA_VARIANT, TetraVariant.DIRECT_CODEC, TetraVariant.DIRECT_CODEC);
		event.dataPackRegistry(CAMEL_VARIANT, CamelVariant.DIRECT_CODEC, CamelVariant.DIRECT_CODEC);
	}

	private static <T> ResourceKey<Registry<T>> key(String name) {
		return ResourceKey.createRegistryKey(Atmospheric.location(name));
	}
}