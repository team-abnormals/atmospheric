package com.teamabnormals.atmospheric.common.block;

import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.lighting.LayerLightEngine;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;

import java.util.Random;

public class CrustoseBlock extends Block {

	public CrustoseBlock(Properties properties) {
		super(properties);
	}

	private static boolean canBeGrass(BlockState state, LevelReader world, BlockPos pos) {
		BlockPos blockpos = pos.above();
		BlockState blockstate = world.getBlockState(blockpos);
		int i = LayerLightEngine.getLightBlockInto(world, state, pos, blockstate, blockpos, Direction.UP, blockstate.getLightBlock(world, blockpos));
		return i < world.getMaxLightLevel();

	}

	private static boolean canPropagate(BlockState state, LevelReader world, BlockPos pos) {
		BlockPos blockpos = pos.above();
		return canBeGrass(state, world, pos) && !world.getFluidState(blockpos).is(FluidTags.WATER);
	}

	@Override
	public BlockState getToolModifiedState(BlockState state, UseOnContext context, ToolAction toolAction, boolean simulate) {
		if (toolAction == ToolActions.AXE_STRIP) {
			return AtmosphericBlocks.CRUSTOSE_PATH.get().defaultBlockState();
		}
		return super.getToolModifiedState(state, context, toolAction, simulate);
	}

	@Override
	public boolean canSustainPlant(BlockState state, BlockGetter blockReader, BlockPos pos, Direction direction, IPlantable iPlantable) {
		final BlockPos plantPos = new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ());
		final PlantType plantType = iPlantable.getPlantType(blockReader, plantPos);
		return plantType == PlantType.PLAINS;
	}

	@Override
	public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, Random random) {
		if (!worldIn.isClientSide) {
			if (!worldIn.isAreaLoaded(pos, 3))
				return; // Forge: prevent loading unloaded chunks when checking neighbor's light and spreading
			if (!canBeGrass(state, worldIn, pos)) {
				worldIn.setBlockAndUpdate(pos, Blocks.DIRT.defaultBlockState());
			} else {
				if (worldIn.getMaxLocalRawBrightness(pos.above()) >= 9) {
					BlockState blockstate = this.defaultBlockState();
					for (int i = 0; i < 4; ++i) {
						BlockPos blockpos = pos.offset(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
						if (worldIn.getBlockState(blockpos).getBlock() == Blocks.DIRT && canPropagate(blockstate, worldIn, blockpos)) {
							worldIn.setBlockAndUpdate(blockpos, this.defaultBlockState());
						} else if (worldIn.getBlockState(blockpos).getBlock() == AtmosphericBlocks.ASPEN_LOG.get() && canPropagate(blockstate, worldIn, blockpos)) {
							worldIn.setBlockAndUpdate(blockpos, AtmosphericBlocks.CRUSTOSE_LOG.get().defaultBlockState().setValue(CrustoseLogBlock.AXIS, worldIn.getBlockState(blockpos).getValue(CrustoseLogBlock.AXIS)));
						}
					}
				}
			}
		}
	}

}
