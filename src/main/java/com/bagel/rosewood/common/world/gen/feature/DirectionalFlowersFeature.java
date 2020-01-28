package com.bagel.rosewood.common.world.gen.feature;

import java.util.Random;
import java.util.function.Function;

import com.bagel.rosewood.common.blocks.MonkeyBrushBlock;
import com.mojang.datafixers.Dynamic;

import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public abstract class DirectionalFlowersFeature extends Feature<NoFeatureConfig> {
	   public DirectionalFlowersFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> p_i49876_1_) {
	      super(p_i49876_1_, false);
	   }

	   public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos pos, NoFeatureConfig config) {
	      BlockState blockstate = this.getRandomFlower(rand, pos);
	      int i = 0;

	      for(int j = 0; j < 64; ++j) {
	         BlockPos blockpos = pos.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));
	         if (worldIn.isAirBlock(blockpos) && blockpos.getY() < 255 && blockstate.isValidPosition(worldIn, blockpos)) {
	        	Direction randomD = Direction.random(rand);
	        	while (randomD == Direction.DOWN) {
		        	randomD = Direction.random(rand);
	        	}
	        	BlockState blockstate2 = this.getRandomFlower(rand, pos).with(MonkeyBrushBlock.FACING, randomD);
	            worldIn.setBlockState(blockpos, blockstate2, 2);
	            ++i;
	         }
	      }

	      return i > 0;
	   }

	   public abstract BlockState getRandomFlower(Random random, BlockPos pos);
	}

//RosewoodBlocks.MONKEY_BRUSH.get().getDefaultState().with(MonkeyBrushBlock.FACING, Direction.random(random))
