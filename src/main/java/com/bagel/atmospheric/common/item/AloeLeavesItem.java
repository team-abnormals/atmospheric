package com.bagel.atmospheric.common.item;

import java.util.Random;

import com.bagel.atmospheric.core.other.AtmosphericCriteriaTriggers;
import com.bagel.atmospheric.core.other.AtmosphericDamageSources;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.stats.Stats;
import net.minecraft.world.World;

public class AloeLeavesItem extends Item {
   public AloeLeavesItem(Item.Properties properties) {
      super(properties);
   }

   public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
	   super.onItemUseFinish(stack, worldIn, entityLiving);
	   if (entityLiving instanceof ServerPlayerEntity) {
		   ServerPlayerEntity serverplayerentity = (ServerPlayerEntity)entityLiving;
		   CriteriaTriggers.CONSUME_ITEM.trigger(serverplayerentity, stack);
		   serverplayerentity.addStat(Stats.ITEM_USED.get(this));
	   }
	   
	   Random rand = new Random();
	   if(rand.nextInt(5) == 0) entityLiving.attackEntityFrom(AtmosphericDamageSources.ALOE_LEAVES, 3.0F);
	   if (entityLiving.getFireTimer() > 0 && entityLiving instanceof ServerPlayerEntity) {
		   ServerPlayerEntity serverplayerentity = (ServerPlayerEntity) entityLiving;
		   if(!entityLiving.getEntityWorld().isRemote()) {
			   AtmosphericCriteriaTriggers.PUT_OUT_FIRE.trigger(serverplayerentity); 
		   }
	   }
	   entityLiving.setFireTimer(0);
	   return entityLiving.onFoodEaten(worldIn, stack);
   }
   
   @Override
	public UseAction getUseAction(ItemStack stack) {
       return UseAction.EAT;
   }
}