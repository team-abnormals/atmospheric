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
import net.minecraft.util.random.WeightedEntry;

public record TetraVariant(Component displayName, ResourceLocation texture, int weight) {
	public static final ResourceKey<TetraVariant> NEON = createKey("neon");
	public static final ResourceKey<TetraVariant> CARDINAL = createKey("cardinal");
	public static final ResourceKey<TetraVariant> BEACON = createKey("beacon");
	public static final ResourceKey<TetraVariant> DIAMOND = createKey("diamond");
	public static final ResourceKey<TetraVariant> EMPEROR = createKey("emperor");
	public static final ResourceKey<TetraVariant> FLAME = createKey("flame");
	public static final ResourceKey<TetraVariant> JEWEL = createKey("jewel");
	public static final ResourceKey<TetraVariant> ORNATE = createKey("ornate");
	public static final ResourceKey<TetraVariant> ROYAL = createKey("royal");

	public static void bootstrap(BootstapContext<TetraVariant> context) {
		registerVariant(context, NEON, 50);
		registerVariant(context, CARDINAL, 50);
		registerVariant(context, BEACON, 1);
		registerVariant(context, DIAMOND, 1);
		registerVariant(context, EMPEROR, 1);
		registerVariant(context, FLAME, 1);
		registerVariant(context, JEWEL, 1);
		registerVariant(context, ORNATE, 1);
		registerVariant(context, ROYAL, 1);
	}

	public static void registerVariant(BootstapContext<TetraVariant> context, ResourceKey<TetraVariant> key, int weight) {
		context.register(key, new TetraVariant(
				Component.translatable(Util.makeDescriptionId("tetra_variant", key.location())),
				key.location().withPrefix("entity/tetra/"), weight));
	}

	public static ResourceKey<TetraVariant> createKey(String name) {
		return ResourceKey.create(AtmosphericRegistries.TETRA_VARIANT, new ResourceLocation(Atmospheric.MOD_ID, name));
	}

	public static final Codec<TetraVariant> CODEC = RecordCodecBuilder.create(instance -> instance.group(
					ExtraCodecs.COMPONENT.fieldOf("description").forGetter(entry -> entry.displayName),
					ResourceLocation.CODEC.fieldOf("asset_id").forGetter(entry -> entry.texture),
					Codec.INT.optionalFieldOf("weight", 1).forGetter(entry -> entry.weight))
			.apply(instance, TetraVariant::new));

	public static final Codec<TetraVariant> NETWORK_CODEC = RecordCodecBuilder.create(instance -> instance.group(
					ExtraCodecs.COMPONENT.fieldOf("description").forGetter(entry -> entry.displayName),
					ResourceLocation.CODEC.fieldOf("asset_id").forGetter(entry -> entry.texture))
			.apply(instance, (desc, asset) -> new TetraVariant(desc, asset, -1)));
}