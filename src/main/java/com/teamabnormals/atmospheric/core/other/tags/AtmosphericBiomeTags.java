package com.teamabnormals.atmospheric.core.other.tags;

import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.blueprint.core.util.TagUtil;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

public class AtmosphericBiomeTags {
	public static final TagKey<Biome> IS_RAINFOREST = biomeTag("is_rainforest");
	public static final TagKey<Biome> IS_DUNES = biomeTag("is_dunes");
	public static final TagKey<Biome> HAS_ARID_SHRINE = biomeTag("has_structure/arid_shrine");

	private static TagKey<Biome> biomeTag(String name) {
		return TagUtil.biomeTag(Atmospheric.MOD_ID, name);
	}
}
