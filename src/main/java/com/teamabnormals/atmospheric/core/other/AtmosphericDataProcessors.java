package com.teamabnormals.atmospheric.core.other;

import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.atmospheric.core.registry.datapack.AtmosphericCamelVariants;
import com.teamabnormals.blueprint.common.world.storage.tracking.TrackedData;
import com.teamabnormals.blueprint.common.world.storage.tracking.TrackedDataManager;
import net.minecraft.resources.ResourceLocation;

public class AtmosphericDataProcessors {
	public static final TrackedData<ResourceLocation> CAMEL_VARIANT = TrackedData.Builder.create(ResourceLocation.STREAM_CODEC, AtmosphericCamelVariants.DEFAULT::location).enableSaving(ResourceLocation.CODEC.fieldOf("ResourceLocation")).build();

	public static void registerTrackedData() {
		TrackedDataManager.INSTANCE.registerData(Atmospheric.location("camel_variant"), CAMEL_VARIANT);
	}
}