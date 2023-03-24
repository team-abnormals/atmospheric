package com.teamabnormals.atmospheric.common.levelgen.feature.treedecorators;

import com.mojang.serialization.Codec;
import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import com.teamabnormals.atmospheric.core.registry.AtmosphericFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;

import java.util.List;

public class CobwebDecorator extends TreeDecorator {
	public static final Codec<CobwebDecorator> CODEC = Codec.floatRange(0.0F, 1.0F).fieldOf("probability").xmap(CobwebDecorator::new, (decorator) -> decorator.probability).codec();
	private final float probability;

	public CobwebDecorator(float probability) {
		this.probability = probability;
	}

	protected TreeDecoratorType<?> type() {
		return AtmosphericFeatures.COBWEB.get();
	}

	public void place(TreeDecorator.Context context) {
		RandomSource random = context.random();
		if (!(random.nextFloat() >= this.probability)) {
			List<BlockPos> logs = context.logs();
			int i = logs.get(0).getY();
			logs.stream().filter((pos) -> pos.getY() - i > 5).forEach((pos) -> {
				for (Direction direction : Direction.values()) {
					if (random.nextFloat() <= 0.05F) {
						Direction oppositeDirection = direction.getOpposite();
						BlockPos cobwebPos = pos.offset(oppositeDirection.getStepX(), oppositeDirection.getStepY(), oppositeDirection.getStepZ());
						if (context.isAir(cobwebPos)) {
							context.setBlock(cobwebPos, AtmosphericBlocks.GRIMWEB.get().defaultBlockState());
						}
					}
				}
			});
		}
	}
}