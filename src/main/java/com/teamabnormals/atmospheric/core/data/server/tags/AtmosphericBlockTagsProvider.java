package com.teamabnormals.atmospheric.core.data.server.tags;

import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.atmospheric.core.other.tags.AtmosphericBlockTags;
import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class AtmosphericBlockTagsProvider extends BlockTagsProvider {

	public AtmosphericBlockTagsProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
		super(generator, Atmospheric.MOD_ID, existingFileHelper);
	}

	@Override
	public void addTags() {
		this.tag(AtmosphericBlockTags.ROSEWOOD_LOGS).add(AtmosphericBlocks.ROSEWOOD_LOG.get(), AtmosphericBlocks.ROSEWOOD.get(), AtmosphericBlocks.STRIPPED_ROSEWOOD_LOG.get(), AtmosphericBlocks.ROSEWOOD.get());
		this.tag(AtmosphericBlockTags.MORADO_LOGS).add(AtmosphericBlocks.MORADO_LOG.get(), AtmosphericBlocks.MORADO_WOOD.get(), AtmosphericBlocks.STRIPPED_MORADO_LOG.get(), AtmosphericBlocks.MORADO_WOOD.get());
		this.tag(AtmosphericBlockTags.YUCCA_LOGS).add(AtmosphericBlocks.YUCCA_LOG.get(), AtmosphericBlocks.YUCCA_WOOD.get(), AtmosphericBlocks.STRIPPED_YUCCA_LOG.get(), AtmosphericBlocks.YUCCA_WOOD.get());
		this.tag(AtmosphericBlockTags.ASPEN_LOGS).add(AtmosphericBlocks.ASPEN_LOG.get(), AtmosphericBlocks.ASPEN_WOOD.get(), AtmosphericBlocks.STRIPPED_ASPEN_LOG.get(), AtmosphericBlocks.ASPEN_WOOD.get(), AtmosphericBlocks.WATCHFUL_ASPEN_LOG.get(), AtmosphericBlocks.WATCHFUL_ASPEN_WOOD.get(), AtmosphericBlocks.CRUSTOSE_LOG.get(), AtmosphericBlocks.CRUSTOSE_WOOD.get());
		this.tag(AtmosphericBlockTags.KOUSA_LOGS).add(AtmosphericBlocks.KOUSA_LOG.get(), AtmosphericBlocks.KOUSA_WOOD.get(), AtmosphericBlocks.STRIPPED_KOUSA_LOG.get(), AtmosphericBlocks.KOUSA_WOOD.get());
		this.tag(AtmosphericBlockTags.GRIMWOOD_LOGS).add(AtmosphericBlocks.GRIMWOOD_LOG.get(), AtmosphericBlocks.GRIMWOOD.get(), AtmosphericBlocks.STRIPPED_GRIMWOOD_LOG.get(), AtmosphericBlocks.GRIMWOOD.get());

		this.tag(AtmosphericBlockTags.MONKEY_BRUSH_PLACEABLE).addTag(BlockTags.DIRT).addTag(BlockTags.LOGS_THAT_BURN);
		this.tag(AtmosphericBlockTags.PASSION_VINE_GROWABLE_ON).add(AtmosphericBlocks.ROSEWOOD_LEAVES.get()).addTag(AtmosphericBlockTags.ROSEWOOD_LOGS);
		this.tag(AtmosphericBlockTags.ALOE_PLACEABLE).addTag(BlockTags.SAND);
		this.tag(AtmosphericBlockTags.TALL_ALOE_GROWABLE_ON).addTag(Tags.Blocks.SAND_COLORLESS);
		this.tag(AtmosphericBlockTags.YUCCA_PLACEABLE).addTag(BlockTags.SAND).addTag(BlockTags.DIRT);
		this.tag(AtmosphericBlockTags.YUCCA_FLOWER_PLACEABLE).add(Blocks.CACTUS, AtmosphericBlocks.YUCCA_LEAVES.get(), AtmosphericBlocks.YUCCA_HEDGE.get());
		this.tag(AtmosphericBlockTags.BARREL_CACTUS_PLACEABLE).addTag(BlockTags.SAND);
	}
}