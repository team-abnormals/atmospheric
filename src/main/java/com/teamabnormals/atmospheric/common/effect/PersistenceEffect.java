package com.teamabnormals.atmospheric.common.effect;

import com.teamabnormals.atmospheric.core.Atmospheric;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;

public class PersistenceEffect extends MobEffect {
	public static final ResourceLocation PERSISTENCE_MODIFIER = Atmospheric.location("persistence_speed_boost");

	public PersistenceEffect() {
		super(MobEffectCategory.BENEFICIAL, 15494786);
	}

	@Override
	public boolean applyEffectTick(LivingEntity entity, int amplifier) {
		if (entity instanceof Player player) {
			int amount = 20 - player.getFoodData().getFoodLevel();
			AttributeInstance instance = player.getAttribute(Attributes.MOVEMENT_SPEED);
			if (instance != null) {
				instance.removeModifier(PERSISTENCE_MODIFIER);
				instance.addTransientModifier(new AttributeModifier(PERSISTENCE_MODIFIER, amount * 0.05D * (amplifier + 1), Operation.ADD_MULTIPLIED_TOTAL));
			}
			return true;
		}
		return false;
	}


	@Override
	public void removeAttributeModifiers(AttributeMap attributeMap) {
		AttributeInstance instance = attributeMap.getInstance(Attributes.MOVEMENT_SPEED);
		if (instance != null) {
			instance.removeModifier(PERSISTENCE_MODIFIER);
		}
	}

	@Override
	public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
		return true;
	}
}
