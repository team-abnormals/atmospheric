package com.bagel.atmospheric.common.block.trees;

import java.util.Random;
import javax.annotation.Nullable;

import com.bagel.atmospheric.common.world.biome.AtmosphericBiomeFeatures;

import net.minecraft.block.trees.BigTree;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.HugeTreeFeatureConfig;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

public class AspenTree extends BigTree {
   /**
    * Get a {@link net.minecraft.world.gen.feature.ConfiguredFeature} of tree
    */
   @Nullable
   protected ConfiguredFeature<TreeFeatureConfig, ?> getTreeFeature(Random randomIn, boolean p_225546_2_) {
      return Feature.NORMAL_TREE.withConfiguration(AtmosphericBiomeFeatures.ASPEN_TREE_CONFIG);
   }

   /**
    * Get a {@link net.minecraft.world.gen.feature.ConfiguredFeature} of the huge variant of this tree
    */
   @Nullable
   protected ConfiguredFeature<HugeTreeFeatureConfig, ?> getHugeTreeFeature(Random rand) {
      return Feature.MEGA_SPRUCE_TREE.withConfiguration(rand.nextBoolean() ? AtmosphericBiomeFeatures.MEGA_ASPEN_TREE_CONFIG : AtmosphericBiomeFeatures.MEGA_ASPEN_TREE_CONFIG);
   }
}
