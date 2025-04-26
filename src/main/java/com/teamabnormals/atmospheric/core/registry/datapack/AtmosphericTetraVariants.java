package com.teamabnormals.atmospheric.core.registry.datapack;

import com.teamabnormals.atmospheric.common.entity.TetraVariant;
import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.atmospheric.core.registry.AtmosphericRegistries;
import net.minecraft.Util;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;

public class AtmosphericTetraVariants {
	public static final ResourceKey<TetraVariant> NEON = create("neon");
	public static final ResourceKey<TetraVariant> CARDINAL = create("cardinal");
	public static final ResourceKey<TetraVariant> BEACON = create("beacon");
	public static final ResourceKey<TetraVariant> DIAMOND = create("diamond");
	public static final ResourceKey<TetraVariant> EMBER = create("ember");
	public static final ResourceKey<TetraVariant> EMPEROR = create("emperor");
	public static final ResourceKey<TetraVariant> FLAME = create("flame");
	public static final ResourceKey<TetraVariant> JEWEL = create("jewel");
	public static final ResourceKey<TetraVariant> ORNATE = create("ornate");
	public static final ResourceKey<TetraVariant> ROYAL = create("royal");
	public static final ResourceKey<TetraVariant> DEFAULT = NEON;

	public static void bootstrap(BootstrapContext<TetraVariant> context) {
		register(context, NEON, 50);
		register(context, CARDINAL, 50);
		register(context, BEACON, 1);
		register(context, DIAMOND, 1);
		register(context, EMBER, 1);
		register(context, EMPEROR, 1);
		register(context, FLAME, 1);
		register(context, JEWEL, 1);
		register(context, ORNATE, 1);
		register(context, ROYAL, 1);
	}

	public static void register(BootstrapContext<TetraVariant> context, ResourceKey<TetraVariant> key, int weight) {
		context.register(key, new TetraVariant(key.location().withPrefix("entity/tetra/"), Component.translatable(Util.makeDescriptionId("tetra_variant", key.location())), weight));
	}

	public static ResourceKey<TetraVariant> create(String name) {
		return ResourceKey.create(AtmosphericRegistries.TETRA_VARIANT, Atmospheric.location(name));
	}
}
