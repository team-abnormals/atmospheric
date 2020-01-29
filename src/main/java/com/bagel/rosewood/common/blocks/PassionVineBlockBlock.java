package com.bagel.rosewood.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PassionVineBlockBlock extends Block {

	public PassionVineBlockBlock(Properties properties) {
		super(properties);
	}
	
	public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
		entityIn.fall(fallDistance, 0.2F);	
	}
	
	public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {

		      super.onBlockHarvested(worldIn, pos, state, player);
		   }	

}
