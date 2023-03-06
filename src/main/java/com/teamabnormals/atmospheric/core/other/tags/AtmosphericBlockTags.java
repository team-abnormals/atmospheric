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
	public static final TagKey<Block> LAUREL_LOGS = blockTag("laurel_logs");

	public static final TagKey<Block> TRAVERTINE = blockTag("travertine");
	public static final TagKey<Block> MONKEY_BRUSH_PLACEABLE = blockTag("monkey_brush_placeable");
	public static final TagKey<Block> PASSION_VINE_GROWABLE_ON = blockTag("passion_vine_growable_on");
	public static final TagKey<Block> ALOE_PLACEABLE = blockTag("aloe_placeable");
	public static final TagKey<Block> TALL_ALOE_GROWABLE_ON = blockTag("tall_aloe_growable_on");
	public static final TagKey<Block> YUCCA_PLACEABLE = blockTag("yucca_placeable");
	public static final TagKey<Block> YUCCA_FLOWER_PLACEABLE = blockTag("yucca_flower_placeable");
	public static final TagKey<Block> BARREL_CACTUS_PLACEABLE = blockTag("barrel_cactus_placeable");
	public static final TagKey<Block> SNOWY_BAMBOO_PLANTABLE_ON = blockTag("snowy_bamboo_plantable_on");
	public static final TagKey<Block> GOLDEN_GROWTHS_PLACEABLE = blockTag("golden_growths_placeable");

	private static TagKey<Block> blockTag(String name) {
		return TagUtil.blockTag(Atmospheric.MOD_ID, name);
	}
}
