package com.teamabnormals.atmospheric.core.data.server.tags;

import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.atmospheric.core.other.tags.AtmosphericBannerPatternTags;
import com.teamabnormals.atmospheric.core.registry.datapack.AtmosphericBannerPatterns;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BannerPatternTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class AtmosphericBannerPatternTagsProvider extends BannerPatternTagsProvider {

	public AtmosphericBannerPatternTagsProvider(PackOutput output, CompletableFuture<Provider> provider, ExistingFileHelper helper) {
		super(output, provider, Atmospheric.MOD_ID, helper);
	}

	@Override
	public void addTags(Provider provider) {
		this.tag(AtmosphericBannerPatternTags.PATTERN_ITEM_COCHINEAL).add(AtmosphericBannerPatterns.COCHINEAL);
	}
}