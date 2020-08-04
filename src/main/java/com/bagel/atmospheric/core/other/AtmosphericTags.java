package com.bagel.atmospheric.core.other;

import com.bagel.atmospheric.core.Atmospheric;

import net.minecraft.block.Block;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag.INamedTag;

public class AtmosphericTags {
	public static final INamedTag<Block> PASSION_VINE_GROWABLE_ON = BlockTags.makeWrapperTag(Atmospheric.MODID + ":passion_vine_growable_on");
	public static final INamedTag<Block> YUCCA_PLANTABLE_ON = BlockTags.makeWrapperTag(Atmospheric.MODID + ":yucca_plantable_on");
	public static final INamedTag<Block> YUCCA_LOGS = BlockTags.makeWrapperTag(Atmospheric.MODID + ":yucca_logs");
}
