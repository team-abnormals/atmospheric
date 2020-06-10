package com.bagel.atmospheric.core.data;

import com.bagel.atmospheric.core.registry.AtmosphericBlocks;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;

public class AtmosphericRenderLayers {
	
	public static void setupRenderLayer()
	{
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.ROSEWOOD_LEAVES.get(),RenderType.getCutoutMipped());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.ROSEWOOD_LEAF_CARPET.get(),RenderType.getCutoutMipped());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.ROSEWOOD_SAPLING.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.POTTED_ROSEWOOD_SAPLING.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.ROSEWOOD_LADDER.get(),RenderType.getCutout());

		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.YUCCA_LEAVES.get(),RenderType.getCutoutMipped());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.YUCCA_LEAF_CARPET.get(),RenderType.getCutoutMipped());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.YUCCA_SAPLING.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.POTTED_YUCCA_SAPLING.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.YUCCA_LADDER.get(),RenderType.getCutout());

		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.KOUSA_LEAVES.get(),RenderType.getCutoutMipped());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.KOUSA_SAPLING.get(),RenderType.getCutoutMipped());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.KOUSA_LEAF_CARPET.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.POTTED_KOUSA_SAPLING.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.KOUSA_LADDER.get(),RenderType.getCutout());
		
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.ASPEN_LEAVES.get(),RenderType.getCutoutMipped());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.ASPEN_LEAF_CARPET.get(),RenderType.getCutoutMipped());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.ASPEN_SAPLING.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.POTTED_ASPEN_SAPLING.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.ASPEN_LADDER.get(),RenderType.getCutout());
		
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.GRIMWOOD_LEAVES.get(),RenderType.getCutoutMipped());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.GRIMWOOD_LEAF_CARPET.get(),RenderType.getCutoutMipped());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.GRIMWOOD_SAPLING.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.POTTED_GRIMWOOD_SAPLING.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.GRIMWOOD_LADDER.get(),RenderType.getCutout());

		//Flowers
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.WARM_MONKEY_BRUSH.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.HOT_MONKEY_BRUSH.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.SCALDING_MONKEY_BRUSH.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.GILIA.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.PASSION_VINE.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.YUCCA_FLOWER.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.TALL_YUCCA_FLOWER.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.YUCCA_BRANCH.get(),RenderType.getCutout());
		
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.ALOE_VERA.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.TALL_ALOE_VERA.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.ALOE_GEL_BLOCK.get(),RenderType.getTranslucent());

		//Potted Flowers
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.POTTED_WARM_MONKEY_BRUSH.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.POTTED_HOT_MONKEY_BRUSH.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.POTTED_SCALDING_MONKEY_BRUSH.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.POTTED_YUCCA_FLOWER.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.POTTED_GILIA.get(),RenderType.getCutout());
	}
}
