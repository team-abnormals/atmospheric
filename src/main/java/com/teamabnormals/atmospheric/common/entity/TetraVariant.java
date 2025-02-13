package com.teamabnormals.atmospheric.common.entity;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.atmospheric.core.registry.AtmosphericRegistries;
import net.minecraft.Util;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ExtraCodecs;

public record TetraVariant(Component displayName, ResourceLocation texture) {
	public static final ResourceKey<TetraVariant> NEON = createKey("neon");
	public static final ResourceKey<TetraVariant> BEACON = createKey("beacon");
	public static final ResourceKey<TetraVariant> DIAMOND = createKey("diamond");
	public static final ResourceKey<TetraVariant> EMPEROR = createKey("emperor");
	public static final ResourceKey<TetraVariant> FLAME = createKey("flame");
	public static final ResourceKey<TetraVariant> JEWEL = createKey("jewel");
	public static final ResourceKey<TetraVariant> ORNATE = createKey("ornate");
	public static final ResourceKey<TetraVariant> ROYAL = createKey("royal");

	public static void bootstrap(BootstapContext<TetraVariant> context) {
		registerVariant(context, NEON);
		registerVariant(context, BEACON);
		registerVariant(context, DIAMOND);
		registerVariant(context, EMPEROR);
		registerVariant(context, FLAME);
		registerVariant(context, JEWEL);
		registerVariant(context, ORNATE);
		registerVariant(context, ROYAL);
	}

	public static void registerVariant(BootstapContext<TetraVariant> context, ResourceKey<TetraVariant> key) {
		context.register(key, new TetraVariant(
				Component.translatable(Util.makeDescriptionId("entity.atmospheric.tetra", key.location())),
				key.location().withPrefix("textures/entity/tetra/").withSuffix("_tetra")));
	}

	public static ResourceKey<TetraVariant> createKey(String name) {
		return ResourceKey.create(AtmosphericRegistries.TETRA_VARIANT, new ResourceLocation(Atmospheric.MOD_ID, name));
	}

	public static final Codec<TetraVariant> CODEC = RecordCodecBuilder.create(instance -> instance.group(
					ExtraCodecs.COMPONENT.fieldOf("description").forGetter(entry -> entry.displayName),
					ResourceLocation.CODEC.fieldOf("texture").forGetter(entry -> entry.texture))
			.apply(instance, TetraVariant::new));
}