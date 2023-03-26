package com.teamabnormals.atmospheric.common.levelgen.feature;

import com.mojang.serialization.Codec;
import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;

public class SmallBushFeature extends AtmosphericTreeFeature {

	public SmallBushFeature(Codec<TreeConfiguration> config) {
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

		this.createLeafLayer(origin, true, random);
		this.createLeafLayer(origin.above(trunkHeight), false, random);
	}

	private void createLeafLayer(BlockPos pos, boolean square, RandomSource random) {
		int leafSize = 1;
		for (int i = -leafSize; i <= leafSize; ++i) {
			for (int k = -leafSize; k <= leafSize; ++k) {
				boolean corner = (Math.abs(i) == leafSize && Math.abs(k) == leafSize);
				if (square) {
					if (!corner || random.nextInt(3) != 0) {
						this.addFoliage(pos.offset(i, 0, k));
					}
				} else {
					if (!corner && (random.nextInt(3) != 0 || i == k)) {
						this.addFoliage(pos.offset(i, 0, k));
					}
				}
			}
		}
	}

	@Override
	public BlockState getSapling() {
		return AtmosphericBlocks.YUCCA_SAPLING.get().defaultBlockState();
	}
}