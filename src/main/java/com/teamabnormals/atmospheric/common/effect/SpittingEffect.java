package com.teamabnormals.atmospheric.common.effect;

import com.teamabnormals.atmospheric.common.entity.projectile.PassionFruitSeed;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class SpittingEffect extends MobEffect {

	public SpittingEffect() {
		super(MobEffectCategory.BENEFICIAL, 15454786);
	}

	@Override
	public void applyEffectTick(LivingEntity entity, int amplifier) {
		RandomSource random = RandomSource.create();
		if (!entity.level.isClientSide && entity.getHealth() > 0) {
			int chance = (6 / (amplifier < 6 ? (amplifier + 1) : 6));
			if (entity.level.getGameTime() % chance == 0) {
				entity.getCommandSenderWorld().playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.LLAMA_SPIT, SoundSource.NEUTRAL, 0.5F, 0.4F + (random.nextFloat() - random.nextFloat()) * 0.2F);
				PassionFruitSeed passionseed = new PassionFruitSeed(entity.level, entity, amplifier);
				passionseed.shootFromRotation(entity, entity.getXRot(), entity.getYRot(), 0.0F, amplifier + 1, 1.0F);
				entity.level.addFreshEntity(passionseed);
			}
		}
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}
