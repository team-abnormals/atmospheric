package com.teamabnormals.atmospheric.core.other;

import com.teamabnormals.atmospheric.core.Atmospheric;
import net.minecraft.client.model.geom.ModelLayerLocation;

public class AtmosphericModelLayers {
	public static final ModelLayerLocation PASSION_FRUIT_SEED = register("passion_fruit_seed");

	public static ModelLayerLocation register(String name) {
		return register(name, "main");
	}

	public static ModelLayerLocation register(String name, String layer) {
		return new ModelLayerLocation(Atmospheric.location(name), layer);
	}
}
