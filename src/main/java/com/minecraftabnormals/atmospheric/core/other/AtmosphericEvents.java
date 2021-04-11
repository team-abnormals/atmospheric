package com.minecraftabnormals.atmospheric.core.other;

import com.minecraftabnormals.atmospheric.core.Atmospheric;
import com.minecraftabnormals.atmospheric.core.registry.AtmosphericEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Atmospheric.MOD_ID)
public class AtmosphericEvents {

	@SubscribeEvent
	public static void livingHurt(LivingHurtEvent event) {
		LivingEntity entity = event.getEntityLiving();

		if (entity.isPotionActive(AtmosphericEffects.GELLED.get())) {
			int amplifier = entity.getActivePotionEffect(AtmosphericEffects.GELLED.get()).getAmplifier();
			if (event.getSource().isFireDamage()) {
				event.setAmount(event.getAmount() / (amplifier + 2));
			}
		}

		if (entity.isPotionActive(AtmosphericEffects.RELIEF.get())) {
			int amplifier = entity.getActivePotionEffect(AtmosphericEffects.RELIEF.get()).getAmplifier();
			if (!entity.isEntityUndead()) {
				entity.getPersistentData().putInt("PotionHealAmplifier", amplifier);
				entity.getPersistentData().putFloat("IncomingDamage", event.getAmount());
				entity.getPersistentData().putBoolean("Heal", true);
			} else {
				if (event.getAmount() >= (amplifier + 1)) {
					event.setAmount(event.getAmount() + (amplifier + 1));
				}
			}

		}

		if (entity.isPotionActive(AtmosphericEffects.WORSENING.get())) {
			int amplifier = entity.getActivePotionEffect(AtmosphericEffects.WORSENING.get()).getAmplifier();
			if (!entity.isEntityUndead()) {
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
	public static void livingTick(LivingUpdateEvent event) {
		float damage = event.getEntity().getPersistentData().getFloat("IncomingDamage");
		int amplifierHeal = event.getEntity().getPersistentData().getInt("PotionHealAmplifier");
		if (event.getEntity().getPersistentData().getBoolean("Heal")) {
			if (damage >= (amplifierHeal + 1)) {
				event.getEntityLiving().heal((amplifierHeal + 1));
				event.getEntityLiving().getPersistentData().putBoolean("Heal", false);
			}
		}
	}
}