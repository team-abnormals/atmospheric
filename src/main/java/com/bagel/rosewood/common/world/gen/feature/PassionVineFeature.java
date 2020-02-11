package com.bagel.rosewood.common.world.gen.feature;

import java.util.Random;
import java.util.function.Function;

import com.bagel.rosewood.common.blocks.PassionVineBlock;
import com.bagel.rosewood.core.registry.RosewoodBlocks;
import com.mojang.datafixers.Dynamic;

import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class PassionVineFeature extends Feature<NoFeatureConfig> {
	public PassionVineFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> p_i49876_1_) {
		super(p_i49876_1_, false);
	}

	public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand,
			BlockPos pos, NoFeatureConfig config) {

			
			int i = 0;

			for (int j = 0; j < 128; ++j) {
				
				Direction direction = Direction.Plane.HORIZONTAL.random(rand);
				BlockState blockstate = RosewoodBlocks.PASSION_VINE.get().getDefaultState().with(PassionVineBlock.FACING, direction).with(PassionVineBlock.AGE, rand.nextInt(2));
				BlockPos blockpos = pos.add(rand.nextInt(6) - rand.nextInt(6), rand.nextInt(2) - rand.nextInt(2), rand.nextInt(6) - rand.nextInt(6));
				
				int lengthA = 3 + rand.nextInt(2) + rand.nextInt(2) + rand.nextInt(2) + rand.nextInt(2) - rand.nextInt(3);
				
				if (worldIn.isAirBlock(blockpos) && blockpos.getY() < 255 && blockstate.isValidPosition(worldIn, blockpos)) {
					worldIn.setBlockState(blockpos, blockstate, 6);
					for (int length = 0; length < lengthA; ++length) {
						blockpos = blockpos.down();
						if (worldIn.isAirBlock(blockpos) && blockpos.getY() < 255 && blockstate.isValidPosition(worldIn, blockpos)) {
							worldIn.setBlockState(blockpos, blockstate.with(PassionVineBlock.AGE, rand.nextInt(2)), 6);
						} else {
							break;
						}
					}
					++i;
				}
			}
			return i > 0;
	
	}
}
