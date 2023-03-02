package com.teamabnormals.atmospheric.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class CurrantStalkBundleBlock extends RotatedPillarBlock implements SimpleWaterloggedBlock {

	public CurrantStalkBundleBlock(Properties properties) {
		super(properties);
	}

	@Override
	public void onCaughtFire(BlockState state, Level level, BlockPos pos, @Nullable Direction face, @Nullable LivingEntity igniter) {
		if (!level.isClientSide()) {
			level.destroyBlock(pos, true);
			for (Direction direction : Direction.values()) {
				if (this.isValidDirection(state, direction)) {
					BlockPos offsetPos = pos.relative(direction);
					BlockState offsetState = level.getBlockState(offsetPos);
					level.scheduleTick(offsetPos, offsetState.getBlock(), 2 + level.getRandom().nextInt(2));
				}
			}
		}
	}

	public boolean isValidDirection(BlockState state, Direction direction) {
		return true;
	}

	@Override
	public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		if (!level.isClientSide()) {
			CurrantStalkBundleBlock.breakNeighbors(level, pos);
			this.onCaughtFire(state, level, pos, null, null);
		}
	}

	public static void breakNeighbors(LevelAccessor level, BlockPos pos) {
		for (Direction direction : Direction.values()) {
			BlockPos offsetPos = pos.relative(direction);
			BlockState offsetState = level.getBlockState(offsetPos);
			if (offsetState.getBlock() instanceof CurrantLeavesBlock currantLeaves) {
				level.scheduleTick(offsetPos, currantLeaves, 2 + level.getRandom().nextInt(2));
			}
		}
	}

	@Override
	public void destroy(LevelAccessor level, BlockPos pos, BlockState state) {
		if (!level.isClientSide()) {
			breakNeighbors(level, pos);
		}
		super.destroy(level, pos, state);
	}
}
