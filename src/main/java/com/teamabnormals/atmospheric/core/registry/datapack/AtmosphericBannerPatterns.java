package com.teamabnormals.atmospheric.core.registry.datapack;

import com.teamabnormals.atmospheric.core.Atmospheric;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.entity.BannerPattern;

public final class AtmosphericBannerPatterns {
	public static final ResourceKey<BannerPattern> COCHINEAL = create("cochineal");

	public static void bootstrap(BootstrapContext<BannerPattern> context) {
		register(context, COCHINEAL);
	}

	private static ResourceKey<BannerPattern> create(String name) {
		return ResourceKey.create(Registries.BANNER_PATTERN, Atmospheric.location(name));
	}

	public static void register(BootstrapContext<BannerPattern> context, ResourceKey<BannerPattern> resourceKey) {
		context.register(resourceKey, new BannerPattern(resourceKey.location(), "block.minecraft.banner." + resourceKey.location().toShortLanguageKey()));
	}
}