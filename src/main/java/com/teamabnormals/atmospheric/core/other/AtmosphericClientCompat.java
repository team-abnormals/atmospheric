package com.teamabnormals.atmospheric.core.other;

import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import com.teamabnormals.atmospheric.core.registry.AtmosphericItems;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.FoliageColor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;

@EventBusSubscriber(modid = Atmospheric.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class AtmosphericClientCompat {

	public static void registerCompat() {
		AtmosphericItems.setupTabEditors();
		AtmosphericBlocks.setupTabEditors();
		AtmosphericClientEvents.registerItemProperties();
		registerRenderLayers();
	}

	@SubscribeEvent
	public static void registerBlockColors(RegisterColorHandlersEvent.Block event) {
		event.register((x, level, pos, u) -> level != null && pos != null ? BiomeColors.getAverageFoliageColor(level, pos) : FoliageColor.get(0.5D, 1.0D),
				AtmosphericBlocks.ROSEWOOD_LEAVES.get(), AtmosphericBlocks.ROSEWOOD_LEAF_PILE.get(),
				AtmosphericBlocks.MORADO_LEAVES.get(), AtmosphericBlocks.MORADO_LEAF_PILE.get(),
				AtmosphericBlocks.FLOWERING_MORADO_LEAVES.get(), AtmosphericBlocks.FLOWERING_MORADO_LEAF_PILE.get(),
				AtmosphericBlocks.YUCCA_LEAVES.get(), AtmosphericBlocks.YUCCA_LEAF_PILE.get(),
				AtmosphericBlocks.GREEN_ASPEN_LEAVES.get(), AtmosphericBlocks.GREEN_ASPEN_LEAF_PILE.get()
		);
	}

	@SubscribeEvent
	public static void registerItemColors(RegisterColorHandlersEvent.Item event) {
		event.register((color, items) -> FoliageColor.get(0.5D, 1.0D),
				AtmosphericBlocks.ROSEWOOD_LEAVES, AtmosphericBlocks.ROSEWOOD_LEAF_PILE,
				AtmosphericBlocks.MORADO_LEAVES, AtmosphericBlocks.MORADO_LEAF_PILE,
				AtmosphericBlocks.FLOWERING_MORADO_LEAVES, AtmosphericBlocks.FLOWERING_MORADO_LEAF_PILE,
				AtmosphericBlocks.YUCCA_LEAVES, AtmosphericBlocks.YUCCA_LEAF_PILE,
				AtmosphericBlocks.GREEN_ASPEN_LEAVES, AtmosphericBlocks.GREEN_ASPEN_LEAF_PILE
		);
	}

	private static void registerRenderLayers() {
		ItemBlockRenderTypes.setRenderLayer(AtmosphericBlocks.ROSEWOOD_SAPLING.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(AtmosphericBlocks.POTTED_ROSEWOOD_SAPLING.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(AtmosphericBlocks.ROSEWOOD_DOOR.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(AtmosphericBlocks.ROSEWOOD_TRAPDOOR.get(), RenderType.cutout());

		ItemBlockRenderTypes.setRenderLayer(AtmosphericBlocks.MORADO_SAPLING.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(AtmosphericBlocks.POTTED_MORADO_SAPLING.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(AtmosphericBlocks.MORADO_DOOR.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(AtmosphericBlocks.MORADO_TRAPDOOR.get(), RenderType.cutout());

		ItemBlockRenderTypes.setRenderLayer(AtmosphericBlocks.YUCCA_SAPLING.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(AtmosphericBlocks.POTTED_YUCCA_SAPLING.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(AtmosphericBlocks.YUCCA_DOOR.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(AtmosphericBlocks.YUCCA_TRAPDOOR.get(), RenderType.cutout());

		ItemBlockRenderTypes.setRenderLayer(AtmosphericBlocks.KOUSA_SAPLING.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(AtmosphericBlocks.POTTED_KOUSA_SAPLING.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(AtmosphericBlocks.KOUSA_DOOR.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(AtmosphericBlocks.KOUSA_TRAPDOOR.get(), RenderType.cutout());

		ItemBlockRenderTypes.setRenderLayer(AtmosphericBlocks.SNOWY_BAMBOO_SAPLING.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(AtmosphericBlocks.SNOWY_BAMBOO.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(AtmosphericBlocks.POTTED_SNOWY_BAMBOO.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(AtmosphericBlocks.SNOWY_CACTUS.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(AtmosphericBlocks.POTTED_SNOWY_CACTUS.get(), RenderType.cutout());

		ItemBlockRenderTypes.setRenderLayer(AtmosphericBlocks.HANGING_CURRANT.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(AtmosphericBlocks.CURRANT_SEEDLING.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(AtmosphericBlocks.POTTED_CURRANT_SEEDLING.get(), RenderType.cutout());

		ItemBlockRenderTypes.setRenderLayer(AtmosphericBlocks.GRIMWEB.get(), RenderType.cutout());

		ItemBlockRenderTypes.setRenderLayer(AtmosphericBlocks.ASPEN_SAPLING.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(AtmosphericBlocks.POTTED_ASPEN_SAPLING.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(AtmosphericBlocks.ASPEN_DOOR.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(AtmosphericBlocks.ASPEN_TRAPDOOR.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(AtmosphericBlocks.GREEN_ASPEN_SAPLING.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(AtmosphericBlocks.POTTED_GREEN_ASPEN_SAPLING.get(), RenderType.cutout());

		ItemBlockRenderTypes.setRenderLayer(AtmosphericBlocks.LAUREL_SAPLING.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(AtmosphericBlocks.POTTED_LAUREL_SAPLING.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(AtmosphericBlocks.DRY_LAUREL_SAPLING.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(AtmosphericBlocks.POTTED_DRY_LAUREL_SAPLING.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(AtmosphericBlocks.LAUREL_DOOR.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(AtmosphericBlocks.LAUREL_TRAPDOOR.get(), RenderType.cutout());

		ItemBlockRenderTypes.setRenderLayer(AtmosphericBlocks.ORANGE.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(AtmosphericBlocks.BLOOD_ORANGE.get(), RenderType.cutout());

		ItemBlockRenderTypes.setRenderLayer(AtmosphericBlocks.GRIMWOOD_SAPLING.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(AtmosphericBlocks.POTTED_GRIMWOOD_SAPLING.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(AtmosphericBlocks.GRIMWOOD_DOOR.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(AtmosphericBlocks.GRIMWOOD_TRAPDOOR.get(), RenderType.cutout());

		ItemBlockRenderTypes.setRenderLayer(AtmosphericBlocks.WARM_MONKEY_BRUSH.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(AtmosphericBlocks.HOT_MONKEY_BRUSH.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(AtmosphericBlocks.SCALDING_MONKEY_BRUSH.get(), RenderType.cutout());

		ItemBlockRenderTypes.setRenderLayer(AtmosphericBlocks.WARM_WALL_MONKEY_BRUSH.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(AtmosphericBlocks.HOT_WALL_MONKEY_BRUSH.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(AtmosphericBlocks.SCALDING_WALL_MONKEY_BRUSH.get(), RenderType.cutout());

		ItemBlockRenderTypes.setRenderLayer(AtmosphericBlocks.ARID_SPROUTS.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(AtmosphericBlocks.GILIA.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(AtmosphericBlocks.PASSION_VINE.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(AtmosphericBlocks.WATER_HYACINTH.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(AtmosphericBlocks.YUCCA_FLOWER.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(AtmosphericBlocks.TALL_YUCCA_FLOWER.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(AtmosphericBlocks.YUCCA_BRANCH.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(AtmosphericBlocks.FIRETHORN.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(AtmosphericBlocks.FORSYTHIA.get(), RenderType.cutout());

		ItemBlockRenderTypes.setRenderLayer(AtmosphericBlocks.ALOE_VERA.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(AtmosphericBlocks.TALL_ALOE_VERA.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(AtmosphericBlocks.ALOE_GEL_BLOCK.get(), RenderType.translucent());
		ItemBlockRenderTypes.setRenderLayer(AtmosphericBlocks.POTTED_ALOE_VERA.get(), RenderType.cutout());

		ItemBlockRenderTypes.setRenderLayer(AtmosphericBlocks.POTTED_WARM_MONKEY_BRUSH.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(AtmosphericBlocks.POTTED_HOT_MONKEY_BRUSH.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(AtmosphericBlocks.POTTED_SCALDING_MONKEY_BRUSH.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(AtmosphericBlocks.POTTED_YUCCA_FLOWER.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(AtmosphericBlocks.POTTED_GILIA.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(AtmosphericBlocks.POTTED_WATER_HYACINTH.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(AtmosphericBlocks.POTTED_FIRETHORN.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(AtmosphericBlocks.POTTED_FORSYTHIA.get(), RenderType.cutout());

		ItemBlockRenderTypes.setRenderLayer(AtmosphericBlocks.AGAVE.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(AtmosphericBlocks.POTTED_AGAVE.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(AtmosphericBlocks.GOLDEN_GROWTHS.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(AtmosphericBlocks.POTTED_GOLDEN_GROWTHS.get(), RenderType.cutout());

		ItemBlockRenderTypes.setRenderLayer(AtmosphericBlocks.ARID_GLASS.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(AtmosphericBlocks.ARID_GLASS_PANE.get(), RenderType.cutout());
	}
}
