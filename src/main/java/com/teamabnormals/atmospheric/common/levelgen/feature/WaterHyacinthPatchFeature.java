package com.teamabnormals.atmospheric.common.levelgen.feature;

import com.mojang.serialization.Codec;
import com.teamabnormals.atmospheric.common.block.WaterHyacinthBlock;
import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.Random;

public class WaterHyacinthPatchFeature extends Feature<NoneFeatureConfiguration> {
	public WaterHyacinthPatchFeature(Codec<NoneFeatureConfiguration> codec) {
		super(codec);
	}

	@Override
	public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
		WorldGenLevel world = context.level();
		RandomSource random = context.random();
		BlockPos pos = context.origin();

		BlockState topState = AtmosphericBlocks.WATER_HYACINTH.get().defaultBlockState();
		BlockState bottomState = AtmosphericBlocks.WATER_HYACINTH.get().defaultBlockState().setValue(WaterHyacinthBlock.WATERLOGGED, true).setValue(WaterHyacinthBlock.HALF, DoubleBlockHalf.LOWER);
		int i = 0;

		int xSpread = 7;
		int ySpread = 3;
		int zSpread = 7;

		BlockPos.MutableBlockPos blockpos$mutable = new BlockPos.MutableBlockPos();

		for (int j = 0; j < 512; ++j) {
			blockpos$mutable.setWithOffset(pos, random.nextInt(xSpread + 1) - random.nextInt(xSpread + 1), random.nextInt(ySpread + 1) - random.nextInt(ySpread + 1), random.nextInt(zSpread + 1) - random.nextInt(zSpread + 1));
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