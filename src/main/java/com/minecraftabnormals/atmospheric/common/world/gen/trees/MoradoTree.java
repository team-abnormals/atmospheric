package com.minecraftabnormals.atmospheric.common.world.gen.trees;

import java.util.Random;

import javax.annotation.Nullable;

import com.minecraftabnormals.atmospheric.common.world.biome.AtmosphericFeatureConfigs;
import com.minecraftabnormals.atmospheric.core.registry.AtmosphericFeatures;

import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;

public class MoradoTree extends Tree {
	
	@Nullable
	protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getTreeFeature(Random randomIn, boolean beehive) {
		return AtmosphericFeatures.ROSEWOOD_TREE.withConfiguration(beehive ? AtmosphericFeatureConfigs.MORADO_TREE_WITH_MORE_BEEHIVES_CONFIG : AtmosphericFeatureConfigs.MORADO_TREE_CONFIG);
	}
}
