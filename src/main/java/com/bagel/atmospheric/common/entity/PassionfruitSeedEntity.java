package com.bagel.atmospheric.common.entity;

import com.bagel.atmospheric.core.other.AtmosphericCriteriaTriggers;
import com.bagel.atmospheric.core.other.AtmosphericDamageSources;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ItemParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class PassionfruitSeedEntity extends ProjectileItemEntity {
	private final int amplifier;
	
   public PassionfruitSeedEntity(EntityType<? extends PassionfruitSeedEntity> type, World world, int amplifier) {
      super(type, world);
      this.amplifier = amplifier;      
   }

   public PassionfruitSeedEntity(World worldIn, LivingEntity throwerIn, int amplifier) {
      super(EntityType.SNOWBALL, throwerIn, worldIn);
      this.amplifier = amplifier;
   }

   public PassionfruitSeedEntity(World worldIn, double x, double y, double z, int amplifier) {
      super(EntityType.SNOWBALL, x, y, z, worldIn);
      this.amplifier = amplifier;
   }

   protected Item getDefaultItem() {
      return Items.SNOWBALL;
   }

   @OnlyIn(Dist.CLIENT)
   private IParticleData makeParticle() {
      ItemStack itemstack = this.func_213882_k();
      return (IParticleData)(itemstack.isEmpty() ? ParticleTypes.ITEM_SNOWBALL : new ItemParticleData(ParticleTypes.ITEM, itemstack));
   }

   @OnlyIn(Dist.CLIENT)
   public void handleStatusUpdate(byte id) {
      if (id == 3) {
         IParticleData iparticledata = this.makeParticle();

         for(int i = 0; i < 8; ++i) {
            this.world.addParticle(iparticledata, this.getPosX(), this.getPosY(), this.getPosZ(), 0.0D, 0.0D, 0.0D);
         }
      }

   }

   protected void onImpact(RayTraceResult result) {
      if (result.getType() == RayTraceResult.Type.ENTITY) {
         Entity entity = ((EntityRayTraceResult)result).getEntity();
         entity.attackEntityFrom(AtmosphericDamageSources.causePassionfruitSeedDamage(this, this.getThrower()), 0.5F + amplifier);
         if (this.getThrower() instanceof ServerPlayerEntity) {
 			ServerPlayerEntity serverplayerentity = (ServerPlayerEntity) this.getThrower();
 			if(!entity.getEntityWorld().isRemote()) {
 				AtmosphericCriteriaTriggers.SPIT_PASSIONFRUIT.trigger(serverplayerentity); 
 			}
 		}
      }

      if (!this.world.isRemote) {
         this.world.setEntityState(this, (byte)3);
         this.remove();
      }

   }
}
