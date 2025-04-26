package com.teamabnormals.atmospheric.core.data.client;

import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.atmospheric.core.registry.datapack.AtmosphericTrimMaterials;
import com.teamabnormals.atmospheric.core.registry.datapack.AtmosphericTrimPatterns;
import com.teamabnormals.blueprint.core.api.BlueprintTrims;
import com.teamabnormals.clayworks.core.api.ClayworksTrims;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.data.SpriteSourceProvider;

import java.util.concurrent.CompletableFuture;

public final class AtmosphericSpriteSourceProvider extends SpriteSourceProvider {

	public AtmosphericSpriteSourceProvider(PackOutput output, CompletableFuture<Provider> provider, ExistingFileHelper helper) {
		super(output, provider, Atmospheric.MOD_ID, helper);
	}

	@Override
	protected void gather() {
		this.atlas(BlueprintTrims.ARMOR_TRIMS_ATLAS)
				.addSource(BlueprintTrims.materialPatternPermutations(AtmosphericTrimMaterials.CARMINE))
				.addSource(BlueprintTrims.patternPermutationsOfVanillaMaterials(
						AtmosphericTrimPatterns.APOSTLE,
						AtmosphericTrimPatterns.DRUID,
						AtmosphericTrimPatterns.PETRIFIED)
				);
		this.atlas(SpriteSourceProvider.BLOCKS_ATLAS).addSource(BlueprintTrims.materialPermutationsForItemLayers(AtmosphericTrimMaterials.CARMINE));
		this.atlas(ClayworksTrims.DECORATED_POT_ATLAS).addSource(ClayworksTrims.materialPatternPermutations(AtmosphericTrimMaterials.CARMINE));
	}
}