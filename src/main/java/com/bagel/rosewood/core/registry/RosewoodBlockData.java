package com.bagel.rosewood.core.registry;

import com.bagel.rosewood.core.util.DataUtils;
import net.minecraftforge.fml.ModList;

public class RosewoodBlockData {
	public static void registerCompostables() {
		DataUtils.registerCompostable(0.3F, RosewoodBlocks.ROSEWOOD_LEAVES.get());
		DataUtils.registerCompostable(0.65F,RosewoodBlocks.MONKEY_BRUSH.get());
		DataUtils.registerCompostable(0.5F, RosewoodBlocks.PASSION_VINE_BUNDLE.get());
		DataUtils.registerCompostable(0.3F, RosewoodBlocks.ROSEWOOD_SAPLING.get());
		DataUtils.registerCompostable(0.5F, RosewoodBlocks.PASSION_VINE.get());
		DataUtils.registerCompostable(0.65F,RosewoodItems.PASSIONFRUIT.get());
		
		if(ModList.get().isLoaded("quark")) {
			DataUtils.registerCompostable(0.3F, RosewoodBlocks.ROSEWOOD_LEAF_CARPET.get());
			DataUtils.registerCompostable(1F, RosewoodBlocks.PASSIONFRUIT_CRATE.get());
		}
	}
	
	public static void registerStrippables() {
		DataUtils.registerStrippable(RosewoodBlocks.ROSEWOOD_LOG.get(), RosewoodBlocks.STRIPPED_ROSEWOOD_LOG.get());
		DataUtils.registerStrippable(RosewoodBlocks.ROSEWOOD.get(), RosewoodBlocks.STRIPPED_ROSEWOOD.get());
	}
	
	public static void registerFlammables() {
		DataUtils.registerFlammable(RosewoodBlocks.ROSEWOOD_LEAVES.get(), 30, 60);
		DataUtils.registerFlammable(RosewoodBlocks.ROSEWOOD_LOG.get(), 5, 5);
		DataUtils.registerFlammable(RosewoodBlocks.ROSEWOOD.get(), 5, 5);
		DataUtils.registerFlammable(RosewoodBlocks.STRIPPED_ROSEWOOD_LOG.get(), 5, 5);
		DataUtils.registerFlammable(RosewoodBlocks.STRIPPED_ROSEWOOD.get(), 5, 5);
		DataUtils.registerFlammable(RosewoodBlocks.ROSEWOOD_PLANKS.get(), 5, 20);
		DataUtils.registerFlammable(RosewoodBlocks.ROSEWOOD_SLAB.get(), 5, 20);
		DataUtils.registerFlammable(RosewoodBlocks.ROSEWOOD_STAIRS.get(), 5, 20);
		DataUtils.registerFlammable(RosewoodBlocks.ROSEWOOD_FENCE.get(), 5, 20);
		DataUtils.registerFlammable(RosewoodBlocks.ROSEWOOD_FENCE_GATE.get(), 5, 20);

		DataUtils.registerFlammable(RosewoodBlocks.PASSION_VINE.get(), 5, 60);
		DataUtils.registerFlammable(RosewoodBlocks.PASSION_VINE_BUNDLE.get(), 5, 60);
		
		if(ModList.get().isLoaded("quark")) {
			DataUtils.registerFlammable(RosewoodBlocks.VERTICAL_ROSEWOOD_PLANKS.get(), 5, 20);
			DataUtils.registerFlammable(RosewoodBlocks.ROSEWOOD_LEAF_CARPET.get(), 30, 60);
			DataUtils.registerFlammable(RosewoodBlocks.ROSEWOOD_VERTICAL_SLAB.get(), 5, 20);
			DataUtils.registerFlammable(RosewoodBlocks.ROSEWOOD_BOOKSHELF.get(), 5, 20);
		}
	}
}
