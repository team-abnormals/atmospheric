package com.bagel.atmospheric.common.block.trees;

import java.util.Random;

import javax.annotation.Nullable;

import com.bagel.atmospheric.common.world.gen.feature.KousaTreeFeature;

import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class KousaTree extends Tree {
	   @Nullable
	   protected AbstractTreeFeature<NoFeatureConfig> getTreeFeature(Random random) {
	      return new KousaTreeFeature(NoFeatureConfig::deserialize, true);
	   }
	}
