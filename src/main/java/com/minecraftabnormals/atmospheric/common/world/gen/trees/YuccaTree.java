package com.minecraftabnormals.atmospheric.common.world.gen.trees;

import com.minecraftabnormals.atmospheric.common.world.gen.feature.config.YuccaTreeFeatureConfig;
import com.minecraftabnormals.atmospheric.core.registry.AtmosphericFeatures;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.tags.BlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.server.level.ServerLevel;

import javax.annotation.Nullable;
import java.util.Random;

public class YuccaTree extends AbstractTreeGrower {
	@Nullable
	protected ConfiguredFeature<YuccaTreeFeatureConfig, ?> getYuccaTreeFeature(Random randomIn, boolean beehive) {
		return AtmosphericFeatures.YUCCA_TREE.get().configured(randomIn.nextInt(10) == 0 ? AtmosphericFeatures.Configs.BABY_YUCCA_TREE_CONFIG : beehive ? AtmosphericFeatures.Configs.YUCCA_TREE_WITH_MORE_BEEHIVES_CONFIG : AtmosphericFeatures.Configs.YUCCA_TREE_CONFIG);
	}

	@Override
	public boolean growTree(ServerLevel world, ChunkGenerator chunkGenerator, BlockPos pos, BlockState state, Random rand) {
		ConfiguredFeature<YuccaTreeFeatureConfig, ?> configuredfeature = this.getYuccaTreeFeature(rand, this.hasNearbyFlora(world, pos));
		if (configuredfeature == null) {
			return false;
		} else {
			world.setBlock(pos, Blocks.AIR.defaultBlockState(), 4);
			configuredfeature.config.forcePlacement();
			if (configuredfeature.place(world, chunkGenerator, rand, pos)) {
				return true;
			} else {
				world.setBlock(pos, state, 4);
				return false;
			}
		}
	}

	private boolean hasNearbyFlora(LevelAccessor world, BlockPos pos) {
		for (BlockPos blockpos : BlockPos.MutableBlockPos.betweenClosed(pos.below().north(2).west(2), pos.above().south(2).east(2))) {
			if (world.getBlockState(blockpos).is(BlockTags.FLOWERS)) {
				return true;
			}
		}

		return false;
	}

	@Override
	protected ConfiguredFeature<TreeConfiguration, ?> getConfiguredFeature(Random randomIn, boolean largeHive) {
		return null;
	}
}
