package com.teamabnormals.atmospheric.common.dispenser;

import com.teamabnormals.atmospheric.common.block.PassionVineBlock;
import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockSource;
import net.minecraft.core.Direction;
import net.minecraft.core.dispenser.OptionalDispenseItemBehavior;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.state.BlockState;

public class PassionVineBundleDispenseBehavior extends OptionalDispenseItemBehavior {

	@Override
	protected ItemStack execute(BlockSource source, ItemStack stack) {
		Item item = stack.getItem();
		if (item instanceof BlockItem) {
			Direction direction = source.getBlockState().getValue(DispenserBlock.FACING);
			Level worldIn = source.getLevel().getLevel();
			BlockPos pos = source.getPos().relative(direction);

			BlockPos nextPos = pos.relative(Direction.DOWN);
			BlockState nextBlock = worldIn.getBlockState(nextPos);
			int counter = 9;
			if (direction != Direction.UP && direction != Direction.DOWN && worldIn.getBlockState(pos).isAir()) {
				BlockState vine = AtmosphericBlocks.PASSION_VINE.get().defaultBlockState().setValue(PassionVineBlock.FACING, direction);
				worldIn.setBlockAndUpdate(pos, vine);
				counter = 8;
				while (counter > 0) {
					if (nextBlock.isAir()) {
						worldIn.setBlockAndUpdate(nextPos, vine);
						counter = counter - 1;
						nextPos = nextPos.relative(Direction.DOWN);
						nextBlock = worldIn.getBlockState(nextPos);
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
