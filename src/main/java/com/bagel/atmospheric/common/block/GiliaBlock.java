package com.bagel.atmospheric.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowerBlock;
import net.minecraft.potion.Effect;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class GiliaBlock extends FlowerBlock  {

	public GiliaBlock(Effect effect, int effectDuration, Properties properties) {
		super(effect, effectDuration, properties);
	}
	
	protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
		Block block = state.getBlock();
		return block.isIn(BlockTags.BAMBOO_PLANTABLE_ON);
	}
}
