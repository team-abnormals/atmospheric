package com.bagel.atmospheric.common.potion;

import java.util.Map.Entry;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifierManager;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

public class PersistenceEffect extends Effect {	
	public PersistenceEffect() {
		super(EffectType.BENEFICIAL, 15494786);
	}

	@Override
	public double getAttributeModifierAmount(int amplifier, AttributeModifier modifier) {
	    return 0.05 * (double)(amplifier + 1);
	}
	
	@Override
	public void applyAttributesModifiersToEntity(LivingEntity entity, AttributeModifierManager attributeMapIn, int amplifier) {
		if (entity instanceof PlayerEntity) {
			int amount = 20 - ((PlayerEntity) entity).getFoodStats().getFoodLevel();
			for(Entry<Attribute, AttributeModifier> entry : this.getAttributeModifierMap().entrySet()) {
			    ModifiableAttributeInstance iattributeinstance = attributeMapIn.createInstanceIfAbsent(entry.getKey());
				if (iattributeinstance != null) {
					AttributeModifier attributemodifier = entry.getValue();
					iattributeinstance.removeModifier(attributemodifier);
					iattributeinstance.applyPersistentModifier(new AttributeModifier(attributemodifier.getID(), this.getName() + " " + amplifier, amount * this.getAttributeModifierAmount(amplifier, attributemodifier), attributemodifier.getOperation()));
				}
			}
		}
	}
	
	@Override
	public void performEffect(LivingEntity entity, int amplifier) {
		if (entity instanceof PlayerEntity) {
			this.applyAttributesModifiersToEntity(entity, entity.getAttributeManager(), amplifier);
		}
	}
	
	@Override
	public boolean isReady(int duration, int amplifier) {
		return true;
	}
}
