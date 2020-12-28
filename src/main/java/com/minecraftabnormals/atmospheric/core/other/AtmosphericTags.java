package com.minecraftabnormals.atmospheric.core.other;

import com.minecraftabnormals.atmospheric.core.Atmospheric;
import net.minecraft.block.Block;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag.INamedTag;

public class AtmosphericTags {
	public static final INamedTag<Block> PASSION_VINE_GROWABLE_ON = BlockTags.makeWrapperTag(Atmospheric.MOD_ID + ":passion_vine_growable_on");
	public static final INamedTag<Block> YUCCA_PLANTABLE_ON = BlockTags.makeWrapperTag(Atmospheric.MOD_ID + ":yucca_plantable_on");
	public static final INamedTag<Block> YUCCA_LOGS = BlockTags.makeWrapperTag(Atmospheric.MOD_ID + ":yucca_logs");
}
