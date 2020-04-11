package com.bagel.atmospheric.core.data;

import javax.annotation.Nullable;

import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IndirectEntityDamageSource;

public class AtmosphericDamageSources {
	public static final DamageSource YUCCA_SAPLING = new DamageSource("yuccaSapling");
	public static final DamageSource YUCCA_FLOWER = new DamageSource("yuccaFlower");
	public static final DamageSource YUCCA_BRANCH = new DamageSource("yuccaBranch");
	public static final DamageSource YUCCA_LEAVES = new DamageSource("yuccaLeaves");
	public static final DamageSource BARREL_CACTUS = new DamageSource("barrelCactus");
	
	public static DamageSource causePassionfruitSeedDamage(Entity source, @Nullable Entity indirectEntityIn) {
	      return (new IndirectEntityDamageSource("passionfruitSeed", source, indirectEntityIn)).setProjectile();
	}
	
	public static DamageSource causeShimmeringPassionfruitSeedDamage(Entity source, @Nullable Entity indirectEntityIn) {
	      return (new IndirectEntityDamageSource("shimmeringPassionfruitSeed", source, indirectEntityIn)).setProjectile();
	}
}
