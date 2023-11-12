package com.teamabnormals.atmospheric.common.levelgen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockPos.MutableBlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.core.Direction.AxisDirection;
import net.minecraft.core.Direction.Plane;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;

public class LargeLaurelTreeFeature extends LaurelTreeFeature {

	public LargeLaurelTreeFeature(Codec<TreeConfiguration> config) {
		super(config);
	}

	@Override
	public void doPlace(FeaturePlaceContext<TreeConfiguration> context) {
		TreeConfiguration config = context.config();
		RandomSource random = context.random();
		BlockPos origin = context.origin();

		boolean shortTrunk = false;
		for (int i = 0; i < 2; i++) {
			for (int k = 0; k < 2; k++) {
				int trunkHeight = getStumpHeight(config, random);
				if (trunkHeight == 2) {
					if (!shortTrunk) {
						shortTrunk = true;
					} else {
						while (trunkHeight == 2) {
							trunkHeight = getStumpHeight(config, random);
						}
					}
				}
				for (int j = 0; j < trunkHeight; j++) {
					this.addLog(origin.offset(i, j, k));
				}
			}
		}

		for (Direction direction : Plane.HORIZONTAL) {
			boolean positive = direction.getAxisDirection() == AxisDirection.POSITIVE;
			boolean xAxis = direction.getAxis() == Axis.X;
			MutableBlockPos pos = new MutableBlockPos();

			this.addRoots(context, direction, pos);

			int height;
			pos.set(origin.offset(xAxis ? positive ? 1 : 0 : random.nextInt(2), 0, !xAxis ? positive ? 1 : 0 : random.nextInt(2)));
			for (height = 0; this.logPositions.contains(pos.above(height)); height++) ;
			pos.set(pos.above(height - 1));

			if (height > 2) {
				this.createBranch(pos, direction, random, 3, this.getMaxLength(), this.getMaxHeight());
			}
		}
	}

	public int getMaxLength() {
		return 4;
	}

	public int getMaxHeight() {
		return 3;
	}

	public void addRoots(FeaturePlaceContext<TreeConfiguration> context, Direction direction, MutableBlockPos pos) {
	}

	private static int getStumpHeight(TreeConfiguration config, RandomSource random) {
		return config.trunkPlacer.getTreeHeight(random) - 1 + random.nextInt(2);
	}

	private void createBranch(BlockPos pos, Direction direction, RandomSource random, int minLength, int maxLength, int maxHeight) {
		MutableBlockPos mutablePos = new MutableBlockPos();
		mutablePos.set(pos);

		int length = minLength;
		if (random.nextInt(3) == 0) {
			length = maxLength;
		}
		int height = 0;
		boolean bonus = false;

		for (int i = 0; i < length; i++) {
			mutablePos.set(mutablePos.relative(direction));
			if (random.nextInt(2) == 0 && height < maxHeight) {
				mutablePos.set(mutablePos.above());
				height++;
			}

			this.addLog(mutablePos);

			int smallMaxSize = this.getSmallMaxSize();
			if (i > 0 && height < maxHeight - 1 && minLength != 2 && (!bonus || !this.limitBranches()) && random.nextInt(this.getSmallMaxSize()) != 0) {
				this.createBranch(mutablePos, random.nextBoolean() ? direction.getClockWise() : direction.getCounterClockWise(), random, 2, smallMaxSize, smallMaxSize);
				bonus = true;
			}
		}

		this.createLeafChunk(mutablePos, direction, random);
	}

	public int getSmallMaxSize() {
		return 2;
	}

	public boolean limitBranches() {
		return true;
	}
}