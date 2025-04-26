package com.teamabnormals.atmospheric.core.registry.datapack;

import com.google.common.collect.ImmutableList;
import com.teamabnormals.atmospheric.common.levelgen.structure.processor.PreventWaterloggingSpreadProcessor;
import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.atmospheric.core.other.AtmosphericLootTables;
import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.structure.templatesystem.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.rule.blockentity.AppendLoot;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.List;

public class AtmosphericProcessorLists {
	public static final ResourceKey<StructureProcessorList> PREVENT_WATERLOGGING_SPREAD = createKey("prevent_waterlogging_spread");
	public static final ResourceKey<StructureProcessorList> ZOMBIE_SCRUBLAND = createKey("zombie_scrubland");
	public static final ResourceKey<StructureProcessorList> FARM_SCRUBLAND = createKey("farm_scrubland");

	public static final ResourceKey<StructureProcessorList> PETRIFIED_ARID_GARDEN = createKey("petrified_arid_garden");
	public static final ResourceKey<StructureProcessorList> ARID_GARDEN_SAND_ARCHAEOLOGY = createKey("arid_garden_sand_archaeology");

	private static ResourceKey<StructureProcessorList> createKey(String name) {
		return ResourceKey.create(Registries.PROCESSOR_LIST, Atmospheric.location(name));
	}

	private static void register(BootstrapContext<StructureProcessorList> context, ResourceKey<StructureProcessorList> key, List<StructureProcessor> processors) {
		context.register(key, new StructureProcessorList(processors));
	}

	public static void bootstrap(BootstrapContext<StructureProcessorList> context) {
		register(context, PREVENT_WATERLOGGING_SPREAD, ImmutableList.of(new PreventWaterloggingSpreadProcessor()));

		register(context, ZOMBIE_SCRUBLAND, ImmutableList.of(
				new RuleProcessor(ImmutableList.of(
						new ProcessorRule(new TagMatchTest(BlockTags.DOORS), AlwaysTrueTest.INSTANCE, Blocks.AIR.defaultBlockState()),
						new ProcessorRule(new BlockMatchTest(Blocks.LANTERN), AlwaysTrueTest.INSTANCE, Blocks.AIR.defaultBlockState()),
						new ProcessorRule(new RandomBlockMatchTest(AtmosphericBlocks.LAUREL_LOG.get(), 0.02F), AlwaysTrueTest.INSTANCE, Blocks.COBWEB.defaultBlockState()),
						new ProcessorRule(new RandomBlockMatchTest(AtmosphericBlocks.LAUREL_PLANKS.get(), 0.07F), AlwaysTrueTest.INSTANCE, Blocks.COBWEB.defaultBlockState()),
						new ProcessorRule(new RandomBlockMatchTest(AtmosphericBlocks.MORADO_PLANKS.get(), 0.07F), AlwaysTrueTest.INSTANCE, Blocks.COBWEB.defaultBlockState()),
						new ProcessorRule(new RandomBlockMatchTest(AtmosphericBlocks.CARMINE_SHINGLES.get(), 0.1F), AlwaysTrueTest.INSTANCE, Blocks.COBWEB.defaultBlockState()),
						new ProcessorRule(new RandomBlockMatchTest(AtmosphericBlocks.CARMINE_SHINGLE_STAIRS.get(), 0.1F), AlwaysTrueTest.INSTANCE, Blocks.COBWEB.defaultBlockState()),
						new ProcessorRule(new RandomBlockMatchTest(Blocks.WHEAT, 0.8F), AlwaysTrueTest.INSTANCE, Blocks.BEETROOTS.defaultBlockState())
				)),
				new PreventWaterloggingSpreadProcessor()
		));

		register(context, FARM_SCRUBLAND, ImmutableList.of(
				new RuleProcessor(ImmutableList.of(new ProcessorRule(new RandomBlockMatchTest(Blocks.WHEAT, 0.8F), AlwaysTrueTest.INSTANCE, Blocks.BEETROOTS.defaultBlockState()))),
				new PreventWaterloggingSpreadProcessor()
		));

		register(context, PETRIFIED_ARID_GARDEN, ImmutableList.of(
				new CappedProcessor(new RuleProcessor(List.of(new ProcessorRule(new BlockMatchTest(AtmosphericBlocks.ARID_SAND.get()), AlwaysTrueTest.INSTANCE, Blocks.DECORATED_POT.defaultBlockState()))), ConstantInt.of(6)),
				aridGardenArchyLootProcessor(AtmosphericLootTables.ARID_GARDEN_ARCHAEOLOGY_COMMON, 12),
				aridGardenArchyLootProcessor(AtmosphericLootTables.ARID_GARDEN_ARCHAEOLOGY_RARE, 6),
				new CappedProcessor(new RuleProcessor(ImmutableList.of(new ProcessorRule(new BlockMatchTest(Blocks.AIR), AlwaysTrueTest.INSTANCE, AtmosphericBlocks.ARID_SAND.get().defaultBlockState()))), ConstantInt.of(3000)),
				aridGardenArchyLootProcessor(AtmosphericLootTables.ARID_GARDEN_ARCHAEOLOGY_COMMON, 16),
				aridGardenArchyLootProcessor(AtmosphericLootTables.ARID_GARDEN_ARCHAEOLOGY_RARE, 4)
		));

		register(context, ARID_GARDEN_SAND_ARCHAEOLOGY, ImmutableList.of(
				aridGardenArchyLootProcessor(AtmosphericLootTables.ARID_GARDEN_ARCHAEOLOGY_COMMON, 3)
		));

	}

	private static CappedProcessor aridGardenArchyLootProcessor(ResourceKey<LootTable> lootTable, int max) {
		return new CappedProcessor(new RuleProcessor(List.of(new ProcessorRule(new BlockMatchTest(AtmosphericBlocks.ARID_SAND.get()), AlwaysTrueTest.INSTANCE, PosAlwaysTrueTest.INSTANCE, AtmosphericBlocks.SUSPICIOUS_ARID_SAND.get().defaultBlockState(), new AppendLoot(lootTable)))), ConstantInt.of(max));
	}
}