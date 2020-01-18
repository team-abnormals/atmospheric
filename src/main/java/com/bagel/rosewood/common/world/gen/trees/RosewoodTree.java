package com.bagel.rosewood.common.world.gen.trees;

import java.util.Random;

import javax.annotation.Nullable;

import com.bagel.rosewood.common.world.gen.feature.RosewoodTreeFeature;

import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class RosewoodTree extends Tree {
	   @Nullable
	   protected AbstractTreeFeature<NoFeatureConfig> getTreeFeature(Random random) {
	      return new RosewoodTreeFeature(NoFeatureConfig::deserialize, true);
	   }
	}
