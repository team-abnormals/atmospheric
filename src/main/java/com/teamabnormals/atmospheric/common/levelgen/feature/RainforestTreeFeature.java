package com.teamabnormals.atmospheric.common.levelgen.feature;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import com.teamabnormals.blueprint.common.levelgen.feature.BlueprintTreeFeature;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockPos.MutableBlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Plane;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;

import java.util.ArrayList;

public class RainforestTreeFeature extends BlueprintTreeFeature {

	public RainforestTreeFeature(Codec<TreeConfiguration> config) {
		super(config);
	}

	@Override
	public void doPlace(FeaturePlaceContext<TreeConfiguration> context) {
		TreeConfiguration config = context.config();
		RandomSource random = context.random();
		BlockPos origin = context.origin();

		boolean morado = config.trunkProvider.getState(random, origin).is(AtmosphericBlocks.MORADO_LOG.get());

		int branches = 2 + random.nextInt(3) - (!morado ? 0 : 1);
		int trunkHeight = config.trunkPlacer.getTreeHeight(random);

		boolean tall = !morado && random.nextInt(100) == 0;
		if (tall) {
			trunkHeight += 6 + random.nextInt(4) + random.nextInt(3);
		}

		boolean canopy = false;
		for (int y = 0; y < trunkHeight; ++y) {
			BlockPos pos = origin.above(y);
			this.addLog(pos);
			if (random.nextInt(6) == 0 && y > (!tall ? 3 : 8) && !canopy) {
				this.createLeafLayer(pos, 1 + random.nextInt(2));
				canopy = true;
			}
		}

		ArrayList<Direction> directions = Lists.newArrayList();
		while (directions.size() < branches) {
			Direction randomDirection = Plane.HORIZONTAL.getRandomDirection(random);
			if (!directions.contains(randomDirection))
				directions.add(randomDirection);
		}

		for (Direction direction : directions) {
			int turns = 1 + random.nextInt(3);
			MutableBlockPos pos = new MutableBlockPos();
			pos.set(origin.above(trunkHeight - 1));

			for (int k4 = 0; k4 < turns; ++k4) {
				int branchLength = tall ? 2 + random.nextInt(2) : !morado ? 1 + random.nextInt(2) + random.nextInt(2) : 1 + random.nextInt(2);
				int branchHeight = tall ? 2 + random.nextInt(2) + random.nextInt(2) : !morado ? 1 + random.nextInt(3) + random.nextInt(2) : 1 + random.nextInt(2);
				this.createHorizontalLog(branchLength, pos, direction, random, config);
				this.createVerticalLog(branchHeight, pos, random);
			}

			int leafSize = 2 + random.nextInt(2);
			this.createLeafLayer(pos, leafSize);
			this.createLeafLayer(pos.above(), Math.max(leafSize - 1, 1));
		}
	}

	public void createLeafLayer(BlockPos pos, int leafSize) {
		for (int i = -leafSize; i <= leafSize; ++i) {
			for (int k = -leafSize; k <= leafSize; ++k) {
				if ((Math.abs(i) != leafSize || Math.abs(k) != leafSize)) {
					this.addFoliage(pos.offset(i, 0, k));
				}
			}
		}
	}

	private void createHorizontalLog(int branchLength, MutableBlockPos pos, Direction direction, RandomSource random, TreeConfiguration config) {
		for (int i = 0; i < branchLength; ++i) {
			pos.setWithOffset(pos, direction);
			this.addSpecialLog(pos, config.trunkProvider.getState(random, pos).setValue(BlockStateProperties.AXIS, direction.getAxis()));
		}
	}

	private void createVerticalLog(int branchHeight, MutableBlockPos pos, RandomSource random) {
		boolean canopy = false;
		for (int i = 0; i < branchHeight; ++i) {
			pos.set(pos.above());
			this.addLog(pos);
			if (random.nextInt(6) == 0 && !canopy) {
				this.createLeafLayer(pos, 1 + random.nextInt(2));
				canopy = true;
			}
		}
	}

	@Override
	public BlockState getSapling() {
		return AtmosphericBlocks.ROSEWOOD_SAPLING.get().defaultBlockState();
	}

	@Override
	public boolean canSurvive(WorldGenLevel level, BlockPos pos) {
		return super.canSurvive(level, pos) || level.getBlockState(pos.below()).is(Blocks.GRAVEL);
	}
}