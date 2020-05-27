package com.bagel.atmospheric.common.potion;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

public class GelledEffect extends Effect {
	
	public GelledEffect() {
		super(EffectType.BENEFICIAL, 3145554);
	}
	
	public void performEffect(LivingEntity entity, int amplifier) {
		int timer = entity.getFireTimer() - ((amplifier + 1) * 40);
		entity.setFireTimer(timer < 0 ? 0 : timer);
		if (entity.getHealth() < entity.getMaxHealth()) {
			entity.heal(0.8F);
		}
	}
	
	public boolean isReady(int duration, int amplifier) {
		int k = 50 >> amplifier;
		if (k > 0) {
			return duration % k == 0;
		} else {
			return true;
		}
	}
}
