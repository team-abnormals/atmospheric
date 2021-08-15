package com.minecraftabnormals.atmospheric.common.potion;

import com.minecraftabnormals.atmospheric.common.entity.PassionfruitSeedEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;

import java.util.Random;

public class SpittingEffect extends Effect {

	public SpittingEffect() {
		super(EffectType.BENEFICIAL, 15454786);
	}

	public void applyEffectTick(LivingEntity entity, int amplifier) {
		Random random = new Random();
		if (!entity.level.isClientSide && entity.getHealth() > 0) {
			int chance = (6 / (amplifier < 6 ? (amplifier + 1) : 6));
			if (entity.level.getGameTime() % chance == 0) {
				entity.getCommandSenderWorld().playSound((PlayerEntity) null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.LLAMA_SPIT, SoundCategory.NEUTRAL, 0.5F, 0.4F / 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F);
				PassionfruitSeedEntity passionseed = new PassionfruitSeedEntity(entity.level, entity, amplifier);
				passionseed.shootFromRotation(entity, entity.xRot, entity.yRot, 0.0F, amplifier + 1, 1.0F);
				entity.level.addFreshEntity(passionseed);
			}
		}
	}

	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}
