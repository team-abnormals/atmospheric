package com.minecraftabnormals.atmospheric.common.block.trees;

import java.util.Random;

import javax.annotation.Nullable;

import com.minecraftabnormals.atmospheric.common.world.biome.AtmosphericFeatureConfigs;

import net.minecraft.block.trees.BigTree;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;

public class AspenTree extends BigTree {

   @Nullable
   protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getTreeFeature(Random randomIn, boolean p_225546_2_) {
      return Feature.field_236291_c_.withConfiguration(AtmosphericFeatureConfigs.ASPEN_TREE_CONFIG);
   }

   @Nullable
   protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getHugeTreeFeature(Random rand) {
      return Feature.field_236291_c_.withConfiguration(AtmosphericFeatureConfigs.MEGA_ASPEN_TREE_CONFIG);
   }
}
