package com.teamabnormals.atmospheric.common.levelgen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration;

public class DirtFeature extends TerrainPatchFeature {

	public DirtFeature(Codec<ProbabilityFeatureConfiguration> codec) {
		super(codec);
	}

	@Override
	public BlockState getTerrainState() {
		return Blocks.DIRT.defaultBlockState();
	}

	@Override
	public boolean canReplace(BlockState state) {
		return state.is(BlockTags.SAND);
	}
}
