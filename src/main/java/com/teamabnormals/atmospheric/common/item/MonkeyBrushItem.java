package com.teamabnormals.atmospheric.common.item;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;

import javax.annotation.Nullable;
import java.util.Map;

public class MonkeyBrushItem extends BlockItem {
	protected final Block wallBlock;

	public MonkeyBrushItem(Block floorBlock, Block wallBlockIn, Item.Properties propertiesIn) {
		super(floorBlock, propertiesIn);
		this.wallBlock = wallBlockIn;
	}

	@Override
	@Nullable
	protected BlockState getPlacementState(BlockPlaceContext context) {
		BlockState wallState = this.wallBlock.getStateForPlacement(context);
		BlockState state = null;
		LevelReader level = context.getLevel();
		BlockPos pos = context.getClickedPos();

		for (Direction direction : context.getNearestLookingDirections()) {
			BlockState dirState = direction.getAxis().isVertical() ? this.getBlock().getStateForPlacement(context) : wallState;
			if (dirState != null && dirState.canSurvive(level, pos)) {
				state = dirState;
				break;
			}
		}

		return state != null && level.isUnobstructed(state, pos, CollisionContext.empty()) ? state : null;
	}

	@Override
	public void registerBlocks(Map<Block, Item> blockToItemMap, Item item) {
		super.registerBlocks(blockToItemMap, item);
		blockToItemMap.put(this.wallBlock, item);
	}
}
