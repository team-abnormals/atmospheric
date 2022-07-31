package com.minecraftabnormals.atmospheric.common.item;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.Item;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.level.LevelReader;

import javax.annotation.Nullable;
import java.util.Map;

public class MonkeyBrushItem extends BlockItem {
	protected final Block wallBlock;

	public MonkeyBrushItem(Block floorBlock, Block wallBlockIn, Item.Properties propertiesIn) {
		super(floorBlock, propertiesIn);
		this.wallBlock = wallBlockIn;
	}

	@Nullable
	protected BlockState getPlacementState(BlockPlaceContext context) {
		BlockState blockstate = this.wallBlock.getStateForPlacement(context);
		BlockState blockstate1 = null;
		LevelReader iworldreader = context.getLevel();
		BlockPos blockpos = context.getClickedPos();

		for (Direction direction : context.getNearestLookingDirections()) {
			BlockState blockstate2 = direction.getAxis().isVertical() ? this.getBlock().getStateForPlacement(context) : blockstate;
			if (blockstate2 != null && blockstate2.canSurvive(iworldreader, blockpos)) {
				blockstate1 = blockstate2;
				break;
			}
		}

		return blockstate1 != null && iworldreader.isUnobstructed(blockstate1, blockpos, CollisionContext.empty()) ? blockstate1 : null;
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
