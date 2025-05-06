package com.teamabnormals.atmospheric.core.registry.datapack;

import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.atmospheric.core.other.AtmosphericConditions;
import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import com.teamabnormals.blueprint.common.world.modification.structure.StructureRepaletterEntry;
import com.teamabnormals.blueprint.core.registry.BlueprintDataPackRegistries;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.random.WeightedEntry;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.structure.BuiltinStructures;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.neoforged.neoforge.common.conditions.ICondition;

import java.util.function.BiConsumer;

import static com.teamabnormals.blueprint.common.world.modification.structure.StructureRepaletterEntry.*;
import static com.teamabnormals.woodworks.core.other.WoodworksConditions.*;

public final class AtmosphericStructureRepaletters {
	public static final ResourceKey<StructureRepaletterEntry> YUCCA_DESERT_VILLAGES = create("yucca_desert_villages");
	public static final ResourceKey<StructureRepaletterEntry> YUCCA_DESERT_VILLAGE_LADDERS = create("yucca_desert_village_ladders");
	public static final ResourceKey<StructureRepaletterEntry> YUCCA_DESERT_VILLAGE_BOOKSHELVES = create("yucca_desert_village_bookshelves");
	public static final ResourceKey<StructureRepaletterEntry> YUCCA_DESERT_VILLAGE_CHESTS = create("yucca_desert_village_chests");

	public static final ResourceKey<StructureRepaletterEntry> GRIMWOOD_ANCIENT_CITIES = create("grimwood_ancient_cities");
	public static final ResourceKey<StructureRepaletterEntry> GRIMWOOD_ANCIENT_CITY_LADDERS = create("grimwood_ancient_city_ladders");
	public static final ResourceKey<StructureRepaletterEntry> GRIMWOOD_ANCIENT_CITY_CHESTS = create("grimwood_ancient_city_chests");

	public static final ResourceKey<StructureRepaletterEntry> SCRUBLAND_VILLAGE_LADDERS = create("scrubland_village_ladders");
	public static final ResourceKey<StructureRepaletterEntry> SCRUBLAND_VILLAGE_BOOKSHELVES = create("scrubland_village_bookshelves");
	public static final ResourceKey<StructureRepaletterEntry> SCRUBLAND_VILLAGE_CHESTS = create("scrubland_village_chests");

