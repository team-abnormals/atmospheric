package com.teamabnormals.atmospheric.common.levelgen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration;

public class CoarseDirtFeature extends TerrainPatchFeature {

	public CoarseDirtFeature(Codec<ProbabilityFeatureConfiguration> codec) {
		super(codec);
	}

	@Override
	public BlockState getTerrainState() {
		return Blocks.COARSE_DIRT.defaultBlockState();
	}

	@Override
	public boolean canReplace(BlockState state) {
		return super.canReplace(state) || state.is(BlockTags.SAND);
	}
}
