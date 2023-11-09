package com.teamabnormals.atmospheric.common.block.grower;

import com.teamabnormals.atmospheric.core.registry.AtmosphericFeatures.AtmosphericConfiguredFeatures;
import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class YuccaTreeGrower extends AbstractTreeGrower {

	@Override
	protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource random, boolean beehive) {
		if (random.nextFloat() < 0.1F) {
			return AtmosphericConfiguredFeatures.BABY_YUCCA.getHolder().get();
		} else {
			return beehive ? AtmosphericConfiguredFeatures.YUCCA_BEES_005.getHolder().get() : AtmosphericConfiguredFeatures.YUCCA.getHolder().get();
		}
	}
}
