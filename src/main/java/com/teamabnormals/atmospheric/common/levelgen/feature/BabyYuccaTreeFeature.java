package com.teamabnormals.atmospheric.common.levelgen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;

public class BabyYuccaTreeFeature extends YuccaTreeFeature {

	public BabyYuccaTreeFeature(Codec<TreeConfiguration> config) {
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
		this.createLeafLayer(info, pos, random, false);
		this.createLeafLayer(info, pos.below(), random, true);
		this.createLeafLayer(info, pos.below(2), random, false);
	}
}