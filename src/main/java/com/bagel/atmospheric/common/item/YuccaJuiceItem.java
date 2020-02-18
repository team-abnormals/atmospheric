package com.bagel.atmospheric.common.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;

public class YuccaJuiceItem extends Item {
   public YuccaJuiceItem(Item.Properties properties) {
      super(properties);
   }

   public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
      super.onItemUseFinish(stack, worldIn, entityLiving);
      return new ItemStack(Items.GLASS_BOTTLE);
   }
}