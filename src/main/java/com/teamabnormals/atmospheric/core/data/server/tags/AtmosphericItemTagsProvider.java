package com.teamabnormals.atmospheric.core.data.server.tags;

import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.atmospheric.core.other.tags.AtmosphericBlockTags;
import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import com.teamabnormals.atmospheric.core.registry.AtmosphericItems;
import com.teamabnormals.blueprint.core.other.tags.BlueprintBlockTags;
import com.teamabnormals.blueprint.core.other.tags.BlueprintItemTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

import static com.teamabnormals.atmospheric.core.other.tags.AtmosphericItemTags.*;
import static com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks.*;

public class AtmosphericItemTagsProvider extends ItemTagsProvider {

	public AtmosphericItemTagsProvider(DataGenerator generator, BlockTagsProvider blockTags, ExistingFileHelper existingFileHelper) {
		super(generator, blockTags, Atmospheric.MOD_ID, existingFileHelper);
	}

	@Override
	public void addTags() {
		this.tag(COCHINEAL_FOOD).add(AtmosphericItems.DRAGON_FRUIT.get()).addTag(COCHINEAL_BREEDING_FOOD);
		this.tag(COCHINEAL_BREEDING_FOOD).add(AtmosphericItems.GOLDEN_DRAGON_FRUIT.get());

		this.copy(AtmosphericBlockTags.ROSEWOOD_LOGS, ROSEWOOD_LOGS);
		this.copy(AtmosphericBlockTags.MORADO_LOGS, MORADO_LOGS);
		this.copy(AtmosphericBlockTags.YUCCA_LOGS, YUCCA_LOGS);
		this.copy(AtmosphericBlockTags.ASPEN_LOGS, ASPEN_LOGS);
		this.copy(AtmosphericBlockTags.KOUSA_LOGS, KOUSA_LOGS);
		this.copy(AtmosphericBlockTags.GRIMWOOD_LOGS, GRIMWOOD_LOGS);
		this.copy(AtmosphericBlockTags.LAUREL_LOGS, LAUREL_LOGS);
		this.copy(AtmosphericBlockTags.TRAVERTINE, TRAVERTINE);

		this.copy(BlockTags.PLANKS, ItemTags.PLANKS);
		this.copy(BlockTags.WOODEN_BUTTONS, ItemTags.WOODEN_BUTTONS);
		this.copy(BlockTags.WOODEN_DOORS, ItemTags.WOODEN_DOORS);
		this.copy(BlockTags.WOODEN_STAIRS, ItemTags.WOODEN_STAIRS);
		this.copy(BlockTags.WOODEN_SLABS, ItemTags.WOODEN_SLABS);
		this.copy(BlockTags.WOODEN_FENCES, ItemTags.WOODEN_FENCES);
		this.copy(BlockTags.WOODEN_PRESSURE_PLATES, ItemTags.WOODEN_PRESSURE_PLATES);
		this.copy(BlockTags.SAPLINGS, ItemTags.SAPLINGS);
		this.copy(BlockTags.LOGS_THAT_BURN, ItemTags.LOGS_THAT_BURN);
		this.copy(BlockTags.SAND, ItemTags.SAND);
		this.copy(BlockTags.SLABS, ItemTags.SLABS);
		this.copy(BlockTags.WALLS, ItemTags.WALLS);
		this.copy(BlockTags.STAIRS, ItemTags.STAIRS);
		this.copy(BlockTags.LEAVES, ItemTags.LEAVES);
		this.copy(BlockTags.WOODEN_TRAPDOORS, ItemTags.WOODEN_TRAPDOORS);
		this.tag(MONKEY_BRUSH).add(WARM_MONKEY_BRUSH.get().asItem(), HOT_MONKEY_BRUSH.get().asItem(), SCALDING_MONKEY_BRUSH.get().asItem());
		this.copy(BlockTags.SMALL_FLOWERS, ItemTags.SMALL_FLOWERS);
		this.copy(BlockTags.TALL_FLOWERS, ItemTags.TALL_FLOWERS);
		this.copy(BlockTags.FLOWERS, ItemTags.FLOWERS);
		this.copy(BlockTags.STANDING_SIGNS, ItemTags.SIGNS);
		this.copy(BlockTags.OVERWORLD_NATURAL_LOGS, ItemTags.OVERWORLD_NATURAL_LOGS);
		this.copy(BlockTags.DIRT, ItemTags.DIRT);
		this.tag(ItemTags.FLOWERS).add(AtmosphericItems.YELLOW_BLOSSOMS.get());
		this.tag(ItemTags.BOATS).add(AtmosphericItems.ROSEWOOD_BOAT.getFirst().get(), AtmosphericItems.MORADO_BOAT.getFirst().get(), AtmosphericItems.YUCCA_BOAT.getFirst().get(), AtmosphericItems.KOUSA_BOAT.getFirst().get(), AtmosphericItems.ASPEN_BOAT.getFirst().get(), AtmosphericItems.GRIMWOOD_BOAT.getFirst().get(), AtmosphericItems.LAUREL_BOAT.getFirst().get());
		this.tag(ItemTags.CHEST_BOATS).add(AtmosphericItems.ROSEWOOD_BOAT.getSecond().get(), AtmosphericItems.MORADO_BOAT.getSecond().get(), AtmosphericItems.YUCCA_BOAT.getSecond().get(), AtmosphericItems.KOUSA_BOAT.getSecond().get(), AtmosphericItems.ASPEN_BOAT.getSecond().get(), AtmosphericItems.GRIMWOOD_BOAT.getSecond().get(), AtmosphericItems.LAUREL_BOAT.getSecond().get());
		this.tag(BlueprintItemTags.FURNACE_BOATS).add(AtmosphericItems.ROSEWOOD_FURNACE_BOAT.get(), AtmosphericItems.MORADO_FURNACE_BOAT.get(), AtmosphericItems.YUCCA_FURNACE_BOAT.get(), AtmosphericItems.KOUSA_FURNACE_BOAT.get(), AtmosphericItems.ASPEN_FURNACE_BOAT.get(), AtmosphericItems.GRIMWOOD_FURNACE_BOAT.get(), AtmosphericItems.LAUREL_FURNACE_BOAT.get());
		this.tag(BlueprintItemTags.LARGE_BOATS).add(AtmosphericItems.LARGE_ROSEWOOD_BOAT.get(), AtmosphericItems.LARGE_MORADO_BOAT.get(), AtmosphericItems.LARGE_YUCCA_BOAT.get(), AtmosphericItems.LARGE_KOUSA_BOAT.get(), AtmosphericItems.LARGE_ASPEN_BOAT.get(), AtmosphericItems.LARGE_GRIMWOOD_BOAT.get(), AtmosphericItems.LARGE_LAUREL_BOAT.get());
		this.tag(ItemTags.PIGLIN_LOVED).add(AtmosphericItems.SHIMMERING_PASSION_FRUIT.get(), AtmosphericBlocks.SHIMMERING_PASSION_FRUIT_CRATE.get().asItem());
		this.tag(ItemTags.STONE_TOOL_MATERIALS).addTag(TRAVERTINE);
		this.tag(ItemTags.STONE_CRAFTING_MATERIALS).addTag(TRAVERTINE);

		this.copy(Tags.Blocks.CHESTS_WOODEN, Tags.Items.CHESTS_WOODEN);
		this.copy(Tags.Blocks.CHESTS_TRAPPED, Tags.Items.CHESTS_TRAPPED);
		this.copy(Tags.Blocks.FENCE_GATES_WOODEN, Tags.Items.FENCE_GATES_WOODEN);
		this.copy(Tags.Blocks.FENCES_WOODEN, Tags.Items.FENCES_WOODEN);
		this.copy(Tags.Blocks.SAND_COLORLESS, Tags.Items.SAND_COLORLESS);
		this.copy(Tags.Blocks.SAND_RED, Tags.Items.SAND_RED);
		this.copy(Tags.Blocks.SANDSTONE, Tags.Items.SANDSTONE);
		this.tag(Tags.Items.BOOKSHELVES).add(ROSEWOOD_BOOKSHELF.get().asItem(), MORADO_BOOKSHELF.get().asItem(), YUCCA_BOOKSHELF.get().asItem(), KOUSA_BOOKSHELF.get().asItem(), ASPEN_BOOKSHELF.get().asItem(), GRIMWOOD_BOOKSHELF.get().asItem(), LAUREL_BOOKSHELF.get().asItem());
		this.tag(FRUITS).addTags(FRUITS_PASSION_FRUIT, FRUITS_CURRANT, FRUITS_DRAGON_FRUIT, FRUITS_ORANGE);
		this.tag(FRUITS_PASSION_FRUIT).add(AtmosphericItems.PASSION_FRUIT.get());
		this.tag(FRUITS_CURRANT).add(AtmosphericItems.CURRANT.get());
		this.tag(FRUITS_DRAGON_FRUIT).add(AtmosphericItems.DRAGON_FRUIT.get());
		this.tag(FRUITS_ORANGE).add(AtmosphericItems.ORANGE.get());
		this.tag(Tags.Items.SEEDS).addTag(SEEDS_ALOE_VERA);
		this.tag(SEEDS_ALOE_VERA).add(AtmosphericItems.ALOE_KERNELS.get());

		this.copy(BlueprintBlockTags.LADDERS, BlueprintItemTags.LADDERS);
		this.copy(BlueprintBlockTags.HEDGES, BlueprintItemTags.HEDGES);
		this.copy(BlueprintBlockTags.VERTICAL_SLABS, BlueprintItemTags.VERTICAL_SLABS);
		this.copy(BlueprintBlockTags.WOODEN_VERTICAL_SLABS, BlueprintItemTags.WOODEN_VERTICAL_SLABS);
		this.tag(BlueprintItemTags.BOATABLE_CHESTS).add(ROSEWOOD_CHESTS.getFirst().get().asItem(), MORADO_CHESTS.getFirst().get().asItem(), YUCCA_CHESTS.getFirst().get().asItem(), KOUSA_CHESTS.getFirst().get().asItem(), ASPEN_CHESTS.getFirst().get().asItem(), GRIMWOOD_CHESTS.getFirst().get().asItem(), LAUREL_CHESTS.getFirst().get().asItem());
		this.tag(BlueprintItemTags.REVERTABLE_CHESTS).add(ROSEWOOD_CHESTS.getFirst().get().asItem(), MORADO_CHESTS.getFirst().get().asItem(), YUCCA_CHESTS.getFirst().get().asItem(), KOUSA_CHESTS.getFirst().get().asItem(), ASPEN_CHESTS.getFirst().get().asItem(), GRIMWOOD_CHESTS.getFirst().get().asItem(), LAUREL_CHESTS.getFirst().get().asItem());
	}
}