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
import static com.teamabnormals.atmospheric.core.registry.AtmosphericItems.*;

public class AtmosphericItemTagsProvider extends BlueprintItemTagsProvider {

	public AtmosphericItemTagsProvider(PackOutput output, CompletableFuture<Provider> provider, CompletableFuture<TagsProvider.TagLookup<Block>> lookup, ExistingFileHelper helper) {
		super(Atmospheric.MOD_ID, output, provider, lookup, helper);
	}

	@Override
	public void addTags(Provider provider) {
		this.copyWoodsetTags();

		this.tag(COCHINEAL_FOOD).add(DRAGON_FRUIT.get()).addTag(COCHINEAL_SUPER_LOVE_FOOD);
		this.tag(COCHINEAL_SUPER_LOVE_FOOD).add(GOLDEN_DRAGON_FRUIT.get());

		this.tag(ItemTags.CHICKEN_FOOD).add(ALOE_KERNELS.get());
		this.tag(ItemTags.PARROT_FOOD).add(ALOE_KERNELS.get());

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
		this.tag(ItemTags.FLOWERS).add(YELLOW_BLOSSOMS.get());
		this.tag(ItemTags.BOATS).add(ROSEWOOD_BOAT.get(), MORADO_BOAT.get(), YUCCA_BOAT.get(), KOUSA_BOAT.get(), ASPEN_BOAT.get(), GRIMWOOD_BOAT.get(), LAUREL_BOAT.get());
		this.tag(ItemTags.CHEST_BOATS).add(ROSEWOOD_CHEST_BOAT.get(), MORADO_CHEST_BOAT.get(), YUCCA_CHEST_BOAT.get(), KOUSA_CHEST_BOAT.get(), ASPEN_CHEST_BOAT.get(), GRIMWOOD_CHEST_BOAT.get(), LAUREL_CHEST_BOAT.get());
		this.tag(BlueprintItemTags.FURNACE_BOATS).add(ROSEWOOD_FURNACE_BOAT.get(), MORADO_FURNACE_BOAT.get(), YUCCA_FURNACE_BOAT.get(), KOUSA_FURNACE_BOAT.get(), ASPEN_FURNACE_BOAT.get(), GRIMWOOD_FURNACE_BOAT.get(), LAUREL_FURNACE_BOAT.get());
		this.tag(BlueprintItemTags.LARGE_BOATS).add(LARGE_ROSEWOOD_BOAT.get(), LARGE_MORADO_BOAT.get(), LARGE_YUCCA_BOAT.get(), LARGE_KOUSA_BOAT.get(), LARGE_ASPEN_BOAT.get(), LARGE_GRIMWOOD_BOAT.get(), LARGE_LAUREL_BOAT.get());
		this.tag(ItemTags.PIGLIN_LOVED).add(SHIMMERING_PASSION_FRUIT.get(), SHIMMERING_PASSION_FRUIT_CRATE.get().asItem(), GOLDEN_DRAGON_FRUIT.get(), GOLDEN_DRAGON_FRUIT_CRATE.get().asItem());
		this.tag(ItemTags.STONE_TOOL_MATERIALS).addTag(TRAVERTINE);
		this.tag(ItemTags.STONE_CRAFTING_MATERIALS).addTag(TRAVERTINE);
		this.tag(ItemTags.DECORATED_POT_SHERDS).add(SCYTHE_POTTERY_SHERD.get(), SUCCULENT_POTTERY_SHERD.get(), SUN_POTTERY_SHERD.get());
		this.tag(ItemTags.TRIM_TEMPLATES).add(APOSTLE_ARMOR_TRIM_SMITHING_TEMPLATE.get(), DRUID_ARMOR_TRIM_SMITHING_TEMPLATE.get(), PETRIFIED_ARMOR_TRIM_SMITHING_TEMPLATE.get());
		this.tag(ItemTags.TRIM_MATERIALS).add(CARMINE_HUSK.get());

		this.copy(Tags.Blocks.STRIPPED_LOGS, Tags.Items.STRIPPED_LOGS);
		this.copy(Tags.Blocks.STRIPPED_WOODS, Tags.Items.STRIPPED_WOODS);
		this.copy(Tags.Blocks.SANDS_COLORLESS, Tags.Items.SANDS_COLORLESS);
		this.copy(Tags.Blocks.SANDSTONE_UNCOLORED_BLOCKS, Tags.Items.SANDSTONE_UNCOLORED_BLOCKS);
		this.copy(Tags.Blocks.SANDSTONE_UNCOLORED_STAIRS, Tags.Items.SANDSTONE_UNCOLORED_STAIRS);
		this.copy(Tags.Blocks.SANDSTONE_UNCOLORED_SLABS, Tags.Items.SANDSTONE_UNCOLORED_SLABS);
		this.copy(Tags.Blocks.SANDS_RED, Tags.Items.SANDS_RED);
		this.copy(Tags.Blocks.SANDSTONE_RED_BLOCKS, Tags.Items.SANDSTONE_RED_BLOCKS);
		this.copy(Tags.Blocks.SANDSTONE_RED_STAIRS, Tags.Items.SANDSTONE_RED_STAIRS);
		this.copy(Tags.Blocks.SANDSTONE_RED_SLABS, Tags.Items.SANDSTONE_RED_SLABS);
		this.copy(Tags.Blocks.GLASS_BLOCKS_CHEAP, Tags.Items.GLASS_BLOCKS_CHEAP);
		this.copy(Tags.Blocks.GLASS_BLOCKS_COLORLESS, Tags.Items.GLASS_BLOCKS_COLORLESS);
		this.copy(Tags.Blocks.GLASS_PANES_COLORLESS, Tags.Items.GLASS_PANES_COLORLESS);
		this.copy(Tags.Blocks.STORAGE_BLOCKS, Tags.Items.STORAGE_BLOCKS);
		this.tag(Tags.Items.BOOKSHELVES).add(ROSEWOOD_BOOKSHELF.get().asItem(), MORADO_BOOKSHELF.get().asItem(), YUCCA_BOOKSHELF.get().asItem(), KOUSA_BOOKSHELF.get().asItem(), ASPEN_BOOKSHELF.get().asItem(), GRIMWOOD_BOOKSHELF.get().asItem(), LAUREL_BOOKSHELF.get().asItem());
		this.tag(Tags.Items.FOODS_FRUIT).addTags(FOODS_PASSION_FRUIT, FOODS_DRAGON_FRUIT, FOODS_ORANGE).add(YUCCA_FRUIT.get(), ROASTED_YUCCA_FRUIT.get(), SHIMMERING_PASSION_FRUIT.get(), GOLDEN_DRAGON_FRUIT.get());
		this.tag(Tags.Items.FOODS_BERRY).addTag(FOODS_CURRANT);
		this.tag(Tags.Items.FOODS_GOLDEN).add(SHIMMERING_PASSION_FRUIT.get(), GOLDEN_DRAGON_FRUIT.get());
		this.tag(Tags.Items.FOODS_VEGETABLE).add(ALOE_LEAVES.get());
		this.tag(Tags.Items.FOODS_EDIBLE_WHEN_PLACED).add(AtmosphericItems.YUCCA_GATEAU.get());
		this.tag(Tags.Items.FOODS).add(PASSION_FRUIT_TART.get(), PASSION_FRUIT_SORBET.get(), CANDIED_ORANGE_SLICES.get(), ORANGE_PUDDING.get(), ORANGE_SORBET.get(), CURRANT_MUFFIN.get(), ALOE_GEL_BOTTLE.get());
		this.tag(Tags.Items.ANIMAL_FOODS).addTag(COCHINEAL_FOOD).addTag(COCHINEAL_SUPER_LOVE_FOOD);
		this.tag(Tags.Items.BUCKETS_ENTITY_WATER).add(TETRA_BUCKET.get());
		this.tag(FOODS_PASSION_FRUIT).add(PASSION_FRUIT.get());
		this.tag(FOODS_CURRANT).add(CURRANT.get());
		this.tag(FOODS_DRAGON_FRUIT).add(DRAGON_FRUIT.get());
		this.tag(FOODS_ORANGE).add(AtmosphericItems.ORANGE.get());
		this.tag(Tags.Items.SEEDS).addTag(SEEDS_ALOE_VERA);
		this.tag(SEEDS_ALOE_VERA).add(ALOE_KERNELS.get());
		this.tag(ICE_CUBES);
	}
}