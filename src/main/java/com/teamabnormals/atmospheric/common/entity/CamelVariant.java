package com.teamabnormals.atmospheric.common.entity;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.teamabnormals.atmospheric.core.registry.AtmosphericRegistries;
import com.teamabnormals.atmospheric.core.registry.datapack.AtmosphericCamelVariants;
import net.minecraft.core.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.RegistryFileCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;

public record CamelVariant(ResourceLocation assetId, HolderSet<Biome> biomes) {

	public static final Codec<CamelVariant> DIRECT_CODEC = RecordCodecBuilder.create(instance -> instance.group(
					ResourceLocation.CODEC.fieldOf("asset_id").forGetter(CamelVariant::assetId),
					RegistryCodecs.homogeneousList(Registries.BIOME).fieldOf("biomes").forGetter(CamelVariant::biomes))
			.apply(instance, CamelVariant::new));

	public static final StreamCodec<RegistryFriendlyByteBuf, CamelVariant> DIRECT_STREAM_CODEC = StreamCodec.composite(
			ResourceLocation.STREAM_CODEC, CamelVariant::assetId,
			ByteBufCodecs.holderSet(Registries.BIOME), CamelVariant::biomes,
			CamelVariant::new
	);

	public static final Codec<Holder<CamelVariant>> CODEC = RegistryFileCodec.create(AtmosphericRegistries.CAMEL_VARIANT, DIRECT_CODEC);
	public static final StreamCodec<RegistryFriendlyByteBuf, Holder<CamelVariant>> STREAM_CODEC = ByteBufCodecs.holder(AtmosphericRegistries.CAMEL_VARIANT, DIRECT_STREAM_CODEC);

	public static Holder<CamelVariant> getSpawnVariant(RegistryAccess registryAccess, Holder<Biome> biome) {
		Registry<CamelVariant> registry = registryAccess.registryOrThrow(AtmosphericRegistries.CAMEL_VARIANT);
		return registry.holders()
				.filter(holder -> holder.value().biomes().contains(biome))
				.findFirst()
				.or(() -> registry.getHolder(AtmosphericCamelVariants.DEFAULT))
				.or(registry::getAny)
				.orElseThrow();
	}
}