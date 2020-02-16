package com.bagel.atmospheric.core.registry;

import com.bagel.atmospheric.core.Atmospheric;

import net.minecraft.block.Block;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;

public class AtmosphericTags {
	public static final Tag<Block> PASSION_VINE_GROWABLE_ON = new BlockTags.Wrapper(new ResourceLocation(Atmospheric.MODID, "passion_vine_growable_on"));
	public static final Tag<Block> ROSEWOOD_LOGS = new BlockTags.Wrapper(new ResourceLocation(Atmospheric.MODID, "rosewood_logs"));
}
