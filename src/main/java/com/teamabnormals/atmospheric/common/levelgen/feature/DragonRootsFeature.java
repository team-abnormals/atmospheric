package com.teamabnormals.atmospheric.common.levelgen.feature;

import com.mojang.serialization.Codec;
import com.teamabnormals.atmospheric.common.block.DragonRootsBlock;
import com.teamabnormals.atmospheric.common.block.state.properties.DragonRootsStage;
import com.teamabnormals.atmospheric.common.block.state.properties.DragonRootsType;
import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraftforge.common.Tags.Blocks;
import org.apache.commons.compress.utils.Lists;

import java.util.List;

public class DragonRootsFeature extends Feature<NoneFeatureConfiguration> {

	public DragonRootsFeature(Codec<NoneFeatureConfiguration> codec) {
		super(codec);
	}

	@Override
	public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
		WorldGenLevel level = context.level();
		RandomSource random = context.random();
		BlockPos origin = context.origin();

		Direction direction = null;
		List<BlockPos> positions = Lists.newArrayList();
		BlockState rootsState = AtmosphericBlocks.DRAGON_ROOTS.get().defaultBlockState().setValue(DragonRootsBlock.STAGE, DragonRootsStage.FRUIT);

		for (int j = 0; j < 512; ++j) {
			BlockPos offsetPos;
			if (direction == null) {
				offsetPos = origin.offset(random.nextInt(8) - random.nextInt(8), random.nextInt(3) - random.nextInt(3), random.nextInt(8) - random.nextInt(8));
				for (Direction dir : DragonRootsBlock.FACING.getPossibleValues()) {
					if (isOnSandstone(level, offsetPos, dir) && level.isEmptyBlock(offsetPos)) {
						direction = dir;
						rootsState = rootsState.setValue(DragonRootsBlock.FACING, direction.getOpposite());
					}
				}
			} else {
				int wideOffset = random.nextInt(8) - random.nextInt(8);
				int shortOffset = random.nextInt(3) - random.nextInt(3);
				boolean xAxis = direction.getAxis() != Axis.X;
				offsetPos = origin.offset(xAxis ? wideOffset : shortOffset, random.nextInt(3) - random.nextInt(3), xAxis ? shortOffset : wideOffset);
			}

			if (direction != null) {
				if (isOnSandOrSandstone(level, offsetPos, direction) && level.isEmptyBlock(offsetPos) && rootsState.canSurvive(level, offsetPos)) {
					if (!positions.contains(offsetPos)) {
						positions.add(offsetPos);
					}
				}
			}
		}

		if (positions.size() > 16) {
			for (BlockPos pos : positions) {
				BlockState roots = rootsState.setValue(DragonRootsBlock.TYPE, random.nextBoolean() ? DragonRootsType.DOUBLE : random.nextBoolean() ? DragonRootsType.TOP : DragonRootsType.BOTTOM);
				level.setBlock(pos, roots, 2);
			}

			return true;
		}

		return false;
	}

	public static boolean isOnSandstone(WorldGenLevel level, BlockPos pos, Direction direction) {
		BlockState state = level.getBlockState(pos.relative(direction));
		return state.is(Blocks.SANDSTONE) || state.is(AtmosphericBlocks.ARID_SANDSTONE.get());
	}

	public static boolean isOnSandOrSandstone(WorldGenLevel level, BlockPos pos, Direction direction) {
		return level.getBlockState(pos.relative(direction)).is(BlockTags.SAND) || isOnSandstone(level, pos, direction);
	}
}
