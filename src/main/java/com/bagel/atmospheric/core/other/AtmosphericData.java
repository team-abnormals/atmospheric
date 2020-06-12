package com.bagel.atmospheric.core.other;

import com.bagel.atmospheric.common.data.PassionVineBundleDispenseBehavior;
import com.bagel.atmospheric.common.data.PassionVineDispenseBehavior;
import com.bagel.atmospheric.core.registry.AtmosphericBlocks;
import com.bagel.atmospheric.core.registry.AtmosphericItems;
import com.teamabnormals.abnormals_core.core.utils.DataUtils;

import net.minecraft.block.DispenserBlock;

public class AtmosphericData {
	
	public static void registerDispenserBehaviors() {
		DispenserBlock.registerDispenseBehavior(AtmosphericBlocks.PASSION_VINE_BUNDLE.get().asItem(), new PassionVineBundleDispenseBehavior());
		DispenserBlock.registerDispenseBehavior(AtmosphericBlocks.PASSION_VINE.get().asItem(), new PassionVineDispenseBehavior());
	}
	
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
	
	public static void registerFlammables() {
		DataUtils.registerFlammable(AtmosphericBlocks.PASSION_VINE.get(), 5, 60);
		DataUtils.registerFlammable(AtmosphericBlocks.PASSION_VINE_BUNDLE.get(), 5, 60);

		DataUtils.registerFlammable(AtmosphericBlocks.YUCCA_FLOWER.get(), 5, 60);
		DataUtils.registerFlammable(AtmosphericBlocks.TALL_YUCCA_FLOWER.get(), 5, 60);
		DataUtils.registerFlammable(AtmosphericBlocks.BARREL_CACTUS.get(), 5, 60);
		
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
		DataUtils.registerFlammable(AtmosphericBlocks.VERTICAL_ROSEWOOD_PLANKS.get(), 5, 20);
		DataUtils.registerFlammable(AtmosphericBlocks.ROSEWOOD_LEAF_CARPET.get(), 30, 60);
		DataUtils.registerFlammable(AtmosphericBlocks.ROSEWOOD_VERTICAL_SLAB.get(), 5, 20);
		DataUtils.registerFlammable(AtmosphericBlocks.ROSEWOOD_BOOKSHELF.get(), 5, 20);
		
		DataUtils.registerFlammable(AtmosphericBlocks.YUCCA_LEAVES.get(), 30, 60);
		DataUtils.registerFlammable(AtmosphericBlocks.YUCCA_LOG.get(), 5, 5);
		DataUtils.registerFlammable(AtmosphericBlocks.YUCCA_WOOD.get(), 5, 5);
		DataUtils.registerFlammable(AtmosphericBlocks.STRIPPED_YUCCA_LOG.get(), 5, 5);
		DataUtils.registerFlammable(AtmosphericBlocks.STRIPPED_YUCCA_WOOD.get(), 5, 5);
		DataUtils.registerFlammable(AtmosphericBlocks.YUCCA_PLANKS.get(), 5, 20);
		DataUtils.registerFlammable(AtmosphericBlocks.YUCCA_SLAB.get(), 5, 20);
		DataUtils.registerFlammable(AtmosphericBlocks.YUCCA_STAIRS.get(), 5, 20);
		DataUtils.registerFlammable(AtmosphericBlocks.YUCCA_FENCE.get(), 5, 20);
		DataUtils.registerFlammable(AtmosphericBlocks.YUCCA_FENCE_GATE.get(), 5, 20);
		DataUtils.registerFlammable(AtmosphericBlocks.VERTICAL_YUCCA_PLANKS.get(), 5, 20);
		DataUtils.registerFlammable(AtmosphericBlocks.YUCCA_LEAF_CARPET.get(), 30, 60);
		DataUtils.registerFlammable(AtmosphericBlocks.YUCCA_VERTICAL_SLAB.get(), 5, 20);
		DataUtils.registerFlammable(AtmosphericBlocks.YUCCA_BOOKSHELF.get(), 5, 20);
		
		DataUtils.registerFlammable(AtmosphericBlocks.KOUSA_LEAVES.get(), 30, 60);
		DataUtils.registerFlammable(AtmosphericBlocks.KOUSA_LOG.get(), 5, 5);
		DataUtils.registerFlammable(AtmosphericBlocks.KOUSA_WOOD.get(), 5, 5);
		DataUtils.registerFlammable(AtmosphericBlocks.STRIPPED_KOUSA_LOG.get(), 5, 5);
		DataUtils.registerFlammable(AtmosphericBlocks.STRIPPED_KOUSA_WOOD.get(), 5, 5);
		DataUtils.registerFlammable(AtmosphericBlocks.KOUSA_PLANKS.get(), 5, 20);
		DataUtils.registerFlammable(AtmosphericBlocks.KOUSA_SLAB.get(), 5, 20);
		DataUtils.registerFlammable(AtmosphericBlocks.KOUSA_STAIRS.get(), 5, 20);
		DataUtils.registerFlammable(AtmosphericBlocks.KOUSA_FENCE.get(), 5, 20);
		DataUtils.registerFlammable(AtmosphericBlocks.KOUSA_FENCE_GATE.get(), 5, 20);
		DataUtils.registerFlammable(AtmosphericBlocks.VERTICAL_KOUSA_PLANKS.get(), 5, 20);
		DataUtils.registerFlammable(AtmosphericBlocks.KOUSA_LEAF_CARPET.get(), 30, 60);
		DataUtils.registerFlammable(AtmosphericBlocks.KOUSA_VERTICAL_SLAB.get(), 5, 20);
		DataUtils.registerFlammable(AtmosphericBlocks.KOUSA_BOOKSHELF.get(), 5, 20);
		
		DataUtils.registerFlammable(AtmosphericBlocks.ASPEN_LEAVES.get(), 30, 60);
		DataUtils.registerFlammable(AtmosphericBlocks.ASPEN_LOG.get(), 5, 5);
		DataUtils.registerFlammable(AtmosphericBlocks.ASPEN_WOOD.get(), 5, 5);
		DataUtils.registerFlammable(AtmosphericBlocks.STRIPPED_ASPEN_LOG.get(), 5, 5);
		DataUtils.registerFlammable(AtmosphericBlocks.STRIPPED_ASPEN_WOOD.get(), 5, 5);
		DataUtils.registerFlammable(AtmosphericBlocks.ASPEN_PLANKS.get(), 5, 20);
		DataUtils.registerFlammable(AtmosphericBlocks.ASPEN_SLAB.get(), 5, 20);
		DataUtils.registerFlammable(AtmosphericBlocks.ASPEN_STAIRS.get(), 5, 20);
		DataUtils.registerFlammable(AtmosphericBlocks.ASPEN_FENCE.get(), 5, 20);
		DataUtils.registerFlammable(AtmosphericBlocks.ASPEN_FENCE_GATE.get(), 5, 20);
		DataUtils.registerFlammable(AtmosphericBlocks.VERTICAL_ASPEN_PLANKS.get(), 5, 20);
		DataUtils.registerFlammable(AtmosphericBlocks.ASPEN_LEAF_CARPET.get(), 30, 60);
		DataUtils.registerFlammable(AtmosphericBlocks.ASPEN_VERTICAL_SLAB.get(), 5, 20);
		DataUtils.registerFlammable(AtmosphericBlocks.ASPEN_BOOKSHELF.get(), 5, 20);
		
		DataUtils.registerFlammable(AtmosphericBlocks.GRIMWOOD_LEAVES.get(), 30, 60);
		DataUtils.registerFlammable(AtmosphericBlocks.GRIMWOOD_LOG.get(), 5, 5);
		DataUtils.registerFlammable(AtmosphericBlocks.GRIMWOOD.get(), 5, 5);
		DataUtils.registerFlammable(AtmosphericBlocks.STRIPPED_GRIMWOOD_LOG.get(), 5, 5);
		DataUtils.registerFlammable(AtmosphericBlocks.STRIPPED_GRIMWOOD.get(), 5, 5);
		DataUtils.registerFlammable(AtmosphericBlocks.GRIMWOOD_PLANKS.get(), 5, 20);
		DataUtils.registerFlammable(AtmosphericBlocks.GRIMWOOD_SLAB.get(), 5, 20);
		DataUtils.registerFlammable(AtmosphericBlocks.GRIMWOOD_STAIRS.get(), 5, 20);
		DataUtils.registerFlammable(AtmosphericBlocks.GRIMWOOD_FENCE.get(), 5, 20);
		DataUtils.registerFlammable(AtmosphericBlocks.GRIMWOOD_FENCE_GATE.get(), 5, 20);
		DataUtils.registerFlammable(AtmosphericBlocks.VERTICAL_GRIMWOOD_PLANKS.get(), 5, 20);
		DataUtils.registerFlammable(AtmosphericBlocks.GRIMWOOD_LEAF_CARPET.get(), 30, 60);
		DataUtils.registerFlammable(AtmosphericBlocks.GRIMWOOD_VERTICAL_SLAB.get(), 5, 20);
		DataUtils.registerFlammable(AtmosphericBlocks.GRIMWOOD_BOOKSHELF.get(), 5, 20);
	}
}
