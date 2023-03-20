package com.teamabnormals.atmospheric.common.levelgen.feature;

import com.google.common.collect.Sets;
import com.mojang.serialization.Codec;
import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockPos.MutableBlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.core.Direction.Plane;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;

import java.util.Set;

public class LaurelTreeFeature extends AtmosphericTreeFeature {

	public LaurelTreeFeature(Codec<TreeConfiguration> config) {
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

		Axis axis = Plane.HORIZONTAL.getRandomAxis(random);
		Plane.HORIZONTAL.stream().filter(direction -> direction.getAxis() == axis).forEach(direction -> {
			this.createBranch(origin.above(trunkHeight), direction, random);
		});
	}

	@Override
	public Block getSapling() {
		return AtmosphericBlocks.YUCCA_SAPLING.get();
	}

	private void createBranch(BlockPos pos, Direction direction, RandomSource random) {
		MutableBlockPos mutablePos = new MutableBlockPos();
		mutablePos.set(pos.relative(direction));

		int firstHeight = 1 + random.nextInt(2);
		mutablePos.set(mutablePos.below(random.nextInt(2)));
		for (int i = 0; i < firstHeight; i++) {
			this.addLog(mutablePos.above(i));
		}

		int secondHeight = 1 + random.nextInt(2);
		mutablePos.set(mutablePos.relative(direction).above(firstHeight - random.nextInt(2)));
		for (int i = 0; i < secondHeight; i++) {
			this.addLog(mutablePos.above(i));
		}

		mutablePos.set(mutablePos.above(secondHeight - 1));
		this.createLeafLayer(mutablePos.above(), false, random);
		this.createLeafLayer(mutablePos, true, random);
		this.createLeafLayer(mutablePos.below(), false, random);
		this.createVines(mutablePos.below().immutable(), direction, random);
	}

	private void createLeafLayer(BlockPos pos, boolean square, RandomSource random) {
		int leafSize = 1;
		for (int i = -leafSize; i <= leafSize; ++i) {
			for (int k = -leafSize; k <= leafSize; ++k) {
				if (square) {
					this.addFoliage(pos.offset(i, 0, k));
				} else {
					if ((Math.abs(i) != leafSize || Math.abs(k) != leafSize)) {
						this.addFoliage(pos.offset(i, 0, k));
					} else if (random.nextInt(4) == 0) {
						this.addFoliage(pos.offset(i, 0, k));
					}
				}
			}
		}
	}

	private void createVines(BlockPos pos, Direction direction, RandomSource random) {
		Set<BlockPos> vinePositions = Sets.newHashSet();
		int vineCount = 2 + random.nextInt(2) + random.nextInt(2);
		int placedVines = 0;

		while (placedVines < vineCount) {
			MutableBlockPos vinePos = new MutableBlockPos();
			vinePos.set(pos);

			if (random.nextInt(5) < 3) {
				vinePos.set(vinePos.relative(direction));
			}

			if (random.nextInt(5) < 4) {
				vinePos.set(random.nextBoolean() ? vinePos.relative(direction.getClockWise()) : vinePos.relative(direction.getCounterClockWise()));
			}

			boolean canGen = pos.getX() != vinePos.getX() || pos.getZ() != vinePos.getZ();
			for (Direction vineDirection : Direction.values()) {
				if (vinePositions.contains(vinePos.relative(vineDirection))) {
					canGen = false;
				}
			}


			if (canGen) {
				int length = 2 + random.nextInt(2);
				for (int i = 0; i < length; i++) {
					this.addFoliage(vinePos.below(i));
				}

				placedVines++;
				vinePositions.add(vinePos);
			}
		}
	}
}