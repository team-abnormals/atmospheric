package com.teamabnormals.atmospheric.core.registry.datapack;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.atmospheric.core.registry.AtmosphericFeatures.AtmosphericPlacedFeatures;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.data.worldgen.ProcessorLists;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool.Projection;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import org.apache.commons.compress.utils.Lists;

import java.util.List;
import java.util.function.Function;

public class AtmosphericTemplatePools {
	public static final ResourceKey<StructureTemplatePool> KOUSA_SANCTUM = create("kousa_sanctum");
	public static final ResourceKey<StructureTemplatePool> ARID_GARDEN = create("arid_garden/arid_garden");
	public static final ResourceKey<StructureTemplatePool> PETRIFIED_GARDEN = create("arid_garden/petrified_garden");
	public static final ResourceKey<StructureTemplatePool> ARID_GARDEN_DECOR = create("arid_garden/decor");
	public static final ResourceKey<StructureTemplatePool> ARID_GARDEN_SAND = create("arid_garden/sand");
	public static final ResourceKey<StructureTemplatePool> VILLAGE_SCRUBLAND_TOWN_CENTERS = create("village/scrubland/town_centers");

	public static void bootstrap(BootstrapContext<StructureTemplatePool> context) {
		HolderGetter<PlacedFeature> features = context.lookup(Registries.PLACED_FEATURE);
		HolderGetter<StructureProcessorList> processors = context.lookup(Registries.PROCESSOR_LIST);
		HolderGetter<StructureTemplatePool> templatePools = context.lookup(Registries.TEMPLATE_POOL);

		Holder<StructureTemplatePool> empty = templatePools.getOrThrow(Pools.EMPTY);

		context.register(ARID_GARDEN, templatePool(empty, "arid_garden/arid_garden", 6, processors.getOrThrow(AtmosphericProcessorLists.ARID_GARDEN)));
		context.register(PETRIFIED_GARDEN, singleTemplatePool(empty, "arid_garden/petrified_garden/petrified_garden", 6, processors.getOrThrow(AtmosphericProcessorLists.PETRIFIED_ARID_GARDEN)));
		context.register(ARID_GARDEN_DECOR, new StructureTemplatePool(empty, ImmutableList.of(Pair.of(StructurePoolElement.feature(features.getOrThrow(AtmosphericPlacedFeatures.BABY_YUCCA_WITH_FLOWERS)), 4)), Projection.RIGID));
		context.register(ARID_GARDEN_SAND, new StructureTemplatePool(empty, ImmutableList.of(Pair.of(StructurePoolElement.legacy(Atmospheric.location("arid_garden/sand").toString(), processors.getOrThrow(AtmosphericProcessorLists.ARID_GARDEN_SAND_ARCHAEOLOGY)), 1)), Projection.RIGID));

		context.register(KOUSA_SANCTUM, new StructureTemplatePool(empty, ImmutableList.of(Pair.of(StructurePoolElement.legacy(KOUSA_SANCTUM.location().toString(), processors.getOrThrow(ProcessorLists.MOSSIFY_70_PERCENT)), 1)), Projection.RIGID));
	}

	public static StructureTemplatePool templatePool(Holder<StructureTemplatePool> fallback, String name, int count, Holder<StructureProcessorList> processor) {
		List<Pair<Function<Projection, ? extends StructurePoolElement>, Integer>> elements = Lists.newArrayList();
		for (int i = 1; i <= count; i++) {
			String id = Atmospheric.location(name + "_" + i).toString();
			elements.add(Pair.of(StructurePoolElement.legacy(id, processor), 1));
		}

		return new StructureTemplatePool(fallback, ImmutableList.copyOf(elements), StructureTemplatePool.Projection.RIGID);
	}

	public static StructureTemplatePool singleTemplatePool(Holder<StructureTemplatePool> fallback, String name, int count, Holder<StructureProcessorList> processor) {
		List<Pair<Function<Projection, ? extends StructurePoolElement>, Integer>> elements = Lists.newArrayList();
		for (int i = 1; i <= count; i++) {
			String id = Atmospheric.location(name + "_" + i).toString();
			elements.add(Pair.of(StructurePoolElement.single(id, processor), 1));
		}

		return new StructureTemplatePool(fallback, ImmutableList.copyOf(elements), StructureTemplatePool.Projection.RIGID);
	}

	public static ResourceKey<StructureTemplatePool> create(String name) {
		return ResourceKey.create(Registries.TEMPLATE_POOL, Atmospheric.location(name));
	}
}