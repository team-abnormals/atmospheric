package com.teamabnormals.atmospheric.core.other;

import com.teamabnormals.atmospheric.core.Atmospheric;
import net.minecraft.client.model.geom.ModelLayerLocation;

public class AtmosphericModelLayers {
	public static final ModelLayerLocation COCHINEAL = register("cochineal");
	public static final ModelLayerLocation COCHINEAL_SADDLE = register("cochineal", "saddle");
	public static final ModelLayerLocation DRAGON_FRUIT = register("dragon_fruit");
	public static final ModelLayerLocation PASSION_FRUIT_SEED = register("passion_fruit_seed");

	public static ModelLayerLocation register(String name) {
		return register(name, "main");
	}

	public static ModelLayerLocation register(String name, String layer) {
		return new ModelLayerLocation(Atmospheric.location(name), layer);
	}
}
