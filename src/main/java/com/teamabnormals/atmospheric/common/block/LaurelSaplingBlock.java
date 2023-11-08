package com.teamabnormals.atmospheric.common.block;

import com.teamabnormals.blueprint.common.block.wood.BlueprintSaplingBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockState;

public class LaurelSaplingBlock extends BlueprintSaplingBlock {

	public LaurelSaplingBlock(AbstractTreeGrower tree, Properties properties) {
		super(tree, properties);
	}

	@Override
	protected boolean mayPlaceOn(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return state.is(BlockTags.SAND) || super.mayPlaceOn(state, worldIn, pos);
	}
}
