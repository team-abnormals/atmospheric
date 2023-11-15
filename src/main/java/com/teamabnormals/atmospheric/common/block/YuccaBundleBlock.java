package com.teamabnormals.atmospheric.common.block;

import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import com.teamabnormals.blueprint.common.block.BlueprintFallingBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class YuccaBundleBlock extends BlueprintFallingBlock {

	public YuccaBundleBlock(Properties properties) {
		super(properties);
	}

	@Override
	public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		BlockState aboveState = level.getBlockState(pos.above());
		boolean solidBlocks = Block.isFaceFull(aboveState.getCollisionShape(level, pos.above()), Direction.DOWN) || (aboveState.is(AtmosphericBlocks.YUCCA_BRANCH.get()) && !aboveState.getValue(YuccaBranchBlock.SNAPPED));

		if (!solidBlocks) {
			super.tick(state, level, pos, random);
		}
	}

	@Override
	protected void falling(FallingBlockEntity fallingEntity) {
		fallingEntity.setHurtsEntities(1.0F, 20);
	}
}
