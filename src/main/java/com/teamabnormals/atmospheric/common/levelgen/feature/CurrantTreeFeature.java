package com.teamabnormals.atmospheric.common.levelgen.feature;

import com.mojang.serialization.Codec;
import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;

public class CurrantTreeFeature extends AtmosphericTreeFeature {

	public CurrantTreeFeature(Codec<TreeConfiguration> config) {
		super(false, config);
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

		int bottomSize = Math.max(5, 3 + random.nextInt(3) + random.nextInt(2));
		int topSize = Math.max(3, 2 + random.nextInt(2) + random.nextInt(2));

		this.createLeafLayer(origin.above(), bottomSize, random);
		this.createLeafLayer(origin.above(trunkHeight), topSize, random);
	}

	@Override
	public BlockState getSapling() {
		return AtmosphericBlocks.CURRANT_SEEDLING.get().defaultBlockState();
	}

	private void createLeafLayer(BlockPos pos, int leafSize, RandomSource random) {
		BlockPos offsetPos = pos.offset(-1 - random.nextInt(leafSize - 2), 0, -1 - random.nextInt(leafSize - 2));
		for (int i = 0; i < leafSize; i++) {
			for (int k = 0; k < leafSize; k++) {
				this.addFoliage(offsetPos.offset(i, 0, k));
			}
		}
	}
}