package com.teamabnormals.atmospheric.core.data.server.tags;

import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.atmospheric.core.other.tags.AtmosphericBiomeTags;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

import static com.teamabnormals.atmospheric.core.registry.datapack.AtmosphericBiomes.*;

public class AtmosphericBiomeTagsProvider extends BiomeTagsProvider {

	public AtmosphericBiomeTagsProvider(PackOutput output, CompletableFuture<Provider> provider, ExistingFileHelper helper) {
		super(output, provider, Atmospheric.MOD_ID, helper);
	}

	@Override
	public void addTags(Provider provider) {

		this.tag(BiomeTags.HAS_MINESHAFT).addTag(AtmosphericBiomeTags.IS_DUNES).add(ASPEN_PARKLAND, KOUSA_JUNGLE, SCRUBLAND, SNOWY_SCRUBLAND, SPINY_THICKET, LAUREL_FOREST, GRIMWOODS);
		this.tag(BiomeTags.IS_OVERWORLD).addTag(AtmosphericBiomeTags.IS_RAINFOREST).addTag(AtmosphericBiomeTags.IS_DUNES).add(ASPEN_PARKLAND, KOUSA_JUNGLE, SCRUBLAND, SNOWY_SCRUBLAND, SPINY_THICKET, LAUREL_FOREST, GRIMWOODS);
		this.tag(BiomeTags.STRONGHOLD_BIASED_TO).addTag(AtmosphericBiomeTags.IS_RAINFOREST).addTag(AtmosphericBiomeTags.IS_DUNES).add(ASPEN_PARKLAND, KOUSA_JUNGLE, SCRUBLAND, SNOWY_SCRUBLAND, SPINY_THICKET, LAUREL_FOREST, GRIMWOODS);
		this.tag(BiomeTags.HAS_TRAIL_RUINS).add(RAINFOREST, ASPEN_PARKLAND, KOUSA_JUNGLE);

		this.tag(BiomeTags.IS_FOREST).addTag(AtmosphericBiomeTags.IS_RAINFOREST).add(LAUREL_FOREST, GRIMWOODS);
		this.tag(Tags.Biomes.IS_DESERT).addTag(AtmosphericBiomeTags.IS_DUNES).add(SCRUBLAND, SNOWY_SCRUBLAND, SPINY_THICKET);
		this.tag(BiomeTags.HAS_RUINED_PORTAL_STANDARD).add(ASPEN_PARKLAND, KOUSA_JUNGLE, GRIMWOODS);
		this.tag(BiomeTags.HAS_RUINED_PORTAL_DESERT).addTag(AtmosphericBiomeTags.IS_DUNES).add(SCRUBLAND, SNOWY_SCRUBLAND, SPINY_THICKET);
		this.tag(BiomeTags.HAS_PILLAGER_OUTPOST).add(SCRUBLAND);
		this.tag(BiomeTags.SPAWNS_COLD_VARIANT_FROGS).add(KOUSA_JUNGLE, SNOWY_SCRUBLAND);
		this.tag(BiomeTags.SPAWNS_WARM_VARIANT_FROGS).addTag(AtmosphericBiomeTags.IS_RAINFOREST).addTag(AtmosphericBiomeTags.IS_DUNES).add(SCRUBLAND, SPINY_THICKET);
		this.tag(BiomeTags.WATER_ON_MAP_OUTLINES).add(RAINFOREST_BASIN, SPARSE_RAINFOREST_BASIN);
		this.tag(BiomeTags.SPAWNS_GOLD_RABBITS).addTag(AtmosphericBiomeTags.IS_DUNES).add(SPINY_THICKET, SCRUBLAND);
		this.tag(BiomeTags.SPAWNS_WHITE_RABBITS).add(KOUSA_JUNGLE, SNOWY_SCRUBLAND);
		this.tag(BiomeTags.SPAWNS_SNOW_FOXES).add(KOUSA_JUNGLE, SNOWY_SCRUBLAND);
		this.tag(BiomeTags.SNOW_GOLEM_MELTS).addTag(AtmosphericBiomeTags.IS_DUNES).add(SCRUBLAND, SPINY_THICKET);
		this.tag(BiomeTags.INCREASED_FIRE_BURNOUT).add(RAINFOREST);

		this.tag(AtmosphericBiomeTags.IS_RAINFOREST).add(RAINFOREST, SPARSE_RAINFOREST, RAINFOREST_BASIN, SPARSE_RAINFOREST_BASIN);
		this.tag(AtmosphericBiomeTags.IS_DUNES).add(DUNES, FLOURISHING_DUNES, ROCKY_DUNES, PETRIFIED_DUNES);
		this.tag(AtmosphericBiomeTags.IS_SCRUBLAND).add(SCRUBLAND, SNOWY_SCRUBLAND);
		this.tag(AtmosphericBiomeTags.IS_COCHINEAL_HABITAT).addTag(AtmosphericBiomeTags.IS_SCRUBLAND).add(SPINY_THICKET);
		this.tag(AtmosphericBiomeTags.HAS_ARID_GARDEN).addTag(AtmosphericBiomeTags.IS_DUNES);
		this.tag(AtmosphericBiomeTags.HAS_VILLAGE_SCRUBLAND).add(SCRUBLAND);
		this.tag(AtmosphericBiomeTags.HAS_KOUSA_SANCTUM).add(KOUSA_JUNGLE);
		this.tag(AtmosphericBiomeTags.SPAWNS_YELLOW_RABBITS).add(ASPEN_PARKLAND);
		this.tag(AtmosphericBiomeTags.SPAWNS_ARID_CAMELS).addTag(AtmosphericBiomeTags.IS_DUNES).addTag(AtmosphericBiomeTags.IS_SCRUBLAND).add(SPINY_THICKET);
	}

	@SafeVarargs
	private void tag(ResourceKey<Biome> biome, TagKey<Biome>... tags) {
		for (TagKey<Biome> key : tags) {
			tag(key).add(biome);
		}
	}
}