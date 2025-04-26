package com.teamabnormals.atmospheric.common.levelgen.feature;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.teamabnormals.atmospheric.core.other.AtmosphericLootTables;
import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

public class SuspiciousAridSandFeature extends Feature<NoneFeatureConfiguration> {

	public SuspiciousAridSandFeature(Codec<NoneFeatureConfiguration> codec) {
		super(codec);
	}

	@Override
	public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
		WorldGenLevel level = context.level();
		RandomSource rand = context.random();
		BlockPos pos = context.origin().below();

		int i = placeSandLayer(level, pos, rand, 1, 0, 0, UniformInt.of(1, 2), ConstantInt.ZERO)
				+ placeSandLayer(level, pos, rand, 2, -2, -1, UniformInt.of(2, 3), UniformInt.of(0, 1))
				+ placeSandLayer(level, pos, rand, 2, -3, -2, UniformInt.of(3, 4), UniformInt.of(1, 3));
		if (i > 0) {
			for (BlockPos newPos : BlockPos.betweenClosedStream(pos.offset(-2, -4, -2), pos.offset(2, -1, 2)).map(BlockPos::immutable).toList()) {
				Optional<Block> block = convertToSandstone(level.getBlockState(newPos));
				if (rand.nextBoolean() && block.isPresent()) {
					level.setBlock(newPos, block.get().defaultBlockState(), 2);
				}
			}
			return true;
		}
		return false;
	}

	public static int placeSandLayer(WorldGenLevel level, BlockPos pos, RandomSource rand, int radius, int yMin, int yMax, IntProvider tries, IntProvider rareCount) {
		int i = 0;
		ArrayList<BlockPos> layer1 = BlockPos.betweenClosedStream(pos.offset(-radius, yMin, -radius), pos.offset(radius, yMax, radius))
				.filter(blockPos -> convertToSuspicious(level.getBlockState(blockPos)).isPresent())
				.map(BlockPos::immutable).collect(Collectors.toCollection(Lists::newArrayList));
		Collections.shuffle(layer1);

		if (!layer1.isEmpty()) {
			int attempts = tries.sample(rand);
			int rare = rareCount.sample(rand);
			for (int j = 0; j < attempts; j++) {
				BlockPos newPos = layer1.get(j);

				ResourceKey<LootTable> lootTable;
				if (rare > 0) {
					lootTable = AtmosphericLootTables.ARID_GARDEN_ARCHAEOLOGY_RARE;
					rare--;
				} else {
					lootTable = AtmosphericLootTables.ARID_GARDEN_ARCHAEOLOGY_COMMON;
				}

				level.setBlock(newPos, convertToSuspicious(level.getBlockState(newPos)).get().defaultBlockState(), 2);
				level.getBlockEntity(newPos, BlockEntityType.BRUSHABLE_BLOCK).ifPresent(entity -> entity.setLootTable(lootTable, newPos.asLong()));
				i++;
			}
		}

		return i;
	}

	public static Optional<Block> convertToSuspicious(BlockState state) {
		if (state.is(AtmosphericBlocks.ARID_SAND.get()) || state.is(AtmosphericBlocks.ARID_SANDSTONE.get())) {
			return Optional.of(AtmosphericBlocks.SUSPICIOUS_ARID_SAND.get());
		}
		if (state.is(AtmosphericBlocks.RED_ARID_SAND.get()) || state.is(AtmosphericBlocks.RED_ARID_SANDSTONE.get())) {
			return Optional.of(AtmosphericBlocks.SUSPICIOUS_RED_ARID_SAND.get());
		}
		return Optional.empty();
	}


	public static Optional<Block> convertToSandstone(BlockState state) {
		if (state.is(AtmosphericBlocks.ARID_SAND.get())) {
			return Optional.of(AtmosphericBlocks.ARID_SANDSTONE.get());
		}
		if (state.is(AtmosphericBlocks.RED_ARID_SAND.get())) {
			return Optional.of(AtmosphericBlocks.RED_ARID_SANDSTONE.get());
		}
		return Optional.empty();
	}
}
