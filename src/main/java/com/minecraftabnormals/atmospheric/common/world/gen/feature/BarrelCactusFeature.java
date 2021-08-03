package com.minecraftabnormals.atmospheric.common.world.gen.feature;

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

import java.util.Random;

public class BarrelCactusFeature extends Feature<BlockClusterFeatureConfig> {
	public BarrelCactusFeature(Codec<BlockClusterFeatureConfig> config) {
		super(config);
	}

	@Override
	public boolean place(ISeedReader worldIn, ChunkGenerator generator, Random rand, BlockPos pos, BlockClusterFeatureConfig config) {
		BlockState blockstate = config.stateProvider.getState(rand, pos);
		BlockPos blockpos;
		if (config.project) {
			blockpos = worldIn.getHeightmapPos(Heightmap.Type.WORLD_SURFACE_WG, pos);
		} else {
			blockpos = pos;
		}

		int i = 0;
		BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();

		for (int j = 0; j < config.tries; ++j) {
			blockpos$mutable.set(blockpos).move(rand.nextInt(config.xspread + 1) - rand.nextInt(config.xspread + 1), rand.nextInt(config.yspread + 1) - rand.nextInt(config.yspread + 1), rand.nextInt(config.zspread + 1) - rand.nextInt(config.zspread + 1));
			BlockPos blockpos1 = blockpos$mutable.below();
			blockstate = blockstate.setValue(BarrelCactusBlock.AGE, rand.nextInt(4));
			BlockState blockstate1 = worldIn.getBlockState(blockpos1);
			if ((worldIn.isEmptyBlock(blockpos$mutable) || config.canReplace && worldIn.getBlockState(blockpos$mutable).getMaterial().isReplaceable()) && blockstate.canSurvive(worldIn, blockpos$mutable) && (config.whitelist.isEmpty() || config.whitelist.contains(blockstate1.getBlock())) && blockstate1.getBlock() != AtmosphericBlocks.ARID_SAND.get() && !config.blacklist.contains(blockstate1) && (!config.needWater || worldIn.getFluidState(blockpos1.west()).is(FluidTags.WATER) || worldIn.getFluidState(blockpos1.east()).is(FluidTags.WATER) || worldIn.getFluidState(blockpos1.north()).is(FluidTags.WATER) || worldIn.getFluidState(blockpos1.south()).is(FluidTags.WATER))) {
				config.blockPlacer.place(worldIn, blockpos$mutable, blockstate, rand);
				++i;
			}
		}
		return i > 0;
	}
}	
