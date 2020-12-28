package com.minecraftabnormals.atmospheric.common.world.gen.trees;

import com.minecraftabnormals.atmospheric.core.registry.AtmosphericFeatures;
import net.minecraft.block.trees.BigTree;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;

import javax.annotation.Nullable;
import java.util.Random;

public class AspenTree extends BigTree {

	@Nullable
	protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getTreeFeature(Random randomIn, boolean p_225546_2_) {
		return Feature.TREE.withConfiguration(AtmosphericFeatures.Configs.ASPEN_TREE_CONFIG);
	}

	@Nullable
	protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getHugeTreeFeature(Random rand) {
		return Feature.TREE.withConfiguration(AtmosphericFeatures.Configs.MEGA_ASPEN_TREE_CONFIG);
	}
}
