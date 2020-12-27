package com.minecraftabnormals.atmospheric.common.world.gen.trees;

import com.minecraftabnormals.atmospheric.common.world.gen.feature.config.YuccaTreeFeatureConfig;
import com.minecraftabnormals.atmospheric.core.registry.AtmosphericFeatures;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.trees.Tree;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.Random;

public class YuccaTree extends Tree {
    @Nullable
    protected ConfiguredFeature<YuccaTreeFeatureConfig, ?> getYuccaTreeFeature(Random randomIn, boolean beehive) {
        return AtmosphericFeatures.YUCCA_TREE.get().withConfiguration(randomIn.nextInt(10) == 0 ? AtmosphericFeatures.Configs.BABY_YUCCA_TREE_CONFIG : beehive ? AtmosphericFeatures.Configs.YUCCA_TREE_WITH_MORE_BEEHIVES_CONFIG : AtmosphericFeatures.Configs.YUCCA_TREE_CONFIG);
    }

    @Override
    public boolean attemptGrowTree(ServerWorld world, ChunkGenerator chunkGenerator, BlockPos pos, BlockState state, Random rand) {
        ConfiguredFeature<YuccaTreeFeatureConfig, ?> configuredfeature = this.getYuccaTreeFeature(rand, this.hasNearbyFlora(world, pos));
        if (configuredfeature == null) {
            return false;
        } else {
            world.setBlockState(pos, Blocks.AIR.getDefaultState(), 4);
            configuredfeature.config.forcePlacement();
            if (configuredfeature.generate(world, chunkGenerator, rand, pos)) {
                return true;
            } else {
                world.setBlockState(pos, state, 4);
                return false;
            }
        }
    }

    private boolean hasNearbyFlora(IWorld world, BlockPos pos) {
        for (BlockPos blockpos : BlockPos.Mutable.getAllInBoxMutable(pos.down().north(2).west(2), pos.up().south(2).east(2))) {
            if (world.getBlockState(blockpos).isIn(BlockTags.FLOWERS)) {
                return true;
            }
        }

        return false;
    }

    @Override
    protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getTreeFeature(Random randomIn, boolean largeHive) {
        return null;
    }
}
