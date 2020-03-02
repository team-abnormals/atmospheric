package com.bagel.atmospheric.core.data;

import com.bagel.atmospheric.core.registry.AtmosphericBlocks;
import com.bagel.atmospheric.core.registry.AtmosphericItems;
import com.bagel.atmospheric.core.util.DataUtils;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;

public class AtmosphericBlockData {
	public static void registerCompostables() {
		DataUtils.registerCompostable(0.3F, AtmosphericBlocks.ROSEWOOD_LEAVES.get());
		DataUtils.registerCompostable(0.65F,AtmosphericBlocks.MONKEY_BRUSH.get());
		DataUtils.registerCompostable(0.5F, AtmosphericBlocks.PASSION_VINE_BUNDLE.get());
		DataUtils.registerCompostable(0.3F, AtmosphericBlocks.ROSEWOOD_SAPLING.get());
		DataUtils.registerCompostable(0.5F, AtmosphericBlocks.PASSION_VINE.get());
		
		DataUtils.registerCompostable(0.65F,AtmosphericItems.PASSIONFRUIT.get());
		DataUtils.registerCompostable(0.65F,AtmosphericItems.YUCCA_FRUIT.get());
		DataUtils.registerCompostable(0.65F,AtmosphericItems.ROASTED_YUCCA_FRUIT.get());
		
		DataUtils.registerCompostable(0.3F, AtmosphericBlocks.YUCCA_LEAVES.get());
		DataUtils.registerCompostable(0.3F, AtmosphericBlocks.YUCCA_SAPLING.get());
		DataUtils.registerCompostable(0.65F,AtmosphericBlocks.YUCCA_FLOWER.get());
		DataUtils.registerCompostable(0.65F,AtmosphericBlocks.TALL_YUCCA_FLOWER.get());
		DataUtils.registerCompostable(0.5F, AtmosphericBlocks.BARREL_CACTUS.get());
		
		DataUtils.registerCompostable(0.65F,AtmosphericBlocks.GILIA.get());
		
		DataUtils.registerCompostable(0.3F, AtmosphericItems.ALOE_KERNELS.get());
		DataUtils.registerCompostable(0.65F, AtmosphericItems.ALOE_LEAVES.get());
		DataUtils.registerCompostable(1F,    AtmosphericBlocks.ALOE_BUNDLE.get());
		
		DataUtils.registerCompostable(0.3F, AtmosphericBlocks.KOUSA_LEAVES.get());
		DataUtils.registerCompostable(0.3F, AtmosphericBlocks.KOUSA_SAPLING.get());
		
		DataUtils.registerCompostable(0.3F, AtmosphericBlocks.ASPEN_LEAVES.get());
		DataUtils.registerCompostable(0.3F, AtmosphericBlocks.ASPEN_SAPLING.get());
		
		DataUtils.registerCompostable(0.3F, AtmosphericBlocks.ROSEWOOD_LEAF_CARPET.get());
		DataUtils.registerCompostable(0.3F, AtmosphericBlocks.YUCCA_LEAF_CARPET.get());
		DataUtils.registerCompostable(0.3F, AtmosphericBlocks.KOUSA_LEAF_CARPET.get());
		DataUtils.registerCompostable(0.3F, AtmosphericBlocks.ASPEN_LEAF_CARPET.get());
		
		DataUtils.registerCompostable(1F, AtmosphericBlocks.PASSIONFRUIT_CRATE.get());
		DataUtils.registerCompostable(1F, AtmosphericBlocks.YUCCA_CASK.get());
		DataUtils.registerCompostable(1F, AtmosphericBlocks.ROASTED_YUCCA_CASK.get());
		DataUtils.registerCompostable(1F, AtmosphericBlocks.BARREL_CACTUS_BATCH.get());
	}
	
