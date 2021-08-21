package com.minecraftabnormals.atmospheric.common.item;

import com.minecraftabnormals.atmospheric.core.registry.AtmosphericBlocks;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;

public class BarrelCactusItem extends BlockItem {
	public BarrelCactusItem(Properties builder) {
		super(AtmosphericBlocks.BARREL_CACTUS.get(), builder);
	}

	@Override
	public EquipmentSlotType getEquipmentSlot(ItemStack stack) {
		return EquipmentSlotType.HEAD;
	}
}
