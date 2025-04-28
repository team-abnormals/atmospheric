package com.teamabnormals.atmospheric.common.entity;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.teamabnormals.atmospheric.core.registry.AtmosphericRegistries;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentSerialization;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.RegistryFileCodec;
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