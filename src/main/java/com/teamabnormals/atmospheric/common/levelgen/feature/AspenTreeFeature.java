package com.teamabnormals.atmospheric.common.levelgen.feature;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mojang.serialization.Codec;
import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import com.teamabnormals.blueprint.core.util.TreeUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

public class AspenTreeFeature extends Feature<TreeConfiguration> {

	public AspenTreeFeature(Codec<TreeConfiguration> config) {
		super(config);
	}

	@Override
	public boolean place(FeaturePlaceContext<TreeConfiguration> context) {
		TreeConfiguration config = context.config();
		WorldGenLevel worldIn = context.level();
		Random rand = context.random();
		BlockPos position = context.origin();

		int height = 12 + rand.nextInt(4) + rand.nextInt(5) + rand.nextInt(6);
		boolean flag = true;

		if (position.getY() > worldIn.getMinBuildHeight() && position.getY() + height + 1 <= worldIn.getMaxBuildHeight()) {
			for (int j = position.getY(); j <= position.getY() + 1 + height; ++j) {
				int k = 1;
				if (j == position.getY()) {
					k = 0;
				}
				if (j >= position.getY() + 1 + height - 2) {
					k = 2;
				}
				BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();

				for (int l = position.getX() - k; l <= position.getX() + k && flag; ++l) {
					for (int i1 = position.getZ() - k; i1 <= position.getZ() + k && flag; ++i1) {
						if (j >= 0 && j < worldIn.getMaxBuildHeight()) {
							if (!TreeUtil.isAirOrLeaves(worldIn, mutable.set(l, j, i1))) {
								flag = false;
							}
						} else {
							flag = false;
						}
					}
				}
			}

			if (!flag) {
				return false;
			} else if (TreeUtil.isValidGround(worldIn, position.below(), (SaplingBlock) AtmosphericBlocks.ASPEN_SAPLING.get()) && position.getY() < worldIn.getMaxBuildHeight()) {
				// base log
				TreeUtil.setDirtAt(worldIn, position.below());
				Set<BlockPos> logsPlaced = Sets.newHashSet();

				int logX = position.getX();
				int logZ = position.getZ();
				int leafHeight = height - 7 - rand.nextInt(3) - rand.nextInt(3);
				int branchHeight = leafHeight - 2 - rand.nextInt(3);
				int bonusBranchHeight = branchHeight - 2 - rand.nextInt(3);

				for (int k1 = 0; k1 < height; ++k1) {
					int logY = position.getY() + k1;
					BlockPos blockpos = new BlockPos(logX, logY, logZ);
					if (TreeUtil.isAirOrLeaves(worldIn, blockpos)) {
						TreeUtil.placeDirectionalLogAt(worldIn, blockpos, Direction.UP, rand, config);
						logsPlaced.add(blockpos.immutable());
					}

					if (k1 >= leafHeight) {
						for (Direction direction : Direction.values()) {
							if (direction.getAxis().getPlane() == Direction.Plane.HORIZONTAL) {
								TreeUtil.placeLeafAt(worldIn, blockpos.relative(direction), rand, config);
								BlockPos offsetPos = blockpos.relative(direction).relative(direction.getClockWise());
								if (k1 > leafHeight && k1 < height - 1 && (rand.nextInt(4) != 0 || worldIn.getBlockState(offsetPos.below()).isAir()))
									TreeUtil.placeLeafAt(worldIn, offsetPos, rand, config);
							}
						}

						// Third layer of leaves
						if (k1 > leafHeight + 1 && k1 < height - 2) {
							for (int k3 = -2; k3 <= 2; ++k3) {
								for (int j4 = -2; j4 <= 2; ++j4) {
									if ((Math.abs(k3) != 2 || Math.abs(j4) != 2) && rand.nextBoolean()) {
										TreeUtil.placeLeafAt(worldIn, blockpos.offset(k3, 0, j4), rand, config);
									}
								}
							}
						}
					} else if ((k1 == branchHeight && branchHeight > 3 && rand.nextInt(5) != 0) || (k1 == bonusBranchHeight && bonusBranchHeight > 2 && rand.nextInt(3) != 0)) {
						int branchSize = 1 + rand.nextInt(2);
						if (rand.nextBoolean())
							branchSize += 1 + rand.nextInt(2);
						ArrayList<Direction> usedDirections = Lists.newArrayList();
						while (usedDirections.size() < branchSize) {
							Direction randomDirection = Direction.Plane.HORIZONTAL.getRandomDirection(rand);
							if (!usedDirections.contains(randomDirection))
								usedDirections.add(randomDirection);
						}
						for (Direction direction : usedDirections) {
							TreeUtil.placeLeafAt(worldIn, blockpos.relative(direction), rand, config);
						}
					}
				}

				TreeUtil.placeLeafAt(worldIn, position.offset(0, height, 0), rand, config);
				TreeUtil.updateLeaves(worldIn, logsPlaced);

				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}