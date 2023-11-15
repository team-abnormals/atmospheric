package com.teamabnormals.atmospheric.core.data.server.tags;

import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.atmospheric.core.other.tags.AtmosphericEntityTypeTags;
import com.teamabnormals.atmospheric.core.registry.AtmosphericEntityTypes;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.common.data.ExistingFileHelper;

public class AtmosphericEntityTypeTagsProvider extends EntityTypeTagsProvider {

	public AtmosphericEntityTypeTagsProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
		super(generator, Atmospheric.MOD_ID, existingFileHelper);
	}

	@Override
	public void addTags() {
		this.tag(AtmosphericEntityTypeTags.YUCCA_IMMUNE).add(EntityType.BEE, EntityType.HUSK, AtmosphericEntityTypes.COCHINEAL.get());
		this.tag(AtmosphericEntityTypeTags.CACTUS_IMMUNE).add(EntityType.HUSK, AtmosphericEntityTypes.COCHINEAL.get());
		this.tag(EntityTypeTags.IMPACT_PROJECTILES).add(AtmosphericEntityTypes.PASSION_FRUIT_SEED.get());
	}
}