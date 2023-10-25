package com.teamabnormals.atmospheric.common.block.grower;

import com.teamabnormals.atmospheric.core.registry.AtmosphericFeatures.AtmosphericConfiguredFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import javax.annotation.Nullable;

public class LaurelTreeGrower extends AbstractTreeGrower {

	@Override
	public boolean growTree(ServerLevel level, ChunkGenerator generator, BlockPos pos, BlockState state, RandomSource random) {
		Holder<? extends ConfiguredFeature<?, ?>> holder = this.getConfiguredFeature(random, level.dimensionTypeId().equals(BuiltinDimensionTypes.NETHER));
		if (holder == null) {
			return false;
		} else {
			ConfiguredFeature<?, ?> configuredfeature = holder.value();
			BlockState blockstate = level.getFluidState(pos).createLegacyBlock();
			level.setBlock(pos, blockstate, 4);
			if (configuredfeature.place(level, generator, random, pos)) {
				if (level.getBlockState(pos) == blockstate) {
					level.sendBlockUpdated(pos, state, blockstate, 2);
				}

				return true;
			} else {
				level.setBlock(pos, state, 4);
				return false;
			}
		}
	}

	@Override
	@Nullable
	protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource random, boolean nether) {
		return nether ? AtmosphericConfiguredFeatures.LAUREL_NETHER.getHolder().get() : AtmosphericConfiguredFeatures.LAUREL_GROWN.getHolder().get();
	}
}
