package com.minecraftabnormals.atmospheric.common.world.gen.feature;

import com.minecraftabnormals.abnormals_core.core.util.TreeUtil;
import com.minecraftabnormals.atmospheric.common.world.gen.feature.config.YuccaTreeFeatureConfig;
import com.minecraftabnormals.atmospheric.core.other.AtmosphericTags;
import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorldWriter;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;

public class YuccaTreeFeature extends Feature<YuccaTreeFeatureConfig> {
	public YuccaTreeFeature(Codec<YuccaTreeFeatureConfig> config) {
		super(config);
	}

	@Override
	public boolean place(ISeedReader worldIn, ChunkGenerator generator, Random rand, BlockPos position, YuccaTreeFeatureConfig config) {
		if (config.baby) {
			int height = 2 + rand.nextInt(2) + rand.nextInt(2);
			boolean flag = true;

			if (position.getY() >= 1 && position.getY() + height + 1 <= worldIn.getMaxBuildHeight()) {
				for (int j = position.getY(); j <= position.getY() + 1 + height; ++j) {
					int k = 1;
					if (j == position.getY())
						k = 0;
					if (j >= position.getY() + 1 + height - 2)
						k = 2;
					BlockPos.Mutable blockpos$mutableblockpos = new BlockPos.Mutable();
					for (int l = position.getX() - k; l <= position.getX() + k && flag; ++l) {
						for (int i1 = position.getZ() - k; i1 <= position.getZ() + k && flag; ++i1) {
							if (j >= 0 && j < worldIn.getMaxBuildHeight()) {
								if (!TreeUtil.isAirOrLeaves(worldIn, blockpos$mutableblockpos.set(l, j, i1)))
									flag = false;
							} else
								flag = false;
						}
					}
				}

				if (!flag) {
					return false;
				} else if (TreeUtil.isInTag(worldIn, position.below(), AtmosphericTags.YUCCA_PLANTABLE_ON) && position.getY() < worldIn.getMaxBuildHeight()) {
					if (!TreeUtil.isInTag(worldIn, position.below(), BlockTags.SAND))
						TreeUtil.setDirtAt(worldIn, position.below());

					int logX = position.getX();
					int logZ = position.getZ();
					int logY = position.getY();

					for (int k1 = 0; k1 < height; ++k1) {
						logY = position.getY() + k1;
						BlockPos blockpos = new BlockPos(logX, logY, logZ);
						if (TreeUtil.isAirOrLeaves(worldIn, blockpos)) {
							TreeUtil.setForcedState(worldIn, blockpos, config.trunkProvider.getState(rand, blockpos));
						}
					}

					logY = position.getY() + height - 1;

					position = new BlockPos(logX, logY, logZ);

					this.createBabyYuccaLeaves(worldIn, position.above(), rand, config, false);
					this.createBabyYuccaLeaves(worldIn, position, rand, config, true);
					this.createBabyYuccaLeaves(worldIn, position.below(), rand, config, false);

					if (config.patch) {
						for (int j = 0; j < 64; ++j) {
							BlockPos blockpos = position.offset(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));
							if (TreeUtil.isAir(worldIn, blockpos) && blockpos.getY() < 255 && config.flowerProvider.getState(rand, blockpos).canSurvive(worldIn, blockpos)) {
								placeFlowerAt(worldIn, blockpos, rand, config);
							}
						}
					}

					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} else {
			int height = 4 + rand.nextInt(2) + rand.nextInt(2);
			int reduction = 2 + rand.nextInt(3);
			if (config.petrified)
				height -= reduction;
			boolean flag = true;

			if (position.getY() >= 1 && position.getY() + height + 1 <= worldIn.getMaxBuildHeight()) {
				for (int j = position.getY(); j <= position.getY() + 1 + height; ++j) {
					int k = 1;
					if (j == position.getY())
						k = 0;
					if (j >= position.getY() + 1 + height - 2)
						k = 2;
					BlockPos.Mutable blockpos$mutableblockpos = new BlockPos.Mutable();
					for (int l = position.getX() - k; l <= position.getX() + k && flag; ++l) {
						for (int i1 = position.getZ() - k; i1 <= position.getZ() + k && flag; ++i1) {
							if (j >= 0 && j < worldIn.getMaxBuildHeight()) {
								if (!TreeUtil.isAirOrLeaves(worldIn, blockpos$mutableblockpos.set(l, j, i1)))
									flag = false;
							} else
								flag = false;
						}
					}
				}

				if (!flag) {
					return false;
				} else if (TreeUtil.isInTag(worldIn, position.below(), AtmosphericTags.YUCCA_PLANTABLE_ON) && position.getY() < worldIn.getMaxBuildHeight()) {
					// base log
					if (!TreeUtil.isInTag(worldIn, position.below(), BlockTags.SAND))
						TreeUtil.setDirtAt(worldIn, position.below());
					for (int ja = 0; ja < reduction; ++ja) {
						if (config.petrified)
							this.placeLogAt(worldIn, position.below(ja), Direction.UP, false, rand, config);
					}

					int logX = position.getX();
					int logZ = position.getZ();
					int logY = position.getY();

					for (int k1 = 0; k1 < height; ++k1) {
						logY = position.getY() + k1;
						BlockPos blockpos = new BlockPos(logX, logY, logZ);
						if (TreeUtil.isAirOrLeaves(worldIn, blockpos)) {
							this.placeLogAt(worldIn, blockpos, Direction.UP, false, rand, config);
						}
					}

					logY = position.getY() + height - 1;

					BlockPos newPos = this.createYuccaBranch(height, worldIn, position, Direction.NORTH, rand, config);
					this.createYuccaLeaves(worldIn, newPos.above(), rand, false, config);
					this.createYuccaLeaves(worldIn, newPos, rand, true, config);
					this.createYuccaLeaves(worldIn, newPos.below(), rand, false, config);

					newPos = this.createYuccaBranch(height, worldIn, position, Direction.EAST, rand, config);
					this.createYuccaLeaves(worldIn, newPos.above(), rand, false, config);
					this.createYuccaLeaves(worldIn, newPos, rand, true, config);
					this.createYuccaLeaves(worldIn, newPos.below(), rand, false, config);

					newPos = this.createYuccaBranch(height, worldIn, position, Direction.SOUTH, rand, config);
					this.createYuccaLeaves(worldIn, newPos.above(), rand, false, config);
					this.createYuccaLeaves(worldIn, newPos, rand, true, config);
					this.createYuccaLeaves(worldIn, newPos.below(), rand, false, config);

					newPos = this.createYuccaBranch(height, worldIn, position, Direction.WEST, rand, config);
					this.createYuccaLeaves(worldIn, newPos.above(), rand, false, config);
					this.createYuccaLeaves(worldIn, newPos, rand, true, config);
					this.createYuccaLeaves(worldIn, newPos.below(), rand, false, config);

					if (config.patch) {
						for (int j = 0; j < 64; ++j) {
							BlockPos blockpos = position.offset(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));
							if (TreeUtil.isAir(worldIn, blockpos) && blockpos.getY() < 255 && config.flowerProvider.getState(rand, blockpos).canSurvive(worldIn, blockpos)) {
								placeFlowerAt(worldIn, blockpos, rand, config);
							}
						}
					}

					if (config.petrified && rand.nextInt(12) == 0) {
						for (int j = 0; j < 12; ++j) {
							BlockPos blockpos = position.offset(rand.nextInt(6) - rand.nextInt(6), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(6) - rand.nextInt(6));
							if (TreeUtil.isAir(worldIn, blockpos) && blockpos.getY() < 255 && TreeUtil.isInTag(worldIn, blockpos.below(), BlockTags.SAND)) {
								TreeUtil.setForcedState(worldIn, blockpos, config.bundleProvider.getState(rand, blockpos));
							}
						}
					}

					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		}
	}

	private void createYuccaLeaves(IWorldGenerationReader worldIn, BlockPos newPos, Random rand, boolean square, YuccaTreeFeatureConfig config) {
		int leafSize = 1;
		for (int k3 = -leafSize; k3 <= leafSize; ++k3) {
			for (int j4 = -leafSize; j4 <= leafSize; ++j4) {
				if (square) {
					this.placeLeafAt(worldIn, newPos.offset(k3, 0, j4), rand, config);
				} else {
					if ((Math.abs(k3) != leafSize || Math.abs(j4) != leafSize)) {
						this.placeLeafAt(worldIn, newPos.offset(k3, 0, j4), rand, config);
					} else if (rand.nextInt(4) == 0) {
						this.placeLeafAt(worldIn, newPos.offset(k3, 0, j4), rand, config);
					}
				}
			}
		}
	}

	private BlockPos createYuccaBranch(int height, IWorldGenerationReader worldIn, BlockPos pos, Direction direction, Random rand, YuccaTreeFeatureConfig config) {
		int logX = pos.getX();
		int logZ = pos.getZ();
		int logY = pos.getY() + height - 1;
		int length = 4 + rand.nextInt(2);
		BlockPos blockpos = new BlockPos(logX, logY, logZ);
		boolean bundle = false;
		boolean anyBundle = false;

		for (int i = 0; i < length; i++) {
			blockpos = new BlockPos(logX, logY, logZ);
			if (!anyBundle && rand.nextInt(16) == 0) {
				bundle = true;
				anyBundle = true;
			} else {
				bundle = false;
			}
			this.createHorizontalLog(1, worldIn, blockpos, direction, bundle, rand, config);
			if (i != length) {
				if (direction == Direction.EAST || direction == Direction.WEST) {
					if (direction == Direction.EAST) {
						logX += rand.nextInt(2);
					} else {
						logX -= rand.nextInt(2);
					}
				} else {
					if (direction == Direction.SOUTH) {
						logZ += rand.nextInt(2);
					} else {
						logZ -= rand.nextInt(2);
					}
				}
				logY += 1;
			}
		}
		return blockpos.relative(direction);
	}

	private void createHorizontalLog(int branchLength, IWorldGenerationReader worldIn, BlockPos pos, Direction direction, boolean bundle, Random rand, YuccaTreeFeatureConfig config) {
		int logX = pos.getX();
		int logY = pos.getY();
		int logZ = pos.getZ();

		for (int k3 = 0; k3 < branchLength; ++k3) {
			logX += direction.getStepX();
			logZ += direction.getStepZ();
			BlockPos blockpos1 = new BlockPos(logX, logY, logZ);
			if (TreeUtil.isAirOrLeaves(worldIn, blockpos1)) {
				if (!TreeUtil.isAir(worldIn, blockpos1.below()))
					bundle = false;
				this.placeLogAt(worldIn, blockpos1, Direction.UP, bundle, rand, config);
			}
		}
	}

	private void placeLogAt(IWorldWriter worldIn, BlockPos pos, Direction direction, boolean bundle, Random rand, YuccaTreeFeatureConfig config) {
		BlockState logState = config.petrified ? config.trunkProvider.getState(rand, pos) : config.trunkProvider.getState(rand, pos).setValue(RotatedPillarBlock.AXIS, direction.getAxis());
		TreeUtil.setForcedState(worldIn, pos, logState);
		if (bundle && !config.petrified) {
			TreeUtil.setForcedState(worldIn, pos.below(), config.branchProvider.getState(rand, pos.below()));
		}
	}

	private void placeLeafAt(IWorldGenerationReader world, BlockPos pos, Random rand, YuccaTreeFeatureConfig config) {
		if (TreeUtil.isAirOrLeaves(world, pos) && !config.petrified) {
			if (config.leavesProvider.getState(rand, pos).hasProperty(LeavesBlock.DISTANCE)) {
				TreeUtil.setForcedState(world, pos, config.leavesProvider.getState(rand, pos).setValue(LeavesBlock.DISTANCE, 1));
			} else {
				TreeUtil.setForcedState(world, pos, config.leavesProvider.getState(rand, pos));
			}
		}
		if (rand.nextInt(8) == 0 && !config.petrified) {
			placeFlowerAt(world, pos.above(), rand, config);
		}
	}

	private void createBabyYuccaLeaves(IWorldGenerationReader worldIn, BlockPos newPos, Random rand, YuccaTreeFeatureConfig config, boolean square) {
		int leafSize = 1;
		for (int k3 = -leafSize; k3 <= leafSize; ++k3) {
			for (int j4 = -leafSize; j4 <= leafSize; ++j4) {
				if (square) {
					this.placeLeafAt(worldIn, newPos.offset(k3, 0, j4), rand, config);
				} else {
					if ((Math.abs(k3) != leafSize || Math.abs(j4) != leafSize)) {
						this.placeLeafAt(worldIn, newPos.offset(k3, 0, j4), rand, config);
					} else if (rand.nextInt(4) == 0) {
						this.placeLeafAt(worldIn, newPos.offset(k3, 0, j4), rand, config);
					}
				}
			}
		}
	}

	private void placeFlowerAt(IWorldGenerationReader world, BlockPos pos, Random rand, YuccaTreeFeatureConfig config) {
		if (TreeUtil.isAir(world, pos)) {
			if (!TreeUtil.isAir(world, pos.above())) {
				TreeUtil.setForcedState(world, pos, config.flowerProvider.getState(rand, pos));
			} else if (rand.nextInt(4) == 0) {
				TreeUtil.setForcedState(world, pos, config.tallFlowerBottomProvider.getState(rand, pos));
				TreeUtil.setForcedState(world, pos.above(), config.tallFlowerTopProvider.getState(rand, pos.above()));
			} else {
				TreeUtil.setForcedState(world, pos, config.flowerProvider.getState(rand, pos));
			}
		}
	}
}