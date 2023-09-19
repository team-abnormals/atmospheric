package com.teamabnormals.atmospheric.common.levelgen.feature;

import com.mojang.serialization.Codec;
import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import com.teamabnormals.blueprint.common.levelgen.feature.BlueprintTreeFeature;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;

public class BabyTreeFeature extends BlueprintTreeFeature {

	public BabyTreeFeature(Codec<TreeConfiguration> config) {
		super(config);
	}

	@Override
	public void doPlace(FeaturePlaceContext<TreeConfiguration> context) {
		TreeConfiguration config = context.config();
		RandomSource random = context.random();
		BlockPos origin = context.origin();

		int trunkHeight = config.trunkPlacer.getTreeHeight(random);
		for (int y = 0; y < trunkHeight; y++) {
			this.addLog(origin.above(y));
		}

		BlockPos pos = origin.above(trunkHeight);
		this.createLeafLayer(pos, random, false);
		this.createLeafLayer(pos.below(), random, true);
		this.createLeafLayer(pos.below(2), random, false);
	}

	@Override
	public BlockState getSapling() {
		return AtmosphericBlocks.KOUSA_SAPLING.get().defaultBlockState();
	}

	public void createLeafLayer(BlockPos pos, RandomSource random, boolean square) {
		int leafSize = 1;
		for (int i = -leafSize; i <= leafSize; ++i) {
			for (int k = -leafSize; k <= leafSize; ++k) {
				if (square) {
					this.addFoliage(pos.offset(i, 0, k));
				} else {
					if ((Math.abs(i) != leafSize || Math.abs(k) != leafSize)) {
						this.addFoliage(pos.offset(i, 0, k));
					}
//					if (random.nextInt(4) == 0) {
//						this.addFoliage(pos.offset(i, 0, k));
//					}
				}
			}
		}
	}
}