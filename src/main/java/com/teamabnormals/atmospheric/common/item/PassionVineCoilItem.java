package com.teamabnormals.atmospheric.common.item;

import com.teamabnormals.atmospheric.common.entity.projectile.ThrownPassionVineCoil;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileItem;
import net.minecraft.world.level.Level;

public class PassionVineCoilItem extends Item implements ProjectileItem {

	public PassionVineCoilItem(Item.Properties builder) {
		super(builder);
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);
		level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.SNOWBALL_THROW, SoundSource.NEUTRAL, 0.5F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
		if (!level.isClientSide) {
			ThrownPassionVineCoil coil = new ThrownPassionVineCoil(level, player);
			coil.setItem(stack);
			coil.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
			level.addFreshEntity(coil);
		}

		player.awardStat(Stats.ITEM_USED.get(this));
		stack.consume(1, player);
		return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
	}

	@Override
	public Projectile asProjectile(Level level, Position pos, ItemStack stack, Direction direction) {
		ThrownPassionVineCoil coil = new ThrownPassionVineCoil(level, pos.x(), pos.y(), pos.z());
		coil.setItem(stack);
		return coil;
	}
}
