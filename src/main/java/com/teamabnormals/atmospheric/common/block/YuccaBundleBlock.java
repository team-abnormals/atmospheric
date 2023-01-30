package com.teamabnormals.atmospheric.common.block;

import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.state.BlockState;

public class YuccaBundleBlock extends FallingBlock {

	public YuccaBundleBlock(Properties properties) {
		super(properties);
	}

	@Override
	public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource random) {
		BlockState up = worldIn.getBlockState(pos.above());
		boolean solidBlocks = up.canOcclude() || (up.getBlock() == AtmosphericBlocks.YUCCA_BRANCH.get() && !up.getValue(YuccaBranchBlock.SNAPPED)); //|| north.isSolid() || east.isSolid() || south.isSolid() || west.isSolid();

		if (!solidBlocks && !worldIn.isClientSide) {
			this.checkFallable(worldIn, pos);
		}
	}

	private void checkFallable(Level worldIn, BlockPos pos) {
		if (worldIn.isEmptyBlock(pos.below()) || isFree(worldIn.getBlockState(pos.below())) && pos.getY() >= 0) {
			if (!worldIn.isClientSide) {
				FallingBlockEntity fallingblockentity = FallingBlockEntity.fall(worldIn, pos, worldIn.getBlockState(pos));
				this.falling(fallingblockentity);
				worldIn.addFreshEntity(fallingblockentity);
			}
		}
	}

	@Override
	protected void falling(FallingBlockEntity fallingEntity) {
		fallingEntity.setHurtsEntities(1.0F, 20);
	}
}
