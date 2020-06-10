package com.bagel.atmospheric.core.data;

import com.bagel.atmospheric.core.registry.AtmosphericBlocks;
import com.bagel.atmospheric.core.registry.AtmosphericItems;
import com.teamabnormals.abnormals_core.core.utils.DataUtils;

public class AtmosphericCompostables {
	
	public static void registerCompostables() {
		DataUtils.registerCompostable(0.3F, AtmosphericBlocks.ROSEWOOD_LEAVES.get());
		DataUtils.registerCompostable(0.3F, AtmosphericBlocks.ROSEWOOD_SAPLING.get());
		DataUtils.registerCompostable(0.3F, AtmosphericBlocks.ROSEWOOD_LEAF_CARPET.get());
		
		DataUtils.registerCompostable(0.65F,AtmosphericBlocks.WARM_MONKEY_BRUSH.get());
		DataUtils.registerCompostable(0.65F,AtmosphericBlocks.HOT_MONKEY_BRUSH.get());
		DataUtils.registerCompostable(0.65F,AtmosphericBlocks.SCALDING_MONKEY_BRUSH.get());
		DataUtils.registerCompostable(0.5F, AtmosphericBlocks.PASSION_VINE_BUNDLE.get());
		DataUtils.registerCompostable(0.5F, AtmosphericBlocks.PASSION_VINE.get());
		
		DataUtils.registerCompostable(0.65F,AtmosphericItems.PASSIONFRUIT.get());
		DataUtils.registerCompostable(0.65F,AtmosphericItems.YUCCA_FRUIT.get());
		DataUtils.registerCompostable(0.65F,AtmosphericItems.ROASTED_YUCCA_FRUIT.get());
		
		DataUtils.registerCompostable(0.3F, AtmosphericBlocks.YUCCA_LEAVES.get());
		DataUtils.registerCompostable(0.3F, AtmosphericBlocks.YUCCA_SAPLING.get());
		DataUtils.registerCompostable(0.3F, AtmosphericBlocks.YUCCA_LEAF_CARPET.get());

		DataUtils.registerCompostable(0.65F,AtmosphericBlocks.YUCCA_FLOWER.get());
		DataUtils.registerCompostable(0.65F,AtmosphericBlocks.TALL_YUCCA_FLOWER.get());
		DataUtils.registerCompostable(0.5F, AtmosphericBlocks.BARREL_CACTUS.get());
		
		DataUtils.registerCompostable(0.65F,AtmosphericBlocks.GILIA.get());
		
		DataUtils.registerCompostable(0.3F, AtmosphericItems.ALOE_KERNELS.get());
		DataUtils.registerCompostable(0.65F, AtmosphericItems.ALOE_LEAVES.get());
		DataUtils.registerCompostable(1F,    AtmosphericBlocks.ALOE_BUNDLE.get());
		
		DataUtils.registerCompostable(0.3F, AtmosphericBlocks.KOUSA_LEAVES.get());
		DataUtils.registerCompostable(0.3F, AtmosphericBlocks.KOUSA_SAPLING.get());
		DataUtils.registerCompostable(0.3F, AtmosphericBlocks.KOUSA_LEAF_CARPET.get());

		DataUtils.registerCompostable(0.3F, AtmosphericBlocks.ASPEN_LEAVES.get());
		DataUtils.registerCompostable(0.3F, AtmosphericBlocks.ASPEN_SAPLING.get());
		DataUtils.registerCompostable(0.3F, AtmosphericBlocks.ASPEN_LEAF_CARPET.get());
		
		DataUtils.registerCompostable(0.3F, AtmosphericBlocks.GRIMWOOD_LEAVES.get());
		DataUtils.registerCompostable(0.3F, AtmosphericBlocks.GRIMWOOD_SAPLING.get());
		DataUtils.registerCompostable(0.3F, AtmosphericBlocks.GRIMWOOD_LEAF_CARPET.get());

		DataUtils.registerCompostable(1F, AtmosphericBlocks.PASSIONFRUIT_CRATE.get());
		DataUtils.registerCompostable(1F, AtmosphericBlocks.YUCCA_CASK.get());
		DataUtils.registerCompostable(1F, AtmosphericBlocks.ROASTED_YUCCA_CASK.get());
		DataUtils.registerCompostable(1F, AtmosphericBlocks.BARREL_CACTUS_BATCH.get());
	}
}
