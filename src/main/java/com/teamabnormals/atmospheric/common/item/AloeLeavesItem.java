package com.teamabnormals.atmospheric.common.item;

import com.teamabnormals.atmospheric.core.other.AtmosphericCriteriaTriggers;
import com.teamabnormals.atmospheric.core.other.AtmosphericDamageSources;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

import java.util.Random;

public class AloeLeavesItem extends Item {
	public AloeLeavesItem(Item.Properties properties) {
		super(properties);
	}

	public ItemStack finishUsingItem(ItemStack stack, Level worldIn, LivingEntity entityLiving) {
		super.finishUsingItem(stack, worldIn, entityLiving);
		if (entityLiving instanceof ServerPlayer serverplayerentity) {
			CriteriaTriggers.CONSUME_ITEM.trigger(serverplayerentity, stack);
			serverplayerentity.awardStat(Stats.ITEM_USED.get(this));
		}

		Random rand = new Random();
		if (rand.nextInt(5) == 0)
			entityLiving.hurt(AtmosphericDamageSources.ALOE_LEAVES, 3.0F);
		if (entityLiving.getRemainingFireTicks() > 0 && entityLiving instanceof ServerPlayer serverplayerentity) {
			if (!entityLiving.getCommandSenderWorld().isClientSide()) {
				AtmosphericCriteriaTriggers.PUT_OUT_FIRE.trigger(serverplayerentity);
			}
		}
		entityLiving.clearFire();
		return entityLiving.eat(worldIn, stack);
	}

	@Override
	public UseAnim getUseAnimation(ItemStack stack) {
		return UseAnim.EAT;
	}
}