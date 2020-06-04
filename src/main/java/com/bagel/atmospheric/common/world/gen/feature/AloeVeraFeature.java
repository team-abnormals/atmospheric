package com.bagel.atmospheric.common.world.gen.feature;

import java.util.Random;

import com.bagel.atmospheric.common.block.AloeVeraBlock;
import com.bagel.atmospheric.common.block.AloeVeraTallBlock;
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

public class AloeVeraFeature extends DefaultFlowersFeature {
   public AloeVeraFeature(Function<Dynamic<?>, ? extends BlockClusterFeatureConfig> config) {
      super(config);
   }
   
   @Override
   public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos pos, BlockClusterFeatureConfig config) {
	   BlockState blockstate = AtmosphericBlocks.ALOE_VERA.get().getDefaultState();
	   BlockState tallBlockstate = AtmosphericBlocks.TALL_ALOE_VERA.get().getDefaultState();
	   int i = 0;
	   
	   for(int j = 0; j < this.func_225560_a_(config); ++j) {
		   BlockPos blockpos = this.getNearbyPos(rand, pos, config);
		   if (worldIn.isAirBlock(blockpos) && blockpos.getY() < worldIn.getMaxHeight() - 1 && blockstate.isValidPosition(worldIn, blockpos) && this.func_225559_a_(worldIn, blockpos, config)) {
			   if (rand.nextBoolean() && worldIn.isAirBlock(blockpos.up())) {
				   int age = 6 + rand.nextInt(3);
				   worldIn.setBlockState(blockpos, tallBlockstate.with(AloeVeraTallBlock.AGE, age), 2);
				   worldIn.setBlockState(blockpos.up(), tallBlockstate.with(AloeVeraTallBlock.HALF, DoubleBlockHalf.UPPER).with(AloeVeraTallBlock.AGE, age), 2);
			   } else {
				   worldIn.setBlockState(blockpos, blockstate.with(AloeVeraBlock.AGE, rand.nextInt(6)), 2);
			   }
			   ++i;
		   }
	   }
	   return i > 0;
   }
}
