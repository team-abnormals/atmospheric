package com.minecraftabnormals.atmospheric.common.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.world.IWorldReader;

import javax.annotation.Nullable;
import java.util.Map;

public class MonkeyBrushItem extends BlockItem {
	protected final Block wallBlock;

	public MonkeyBrushItem(Block floorBlock, Block wallBlockIn, Item.Properties propertiesIn) {
		super(floorBlock, propertiesIn);
		this.wallBlock = wallBlockIn;
	}

	@Nullable
	protected BlockState getPlacementState(BlockItemUseContext context) {
		BlockState blockstate = this.wallBlock.getStateForPlacement(context);
		BlockState blockstate1 = null;
		IWorldReader iworldreader = context.getLevel();
		BlockPos blockpos = context.getClickedPos();

		for (Direction direction : context.getNearestLookingDirections()) {
			BlockState blockstate2 = direction.getAxis().isVertical() ? this.getBlock().getStateForPlacement(context) : blockstate;
			if (blockstate2 != null && blockstate2.canSurvive(iworldreader, blockpos)) {
				blockstate1 = blockstate2;
				break;
			}
		}

		return blockstate1 != null && iworldreader.isUnobstructed(blockstate1, blockpos, ISelectionContext.empty()) ? blockstate1 : null;
	}

	public void registerBlocks(Map<Block, Item> blockToItemMap, Item itemIn) {
		super.registerBlocks(blockToItemMap, itemIn);
		blockToItemMap.put(this.wallBlock, itemIn);
	}

	public void removeFromBlockToItemMap(Map<Block, Item> blockToItemMap, Item itemIn) {
		super.removeFromBlockToItemMap(blockToItemMap, itemIn);
		blockToItemMap.remove(this.wallBlock);
	}
}
