package com.teamabnormals.atmospheric.core.registry.datapack;

import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import com.teamabnormals.atmospheric.core.other.AtmosphericConditions;
import com.teamabnormals.blueprint.common.world.modification.structure.SimpleStructureRepaletter;
import com.teamabnormals.blueprint.common.world.modification.structure.StructureRepaletterEntry;
import com.teamabnormals.blueprint.common.world.modification.structure.WeightedStructureRepaletter;
import com.teamabnormals.blueprint.core.registry.BlueprintDataPackRegistries;
import com.teamabnormals.woodworks.core.other.WoodworksConditions;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.random.WeightedEntry;
import net.minecraft.util.random.WeightedRandomList;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.structure.BuiltinStructures;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.neoforged.neoforge.common.conditions.ICondition;

import java.util.function.BiConsumer;

public final class AtmosphericStructureRepaletters {
	public static final ResourceKey<StructureRepaletterEntry> YUCCA_DESERT_VILLAGES = create("yucca_desert_villages");
	public static final ResourceKey<StructureRepaletterEntry> YUCCA_DESERT_VILLAGE_LADDERS = create("yucca_desert_village_ladders");
	public static final ResourceKey<StructureRepaletterEntry> YUCCA_DESERT_VILLAGE_BOOKSHELVES = create("yucca_desert_village_bookshelves");
	public static final ResourceKey<StructureRepaletterEntry> YUCCA_DESERT_VILLAGE_CHESTS = create("yucca_desert_village_chests");

	public static final ResourceKey<StructureRepaletterEntry> GRIMWOOD_ANCIENT_CITIES = create("grimwood_ancient_cities");
	public static final ResourceKey<StructureRepaletterEntry> GRIMWOOD_ANCIENT_CITY_LADDERS = create("grimwood_ancient_city_ladders");
	public static final ResourceKey<StructureRepaletterEntry> GRIMWOOD_ANCIENT_CITY_CHESTS = create("grimwood_ancient_city_chests");

	public static final ResourceKey<Structure> VILLAGE_SCRUBLAND = createStructureKey("village_scrubland");
	public static final ResourceKey<StructureRepaletterEntry> SCRUBLAND_VILLAGE_LADDERS = create("scrubland_village_ladders");
	public static final ResourceKey<StructureRepaletterEntry> SCRUBLAND_VILLAGE_BOOKSHELVES = create("scrubland_village_bookshelves");
	public static final ResourceKey<StructureRepaletterEntry> SCRUBLAND_VILLAGE_CHESTS = create("scrubland_village_chests");

