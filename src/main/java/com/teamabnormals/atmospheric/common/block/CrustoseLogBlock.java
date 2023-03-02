package com.teamabnormals.atmospheric.common.block;

import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import com.teamabnormals.blueprint.common.block.wood.LogBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.lighting.LayerLightEngine;

import java.util.function.Supplier;

public class CrustoseLogBlock extends LogBlock implements BonemealableBlock {

	public CrustoseLogBlock(Supplier<Block> block, Properties properties) {
		super(block, properties);
	}

	private static boolean canBeGrass(BlockState state, LevelReader world, BlockPos pos) {
		BlockPos abovePos = pos.above();
		BlockState blockstate = world.getBlockState(abovePos);
		int i = LayerLightEngine.getLightBlockInto(world, state, pos, blockstate, abovePos, Direction.UP, blockstate.getLightBlock(world, abovePos));
		return i < world.getMaxLightLevel();

	}

	private static boolean canPropagate(BlockState state, LevelReader world, BlockPos pos) {
		BlockPos blockpos = pos.above();
		return canBeGrass(state, world, pos) && !world.getFluidState(blockpos).is(FluidTags.WATER);
	}

	@Override
	public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource random) {
		if (!worldIn.isClientSide) {
			if (!worldIn.isAreaLoaded(pos, 3))
				return; // Forge: prevent loading unloaded chunks when checking neighbor's light and spreading
			if (!canBeGrass(state, worldIn, pos)) {
				worldIn.setBlockAndUpdate(pos, AtmosphericBlocks.ASPEN_LOG.get().defaultBlockState().setValue(CrustoseLogBlock.AXIS, state.getValue(CrustoseLogBlock.AXIS)));
			} else {
				if (worldIn.getMaxLocalRawBrightness(pos.above()) >= 9) {
					BlockState blockstate = this.defaultBlockState();
					for (int i = 0; i < 4; ++i) {
						BlockPos blockpos = pos.offset(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
						if (worldIn.getBlockState(blockpos).getBlock() == Blocks.DIRT && canPropagate(blockstate, worldIn, blockpos)) {
							worldIn.setBlockAndUpdate(blockpos, AtmosphericBlocks.CRUSTOSE.get().defaultBlockState());
						} else if (worldIn.getBlockState(blockpos).getBlock() == AtmosphericBlocks.ASPEN_LOG.get() && canPropagate(blockstate, worldIn, blockpos)) {
							worldIn.setBlockAndUpdate(blockpos, this.defaultBlockState().setValue(CrustoseLogBlock.AXIS, worldIn.getBlockState(blockpos).getValue(CrustoseLogBlock.AXIS)));
						}
					}
				}
			}
		}
	}

	@Override
	public boolean isValidBonemealTarget(BlockGetter worldIn, BlockPos pos, BlockState state, boolean isClient) {
		return worldIn.getBlockState(pos.above()).isAir();
	}

	@Override
	public boolean isBonemealSuccess(Level worldIn, RandomSource rand, BlockPos pos, BlockState state) {
		return true;
	}

	@Override
	public void performBonemeal(ServerLevel worldIn, RandomSource rand, BlockPos pos, BlockState state) {
		BlockPos blockpos = pos.above();
		BlockState blockstate = AtmosphericBlocks.CRUSTOSE_SPROUTS.get().defaultBlockState();

		label48:
		for (int i = 0; i < 128; ++i) {
			BlockPos blockpos1 = blockpos;

			for (int j = 0; j < i / 16; ++j) {
				blockpos1 = blockpos1.offset(rand.nextInt(3) - 1, (rand.nextInt(3) - 1) * rand.nextInt(3) / 2, rand.nextInt(3) - 1);
				if (!worldIn.getBlockState(blockpos1.below()).is(this) || worldIn.getBlockState(blockpos1).isCollisionShapeFullBlock(worldIn, blockpos1)) {
					continue label48;
				}
			}

			if (worldIn.getBlockState(blockpos1).isAir() && blockstate.canSurvive(worldIn, blockpos1)) {
				worldIn.setBlock(blockpos1, blockstate, 3);
			}
		}
	}
}
