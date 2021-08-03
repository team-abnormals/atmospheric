package com.minecraftabnormals.atmospheric.common.potion;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

public class GelledEffect extends Effect {

	public GelledEffect() {
		super(EffectType.BENEFICIAL, 3145554);
	}

	public void applyEffectTick(LivingEntity entity, int amplifier) {
		int timer = entity.getRemainingFireTicks() - ((amplifier + 1) * 40);
		entity.setSecondsOnFire(timer < 0 ? 0 : timer);
		if (entity.getHealth() < entity.getMaxHealth()) {
			entity.heal(0.5F);
		}
	}

	public boolean isDurationEffectTick(int duration, int amplifier) {
		int k = 50 >> amplifier;
		if (k > 0) {
			return duration % k == 0;
		} else {
			return true;
		}
	}
}
