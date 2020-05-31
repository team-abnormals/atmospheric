package com.bagel.atmospheric.common.block.trees;

import java.util.Random;

import javax.annotation.Nullable;

import com.bagel.atmospheric.common.world.biome.AtmosphericFeatureConfigs;
import com.bagel.atmospheric.core.registry.AtmosphericFeatures;

import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

public class YuccaTree extends Tree {
	@Nullable
	protected ConfiguredFeature<TreeFeatureConfig, ?> getTreeFeature(Random randomIn, boolean p_225546_2_) {
		return randomIn.nextInt(10) == 0 ? 
				AtmosphericFeatures.BABY_YUCCA_TREE.withConfiguration(AtmosphericFeatureConfigs.YUCCA_TREE_CONFIG) : 
					AtmosphericFeatures.YUCCA_TREE.withConfiguration(AtmosphericFeatureConfigs.YUCCA_TREE_CONFIG);
	}
}
