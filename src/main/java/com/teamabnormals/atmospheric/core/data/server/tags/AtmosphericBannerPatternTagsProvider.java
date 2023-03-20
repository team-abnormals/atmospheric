package com.teamabnormals.atmospheric.core.data.server.tags;

import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.atmospheric.core.other.tags.AtmosphericBannerPatternTags;
import com.teamabnormals.atmospheric.core.registry.AtmosphericBannerPatterns;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BannerPatternTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class AtmosphericBannerPatternTagsProvider extends BannerPatternTagsProvider {

	public AtmosphericBannerPatternTagsProvider(DataGenerator generator, ExistingFileHelper helper) {
		super(generator, Atmospheric.MOD_ID, helper);
	}

	@Override
	public void addTags() {
		this.tag(AtmosphericBannerPatternTags.PATTERN_ITEM_COCHINEAL).add(AtmosphericBannerPatterns.COCHINEAL.get());
	}
}