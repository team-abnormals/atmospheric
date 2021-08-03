package com.minecraftabnormals.atmospheric.common.block;

import com.minecraftabnormals.atmospheric.core.registry.AtmosphericBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.lighting.LightEngine;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;
import net.minecraftforge.common.ToolType;

import java.util.Random;

public class CrustoseBlock extends Block {

	public CrustoseBlock(Properties properties) {
		super(properties);
	}

	private static boolean canBeGrass(BlockState state, IWorldReader world, BlockPos pos) {
		BlockPos blockpos = pos.above();
		BlockState blockstate = world.getBlockState(blockpos);
		int i = LightEngine.getLightBlockInto(world, state, pos, blockstate, blockpos, Direction.UP, blockstate.getLightBlock(world, blockpos));
		return i < world.getMaxLightLevel();

	}

	private static boolean canPropagate(BlockState state, IWorldReader world, BlockPos pos) {
		BlockPos blockpos = pos.above();
		return canBeGrass(state, world, pos) && !world.getFluidState(blockpos).is(FluidTags.WATER);
	}

	public BlockState getToolModifiedState(BlockState state, World world, BlockPos pos, PlayerEntity player, ItemStack stack, ToolType toolType) {
		if (toolType == ToolType.SHOVEL) {
			return AtmosphericBlocks.CRUSTOSE_PATH.get().defaultBlockState();
		} else {
			return super.getToolModifiedState(state, world, pos, player, stack, toolType);
		}
	}

	@Override
	public boolean canSustainPlant(BlockState state, IBlockReader blockReader, BlockPos pos, Direction direction, IPlantable iPlantable) {
		final BlockPos plantPos = new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ());
		final PlantType plantType = iPlantable.getPlantType(blockReader, plantPos);
		return plantType == PlantType.PLAINS;
	}

	public void tick(BlockState state, World worldIn, BlockPos pos, Random random) {
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
