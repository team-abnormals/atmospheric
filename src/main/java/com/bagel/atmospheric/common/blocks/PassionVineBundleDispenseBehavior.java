package com.bagel.atmospheric.common.blocks;

import com.bagel.atmospheric.core.registry.AtmosphericBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.DispenserBlock;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.dispenser.OptionalDispenseBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PassionVineBundleDispenseBehavior extends OptionalDispenseBehavior {
	@SuppressWarnings("deprecation")
	protected ItemStack dispenseStack(IBlockSource source, ItemStack stack) {
		this.successful = false;
		Item item = stack.getItem();
		if (item instanceof BlockItem) {
			Direction direction = source.getBlockState().get(DispenserBlock.FACING);
			World worldIn = source.getWorld().getWorld();
			BlockPos pos = source.getBlockPos().offset(direction);
			
			BlockPos nextPos = pos.offset(Direction.DOWN);
	 	    Block nextBlock = worldIn.getBlockState(nextPos).getBlock();
	 		int counter = 9;
	 		if (direction != Direction.UP && direction != Direction.DOWN && worldIn.getBlockState(pos).getBlock().isAir(worldIn.getBlockState(pos))) {
	 		BlockState vine = AtmosphericBlocks.PASSION_VINE.get().getDefaultState().with(PassionVineBlock.FACING, direction);
	 		worldIn.setBlockState(pos, vine);
	 		counter = 8;
	 		while (counter > 0) {
	 			if (nextBlock.isAir(worldIn.getBlockState(nextPos))) {
	 				worldIn.setBlockState(nextPos, vine);
	 				counter = counter - 1;
	 				nextPos = nextPos.offset(Direction.DOWN);
	 				nextBlock = worldIn.getBlockState(nextPos).getBlock();
	 			} else {
	 				break;	
	 			}
	 			
	 		}
	 		}
	 		Block.spawnAsEntity(worldIn, nextPos.offset(Direction.UP), new ItemStack(AtmosphericBlocks.PASSION_VINE.get(), counter));
	 		
	 		stack.shrink(1);
	 		this.successful = true;	
		}
		return stack;	
	}	
}
