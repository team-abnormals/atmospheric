package com.minecraftabnormals.atmospheric.core.other;

import com.minecraftabnormals.atmospheric.core.Atmospheric;
import com.minecraftabnormals.atmospheric.core.registry.AtmosphericEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Atmospheric.MODID)
public class AtmosphericEvents {

	@SubscribeEvent
	public static void livingHurt(LivingHurtEvent event) {
		LivingEntity entity = event.getEntityLiving();

		// GELLED //
		if (event.getEntityLiving().isPotionActive(AtmosphericEffects.GELLED.get())) {
			int amplifier = entity.getActivePotionEffect(AtmosphericEffects.GELLED.get()).getAmplifier();
			if (event.getSource().isFireDamage()) {
				event.setAmount(event.getAmount() / (amplifier + 2));
			}
		}

		// RELIEF //
		if (entity.isPotionActive(AtmosphericEffects.RELIEF.get())) {
			if (!entity.isEntityUndead()) {
				int amplifier = entity.getActivePotionEffect(AtmosphericEffects.RELIEF.get()).getAmplifier();
				entity.getPersistentData().putInt("PotionHealAmplifier", amplifier);
				entity.getPersistentData().putFloat("IncomingDamage", event.getAmount());
				entity.getPersistentData().putBoolean("Heal", true);
			} else {
				int amplifier = entity.getActivePotionEffect(AtmosphericEffects.RELIEF.get()).getAmplifier();
				if (event.getAmount() >= (amplifier + 1)) {
					event.setAmount(event.getAmount() + (amplifier + 1));
				}
			}

		}

		// WORSENING //
		if (entity.isPotionActive(AtmosphericEffects.WORSENING.get())) {
			if (!entity.isEntityUndead()) {
				int amplifier = entity.getActivePotionEffect(AtmosphericEffects.WORSENING.get()).getAmplifier();
				if (event.getAmount() >= (amplifier + 1)) {
					event.setAmount(event.getAmount() + (amplifier + 1));
				}
			} else {
				int amplifier = entity.getActivePotionEffect(AtmosphericEffects.WORSENING.get()).getAmplifier();
				entity.getPersistentData().putInt("PotionHealAmplifier", amplifier);
				entity.getPersistentData().putFloat("IncomingDamage", event.getAmount());
				entity.getPersistentData().putBoolean("Heal", true);
			}
		}
	}

	@SubscribeEvent
	public static void livingTick(LivingUpdateEvent event) {

		// RELIEF //
		float damage = event.getEntity().getPersistentData().getFloat("IncomingDamage");
		int amplifierHeal = event.getEntity().getPersistentData().getInt("PotionHealAmplifier");
		boolean heal = event.getEntity().getPersistentData().getBoolean("Heal");
		if (heal == true) {
			if (damage >= (amplifierHeal + 1)) {
				event.getEntityLiving().heal((amplifierHeal + 1));
				event.getEntityLiving().getPersistentData().putBoolean("Heal", false);
			}
		}
	}
}