package com.teamabnormals.atmospheric.common.block;

import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import com.teamabnormals.blueprint.core.util.BlockUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.lighting.LayerLightEngine;

public interface Crustose {

	Block getUnspreadBlock();

	static boolean canBeGrass(BlockState state, LevelReader world, BlockPos pos) {
		BlockPos blockpos = pos.above();
		BlockState blockstate = world.getBlockState(blockpos);
		if (blockstate.getFluidState().getAmount() == 8) {
			return false;
		} else {
			int i = LayerLightEngine.getLightBlockInto(world, state, pos, blockstate, blockpos, Direction.UP, blockstate.getLightBlock(world, blockpos));
			return i < world.getMaxLightLevel();
		}
	}

	static boolean canPropagate(BlockState state, LevelReader world, BlockPos pos) {
		BlockPos blockpos = pos.above();
		return canBeGrass(state, world, pos) && !world.getFluidState(blockpos).is(FluidTags.WATER);
	}

	default void randomCrustoseTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		if (!canBeGrass(state, level, pos)) {
			if (!level.isAreaLoaded(pos, 1)) return;
			level.setBlockAndUpdate(pos, BlockUtil.transferAllBlockStates(state, this.getUnspreadBlock().defaultBlockState()));
		} else {
			if (!level.isAreaLoaded(pos, 3)) return;
			if (level.getMaxLocalRawBrightness(pos.above()) >= 9) {
				for (int i = 0; i < 4; ++i) {
					BlockPos offsetPos = pos.offset(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
					BlockState offsetState = level.getBlockState(offsetPos);
					Block newBlock = offsetState.is(Blocks.DIRT) ? AtmosphericBlocks.CRUSTOSE.get() : offsetState.is(AtmosphericBlocks.ASPEN_LOG.get()) ? AtmosphericBlocks.CRUSTOSE_LOG.get() : offsetState.is(AtmosphericBlocks.ASPEN_WOOD.get()) ? AtmosphericBlocks.CRUSTOSE_WOOD.get() : null;
					if (newBlock != null) {
						BlockState newState = BlockUtil.transferAllBlockStates(level.getBlockState(offsetPos), newBlock.defaultBlockState());
						if (Crustose.canPropagate(newState, level, offsetPos)) {
							level.setBlockAndUpdate(offsetPos, newState);
						}
					}
				}
			}

		}
	}

	default boolean isCrustoseValidBonemealTarget(BlockGetter level, BlockPos pos, BlockState state, boolean isClient) {
		return level.getBlockState(pos.above()).isAir();
	}

	default void performCrustoseBonemeal(Block block, ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
		BlockPos abovePos = pos.above();
		BlockState crustoseSprouts = AtmosphericBlocks.GOLDEN_GROWTHS.get().defaultBlockState();

		label48:
		for (int i = 0; i < 128; ++i) {
			BlockPos offsetPos = abovePos;

			for (int j = 0; j < i / 16; ++j) {
				offsetPos = offsetPos.offset(random.nextInt(3) - 1, (random.nextInt(3) - 1) * random.nextInt(3) / 2, random.nextInt(3) - 1);
				if (!level.getBlockState(offsetPos.below()).is(block) || level.getBlockState(offsetPos).isCollisionShapeFullBlock(level, offsetPos)) {
					continue label48;
				}
			}

			if (level.getBlockState(offsetPos).isAir() && crustoseSprouts.canSurvive(level, offsetPos)) {
				level.setBlock(offsetPos, crustoseSprouts, 3);
			}
		}
	}
}
