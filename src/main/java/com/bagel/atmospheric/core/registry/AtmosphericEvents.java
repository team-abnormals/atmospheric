package com.bagel.atmospheric.core.registry;

import java.util.Random;

import com.bagel.atmospheric.common.entity.GoldenPassionfruitSeedEntity;
import com.bagel.atmospheric.common.entity.PassionfruitSeedEntity;
import com.bagel.atmospheric.core.Atmospheric;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Atmospheric.MODID)
public class AtmosphericEvents {
	@SubscribeEvent
	public static void onLivingHurt(LivingHurtEvent event) {
		LivingEntity entity = event.getEntityLiving();
		
		if (entity.isPotionActive(AtmosphericEffects.RELIEF.get())) {
			if (entity.isEntityUndead() == false) {
				int amplifier = entity.getActivePotionEffect(AtmosphericEffects.RELIEF.get()).getAmplifier();
				entity.getPersistentData().putInt("PotionHealAmplifier", amplifier);
				entity.getPersistentData().putFloat("IncomingDamage", event.getAmount());
				entity.getPersistentData().putBoolean("Heal", true);
			} else {
				int amplifier = entity.getActivePotionEffect(AtmosphericEffects.RELIEF.get()).getAmplifier();
				if (event.getAmount() >= (amplifier + 1)) {
					event.setAmount(event.getAmount() + (amplifier + 1));
				}
			}
			
		}
		
		if (entity.isPotionActive(AtmosphericEffects.WORSENING.get())) {
			if (entity.isEntityUndead() == false) {
				int amplifier = entity.getActivePotionEffect(AtmosphericEffects.WORSENING.get()).getAmplifier();
				if (event.getAmount() >= (amplifier + 1)) {
					event.setAmount(event.getAmount() + (amplifier + 1));
				}
			} else {
				int amplifier = entity.getActivePotionEffect(AtmosphericEffects.WORSENING.get()).getAmplifier();
				entity.getPersistentData().putInt("PotionHealAmplifier", amplifier);
				entity.getPersistentData().putFloat("IncomingDamage", event.getAmount());
				entity.getPersistentData().putBoolean("Heal", true);
			}
		}
	}
	
	@SubscribeEvent
	public static void spittingTick(LivingUpdateEvent event) {
		if (event.getEntityLiving().isPotionActive(AtmosphericFoods.GOLDEN_SPITTING)) {
			World worldIn = event.getEntityLiving().getEntityWorld();
			LivingEntity playerIn = event.getEntityLiving();
			Random random = new Random();
			if (!worldIn.isRemote && playerIn.getHealth() > 0) {
				if (playerIn.world.getGameTime() % 3 == 0) {
					worldIn.playSound((PlayerEntity)null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_LLAMA_SPIT, SoundCategory.NEUTRAL, 0.5F, 0.4F / 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F);
					GoldenPassionfruitSeedEntity passionseed = new GoldenPassionfruitSeedEntity(worldIn, playerIn, playerIn.getActivePotionEffect(AtmosphericFoods.GOLDEN_SPITTING).getAmplifier());
					passionseed.setItem(new ItemStack(AtmosphericItems.SHIMMERING_PASSIONFRUIT_SEED.get()));
					passionseed.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 3F, 1.0F);
					worldIn.addEntity(passionseed);    
				}	
			}
		} else if (event.getEntityLiving().isPotionActive(AtmosphericFoods.SPITTING)) {
			World worldIn = event.getEntityLiving().getEntityWorld();
			LivingEntity playerIn = event.getEntityLiving();
			Random random = new Random();
			if (!worldIn.isRemote && playerIn.getHealth() > 0) {
				if (playerIn.world.getGameTime() % 5 == 0) {
					worldIn.playSound((PlayerEntity)null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_LLAMA_SPIT, SoundCategory.NEUTRAL, 0.5F, 0.4F / 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F);
					PassionfruitSeedEntity passionseed = new PassionfruitSeedEntity(worldIn, playerIn, playerIn.getActivePotionEffect(AtmosphericFoods.SPITTING).getAmplifier());
					passionseed.setItem(new ItemStack(AtmosphericItems.PASSIONFRUIT_SEED.get()));
					passionseed.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1F, 1.0F);
					worldIn.addEntity(passionseed);    
				}	
			}
		}
	}
	
	@SubscribeEvent
	public static void onEntityLiving(LivingUpdateEvent event) {
		float damage = event.getEntity().getPersistentData().getFloat("IncomingDamage");
		int amplifier = event.getEntity().getPersistentData().getInt("PotionHealAmplifier");
		boolean heal = event.getEntity().getPersistentData().getBoolean("Heal");
		if (heal == true) {
			if (damage >= (amplifier + 1)) {
				event.getEntityLiving().heal((amplifier + 1));
				event.getEntityLiving().getPersistentData().putBoolean("Heal", false);
			}
		}
	}
	/*
	@SuppressWarnings("deprecation")
	@SubscribeEvent
	public static void onPistonPush(PistonEvent.Post event) {
		World worldIn = event.getWorld().getWorld();
		
		BlockPos pos = event.getPos();
		Direction direction = event.getDirection();
		
		BlockPos nextPos = pos.offset(Direction.DOWN);
	    Block nextBlock = worldIn.getBlockState(nextPos).getBlock();
		int counter = 9;
		
			BlockState vine = RosewoodBlocks.PASSION_VINE.get().getDefaultState().with(PassionVineBlock.FACING, direction.getOpposite());
			worldIn.setBlockState(pos, vine);
			counter = 8;
			while (counter > 0) {
				if (nextBlock.isAir(worldIn.getBlockState(nextPos))) {
					worldIn.setBlockState(nextPos, vine);
					counter = counter - 1;
					nextPos = nextPos.offset(Direction.DOWN);
					nextBlock = worldIn.getBlockState(nextPos).getBlock();	
				} else {
					break;	
				}	
			}
			Block.spawnAsEntity(worldIn, nextPos.offset(Direction.UP), new ItemStack(RosewoodBlocks.PASSION_VINE.get(), counter));	
		
	}*/
}