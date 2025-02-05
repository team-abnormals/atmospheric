package com.teamabnormals.atmospheric.core.data.client;

import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.atmospheric.core.registry.builtin.AtmosphericTrimMaterials;
import com.teamabnormals.atmospheric.core.registry.builtin.AtmosphericTrimPatterns;
import com.teamabnormals.blueprint.core.api.BlueprintTrims;
import com.teamabnormals.clayworks.core.api.ClayworksTrims;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.SpriteSourceProvider;

public final class AtmosphericSpriteSourceProvider extends SpriteSourceProvider {

	public AtmosphericSpriteSourceProvider(PackOutput output, ExistingFileHelper helper) {
		super(output, helper, Atmospheric.MOD_ID);
	}

	@Override
	protected void addSources() {
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