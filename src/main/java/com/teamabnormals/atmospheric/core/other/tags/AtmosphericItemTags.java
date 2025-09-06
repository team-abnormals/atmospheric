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
	public static final TagKey<Item> SMELTS_TO_ARID_GLASS = itemTag("smelts_to_arid_glass");

	public static final TagKey<Item> MONKEY_BRUSH = itemTag("monkey_brush");
	public static final TagKey<Item> TRAVERTINE = itemTag("travertine");
	public static final TagKey<Item> FOODS_PASSION_FRUIT = TagUtil.itemTag("c", "foods/passion_fruit");
	public static final TagKey<Item> FOODS_CURRANT = TagUtil.itemTag("c", "foods/currant");
	public static final TagKey<Item> FOODS_DRAGON_FRUIT = TagUtil.itemTag("c", "foods/dragon_fruit");
	public static final TagKey<Item> FOODS_ORANGE = TagUtil.itemTag("c", "foods/orange");
	public static final TagKey<Item> SEEDS_ALOE_VERA = TagUtil.itemTag("c", "seeds/aloe_vera");
	public static final TagKey<Item> ICE_CUBES = TagUtil.itemTag("c", "ice_cubes");

	public static final TagKey<Item> COCHINEAL_FOOD = itemTag("cochineal_food");
	public static final TagKey<Item> COCHINEAL_SUPER_LOVE_FOOD = itemTag("cochineal_super_love_food");

	private static TagKey<Item> itemTag(String name) {
		return TagUtil.itemTag(Atmospheric.MOD_ID, name);
	}
}
