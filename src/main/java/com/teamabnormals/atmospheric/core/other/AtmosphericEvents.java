package com.teamabnormals.atmospheric.core.other;

import com.teamabnormals.atmospheric.common.block.PassionVineBundleBlock;
import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import com.teamabnormals.atmospheric.core.registry.AtmosphericMobEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingTickEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Atmospheric.MOD_ID)
public class AtmosphericEvents {

	@SubscribeEvent
	public static void livingHurt(LivingHurtEvent event) {
		LivingEntity entity = event.getEntity();

		if (entity.hasEffect(AtmosphericMobEffects.RELIEF.get())) {
			int amplifier = entity.getEffect(AtmosphericMobEffects.RELIEF.get()).getAmplifier();
			if (!entity.isInvertedHealAndHarm()) {
				entity.getPersistentData().putInt("PotionHealAmplifier", amplifier);
				entity.getPersistentData().putFloat("IncomingDamage", event.getAmount());
				entity.getPersistentData().putBoolean("Heal", true);
			} else {
				if (event.getAmount() >= (amplifier + 1)) {
					event.setAmount(event.getAmount() + (amplifier + 1));
				}
			}

		}

		if (entity.hasEffect(AtmosphericMobEffects.WORSENING.get())) {
			int amplifier = entity.getEffect(AtmosphericMobEffects.WORSENING.get()).getAmplifier();
			if (!entity.isInvertedHealAndHarm()) {
				if (event.getAmount() >= (amplifier + 1)) {
					event.setAmount(event.getAmount() + (amplifier + 1));
				}
			} else {
				entity.getPersistentData().putInt("PotionHealAmplifier", amplifier);
				entity.getPersistentData().putFloat("IncomingDamage", event.getAmount());
				entity.getPersistentData().putBoolean("Heal", true);
			}
		}
	}

	@SubscribeEvent
	public static void projectileImpact(ProjectileImpactEvent event) {
		Projectile projectile = event.getProjectile();
		if (projectile instanceof Snowball snowball) {
			if (event.getRayTraceResult() instanceof BlockHitResult result) {
				Level level = snowball.getLevel();
				BlockPos pos = result.getBlockPos();
				if (level.getBlockState(pos).is(Blocks.POTTED_BAMBOO)) {
					level.setBlockAndUpdate(pos, AtmosphericBlocks.POTTED_SNOWY_BAMBOO.get().defaultBlockState());
				}
			}
		}
	}

	@SubscribeEvent
	public static void breakSpead(BreakSpeed event) {
		if (event.getState().getBlock() instanceof PassionVineBundleBlock && event.getEntity().getMainHandItem().getItem() == Items.SHEARS)
			event.setNewSpeed(15.0F);
	}

	@SubscribeEvent
	public static void livingTick(LivingTickEvent event) {
		LivingEntity entity = event.getEntity();
		float damage = entity.getPersistentData().getFloat("IncomingDamage");
		int amplifierHeal = entity.getPersistentData().getInt("PotionHealAmplifier");
		if (entity.getPersistentData().getBoolean("Heal")) {
			if (damage >= (amplifierHeal + 1)) {
				entity.heal((amplifierHeal + 1));
				entity.getPersistentData().putBoolean("Heal", false);
			}
		}
	}
}