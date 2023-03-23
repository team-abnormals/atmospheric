package com.teamabnormals.atmospheric.core.other;

import com.teamabnormals.atmospheric.core.Atmospheric;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.IndirectEntityDamageSource;
import net.minecraft.world.entity.Entity;

import javax.annotation.Nullable;

public class AtmosphericDamageSources {
	public static final DamageSource YUCCA_SAPLING = createDamageSource("yuccaSapling");
	public static final DamageSource YUCCA_FLOWER = createDamageSource("yuccaFlower");
	public static final DamageSource YUCCA_BRANCH = createDamageSource("yuccaBranch");
	public static final DamageSource YUCCA_LEAVES = createDamageSource("yuccaLeaves");
	public static final DamageSource BARREL_CACTUS = createDamageSource("barrelCactus");
	public static final DamageSource ALOE_LEAVES = createDamageSource("aloeLeaves");

	public static DamageSource passionFruitSeedAttack(Entity source, @Nullable Entity indirectEntityIn) {
		return (new IndirectEntityDamageSource(Atmospheric.MOD_ID + ".passionFruitSeed", source, indirectEntityIn)).setProjectile();
	}

	private static DamageSource createDamageSource(String name) {
		return new DamageSource(Atmospheric.MOD_ID + "." + name);
	}
}
