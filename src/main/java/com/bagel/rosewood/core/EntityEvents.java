package com.bagel.rosewood.core;

import com.bagel.rosewood.core.registry.RosewoodEffects;

import net.minecraft.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Rosewood.MODID)
public class EntityEvents {
	@SubscribeEvent
	public static void onLivingHurt(LivingHurtEvent event) {
		LivingEntity entity = event.getEntityLiving();
		
		if (entity.isPotionActive(RosewoodEffects.RELIEF.get())) {
			int amplifier = entity.getActivePotionEffect(RosewoodEffects.RELIEF.get()).getAmplifier();
			if (event.getAmount() >= (amplifier + 1)) {
				entity.heal((amplifier + 1));
			}
		}
		
		if (entity.isPotionActive(RosewoodEffects.WORSENING.get())) {
			int amplifier = entity.getActivePotionEffect(RosewoodEffects.WORSENING.get()).getAmplifier();
			if (event.getAmount() >= (amplifier + 1)) {
				entity.attackEntityFrom(event.getSource(), (amplifier + 1));
			}
			
		}
	}
}
