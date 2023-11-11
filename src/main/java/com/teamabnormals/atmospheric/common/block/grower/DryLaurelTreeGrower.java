package com.teamabnormals.atmospheric.common.block.grower;

import com.teamabnormals.atmospheric.core.registry.AtmosphericFeatures.AtmosphericConfiguredFeatures;
import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import javax.annotation.Nullable;

public class DryLaurelTreeGrower extends LaurelTreeGrower {

	@Nullable
	@Override
	protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource random, boolean flowers) {
		return AtmosphericConfiguredFeatures.DRY_LAUREL.getHolder().get();
	}

	protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredNetherFeature(RandomSource random) {
		return AtmosphericConfiguredFeatures.DRY_LAUREL_BLOOD_ORANGES_08.getHolder().get();
	}

	protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredOrangesFeature(RandomSource random) {
		return AtmosphericConfiguredFeatures.DRY_LAUREL_ORANGES_08.getHolder().get();
	}

	@Nullable
	@Override
	protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredMegaFeature(RandomSource random) {
		return this.shouldBeGiant(random) ? AtmosphericConfiguredFeatures.GIANT_DRY_LAUREL.getHolder().get() : AtmosphericConfiguredFeatures.LARGE_DRY_LAUREL.getHolder().get();
	}

	protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredOrangesMegaFeature(RandomSource random) {
		return this.shouldBeGiant(random) ? AtmosphericConfiguredFeatures.GIANT_DRY_LAUREL_ORANGES_08.getHolder().get() : AtmosphericConfiguredFeatures.LARGE_DRY_LAUREL_ORANGES_08.getHolder().get();
	}

	protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredNetherMegaFeature(RandomSource random) {
		return this.shouldBeGiant(random) ? AtmosphericConfiguredFeatures.GIANT_DRY_LAUREL_BLOOD_ORANGES_08.getHolder().get() : AtmosphericConfiguredFeatures.LARGE_DRY_LAUREL_BLOOD_ORANGES_08.getHolder().get();
	}
}
