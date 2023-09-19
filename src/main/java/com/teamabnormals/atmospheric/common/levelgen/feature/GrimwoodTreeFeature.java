package com.teamabnormals.atmospheric.common.levelgen.feature;

import com.mojang.serialization.Codec;
import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import com.teamabnormals.blueprint.common.levelgen.feature.BlueprintTreeFeature;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockPos.MutableBlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.core.Direction.AxisDirection;
import net.minecraft.core.Direction.Plane;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;

public class GrimwoodTreeFeature extends BlueprintTreeFeature {

	public GrimwoodTreeFeature(Codec<TreeConfiguration> config) {
		super(config);
	}

	@Override
	public void doPlace(FeaturePlaceContext<TreeConfiguration> context) {
		TreeConfiguration config = context.config();
		RandomSource random = context.random();
		BlockPos origin = context.origin();

		int trunkHeight = config.trunkPlacer.getTreeHeight(random);
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < trunkHeight; j++) {
				for (int k = 0; k < 2; k++) {
					this.addLog(origin.offset(i, j, k));
				}
			}
		}

		Direction direction = Plane.HORIZONTAL.getRandomDirection(random);
		boolean positive = direction.getAxisDirection() == AxisDirection.POSITIVE;
		boolean xAxis = direction.getAxis() == Axis.X;

		MutableBlockPos pos = new MutableBlockPos();
		pos.set(origin.offset(xAxis ? positive ? 1 : 0 : random.nextInt(2), trunkHeight, !xAxis ? positive ? 1 : 0 : random.nextInt(2)));

		this.addLog(pos);

		this.createBranch(2 + random.nextInt(2), 3 + random.nextInt(2), pos, direction, random, config);
		if (random.nextInt(3) != 0) {
			this.createBranch(2 + random.nextInt(3), 3 + random.nextInt(2), pos, direction.getOpposite(), random, config);
			this.createBranch(1 + random.nextInt(2), 2 + random.nextInt(2), pos, direction, random, config);
		}

		int height = 5 + random.nextInt(5) + random.nextInt(3);
		this.createBranch(1 + random.nextInt(3), height, pos, direction.getOpposite(), random, config);
		this.addFoliage(pos.above());
		this.createLeafLayer(pos, 1, true);
		boolean big = false;
		for (int i = 0; i < height - 2 - random.nextInt(2); i++) {
			if (!big) big = i > 3 && random.nextBoolean();
			if (i % 2 == 0) {
				this.createLeafLayer(pos.set(pos.below(2)), big ? 3 : 2, false);
			}
		}
	}

	@Override
	public BlockState getSapling() {
		return AtmosphericBlocks.GRIMWOOD_SAPLING.get().defaultBlockState();
	}

	private void createBranch(int depth, int height, MutableBlockPos pos, Direction direction, RandomSource random, TreeConfiguration config) {
		for (int j = 0; j < depth; j++) {
			this.addSpecialLog(pos.set(pos.relative(direction)), config.trunkProvider.getState(random, pos).setValue(BlockStateProperties.AXIS, direction.getAxis()));
		}

		for (int j = 0; j < height; j++) {
			this.addLog(pos.set(pos.above()));
		}
	}

	private void createLeafLayer(BlockPos pos, int leafSize, boolean square) {
		for (int i = -leafSize; i <= leafSize; ++i) {
			for (int k = -leafSize; k <= leafSize; ++k) {
				if (square) {
					this.addFoliage(pos.offset(i, 0, k));
				} else {
					int xDis = Math.abs(i);
					int zDis = Math.abs(k);
					if ((xDis != leafSize || zDis != leafSize) && (leafSize <= 2 || ((xDis != leafSize || zDis != leafSize - 1) && (xDis != leafSize - 1 || zDis != leafSize)))) {
						this.addFoliage(pos.offset(i, 0, k));
					}
				}
			}
		}
	}
}