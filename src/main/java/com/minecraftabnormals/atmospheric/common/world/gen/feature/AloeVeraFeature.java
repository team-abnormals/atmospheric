package com.minecraftabnormals.atmospheric.common.world.gen.feature;

import java.util.Random;

import com.minecraftabnormals.atmospheric.common.block.AloeVeraBlock;
import com.minecraftabnormals.atmospheric.common.block.AloeVeraTallBlock;
import com.minecraftabnormals.atmospheric.core.registry.AtmosphericBlocks;
import com.mojang.serialization.Codec;

import net.minecraft.block.BlockState;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.feature.DefaultFlowersFeature;
import net.minecraft.world.gen.feature.structure.StructureManager;

public class AloeVeraFeature extends DefaultFlowersFeature {
   public AloeVeraFeature(Codec<BlockClusterFeatureConfig> config) {
      super(config);
   }
   
   @Override
   public boolean func_230362_a_(ISeedReader worldIn, StructureManager manager, ChunkGenerator generator, Random rand, BlockPos pos, BlockClusterFeatureConfig config) {
	   BlockState blockstate = AtmosphericBlocks.ALOE_VERA.get().getDefaultState();
	   BlockState tallBlockstate = AtmosphericBlocks.TALL_ALOE_VERA.get().getDefaultState();
	   int i = 0;
	   
	   for(int j = 0; j < this.getFlowerCount(config); ++j) {
		   BlockPos blockpos = this.getNearbyPos(rand, pos, config);
		   if (worldIn.isAirBlock(blockpos) && blockpos.getY() < worldIn.getHeight() - 1 && blockstate.isValidPosition(worldIn, blockpos) && this.isValidPosition(worldIn, blockpos, config)) {
			   if (rand.nextBoolean() && worldIn.isAirBlock(blockpos.up())) {
				   worldIn.setBlockState(blockpos, tallBlockstate.with(AloeVeraTallBlock.AGE, 8), 2);
				   worldIn.setBlockState(blockpos.up(), tallBlockstate.with(AloeVeraTallBlock.HALF, DoubleBlockHalf.UPPER).with(AloeVeraTallBlock.AGE, 8), 2);
			   } else {
				   worldIn.setBlockState(blockpos, blockstate.with(AloeVeraBlock.AGE, 5), 2);
			   }
			   ++i;
		   }
	   }
	   return i > 0;
   }
}
