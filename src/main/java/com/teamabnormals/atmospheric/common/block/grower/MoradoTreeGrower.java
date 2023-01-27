package com.teamabnormals.atmospheric.common.block.grower;

import com.teamabnormals.atmospheric.core.registry.AtmosphericFeatures.AtmosphericConfiguredFeatures;
import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import javax.annotation.Nullable;
import java.util.Random;

public class MoradoTreeGrower extends AbstractTreeGrower {

	@Override
	@Nullable
	protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource random, boolean beehive) {
		return beehive ? AtmosphericConfiguredFeatures.MORADO_BEES_005.getHolder().get() : AtmosphericConfiguredFeatures.MORADO.getHolder().get();
	}
}
