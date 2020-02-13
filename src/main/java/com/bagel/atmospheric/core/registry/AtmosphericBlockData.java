package com.bagel.atmospheric.core.registry;

import com.bagel.atmospheric.core.util.DataUtils;

import net.minecraftforge.fml.ModList;

public class AtmosphericBlockData {
	public static void registerCompostables() {
		DataUtils.registerCompostable(0.3F, AtmosphericBlocks.ROSEWOOD_LEAVES.get());
		DataUtils.registerCompostable(0.65F,AtmosphericBlocks.MONKEY_BRUSH.get());
		DataUtils.registerCompostable(0.5F, AtmosphericBlocks.PASSION_VINE_BUNDLE.get());
		DataUtils.registerCompostable(0.3F, AtmosphericBlocks.ROSEWOOD_SAPLING.get());
		DataUtils.registerCompostable(0.5F, AtmosphericBlocks.PASSION_VINE.get());
		DataUtils.registerCompostable(0.65F,AtmosphericItems.PASSIONFRUIT.get());
		
		if(ModList.get().isLoaded("quark")) {
			DataUtils.registerCompostable(0.3F, AtmosphericBlocks.ROSEWOOD_LEAF_CARPET.get());
			DataUtils.registerCompostable(1F, AtmosphericBlocks.PASSIONFRUIT_CRATE.get());
		}
	}
	
	public static void registerStrippables() {
		DataUtils.registerStrippable(AtmosphericBlocks.ROSEWOOD_LOG.get(), AtmosphericBlocks.STRIPPED_ROSEWOOD_LOG.get());
		DataUtils.registerStrippable(AtmosphericBlocks.ROSEWOOD.get(), AtmosphericBlocks.STRIPPED_ROSEWOOD.get());
	}
	
	public static void registerFlammables() {
		DataUtils.registerFlammable(AtmosphericBlocks.ROSEWOOD_LEAVES.get(), 30, 60);
		DataUtils.registerFlammable(AtmosphericBlocks.ROSEWOOD_LOG.get(), 5, 5);
		DataUtils.registerFlammable(AtmosphericBlocks.ROSEWOOD.get(), 5, 5);
		DataUtils.registerFlammable(AtmosphericBlocks.STRIPPED_ROSEWOOD_LOG.get(), 5, 5);
		DataUtils.registerFlammable(AtmosphericBlocks.STRIPPED_ROSEWOOD.get(), 5, 5);
		DataUtils.registerFlammable(AtmosphericBlocks.ROSEWOOD_PLANKS.get(), 5, 20);
		DataUtils.registerFlammable(AtmosphericBlocks.ROSEWOOD_SLAB.get(), 5, 20);
		DataUtils.registerFlammable(AtmosphericBlocks.ROSEWOOD_STAIRS.get(), 5, 20);
		DataUtils.registerFlammable(AtmosphericBlocks.ROSEWOOD_FENCE.get(), 5, 20);
		DataUtils.registerFlammable(AtmosphericBlocks.ROSEWOOD_FENCE_GATE.get(), 5, 20);

		DataUtils.registerFlammable(AtmosphericBlocks.PASSION_VINE.get(), 5, 60);
		DataUtils.registerFlammable(AtmosphericBlocks.PASSION_VINE_BUNDLE.get(), 5, 60);
		
		if(ModList.get().isLoaded("quark")) {
			DataUtils.registerFlammable(AtmosphericBlocks.VERTICAL_ROSEWOOD_PLANKS.get(), 5, 20);
			DataUtils.registerFlammable(AtmosphericBlocks.ROSEWOOD_LEAF_CARPET.get(), 30, 60);
			DataUtils.registerFlammable(AtmosphericBlocks.ROSEWOOD_VERTICAL_SLAB.get(), 5, 20);
			DataUtils.registerFlammable(AtmosphericBlocks.ROSEWOOD_BOOKSHELF.get(), 5, 20);
		}
	}
}
