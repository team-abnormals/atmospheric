package com.minecraftabnormals.atmospheric.common.block;

import com.google.common.base.Supplier;
import com.minecraftabnormals.abnormals_core.common.blocks.wood.AbnormalsLogBlock;
import com.minecraftabnormals.atmospheric.core.registry.AtmosphericBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.lighting.LightEngine;

import java.util.Random;

public class CrustoseLogBlock extends AbnormalsLogBlock {

	public CrustoseLogBlock(Supplier<Block> block, Properties properties) {
		super(block, properties);
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

	public void tick(BlockState state, World worldIn, BlockPos pos, Random random) {
		if (!worldIn.isRemote) {
			if (!worldIn.isAreaLoaded(pos, 3))
				return; // Forge: prevent loading unloaded chunks when checking neighbor's light and spreading
			if (!func_220257_b(state, worldIn, pos)) {
				worldIn.setBlockState(pos, AtmosphericBlocks.ASPEN_LOG.get().getDefaultState().with(CrustoseLogBlock.AXIS, state.get(CrustoseLogBlock.AXIS)));
			} else {
				if (worldIn.getLight(pos.up()) >= 9) {
					BlockState blockstate = this.getDefaultState();
					for (int i = 0; i < 4; ++i) {
						BlockPos blockpos = pos.add(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
						if (worldIn.getBlockState(blockpos).getBlock() == Blocks.DIRT && func_220256_c(blockstate, worldIn, blockpos)) {
							worldIn.setBlockState(blockpos, AtmosphericBlocks.CRUSTOSE.get().getDefaultState());
						} else if (worldIn.getBlockState(blockpos).getBlock() == AtmosphericBlocks.ASPEN_LOG.get() && func_220256_c(blockstate, worldIn, blockpos)) {
							worldIn.setBlockState(blockpos, this.getDefaultState().with(CrustoseLogBlock.AXIS, worldIn.getBlockState(blockpos).get(CrustoseLogBlock.AXIS)));
						}
					}
				}
			}
		}
	}

}
