package com.bagel.atmospheric.common.block.trees;

import java.util.Random;

import javax.annotation.Nullable;

import com.bagel.atmospheric.common.world.biome.AtmosphericFeatureConfigs;
import com.bagel.atmospheric.core.registry.AtmosphericFeatures;

import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;

public class RosewoodTree extends Tree {
	
	@Nullable
	protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getTreeFeature(Random randomIn, boolean beehive) {
		return AtmosphericFeatures.ROSEWOOD_TREE.withConfiguration(beehive ? AtmosphericFeatureConfigs.ROSEWOOD_TREE_WITH_MORE_BEEHIVES_CONFIG : AtmosphericFeatureConfigs.ROSEWOOD_TREE_CONFIG);
	}
}
