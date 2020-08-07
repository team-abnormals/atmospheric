package com.minecraftabnormals.atmospheric.common.world.gen.feature;

import java.util.Random;

import com.minecraftabnormals.atmospheric.common.block.BarrelCactusBlock;
import com.minecraftabnormals.atmospheric.core.registry.AtmosphericBlocks;
import com.mojang.serialization.Codec;

import net.minecraft.block.BlockState;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.structure.StructureManager;

public class BarrelCactusFeature extends Feature<BlockClusterFeatureConfig> {
   public BarrelCactusFeature(Codec<BlockClusterFeatureConfig> config) {
      super(config);
   }

   @Override
   public boolean func_230362_a_(ISeedReader worldIn, StructureManager manager, ChunkGenerator generator, Random rand, BlockPos pos, BlockClusterFeatureConfig config) {
	   BlockState blockstate = config.stateProvider.getBlockState(rand, pos);
	   BlockPos blockpos;
	   if (config.field_227298_k_) {
		   blockpos = worldIn.getHeight(Heightmap.Type.WORLD_SURFACE_WG, pos);
	   } else {
		   blockpos = pos;
	   }
	   
	   int i = 0;
	   BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();
	   
	   for(int j = 0; j < config.tryCount; ++j) {
		   blockpos$mutable.setPos(blockpos).move(rand.nextInt(config.xSpread + 1) - rand.nextInt(config.xSpread + 1), rand.nextInt(config.ySpread + 1) - rand.nextInt(config.ySpread + 1), rand.nextInt(config.zSpread + 1) - rand.nextInt(config.zSpread + 1));
		   BlockPos blockpos1 = blockpos$mutable.down();
		   blockstate = blockstate.with(BarrelCactusBlock.AGE, rand.nextInt(4));
		   BlockState blockstate1 = worldIn.getBlockState(blockpos1);
		   if ((worldIn.isAirBlock(blockpos$mutable) || config.isReplaceable && worldIn.getBlockState(blockpos$mutable).getMaterial().isReplaceable()) && blockstate.isValidPosition(worldIn, blockpos$mutable) && (config.whitelist.isEmpty() || config.whitelist.contains(blockstate1.getBlock())) && blockstate1.getBlock() != AtmosphericBlocks.ARID_SAND.get() && !config.blacklist.contains(blockstate1) && (!config.requiresWater || worldIn.getFluidState(blockpos1.west()).isTagged(FluidTags.WATER) || worldIn.getFluidState(blockpos1.east()).isTagged(FluidTags.WATER) || worldIn.getFluidState(blockpos1.north()).isTagged(FluidTags.WATER) || worldIn.getFluidState(blockpos1.south()).isTagged(FluidTags.WATER))) {
			   config.blockPlacer.func_225567_a_(worldIn, blockpos$mutable, blockstate, rand);
			   ++i;
		   }
	   }  
	   return i > 0;
   }
}	
