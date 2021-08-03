package com.minecraftabnormals.atmospheric.common.potion;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifierManager;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

import java.util.Map.Entry;

public class PersistenceEffect extends Effect {
	public PersistenceEffect() {
		super(EffectType.BENEFICIAL, 15494786);
	}

	@Override
	public double getAttributeModifierValue(int amplifier, AttributeModifier modifier) {
		return 0.05 * (double) (amplifier + 1);
	}

	@Override
	public void addAttributeModifiers(LivingEntity entity, AttributeModifierManager attributeMapIn, int amplifier) {
		if (entity instanceof PlayerEntity) {
			int amount = 20 - ((PlayerEntity) entity).getFoodData().getFoodLevel();
			for (Entry<Attribute, AttributeModifier> entry : this.getAttributeModifiers().entrySet()) {
				ModifiableAttributeInstance iattributeinstance = attributeMapIn.getInstance(entry.getKey());
				if (iattributeinstance != null) {
					AttributeModifier attributemodifier = entry.getValue();
					iattributeinstance.removeModifier(attributemodifier);
					iattributeinstance.addPermanentModifier(new AttributeModifier(attributemodifier.getId(), this.getDescriptionId() + " " + amplifier, amount * this.getAttributeModifierValue(amplifier, attributemodifier), attributemodifier.getOperation()));
				}
			}
		}
	}

	@Override
	public void applyEffectTick(LivingEntity entity, int amplifier) {
		if (entity instanceof PlayerEntity) {
			this.addAttributeModifiers(entity, entity.getAttributes(), amplifier);
		}
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}
