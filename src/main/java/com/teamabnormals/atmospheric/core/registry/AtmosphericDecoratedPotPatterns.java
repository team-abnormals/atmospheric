package com.teamabnormals.atmospheric.core.registry;

import com.mojang.datafixers.util.Pair;
import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.blueprint.core.util.DataUtil;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.DecoratedPotPattern;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class AtmosphericDecoratedPotPatterns {
	public static final DeferredRegister<DecoratedPotPattern> DECORATED_POT_PATTERNS = DeferredRegister.create(Registries.DECORATED_POT_PATTERN, Atmospheric.MOD_ID);

	public static final DeferredHolder<DecoratedPotPattern, ?> SCYTHE = register("scythe");
	public static final DeferredHolder<DecoratedPotPattern, ?> SUCCULENT = register("succulent");
	public static final DeferredHolder<DecoratedPotPattern, ?> SUN = register("sun");

	public static void registerDecoratedPotPatterns() {
		DataUtil.registerDecoratedPotPattern(
				Pair.of(AtmosphericItems.SCYTHE_POTTERY_SHERD.get(), SCYTHE),
				Pair.of(AtmosphericItems.SUCCULENT_POTTERY_SHERD.get(), SUCCULENT),
				Pair.of(AtmosphericItems.SUN_POTTERY_SHERD.get(), SUN)
		);
	}

	public static DeferredHolder<DecoratedPotPattern, ?> register(String name) {
		return DECORATED_POT_PATTERNS.register(name, () -> new DecoratedPotPattern(Atmospheric.location(name + "_pottery_pattern")));
	}
}