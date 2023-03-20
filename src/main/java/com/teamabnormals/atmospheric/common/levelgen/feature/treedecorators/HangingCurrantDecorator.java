package com.teamabnormals.atmospheric.common.levelgen.feature.treedecorators;

import com.mojang.serialization.Codec;
import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import com.teamabnormals.atmospheric.core.registry.AtmosphericFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;

public class HangingCurrantDecorator extends TreeDecorator {
	public static final Codec<HangingCurrantDecorator> CODEC = Codec.floatRange(0.0F, 1.0F).fieldOf("probability").xmap(HangingCurrantDecorator::new, (decorator) -> decorator.probability).codec();
	private final float probability;

	public HangingCurrantDecorator(float probability) {
		this.probability = probability;
	}

	@Override
	public void place(Context context) {
		for (BlockPos pos : context.leaves()) {
			if (TreeFeature.validTreePos(context.level(), pos.below()) && !TreeFeature.isBlockWater(context.level(), pos.below())) {
				if (context.random().nextFloat() < this.probability) {
					context.setBlock(pos.below(), AtmosphericBlocks.HANGING_CURRANT.get().defaultBlockState());
				}
			}
		}
	}

	@Override
	protected TreeDecoratorType<?> type() {
		return AtmosphericFeatures.HANGING_CURRANT.get();
	}
}