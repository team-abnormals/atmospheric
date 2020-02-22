package com.bagel.atmospheric.common.block;

import java.util.Random;

import net.minecraft.block.BlockState;
import net.minecraft.block.FallingBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.FallingBlockEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class YuccaBundleBlock extends FallingBlock {

	public YuccaBundleBlock(Properties properties) {
		super(properties);
	}

	@Override
	public void tick(BlockState state, World worldIn, BlockPos pos, Random random) {
		Material up = worldIn.getBlockState(pos.offset(Direction.UP)).getMaterial();
		/*Material north = worldIn.getBlockState(pos.offset(Direction.NORTH)).getMaterial();
		* Material east = worldIn.getBlockState(pos.offset(Direction.EAST)).getMaterial();
		* Material south = worldIn.getBlockState(pos.offset(Direction.SOUTH)).getMaterial();
		* Material west = worldIn.getBlockState(pos.offset(Direction.WEST)).getMaterial();*/
		
		boolean solidBlocks = up.isSolid(); //|| north.isSolid() || east.isSolid() || south.isSolid() || west.isSolid();
		
		if (!solidBlocks && !worldIn.isRemote) {
			this.checkFallable(worldIn, pos);
		}
	}

	private void checkFallable(World worldIn, BlockPos pos) {
		if (worldIn.isAirBlock(pos.down()) || canFallThrough(worldIn.getBlockState(pos.down())) && pos.getY() >= 0) {
			if (!worldIn.isRemote) {
				FallingBlockEntity fallingblockentity = new FallingBlockEntity(worldIn, (double) pos.getX() + 0.5D,(double) pos.getY(), (double) pos.getZ() + 0.5D, worldIn.getBlockState(pos));
				this.onStartFalling(fallingblockentity);
				worldIn.addEntity(fallingblockentity);
			}
		}
	}
}