	public static void bootstrap(BootstrapContext<StructureRepaletterEntry> context) {
		HolderGetter<Structure> structures = context.lookup(Registries.STRUCTURE);

		HolderSet<Structure> desertVillage = holder(structures, BuiltinStructures.VILLAGE_DESERT);

		context.register(YUCCA_DESERT_VILLAGES, repalette().repaletters(
				simple(Blocks.JUNGLE_BUTTON, AtmosphericBlocks.YUCCA_BUTTON.get()),
				simple(Blocks.JUNGLE_DOOR, AtmosphericBlocks.YUCCA_DOOR.get()),
				simple(Blocks.JUNGLE_FENCE, AtmosphericBlocks.YUCCA_FENCE.get()),
				simple(Blocks.JUNGLE_FENCE_GATE, AtmosphericBlocks.YUCCA_FENCE_GATE.get()),
				simple(Blocks.JUNGLE_TRAPDOOR, AtmosphericBlocks.YUCCA_TRAPDOOR.get())
		).select(desertVillage));

		context.register(YUCCA_DESERT_VILLAGE_LADDERS, repalette().priority(50).repaletters(simple(Blocks.LADDER, AtmosphericBlocks.YUCCA_LADDER.get())).select(desertVillage));
		context.register(YUCCA_DESERT_VILLAGE_BOOKSHELVES, repalette().priority(50).repaletters(simple(Blocks.BOOKSHELF, AtmosphericBlocks.YUCCA_BOOKSHELF.get())).select(desertVillage));
		context.register(YUCCA_DESERT_VILLAGE_CHESTS, repalette().priority(50).repaletters(simple(Blocks.CHEST, AtmosphericBlocks.YUCCA_CHEST.get())).select(desertVillage));

		HolderSet<Structure> ancientCity = holder(structures, BuiltinStructures.ANCIENT_CITY);

		context.register(GRIMWOOD_ANCIENT_CITIES, repalette().repaletters(
				simple(Blocks.DARK_OAK_LOG, AtmosphericBlocks.GRIMWOOD_LOG.get()),
				simple(Blocks.DARK_OAK_FENCE, AtmosphericBlocks.GRIMWOOD_FENCE.get()),
				simple(Blocks.DARK_OAK_PLANKS, AtmosphericBlocks.GRIMWOOD_PLANKS.get())
		).select(ancientCity));

		context.register(GRIMWOOD_ANCIENT_CITY_LADDERS, repalette().priority(50).repaletters(simple(Blocks.LADDER, AtmosphericBlocks.GRIMWOOD_LADDER.get())).select(ancientCity));
		context.register(GRIMWOOD_ANCIENT_CITY_CHESTS, repalette().priority(50).repaletters(simple(Blocks.CHEST, AtmosphericBlocks.GRIMWOOD_CHEST.get())).select(ancientCity));

		HolderSet<Structure> scrublandVillage = holder(structures, AtmosphericStructures.VILLAGE_SCRUBLAND);

		context.register(SCRUBLAND_VILLAGE_LADDERS, repalette().repaletters(simple(Blocks.LADDER, AtmosphericBlocks.LAUREL_LADDER.get())).select(scrublandVillage));
		context.register(SCRUBLAND_VILLAGE_BOOKSHELVES, repalette().repaletters(simple(Blocks.BOOKSHELF, AtmosphericBlocks.LAUREL_BOOKSHELF.get())).select(scrublandVillage));
		context.register(SCRUBLAND_VILLAGE_CHESTS, repalette().repaletters(weighted(Blocks.CHEST,
				WeightedEntry.wrap(AtmosphericBlocks.LAUREL_CHEST.get(), 1),
				WeightedEntry.wrap(AtmosphericBlocks.MORADO_CHEST.get(), 1))
		).select(scrublandVillage));
	}

	public static void applyConditions(BiConsumer<ResourceKey<?>, ICondition> builder) {
		builder.accept(YUCCA_DESERT_VILLAGES, AtmosphericConditions.YUCCA_DESERT_VILLAGES);
		builder.accept(YUCCA_DESERT_VILLAGE_LADDERS, compat(AtmosphericConditions.YUCCA_DESERT_VILLAGES, WOODEN_LADDERS_IN_VILLAGES));
		builder.accept(YUCCA_DESERT_VILLAGE_BOOKSHELVES, compat(AtmosphericConditions.YUCCA_DESERT_VILLAGES, WOODEN_BOOKSHELVES_IN_VILLAGES));
		builder.accept(YUCCA_DESERT_VILLAGE_CHESTS, compat(AtmosphericConditions.YUCCA_DESERT_VILLAGES, WOODEN_CHESTS_IN_VILLAGES));

		builder.accept(GRIMWOOD_ANCIENT_CITIES, AtmosphericConditions.GRIMWOOD_ANCIENT_CITIES);
		builder.accept(GRIMWOOD_ANCIENT_CITY_LADDERS, compat(AtmosphericConditions.GRIMWOOD_ANCIENT_CITIES, WOODEN_LADDERS_IN_VILLAGES));
		builder.accept(GRIMWOOD_ANCIENT_CITY_CHESTS, compat(AtmosphericConditions.GRIMWOOD_ANCIENT_CITIES, WOODEN_CHESTS_IN_VILLAGES));

		builder.accept(SCRUBLAND_VILLAGE_LADDERS, compat(WOODEN_LADDERS_IN_VILLAGES));
		builder.accept(SCRUBLAND_VILLAGE_BOOKSHELVES, compat(WOODEN_BOOKSHELVES_IN_VILLAGES));
		builder.accept(SCRUBLAND_VILLAGE_CHESTS, compat(WOODEN_CHESTS_IN_VILLAGES));
	}

	private static ResourceKey<StructureRepaletterEntry> create(String name) {
		return ResourceKey.create(BlueprintDataPackRegistries.STRUCTURE_REPALETTERS, Atmospheric.location(name));
	}
}