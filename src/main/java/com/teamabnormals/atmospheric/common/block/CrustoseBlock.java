package com.teamabnormals.atmospheric.common.block;

import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;

public class CrustoseBlock extends Block implements BonemealableBlock, Crustose {

	public CrustoseBlock(Properties properties) {
		super(properties);
	}

	@Override
	public BlockState getToolModifiedState(BlockState state, UseOnContext context, ToolAction toolAction, boolean simulate) {
		if (toolAction == ToolActions.SHOVEL_FLATTEN) {
			return AtmosphericBlocks.CRUSTOSE_PATH.get().defaultBlockState();
		}
		return super.getToolModifiedState(state, context, toolAction, simulate);
	}

	@Override
	public boolean canSustainPlant(BlockState state, BlockGetter level, BlockPos pos, Direction direction, IPlantable plantable) {
		final BlockPos plantPos = new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ());
		final PlantType plantType = plantable.getPlantType(level, plantPos);
		return plantType == PlantType.PLAINS;
	}

	@Override
	public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		this.randomCrustoseTick(state, level, pos, random);
	}


	@Override
	public boolean isValidBonemealTarget(BlockGetter level, BlockPos pos, BlockState state, boolean isClient) {
		return this.isCrustoseValidBonemealTarget(level, pos, state, isClient);
	}

	@Override
	public boolean isBonemealSuccess(Level worldIn, RandomSource rand, BlockPos pos, BlockState state) {
		return true;
	}

	@Override
	public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
		this.performCrustoseBonemeal(this, level, random, pos, state);
	}

	@Override
	public Block getUnspreadBlock() {
		return Blocks.DIRT;
	}
}
