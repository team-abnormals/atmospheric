package com.minecraftabnormals.atmospheric.common.item;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class AloeGelBottleItem extends Item {
	public AloeGelBottleItem(Item.Properties properties) {
		super(properties);
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level worldIn, LivingEntity entityLiving) {
		super.finishUsingItem(stack, worldIn, entityLiving);
		entityLiving.clearFire();
		if (entityLiving instanceof ServerPlayer serverplayerentity) {
			CriteriaTriggers.CONSUME_ITEM.trigger(serverplayerentity, stack);
			serverplayerentity.awardStat(Stats.ITEM_USED.get(this));
		}

		if (stack.isEmpty()) {
			return new ItemStack(Items.GLASS_BOTTLE);
		} else {
			if (entityLiving instanceof Player player && !((Player) entityLiving).getAbilities().instabuild) {
				ItemStack itemstack = new ItemStack(Items.GLASS_BOTTLE);
				if (!player.getInventory().add(itemstack)) {
					player.drop(itemstack, false);
				}
			}

			return stack;
		}
	}

	@Override
	public UseAnim getUseAnimation(ItemStack stack) {
		return UseAnim.DRINK;
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
		playerIn.startUsingItem(handIn);
		return InteractionResultHolder.success(playerIn.getItemInHand(handIn));
	}
}