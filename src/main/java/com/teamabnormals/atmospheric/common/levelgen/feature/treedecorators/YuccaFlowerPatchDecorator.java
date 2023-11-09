package com.teamabnormals.atmospheric.common.levelgen.feature.treedecorators;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.teamabnormals.atmospheric.core.registry.AtmosphericFeatures;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;

public class YuccaFlowerPatchDecorator extends TreeDecorator {
	public static final Codec<YuccaFlowerPatchDecorator> CODEC = RecordCodecBuilder.create(codec -> codec.group(
			BlockStateProvider.CODEC.fieldOf("block_provider").forGetter(instance -> instance.blockProvider)
	).apply(codec, YuccaFlowerPatchDecorator::new));

	private final BlockStateProvider blockProvider;

	public YuccaFlowerPatchDecorator(BlockStateProvider blockProvider) {
		this.blockProvider = blockProvider;
	}

	@Override
	public void place(Context context) {
		LevelSimulatedReader level = context.level();
		RandomSource random = context.random();
		BlockPos pos = getLowestBlockPos(context.logs());

		for (int j = 0; j < 64; ++j) {
			BlockPos offsetPos = pos.offset(random.nextInt(8) - random.nextInt(8), random.nextInt(4) - random.nextInt(4), random.nextInt(8) - random.nextInt(8));
			if (level.isStateAtPosition(offsetPos.below(), state -> state.is(BlockTags.SAND))) {
				YuccaFlowersDecorator.placeFlower(context, this.blockProvider.getState(random, offsetPos), offsetPos);
			}
		}
	}

	public static BlockPos getLowestBlockPos(ObjectArrayList<BlockPos> positions) {
		BlockPos lowest = positions.get(0);
		for (BlockPos pos : positions) {
			if (pos.getY() < lowest.getY()) {
				lowest = pos;
			}
		}
		return lowest;
	}

	@Override
	protected TreeDecoratorType<?> type() {
		return AtmosphericFeatures.YUCCA_FLOWER_PATCH.get();
	}
}