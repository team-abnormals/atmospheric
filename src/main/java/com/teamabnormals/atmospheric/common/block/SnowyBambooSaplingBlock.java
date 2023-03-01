package com.teamabnormals.atmospheric.common.block;

import com.teamabnormals.atmospheric.core.other.tags.AtmosphericBlockTags;
import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BambooBlock;
import net.minecraft.world.level.block.BambooSaplingBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BambooLeaves;

public class SnowyBambooSaplingBlock extends BambooSaplingBlock {

	public SnowyBambooSaplingBlock(BlockBehaviour.Properties properties) {
		super(properties);
	}

	@Override
	public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
		return level.getBlockState(pos.below()).is(AtmosphericBlockTags.SNOWY_BAMBOO_PLANTABLE_ON);
	}

	@Override
	public BlockState updateShape(BlockState state, Direction direction, BlockState offsetState, LevelAccessor level, BlockPos pos, BlockPos offsetPos) {
		if (!state.canSurvive(level, pos)) {
			return Blocks.AIR.defaultBlockState();
		} else {
			if (direction == Direction.UP && offsetState.is(AtmosphericBlocks.SNOWY_BAMBOO.get())) {
				level.setBlock(pos, AtmosphericBlocks.SNOWY_BAMBOO.get().defaultBlockState(), 2);
			}

			return state;
		}
	}

	@Override
	protected void growBamboo(Level level, BlockPos pos) {
		level.setBlock(pos.above(), AtmosphericBlocks.SNOWY_BAMBOO.get().defaultBlockState().setValue(BambooBlock.LEAVES, BambooLeaves.SMALL), 3);
	}
}
