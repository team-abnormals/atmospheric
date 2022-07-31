package com.minecraftabnormals.atmospheric.common.item;

import com.minecraftabnormals.atmospheric.common.entity.PassionVineCoilEntity;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class PassionVineCoilItem extends Item {
	public PassionVineCoilItem(Item.Properties builder) {
		super(builder);
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
		ItemStack itemstack = playerIn.getItemInHand(handIn);
		worldIn.playSound(null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundEvents.SNOWBALL_THROW, SoundSource.NEUTRAL, 0.5F, 0.4F / (worldIn.getRandom().nextFloat() * 0.4F + 0.8F));
		if (!worldIn.isClientSide) {
			PassionVineCoilEntity coil = new PassionVineCoilEntity(worldIn, playerIn);
			coil.setItem(itemstack);
			coil.shootFromRotation(playerIn, playerIn.getXRot(), playerIn.getYRot(), 0.0F, 1.5F, 1.0F);
			worldIn.addFreshEntity(coil);
		}

		playerIn.awardStat(Stats.ITEM_USED.get(this));
		if (!playerIn.getAbilities().instabuild) {
			itemstack.shrink(1);
		}

		return InteractionResultHolder.sidedSuccess(itemstack, worldIn.isClientSide());
	}
}
