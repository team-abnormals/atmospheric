package com.teamabnormals.atmospheric.common.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class PersistenceEffect extends MobEffect {
	public PersistenceEffect() {
		super(MobEffectCategory.BENEFICIAL, 15494786);
	}

	// TODO: Finish
//	@Override
//	public double getAttributeModifierValue(int amplifier, AttributeModifier modifier) {
//		return 0.05 * (double) (amplifier + 1);
//	}
//
//	@Override
//	public void addAttributeModifiers(LivingEntity entity, AttributeMap attributeMapIn, int amplifier) {
//		if (entity instanceof Player) {
//			int amount = 20 - ((Player) entity).getFoodData().getFoodLevel();
//			for (Entry<Attribute, AttributeModifier> entry : this.getAttributeModifiers().entrySet()) {
//				AttributeInstance iattributeinstance = attributeMapIn.getInstance(entry.getKey());
//				if (iattributeinstance != null) {
//					AttributeModifier attributemodifier = entry.getValue();
//					iattributeinstance.removeModifier(attributemodifier);
//					iattributeinstance.addPermanentModifier(new AttributeModifier(attributemodifier.getId(), this.getDescriptionId() + " " + amplifier, amount * this.getAttributeModifierValue(amplifier, attributemodifier), attributemodifier.getOperation()));
//				}
//			}
//		}
//	}
//
//	@Override
//	public boolean applyEffectTick(LivingEntity entity, int amplifier) {
//		if (entity instanceof Player) {
//			this.addAttributeModifiers(entity, entity.getAttributes(), amplifier);
//		}
//		return true;
//	}

	@Override
	public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
		return true;
	}
}
