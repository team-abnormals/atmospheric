package com.teamabnormals.atmospheric.core.data.server.tags;

import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.atmospheric.core.other.tags.AtmosphericEntityTypeTags;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.EntityType;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

import static com.teamabnormals.atmospheric.core.registry.AtmosphericEntityTypes.*;

public class AtmosphericEntityTypeTagsProvider extends EntityTypeTagsProvider {

	public AtmosphericEntityTypeTagsProvider(PackOutput output, CompletableFuture<Provider> provider, ExistingFileHelper helper) {
		super(output, provider, Atmospheric.MOD_ID, helper);
	}

	@Override
	public void addTags(Provider provider) {
		this.tag(EntityTypeTags.ARTHROPOD).add(COCHINEAL.get());
		this.tag(EntityTypeTags.CAN_BREATHE_UNDER_WATER).add(TETRA.get());
		this.tag(EntityTypeTags.AXOLOTL_HUNT_TARGETS).add(TETRA.get());
		this.tag(EntityTypeTags.NOT_SCARY_FOR_PUFFERFISH).add(TETRA.get());
		this.tag(EntityTypeTags.AQUATIC).add(TETRA.get());
		this.tag(AtmosphericEntityTypeTags.YUCCA_IMMUNE).add(EntityType.BEE, EntityType.HUSK, EntityType.CAMEL, COCHINEAL.get());
		this.tag(AtmosphericEntityTypeTags.CACTUS_IMMUNE).add(EntityType.HUSK, EntityType.CAMEL, COCHINEAL.get());
		this.tag(EntityTypeTags.IMPACT_PROJECTILES).add(PASSION_FRUIT_SEED.get());
	}
}