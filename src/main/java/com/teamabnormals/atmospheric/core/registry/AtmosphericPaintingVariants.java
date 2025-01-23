package com.teamabnormals.atmospheric.core.registry;

import com.teamabnormals.atmospheric.core.Atmospheric;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class AtmosphericPaintingVariants {
	public static final DeferredRegister<PaintingVariant> PAINTING_VARIANTS = DeferredRegister.create(ForgeRegistries.PAINTING_VARIANTS, Atmospheric.MOD_ID);

	public static final RegistryObject<PaintingVariant> FATEFUL_OUTING = PAINTING_VARIANTS.register("fateful_outing", () -> new PaintingVariant(48, 48));
	public static final RegistryObject<PaintingVariant> MONSOON = PAINTING_VARIANTS.register("monsoon", () -> new PaintingVariant(32, 32));
	public static final RegistryObject<PaintingVariant> NOT_SO_STILL_LIFE = PAINTING_VARIANTS.register("not_so_still_life", () -> new PaintingVariant(16, 16));
	public static final RegistryObject<PaintingVariant> WAYWARD = PAINTING_VARIANTS.register("wayward", () -> new PaintingVariant(32, 32));
	public static final RegistryObject<PaintingVariant> CANYON = PAINTING_VARIANTS.register("canyon", () -> new PaintingVariant(48, 32));
	public static final RegistryObject<PaintingVariant> LOST = PAINTING_VARIANTS.register("lost", () -> new PaintingVariant(32, 64));
}
