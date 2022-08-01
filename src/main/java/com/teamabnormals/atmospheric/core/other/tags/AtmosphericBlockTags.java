package com.teamabnormals.atmospheric.core.other.tags;

import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.blueprint.core.util.TagUtil;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class AtmosphericBlockTags {
	public static final TagKey<Block> ROSEWOOD_LOGS = blockTag("rosewood_logs");
	public static final TagKey<Block> MORADO_LOGS = blockTag("morado_logs");
	public static final TagKey<Block> YUCCA_LOGS = blockTag("yucca_logs");
	public static final TagKey<Block> ASPEN_LOGS = blockTag("aspen_logs");
	public static final TagKey<Block> KOUSA_LOGS = blockTag("kousa_logs");
	public static final TagKey<Block> GRIMWOOD_LOGS = blockTag("grimwood_logs");

	public static final TagKey<Block> PASSION_VINE_GROWABLE_ON = blockTag("passion_vine_growable_on");
	public static final TagKey<Block> YUCCA_PLANTABLE_ON = blockTag("yucca_plantable_on");

	private static TagKey<Block> blockTag(String name) {
		return TagUtil.blockTag(Atmospheric.MOD_ID, name);
	}
}
