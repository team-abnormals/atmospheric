package com.minecraftabnormals.atmospheric.common.world.gen.trees;

import com.minecraftabnormals.atmospheric.core.registry.AtmosphericFeatures;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import javax.annotation.Nullable;
import java.util.Random;

public class RosewoodTree extends AbstractTreeGrower {

	@Nullable
	protected ConfiguredFeature<TreeConfiguration, ?> getConfiguredFeature(Random randomIn, boolean beehive) {
		return AtmosphericFeatures.ROSEWOOD_TREE.get().configured(beehive ? AtmosphericFeatures.Configs.ROSEWOOD_TREE_WITH_MORE_BEEHIVES_CONFIG : AtmosphericFeatures.Configs.ROSEWOOD_TREE_CONFIG);
	}
}
