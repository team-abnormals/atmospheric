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
		BlockState up = worldIn.getBlockState(pos.up());
		boolean solidBlocks = up.isSolid() || (up.getBlock() == AtmosphericBlocks.YUCCA_BRANCH.get() && !up.get(YuccaBranchBlock.SNAPPED)); //|| north.isSolid() || east.isSolid() || south.isSolid() || west.isSolid();

		if (!solidBlocks && !worldIn.isRemote) {
			this.checkFallable(worldIn, pos);
		}
	}

	private void checkFallable(World worldIn, BlockPos pos) {
		if (worldIn.isAirBlock(pos.down()) || canFallThrough(worldIn.getBlockState(pos.down())) && pos.getY() >= 0) {
			if (!worldIn.isRemote) {
				FallingBlockEntity fallingblockentity = new FallingBlockEntity(worldIn, (double) pos.getX() + 0.5D, (double) pos.getY(), (double) pos.getZ() + 0.5D, worldIn.getBlockState(pos));
				this.onStartFalling(fallingblockentity);
				worldIn.addEntity(fallingblockentity);
			}
		}
	}

	protected void onStartFalling(FallingBlockEntity fallingEntity) {
		fallingEntity.setHurtEntities(true);
	}
}
