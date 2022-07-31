package com.minecraftabnormals.atmospheric.core.other;

import com.minecraftabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.blueprint.core.util.TagUtil;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class AtmosphericTags {
	public static final TagKey<Block> PASSION_VINE_GROWABLE_ON = blockTag("passion_vine_growable_on");
	public static final TagKey<Block> YUCCA_PLANTABLE_ON = blockTag("yucca_plantable_on");
	public static final TagKey<Block> YUCCA_LOGS = blockTag("yucca_logs");

	private static TagKey<Block> blockTag(String name) {
		return TagUtil.blockTag(Atmospheric.MOD_ID, name);
	}
}
