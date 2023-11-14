package com.teamabnormals.atmospheric.common.item;

import com.teamabnormals.atmospheric.common.block.OrangeBlock;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class OrangeBlockItem extends BlockItem {

	public OrangeBlockItem(Block block, Properties builder) {
		super(block, builder);
	}

	@Override
	public InteractionResult place(BlockPlaceContext context) {
		BlockState state = context.getLevel().getBlockState(context.getClickedPos());
		return context.getPlayer().isSecondaryUseActive() || state.is(this.getBlock()) && state.getValue(OrangeBlock.ORANGES) < 2 ? super.place(context) : InteractionResult.FAIL;
	}
}
