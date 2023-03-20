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
	public static final TagKey<Item> LAUREL_LOGS = itemTag("laurel_logs");

	public static final TagKey<Item> TRAVERTINE = itemTag("travertine");
	public static final TagKey<Item> FRUITS = TagUtil.itemTag("forge", "fruits");
	public static final TagKey<Item> FRUITS_PASSIONFRUIT = TagUtil.itemTag("forge", "fruits/passionfruit");
	public static final TagKey<Item> FRUITS_CURRANT = TagUtil.itemTag("forge", "fruits/currant");
	public static final TagKey<Item> SEEDS_ALOE_VERA = TagUtil.itemTag("forge", "seeds/aloe_vera");

	private static TagKey<Item> itemTag(String name) {
		return TagUtil.itemTag(Atmospheric.MOD_ID, name);
	}
}
