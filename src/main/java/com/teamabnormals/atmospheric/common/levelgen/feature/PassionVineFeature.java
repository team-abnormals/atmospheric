package com.teamabnormals.atmospheric.common.levelgen.feature;

import com.mojang.serialization.Codec;
import com.teamabnormals.atmospheric.common.block.PassionVineBlock;
import com.teamabnormals.atmospheric.core.other.tags.AtmosphericBlockTags;
import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import org.apache.commons.compress.utils.Lists;

import java.util.List;

public class PassionVineFeature extends Feature<NoneFeatureConfiguration> {
	public PassionVineFeature(Codec<NoneFeatureConfiguration> p_i49876_1_) {
		super(p_i49876_1_);
	}

	@Override
	public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
		WorldGenLevel level = context.level();
		RandomSource random = context.random();
		BlockPos origin = context.origin();
		List<BlockPos> positions = Lists.newArrayList();
		int i = 0;
		for (int j = 0; j < 400; ++j) {
			Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(random);
			BlockState state = AtmosphericBlocks.PASSION_VINE.get().defaultBlockState().setValue(PassionVineBlock.FACING, direction);
			BlockPos pos = origin.offset(random.nextInt(8) - random.nextInt(8), random.nextInt(4) - random.nextInt(4), random.nextInt(8) - random.nextInt(8));

			int lengthA = 3 + random.nextInt(2) + random.nextInt(2) + random.nextInt(2) - random.nextInt(3);

			if (level.isEmptyBlock(pos) && pos.getY() > 50 && pos.getY() < level.getMaxBuildHeight() && state.canSurvive(level, pos)) {
				positions.add(pos);
				level.setBlock(pos, getVineState(level, state, pos, random), 2);
				for (int length = 0; length < lengthA; ++length) {
					pos = pos.below();
					if (level.isEmptyBlock(pos) && pos.getY() < level.getMaxBuildHeight() && state.canSurvive(level, pos)) {
						positions.add(pos);
						level.setBlock(pos, getVineState(level, state, pos, random), 2);
					} else {
						break;
					}
				}
				++i;
			}
		}

		positions.forEach(vinePos -> {
			BlockState state = level.getBlockState(vinePos);
			if (level.getBlockState(vinePos).is(AtmosphericBlocks.PASSION_VINE.get())) {
				level.setBlock(vinePos, ((PassionVineBlock) state.getBlock()).determineState(state, level, vinePos), 2);
			}
		});
		return i > 0;
	}

	private static BlockState getVineState(WorldGenLevel world, BlockState state, BlockPos pos, RandomSource rand) {
		if (world.getBlockState(pos.relative(state.getValue(PassionVineBlock.FACING).getOpposite())).is(AtmosphericBlockTags.PASSION_VINE_GROWABLE_ON)) {
			return state.setValue(PassionVineBlock.AGE, 4);
		} else {
			return state.setValue(PassionVineBlock.AGE, 1);
		}
	}
}
