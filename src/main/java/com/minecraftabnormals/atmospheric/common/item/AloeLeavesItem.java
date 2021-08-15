package com.minecraftabnormals.atmospheric.common.item;

import com.minecraftabnormals.atmospheric.core.other.AtmosphericCriteriaTriggers;
import com.minecraftabnormals.atmospheric.core.other.AtmosphericDamageSources;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.stats.Stats;
import net.minecraft.world.World;

import java.util.Random;

public class AloeLeavesItem extends Item {
	public AloeLeavesItem(Item.Properties properties) {
		super(properties);
	}

	public ItemStack finishUsingItem(ItemStack stack, World worldIn, LivingEntity entityLiving) {
		super.finishUsingItem(stack, worldIn, entityLiving);
		if (entityLiving instanceof ServerPlayerEntity) {
			ServerPlayerEntity serverplayerentity = (ServerPlayerEntity) entityLiving;
			CriteriaTriggers.CONSUME_ITEM.trigger(serverplayerentity, stack);
			serverplayerentity.awardStat(Stats.ITEM_USED.get(this));
		}

		Random rand = new Random();
		if (rand.nextInt(5) == 0)
			entityLiving.hurt(AtmosphericDamageSources.ALOE_LEAVES, 3.0F);
		if (entityLiving.getRemainingFireTicks() > 0 && entityLiving instanceof ServerPlayerEntity) {
			ServerPlayerEntity serverplayerentity = (ServerPlayerEntity) entityLiving;
			if (!entityLiving.getCommandSenderWorld().isClientSide()) {
				AtmosphericCriteriaTriggers.PUT_OUT_FIRE.trigger(serverplayerentity);
			}
		}
		entityLiving.clearFire();
		return entityLiving.eat(worldIn, stack);
	}

	@Override
	public UseAction getUseAnimation(ItemStack stack) {
		return UseAction.EAT;
	}
}