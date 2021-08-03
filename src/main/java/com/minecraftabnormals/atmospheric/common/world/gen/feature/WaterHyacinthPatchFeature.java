package com.minecraftabnormals.atmospheric.common.world.gen.feature;

import com.minecraftabnormals.atmospheric.common.block.WaterHyacinthBlock;
import com.minecraftabnormals.atmospheric.core.registry.AtmosphericBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

public class WaterHyacinthPatchFeature extends Feature<NoFeatureConfig> {
	public WaterHyacinthPatchFeature(Codec<NoFeatureConfig> codec) {
		super(codec);
	}

	@Override
	public boolean place(ISeedReader world, ChunkGenerator generator, Random random, BlockPos pos, NoFeatureConfig config) {
		BlockState topState = AtmosphericBlocks.WATER_HYACINTH.get().defaultBlockState();
		BlockState bottomState = AtmosphericBlocks.WATER_HYACINTH.get().defaultBlockState().setValue(WaterHyacinthBlock.WATERLOGGED, true).setValue(WaterHyacinthBlock.HALF, DoubleBlockHalf.LOWER);
		BlockPos blockpos = pos;
		int i = 0;

		int xSpread = 7;
		int ySpread = 3;
		int zSpread = 7;

		BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();

		for (int j = 0; j < 512; ++j) {
			blockpos$mutable.setWithOffset(blockpos, random.nextInt(xSpread + 1) - random.nextInt(xSpread + 1), random.nextInt(ySpread + 1) - random.nextInt(ySpread + 1), random.nextInt(zSpread + 1) - random.nextInt(zSpread + 1));
			BlockPos blockpos1 = blockpos$mutable.below();
			if (world.isEmptyBlock(blockpos$mutable) && world.getBlockState(blockpos1).getBlock() == Blocks.WATER) {
				world.setBlock(blockpos$mutable, topState, 2);
				world.setBlock(blockpos1, bottomState, 2);
				++i;
			}
		}

		return i > 0;
	}
}