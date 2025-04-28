package com.teamabnormals.atmospheric.core.data.server.modifiers;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.atmospheric.core.registry.AtmosphericItems;
import com.teamabnormals.atmospheric.core.registry.datapack.AtmosphericProcessorLists;
import com.teamabnormals.atmospheric.core.registry.datapack.AtmosphericStructures;
import com.teamabnormals.blueprint.common.remolder.data.RemolderProvider;
import com.teamabnormals.blueprint.core.util.modification.selection.ConditionedResourceSelector;
import com.teamabnormals.blueprint.core.util.modification.selection.selectors.NamesResourceSelector;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.PackOutput.Target;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.StructureSet.StructureSelectionEntry;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool.Projection;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.neoforged.neoforge.common.conditions.ModLoadedCondition;

import java.util.concurrent.CompletableFuture;

import static com.teamabnormals.blueprint.common.remolder.RemolderTypes.add;
import static com.teamabnormals.blueprint.common.remolder.RemolderTypes.sequence;
import static com.teamabnormals.blueprint.common.remolder.data.DynamicReference.target;
import static com.teamabnormals.blueprint.common.remolder.data.DynamicReference.value;
import static com.teamabnormals.blueprint.common.remolder.util.LootRemolders.addEntry;

public class AtmosphericDataRemolderProvider extends RemolderProvider {

	public AtmosphericDataRemolderProvider(PackOutput output, CompletableFuture<Provider> provider) {
		super(Atmospheric.MOD_ID, Target.DATA_PACK, output, provider);
	}

	@Override
	protected void registerEntries(Provider provider) {
		this.entry("loot_table/chests/ruined_portal")
				.path("loot_table/chests/ruined_portal")
				.remolder(sequence(
						addEntry(0, LootItem.lootTableItem(AtmosphericItems.SHIMMERING_PASSION_FRUIT.get()).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0F, 9.0F))).build()),
						addEntry(0, LootItem.lootTableItem(AtmosphericItems.GOLDEN_DRAGON_FRUIT.get()).setWeight(15).build())
				));

		HolderGetter<Structure> structures = provider.lookupOrThrow(Registries.STRUCTURE);
		this.entry("worldgen/structure_set/villages")
				.path("worldgen/structure_set/villages")
				.remolder(add(target("structures[]"), value(
						StructureSet.entry(structures.getOrThrow(AtmosphericStructures.VILLAGE_SCRUBLAND), 1), StructureSelectionEntry.CODEC)
				));

		HolderGetter<StructureProcessorList> processors = provider.lookupOrThrow(Registries.PROCESSOR_LIST);
		Holder<StructureProcessorList> zombie = processors.getOrThrow(AtmosphericProcessorLists.ZOMBIE_SCRUBLAND);
		Codec<Pair<StructurePoolElement, Integer>> codec = Codec.mapPair(StructurePoolElement.CODEC.fieldOf("element"), Codec.intRange(1, 150).fieldOf("weight")).codec();

		NamesResourceSelector selector = new NamesResourceSelector(Atmospheric.location("worldgen/template_pool/village/scrubland/houses"));
		NamesResourceSelector zombieSelector = new NamesResourceSelector(Atmospheric.location("worldgen/template_pool/village/scrubland/zombie/houses"));

		ModLoadedCondition incubation = new ModLoadedCondition("incubation");
		ModLoadedCondition abnormalsDelight = new ModLoadedCondition("abnormals_delight");

		this.entry("worldgen/template_pool/village/scrubland/houses/chicken_coop_scrubland")
				.path(new ConditionedResourceSelector(selector, incubation))
				.remolder(add(target("elements[]"), value(
						Pair.of(StructurePoolElement.legacy("atmospheric:village/scrubland/houses/scrubland_chicken_coop_1").apply(Projection.RIGID), 2), codec)
				));

		this.entry("worldgen/template_pool/village/scrubland/zombie/houses/chicken_coop_scrubland")
				.path(new ConditionedResourceSelector(zombieSelector, incubation))
				.remolder(add(target("elements[]"), value(
						Pair.of(StructurePoolElement.legacy("atmospheric:village/scrubland/houses/scrubland_chicken_coop_1", zombie).apply(Projection.RIGID), 2), codec)
				));

		this.entry("worldgen/template_pool/village/scrubland/houses/compost_pile_scrubland")
				.path(new ConditionedResourceSelector(selector, abnormalsDelight))
				.remolder(add(target("elements[]"), value(
						Pair.of(StructurePoolElement.legacy("atmospheric:village/scrubland/houses/scrubland_compost_pile_1").apply(Projection.RIGID), 3), codec)
				));

		this.entry("worldgen/template_pool/village/scrubland/zombie/houses/compost_pile_scrubland")
				.path(new ConditionedResourceSelector(zombieSelector, abnormalsDelight))
				.remolder(add(target("elements[]"), value(
						Pair.of(StructurePoolElement.legacy("atmospheric:village/scrubland/houses/scrubland_compost_pile_1", zombie).apply(Projection.RIGID), 3), codec)
				));
	}
}