package com.teamabnormals.atmospheric.core.other.tags;

import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.blueprint.core.util.TagUtil;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class AtmosphericItemTags {
	public static final TagKey<Item> ROSEWOOD_LOGS = itemTag("rosewood_logs");
	public static final TagKey<Item> MORADO_LOGS = itemTag("morado_logs");
	public static final TagKey<Item> YUCCA_LOGS = itemTag("yucca_logs");
	public static final TagKey<Item> ASPEN_LOGS = itemTag("aspen_logs");
	public static final TagKey<Item> KOUSA_LOGS = itemTag("kousa_logs");
	public static final TagKey<Item> GRIMWOOD_LOGS = itemTag("grimwood_logs");

	private static TagKey<Item> itemTag(String name) {
		return TagUtil.itemTag(Atmospheric.MOD_ID, name);
	}
}
