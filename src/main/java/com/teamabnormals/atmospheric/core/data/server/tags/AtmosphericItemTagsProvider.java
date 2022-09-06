package com.teamabnormals.atmospheric.core.data.server.tags;

import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.atmospheric.core.other.tags.AtmosphericBlockTags;
import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.data.ExistingFileHelper;

import static com.teamabnormals.atmospheric.core.other.tags.AtmosphericItemTags.*;
import static com.teamabnormals.atmospheric.core.registry.AtmosphericItems.*;

public class AtmosphericItemTagsProvider extends ItemTagsProvider {

	public AtmosphericItemTagsProvider(DataGenerator generator, BlockTagsProvider blockTags, ExistingFileHelper existingFileHelper) {
		super(generator, blockTags, Atmospheric.MOD_ID, existingFileHelper);
	}

	@Override
	public void addTags() {
		this.copy(AtmosphericBlockTags.ROSEWOOD_LOGS, ROSEWOOD_LOGS);
		this.copy(AtmosphericBlockTags.MORADO_LOGS, MORADO_LOGS);
		this.copy(AtmosphericBlockTags.YUCCA_LOGS, YUCCA_LOGS);
		this.copy(AtmosphericBlockTags.ASPEN_LOGS, ASPEN_LOGS);
		this.copy(AtmosphericBlockTags.KOUSA_LOGS, KOUSA_LOGS);
		this.copy(AtmosphericBlockTags.GRIMWOOD_LOGS, GRIMWOOD_LOGS);

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
		this.copy(BlockTags.SMALL_FLOWERS, ItemTags.SMALL_FLOWERS);
		this.copy(BlockTags.TALL_FLOWERS, ItemTags.TALL_FLOWERS);
		this.copy(BlockTags.FLOWERS, ItemTags.FLOWERS);
		this.copy(BlockTags.STANDING_SIGNS, ItemTags.SIGNS);
		this.tag(ItemTags.BOATS).add(ROSEWOOD_BOAT.get(), MORADO_BOAT.get(), YUCCA_BOAT.get(), KOUSA_BOAT.get(), ASPEN_BOAT.get(), GRIMWOOD_BOAT.get());
		this.tag(ItemTags.PIGLIN_LOVED).add(SHIMMERING_PASSIONFRUIT.get(), AtmosphericBlocks.SHIMMERING_PASSIONFRUIT_CRATE.get().asItem());

	}
}