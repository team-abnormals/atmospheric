package com.teamabnormals.atmospheric.common.dispenser;

import com.teamabnormals.atmospheric.common.block.PassionVineBlock;
import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockSource;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.core.dispenser.OptionalDispenseItemBehavior;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.state.BlockState;

public class PassionVineDispenseBehavior extends OptionalDispenseItemBehavior {

	@Override
	protected ItemStack execute(BlockSource source, ItemStack stack) {
		Item item = stack.getItem();
		if (item instanceof BlockItem) {
			Direction direction = source.getBlockState().getValue(DispenserBlock.FACING);
			Level worldIn = source.getLevel();
			BlockPos pos = source.getPos().relative(direction);

			if (direction != Direction.UP && direction != Direction.DOWN && worldIn.getBlockState(pos).isAir()) {
				BlockState vine = AtmosphericBlocks.PASSION_VINE.get().defaultBlockState().setValue(PassionVineBlock.FACING, direction);
				worldIn.setBlockAndUpdate(pos, vine);
			} else {
				Position position = DispenserBlock.getDispensePosition(source);
				ItemStack itemstack = stack.split(1);
				spawnItem(source.getLevel(), itemstack, 6, direction, position);
			}
			stack.shrink(1);
		}
		return stack;
	}
}
