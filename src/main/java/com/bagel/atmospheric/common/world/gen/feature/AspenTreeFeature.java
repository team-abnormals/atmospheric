package com.bagel.atmospheric.common.world.gen.feature;

import java.util.Random;
import java.util.Set;
import java.util.function.Function;

import com.bagel.atmospheric.core.registry.AtmosphericBlocks;
import com.mojang.datafixers.Dynamic;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class AspenTreeFeature extends AbstractTreeFeature<NoFeatureConfig> {

   public AspenTreeFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> p_i51429_1_, boolean p_i51429_2_) {
      super(p_i51429_1_, p_i51429_2_);
      setSapling((net.minecraftforge.common.IPlantable)AtmosphericBlocks.ASPEN_SAPLING.get());
   }

   public boolean place(Set<BlockPos> changedBlocks, IWorldGenerationReader worldIn, Random rand, BlockPos position, MutableBoundingBox boundsIn) {
      int i = rand.nextInt(4) + 6;
      int j = 1 + rand.nextInt(2);
      int k = i - j;
      int l = 2 + rand.nextInt(2);
      boolean flag = true;
      if (position.getY() >= 1 && position.getY() + i + 1 <= worldIn.getMaxHeight()) {
         for(int i1 = position.getY(); i1 <= position.getY() + 1 + i && flag; ++i1) {
            int j1;
            if (i1 - position.getY() < j) {
               j1 = 0;
            } else {
               j1 = l;
            }

            BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

            for(int k1 = position.getX() - j1; k1 <= position.getX() + j1 && flag; ++k1) {
               for(int l1 = position.getZ() - j1; l1 <= position.getZ() + j1 && flag; ++l1) {
                  if (i1 >= 0 && i1 < worldIn.getMaxHeight()) {
                     blockpos$mutableblockpos.setPos(k1, i1, l1);
                     if (!isAirOrLeaves(worldIn, blockpos$mutableblockpos)) {
                        flag = false;
                     }
                  } else {
                     flag = false;
                  }
               }
            }
         }

         if (!flag) {
            return false;
         } else if (isSoil(worldIn, position.down(), getSapling()) && position.getY() < worldIn.getMaxHeight() - i - 1) {
            this.setDirtAt(worldIn, position.down(), position);
            int i3 = rand.nextInt(2);
            int j3 = 1;
            int k3 = 0;

            for(int l3 = 0; l3 <= k; ++l3) {
               int j4 = position.getY() + i - l3;

               for(int i2 = position.getX() - i3; i2 <= position.getX() + i3; ++i2) {
                  int j2 = i2 - position.getX();

                  for(int k2 = position.getZ() - i3; k2 <= position.getZ() + i3; ++k2) {
                     int l2 = k2 - position.getZ();
                     if (Math.abs(j2) != i3 || Math.abs(l2) != i3 || i3 <= 0) {
                        BlockPos blockpos = new BlockPos(i2, j4, k2);
                        if (isAirOrLeaves(worldIn, blockpos) || isTallPlants(worldIn, blockpos)) {
                           this.setLogState(changedBlocks, worldIn, blockpos, AtmosphericBlocks.ASPEN_LEAVES.get().getDefaultState(), boundsIn);
                        }
                     }
                  }
               }

               if (i3 >= j3) {
                  i3 = k3;
                  k3 = 1;
                  ++j3;
                  if (j3 > l) {
                     j3 = l;
                  }
               } else {
                  ++i3;
               }
            }

            int i4 = rand.nextInt(3);

            for(int k4 = 0; k4 < i - i4; ++k4) {
               if (isAirOrLeaves(worldIn, position.up(k4))) {
                  this.setLogState(changedBlocks, worldIn, position.up(k4), AtmosphericBlocks.ASPEN_LOG.get().getDefaultState(), boundsIn);
               }
            }

            return true;
         } else {
            return false;
         }
      } else {
         return false;
      }
   }
}
