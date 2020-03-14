package com.bagel.atmospheric.common.entity;

import com.bagel.atmospheric.common.block.PassionVineBlock;
import com.bagel.atmospheric.common.block.PassionVineBundleBlock;
import com.bagel.atmospheric.core.registry.AtmosphericBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ItemParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class PassionVineCoilEntity extends ProjectileItemEntity {
	
   public PassionVineCoilEntity(EntityType<? extends PassionVineCoilEntity> p_i50159_1_, World p_i50159_2_) {
      super(p_i50159_1_, p_i50159_2_);
      
   }

   public PassionVineCoilEntity(World worldIn, LivingEntity throwerIn) {
      super(EntityType.SNOWBALL, throwerIn, worldIn);
   }

   public PassionVineCoilEntity(World worldIn, double x, double y, double z) {
      super(EntityType.SNOWBALL, x, y, z, worldIn);
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

   @SuppressWarnings("deprecation")
   protected void onImpact(RayTraceResult result) {
	   if (result.getType() == RayTraceResult.Type.ENTITY) {
		   Entity entity = ((EntityRayTraceResult)result).getEntity();
		   entity.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 0);
	   } else if (result.getType() == RayTraceResult.Type.BLOCK) {
		   BlockPos pos = this.getPosition();
		   World worldIn = this.getEntityWorld();
		   
		   BlockPos nextPos = pos.offset(Direction.DOWN);
		   Block nextBlock = worldIn.getBlockState(nextPos).getBlock();
		   int counter = 8;
		   Direction direction = this.getHorizontalFacing();
		   //Block blockreader = worldIn.getBlockState(pos.offset(direction)).getBlock();
		   if (!worldIn.getBlockState(pos.offset(direction)).getBlock().isAir(worldIn.getBlockState(pos.offset(direction))) 
				   && AtmosphericBlocks.PASSION_VINE.get().getDefaultState().with(PassionVineBlock.FACING, direction.getOpposite()).isValidPosition(worldIn, pos)
				   ) {
			   BlockState vine = AtmosphericBlocks.PASSION_VINE.get().getDefaultState().with(PassionVineBlock.FACING, direction.getOpposite());
			   worldIn.setBlockState(pos, vine);
			   counter = 7;
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
			   PassionVineBundleBlock.spawnAsEntity(worldIn, nextPos.offset(Direction.UP), new ItemStack(AtmosphericBlocks.PASSION_VINE.get(), counter));	
		   } else {
			   int k1 = 0;
			   while(k1 < 3) {
				   if (direction == Direction.NORTH) {
					   direction = Direction.EAST;
				   } else if (direction == Direction.EAST) {
					   direction = Direction.SOUTH;
				   } else if (direction == Direction.SOUTH) {
					   direction = Direction.WEST;
				   } else if (direction == Direction.WEST) {
					   direction = Direction.NORTH;
				   }
				   k1 = k1 + 1;
				   if (!worldIn.getBlockState(pos.offset(direction)).getBlock().isAir(worldIn.getBlockState(pos.offset(direction))) 
						   && AtmosphericBlocks.PASSION_VINE.get().getDefaultState().with(PassionVineBlock.FACING, direction.getOpposite()).isValidPosition(worldIn, pos)
						   ) {
					   BlockState vine = AtmosphericBlocks.PASSION_VINE.get().getDefaultState().with(PassionVineBlock.FACING, direction.getOpposite());
					   worldIn.setBlockState(pos, vine);
					   counter = 7;
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
					   PassionVineBundleBlock.spawnAsEntity(worldIn, nextPos.offset(Direction.UP), new ItemStack(AtmosphericBlocks.PASSION_VINE.get(), counter));	
					   break;
				   } else if (k1 >= 3){
					   worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());
					   PassionVineBundleBlock.spawnAsEntity(worldIn, nextPos.offset(Direction.UP), new ItemStack(AtmosphericBlocks.PASSION_VINE.get(), 8));	
					   break;
				   }
			   }
		   }
	   }

	   if (!this.world.isRemote) {
		   this.world.setEntityState(this, (byte)3);
		   this.remove();
	   }	   
   }
}
