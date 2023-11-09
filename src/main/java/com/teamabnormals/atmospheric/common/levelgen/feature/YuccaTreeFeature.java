package com.teamabnormals.atmospheric.common.levelgen.feature;

import com.mojang.serialization.Codec;
import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import com.teamabnormals.blueprint.common.levelgen.feature.BlueprintTreeFeature;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Plane;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;

public class YuccaTreeFeature extends BlueprintTreeFeature {

	public YuccaTreeFeature(Codec<TreeConfiguration> config) {
		super(config);
	}

	@Override
	public void doPlace(FeaturePlaceContext<TreeConfiguration> context) {
		TreeConfiguration config = context.config();
		RandomSource random = context.random();
		BlockPos origin = context.origin();

		int trunkHeight = config.trunkPlacer.getTreeHeight(random);
		for (int y = 0; y < trunkHeight; ++y) {
			this.addLog(origin.above(y));
		}

		for (Direction direction : Plane.HORIZONTAL) {
			BlockPos pos = this.createYuccaBranch(origin.above(trunkHeight - 1), direction, random);
			this.createLeafLayer(pos.above(), random, false);
			this.createLeafLayer(pos, random, true);
			this.createLeafLayer(pos.below(), random, false);
		}
	}

	@Override
	public BlockState getSapling() {
		return AtmosphericBlocks.YUCCA_SAPLING.get().defaultBlockState();
	}

	public BlockPos createYuccaBranch(BlockPos pos, Direction direction, RandomSource rand) {
		int length = 4 + rand.nextInt(2);
		for (int i = 0; i < length; i++) {
			this.addLog(pos.relative(direction));
			if (i != length - 1) {
				pos = pos.above().relative(direction, rand.nextInt(2));
			}
		}

		return pos.relative(direction);
	}

	public void createLeafLayer(BlockPos newPos, RandomSource random, boolean square) {
		for (int i = -1; i <= 1; ++i) {
			for (int k = -1; k <= 1; ++k) {
				if (square) {
					this.addFoliage(newPos.offset(i, 0, k));
				} else {
					if ((Math.abs(i) != 1 || Math.abs(k) != 1)) {
						this.addFoliage(newPos.offset(i, 0, k));
					} else if (random.nextInt(4) == 0) {
						this.addFoliage(newPos.offset(i, 0, k));
					}
				}
			}
		}
	}
}