package com.teamabnormals.atmospheric.common.levelgen.feature;

import com.mojang.serialization.Codec;
import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.BambooBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BambooLeaves;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration;

public class SnowyBambooFeature extends Feature<ProbabilityFeatureConfiguration> {

	public SnowyBambooFeature(Codec<ProbabilityFeatureConfiguration> codec) {
		super(codec);
	}

	public boolean place(FeaturePlaceContext<ProbabilityFeatureConfiguration> context) {
		BlockState bambooTrunk = AtmosphericBlocks.SNOWY_BAMBOO.get().defaultBlockState().setValue(BambooBlock.AGE, 1).setValue(BambooBlock.LEAVES, BambooLeaves.NONE).setValue(BambooBlock.STAGE, Integer.valueOf(0));
		BlockState bambooFinalLarge = bambooTrunk.setValue(BambooBlock.LEAVES, BambooLeaves.LARGE).setValue(BambooBlock.STAGE, 1);
		BlockState bambooTopLarge = bambooTrunk.setValue(BambooBlock.LEAVES, BambooLeaves.LARGE);
		BlockState bambooTopSmall = bambooTrunk.setValue(BambooBlock.LEAVES, BambooLeaves.SMALL);

		int i = 0;
		BlockPos origin = context.origin();
		WorldGenLevel level = context.level();
		RandomSource random = context.random();
		ProbabilityFeatureConfiguration config = context.config();
		BlockPos.MutableBlockPos bambooPos = origin.mutable();
		BlockPos.MutableBlockPos snowPos = origin.mutable();

		if (level.isEmptyBlock(bambooPos)) {
			if (Blocks.BAMBOO.defaultBlockState().canSurvive(level, bambooPos)) {
				level.setBlock(bambooPos.below(), Blocks.SNOW_BLOCK.defaultBlockState(), 2);

				int j = random.nextInt(12) + 5;
				if (random.nextFloat() < config.probability) {
					int k = random.nextInt(4) + 1;

					for (int l = origin.getX() - k; l <= origin.getX() + k; ++l) {
						for (int i1 = origin.getZ() - k; i1 <= origin.getZ() + k; ++i1) {
							int j1 = l - origin.getX();
							int k1 = i1 - origin.getZ();
							if (j1 * j1 + k1 * k1 <= k * k) {
								snowPos.set(l, level.getHeight(Heightmap.Types.WORLD_SURFACE, l, i1) - 1, i1);
								if (isDirt(level.getBlockState(snowPos))) {
									level.setBlock(snowPos, Blocks.SNOW_BLOCK.defaultBlockState(), 2);
								}
							}
						}
					}
				}

				for (int l1 = 0; l1 < j && level.isEmptyBlock(bambooPos); ++l1) {
					level.setBlock(bambooPos, bambooTrunk, 2);
					bambooPos.move(Direction.UP, 1);
				}

				if (bambooPos.getY() - origin.getY() >= 3) {
					level.setBlock(bambooPos, bambooFinalLarge, 2);
					level.setBlock(bambooPos.move(Direction.DOWN, 1), bambooTopLarge, 2);
					level.setBlock(bambooPos.move(Direction.DOWN, 1), bambooTopSmall, 2);
				}
			}

			++i;
		}

		return i > 0;
	}
}