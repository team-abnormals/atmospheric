package com.teamabnormals.atmospheric.core.data.server.tags;

import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.atmospheric.core.registry.AtmosphericStructures;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.StructureTagsProvider;
import net.minecraft.tags.StructureTags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class AtmosphericStructureTagsProvider extends StructureTagsProvider {

	public AtmosphericStructureTagsProvider(DataGenerator generator, ExistingFileHelper helper) {
		super(generator, Atmospheric.MOD_ID, helper);
	}

	@Override
	public void addTags() {
		this.tag(StructureTags.VILLAGE).add(AtmosphericStructures.VILLAGE_SCRUBLAND);
	}
}