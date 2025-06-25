package com.teamabnormals.atmospheric.core.registry.datapack;

import com.teamabnormals.atmospheric.core.Atmospheric;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.animal.WolfVariant;
import net.minecraft.world.level.biome.Biome;

public class AtmosphericWolfVariants {
	public static final ResourceKey<WolfVariant> BUSH = create("bush");
	public static final ResourceKey<WolfVariant> CREAM = create("cream");
	public static final ResourceKey<WolfVariant> GOLDEN = create("golden");
	public static final ResourceKey<WolfVariant> MANED = create("maned");
	public static final ResourceKey<WolfVariant> MASKED = create("masked");

	public static void bootstrap(BootstrapContext<WolfVariant> context) {
		register(context, BUSH, "wolf_bush", AtmosphericBiomes.RAINFOREST);
		register(context, CREAM, "wolf_cream", AtmosphericBiomes.LAUREL_FOREST);
		register(context, GOLDEN, "wolf_golden", AtmosphericBiomes.ASPEN_PARKLAND);
		register(context, MANED, "wolf_maned", AtmosphericBiomes.SPARSE_RAINFOREST);
		register(context, MASKED, "wolf_masked", AtmosphericBiomes.KOUSA_JUNGLE);
	}

	private static ResourceKey<WolfVariant> create(String name) {
		return ResourceKey.create(Registries.WOLF_VARIANT, Atmospheric.location(name));
	}

	static void register(BootstrapContext<WolfVariant> context, ResourceKey<WolfVariant> key, String name, ResourceKey<Biome> spawnBiome) {
		register(context, key, name, HolderSet.direct(context.lookup(Registries.BIOME).getOrThrow(spawnBiome)));
	}

	private static void register(BootstrapContext<WolfVariant> context, ResourceKey<WolfVariant> key, String name, TagKey<Biome> spawnBiomes) {
		register(context, key, name, context.lookup(Registries.BIOME).getOrThrow(spawnBiomes));
	}

	private static void register(BootstrapContext<WolfVariant> context, ResourceKey<WolfVariant> key, String name, HolderSet<Biome> spawnBiomes) {
		ResourceLocation texture = Atmospheric.location("entity/wolf/" + name);
		ResourceLocation tame = Atmospheric.location("entity/wolf/" + name + "_tame");
		ResourceLocation angry = Atmospheric.location("entity/wolf/" + name + "_angry");
		context.register(key, new WolfVariant(texture, tame, angry, spawnBiomes));
	}
}