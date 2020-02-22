package com.bagel.atmospheric.common.data;

import com.bagel.atmospheric.common.block.PassionVineBlock;
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

public class PassionVineDispenseBehavior extends OptionalDispenseBehavior {
	@SuppressWarnings("deprecation")
	protected ItemStack dispenseStack(IBlockSource source, ItemStack stack) {
		this.successful = false;
		Item item = stack.getItem();
		if (item instanceof BlockItem) {
			
			Direction direction = source.getBlockState().get(DispenserBlock.FACING);
			World worldIn = source.getWorld().getWorld();
			BlockPos pos = source.getBlockPos().offset(direction);
			
	 		if (direction != Direction.UP && direction != Direction.DOWN && worldIn.getBlockState(pos).getBlock().isAir(worldIn.getBlockState(pos))) {
	 			BlockState vine = AtmosphericBlocks.PASSION_VINE.get().getDefaultState().with(PassionVineBlock.FACING, direction);
	 			worldIn.setBlockState(pos, vine);
	 		} else {
	 			Block.spawnAsEntity(worldIn, pos.offset(Direction.UP), new ItemStack(AtmosphericBlocks.PASSION_VINE.get(), 1));
	 		}
	 		stack.shrink(1);
	 		this.successful = true;	
		}
		return stack;	
	}	
}
