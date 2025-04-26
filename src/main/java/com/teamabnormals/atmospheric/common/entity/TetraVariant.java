package com.teamabnormals.atmospheric.common.entity;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.atmospheric.core.registry.AtmosphericRegistries;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentSerialization;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.RegistryFileCodec;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.util.random.Weight;
import net.minecraft.util.random.WeightedEntry;
import net.minecraft.util.random.WeightedRandomList;

public record TetraVariant(ResourceLocation assetId, Component description, int weight) implements WeightedEntry {

	@Override
	public Weight getWeight() {
		return Weight.of(this.weight());
	}

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
		context.register(key, new TetraVariant(
				key.location().withPrefix("entity/tetra/"),
				Component.translatable(Util.makeDescriptionId("tetra_variant", key.location())),
				weight));
	}

	public static ResourceKey<TetraVariant> create(String name) {
		return ResourceKey.create(AtmosphericRegistries.TETRA_VARIANT, Atmospheric.location(name));
	}

	public static final Codec<TetraVariant> DIRECT_CODEC = RecordCodecBuilder.create(instance -> instance.group(
					ResourceLocation.CODEC.fieldOf("asset_id").forGetter(TetraVariant::assetId),
					ComponentSerialization.CODEC.fieldOf("description").forGetter(TetraVariant::description),
					Codec.INT.optionalFieldOf("weight", 1).forGetter(TetraVariant::weight))
			.apply(instance, TetraVariant::new));

	public static final StreamCodec<RegistryFriendlyByteBuf, TetraVariant> DIRECT_STREAM_CODEC = StreamCodec.composite(
			ResourceLocation.STREAM_CODEC, TetraVariant::assetId,
			ComponentSerialization.STREAM_CODEC, TetraVariant::description,
			ByteBufCodecs.INT, TetraVariant::weight,
			TetraVariant::new
	);

	public static final Codec<Holder<TetraVariant>> CODEC = RegistryFileCodec.create(AtmosphericRegistries.TETRA_VARIANT, DIRECT_CODEC);
	public static final StreamCodec<RegistryFriendlyByteBuf, Holder<TetraVariant>> STREAM_CODEC = ByteBufCodecs.holder(AtmosphericRegistries.TETRA_VARIANT, DIRECT_STREAM_CODEC);

	public static Holder<TetraVariant> getSpawnVariant(RegistryAccess registryAccess, RandomSource random) {
		Registry<TetraVariant> registry = registryAccess.registryOrThrow(AtmosphericRegistries.TETRA_VARIANT);
		WeightedRandomList<TetraVariant> variants = WeightedRandomList.create(registry.stream().toList());
		return registry.wrapAsHolder(variants.getRandom(random).orElseThrow());
	}
}