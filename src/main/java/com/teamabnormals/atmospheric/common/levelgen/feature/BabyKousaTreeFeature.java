package com.teamabnormals.atmospheric.common.levelgen.feature;

import com.mojang.serialization.Codec;
import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import com.teamabnormals.blueprint.common.levelgen.feature.BlueprintTreeFeature;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;

public class BabyKousaTreeFeature extends BlueprintTreeFeature {

	public BabyKousaTreeFeature(Codec<TreeConfiguration> config) {
		super(config);
	}

	@Override
	public void doPlace(FeaturePlaceContext<TreeConfiguration> context, TreeInfo info) {
		TreeConfiguration config = context.config();
		RandomSource random = context.random();
		BlockPos origin = context.origin();

		int trunkHeight = config.trunkPlacer.getTreeHeight(random);
		for (int y = 0; y < trunkHeight; y++) {
			info.addLog(origin.above(y));
		}

		BlockPos pos = origin.above(trunkHeight);
		this.createLeafLayer(info, pos, false);
		this.createLeafLayer(info, pos.below(), true);
		this.createLeafLayer(info, pos.below(2), false);
	}

	@Override
	public BlockState getSapling() {
		return AtmosphericBlocks.KOUSA_SAPLING.get().defaultBlockState();
	}

	public void createLeafLayer(TreeInfo info, BlockPos pos, boolean square) {
		for (int i = -1; i <= 1; ++i) {
			for (int k = -1; k <= 1; ++k) {
				if (square) {
					info.addFoliage(pos.offset(i, 0, k));
				} else {
					if ((Math.abs(i) != 1 || Math.abs(k) != 1)) {
						info.addFoliage(pos.offset(i, 0, k));
					}
				}
			}
		}
	}
}