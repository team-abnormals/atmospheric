package com.minecraftabnormals.atmospheric.common.block;

import com.google.common.base.Supplier;
import com.minecraftabnormals.atmospheric.core.other.AtmosphericTags;
import com.minecraftabnormals.abnormals_core.common.blocks.AbnormalsFlowerBlock;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.potion.Effect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class GiliaBlock extends AbnormalsFlowerBlock  {

	public GiliaBlock(Supplier<Effect> effect, int effectDuration, Properties properties) {
		super(effect, effectDuration, properties);
	}
	
	@Override
	protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
		Block block = state.getBlock();
		return block.isIn(AtmosphericTags.YUCCA_PLANTABLE_ON);
	}
	
	@Override
	public net.minecraftforge.common.PlantType getPlantType(IBlockReader world, BlockPos pos) {
		return net.minecraftforge.common.PlantType.DESERT;	
	}
}
