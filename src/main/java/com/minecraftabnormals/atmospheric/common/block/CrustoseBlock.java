package com.minecraftabnormals.atmospheric.common.block;

import java.util.Random;

import com.minecraftabnormals.atmospheric.core.registry.AtmosphericBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.lighting.LightEngine;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;

public class CrustoseBlock extends Block {

	public CrustoseBlock(Properties properties) {
		super(properties);
	}

	private static boolean func_220257_b(BlockState state, IWorldReader world, BlockPos pos) {
		BlockPos blockpos = pos.up();
		BlockState blockstate = world.getBlockState(blockpos);
		int i = LightEngine.func_215613_a(world, state, pos, blockstate, blockpos, Direction.UP, blockstate.getOpacity(world, blockpos));
		return i < world.getMaxLightLevel();

	}

	private static boolean func_220256_c(BlockState state, IWorldReader world, BlockPos pos) {
		BlockPos blockpos = pos.up();
		return func_220257_b(state, world, pos)
				&& !world.getFluidState(blockpos).isTagged(FluidTags.WATER);
	}
	
	@Override
    public boolean canSustainPlant(BlockState state, IBlockReader blockReader, BlockPos pos, Direction direction, IPlantable iPlantable) {
        final BlockPos plantPos = new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ());
        final PlantType plantType = iPlantable.getPlantType(blockReader, plantPos);
        if (plantType == PlantType.PLAINS) {
            return true;
        } else {
            return false;
        }
    }

	public void tick(BlockState state, World worldIn, BlockPos pos, Random random) {
		if (!worldIn.isRemote) {
			if (!worldIn.isAreaLoaded(pos, 3))
				return; // Forge: prevent loading unloaded chunks when checking neighbor's light and spreading
			if (!func_220257_b(state, worldIn, pos)) {
				worldIn.setBlockState(pos, Blocks.DIRT.getDefaultState());
			} else {
				if (worldIn.getLight(pos.up()) >= 9) {
					BlockState blockstate = this.getDefaultState();
					for (int i = 0; i < 4; ++i) {
						BlockPos blockpos = pos.add(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
						if (worldIn.getBlockState(blockpos).getBlock() == Blocks.DIRT && func_220256_c(blockstate, worldIn, blockpos)) {
							worldIn.setBlockState(blockpos, this.getDefaultState());
						} else if (worldIn.getBlockState(blockpos).getBlock() == AtmosphericBlocks.ASPEN_LOG.get() && func_220256_c(blockstate, worldIn, blockpos)) {
							worldIn.setBlockState(blockpos, AtmosphericBlocks.CRUSTOSE_LOG.get().getDefaultState().with(CrustoseLogBlock.AXIS, worldIn.getBlockState(blockpos).get(CrustoseLogBlock.AXIS)));
						}
					}
				}
			}
		}
	}

}
