package com.teamabnormals.atmospheric.common.effect;

import com.teamabnormals.atmospheric.common.entity.projectile.PassionFruitSeed;
import com.teamabnormals.atmospheric.core.registry.AtmosphericSoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class SpittingEffect extends MobEffect {

	public SpittingEffect() {
		super(MobEffectCategory.BENEFICIAL, 15454786);
	}

	@Override
	public boolean applyEffectTick(LivingEntity entity, int amplifier) {
		RandomSource random = entity.getRandom();
		Level level = entity.level();
		if (!level.isClientSide && entity.getHealth() > 0) {
			int chance = (6 / (amplifier < 6 ? (amplifier + 1) : 6));
			if (level.getGameTime() % chance == 0) {
				level.playSound(null, entity.getX(), entity.getY(), entity.getZ(), AtmosphericSoundEvents.PASSION_FRUIT_SEED_SPIT.get(), SoundSource.NEUTRAL, 0.5F, 0.4F + (random.nextFloat() - random.nextFloat()) * 0.2F);
				PassionFruitSeed seed = new PassionFruitSeed(level, entity, amplifier);
				seed.shootFromRotation(entity, entity.getXRot(), entity.getYRot(), 0.0F, amplifier + 1, 1.0F);
				level.addFreshEntity(seed);
			}
		}
		return true;
	}

	@Override
	public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
		return true;
	}
}
