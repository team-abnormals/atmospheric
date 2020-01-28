package com.bagel.rosewood.common.world.gen.feature;

import java.util.Random;
import java.util.function.Function;

import com.bagel.rosewood.common.blocks.PassionVineBlock;
import com.bagel.rosewood.core.registry.RosewoodBlocks;
import com.mojang.datafixers.Dynamic;

import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class PassionVineFeature extends Feature<NoFeatureConfig> {
	   private static final Direction[] DIRECTIONS = Direction.values();

	   public PassionVineFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> p_i51418_1_) {
	      super(p_i51418_1_);
	   }

	   public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos pos, NoFeatureConfig config) {
	      BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos(pos);

	      for(int i = pos.getY(); i < worldIn.getWorld().getDimension().getHeight(); ++i) {
	         blockpos$mutableblockpos.setPos(pos);
	         blockpos$mutableblockpos.move(rand.nextInt(4) - rand.nextInt(4), 0, rand.nextInt(4) - rand.nextInt(4));
	         blockpos$mutableblockpos.setY(i);
	         if (worldIn.isAirBlock(blockpos$mutableblockpos)) {
	            for(Direction direction : DIRECTIONS) {
	               if (direction != Direction.DOWN && PassionVineBlock.canAttachTo(worldIn, blockpos$mutableblockpos, direction)) {
	                  worldIn.setBlockState(blockpos$mutableblockpos, RosewoodBlocks.PASSION_VINE.get().getDefaultState().with(PassionVineBlock.FACING, Direction.random(rand)), 2);
	                  break;
	               }
	            }
	         }
	      }

	      return true;
	   }
	}
