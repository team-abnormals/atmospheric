package com.teamabnormals.atmospheric.common.block;

import com.teamabnormals.blueprint.core.util.BlockUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CactusBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ForgeHooks;

public class SnowyCactusBlock extends CactusBlock {

	public SnowyCactusBlock(Properties properties) {
		super(properties);
	}

	@Override
	public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		BlockPos abovePos = pos.above();
		if (level.isEmptyBlock(abovePos)) {
			int i;
			for (i = 1; level.getBlockState(pos.below(i)).getBlock() instanceof CactusBlock; ++i) {
			}

			if (i < 3) {
				int j = state.getValue(AGE);
				System.out.println(j);
				if (ForgeHooks.onCropsGrowPre(level, abovePos, state, true)) {
					if (j == 15) {
						level.setBlockAndUpdate(abovePos, this.defaultBlockState());
						BlockState newState = Blocks.CACTUS.defaultBlockState().setValue(AGE, 0);
						level.setBlockAndUpdate(pos, newState);
						level.neighborChanged(newState, abovePos, this, pos, false);
					} else {
						level.setBlock(pos, state.setValue(AGE, j + 1), 4);
					}
					ForgeHooks.onCropsGrowPost(level, pos, state);
				}
			}
		}
	}

	@Override
	public ItemStack getCloneItemStack(BlockGetter level, BlockPos pos, BlockState state) {
		return new ItemStack(Blocks.CACTUS);
	}

	@Override
	public BlockState updateShape(BlockState state, Direction direction, BlockState offsetState, LevelAccessor level, BlockPos pos, BlockPos offsetPos) {
		if (direction == Direction.UP && offsetState.is(Blocks.CACTUS)) {
			level.setBlock(pos.above(), BlockUtil.transferAllBlockStates(offsetState, this.defaultBlockState()), 2);
			return BlockUtil.transferAllBlockStates(state, Blocks.CACTUS.defaultBlockState());
		}

		return super.updateShape(state, direction, offsetState, level, pos, offsetPos);
	}

	@Override
	public BlockState getPlant(BlockGetter world, BlockPos pos) {
		return Blocks.CACTUS.defaultBlockState();
	}
}
