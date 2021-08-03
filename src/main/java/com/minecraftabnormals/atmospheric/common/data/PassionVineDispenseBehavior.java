package com.minecraftabnormals.atmospheric.common.data;

import com.minecraftabnormals.atmospheric.common.block.PassionVineBlock;
import com.minecraftabnormals.atmospheric.core.registry.AtmosphericBlocks;
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

	protected ItemStack execute(IBlockSource source, ItemStack stack) {
		Item item = stack.getItem();
		if (item instanceof BlockItem) {

			Direction direction = source.getBlockState().getValue(DispenserBlock.FACING);
			World worldIn = source.getLevel().getLevel();
			BlockPos pos = source.getPos().relative(direction);

			if (direction != Direction.UP && direction != Direction.DOWN && worldIn.getBlockState(pos).getBlock().isAir(worldIn.getBlockState(pos), worldIn, pos)) {
				BlockState vine = AtmosphericBlocks.PASSION_VINE.get().defaultBlockState().setValue(PassionVineBlock.FACING, direction);
				worldIn.setBlockAndUpdate(pos, vine);
			} else {
				Block.popResource(worldIn, pos.relative(Direction.UP), new ItemStack(AtmosphericBlocks.PASSION_VINE.get(), 1));
			}
			stack.shrink(1);
		}
		return stack;
	}
}
