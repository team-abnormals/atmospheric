package com.minecraftabnormals.atmospheric.common.world.gen.feature;

import com.minecraftabnormals.abnormals_core.core.util.TreeUtil;
import com.minecraftabnormals.atmospheric.core.registry.AtmosphericBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SaplingBlock;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.IWorldGenerationBaseReader;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RainforestTreeFeature extends Feature<BaseTreeFeatureConfig> {
	private List<Block> brushes = new ArrayList<>();
	private boolean water = false;

	public RainforestTreeFeature(Codec<BaseTreeFeatureConfig> config, boolean water) {
		super(config);
		this.water = water;
	}

	@Override
	public boolean place(ISeedReader worldIn, ChunkGenerator generator, Random rand, BlockPos position, BaseTreeFeatureConfig config) {
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

		if (position.getY() >= 1 && position.getY() + height + 1 <= worldIn.getMaxBuildHeight()) {
			for (int j = position.getY(); j <= position.getY() + 1 + height; ++j) {
				int k = 1;
				if (j == position.getY()) {
					k = 0;
				}
				if (j >= position.getY() + 1 + height - 2) {
					k = 2;
				}
				BlockPos.Mutable blockpos$mutableblockpos = new BlockPos.Mutable();

				for (int l = position.getX() - k; l <= position.getX() + k && flag; ++l) {
					for (int i1 = position.getZ() - k; i1 <= position.getZ() + k && flag; ++i1) {
						if (j >= 0 && j < worldIn.getMaxBuildHeight()) {
							if (!this.water ? !TreeUtil.isAirOrLeaves(worldIn, blockpos$mutableblockpos.set(l, j, i1)) : !isAirOrWaterOrLeaves(worldIn, blockpos$mutableblockpos.set(l, j, i1))) {
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
			} else if (TreeUtil.isValidGround(worldIn, position.below(), (SaplingBlock) AtmosphericBlocks.ROSEWOOD_SAPLING.get()) && position.getY() < worldIn.getMaxBuildHeight() - branches - 1) {
				// base log
				if (!this.water)
					TreeUtil.setDirtAt(worldIn, position.below());
				List<BlockPos> logsPlaced = new ArrayList<>();

				int logX = position.getX();
				int logZ = position.getZ();
				boolean canopy = false;

				for (int k1 = 0; k1 < height; ++k1) {
					int logY = position.getY() + k1;
					BlockPos blockpos = new BlockPos(logX, logY, logZ);
					if (!this.water ? TreeUtil.isAirOrLeaves(worldIn, blockpos) : isAirOrWaterOrLeaves(worldIn, blockpos)) {
						TreeUtil.placeDirectionalLogAt(worldIn, blockpos, Direction.UP, rand, config);
						logsPlaced.add(blockpos);
					}
					if (rand.nextInt(6) == 0 && k1 > 3 && k1 < height && canopy == false) {
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

				// branches
				ArrayList<String> directions = new ArrayList<String>();

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
						createHorizontalLog(branchLength, worldIn, currentPos, offset, rand, config, logsPlaced);
						createVerticalLog(branchHeight, worldIn, currentPos.relative(offset, branchLength), rand, config, logsPlaced);
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
								TreeUtil.placeLeafAt(worldIn, currentPos.offset(k3, 0, j4), rand, config);
							}
						}
					}

					// second layer of leaves
					currentPos = currentPos.above(1);
					for (int k3 = -leafSizeTop; k3 <= leafSizeTop; ++k3) {
						for (int j4 = -leafSizeTop; j4 <= leafSizeTop; ++j4) {
							if (Math.abs(k3) != leafSizeTop || Math.abs(j4) != leafSizeTop) {
								TreeUtil.placeLeafAt(worldIn, currentPos.offset(k3, 0, j4), rand, config);
							}
						}
					}

					if (morado) {
						for (int k3 = -leafSizeTop; k3 <= leafSizeTop; ++k3) {
							for (int j4 = -leafSizeTop - 1; j4 <= leafSizeTop + 1; ++j4) {
								if (Math.abs(k3) != leafSizeTop || Math.abs(j4) != leafSizeTop) {
									if (rand.nextBoolean())
										TreeUtil.placeLeafAt(worldIn, currentPos.offset(k3, 0, j4), rand, config);
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
										TreeUtil.placeLeafAt(worldIn, currentPos.offset(k3, 0, j4), rand, config);
								}
							}
						}
					}

					logX = position.getX();
					logZ = position.getZ();
				}

				if (!brushes.isEmpty()) {
					for (BlockPos pos : logsPlaced) {
						for (Direction direction2 : Direction.values()) {
							if (TreeUtil.isAir(worldIn, pos.relative(direction2)) && rand.nextInt(3) == 0) {
								worldIn.setBlock(pos.relative(direction2), MonkeyBrushFeature.monkeyBrushState(brushes.get(rand.nextInt(brushes.size())).defaultBlockState(), direction2), 18);
							}
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

	private void createHorizontalLog(int branchLength, IWorldGenerationReader worldIn, BlockPos pos, Direction direction, Random rand, BaseTreeFeatureConfig config, List<BlockPos> logsPlaced) {
		int logX = pos.getX();
		int logY = pos.getY();
		int logZ = pos.getZ();

		for (int k3 = 0; k3 < branchLength; ++k3) {

			logX += direction.getStepX();
			logZ += direction.getStepZ();

			BlockPos blockpos1 = new BlockPos(logX, logY, logZ);
			if (!this.water ? TreeUtil.isAirOrLeaves(worldIn, blockpos1) : isAirOrWaterOrLeaves(worldIn, blockpos1)) {
				TreeUtil.placeDirectionalLogAt(worldIn, blockpos1, direction, rand, config);
				logsPlaced.add(blockpos1);
			}
		}
	}

	private void createVerticalLog(int branchHeight, IWorldGenerationReader worldIn, BlockPos pos, Random rand, BaseTreeFeatureConfig config, List<BlockPos> logsPlaced) {
		int logX = pos.getX();
		int logY = pos.getY();
		int logZ = pos.getZ();
		boolean canopy = false;

		for (int k1 = 0; k1 < branchHeight; ++k1) {
			logY += 1;
			BlockPos blockpos = new BlockPos(logX, logY, logZ);
			if (!this.water ? TreeUtil.isAirOrLeaves(worldIn, blockpos) : isAirOrWaterOrLeaves(worldIn, blockpos)) {
				TreeUtil.placeDirectionalLogAt(worldIn, blockpos, Direction.UP, rand, config);
				logsPlaced.add(blockpos);
			}
			if (rand.nextInt(6) == 0 && canopy == false) {
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

	public static boolean isAirOrWater(IWorldGenerationBaseReader world, BlockPos pos) {
		if (!(world instanceof IBlockReader)) {
			return world.isStateAtPosition(pos, BlockState::isAir) || world.isStateAtPosition(pos, state -> state.getFluidState().is(FluidTags.WATER));
		} else {
			return world.isStateAtPosition(pos, state -> state.isAir((IBlockReader) world, pos)) || world.isStateAtPosition(pos, state -> state.getFluidState().is(FluidTags.WATER));
		}
	}

	public static boolean isAirOrWaterOrLeaves(IWorldGenerationBaseReader world, BlockPos pos) {
		if (world instanceof IWorldReader) {
			return world.isStateAtPosition(pos, state -> state.canBeReplacedByLeaves((IWorldReader) world, pos)) || world.isStateAtPosition(pos, state -> state.getFluidState().is(FluidTags.WATER));
		}
		return world.isStateAtPosition(pos, (state) -> {
			return isAirOrWater(world, pos) || state.is(BlockTags.LEAVES);
		});
	}
}