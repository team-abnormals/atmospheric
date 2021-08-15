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

public class PassionVineBundleDispenseBehavior extends OptionalDispenseBehavior {

	protected ItemStack execute(IBlockSource source, ItemStack stack) {
		Item item = stack.getItem();
		if (item instanceof BlockItem) {
			Direction direction = source.getBlockState().getValue(DispenserBlock.FACING);
			World worldIn = source.getLevel().getLevel();
			BlockPos pos = source.getPos().relative(direction);

			BlockPos nextPos = pos.relative(Direction.DOWN);
			Block nextBlock = worldIn.getBlockState(nextPos).getBlock();
			int counter = 9;
			if (direction != Direction.UP && direction != Direction.DOWN && worldIn.getBlockState(pos).getBlock().isAir(worldIn.getBlockState(pos), worldIn, pos)) {
				BlockState vine = AtmosphericBlocks.PASSION_VINE.get().defaultBlockState().setValue(PassionVineBlock.FACING, direction);
				worldIn.setBlockAndUpdate(pos, vine);
				counter = 8;
				while (counter > 0) {
					if (nextBlock.isAir(worldIn.getBlockState(nextPos), worldIn, nextPos)) {
						worldIn.setBlockAndUpdate(nextPos, vine);
						counter = counter - 1;
						nextPos = nextPos.relative(Direction.DOWN);
						nextBlock = worldIn.getBlockState(nextPos).getBlock();
					} else {
						break;
					}

				}
			}
			Block.popResource(worldIn, nextPos.relative(Direction.UP), new ItemStack(AtmosphericBlocks.PASSION_VINE.get(), counter));
			stack.shrink(1);
		}
		return stack;
	}
}
