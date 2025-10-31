package com.teamabnormals.atmospheric.common.dispenser;

import com.teamabnormals.atmospheric.common.block.PassionVineBlock;
import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.dispenser.BlockSource;
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
			Direction direction = source.state().getValue(DispenserBlock.FACING);
			Level level = source.level().getLevel();
			BlockPos pos = source.pos().relative(direction);
			if (direction != Direction.UP && direction != Direction.DOWN && level.getBlockState(pos).isAir()) {
				BlockState vine = AtmosphericBlocks.PASSION_VINE.get().defaultBlockState().setValue(PassionVineBlock.FACING, direction);
				int counter = 0;
				while (counter < 9 && level.isEmptyBlock(pos)) {
					level.setBlockAndUpdate(pos, vine);
					pos = pos.below();
					counter++;
				}
				if (counter < 9) {
					Block.popResource(level, pos.relative(Direction.UP), new ItemStack(AtmosphericBlocks.PASSION_VINE.get(), 9 - counter));
				}
				stack.shrink(1);
			}
		}
		return stack;
	}
}
