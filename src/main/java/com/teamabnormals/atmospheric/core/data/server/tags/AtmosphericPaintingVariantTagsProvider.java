package com.teamabnormals.atmospheric.core.data.server.tags;

import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.atmospheric.core.registry.AtmosphericPaintingVariants;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.PaintingVariantTagsProvider;
import net.minecraft.tags.PaintingVariantTags;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import java.util.concurrent.CompletableFuture;

public class AtmosphericPaintingVariantTagsProvider extends PaintingVariantTagsProvider {

	public AtmosphericPaintingVariantTagsProvider(PackOutput output, CompletableFuture<Provider> provider, ExistingFileHelper helper) {
		super(output, provider, Atmospheric.MOD_ID, helper);
	}

	@Override
	public void addTags(Provider provider) {
		TagAppender<PaintingVariant> appender = this.tag(PaintingVariantTags.PLACEABLE);
		for (RegistryObject<PaintingVariant> variant : AtmosphericPaintingVariants.PAINTING_VARIANTS.getEntries()) {
			appender.add(variant.getKey());
		}
	}
}