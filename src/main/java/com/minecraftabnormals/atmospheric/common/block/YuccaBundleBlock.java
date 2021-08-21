package com.minecraftabnormals.atmospheric.common.block;

import com.minecraftabnormals.atmospheric.core.registry.AtmosphericBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.FallingBlock;
import net.minecraft.entity.item.FallingBlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class YuccaBundleBlock extends FallingBlock {

	public YuccaBundleBlock(Properties properties) {
		super(properties);
	}

	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
		BlockState up = worldIn.getBlockState(pos.above());
		boolean solidBlocks = up.canOcclude() || (up.getBlock() == AtmosphericBlocks.YUCCA_BRANCH.get() && !up.getValue(YuccaBranchBlock.SNAPPED)); //|| north.isSolid() || east.isSolid() || south.isSolid() || west.isSolid();

		if (!solidBlocks && !worldIn.isClientSide) {
			this.checkFallable(worldIn, pos);
		}
	}

	private void checkFallable(World worldIn, BlockPos pos) {
		if (worldIn.isEmptyBlock(pos.below()) || isFree(worldIn.getBlockState(pos.below())) && pos.getY() >= 0) {
			if (!worldIn.isClientSide) {
				FallingBlockEntity fallingblockentity = new FallingBlockEntity(worldIn, (double) pos.getX() + 0.5D, pos.getY(), (double) pos.getZ() + 0.5D, worldIn.getBlockState(pos));
				this.falling(fallingblockentity);
				worldIn.addFreshEntity(fallingblockentity);
			}
		}
	}

	protected void falling(FallingBlockEntity fallingEntity) {
		fallingEntity.setHurtsEntities(true);
	}
}
