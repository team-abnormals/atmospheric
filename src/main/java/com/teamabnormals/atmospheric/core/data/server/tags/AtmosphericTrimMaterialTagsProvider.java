package com.teamabnormals.atmospheric.core.data.server.tags;

import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.atmospheric.core.registry.datapack.AtmosphericTrimMaterials;
import com.teamabnormals.blueprint.core.other.tags.BlueprintTrimMaterialTags;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class AtmosphericTrimMaterialTagsProvider extends TagsProvider<TrimMaterial> {

	public AtmosphericTrimMaterialTagsProvider(PackOutput output, CompletableFuture<Provider> provider, ExistingFileHelper helper) {
		super(output, Registries.TRIM_MATERIAL, provider, Atmospheric.MOD_ID, helper);
	}

	@Override
	public void addTags(Provider provider) {
		this.tag(BlueprintTrimMaterialTags.GENERATES_OVERRIDES).add(AtmosphericTrimMaterials.CARMINE);
	}
}