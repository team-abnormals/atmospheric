package com.teamabnormals.atmospheric.common.levelgen.feature;

import com.google.common.collect.Sets;
import com.mojang.serialization.Codec;
import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import com.teamabnormals.blueprint.core.util.TreeUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelSimulatedRW;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraftforge.common.Tags;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class RainforestTreeFeature extends Feature<TreeConfiguration> {
	private final List<Block> brushes = new ArrayList<>();
	private final boolean water;

	public RainforestTreeFeature(Codec<TreeConfiguration> config, boolean water) {
		super(config);
		this.water = water;
	}

	@Override
	public boolean place(FeaturePlaceContext<TreeConfiguration> context) {
		TreeConfiguration config = context.config();
		WorldGenLevel level = context.level();
		Random rand = context.random();
		BlockPos position = context.origin();

		boolean morado = config.trunkProvider.getState(rand, position).is(AtmosphericBlocks.MORADO_LOG.get());
		if (rand.nextInt(250) == 0) {
			if (rand.nextInt(2) == 0)
				brushes.add(AtmosphericBlocks.WARM_MONKEY_BRUSH.get());
			if (rand.nextInt(3) == 0)
				brushes.add(AtmosphericBlocks.HOT_MONKEY_BRUSH.get());
			if (rand.nextInt(4) == 0)
				brushes.add(AtmosphericBlocks.SCALDING_MONKEY_BRUSH.get());
		} else {
			brushes.clear();
		}

		int branches = 2 + rand.nextInt(3) - (!morado ? 0 : 1);
		int height = 4 + rand.nextInt(2) + rand.nextInt(3) + (!morado ? rand.nextInt(3) : -1);
		boolean flag = true;

		if (position.getY() >= 1 && position.getY() + height + 1 <= level.getMaxBuildHeight()) {
			for (int j = position.getY(); j <= position.getY() + 1 + height; ++j) {
				int k = 1;
				if (j == position.getY()) {
					k = 0;
				}
				if (j >= position.getY() + 1 + height - 2) {
					k = 2;
				}
				BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

				for (int l = position.getX() - k; l <= position.getX() + k && flag; ++l) {
					for (int i1 = position.getZ() - k; i1 <= position.getZ() + k && flag; ++i1) {
						if (j >= 0 && j < level.getMaxBuildHeight()) {
							if (!this.water ? !TreeUtil.isAirOrLeaves(level, blockpos$mutableblockpos.set(l, j, i1)) : !isAirOrWaterOrLeaves(level, blockpos$mutableblockpos.set(l, j, i1))) {
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
			} else if ((TreeUtil.isValidGround(level, position.below(), (SaplingBlock) AtmosphericBlocks.ROSEWOOD_SAPLING.get()) || level.getBlockState(position.below()).is(Tags.Blocks.GRAVEL)) && position.getY() < level.getMaxBuildHeight() - branches - 1) {
				// base log
				if (!this.water)
					TreeUtil.setDirtAt(level, position.below());
				Set<BlockPos> logsPlaced = Sets.newHashSet();

				int logX = position.getX();
				int logZ = position.getZ();
				boolean canopy = false;

				for (int k1 = 0; k1 < height; ++k1) {
					int logY = position.getY() + k1;
					BlockPos blockpos = new BlockPos(logX, logY, logZ);
					if (!this.water ? TreeUtil.isAirOrLeaves(level, blockpos) : isAirOrWaterOrLeaves(level, blockpos)) {
						TreeUtil.placeDirectionalLogAt(level, blockpos, Direction.UP, rand, config);
						logsPlaced.add(blockpos.immutable());
					}
					if (rand.nextInt(6) == 0 && k1 > 3 && !canopy) {
						int leafSize = 1 + rand.nextInt(2);
						for (int k3 = -leafSize; k3 <= leafSize; ++k3) {
							for (int j4 = -leafSize; j4 <= leafSize; ++j4) {
								if (Math.abs(k3) != leafSize || Math.abs(j4) != leafSize) {
									TreeUtil.placeLeafAt(level, blockpos.offset(k3, 0, j4), rand, config);
								}
							}
						}
						canopy = true;
					}
				}

				// branches
				ArrayList<String> directions = new ArrayList<>();

				for (int k2 = 0; k2 < branches; ++k2) {
					Direction offset = Direction.Plane.HORIZONTAL.getRandomDirection(rand);

					while (directions.contains(offset.toString())) {
						offset = Direction.Plane.HORIZONTAL.getRandomDirection(rand);
					}
					directions.add(offset.toString());
					int turns = 1 + rand.nextInt(3);

					BlockPos currentPos = position.above(height - 1);
					int branchLength = 0;
					int branchHeight = 0;

					for (int k4 = 0; k4 < turns; ++k4) {
						branchLength = !morado ? 1 + rand.nextInt(2) + rand.nextInt(2) : 1 + rand.nextInt(2);
						branchHeight = !morado ? 1 + rand.nextInt(3) + rand.nextInt(2) : 1 + rand.nextInt(3);
						createHorizontalLog(branchLength, level, currentPos, offset, rand, config, logsPlaced);
						createVerticalLog(branchHeight, level, currentPos.relative(offset, branchLength), rand, config, logsPlaced);
						currentPos = currentPos.relative(offset, branchLength).relative(Direction.UP, branchHeight);
					}

					int leafSize = 2 + rand.nextInt(2);
					int leafSizeTop = 0;
					if (leafSize == 2) {
						leafSizeTop = leafSize - 1;
					} else {
						leafSizeTop = leafSize - 1 - rand.nextInt(2);
					}
					// first layer of leaves
					for (int k3 = -leafSize; k3 <= leafSize; ++k3) {
						for (int j4 = -leafSize; j4 <= leafSize; ++j4) {
							if (Math.abs(k3) != leafSize || Math.abs(j4) != leafSize) {
								TreeUtil.placeLeafAt(level, currentPos.offset(k3, 0, j4), rand, config);
							}
						}
					}

					// second layer of leaves
					currentPos = currentPos.above(1);
					for (int k3 = -leafSizeTop; k3 <= leafSizeTop; ++k3) {
						for (int j4 = -leafSizeTop; j4 <= leafSizeTop; ++j4) {
							if (Math.abs(k3) != leafSizeTop || Math.abs(j4) != leafSizeTop) {
								TreeUtil.placeLeafAt(level, currentPos.offset(k3, 0, j4), rand, config);
							}
						}
					}

					if (morado) {
						for (int k3 = -leafSizeTop; k3 <= leafSizeTop; ++k3) {
							for (int j4 = -leafSizeTop - 1; j4 <= leafSizeTop + 1; ++j4) {
								if (Math.abs(k3) != leafSizeTop || Math.abs(j4) != leafSizeTop) {
									if (rand.nextBoolean())
										TreeUtil.placeLeafAt(level, currentPos.offset(k3, 0, j4), rand, config);
								}
							}
						}
					}

					if (morado) {
						currentPos = currentPos.below(2);
						for (int k3 = -leafSizeTop; k3 <= leafSizeTop; ++k3) {
							for (int j4 = -leafSizeTop - 1; j4 <= leafSizeTop + 1; ++j4) {
								if (Math.abs(k3) != leafSizeTop || Math.abs(j4) != leafSizeTop) {
									if (rand.nextBoolean())
										TreeUtil.placeLeafAt(level, currentPos.offset(k3, 0, j4), rand, config);
								}
							}
						}
					}
				}

				if (!brushes.isEmpty()) {
					for (BlockPos pos : logsPlaced) {
						for (Direction direction2 : Direction.values()) {
							if (isAir(level, pos.relative(direction2)) && rand.nextInt(3) == 0) {
								level.setBlock(pos.relative(direction2), MonkeyBrushFeature.monkeyBrushState(brushes.get(rand.nextInt(brushes.size())).defaultBlockState(), direction2), 18);
							}
						}
					}
				}

				TreeUtil.updateLeaves(level, logsPlaced);
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	private void createHorizontalLog(int branchLength, LevelSimulatedRW worldIn, BlockPos pos, Direction direction, Random rand, TreeConfiguration config, Set<BlockPos> logsPlaced) {
		int logX = pos.getX();
		int logY = pos.getY();
		int logZ = pos.getZ();

		for (int k3 = 0; k3 < branchLength; ++k3) {

			logX += direction.getStepX();
			logZ += direction.getStepZ();

			BlockPos blockpos1 = new BlockPos(logX, logY, logZ);
			if (!this.water ? TreeUtil.isAirOrLeaves(worldIn, blockpos1) : isAirOrWaterOrLeaves(worldIn, blockpos1)) {
				TreeUtil.placeDirectionalLogAt(worldIn, blockpos1, direction, rand, config);
				logsPlaced.add(blockpos1.immutable());
			}
		}
	}

	private void createVerticalLog(int branchHeight, LevelSimulatedRW worldIn, BlockPos pos, Random rand, TreeConfiguration config, Set<BlockPos> logsPlaced) {
		int logX = pos.getX();
		int logY = pos.getY();
		int logZ = pos.getZ();
		boolean canopy = false;

		for (int k1 = 0; k1 < branchHeight; ++k1) {
			logY += 1;
			BlockPos blockpos = new BlockPos(logX, logY, logZ);
			if (!this.water ? TreeUtil.isAirOrLeaves(worldIn, blockpos) : isAirOrWaterOrLeaves(worldIn, blockpos)) {
				TreeUtil.placeDirectionalLogAt(worldIn, blockpos, Direction.UP, rand, config);
				logsPlaced.add(blockpos.immutable());
			}
			if (rand.nextInt(6) == 0 && !canopy) {
				int leafSize = 1 + rand.nextInt(2);
				for (int k3 = -leafSize; k3 <= leafSize; ++k3) {
					for (int j4 = -leafSize; j4 <= leafSize; ++j4) {
						if (Math.abs(k3) != leafSize || Math.abs(j4) != leafSize) {
							TreeUtil.placeLeafAt(worldIn, blockpos.offset(k3, 0, j4), rand, config);
						}
					}
				}
				canopy = true;
			}
		}
	}

	public static boolean isAirOrWater(LevelSimulatedReader world, BlockPos pos) {
		return isAir(world, pos) || isWater(world, pos);
	}

	public static boolean isAirOrWaterOrLeaves(LevelSimulatedReader world, BlockPos pos) {
		return TreeFeature.isAirOrLeaves(world, pos) || isWater(world, pos);
	}

	public static boolean isWater(LevelSimulatedReader world, BlockPos pos) {
		return world.isStateAtPosition(pos, (state) -> state.is(Blocks.WATER));
	}
}