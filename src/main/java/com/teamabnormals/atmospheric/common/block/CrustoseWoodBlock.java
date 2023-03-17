package com.teamabnormals.atmospheric.common.block;

import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import com.teamabnormals.blueprint.common.block.wood.WoodBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Supplier;

public class CrustoseWoodBlock extends WoodBlock implements BonemealableBlock, Crustose {

	public CrustoseWoodBlock(Supplier<Block> block, Properties properties) {
		super(block, properties);
	}

	@Override
	public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		this.randomCrustoseTick(state, level, pos, random);
	}

	@Override
	public boolean isValidBonemealTarget(BlockGetter level, BlockPos pos, BlockState state, boolean isClient) {
		return this.isCrustoseValidBonemealTarget(level, pos, state, isClient);
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
		return AtmosphericBlocks.ASPEN_WOOD.get();
	}
}
