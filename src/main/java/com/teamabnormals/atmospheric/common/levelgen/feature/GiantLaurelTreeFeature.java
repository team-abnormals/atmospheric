package com.teamabnormals.atmospheric.common.levelgen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockPos.MutableBlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.core.Direction.AxisDirection;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;

public class GiantLaurelTreeFeature extends LargeLaurelTreeFeature {

	public GiantLaurelTreeFeature(Codec<TreeConfiguration> config) {
		super(config);
	}

	@Override
	public void addRoots(BlockPos origin, RandomSource random, Direction direction, MutableBlockPos pos) {
		boolean positive = direction.getAxisDirection() == AxisDirection.POSITIVE;
		boolean xAxis = direction.getAxis() == Axis.X;

		pos.set(origin.offset(xAxis ? positive ? 1 : 0 : random.nextInt(2), 0, !xAxis ? positive ? 1 : 0 : random.nextInt(2)));
		int length = 1 + random.nextInt(2);
		for (int i = 0; i < length; i++) {
			this.logPositions.add(pos.relative(direction, i + 1));
		}

		pos.set(origin.offset(xAxis ? positive ? 1 : 0 : random.nextInt(2), 0, !xAxis ? positive ? 1 : 0 : random.nextInt(2)));
		length = random.nextInt(2);
		for (int i = 0; i < length; i++) {
			this.logPositions.add(pos.relative(direction, i + 1));
		}
	}

	@Override
	public int getMaxLength() {
		return 5;
	}

	@Override
	public int getMaxHeight() {
		return 4;
	}

	@Override
	public int getSmallMaxSize() {
		return 3;
	}
}