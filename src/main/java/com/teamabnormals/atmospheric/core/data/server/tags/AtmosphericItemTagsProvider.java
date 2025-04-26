package com.teamabnormals.atmospheric.core.data.server.tags;

import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.atmospheric.core.other.tags.AtmosphericBlockTags;
import com.teamabnormals.atmospheric.core.registry.AtmosphericItems;
import com.teamabnormals.blueprint.core.data.server.tags.BlueprintItemTagsProvider;
import com.teamabnormals.blueprint.core.other.tags.BlueprintItemTags;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

import static com.teamabnormals.atmospheric.core.other.tags.AtmosphericItemTags.*;
import static com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks.*;

public class AtmosphericItemTagsProvider extends BlueprintItemTagsProvider {

	public AtmosphericItemTagsProvider(PackOutput output, CompletableFuture<Provider> provider, CompletableFuture<TagsProvider.TagLookup<Block>> lookup, ExistingFileHelper helper) {
		super(Atmospheric.MOD_ID, output, provider, lookup, helper);
	}

	@Override
	public void addTags(Provider provider) {
		this.copyWoodsetTags();

		this.tag(COCHINEAL_FOOD).add(AtmosphericItems.DRAGON_FRUIT.get()).addTag(COCHINEAL_SUPER_LOVE_FOOD);
		this.tag(COCHINEAL_SUPER_LOVE_FOOD).add(AtmosphericItems.GOLDEN_DRAGON_FRUIT.get());

		this.tag(ItemTags.CHICKEN_FOOD).add(AtmosphericItems.ALOE_KERNELS.get());
		this.tag(ItemTags.PARROT_FOOD).add(AtmosphericItems.ALOE_KERNELS.get());

		this.copy(AtmosphericBlockTags.ROSEWOOD_LOGS, ROSEWOOD_LOGS);
		this.copy(AtmosphericBlockTags.MORADO_LOGS, MORADO_LOGS);
		this.copy(AtmosphericBlockTags.YUCCA_LOGS, YUCCA_LOGS);
		this.copy(AtmosphericBlockTags.ASPEN_LOGS, ASPEN_LOGS);
		this.copy(AtmosphericBlockTags.KOUSA_LOGS, KOUSA_LOGS);
		this.copy(AtmosphericBlockTags.GRIMWOOD_LOGS, GRIMWOOD_LOGS);
		this.copy(AtmosphericBlockTags.LAUREL_LOGS, LAUREL_LOGS);
		this.copy(AtmosphericBlockTags.TRAVERTINE, TRAVERTINE);
		this.tag(SMELTS_TO_ARID_GLASS).add(ARID_SAND.get().asItem(), RED_ARID_SAND.get().asItem());

		this.copy(BlockTags.SAND, ItemTags.SAND);
		this.copy(BlockTags.SLABS, ItemTags.SLABS);
		this.copy(BlockTags.WALLS, ItemTags.WALLS);
		this.copy(BlockTags.STAIRS, ItemTags.STAIRS);
		this.tag(MONKEY_BRUSH).add(WARM_MONKEY_BRUSH.get().asItem(), HOT_MONKEY_BRUSH.get().asItem(), SCALDING_MONKEY_BRUSH.get().asItem());
		this.copy(BlockTags.SMALL_FLOWERS, ItemTags.SMALL_FLOWERS);
		this.copy(BlockTags.TALL_FLOWERS, ItemTags.TALL_FLOWERS);
		this.copy(BlockTags.FLOWERS, ItemTags.FLOWERS);
		this.copy(BlockTags.STANDING_SIGNS, ItemTags.SIGNS);
		this.copy(BlockTags.DIRT, ItemTags.DIRT);
		this.tag(ItemTags.FLOWERS).add(AtmosphericItems.YELLOW_BLOSSOMS.get());
		this.tag(ItemTags.BOATS).add(AtmosphericItems.ROSEWOOD_BOAT.get(), AtmosphericItems.MORADO_BOAT.get(), AtmosphericItems.YUCCA_BOAT.get(), AtmosphericItems.KOUSA_BOAT.get(), AtmosphericItems.ASPEN_BOAT.get(), AtmosphericItems.GRIMWOOD_BOAT.get(), AtmosphericItems.LAUREL_BOAT.get());
		this.tag(ItemTags.CHEST_BOATS).add(AtmosphericItems.ROSEWOOD_CHEST_BOAT.get(), AtmosphericItems.MORADO_CHEST_BOAT.get(), AtmosphericItems.YUCCA_CHEST_BOAT.get(), AtmosphericItems.KOUSA_CHEST_BOAT.get(), AtmosphericItems.ASPEN_CHEST_BOAT.get(), AtmosphericItems.GRIMWOOD_CHEST_BOAT.get(), AtmosphericItems.LAUREL_CHEST_BOAT.get());
		this.tag(BlueprintItemTags.FURNACE_BOATS).add(AtmosphericItems.ROSEWOOD_FURNACE_BOAT.get(), AtmosphericItems.MORADO_FURNACE_BOAT.get(), AtmosphericItems.YUCCA_FURNACE_BOAT.get(), AtmosphericItems.KOUSA_FURNACE_BOAT.get(), AtmosphericItems.ASPEN_FURNACE_BOAT.get(), AtmosphericItems.GRIMWOOD_FURNACE_BOAT.get(), AtmosphericItems.LAUREL_FURNACE_BOAT.get());
		this.tag(BlueprintItemTags.LARGE_BOATS).add(AtmosphericItems.LARGE_ROSEWOOD_BOAT.get(), AtmosphericItems.LARGE_MORADO_BOAT.get(), AtmosphericItems.LARGE_YUCCA_BOAT.get(), AtmosphericItems.LARGE_KOUSA_BOAT.get(), AtmosphericItems.LARGE_ASPEN_BOAT.get(), AtmosphericItems.LARGE_GRIMWOOD_BOAT.get(), AtmosphericItems.LARGE_LAUREL_BOAT.get());
		this.tag(ItemTags.PIGLIN_LOVED).add(AtmosphericItems.SHIMMERING_PASSION_FRUIT.get(), SHIMMERING_PASSION_FRUIT_CRATE.get().asItem(), AtmosphericItems.GOLDEN_DRAGON_FRUIT.get(), GOLDEN_DRAGON_FRUIT_CRATE.get().asItem());
		this.tag(ItemTags.STONE_TOOL_MATERIALS).addTag(TRAVERTINE);
		this.tag(ItemTags.STONE_CRAFTING_MATERIALS).addTag(TRAVERTINE);
		this.tag(ItemTags.DECORATED_POT_SHERDS).add(AtmosphericItems.SCYTHE_POTTERY_SHERD.get(), AtmosphericItems.SUCCULENT_POTTERY_SHERD.get(), AtmosphericItems.SUN_POTTERY_SHERD.get());
		this.tag(ItemTags.TRIM_TEMPLATES).add(AtmosphericItems.APOSTLE_ARMOR_TRIM_SMITHING_TEMPLATE.get(), AtmosphericItems.DRUID_ARMOR_TRIM_SMITHING_TEMPLATE.get(), AtmosphericItems.PETRIFIED_ARMOR_TRIM_SMITHING_TEMPLATE.get());
		this.tag(ItemTags.TRIM_MATERIALS).add(AtmosphericItems.CARMINE_HUSK.get());

		this.copy(Tags.Blocks.STRIPPED_LOGS, Tags.Items.STRIPPED_LOGS);
		this.copy(Tags.Blocks.STRIPPED_WOODS, Tags.Items.STRIPPED_WOODS);
		this.copy(Tags.Blocks.SANDS_COLORLESS, Tags.Items.SANDS_COLORLESS);
		this.copy(Tags.Blocks.SANDS_RED, Tags.Items.SANDS_RED);
		this.copy(Tags.Blocks.SANDSTONE_BLOCKS, Tags.Items.SANDSTONE_BLOCKS);
		this.copy(Tags.Blocks.GLASS_BLOCKS_COLORLESS, Tags.Items.GLASS_BLOCKS_COLORLESS);
		this.copy(Tags.Blocks.GLASS_PANES_COLORLESS, Tags.Items.GLASS_PANES_COLORLESS);
		this.tag(Tags.Items.BOOKSHELVES).add(ROSEWOOD_BOOKSHELF.get().asItem(), MORADO_BOOKSHELF.get().asItem(), YUCCA_BOOKSHELF.get().asItem(), KOUSA_BOOKSHELF.get().asItem(), ASPEN_BOOKSHELF.get().asItem(), GRIMWOOD_BOOKSHELF.get().asItem(), LAUREL_BOOKSHELF.get().asItem());
		this.tag(FRUITS).addTags(FRUITS_PASSION_FRUIT, FRUITS_CURRANT, FRUITS_DRAGON_FRUIT, FRUITS_ORANGE);
		this.tag(FRUITS_PASSION_FRUIT).add(AtmosphericItems.PASSION_FRUIT.get());
		this.tag(FRUITS_CURRANT).add(AtmosphericItems.CURRANT.get());
		this.tag(FRUITS_DRAGON_FRUIT).add(AtmosphericItems.DRAGON_FRUIT.get());
		this.tag(FRUITS_ORANGE).add(AtmosphericItems.ORANGE.get());
		this.tag(Tags.Items.SEEDS).addTag(SEEDS_ALOE_VERA);
		this.tag(SEEDS_ALOE_VERA).add(AtmosphericItems.ALOE_KERNELS.get());
	}
}