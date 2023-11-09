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

public class ExtendPetrifiedYuccaTreeDecorator extends TreeDecorator {
	public static final Codec<ExtendPetrifiedYuccaTreeDecorator> CODEC = RecordCodecBuilder.create(codec -> codec.group(
			BlockStateProvider.CODEC.fieldOf("block_provider").forGetter(instance -> instance.blockProvider)
	).apply(codec, ExtendPetrifiedYuccaTreeDecorator::new));

	private final BlockStateProvider blockProvider;

	public ExtendPetrifiedYuccaTreeDecorator(BlockStateProvider blockProvider) {
		this.blockProvider = blockProvider;
	}

	@Override
	public void place(Context context) {
		LevelSimulatedReader level = context.level();
		RandomSource random = context.random();
		BlockPos pos = YuccaFlowerPatchDecorator.getLowestBlockPos(context.logs());

		int height = 1 + random.nextInt(3);
		for (int i = 0; i < height; i++) {
			BlockPos offsetPos = pos.below(i + 1);
			if (level.isStateAtPosition(offsetPos, state -> state.is(BlockTags.SAND))) {
				context.setBlock(offsetPos, this.blockProvider.getState(random, offsetPos));
			}
		}
	}

	@Override
	protected TreeDecoratorType<?> type() {
		return AtmosphericFeatures.EXTEND_PETRIFIED_YUCCA_TREE.get();
	}
}