	public static void registerStrippables() {
		DataUtils.registerStrippable(AtmosphericBlocks.ROSEWOOD_LOG.get(), AtmosphericBlocks.STRIPPED_ROSEWOOD_LOG.get());
		DataUtils.registerStrippable(AtmosphericBlocks.ROSEWOOD.get(), AtmosphericBlocks.STRIPPED_ROSEWOOD.get());
		
		DataUtils.registerStrippable(AtmosphericBlocks.YUCCA_LOG.get(), AtmosphericBlocks.STRIPPED_YUCCA_LOG.get());
		DataUtils.registerStrippable(AtmosphericBlocks.YUCCA_WOOD.get(), AtmosphericBlocks.STRIPPED_YUCCA_WOOD.get());
		
		DataUtils.registerStrippable(AtmosphericBlocks.KOUSA_LOG.get(), AtmosphericBlocks.STRIPPED_KOUSA_LOG.get());
		DataUtils.registerStrippable(AtmosphericBlocks.KOUSA_WOOD.get(), AtmosphericBlocks.STRIPPED_KOUSA_WOOD.get());
		
		DataUtils.registerStrippable(AtmosphericBlocks.ASPEN_LOG.get(), AtmosphericBlocks.STRIPPED_ASPEN_LOG.get());
		DataUtils.registerStrippable(AtmosphericBlocks.ASPEN_WOOD.get(), AtmosphericBlocks.STRIPPED_ASPEN_WOOD.get());
		DataUtils.registerStrippable(AtmosphericBlocks.CRUSTOSE_LOG.get(), AtmosphericBlocks.ASPEN_LOG.get());
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
		
		DataUtils.registerFlammable(AtmosphericBlocks.YUCCA_FLOWER.get(), 5, 60);
		DataUtils.registerFlammable(AtmosphericBlocks.TALL_YUCCA_FLOWER.get(), 5, 60);
		DataUtils.registerFlammable(AtmosphericBlocks.BARREL_CACTUS.get(), 5, 60);
		
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
		
		
		DataUtils.registerFlammable(AtmosphericBlocks.VERTICAL_ROSEWOOD_PLANKS.get(), 5, 20);
		DataUtils.registerFlammable(AtmosphericBlocks.ROSEWOOD_LEAF_CARPET.get(), 30, 60);
		DataUtils.registerFlammable(AtmosphericBlocks.ROSEWOOD_VERTICAL_SLAB.get(), 5, 20);
		DataUtils.registerFlammable(AtmosphericBlocks.ROSEWOOD_BOOKSHELF.get(), 5, 20);
		
		DataUtils.registerFlammable(AtmosphericBlocks.VERTICAL_YUCCA_PLANKS.get(), 5, 20);
		DataUtils.registerFlammable(AtmosphericBlocks.YUCCA_LEAF_CARPET.get(), 30, 60);
		DataUtils.registerFlammable(AtmosphericBlocks.YUCCA_VERTICAL_SLAB.get(), 5, 20);
		DataUtils.registerFlammable(AtmosphericBlocks.YUCCA_BOOKSHELF.get(), 5, 20);
			
		DataUtils.registerFlammable(AtmosphericBlocks.VERTICAL_KOUSA_PLANKS.get(), 5, 20);
		DataUtils.registerFlammable(AtmosphericBlocks.KOUSA_LEAF_CARPET.get(), 30, 60);
		DataUtils.registerFlammable(AtmosphericBlocks.KOUSA_VERTICAL_SLAB.get(), 5, 20);
		DataUtils.registerFlammable(AtmosphericBlocks.KOUSA_BOOKSHELF.get(), 5, 20);
			
		DataUtils.registerFlammable(AtmosphericBlocks.VERTICAL_ASPEN_PLANKS.get(), 5, 20);
		DataUtils.registerFlammable(AtmosphericBlocks.ASPEN_LEAF_CARPET.get(), 30, 60);
		DataUtils.registerFlammable(AtmosphericBlocks.ASPEN_VERTICAL_SLAB.get(), 5, 20);
		DataUtils.registerFlammable(AtmosphericBlocks.ASPEN_BOOKSHELF.get(), 5, 20);
	}
	
	public static void setupRenderLayer()
	{
		//Doors and Trapdoors
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.ASPEN_LEAVES.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.KOUSA_LEAVES.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.ROSEWOOD_LEAVES.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.YUCCA_LEAVES.get(),RenderType.getCutout());

		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.ASPEN_LEAF_CARPET.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.KOUSA_LEAF_CARPET.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.ROSEWOOD_LEAF_CARPET.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.YUCCA_LEAF_CARPET.get(),RenderType.getCutout());
		
		//Flowers
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.MONKEY_BRUSH.get(),RenderType.getCutoutMipped());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.GILIA.get(),RenderType.getCutoutMipped());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.PASSION_VINE.get(),RenderType.getCutoutMipped());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.YUCCA_FLOWER.get(),RenderType.getCutoutMipped());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.TALL_YUCCA_FLOWER.get(),RenderType.getCutoutMipped());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.ASPEN_SAPLING.get(),RenderType.getCutoutMipped());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.KOUSA_SAPLING.get(),RenderType.getCutoutMipped());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.ROSEWOOD_SAPLING.get(),RenderType.getCutoutMipped());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.YUCCA_SAPLING.get(),RenderType.getCutoutMipped());
		
		//Potted Flowers
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.POTTED_MONKEY_BRUSH.get(),RenderType.getCutoutMipped());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.POTTED_YUCCA_FLOWER.get(),RenderType.getCutoutMipped());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.POTTED_GILIA.get(),RenderType.getCutoutMipped());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.POTTED_ASPEN_SAPLING.get(),RenderType.getCutoutMipped());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.POTTED_KOUSA_SAPLING.get(),RenderType.getCutoutMipped());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.POTTED_ROSEWOOD_SAPLING.get(),RenderType.getCutoutMipped());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.POTTED_YUCCA_SAPLING.get(),RenderType.getCutoutMipped());
	}
}
