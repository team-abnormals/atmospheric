package com.teamabnormals.atmospheric.core.other.tags;

import com.teamabnormals.atmospheric.core.Atmospheric;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.entity.BannerPattern;

public class AtmosphericBannerPatternTags {
	public static final TagKey<BannerPattern> PATTERN_ITEM_COCHINEAL = bannerPatternTag("pattern_item/cochineal");

	private static TagKey<BannerPattern> bannerPatternTag(String name) {
		return TagKey.create(Registries.BANNER_PATTERN, Atmospheric.location(name));
	}
}