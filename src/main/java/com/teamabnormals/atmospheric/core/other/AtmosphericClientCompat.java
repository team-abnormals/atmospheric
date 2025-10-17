package com.teamabnormals.atmospheric.core.other;

import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import com.teamabnormals.atmospheric.core.registry.AtmosphericItems;
import com.teamabnormals.atmospheric.core.registry.datapack.AtmosphericTetraVariants;
import com.teamabnormals.blueprint.client.model.DynamicItemModel;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.world.level.FoliageColor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ModelEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.registries.DeferredBlock;

import static com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks.*;

@EventBusSubscriber(modid = Atmospheric.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class AtmosphericClientCompat {

	public static void registerCompat() {
		AtmosphericItems.setupTabEditors();
		AtmosphericBlocks.setupTabEditors();
		AtmosphericClientEvents.registerItemProperties();
		registerRenderLayers();
	}

	@SubscribeEvent
	public static void registerAdditional(ModelEvent.RegisterAdditional event) {
		DynamicItemModel.register(event, "tetra_bucket");
	}

	@SubscribeEvent
	public static void modifyBakingResult(ModelEvent.ModifyBakingResult event) {
		DynamicItemModel.bake(event, AtmosphericItems.TETRA_BUCKET.getId(), "tetra_bucket", ModelResourceLocation.standalone(AtmosphericTetraVariants.NEON.location().withPrefix("item/tetra_bucket/")), DynamicItemModel.fishBucket());
	}

	@SubscribeEvent
	public static void registerBlockColors(RegisterColorHandlersEvent.Block event) {
		event.register((x, level, pos, u) -> level != null && pos != null ? BiomeColors.getAverageFoliageColor(level, pos) : FoliageColor.get(0.5D, 1.0D),
				ROSEWOOD_LEAVES.get(), ROSEWOOD_LEAF_PILE.get(),
				MORADO_LEAVES.get(), MORADO_LEAF_PILE.get(),
				FLOWERING_MORADO_LEAVES.get(), FLOWERING_MORADO_LEAF_PILE.get(),
				YUCCA_LEAVES.get(), YUCCA_LEAF_PILE.get(),
				GREEN_ASPEN_LEAVES.get(), GREEN_ASPEN_LEAF_PILE.get()
		);
	}

	@SubscribeEvent
	public static void registerItemColors(RegisterColorHandlersEvent.Item event) {
		event.register((color, items) -> items > 0 ? -1 : FoliageColor.get(0.5D, 1.0D),
				ROSEWOOD_LEAVES, ROSEWOOD_LEAF_PILE,
				MORADO_LEAVES, MORADO_LEAF_PILE,
				FLOWERING_MORADO_LEAVES, FLOWERING_MORADO_LEAF_PILE,
				YUCCA_LEAVES, YUCCA_LEAF_PILE,
				GREEN_ASPEN_LEAVES, GREEN_ASPEN_LEAF_PILE
		);
	}

	private static void registerRenderLayers() {
		ItemBlockRenderTypes.setRenderLayer(ALOE_GEL_BLOCK.get(), RenderType.translucent());
		for (DeferredBlock<?> block : new DeferredBlock[]{
				ROSEWOOD_SAPLING, POTTED_ROSEWOOD_SAPLING, ROSEWOOD_DOOR, ROSEWOOD_TRAPDOOR,
				MORADO_SAPLING, POTTED_MORADO_SAPLING, MORADO_DOOR, MORADO_TRAPDOOR,
				WARM_MONKEY_BRUSH, WARM_WALL_MONKEY_BRUSH, POTTED_WARM_MONKEY_BRUSH,
				HOT_MONKEY_BRUSH, HOT_WALL_MONKEY_BRUSH, POTTED_HOT_MONKEY_BRUSH,
				SCALDING_MONKEY_BRUSH, SCALDING_WALL_MONKEY_BRUSH, POTTED_SCALDING_MONKEY_BRUSH,
				PASSION_VINE, WATER_HYACINTH, POTTED_WATER_HYACINTH,

				YUCCA_SAPLING, POTTED_YUCCA_SAPLING, YUCCA_DOOR, YUCCA_TRAPDOOR,
				ARID_SPROUTS, YUCCA_FLOWER, TALL_YUCCA_FLOWER, POTTED_YUCCA_FLOWER, YUCCA_BRANCH,
				GILIA, POTTED_GILIA, FIRETHORN, POTTED_FIRETHORN, FORSYTHIA, POTTED_FORSYTHIA,
				ALOE_VERA, TALL_ALOE_VERA, POTTED_ALOE_VERA,
				ARID_GLASS, ARID_GLASS_PANE,

				KOUSA_SAPLING, POTTED_KOUSA_SAPLING, KOUSA_DOOR, KOUSA_TRAPDOOR,
				SNOWY_BAMBOO_SAPLING, SNOWY_BAMBOO, POTTED_SNOWY_BAMBOO, SNOWY_CACTUS, POTTED_SNOWY_CACTUS,
				HANGING_CURRANT, CURRANT_SEEDLING, POTTED_CURRANT_SEEDLING,

				ASPEN_SAPLING, POTTED_ASPEN_SAPLING, GREEN_ASPEN_SAPLING, POTTED_GREEN_ASPEN_SAPLING, ASPEN_DOOR, ASPEN_TRAPDOOR,
				AGAVE, POTTED_AGAVE, GOLDEN_GROWTHS, POTTED_GOLDEN_GROWTHS,

				LAUREL_SAPLING, POTTED_LAUREL_SAPLING, DRY_LAUREL_SAPLING, POTTED_DRY_LAUREL_SAPLING, LAUREL_DOOR, LAUREL_TRAPDOOR,
				ORANGE, BLOOD_ORANGE,

				GRIMWOOD_SAPLING, POTTED_GRIMWOOD_SAPLING, GRIMWOOD_DOOR, GRIMWOOD_TRAPDOOR, GRIMWEB
		}) {
			ItemBlockRenderTypes.setRenderLayer(block.get(), RenderType.cutout());
		}
	}
}
