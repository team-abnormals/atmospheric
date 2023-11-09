package com.teamabnormals.atmospheric.common.block.grower;

import com.teamabnormals.atmospheric.core.other.tags.AtmosphericBlockTags;
import com.teamabnormals.atmospheric.core.registry.AtmosphericFeatures.AtmosphericConfiguredFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import javax.annotation.Nullable;

public class LaurelTreeGrower extends AbstractTreeGrower {

	@Override
	public boolean growTree(ServerLevel level, ChunkGenerator generator, BlockPos pos, BlockState state, RandomSource random) {
		boolean nether = level.dimensionTypeId().equals(BuiltinDimensionTypes.NETHER);
		boolean oranges = this.hasOranges(level, pos);
		Holder<? extends ConfiguredFeature<?, ?>> holder = nether ? this.getNetherFeature(random, oranges) : this.getConfiguredFeature(random, oranges);
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

	private boolean hasOranges(LevelAccessor level, BlockPos pos) {
		for (BlockPos blockpos : BlockPos.MutableBlockPos.betweenClosed(pos.below().north(3).west(3), pos.above(5).south(3).east(3))) {
			if (level.getBlockState(blockpos).is(AtmosphericBlockTags.ORANGES)) {
				return true;
			}
		}

		return false;
	}

	@Override
	@Nullable
	protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource random, boolean oranges) {
		return oranges ? AtmosphericConfiguredFeatures.LAUREL_ORANGES_08.getHolder().get() : AtmosphericConfiguredFeatures.LAUREL.getHolder().get();
	}

	@Nullable
	protected Holder<? extends ConfiguredFeature<?, ?>> getNetherFeature(RandomSource random, boolean oranges) {
		return oranges ? AtmosphericConfiguredFeatures.LAUREL_BLOOD_ORANGES_08.getHolder().get() : AtmosphericConfiguredFeatures.LAUREL.getHolder().get();
	}
}
