package com.teamabnormals.atmospheric.core.registry.datapack;

import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.atmospheric.core.other.tags.AtmosphericBiomeTags;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.heightproviders.ConstantHeight;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadType;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.structures.JigsawStructure;
import net.minecraft.world.level.levelgen.structure.templatesystem.LiquidSettings;

import java.util.List;
import java.util.Optional;

public class AtmosphericStructures {
	public static final ResourceKey<Structure> ARID_GARDEN = create("arid_garden");
	public static final ResourceKey<Structure> KOUSA_SANCTUM = create("kousa_sanctum");
	public static final ResourceKey<Structure> VILLAGE_SCRUBLAND = create("village_scrubland");

	public static void bootstrap(BootstrapContext<Structure> context) {
		HolderGetter<Biome> biomes = context.lookup(Registries.BIOME);
		HolderGetter<StructureTemplatePool> templatePools = context.lookup(Registries.TEMPLATE_POOL);

		context.register(ARID_GARDEN, new JigsawStructure(
				new Structure.StructureSettings.Builder(biomes.getOrThrow(AtmosphericBiomeTags.HAS_ARID_GARDEN)).terrainAdapation(TerrainAdjustment.BEARD_THIN).build(),
				templatePools.getOrThrow(AtmosphericTemplatePools.ARID_GARDEN), 6, ConstantHeight.of(VerticalAnchor.absolute(0)), false
		));

		context.register(KOUSA_SANCTUM, new JigsawStructure(
				new Structure.StructureSettings.Builder(biomes.getOrThrow(AtmosphericBiomeTags.HAS_KOUSA_SANCTUM)).terrainAdapation(TerrainAdjustment.BEARD_THIN).build(),
				templatePools.getOrThrow(AtmosphericTemplatePools.KOUSA_SANCTUM), 2, ConstantHeight.of(VerticalAnchor.absolute(0)), false
		));

		context.register(VILLAGE_SCRUBLAND, new JigsawStructure(
				new Structure.StructureSettings.Builder(biomes.getOrThrow(AtmosphericBiomeTags.HAS_VILLAGE_SCRUBLAND)).terrainAdapation(TerrainAdjustment.BEARD_THIN).build(),
				templatePools.getOrThrow(AtmosphericTemplatePools.VILLAGE_SCRUBLAND_TOWN_CENTERS), Optional.empty(), 6,
				ConstantHeight.of(VerticalAnchor.absolute(0)), true, Optional.of(Heightmap.Types.WORLD_SURFACE_WG), 80,
				List.of(), JigsawStructure.DEFAULT_DIMENSION_PADDING, LiquidSettings.IGNORE_WATERLOGGING
		));
	}

	private static ResourceKey<Structure> create(String name) {
		return ResourceKey.create(Registries.STRUCTURE, Atmospheric.location(name));
	}

	public static class AtmosphericStructureSets {
		public static final ResourceKey<StructureSet> ARID_GARDENS = create("arid_gardens");
		public static final ResourceKey<StructureSet> KOUSA_SANCTUMS = create("kousa_sanctums");

		public static void bootstrap(BootstrapContext<StructureSet> context) {
			HolderGetter<Structure> structures = context.lookup(Registries.STRUCTURE);

			context.register(ARID_GARDENS, new StructureSet(structures.getOrThrow(ARID_GARDEN),
					new RandomSpreadStructurePlacement(24, 6, RandomSpreadType.LINEAR, 304972539)
			));

			context.register(KOUSA_SANCTUMS, new StructureSet(structures.getOrThrow(KOUSA_SANCTUM),
					new RandomSpreadStructurePlacement(32, 8, RandomSpreadType.LINEAR, 502973253)
			));
		}

		private static ResourceKey<StructureSet> create(String name) {
			return ResourceKey.create(Registries.STRUCTURE_SET, Atmospheric.location(name));
		}
	}
}
