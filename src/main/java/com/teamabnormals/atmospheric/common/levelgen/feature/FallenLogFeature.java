package com.teamabnormals.atmospheric.common.levelgen.feature;

import com.google.common.collect.Sets;
import com.mojang.serialization.Codec;
import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Plane;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.material.Material;

import java.util.Set;

public class FallenLogFeature extends Feature<SimpleBlockConfiguration> {

	public FallenLogFeature(Codec<SimpleBlockConfiguration> codec) {
		super(codec);
	}

	public boolean place(FeaturePlaceContext<SimpleBlockConfiguration> context) {
		SimpleBlockConfiguration config = context.config();
		RandomSource random = context.random();
		WorldGenLevel level = context.level();
		BlockPos origin = context.origin();

		int length = 3 + random.nextInt(2) + random.nextInt(2);
		Direction direction = Plane.HORIZONTAL.getRandomDirection(random);
		Set<BlockPos> logPositions = Sets.newHashSet();

		if (level.getBlockState(origin.below()).is(AtmosphericBlocks.CRUSTOSE.get())) {
			for (int i = 0; i < length; i++) {
				BlockPos offsetPos = origin.relative(direction, i);
				if ((level.isEmptyBlock(offsetPos) || isReplaceablePlant(level, offsetPos)) && !level.isEmptyBlock(offsetPos.below())) {
					logPositions.add(offsetPos.immutable());
				} else {
					return false;
				}
			}

			if (logPositions.size() > 3) {
				logPositions.forEach(pos -> {
					level.setBlock(pos, config.toPlace().getState(random, pos).setValue(BlockStateProperties.AXIS, direction.getAxis()), 2);
					if (level.getBlockState(pos.below()).is(AtmosphericBlocks.CRUSTOSE.get())) {
						level.setBlock(pos.below(), Blocks.DIRT.defaultBlockState(), 2);
					}
				});
				return true;
			}
		}

		return false;
	}

	private static boolean isReplaceablePlant(LevelSimulatedReader level, BlockPos pos) {
		return level.isStateAtPosition(pos, (state) -> {
			Material material = state.getMaterial();
			return material == Material.REPLACEABLE_PLANT || material == Material.REPLACEABLE_WATER_PLANT || material == Material.REPLACEABLE_FIREPROOF_PLANT;
		});
	}
}