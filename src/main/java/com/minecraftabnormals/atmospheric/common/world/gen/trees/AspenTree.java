package com.minecraftabnormals.atmospheric.common.world.gen.trees;

import com.minecraftabnormals.atmospheric.core.registry.AtmosphericFeatures;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import javax.annotation.Nullable;
import java.util.Random;

public class AspenTree extends AbstractTreeGrower {

	@Nullable
	protected ConfiguredFeature<TreeConfiguration, ?> getConfiguredFeature(Random randomIn, boolean p_225546_2_) {
		return AtmosphericFeatures.ASPEN_TREE.get().configured(AtmosphericFeatures.Configs.ASPEN_TREE_CONFIG);
	}
}
