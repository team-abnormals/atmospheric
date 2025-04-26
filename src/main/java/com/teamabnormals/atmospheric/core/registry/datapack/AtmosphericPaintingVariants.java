package com.teamabnormals.atmospheric.core.registry.datapack;

import com.teamabnormals.atmospheric.core.Atmospheric;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.decoration.PaintingVariant;

public class AtmosphericPaintingVariants {
	public static final ResourceKey<PaintingVariant> FATEFUL_OUTING = create("fateful_outing");
	public static final ResourceKey<PaintingVariant> MONSOON = create("monsoon");
	public static final ResourceKey<PaintingVariant> NOT_SO_STILL_LIFE = create("not_so_still_life");
	public static final ResourceKey<PaintingVariant> WAYWARD = create("wayward");
	public static final ResourceKey<PaintingVariant> CANYON = create("canyon");
	public static final ResourceKey<PaintingVariant> LOST = create("lost");

	public static void bootstrap(BootstrapContext<PaintingVariant> context) {
		register(context, FATEFUL_OUTING, 3, 3);
		register(context, MONSOON, 2, 2);
		register(context, NOT_SO_STILL_LIFE, 1, 1);
		register(context, WAYWARD, 2, 2);
		register(context, CANYON, 3, 2);
		register(context, LOST, 2, 4);
	}

	private static ResourceKey<PaintingVariant> create(String name) {
		return ResourceKey.create(Registries.PAINTING_VARIANT, Atmospheric.location(name));
	}

	private static void register(BootstrapContext<PaintingVariant> context, ResourceKey<PaintingVariant> key, int width, int height) {
		context.register(key, new PaintingVariant(width, height, key.location()));
	}
}
