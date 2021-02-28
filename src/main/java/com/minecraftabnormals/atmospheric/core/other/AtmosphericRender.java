package com.minecraftabnormals.atmospheric.core.other;

import com.minecraftabnormals.abnormals_core.core.util.DataUtil;
import com.minecraftabnormals.atmospheric.client.render.PassionfruitSeedRenderer;
import com.minecraftabnormals.atmospheric.core.registry.AtmosphericBlocks;
import com.minecraftabnormals.atmospheric.core.registry.AtmosphericEntities;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.world.FoliageColors;
import net.minecraft.world.biome.BiomeColors;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

import java.util.Arrays;
import java.util.List;

public class AtmosphericRender {
	public static void registerBlockColors() {
		BlockColors blockColors = Minecraft.getInstance().getBlockColors();
		ItemColors itemColors = Minecraft.getInstance().getItemColors();

		List<RegistryObject<Block>> foliageColors = Arrays.asList(
				AtmosphericBlocks.ROSEWOOD_LEAVES,
				AtmosphericBlocks.ROSEWOOD_LEAF_CARPET,
				AtmosphericBlocks.ROSEWOOD_HEDGE,
				AtmosphericBlocks.MORADO_LEAVES,
				AtmosphericBlocks.MORADO_LEAF_CARPET,
				AtmosphericBlocks.MORADO_HEDGE,
				AtmosphericBlocks.FLOWERING_MORADO_LEAVES,
				AtmosphericBlocks.FLOWERING_MORADO_LEAF_CARPET,
				AtmosphericBlocks.FLOWERING_MORADO_HEDGE,
				AtmosphericBlocks.YUCCA_LEAVES,
				AtmosphericBlocks.YUCCA_LEAF_CARPET,
				AtmosphericBlocks.YUCCA_HEDGE
		);

		DataUtil.registerBlockColor(blockColors, (x, world, pos, u) -> world != null && pos != null ? BiomeColors.getFoliageColor(world, pos) : FoliageColors.get(0.5D, 1.0D), foliageColors);
		DataUtil.registerBlockItemColor(itemColors, (color, items) -> FoliageColors.get(0.5D, 1.0D), foliageColors);
	}

	public static void registerEntityRenderers() {
		RenderingRegistry.registerEntityRenderingHandler(AtmosphericEntities.PASSIONFRUIT_SEED.get(), PassionfruitSeedRenderer::new);
	}

	public static void registerRenderLayers() {
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.ROSEWOOD_LADDER.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.ROSEWOOD_POST.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.STRIPPED_ROSEWOOD_POST.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.ROSEWOOD_LEAVES.get(), RenderType.getCutoutMipped());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.ROSEWOOD_LEAF_CARPET.get(), RenderType.getCutoutMipped());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.ROSEWOOD_HEDGE.get(), RenderType.getCutoutMipped());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.ROSEWOOD_SAPLING.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.POTTED_ROSEWOOD_SAPLING.get(), RenderType.getCutout());

		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.MORADO_LADDER.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.MORADO_POST.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.STRIPPED_MORADO_POST.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.MORADO_LEAVES.get(), RenderType.getCutoutMipped());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.MORADO_LEAF_CARPET.get(), RenderType.getCutoutMipped());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.MORADO_HEDGE.get(), RenderType.getCutoutMipped());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.FLOWERING_MORADO_LEAVES.get(), RenderType.getCutoutMipped());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.FLOWERING_MORADO_LEAF_CARPET.get(), RenderType.getCutoutMipped());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.FLOWERING_MORADO_HEDGE.get(), RenderType.getCutoutMipped());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.MORADO_SAPLING.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.POTTED_MORADO_SAPLING.get(), RenderType.getCutout());

		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.YUCCA_LADDER.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.YUCCA_POST.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.STRIPPED_YUCCA_POST.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.YUCCA_LEAVES.get(), RenderType.getCutoutMipped());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.YUCCA_LEAF_CARPET.get(), RenderType.getCutoutMipped());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.YUCCA_HEDGE.get(), RenderType.getCutoutMipped());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.YUCCA_SAPLING.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.POTTED_YUCCA_SAPLING.get(), RenderType.getCutout());

		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.KOUSA_LADDER.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.KOUSA_POST.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.STRIPPED_KOUSA_POST.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.KOUSA_LEAVES.get(), RenderType.getCutoutMipped());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.KOUSA_SAPLING.get(), RenderType.getCutoutMipped());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.KOUSA_LEAF_CARPET.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.KOUSA_HEDGE.get(), RenderType.getCutoutMipped());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.POTTED_KOUSA_SAPLING.get(), RenderType.getCutout());

		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.ASPEN_LADDER.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.ASPEN_POST.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.STRIPPED_ASPEN_POST.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.ASPEN_LEAVES.get(), RenderType.getCutoutMipped());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.ASPEN_LEAF_CARPET.get(), RenderType.getCutoutMipped());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.ASPEN_HEDGE.get(), RenderType.getCutoutMipped());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.ASPEN_SAPLING.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.POTTED_ASPEN_SAPLING.get(), RenderType.getCutout());

		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.GRIMWOOD_LADDER.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.GRIMWOOD_POST.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.STRIPPED_GRIMWOOD_POST.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.GRIMWOOD_LEAVES.get(), RenderType.getCutoutMipped());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.GRIMWOOD_LEAF_CARPET.get(), RenderType.getCutoutMipped());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.GRIMWOOD_HEDGE.get(), RenderType.getCutoutMipped());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.GRIMWOOD_SAPLING.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.POTTED_GRIMWOOD_SAPLING.get(), RenderType.getCutout());

		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.WARM_MONKEY_BRUSH.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.HOT_MONKEY_BRUSH.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.SCALDING_MONKEY_BRUSH.get(), RenderType.getCutout());

		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.WARM_WALL_MONKEY_BRUSH.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.HOT_WALL_MONKEY_BRUSH.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.SCALDING_WALL_MONKEY_BRUSH.get(), RenderType.getCutout());

		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.GILIA.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.PASSION_VINE.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.WATER_HYACINTH.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.YUCCA_FLOWER.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.TALL_YUCCA_FLOWER.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.YUCCA_BRANCH.get(), RenderType.getCutout());

		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.ALOE_VERA.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.TALL_ALOE_VERA.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.ALOE_GEL_BLOCK.get(), RenderType.getTranslucent());

		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.POTTED_WARM_MONKEY_BRUSH.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.POTTED_HOT_MONKEY_BRUSH.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.POTTED_SCALDING_MONKEY_BRUSH.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.POTTED_YUCCA_FLOWER.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.POTTED_GILIA.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(AtmosphericBlocks.POTTED_WATER_HYACINTH.get(), RenderType.getCutout());
	}
}
