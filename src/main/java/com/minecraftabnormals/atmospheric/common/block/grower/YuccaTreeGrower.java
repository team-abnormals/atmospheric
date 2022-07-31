package com.minecraftabnormals.atmospheric.common.block.grower;

import com.minecraftabnormals.atmospheric.core.registry.AtmosphericFeatures.AtmosphericConfiguredFeatures;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import java.util.Random;

public class YuccaTreeGrower extends AbstractTreeGrower {

	protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(Random random, boolean beehive) {
		if (random.nextInt(10) == 0) {
			return AtmosphericConfiguredFeatures.BABY_YUCCA.getHolder().get();
		} else {
			return beehive ? AtmosphericConfiguredFeatures.YUCCA_BEES_005.getHolder().get() : AtmosphericConfiguredFeatures.YUCCA.getHolder().get();
		}
	}
}
