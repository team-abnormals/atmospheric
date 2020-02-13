package com.bagel.atmospheric.common.world.gen.feature;

import java.util.Random;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;

import net.minecraft.block.Blocks;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.ProbabilityConfig;

public class PodzolFeature extends Feature<ProbabilityConfig> {

	   public PodzolFeature(Function<Dynamic<?>, ? extends ProbabilityConfig> p_i49919_1_) {
	      super(p_i49919_1_);
	   }

	   public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos pos, ProbabilityConfig config) {
	      int i = 0;
	      BlockPos.MutableBlockPos blockpos$mutableblockpos1 = new BlockPos.MutableBlockPos(pos);
	            if (rand.nextFloat() < config.probability) {
	               int k = rand.nextInt(4) + 1;
	               for(int l = pos.getX() - k; l <= pos.getX() + k; ++l) {
	                  for(int i1 = pos.getZ() - k; i1 <= pos.getZ() + k; ++i1) {
	                     int j1 = l - pos.getX();
	                     int k1 = i1 - pos.getZ();
	                     if (j1 * j1 + k1 * k1 <= k * k) {
	                        blockpos$mutableblockpos1.setPos(l, worldIn.getHeight(Heightmap.Type.WORLD_SURFACE, l, i1) - 1, i1);
	                        if (worldIn.getBlockState(blockpos$mutableblockpos1).getBlock().isIn(BlockTags.DIRT_LIKE)) {
	                           worldIn.setBlockState(blockpos$mutableblockpos1, Blocks.PODZOL.getDefaultState(), 2);
	                        }
	                     }
	                  }
	               
	            

	            
	         }

	         ++i;
	      }

	      return i > 0;
	   }
	}
