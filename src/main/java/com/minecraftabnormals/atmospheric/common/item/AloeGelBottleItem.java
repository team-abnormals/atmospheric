package com.minecraftabnormals.atmospheric.common.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class AloeGelBottleItem extends YuccaJuiceItem {
	public AloeGelBottleItem(Item.Properties properties) {
		super(properties);
	}

	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
		entityLiving.extinguish();
		return super.onItemUseFinish(stack, worldIn, entityLiving);

	}
}