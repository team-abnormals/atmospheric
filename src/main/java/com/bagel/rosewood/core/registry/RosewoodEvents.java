package com.bagel.rosewood.core.registry;

import com.bagel.rosewood.core.Rosewood;

import net.minecraft.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Rosewood.MODID)
public class RosewoodEvents {
	@SubscribeEvent
	public static void onLivingHurt(LivingHurtEvent event) {
		LivingEntity entity = event.getEntityLiving();
		
		if (entity.isPotionActive(RosewoodEffects.RELIEF.get())) {
			if (entity.isEntityUndead() == false) {
				int amplifier = entity.getActivePotionEffect(RosewoodEffects.RELIEF.get()).getAmplifier();
				entity.getPersistentData().putInt("PotionHealAmplifier", amplifier);
				entity.getPersistentData().putFloat("IncomingDamage", event.getAmount());
				entity.getPersistentData().putBoolean("Heal", true);
			} else {
				int amplifier = entity.getActivePotionEffect(RosewoodEffects.RELIEF.get()).getAmplifier();
				if (event.getAmount() >= (amplifier + 1)) {
					event.setAmount(event.getAmount() + (amplifier + 1));
				}
			}
			
		}
		
		if (entity.isPotionActive(RosewoodEffects.WORSENING.get())) {
			if (entity.isEntityUndead() == false) {
				int amplifier = entity.getActivePotionEffect(RosewoodEffects.WORSENING.get()).getAmplifier();
				if (event.getAmount() >= (amplifier + 1)) {
					event.setAmount(event.getAmount() + (amplifier + 1));
				}
			} else {
				int amplifier = entity.getActivePotionEffect(RosewoodEffects.WORSENING.get()).getAmplifier();
				entity.getPersistentData().putInt("PotionHealAmplifier", amplifier);
				entity.getPersistentData().putFloat("IncomingDamage", event.getAmount());
				entity.getPersistentData().putBoolean("Heal", true);
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