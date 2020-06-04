package com.bagel.atmospheric.common.world.gen.feature;

import java.util.Random;

import com.bagel.atmospheric.common.block.YuccaFlowerDoubleBlock;
import com.bagel.atmospheric.core.registry.AtmosphericBlocks;
import com.google.common.base.Function;
import com.mojang.datafixers.Dynamic;

import net.minecraft.block.BlockState;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.feature.DefaultFlowersFeature;

public class YuccaFlowerFeature extends DefaultFlowersFeature {
   public YuccaFlowerFeature(Function<Dynamic<?>, ? extends BlockClusterFeatureConfig> config) {
      super(config);
   }
   
   @Override
   public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos pos, BlockClusterFeatureConfig config) {
	   BlockState blockstate = AtmosphericBlocks.YUCCA_FLOWER.get().getDefaultState();
	   BlockState tallBlockstate = AtmosphericBlocks.TALL_YUCCA_FLOWER.get().getDefaultState();
	   int i = 0;
	   
	   for(int j = 0; j < this.func_225560_a_(config); ++j) {
		   BlockPos blockpos = this.getNearbyPos(rand, pos, config);
		   if (worldIn.isAirBlock(blockpos) && blockpos.getY() < worldIn.getMaxHeight() - 1 && blockstate.isValidPosition(worldIn, blockpos) && this.func_225559_a_(worldIn, blockpos, config)) {
			   if (rand.nextBoolean() && worldIn.isAirBlock(blockpos.up())) {
				   worldIn.setBlockState(blockpos, tallBlockstate, 2);
				   worldIn.setBlockState(blockpos.up(), tallBlockstate.with(YuccaFlowerDoubleBlock.HALF, DoubleBlockHalf.UPPER), 2);
			   } else {
				   worldIn.setBlockState(blockpos, blockstate, 2);
			   }
			   ++i;
		   }
	   }
	   return i > 0;
   }
}
