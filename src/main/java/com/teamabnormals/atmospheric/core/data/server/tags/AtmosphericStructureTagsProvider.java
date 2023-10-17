package com.teamabnormals.atmospheric.core.data.server.tags;

import com.teamabnormals.atmospheric.core.Atmospheric;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.StructureTagsProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.StructureTags;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraftforge.common.data.ExistingFileHelper;

public class AtmosphericStructureTagsProvider extends StructureTagsProvider {

	public static final ResourceKey<Structure> VILLAGE_SCRUBLAND = ResourceKey.create(Registry.STRUCTURE_REGISTRY, new ResourceLocation(Atmospheric.MOD_ID, "village_scrubland"));

	public AtmosphericStructureTagsProvider(DataGenerator generator, ExistingFileHelper helper) {
		super(generator, Atmospheric.MOD_ID, helper);
	}

	@Override
	public void addTags() {
		this.tag(StructureTags.VILLAGE).add(VILLAGE_SCRUBLAND);
	}
}