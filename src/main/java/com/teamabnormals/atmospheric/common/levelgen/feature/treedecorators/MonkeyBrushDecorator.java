package com.teamabnormals.atmospheric.common.levelgen.feature.treedecorators;

import com.mojang.serialization.Codec;
import com.teamabnormals.atmospheric.common.levelgen.feature.MonkeyBrushFeature;
import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import com.teamabnormals.atmospheric.core.registry.AtmosphericFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour.BlockStateBase;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;

import java.util.ArrayList;

public class MonkeyBrushDecorator extends TreeDecorator {
	public static final Codec<MonkeyBrushDecorator> CODEC = Codec.floatRange(0.0F, 1.0F).fieldOf("probability").xmap(MonkeyBrushDecorator::new, (decorator) -> decorator.probability).codec();
	private final float probability;

	public MonkeyBrushDecorator(float probability) {
		this.probability = probability;
	}

	@Override
	public void place(Context context) {
		RandomSource random = context.random();
		if (random.nextFloat() < this.probability) {
			ArrayList<Block> brushes = new ArrayList<>();
			if (random.nextInt(2) == 0) brushes.add(AtmosphericBlocks.WARM_MONKEY_BRUSH.get());
			if (random.nextInt(3) == 0) brushes.add(AtmosphericBlocks.HOT_MONKEY_BRUSH.get());
			if (random.nextInt(4) == 0) brushes.add(AtmosphericBlocks.SCALDING_MONKEY_BRUSH.get());

			if (!brushes.isEmpty()) {
				for (BlockPos pos : context.logs()) {
					for (Direction direction : Direction.values()) {
						BlockPos offsetPos = pos.relative(direction);
						if (random.nextInt(3) == 0 && context.level().isStateAtPosition(offsetPos, BlockStateBase::isAir)) {
							context.setBlock(offsetPos, MonkeyBrushFeature.monkeyBrushState(brushes.get(random.nextInt(brushes.size())).defaultBlockState(), direction));
						}
					}
				}
			}
		}
	}

	@Override
	protected TreeDecoratorType<?> type() {
		return AtmosphericFeatures.MONKEY_BRUSH.get();
	}
}