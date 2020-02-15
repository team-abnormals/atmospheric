package com.bagel.atmospheric.common.block;

import com.bagel.atmospheric.core.registry.AtmosphericBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.trees.Tree;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class YuccaSaplingBlock extends SaplingBlock implements net.minecraftforge.common.IPlantable {
	public YuccaSaplingBlock(Tree tree, Properties properties) {
        super(tree, properties);
    }
	
	@Override
	protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
		Block block = state.getBlock();
	    return block == Blocks.GRASS_BLOCK || block == Blocks.DIRT || block == Blocks.COARSE_DIRT || block == Blocks.PODZOL || block == Blocks.FARMLAND
	    		|| block == Blocks.SAND || block == Blocks.RED_SAND || block == AtmosphericBlocks.ARID_SAND.get();      
	}
	
	@Override
	public net.minecraftforge.common.PlantType getPlantType(IBlockReader world, BlockPos pos) {
		return net.minecraftforge.common.PlantType.Desert;	
	}
}
