package com.teamabnormals.atmospheric.common.levelgen.feature.treedecorators;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.teamabnormals.atmospheric.core.registry.AtmosphericFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;

public class RoastedYuccaBundleDecorator extends TreeDecorator {
	public static final Codec<RoastedYuccaBundleDecorator> CODEC = RecordCodecBuilder.create(codec -> codec.group(
			Codec.floatRange(0.0F, 1.0F).fieldOf("probability").forGetter(instance -> instance.probability),
			BlockStateProvider.CODEC.fieldOf("block_provider").forGetter(instance -> instance.blockProvider)
	).apply(codec, RoastedYuccaBundleDecorator::new));

	private final float probability;
	private final BlockStateProvider blockProvider;

	public RoastedYuccaBundleDecorator(float probability, BlockStateProvider blockProvider) {
		this.probability = probability;
		this.blockProvider = blockProvider;
	}

	@Override
	public void place(Context context) {
		LevelSimulatedReader level = context.level();
		RandomSource random = context.random();
		BlockPos pos = YuccaFlowerPatchDecorator.getLowestBlockPos(context.logs());

		if (random.nextFloat() < this.probability) {
			for (int j = 0; j < 12; ++j) {
				BlockPos offsetPos = pos.offset(random.nextInt(4) - random.nextInt(4), random.nextInt(3) - random.nextInt(3), random.nextInt(4) - random.nextInt(4));
				if (context.isAir(offsetPos) && level.isStateAtPosition(offsetPos.below(), state -> state.is(BlockTags.SAND))) {
					context.setBlock(offsetPos, this.blockProvider.getState(random, offsetPos));
					return;
				}
			}
		}
	}

	@Override
	protected TreeDecoratorType<?> type() {
		return AtmosphericFeatures.ROASTED_YUCCA_BUNDLE.get();
	}
}