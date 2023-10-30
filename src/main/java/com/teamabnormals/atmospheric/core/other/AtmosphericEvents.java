package com.teamabnormals.atmospheric.core.other;

import com.teamabnormals.atmospheric.common.block.StemmedOrangeBlock;
import com.teamabnormals.atmospheric.common.block.YuccaBundleBlock;
import com.teamabnormals.atmospheric.common.entity.OrangeVaporCloud;
import com.teamabnormals.atmospheric.common.item.OrangeBlockItem;
import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import com.teamabnormals.atmospheric.core.registry.AtmosphericItems;
import com.teamabnormals.atmospheric.core.registry.AtmosphericMobEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingTickEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.level.PistonEvent;
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
	public static void breakSpead(BreakSpeed event) {
		ItemStack stack = event.getEntity().getMainHandItem();
		BlockState state = event.getState();

		if (stack.is(Tags.Items.SHEARS)) {
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

		if (state.is(AtmosphericBlocks.GRIMWEB.get()) && (stack.is(Tags.Items.SHEARS) || stack.getItem() instanceof SwordItem)) {
			event.setNewSpeed(15.0F);
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
	public static void onPistonPush(PistonEvent.Pre event) {
		LevelAccessor level = event.getLevel();
		BlockPos pos = event.getFaceOffsetPos();
		if (level instanceof ServerLevel serverLevel && level.getBlockState(pos).getBlock() instanceof StemmedOrangeBlock) {
			OrangeBlockItem.createVaporCloud(serverLevel, new Vec3(pos.getX() + 0.5F, pos.getY(), pos.getZ() + 0.5F));
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

		if (event.getEntity() instanceof ServerPlayer player && !player.getCommandSenderWorld().isClientSide()) {
			if (player.hasEffect(AtmosphericMobEffects.PERSISTENCE.get()) && player.getFoodData().getFoodLevel() <= 6.0F) {
				AtmosphericCriteriaTriggers.PERSISTENCE_WHILE_STARVING.trigger(player);
			}
		}
	}

	@SubscribeEvent
	public static void onInteractWithBlock(PlayerInteractEvent.RightClickBlock event) {
		if (event.getEntity() instanceof ServerPlayer player) {
			BlockPos pos = event.getPos();
			Level level = event.getLevel();
			if (level.getBlockEntity(pos) instanceof RandomizableContainerBlockEntity container && container.serializeNBT().getString("LootTable").equals(Atmospheric.location("chests/arid_garden").toString())) {
				if (player.getItemBySlot(EquipmentSlot.HEAD).is(AtmosphericItems.BARREL_CACTUS.get()) && !player.getCommandSenderWorld().isClientSide()) {
					AtmosphericCriteriaTriggers.LOOT_ARID_GARDEN.trigger(player);
				}
			}
		}
	}
}