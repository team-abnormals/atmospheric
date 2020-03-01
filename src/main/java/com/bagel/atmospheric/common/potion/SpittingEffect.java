package com.bagel.atmospheric.common.potion;

import java.util.Random;

import com.bagel.atmospheric.core.registry.AtmosphericItems;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.SnowballEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class SpittingEffect extends Effect {

	public SpittingEffect() {
		super(EffectType.BENEFICIAL, 15454786);
	}
	
	public void performEffect(LivingEntity entityLivingBaseIn, int amplifier) {
		World worldIn = entityLivingBaseIn.getEntityWorld();
		LivingEntity playerIn = entityLivingBaseIn;
		Random random = new Random();
		if (!worldIn.isRemote) {
			if (playerIn.world.getGameTime() % 6 == 0) {
				//playSound((PlayerEntity)null, this.posX, this.posY, this.posZ, SoundEvents.ENTITY_LLAMA_SPIT, this.getSoundCategory(), 1.0F, 1.0F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F);
				worldIn.playSound((PlayerEntity)null, playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ(), SoundEvents.ENTITY_LLAMA_SPIT, SoundCategory.NEUTRAL, 0.5F, 0.4F / 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F);
				SnowballEntity snowballentity = new SnowballEntity(worldIn, playerIn);
				snowballentity.setItem(new ItemStack(AtmosphericItems.PASSIONFRUIT_SEED.get()));
				snowballentity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 2F, 1.0F);
				worldIn.addEntity(snowballentity);    
			}	
		}
	}

}
