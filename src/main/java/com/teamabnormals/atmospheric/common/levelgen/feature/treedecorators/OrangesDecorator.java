package com.teamabnormals.atmospheric.common.levelgen.feature.treedecorators;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.teamabnormals.atmospheric.common.block.OrangeBlock;
import com.teamabnormals.atmospheric.core.registry.AtmosphericFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OrangesDecorator extends TreeDecorator {
	public static final Codec<OrangesDecorator> CODEC = RecordCodecBuilder.create(codec -> codec.group(
			Codec.floatRange(0.0F, 1.0F).fieldOf("probability").forGetter(instance -> instance.probability),
			BlockStateProvider.CODEC.fieldOf("block_provider").forGetter(instance -> instance.blockProvider),
			Codec.floatRange(0.0F, 1.0F).fieldOf("orange_probability").forGetter(instance -> instance.orangeProbability),
			Codec.floatRange(0.0F, 1.0F).fieldOf("double_probability").forGetter(instance -> instance.doubleProbability)
	).apply(codec, OrangesDecorator::new));

	private final float probability;
	private final float orangeProbability;
	private final float doubleProbability;
	private final BlockStateProvider blockProvider;

	public OrangesDecorator(float probability, BlockStateProvider provider, float orangeProbability, float doubleProbability) {
		this.probability = probability;
		this.blockProvider = provider;
		this.orangeProbability = orangeProbability;
		this.doubleProbability = doubleProbability;
	}

	@Override
	public void place(Context context) {
		RandomSource random = context.random();
		if (random.nextFloat() < this.probability) {
			for (BlockPos pos : context.leaves()) {
				ArrayList<Direction> directions = new ArrayList<>(List.of(Direction.values()));
				directions.remove(Direction.UP);
				Collections.shuffle(directions);
				for (Direction direction : directions) {
					BlockPos offsetPos = pos.relative(direction);
					if (random.nextFloat() < this.orangeProbability && context.isAir(offsetPos)) {
						context.setBlock(offsetPos, this.blockProvider.getState(random, offsetPos).setValue(OrangeBlock.FACING, direction).setValue(OrangeBlock.ORANGES, random.nextFloat() < this.doubleProbability ? 2 : 1));
					}
				}
			}
		}
	}

	@Override
	protected TreeDecoratorType<?> type() {
		return AtmosphericFeatures.ORANGES.get();
	}
}