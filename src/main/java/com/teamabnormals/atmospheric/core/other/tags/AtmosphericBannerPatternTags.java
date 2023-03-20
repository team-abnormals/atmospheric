package com.teamabnormals.atmospheric.core.other.tags;

import com.teamabnormals.atmospheric.core.Atmospheric;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.entity.BannerPattern;

public class AtmosphericBannerPatternTags {
	public static final TagKey<BannerPattern> PATTERN_ITEM_COCHINEAL = bannerPatternTag("pattern_item/cochineal");

	private static TagKey<BannerPattern> bannerPatternTag(String name) {
		return TagKey.create(Registry.BANNER_PATTERN_REGISTRY, new ResourceLocation(Atmospheric.MOD_ID, name));
	}
}