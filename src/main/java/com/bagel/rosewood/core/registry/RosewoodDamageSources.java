package com.bagel.rosewood.core.registry;

import javax.annotation.Nullable;

import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IndirectEntityDamageSource;

public class RosewoodDamageSources {
	public static DamageSource causeThrownDamage(Entity source, @Nullable Entity indirectEntityIn) {
	      return (new IndirectEntityDamageSource("passion", source, indirectEntityIn)).setProjectile();
	}
}
