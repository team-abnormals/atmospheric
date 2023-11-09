package com.teamabnormals.atmospheric.common.levelgen.feature.treedecorators;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.teamabnormals.atmospheric.core.registry.AtmosphericFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;

public class YuccaFlowersDecorator extends TreeDecorator {
	public static final Codec<YuccaFlowersDecorator> CODEC = RecordCodecBuilder.create(codec -> codec.group(
			Codec.floatRange(0.0F, 1.0F).fieldOf("probability").forGetter(instance -> instance.probability),
			BlockStateProvider.CODEC.fieldOf("block_provider").forGetter(instance -> instance.blockProvider)
	).apply(codec, YuccaFlowersDecorator::new));

	private final float probability;
	private final BlockStateProvider blockProvider;

	public YuccaFlowersDecorator(float probability, BlockStateProvider blockProvider) {
		this.probability = probability;
		this.blockProvider = blockProvider;
	}

	@Override
	public void place(Context context) {
		RandomSource random = context.random();
		for (BlockPos pos : context.leaves()) {
			if (random.nextFloat() < this.probability) {
				placeFlower(context, this.blockProvider.getState(random, pos.above()), pos.above());
			}
		}
	}

	public static void placeFlower(Context context, BlockState state, BlockPos pos) {
		if (context.isAir(pos)) {
			if (state.getBlock() instanceof DoublePlantBlock) {
				if (context.isAir(pos.above())) {
					context.setBlock(pos, state.setValue(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER));
					context.setBlock(pos.above(), state.setValue(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER));
				}
			} else {
				context.setBlock(pos, state);
			}
		}
	}

	@Override
	protected TreeDecoratorType<?> type() {
		return AtmosphericFeatures.YUCCA_FLOWERS.get();
	}
}