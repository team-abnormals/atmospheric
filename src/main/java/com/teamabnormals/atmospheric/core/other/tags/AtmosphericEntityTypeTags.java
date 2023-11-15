package com.teamabnormals.atmospheric.core.other.tags;

import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.blueprint.core.util.TagUtil;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;

public class AtmosphericEntityTypeTags {
	public static final TagKey<EntityType<?>> YUCCA_IMMUNE = entityTypeTag("yucca_immune");
	public static final TagKey<EntityType<?>> CACTUS_IMMUNE = entityTypeTag("cactus_immune");

	private static TagKey<EntityType<?>> entityTypeTag(String name) {
		return TagUtil.entityTypeTag(Atmospheric.MOD_ID, name);
	}
}
