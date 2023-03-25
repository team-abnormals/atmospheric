package com.teamabnormals.atmospheric.common.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FlowerPotBlock;

import java.util.function.Supplier;

public class SnowyFlowerPotBlock extends FlowerPotBlock {
	private final Supplier<Block> actualContent;

	public SnowyFlowerPotBlock(Block block, Supplier<Block> actualContent, Properties properties) {
		super(block, properties);
		this.actualContent = actualContent;
	}

	@Override
	public Block getContent() {
		return actualContent.get();
	}
}
