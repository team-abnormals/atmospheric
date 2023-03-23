package com.teamabnormals.atmospheric.core.other;

import com.teamabnormals.atmospheric.common.block.YuccaBundleBlock;
import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import com.teamabnormals.atmospheric.core.registry.AtmosphericMobEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingTickEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = Atmospheric.MOD_ID)
public class AtmosphericEvents {

	@SubscribeEvent
	public static void projectileImpact(ProjectileImpactEvent event) {
		if (event.getProjectile() instanceof Snowball snowball) {
			if (event.getRayTraceResult() instanceof BlockHitResult result) {
				Level level = snowball.getLevel();
				BlockPos pos = result.getBlockPos();
				if (level.getBlockState(pos).is(Blocks.POTTED_BAMBOO)) {
					level.setBlockAndUpdate(pos, AtmosphericBlocks.POTTED_SNOWY_BAMBOO.get().defaultBlockState());
				}
			}
		}
	}

	@SubscribeEvent
	public static void breakSpead(BreakSpeed event) {
		if (event.getEntity().getMainHandItem().is(Tags.Items.SHEARS)) {
			BlockState state = event.getState();
			if (state.is(AtmosphericBlocks.PASSION_VINE_BUNDLE.get()) || state.is(AtmosphericBlocks.YUCCA_FLOWER.get()) || state.is(AtmosphericBlocks.TALL_YUCCA_FLOWER.get()) || state.is(AtmosphericBlocks.DRAGON_ROOTS.get())) {
				event.setNewSpeed(15.0F);
			}

			if (state.is(AtmosphericBlocks.CURRANT_STALK.get())) {
				event.setNewSpeed(10.0F);
			}

			if (state.is(AtmosphericBlocks.CURRANT_STALK_BUNDLE.get()) || state.getBlock() instanceof YuccaBundleBlock) {
				event.setNewSpeed(5.0F);
			}
		}
	}

	@SubscribeEvent
	public static void livingHurt(LivingHurtEvent event) {
		LivingEntity entity = event.getEntity();

		boolean undead = entity.isInvertedHealAndHarm();
		boolean hasRelief = entity.hasEffect(AtmosphericMobEffects.RELIEF.get());
		boolean hasWorsening = entity.hasEffect(AtmosphericMobEffects.WORSENING.get());

		if ((!undead && hasRelief) || (undead && hasWorsening)) {
			int amplifier = entity.getEffect(!undead ? AtmosphericMobEffects.RELIEF.get() : AtmosphericMobEffects.WORSENING.get()).getAmplifier();
			entity.getPersistentData().putInt("PotionHealAmplifier", amplifier);
			entity.getPersistentData().putFloat("IncomingDamage", event.getAmount());
			entity.getPersistentData().putBoolean("Heal", true);
		}

		if ((!undead && hasWorsening) || (undead && hasRelief)) {
			int amplifier = entity.getEffect(!undead ? AtmosphericMobEffects.WORSENING.get() : AtmosphericMobEffects.RELIEF.get()).getAmplifier();
			if (event.getAmount() >= (amplifier + 1)) {
				event.setAmount(event.getAmount() + (amplifier + 1));
			}
		}
	}

	@SubscribeEvent
	public static void livingTick(LivingTickEvent event) {
		LivingEntity entity = event.getEntity();
		float damage = entity.getPersistentData().getFloat("IncomingDamage");
		int amplifierHeal = entity.getPersistentData().getInt("PotionHealAmplifier");
		if (entity.getPersistentData().getBoolean("Heal")) {
			if (damage >= (amplifierHeal + 1)) {
				entity.heal((amplifierHeal + 1));
				entity.getPersistentData().putBoolean("Heal", false);
			}
		}
	}
}