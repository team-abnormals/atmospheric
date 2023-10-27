package com.teamabnormals.atmospheric.common.block.grower;

import com.teamabnormals.atmospheric.core.registry.AtmosphericFeatures.AtmosphericConfiguredFeatures;
import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import javax.annotation.Nullable;

public class DryLaurelTreeGrower extends LaurelTreeGrower {

	@Override
	@Nullable
	protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource random, boolean oranges) {
		return oranges ? AtmosphericConfiguredFeatures.DRY_LAUREL_BOOSTED.getHolder().get() : AtmosphericConfiguredFeatures.DRY_LAUREL_GROWN.getHolder().get();
	}

	@Nullable
	protected Holder<? extends ConfiguredFeature<?, ?>> getNetherFeature(RandomSource random, boolean oranges) {
		return oranges ? AtmosphericConfiguredFeatures.DRY_LAUREL_NETHER_BOOSTED.getHolder().get() : AtmosphericConfiguredFeatures.DRY_LAUREL_NETHER_GROWN.getHolder().get();
	}
}
