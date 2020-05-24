package com.bagel.atmospheric.common.potion;

import java.util.Map.Entry;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AbstractAttributeMap;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
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
	public void applyAttributesModifiersToEntity(LivingEntity entity, AbstractAttributeMap attributeMapIn, int amplifier) {
		if (entity instanceof PlayerEntity) {
			int amount = 20 - ((PlayerEntity) entity).getFoodStats().getFoodLevel();
			System.out.println(amount);
			for(Entry<IAttribute, AttributeModifier> entry : this.getAttributeModifierMap().entrySet()) {
				IAttributeInstance iattributeinstance = attributeMapIn.getAttributeInstance(entry.getKey());
				if (iattributeinstance != null) {
					AttributeModifier attributemodifier = entry.getValue();
					iattributeinstance.removeModifier(attributemodifier);
					iattributeinstance.applyModifier(new AttributeModifier(attributemodifier.getID(), this.getName() + " " + amplifier, amount * this.getAttributeModifierAmount(amplifier, attributemodifier), attributemodifier.getOperation()));
					System.out.println(amount * this.getAttributeModifierAmount(amplifier, attributemodifier));
				}
			}
		}
	}
}