	public static void bootstrap(BootstrapContext<StructureRepaletterEntry> context) {
		HolderGetter<Structure> structures = context.lookup(Registries.STRUCTURE);

		HolderSet<Structure> desertVillage = holder(BuiltinStructures.VILLAGE_DESERT, structures);

		context.register(YUCCA_DESERT_VILLAGES, StructureRepaletterEntry.repalette().repaletters(
				new SimpleStructureRepaletter(Blocks.JUNGLE_BUTTON, AtmosphericBlocks.YUCCA_BUTTON.get()),
				new SimpleStructureRepaletter(Blocks.JUNGLE_DOOR, AtmosphericBlocks.YUCCA_DOOR.get()),
				new SimpleStructureRepaletter(Blocks.JUNGLE_FENCE, AtmosphericBlocks.YUCCA_FENCE.get()),
				new SimpleStructureRepaletter(Blocks.JUNGLE_FENCE_GATE, AtmosphericBlocks.YUCCA_FENCE_GATE.get()),
				new SimpleStructureRepaletter(Blocks.JUNGLE_TRAPDOOR, AtmosphericBlocks.YUCCA_TRAPDOOR.get())
		).select(desertVillage));

		context.register(YUCCA_DESERT_VILLAGE_LADDERS, StructureRepaletterEntry.repalette().priority(50).repaletters(new SimpleStructureRepaletter(Blocks.LADDER, AtmosphericBlocks.YUCCA_LADDER.get())).select(desertVillage));
		context.register(YUCCA_DESERT_VILLAGE_BOOKSHELVES, StructureRepaletterEntry.repalette().priority(50).repaletters(new SimpleStructureRepaletter(Blocks.BOOKSHELF, AtmosphericBlocks.YUCCA_BOOKSHELF.get())).select(desertVillage));
		context.register(YUCCA_DESERT_VILLAGE_CHESTS, StructureRepaletterEntry.repalette().priority(50).repaletters(new SimpleStructureRepaletter(Blocks.CHEST, AtmosphericBlocks.YUCCA_CHEST.get())).select(desertVillage));

		HolderSet<Structure> ancientCity = holder(BuiltinStructures.ANCIENT_CITY, structures);

		context.register(GRIMWOOD_ANCIENT_CITIES, StructureRepaletterEntry.repalette().repaletters(
				new SimpleStructureRepaletter(Blocks.DARK_OAK_LOG, AtmosphericBlocks.GRIMWOOD_LOG.get()),
				new SimpleStructureRepaletter(Blocks.DARK_OAK_FENCE, AtmosphericBlocks.GRIMWOOD_FENCE.get()),
				new SimpleStructureRepaletter(Blocks.DARK_OAK_PLANKS, AtmosphericBlocks.GRIMWOOD_PLANKS.get())
		).select(ancientCity));

		context.register(GRIMWOOD_ANCIENT_CITY_LADDERS, StructureRepaletterEntry.repalette().priority(50).repaletters(new SimpleStructureRepaletter(Blocks.LADDER, AtmosphericBlocks.GRIMWOOD_LADDER.get())).select(ancientCity));
		context.register(GRIMWOOD_ANCIENT_CITY_CHESTS, StructureRepaletterEntry.repalette().priority(50).repaletters(new SimpleStructureRepaletter(Blocks.CHEST, AtmosphericBlocks.GRIMWOOD_CHEST.get())).select(ancientCity));

		HolderSet<Structure> scrublandVillage = holder(VILLAGE_SCRUBLAND, structures);

		context.register(SCRUBLAND_VILLAGE_LADDERS, StructureRepaletterEntry.repalette().repaletters(new SimpleStructureRepaletter(Blocks.LADDER, AtmosphericBlocks.LAUREL_LADDER.get())).select(scrublandVillage));
		context.register(SCRUBLAND_VILLAGE_BOOKSHELVES, StructureRepaletterEntry.repalette().repaletters(new SimpleStructureRepaletter(Blocks.BOOKSHELF, AtmosphericBlocks.LAUREL_BOOKSHELF.get())).select(scrublandVillage));
		context.register(SCRUBLAND_VILLAGE_CHESTS, StructureRepaletterEntry.repalette().repaletters(new WeightedStructureRepaletter(Blocks.CHEST, WeightedRandomList.create(WeightedEntry.wrap(AtmosphericBlocks.LAUREL_CHEST.get(), 1), WeightedEntry.wrap(AtmosphericBlocks.MORADO_CHEST.get(), 1)))).select(scrublandVillage));
	}

	public static void applyConditions(BiConsumer<ResourceKey<?>, ICondition> builder) {
		builder.accept(YUCCA_DESERT_VILLAGES, AtmosphericConditions.YUCCA_DESERT_VILLAGES);
		builder.accept(YUCCA_DESERT_VILLAGE_LADDERS, AtmosphericConditions.yuccaDesertVillages(WoodworksConditions.WOODEN_LADDERS_IN_VILLAGES));
		builder.accept(YUCCA_DESERT_VILLAGE_BOOKSHELVES, AtmosphericConditions.yuccaDesertVillages(WoodworksConditions.WOODEN_BOOKSHELVES_IN_VILLAGES));
		builder.accept(YUCCA_DESERT_VILLAGE_CHESTS, AtmosphericConditions.yuccaDesertVillages(WoodworksConditions.WOODEN_CHESTS_IN_VILLAGES));

		builder.accept(GRIMWOOD_ANCIENT_CITIES, AtmosphericConditions.GRIMWOOD_ANCIENT_CITIES);
		builder.accept(GRIMWOOD_ANCIENT_CITY_LADDERS, AtmosphericConditions.grimwoodAncientCities(WoodworksConditions.WOODEN_LADDERS_IN_VILLAGES));
		builder.accept(GRIMWOOD_ANCIENT_CITY_CHESTS, AtmosphericConditions.grimwoodAncientCities(WoodworksConditions.WOODEN_CHESTS_IN_VILLAGES));

		builder.accept(SCRUBLAND_VILLAGE_LADDERS, WoodworksConditions.WOODEN_LADDERS_IN_VILLAGES);
		builder.accept(SCRUBLAND_VILLAGE_BOOKSHELVES, WoodworksConditions.WOODEN_BOOKSHELVES_IN_VILLAGES);
		builder.accept(SCRUBLAND_VILLAGE_CHESTS, WoodworksConditions.WOODEN_CHESTS_IN_VILLAGES);
	}

	public static HolderSet<Structure> holder(ResourceKey<Structure> key, HolderGetter<Structure> structures) {
		return HolderSet.direct(structures.getOrThrow(key));
	}

	private static ResourceKey<StructureRepaletterEntry> create(String name) {
		return ResourceKey.create(BlueprintDataPackRegistries.STRUCTURE_REPALETTERS, Atmospheric.location(name));
	}

	private static ResourceKey<Structure> createStructureKey(String name) {
		return ResourceKey.create(Registries.STRUCTURE, Atmospheric.location(name));
	}
}