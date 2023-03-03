package com.teamabnormals.atmospheric.common.levelgen.feature;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Plane;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;

import java.util.ArrayList;

public class AspenTreeFeature extends AtmosphericTreeFeature {

	public AspenTreeFeature(Codec<TreeConfiguration> config) {
		super(config);
	}

	@Override
	public void doPlace(FeaturePlaceContext<TreeConfiguration> context) {
		TreeConfiguration config = context.config();
		RandomSource random = context.random();
		BlockPos origin = context.origin();

		int trunkHeight = config.trunkPlacer.getTreeHeight(random);
		int leafHeight = trunkHeight - 7 - random.nextInt(3) - random.nextInt(3);
		int branchHeight = leafHeight - 2 - random.nextInt(3);
		int bonusBranchHeight = branchHeight - 2 - random.nextInt(3);

		for (int y = 0; y < trunkHeight; y++) {
			BlockPos pos = origin.above(y);
			this.addLog(pos);

			if (y >= leafHeight) {
				for (Direction direction : Plane.HORIZONTAL) {
					this.addFoliage(pos.relative(direction));
					BlockPos offsetPos = pos.relative(direction).relative(direction.getClockWise());
					if (y > leafHeight && y < trunkHeight - 1 && (random.nextInt(4) != 0 || !this.foliagePositions.contains(offsetPos.below()))) {
						this.addFoliage(offsetPos);
					}
				}

				if (y > leafHeight + 1 && y < trunkHeight - 2) {
					for (int i = -2; i <= 2; ++i) {
						for (int k = -2; k <= 2; ++k) {
							if ((Math.abs(i) != 2 || Math.abs(k) != 2) && random.nextBoolean()) {
								this.addFoliage(pos.offset(i, 0, k));
							}
						}
					}
				}
			} else if ((y == branchHeight && branchHeight > 3 && random.nextInt(5) != 0) || (y == bonusBranchHeight && bonusBranchHeight > 2 && random.nextInt(3) != 0)) {
				int branchSize = 1 + random.nextInt(2);
				if (random.nextBoolean())
					branchSize += 1 + random.nextInt(2);
				ArrayList<Direction> usedDirections = Lists.newArrayList();
				while (usedDirections.size() < branchSize) {
					Direction randomDirection = Plane.HORIZONTAL.getRandomDirection(random);
					if (!usedDirections.contains(randomDirection))
						usedDirections.add(randomDirection);
				}
				for (Direction direction : usedDirections) {
					this.addFoliage(pos.relative(direction));
				}
			}
		}

		this.addFoliage(origin.above(trunkHeight));
	}

	@Override
	public Block getSapling() {
		return AtmosphericBlocks.ASPEN_SAPLING.get();
	}
}