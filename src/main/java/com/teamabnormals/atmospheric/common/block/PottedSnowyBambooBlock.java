package com.teamabnormals.atmospheric.common.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;

public class PottedSnowyBambooBlock extends FlowerPotBlock {

	public PottedSnowyBambooBlock(Block block, Properties properties) {
		super(block, properties);
	}

	@Override
	public Block getContent() {
		return Blocks.BAMBOO;
	}
}
