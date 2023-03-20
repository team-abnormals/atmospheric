package com.teamabnormals.atmospheric.core.registry;

import com.teamabnormals.atmospheric.core.Atmospheric;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public final class AtmosphericBannerPatterns {
	public static final DeferredRegister<BannerPattern> BANNER_PATTERNS = DeferredRegister.create(Registry.BANNER_PATTERN_REGISTRY, Atmospheric.MOD_ID);

	public static final RegistryObject<BannerPattern> COCHINEAL = BANNER_PATTERNS.register("cochineal", () -> new BannerPattern("chc"));
}