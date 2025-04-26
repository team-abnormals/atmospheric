package com.teamabnormals.atmospheric.core.data.server;

import com.teamabnormals.atmospheric.core.registry.AtmosphericVillagerTypes;
import com.teamabnormals.atmospheric.core.other.tags.AtmosphericBiomeTags;
import com.teamabnormals.atmospheric.core.registry.AtmosphericItems;
import com.teamabnormals.atmospheric.core.registry.datapack.AtmosphericBiomes;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.world.entity.npc.VillagerType;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.BiomeVillagerType;
import net.neoforged.neoforge.registries.datamaps.builtin.Compostable;
import net.neoforged.neoforge.registries.datamaps.builtin.FurnaceFuel;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;

import java.util.concurrent.CompletableFuture;

import static com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks.*;

public class AtmosphericDataMapProvider extends DataMapProvider {

	public AtmosphericDataMapProvider(PackOutput output, CompletableFuture<Provider> provider) {
		super(output, provider);
	}

	@Override
	protected void gather(Provider provider) {
		this.builder(NeoForgeDataMaps.VILLAGER_TYPES)
				.add(AtmosphericBiomeTags.IS_RAINFOREST, new BiomeVillagerType(VillagerType.JUNGLE), false)
				.add(AtmosphericBiomeTags.IS_DUNES, new BiomeVillagerType(AtmosphericVillagerTypes.SCRUBLAND.get()), false)
				.add(AtmosphericBiomeTags.IS_SCRUBLAND, new BiomeVillagerType(AtmosphericVillagerTypes.SCRUBLAND.get()), false)
				.add(AtmosphericBiomes.SPINY_THICKET, new BiomeVillagerType(AtmosphericVillagerTypes.SCRUBLAND.get()), false);

		this.builder(NeoForgeDataMaps.FURNACE_FUELS)
				.add(CURRANT_STALK.getId(), new FurnaceFuel(50), false)
				.add(CURRANT_STALK_BUNDLE.getId(), new FurnaceFuel(200), false);

		this.builder(NeoForgeDataMaps.COMPOSTABLES)
				.add(ROSEWOOD_LEAVES.getId(), new Compostable(0.3F), false)
				.add(ROSEWOOD_SAPLING.getId(), new Compostable(0.3F), false)

				.add(MORADO_LEAVES.getId(), new Compostable(0.3F), false)
				.add(MORADO_SAPLING.getId(), new Compostable(0.3F), false)

				.add(FLOWERING_MORADO_LEAVES.getId(), new Compostable(0.3F), false)
				.add(AtmosphericItems.YELLOW_BLOSSOMS, new Compostable(0.3F), false)

				.add(WARM_MONKEY_BRUSH.getId(), new Compostable(0.65F), false)
				.add(HOT_MONKEY_BRUSH.getId(), new Compostable(0.65F), false)
				.add(SCALDING_MONKEY_BRUSH.getId(), new Compostable(0.65F), false)
				.add(PASSION_VINE.getId(), new Compostable(0.3F), false)
				.add(AtmosphericItems.PASSION_VINE_COIL, new Compostable(0.85F), false)
				.add(PASSION_VINE_BUNDLE.getId(), new Compostable(1.0F), false)
				.add(WATER_HYACINTH.getId(), new Compostable(0.65F), false)

				.add(AtmosphericItems.PASSION_FRUIT, new Compostable(0.65F), false)
				.add(AtmosphericItems.PASSION_FRUIT_TART, new Compostable(0.85F), false)
				.add(AtmosphericItems.YUCCA_FRUIT, new Compostable(0.65F), false)
				.add(AtmosphericItems.ROASTED_YUCCA_FRUIT, new Compostable(0.65F), false)
				.add(YUCCA_BRANCH.getId(), new Compostable(0.65F), false)
				.add(YUCCA_GATEAU.getId(), new Compostable(1.0F), false)

				.add(YUCCA_LEAVES.getId(), new Compostable(0.3F), false)
				.add(YUCCA_SAPLING.getId(), new Compostable(0.3F), false)

				.add(YUCCA_FLOWER.getId(), new Compostable(0.65F), false)
				.add(TALL_YUCCA_FLOWER.getId(), new Compostable(0.65F), false)
				.add(BARREL_CACTUS.getId(), new Compostable(0.5F), false)

				.add(GILIA.getId(), new Compostable(0.65F), false)
				.add(FIRETHORN.getId(), new Compostable(0.65F), false)
				.add(FORSYTHIA.getId(), new Compostable(0.65F), false)

				.add(ARID_SPROUTS.getId(), new Compostable(0.3F), false)
				.add(AtmosphericItems.ALOE_KERNELS, new Compostable(0.3F), false)
				.add(AtmosphericItems.ALOE_LEAVES, new Compostable(0.65F), false)
				.add(ALOE_BUNDLE.getId(), new Compostable(1.0F), false)

				.add(KOUSA_LEAVES.getId(), new Compostable(0.3F), false)
				.add(KOUSA_SAPLING.getId(), new Compostable(0.3F), false)

				.add(ASPEN_LEAVES.getId(), new Compostable(0.3F), false)
				.add(ASPEN_SAPLING.getId(), new Compostable(0.3F), false)

				.add(GREEN_ASPEN_LEAVES.getId(), new Compostable(0.3F), false)
				.add(GREEN_ASPEN_SAPLING.getId(), new Compostable(0.3F), false)

				.add(GRIMWOOD_LEAVES.getId(), new Compostable(0.3F), false)
				.add(GRIMWOOD_SAPLING.getId(), new Compostable(0.3F), false)

				.add(LAUREL_LEAVES.getId(), new Compostable(0.3F), false)
				.add(LAUREL_SAPLING.getId(), new Compostable(0.3F), false)

				.add(DRY_LAUREL_LEAVES.getId(), new Compostable(0.3F), false)
				.add(DRY_LAUREL_SAPLING.getId(), new Compostable(0.3F), false)

				.add(AGAVE.getId(), new Compostable(0.65F), false)
				.add(GOLDEN_GROWTHS.getId(), new Compostable(0.3F), false)
				.add(AtmosphericItems.CURRANT, new Compostable(0.3F), false)
				.add(AtmosphericItems.CURRANT_MUFFIN, new Compostable(0.65F), false)
				.add(HANGING_CURRANT.getId(), new Compostable(0.3F), false)
				.add(CURRANT_LEAVES.getId(), new Compostable(0.3F), false)
				.add(CURRANT_SEEDLING.getId(), new Compostable(0.3F), false)
				.add(CURRANT_CRATE.getId(), new Compostable(1.0F), false)
				.add(CURRANT_STALK.getId(), new Compostable(0.65F), false)
				.add(CURRANT_STALK_BUNDLE.getId(), new Compostable(1.0F), false)

				.add(AtmosphericItems.ORANGE, new Compostable(0.65F), false)
				.add(AtmosphericItems.BLOOD_ORANGE, new Compostable(0.65F), false)
				.add(AtmosphericItems.CANDIED_ORANGE_SLICES, new Compostable(0.65F), false)
				.add(AtmosphericItems.DRAGON_FRUIT, new Compostable(0.65F), false)
				.add(DRAGON_ROOTS.getId(), new Compostable(0.65F), false)

				.add(AtmosphericItems.ORANGE_PUDDING, new Compostable(1.0F), false)

				.add(YUCCA_BUNDLE.getId(), new Compostable(0.85F), false)
				.add(ROASTED_YUCCA_BUNDLE.getId(), new Compostable(0.85F), false)

				.add(PASSION_FRUIT_CRATE.getId(), new Compostable(1.0F), false)
				.add(YUCCA_CASK.getId(), new Compostable(1.0F), false)
				.add(ROASTED_YUCCA_CASK.getId(), new Compostable(1.0F), false)
				.add(BARREL_CACTUS_BATCH.getId(), new Compostable(1.0F), false)
				.add(DRAGON_FRUIT_CRATE.getId(), new Compostable(1.0F), false)
				.add(ORANGE_CRATE.getId(), new Compostable(1.0F), false)
				.add(BLOOD_ORANGE_CRATE.getId(), new Compostable(1.0F), false);
	}
}