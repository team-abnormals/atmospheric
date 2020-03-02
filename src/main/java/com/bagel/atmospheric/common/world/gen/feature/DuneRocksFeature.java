package com.bagel.atmospheric.common.world.gen.feature;

import java.util.Random;
import java.util.function.Function;

import com.bagel.atmospheric.core.registry.AtmosphericBlocks;
import com.mojang.datafixers.Dynamic;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.BlockBlobConfig;
import net.minecraft.world.gen.feature.Feature;

public class DuneRocksFeature extends Feature<BlockBlobConfig> {
	   public DuneRocksFeature(Function<Dynamic<?>, ? extends BlockBlobConfig> p_i49915_1_) {
	      super(p_i49915_1_);
	   }

	   public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos pos, BlockBlobConfig config) {
	      while(true) {
	         label50: {
	            if (pos.getY() > 3) {
	               if (worldIn.isAirBlock(pos.down())) {
	                  break label50;
	               }

	               Block block = worldIn.getBlockState(pos.down()).getBlock();
	               if (block != AtmosphericBlocks.ARID_SAND.get() && isStone(block)) {
	                  break label50;
	               }
	            }

	            if (pos.getY() <= 3) {
	               return false;
	            }

	            int i1 = config.startRadius;

	            for(int i = 0; i1 >= 0 && i < 3; ++i) {
	               int j = i1 + rand.nextInt(2);
	               int k = i1 + rand.nextInt(2);
	               int l = i1 + rand.nextInt(2);
	               float f = (float)(j + k + l) * 0.333F + 0.5F;

	               for(BlockPos blockpos : BlockPos.getAllInBoxMutable(pos.add(-j, -k, -l), pos.add(j, k, l))) {
	                  if (blockpos.distanceSq(pos) <= (double)(f * f)) {
	                     worldIn.setBlockState(blockpos, config.state, 4);
	                  }
	               }

	               pos = pos.add(-(i1 + 1) + rand.nextInt(2 + i1 * 2), 0 - rand.nextInt(2), -(i1 + 1) + rand.nextInt(2 + i1 * 2));
	            }

	            return true;
	         }

	         pos = pos.down();
	      }
	   }
	}
