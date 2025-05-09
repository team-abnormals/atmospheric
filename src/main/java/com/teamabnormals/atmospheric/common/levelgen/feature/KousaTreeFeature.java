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
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;

import java.util.ArrayList;

public class KousaTreeFeature extends BlueprintTreeFeature {

	public KousaTreeFeature(Codec<TreeConfiguration> config) {
		super(config);
	}

	@Override
	public void doPlace(FeaturePlaceContext<TreeConfiguration> context, TreeInfo info) {
		TreeConfiguration config = context.config();
		RandomSource random = context.random();
		BlockPos origin = context.origin();

		int trunkHeight = config.trunkPlacer.getTreeHeight(random);
		for (int y = 0; y < trunkHeight; y++) {
			info.addLog(origin.above(y));
		}

		ArrayList<Direction> branchDirections = Lists.newArrayList();
		Plane.HORIZONTAL.forEach(branchDirections::add);

		int branches = 2 + random.nextInt(3) - (random.nextBoolean() ? random.nextInt(2) : 0);
		if (branches < 3) {
			info.addLog(origin.above(trunkHeight));
			this.createLeafBalloon(info, origin.above(trunkHeight));
		}

		if (branches > 1) {
			for (int i = 0; i < branches; i++) {
				Direction direction = branchDirections.get(random.nextInt(branchDirections.size()));
				this.createBranch(info, origin.above(trunkHeight), direction, random);
				branchDirections.remove(direction);
			}
		}

	}

	@Override
	public BlockState getSapling() {
		return AtmosphericBlocks.KOUSA_SAPLING.get().defaultBlockState();
	}

	private void createBranch(TreeInfo info, BlockPos pos, Direction direction, RandomSource random) {
		MutableBlockPos mutablePos = new MutableBlockPos();
		mutablePos.set(pos.below());

		for (int i = 0; i < 2; i++) {
			mutablePos.set(mutablePos.relative(direction).above(random.nextInt(2)));
			info.addLog(mutablePos);
		}

		info.addLog(mutablePos.set(mutablePos.relative(direction, random.nextInt(2)).above()));
		this.createLeafBalloon(info, mutablePos);
	}

	private void createLeafBalloon(TreeInfo info, BlockPos pos) {
		int leafSize = 1;
		for (int i = -leafSize; i <= leafSize; i++) {
			for (int j = -leafSize; j <= leafSize; j++) {
				for (int k = -leafSize; k <= leafSize; k++) {
					info.addFoliage(pos.offset(i, j, k));
				}
			}
		}

		for (Direction direction : Direction.values()) {
			BlockPos offsetPos = pos.relative(direction, 2);
			info.addFoliage(offsetPos);
			for (Direction sideDirection : Direction.values()) {
				if (sideDirection.getAxis() != direction.getAxis()) {
					info.addFoliage(offsetPos.relative(sideDirection));
				}
			}
		}
	}
}