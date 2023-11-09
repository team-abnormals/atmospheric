package com.teamabnormals.atmospheric.common.levelgen.feature.treedecorators;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.teamabnormals.atmospheric.core.registry.AtmosphericFeatures;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;

import java.util.Collections;

public class YuccaBundleDecorator extends TreeDecorator {
	public static final Codec<YuccaBundleDecorator> CODEC = RecordCodecBuilder.create(codec -> codec.group(
			Codec.floatRange(0.0F, 1.0F).fieldOf("probability").forGetter(instance -> instance.probability),
			BlockStateProvider.CODEC.fieldOf("bundle_provider").forGetter(instance -> instance.bundleProvider),
			BlockStateProvider.CODEC.fieldOf("branch_provider").forGetter(instance -> instance.branchProvider)
	).apply(codec, YuccaBundleDecorator::new));

	private final float probability;
	private final BlockStateProvider bundleProvider;
	private final BlockStateProvider branchProvider;

	public YuccaBundleDecorator(float probability, BlockStateProvider bundleProvider, BlockStateProvider branchProvider) {
		this.probability = probability;
		this.bundleProvider = bundleProvider;
		this.branchProvider = branchProvider;
	}

	@Override
	public void place(Context context) {
		ObjectArrayList<BlockPos> logs = context.logs();
		Collections.shuffle(logs);
		RandomSource random = context.random();

		if (context.random().nextFloat() < this.probability) {
			for (BlockPos pos : logs) {
				if (context.isAir(pos.below()) && context.isAir(pos.below(2))) {
					context.setBlock(pos.below(), this.branchProvider.getState(random, pos.below()));
					context.setBlock(pos.below(2), this.bundleProvider.getState(random, pos.below(2)));
					return;
				}
			}
		}
	}

	@Override
	protected TreeDecoratorType<?> type() {
		return AtmosphericFeatures.YUCCA_BUNDLE.get();
	}
}