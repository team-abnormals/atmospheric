package com.teamabnormals.atmospheric.common.levelgen.feature;

import com.google.common.collect.Sets;
import com.mojang.serialization.Codec;
import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import com.teamabnormals.blueprint.core.util.TreeUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockPos.MutableBlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;

import java.util.Set;
import java.util.function.BiConsumer;

public class LaurelTreeFeature extends Feature<TreeConfiguration> {
	private Set<BlockPos> logPositions;
	private Set<BlockPos> foliagePositions;

	public LaurelTreeFeature(Codec<TreeConfiguration> config) {
		super(config);
	}

	@Override
	public boolean place(FeaturePlaceContext<TreeConfiguration> context) {
		TreeConfiguration config = context.config();
		WorldGenLevel level = context.level();
		RandomSource random = context.random();
		BlockPos origin = context.origin();

		this.logPositions = Sets.newHashSet();
		this.foliagePositions = Sets.newHashSet();

		if (TreeUtil.isValidGround(level, origin.below(), (SaplingBlock) AtmosphericBlocks.LAUREL_SAPLING.get())) {
			int trunkHeight = config.trunkPlacer.getTreeHeight(random);
			for (int y = 0; y < trunkHeight; y++) {
				this.addLog(origin.above(y));
			}

			Axis axis = random.nextBoolean() ? Axis.X : Axis.Z;
			BlockPos pos = origin.above(trunkHeight);
			for (Direction direction : Direction.values()) {
				if (direction.getAxis() == axis) {
					this.createBranch(pos, direction, random);
				}
			}

			for (BlockPos logPos : this.logPositions) {
				if (!TreeFeature.validTreePos(level, logPos) || logPos.getY() > level.getMaxBuildHeight()) return false;
			}

			for (BlockPos foliagePos : this.foliagePositions) {
				if (!TreeFeature.validTreePos(level, foliagePos) || foliagePos.getY() > level.getMaxBuildHeight()) return false;
			}

			setDirtAt(level, random, origin.below(), config);
			this.logPositions.forEach(logPos -> TreeUtil.setForcedState(level, logPos, config.trunkProvider.getState(random, logPos)));
			this.foliagePositions.forEach(foliagePos -> {
				if (TreeFeature.validTreePos(level, foliagePos))
					TreeUtil.setForcedState(level, foliagePos, config.foliageProvider.getState(random, foliagePos));
			});

			TreeUtil.updateLeaves(level, this.logPositions);
			if (!config.decorators.isEmpty()) {
				BiConsumer<BlockPos, BlockState> decorationSetter = (decorationPos, state) -> level.setBlock(decorationPos, state, 19);
				TreeDecorator.Context decoratorContext = new TreeDecorator.Context(level, decorationSetter, random, this.logPositions, this.foliagePositions, Sets.newHashSet());
				config.decorators.forEach((decorator) -> decorator.place(decoratorContext));
			}

			return true;
		} else {
			return false;
		}
	}

	private void addLog(BlockPos pos) {
		this.logPositions.add(pos.immutable());
	}

	private void addFoliage(BlockPos pos) {
		this.foliagePositions.add(pos.immutable());
	}

	private void createBranch(BlockPos pos, Direction direction, RandomSource random) {
		MutableBlockPos mutablePos = new MutableBlockPos();
		mutablePos.set(pos.relative(direction));

		int firstHeight = 1 + random.nextInt(2);
		int secondHeight = 1 + random.nextInt(2);

		for (int i = 0; i < firstHeight; i++) {
			this.addLog(mutablePos.above(i));
		}

		mutablePos.set(mutablePos.relative(direction).above(firstHeight));
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

	public static void setDirtAt(WorldGenLevel level, RandomSource random, BlockPos pos, TreeConfiguration config) {
		if (config.forceDirt || !isDirt(level, pos)) {
			TreeUtil.setForcedState(level, pos, config.dirtProvider.getState(random, pos));
		}
	}

	public static boolean isDirt(LevelSimulatedReader level, BlockPos pos) {
		return level.isStateAtPosition(pos, (state) -> Feature.isDirt(state) && !state.is(Blocks.GRASS_BLOCK) && !state.is(Blocks.MYCELIUM));
	}
}