package com.bagel.atmospheric.common.block.trees;

import java.util.Random;

import javax.annotation.Nullable;

import com.bagel.atmospheric.common.world.gen.feature.AspenTreeFeature;
import com.bagel.atmospheric.common.world.gen.feature.MegaAspenTree;

import net.minecraft.block.trees.BigTree;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class AspenTree extends BigTree {
   @Nullable
   protected AbstractTreeFeature<NoFeatureConfig> getTreeFeature(Random random) {
      return new AspenTreeFeature(NoFeatureConfig::deserialize, true);
   }

   @Nullable
   protected AbstractTreeFeature<NoFeatureConfig> getBigTreeFeature(Random random) {
      return new MegaAspenTree(NoFeatureConfig::deserialize, false, random.nextBoolean());
   }
}
