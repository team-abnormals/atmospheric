package com.teamabnormals.atmospheric.core.registry.datapack;

import com.teamabnormals.atmospheric.core.Atmospheric;
import net.minecraft.core.Holder.Reference;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

public class AtmosphericDamageTypes {
	public static final ResourceKey<DamageType> YUCCA_SAPLING = createKey("yucca_sapling");
	public static final ResourceKey<DamageType> YUCCA_FLOWER = createKey("yucca_flower");
	public static final ResourceKey<DamageType> YUCCA_BRANCH = createKey("yucca_branch");
	public static final ResourceKey<DamageType> YUCCA_LEAVES = createKey("yucca_leaves");
	public static final ResourceKey<DamageType> BARREL_CACTUS = createKey("barrel_cactus");
	public static final ResourceKey<DamageType> ALOE_LEAVES = createKey("aloe_leaves");
	public static final ResourceKey<DamageType> PASSION_FRUIT_SEED = createKey("passion_fruit_seed");

	public static void bootstrap(BootstrapContext<DamageType> context) {
		register(context, PASSION_FRUIT_SEED, "passionFruitSeed", 0.1F);
		register(context, YUCCA_FLOWER, "yuccaFlower", 0.1F);
		register(context, YUCCA_BRANCH, "yuccaBranch", 0.1F);
		register(context, YUCCA_LEAVES, "yuccaLeaves", 0.1F);
		register(context, YUCCA_SAPLING, "yuccaSapling", 0.1F);
		register(context, BARREL_CACTUS, "barrelCactus", 0.1F);
		register(context, ALOE_LEAVES, "aloeLeaves", 0.1F);
	}

	public static DamageSource passionFruitSeed(Level level, Entity source, @Nullable Entity causingEntity) {
		return level.damageSources().source(PASSION_FRUIT_SEED, source, causingEntity);
	}

	public static DamageSource barrelCactus(Level level) {
		return level.damageSources().source(BARREL_CACTUS);
	}

	public static DamageSource aloeLeaves(Level level) {
		return level.damageSources().source(ALOE_LEAVES);
	}

	public static Reference<DamageType> register(BootstrapContext<DamageType> context, ResourceKey<DamageType> key, String localizationKey, float exhaustion) {
		return context.register(key, new DamageType(Atmospheric.MOD_ID + "." + localizationKey, exhaustion));
	}

	public static ResourceKey<DamageType> createKey(String name) {
		return ResourceKey.create(Registries.DAMAGE_TYPE, Atmospheric.location(name));
	}

}
