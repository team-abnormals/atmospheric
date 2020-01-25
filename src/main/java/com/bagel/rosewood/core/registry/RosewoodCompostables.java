package com.bagel.rosewood.core.registry;

import net.minecraft.block.ComposterBlock;
import net.minecraftforge.fml.ModList;

public class RosewoodCompostables {
	public static void registerCompostables() {
		ComposterBlock.registerCompostable(0.3F, RosewoodBlocks.ROSEWOOD_LEAVES.get());
		ComposterBlock.registerCompostable(0.65F, RosewoodBlocks.MONKEY_BRUSH.get());
		ComposterBlock.registerCompostable(0.5F, RosewoodBlocks.PASSION_VINE_BLOCK.get());
		ComposterBlock.registerCompostable(0.3F, RosewoodBlocks.ROSEWOOD_SAPLING.get());
		ComposterBlock.registerCompostable(0.5F, RosewoodBlocks.PASSION_VINE.get());
		ComposterBlock.registerCompostable(0.65F, RosewoodItems.PASSIONFRUIT.get());
		if(ModList.get().isLoaded("quark")) {
			ComposterBlock.registerCompostable(0.3F, RosewoodBlocks.ROSEWOOD_LEAF_CARPET.get());
			ComposterBlock.registerCompostable(1F, RosewoodBlocks.PASSIONFRUIT_CRATE.get());
		}
	}
}
