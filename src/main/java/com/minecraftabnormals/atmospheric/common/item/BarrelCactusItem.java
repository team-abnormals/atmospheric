package com.minecraftabnormals.atmospheric.common.item;

import com.minecraftabnormals.atmospheric.core.registry.AtmosphericBlocks;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;

import net.minecraft.world.item.Item.Properties;

public class BarrelCactusItem extends BlockItem {
	public BarrelCactusItem(Properties builder) {
		super(AtmosphericBlocks.BARREL_CACTUS.get(), builder);
	}

	@Override
	public EquipmentSlot getEquipmentSlot(ItemStack stack) {
		return EquipmentSlot.HEAD;
	}
}
