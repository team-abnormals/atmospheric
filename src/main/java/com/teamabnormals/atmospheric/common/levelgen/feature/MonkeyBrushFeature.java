package com.teamabnormals.atmospheric.common.levelgen.feature;

import com.mojang.serialization.Codec;
import com.teamabnormals.atmospheric.common.block.MonkeyBrushBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;

public class MonkeyBrushFeature extends Feature<SimpleBlockConfiguration> {

	public MonkeyBrushFeature(Codec<SimpleBlockConfiguration> config) {
		super(config);
	}

	@Override
	public boolean place(FeaturePlaceContext<SimpleBlockConfiguration> context) {
		WorldGenLevel level = context.level();
		RandomSource random = context.random();
		int i = 0;
		for (int j = 0; j < 64; ++j) {
			BlockPos pos = context.origin().offset(random.nextInt(8) - random.nextInt(8), random.nextInt(4) - random.nextInt(4), random.nextInt(8) - random.nextInt(8));
			if (MonkeyBrushBlock.attemptBrush(level, pos, context.config().toPlace().getState(random, pos))) {
				i++;
			}
		}

		return i > 0;
	}
}
