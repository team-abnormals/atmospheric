package com.teamabnormals.atmospheric.core.other.tags;

import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.blueprint.core.util.TagUtil;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

public class AtmosphericBiomeTags {
	public static final TagKey<Biome> IS_RAINFOREST = biomeTag("is_rainforest");
	public static final TagKey<Biome> IS_DUNES = biomeTag("is_dunes");
	public static final TagKey<Biome> IS_SCRUBLAND = biomeTag("is_scrubland");
	public static final TagKey<Biome> IS_COCHINEAL_HABITAT = biomeTag("is_cochineal_habitat");
	public static final TagKey<Biome> HAS_ARID_GARDEN = biomeTag("has_structure/arid_garden");
	public static final TagKey<Biome> HAS_KOUSA_SANCTUM = biomeTag("has_structure/kousa_sanctum");
	public static final TagKey<Biome> HAS_VILLAGE_SCRUBLAND = biomeTag("has_structure/village_scrubland");
	public static final TagKey<Biome> ONLY_ALLOWS_YELLOW_RABBITS = biomeTag("only_allows_yellow_rabbits");

	private static TagKey<Biome> biomeTag(String name) {
		return TagUtil.biomeTag(Atmospheric.MOD_ID, name);
	}
}
