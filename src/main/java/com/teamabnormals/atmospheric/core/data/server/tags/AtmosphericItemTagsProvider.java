package com.teamabnormals.atmospheric.core.data.server.tags;

import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.atmospheric.core.other.tags.AtmosphericBlockTags;
import com.teamabnormals.atmospheric.core.other.tags.AtmosphericItemTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class AtmosphericItemTagsProvider extends ItemTagsProvider {

	public AtmosphericItemTagsProvider(DataGenerator generator, BlockTagsProvider blockTags, ExistingFileHelper existingFileHelper) {
		super(generator, blockTags, Atmospheric.MOD_ID, existingFileHelper);
	}

	@Override
	public void addTags() {
		this.copy(AtmosphericBlockTags.ROSEWOOD_LOGS, AtmosphericItemTags.ROSEWOOD_LOGS);
		this.copy(AtmosphericBlockTags.MORADO_LOGS, AtmosphericItemTags.MORADO_LOGS);
		this.copy(AtmosphericBlockTags.YUCCA_LOGS, AtmosphericItemTags.YUCCA_LOGS);
		this.copy(AtmosphericBlockTags.ASPEN_LOGS, AtmosphericItemTags.ASPEN_LOGS);
		this.copy(AtmosphericBlockTags.KOUSA_LOGS, AtmosphericItemTags.KOUSA_LOGS);
		this.copy(AtmosphericBlockTags.GRIMWOOD_LOGS, AtmosphericItemTags.GRIMWOOD_LOGS);

//		this.copy(BlockTags.SMALL_FLOWERS, ItemTags.SMALL_FLOWERS);
//		this.copy(BlockTags.SLABS, ItemTags.SLABS);
//		this.copy(BlockTags.STAIRS, ItemTags.STAIRS);
//		this.copy(BlockTags.WALLS, ItemTags.WALLS);
//		this.copy(BlueprintBlockTags.VERTICAL_SLABS, BlueprintItemTags.VERTICAL_SLABS);
//
//		this.copy(BlockTags.DOORS, ItemTags.DOORS);
//		this.copy(BlockTags.TRAPDOORS, ItemTags.TRAPDOORS);
	}
}