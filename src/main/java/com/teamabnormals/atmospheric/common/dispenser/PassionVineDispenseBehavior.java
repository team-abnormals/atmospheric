package com.teamabnormals.atmospheric.common.dispenser;

import com.teamabnormals.atmospheric.common.block.PassionVineBlock;
import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.OptionalDispenseItemBehavior;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;

public class PassionVineDispenseBehavior extends OptionalDispenseItemBehavior {

	@Override
	protected ItemStack execute(BlockSource source, ItemStack stack) {
		Item item = stack.getItem();
		if (item instanceof BlockItem) {
			Direction direction = source.state().getValue(DispenserBlock.FACING);
			Level level = source.level();
			BlockPos pos = source.pos().relative(direction);

			if (direction != Direction.UP && direction != Direction.DOWN && level.getBlockState(pos).isAir()) {
				level.setBlockAndUpdate(pos, AtmosphericBlocks.PASSION_VINE.get().defaultBlockState().setValue(PassionVineBlock.FACING, direction));
			} else {
				Position position = DispenserBlock.getDispensePosition(source);
				spawnItem(source.level(), stack.split(1), 6, direction, position);
			}
			stack.shrink(1);
		}
		return stack;
	}
}
