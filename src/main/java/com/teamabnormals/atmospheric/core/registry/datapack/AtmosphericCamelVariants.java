package com.teamabnormals.atmospheric.core.registry.datapack;

import com.teamabnormals.atmospheric.common.entity.CamelVariant;
import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.atmospheric.core.other.tags.AtmosphericBiomeTags;
import com.teamabnormals.atmospheric.core.registry.AtmosphericRegistries;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;

public class AtmosphericCamelVariants {
	public static final ResourceKey<CamelVariant> DESERT = create("desert");
	public static final ResourceKey<CamelVariant> ARID = create("arid");
	public static final ResourceKey<CamelVariant> HYBRID = create("hybrid");
	public static final ResourceKey<CamelVariant> DEFAULT = DESERT;

	public static void bootstrap(BootstrapContext<CamelVariant> context) {
		context.register(DESERT, new CamelVariant(ResourceLocation.withDefaultNamespace("entity/camel/camel"), HolderSet.direct(context.lookup(Registries.BIOME).getOrThrow(Biomes.DESERT))));
		register(context, ARID, "camel_arid", AtmosphericBiomeTags.SPAWNS_ARID_CAMELS);
		register(context, HYBRID, "camel_hybrid", HolderSet.direct());
	}

	public static void register(BootstrapContext<CamelVariant> context, ResourceKey<CamelVariant> key, String name, ResourceKey<Biome> spawnBiome) {
		register(context, key, name, HolderSet.direct(context.lookup(Registries.BIOME).getOrThrow(spawnBiome)));
	}

	public static void register(BootstrapContext<CamelVariant> context, ResourceKey<CamelVariant> key, String name, TagKey<Biome> spawnBiomes) {
		register(context, key, name, context.lookup(Registries.BIOME).getOrThrow(spawnBiomes));
	}

	public static void register(BootstrapContext<CamelVariant> context, ResourceKey<CamelVariant> key, String name, HolderSet<Biome> spawnBiomes) {
		context.register(key, new CamelVariant(key.location().withPath("entity/camel/" + name), spawnBiomes));
	}

	public static ResourceKey<CamelVariant> create(String name) {
		return ResourceKey.create(AtmosphericRegistries.CAMEL_VARIANT, Atmospheric.location(name));
	}
}
