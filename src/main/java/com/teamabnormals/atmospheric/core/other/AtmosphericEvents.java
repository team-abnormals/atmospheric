package com.teamabnormals.atmospheric.core.other;

import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.atmospheric.core.other.tags.AtmosphericEntityTypeTags;
import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import com.teamabnormals.atmospheric.core.registry.AtmosphericCriteriaTriggers;
import com.teamabnormals.atmospheric.core.registry.AtmosphericItems;
import com.teamabnormals.atmospheric.core.registry.AtmosphericMobEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.event.entity.ProjectileImpactEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.level.BlockEvent.BlockToolModificationEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;

import java.util.Calendar;

@EventBusSubscriber(modid = Atmospheric.MOD_ID)
public class AtmosphericEvents {

	@SubscribeEvent
	public static void projectileImpact(ProjectileImpactEvent event) {
		if (event.getProjectile() instanceof Snowball snowball) {
			if (event.getRayTraceResult() instanceof BlockHitResult result) {
				Level level = snowball.level();
				BlockPos pos = result.getBlockPos();
				BlockState state = level.getBlockState(pos);
				Block newBlock = state.is(Blocks.POTTED_BAMBOO) ? AtmosphericBlocks.POTTED_SNOWY_BAMBOO.get() :
						state.is(Blocks.POTTED_CACTUS) ? AtmosphericBlocks.POTTED_SNOWY_CACTUS.get() :
								state.is(AtmosphericBlocks.POTTED_BARREL_CACTUS.get()) ? AtmosphericBlocks.POTTED_SNOWY_BARREL_CACTUS.get() : null;

				if (newBlock != null) {
					level.setBlockAndUpdate(pos, newBlock.defaultBlockState());
				}
			}
		}
	}


	@SubscribeEvent
	public static void onLivingAttack(LivingIncomingDamageEvent event) {
		if (event.getEntity().getType().is(AtmosphericEntityTypeTags.CACTUS_IMMUNE) && event.getSource().is(DamageTypes.CACTUS)) {
			event.setCanceled(true);
		}
	}

	@SubscribeEvent
	public static void livingHurt(LivingDamageEvent.Pre event) {
		LivingEntity entity = event.getEntity();

		boolean undead = entity.isInvertedHealAndHarm();
		boolean hasRelief = entity.hasEffect(AtmosphericMobEffects.RELIEF);
		boolean hasWorsening = entity.hasEffect(AtmosphericMobEffects.WORSENING);

		if ((!undead && hasWorsening) || (undead && hasRelief)) {
			int amplifier = entity.getEffect(!undead ? AtmosphericMobEffects.WORSENING : AtmosphericMobEffects.RELIEF).getAmplifier();
			if (event.getOriginalDamage() >= (amplifier + 1)) {
				event.setNewDamage(event.getOriginalDamage() + (amplifier + 1));
			}
		}

		if ((!undead && hasRelief) || (undead && hasWorsening)) {
			int amplifier = entity.getEffect(!undead ? AtmosphericMobEffects.RELIEF : AtmosphericMobEffects.WORSENING).getAmplifier();
			entity.getPersistentData().putInt("PotionHealAmplifier", amplifier);
			entity.getPersistentData().putFloat("IncomingDamage", event.getNewDamage());
			entity.getPersistentData().putBoolean("Heal", true);
		}
	}

	@SubscribeEvent
	public static void livingTick(EntityTickEvent.Pre event) {
		if (event.getEntity() instanceof LivingEntity entity) {
			float damage = entity.getPersistentData().getFloat("IncomingDamage");
			int amplifierHeal = entity.getPersistentData().getInt("PotionHealAmplifier");
			if (entity.getPersistentData().getBoolean("Heal")) {
				if (damage >= (amplifierHeal + 1)) {
					entity.heal((amplifierHeal + 1));
					entity.getPersistentData().putBoolean("Heal", false);
				}
			}

			if (event.getEntity() instanceof ServerPlayer player && !player.getCommandSenderWorld().isClientSide()) {
				if (player.hasEffect(AtmosphericMobEffects.PERSISTENCE) && player.getFoodData().getFoodLevel() <= 6.0F) {
					AtmosphericCriteriaTriggers.PERSISTENCE_WHILE_STARVING.get().trigger(player);
				}
			}
		}
	}

	@SubscribeEvent
	public static void onInteractWithBlock(PlayerInteractEvent.RightClickBlock event) {
		if (event.getEntity() instanceof ServerPlayer player) {
			BlockPos pos = event.getPos();
			Level level = event.getLevel();
			if (level.getBlockEntity(pos) instanceof RandomizableContainerBlockEntity container && container.getLootTable() == AtmosphericLootTables.ARID_GARDEN) {
				if (player.getItemBySlot(EquipmentSlot.HEAD).is(AtmosphericItems.BARREL_CACTUS.get()) && !player.getCommandSenderWorld().isClientSide()) {
					AtmosphericCriteriaTriggers.LOOT_ARID_GARDEN.get().trigger(player);
				}
			}
		}
	}

	@SubscribeEvent
	public static void onBlockToolModify(BlockToolModificationEvent event) {
		if (event.getItemAbility() == ItemAbilities.HOE_TILL && event.getFinalState().is(AtmosphericBlocks.CRUSTOSE_PATH)) {
			event.setFinalState(Blocks.FARMLAND.defaultBlockState());
		}
	}

	public static boolean isAprilFools() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.MONTH) + 1 == 4 && calendar.get(Calendar.DATE) == 1;
	}
}