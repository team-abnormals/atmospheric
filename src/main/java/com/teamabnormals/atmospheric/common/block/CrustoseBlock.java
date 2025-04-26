package com.teamabnormals.atmospheric.common.block;

import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;

public class CrustoseBlock extends Block implements BonemealableBlock, Crustose {

	public CrustoseBlock(Properties properties) {
		super(properties);
	}

	@Override
	public BlockState getToolModifiedState(BlockState state, UseOnContext context, ItemAbility toolAction, boolean simulate) {
		if (toolAction == ItemAbilities.SHOVEL_FLATTEN) {
			return AtmosphericBlocks.CRUSTOSE_PATH.get().defaultBlockState();
		}
		return super.getToolModifiedState(state, context, toolAction, simulate);
	}

	@Override
	public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		this.randomCrustoseTick(state, level, pos, random);
	}


	@Override
	public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
		return this.isCrustoseValidBonemealTarget(level, pos, state);
	}

	@Override
	public boolean isBonemealSuccess(Level worldIn, RandomSource rand, BlockPos pos, BlockState state) {
		return true;
	}

	@Override
	public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
		this.performCrustoseBonemeal(this, level, random, pos, state);
	}

	@Override
	public Block getUnspreadBlock() {
		return Blocks.DIRT;
	}
}
