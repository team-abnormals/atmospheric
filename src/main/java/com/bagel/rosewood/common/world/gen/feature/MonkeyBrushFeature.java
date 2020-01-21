package com.bagel.rosewood.common.world.gen.feature;

import java.util.Random;
import java.util.function.Function;

import com.bagel.rosewood.core.registry.RosewoodBlocks;
import com.mojang.datafixers.Dynamic;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.FlowersFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class MonkeyBrushFeature extends FlowersFeature {
	   public MonkeyBrushFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> p_i49889_1_) {
	      super(p_i49889_1_);
	   }

	   public BlockState getRandomFlower(Random random, BlockPos pos) {
	      return random.nextFloat() > 0.6666667F ? RosewoodBlocks.MONKEY_BRUSH.get().getDefaultState() : Blocks.LILY_OF_THE_VALLEY.getDefaultState();
	   }
	}
