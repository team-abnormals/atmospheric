package com.minecraftabnormals.atmospheric.common.potion;

import java.util.Random;

import com.minecraftabnormals.atmospheric.common.entity.PassionfruitSeedEntity;
import com.minecraftabnormals.atmospheric.core.registry.AtmosphericItems;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;

public class SpittingEffect extends Effect {

	public SpittingEffect() {
		super(EffectType.BENEFICIAL, 15454786);
	}
	
	public void performEffect(LivingEntity entity, int amplifier) {
		Random random = new Random();
		if (!entity.world.isRemote && entity.getHealth() > 0) {
			int chance = (6 / (amplifier < 6 ? (amplifier +1 ) : 6));
			if (entity.world.getGameTime() % chance == 0) {
				entity.getEntityWorld().playSound((PlayerEntity)null, entity.getPosX(), entity.getPosY(), entity.getPosZ(), SoundEvents.ENTITY_LLAMA_SPIT, SoundCategory.NEUTRAL, 0.5F, 0.4F / 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F);
				PassionfruitSeedEntity passionseed = new PassionfruitSeedEntity(entity.world, entity, amplifier);
				passionseed.setItem(new ItemStack(AtmosphericItems.PASSIONFRUIT_SEED.get()));
				passionseed.func_234612_a_(entity, entity.rotationPitch, entity.rotationYaw, 0.0F, amplifier + 1, 1.0F);
				entity.world.addEntity(passionseed);    
			}	
		}
	}
	
	public boolean isReady(int duration, int amplifier) {
		return true;
	}
}
