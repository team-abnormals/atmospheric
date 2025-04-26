package com.teamabnormals.atmospheric.core.registry.datapack;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.teamabnormals.atmospheric.core.Atmospheric;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool.Projection;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import org.apache.commons.compress.utils.Lists;

import java.util.List;
import java.util.function.Function;

public class AtmosphericTemplatePools {
	public static final ResourceKey<StructureTemplatePool> PETRIFIED_GARDEN = createKey("arid_garden/petrified_garden");

	public static void bootstrap(BootstrapContext<StructureTemplatePool> context) {
		HolderGetter<StructureProcessorList> processorLists = context.lookup(Registries.PROCESSOR_LIST);
		Holder<StructureProcessorList> petrifiedAridGarden = processorLists.getOrThrow(AtmosphericProcessorLists.PETRIFIED_ARID_GARDEN);

		HolderGetter<StructureTemplatePool> templatePools = context.lookup(Registries.TEMPLATE_POOL);
		Holder<StructureTemplatePool> empty = templatePools.getOrThrow(Pools.EMPTY);

		context.register(PETRIFIED_GARDEN, structureTemplatePool(empty, "arid_garden/petrified_garden/petrified_garden", 6, petrifiedAridGarden));
	}

	public static StructureTemplatePool structureTemplatePool(Holder<StructureTemplatePool> fallback, String name, int count, Holder<StructureProcessorList> processor) {
		List<Pair<Function<Projection, ? extends StructurePoolElement>, Integer>> elements = Lists.newArrayList();
		for (int i = 1; i <= count; i++) {
			elements.add(singlePoolElement(name + "_" + i, processor));
		}

		return new StructureTemplatePool(fallback, ImmutableList.copyOf(elements), StructureTemplatePool.Projection.RIGID);
	}

	public static Pair<Function<Projection, ? extends StructurePoolElement>, Integer> singlePoolElement(String name, Holder<StructureProcessorList> holderGetter) {
		return Pair.of(StructurePoolElement.single(Atmospheric.location(name).toString(), holderGetter), 1);
	}

	public static ResourceKey<StructureTemplatePool> createKey(String name) {
		return ResourceKey.create(Registries.TEMPLATE_POOL, Atmospheric.location(name));
	}
}