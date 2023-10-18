package com.teamabnormals.atmospheric.core.data.server;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.teamabnormals.atmospheric.common.block.AloeVeraBlock;
import com.teamabnormals.atmospheric.common.block.AloeVeraTallBlock;
import com.teamabnormals.atmospheric.common.block.BarrelCactusBlock;
import com.teamabnormals.atmospheric.common.block.DragonRootsBlock;
import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import com.teamabnormals.atmospheric.core.registry.AtmosphericItems;
import com.teamabnormals.blueprint.common.block.VerticalSlabBlock;
import com.teamabnormals.blueprint.common.block.VerticalSlabBlock.VerticalSlabType;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.BlockPos;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.data.loot.ChestLoot;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.storage.loot.*;
import net.minecraft.world.level.storage.loot.LootTable.Builder;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.*;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.*;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks.*;

public class AtmosphericLootTableProvider extends LootTableProvider {
	private final List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, Builder>>>, LootContextParamSet>> tables = ImmutableList.of(Pair.of(AtmosphericBlockLoot::new, LootContextParamSets.BLOCK), Pair.of(AtmosphericChestLoot::new, LootContextParamSets.CHEST));

	public AtmosphericLootTableProvider(DataGenerator generator) {
		super(generator);
	}

	@Override
	public List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, Builder>>>, LootContextParamSet>> getTables() {
		return tables;
	}

	@Override
	protected void validate(Map<ResourceLocation, LootTable> map, ValidationContext context) {
	}

	private static class AtmosphericBlockLoot extends BlockLoot {
		private static final LootItemCondition.Builder HAS_SILK_TOUCH = MatchTool.toolMatches(ItemPredicate.Builder.item().hasEnchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.Ints.atLeast(1))));
		private static final LootItemCondition.Builder HAS_SHEARS = MatchTool.toolMatches(ItemPredicate.Builder.item().of(Tags.Items.SHEARS));
		private static final LootItemCondition.Builder HAS_SHEARS_OR_SILK_TOUCH = HAS_SHEARS.or(HAS_SILK_TOUCH);
		private static final LootItemCondition.Builder HAS_NO_SHEARS_OR_SILK_TOUCH = HAS_SHEARS_OR_SILK_TOUCH.invert();

		private static final float[] NORMAL_LEAVES_SAPLING_CHANCES = new float[]{0.05F, 0.0625F, 0.083333336F, 0.1F};
		private static final float[] CURRANT_LEAVES_STALK_CHANCES = new float[]{0.04F, 0.044444446F, 0.05F, 0.066666670F, 0.2F};

		@Override
		public void addTables() {
			this.dropSelf(WARM_MONKEY_BRUSH.get());
			this.dropPottedContents(POTTED_WARM_MONKEY_BRUSH.get());
			this.dropSelf(HOT_MONKEY_BRUSH.get());
			this.dropPottedContents(POTTED_HOT_MONKEY_BRUSH.get());
			this.dropSelf(SCALDING_MONKEY_BRUSH.get());
			this.dropPottedContents(POTTED_SCALDING_MONKEY_BRUSH.get());
			this.add(PASSION_VINE.get(), (block) -> applyExplosionDecay(block, LootTable.lootTable().withPool(LootPool.lootPool().when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(PASSION_VINE.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(AloeVeraBlock.AGE, 4))).add(LootItem.lootTableItem(AtmosphericItems.PASSION_FRUIT.get())).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))).withPool(LootPool.lootPool().add(LootItem.lootTableItem(PASSION_VINE.get())))));
			this.add(PASSION_VINE_BUNDLE.get(), (block) -> LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).when(HAS_SHEARS_OR_SILK_TOUCH).add(LootItem.lootTableItem(block))));
			this.add(WATER_HYACINTH.get(), (block) -> createSinglePropConditionTable(block, DoublePlantBlock.HALF, DoubleBlockHalf.UPPER));
			this.dropPottedContents(POTTED_WATER_HYACINTH.get());
			this.dropSelf(PASSION_FRUIT_CRATE.get());
			this.dropSelf(SHIMMERING_PASSION_FRUIT_CRATE.get());

			this.add(ARID_SPROUTS.get(), BlockLoot::createShearsOnlyDrop);
			this.add(ALOE_VERA.get(), block -> applyExplosionDecay(block, LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(AtmosphericItems.ALOE_LEAVES.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))).apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE)).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(AloeVeraBlock.AGE, 5))).otherwise(LootItem.lootTableItem(AtmosphericItems.ALOE_KERNELS.get())))).withPool(LootPool.lootPool().when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(AloeVeraBlock.AGE, 5))).add(LootItem.lootTableItem(AtmosphericItems.ALOE_KERNELS.get()).apply(ApplyBonusCount.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE, 0.5714286F, 3))))));
			this.add(TALL_ALOE_VERA.get(), AtmosphericBlockLoot::createTallAloeVeraDrops);
			this.dropSelf(ALOE_BUNDLE.get());
			this.dropSelf(ALOE_GEL_BLOCK.get());
			this.add(BARREL_CACTUS.get(), AtmosphericBlockLoot::createBarrelCactusDrops);
			this.add(SNOWY_BARREL_CACTUS.get(), AtmosphericBlockLoot::createBarrelCactusDrops);
			this.dropOther(SNOWY_CACTUS.get(), Blocks.CACTUS);
			this.dropSelf(BARREL_CACTUS_BATCH.get());
			this.dropPottedContents(POTTED_BARREL_CACTUS.get());
			this.dropPottedContents(POTTED_SNOWY_CACTUS.get());
			this.dropPottedContents(POTTED_SNOWY_BARREL_CACTUS.get());
			this.dropSelf(GILIA.get());
			this.dropPottedContents(POTTED_GILIA.get());
			this.dropSelf(YUCCA_FLOWER.get());
			this.dropPottedContents(POTTED_YUCCA_FLOWER.get());
			this.add(TALL_YUCCA_FLOWER.get(), block -> createDoublePlantDrops(block, YUCCA_FLOWER.get()));
			this.add(YUCCA_GATEAU.get(), noDrop());
			this.add(YUCCA_BRANCH.get(), (block) -> createShearsDispatchTable(block, applyExplosionDecay(block, LootItem.lootTableItem(Items.STICK).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F))))));
			this.add(YUCCA_BUNDLE.get(), (block) -> createSilkTouchOrShearsDispatchTable(block, applyExplosionDecay(block, LootItem.lootTableItem(AtmosphericItems.YUCCA_FRUIT.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 6.0F))).apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE)).apply(LimitCount.limitCount(IntRange.upperBound(8))))));
			this.add(ROASTED_YUCCA_BUNDLE.get(), (block) -> createSilkTouchOrShearsDispatchTable(block, applyExplosionDecay(block, LootItem.lootTableItem(AtmosphericItems.ROASTED_YUCCA_FRUIT.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 6.0F))).apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE)).apply(LimitCount.limitCount(IntRange.upperBound(8))))));
			this.dropSelf(YUCCA_CASK.get());
			this.dropSelf(ROASTED_YUCCA_CASK.get());

			this.add(AGAVE.get(), BlockLoot::createShearsOnlyDrop);
			this.dropPottedContents(POTTED_AGAVE.get());
			this.add(GOLDEN_GROWTHS.get(), BlockLoot::createShearsOnlyDrop);
			this.dropPottedContents(POTTED_GOLDEN_GROWTHS.get());
			this.add(CRUSTOSE.get(), (block) -> createSingleItemTableWithSilkTouch(block, Blocks.DIRT));
			this.add(CRUSTOSE_LOG.get(), (block) -> createSingleItemTableWithSilkTouch(block, ASPEN_LOG.get()));
			this.add(CRUSTOSE_WOOD.get(), (block) -> createSingleItemTableWithSilkTouch(block, ASPEN_WOOD.get()));
			this.dropOther(CRUSTOSE_PATH.get(), Blocks.DIRT);

			this.dropSelf(CURRANT_STALK.get());
			this.dropSelf(CURRANT_STALK_BUNDLE.get());
			this.dropSelf(CURRANT_BASKET.get());
			this.add(HANGING_CURRANT.get(), BlockLoot::createShearsOnlyDrop);
			this.add(CURRANT_SEEDLING.get(), BlockLoot::createShearsOnlyDrop);
			this.dropPottedContents(POTTED_CURRANT_SEEDLING.get());
			this.dropSelf(CURRANT_HEDGE.get());
			this.dropSelf(CURRANT_LEAF_CARPET.get());
			this.add(CURRANT_LEAF_PILE.get(), AtmosphericBlockLoot::createLeafPileDrops);
			this.add(CURRANT_LEAVES.get(), block -> createSilkTouchOrShearsDispatchTable(block, applyExplosionDecay(block, LootItem.lootTableItem(CURRANT_STALK.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))).when(BonusLevelTableCondition.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, CURRANT_LEAVES_STALK_CHANCES))));
			this.dropOther(SNOWY_BAMBOO.get(), Items.BAMBOO);
			this.dropOther(SNOWY_BAMBOO_SAPLING.get(), Items.BAMBOO);
			this.dropPottedContents(POTTED_SNOWY_BAMBOO.get());

			this.add(GRIMWEB.get(), (block) -> createSilkTouchOrShearsDispatchTable(block, applyExplosionCondition(block, LootItem.lootTableItem(Items.STRING))));

			this.dropSelf(CARMINE_BLOCK.get());
			this.dropSelf(CARMINE_SHINGLES.get());
			this.dropSelf(CARMINE_SHINGLE_STAIRS.get());
			this.dropSelf(CARMINE_SHINGLE_WALL.get());
			this.add(CARMINE_SHINGLE_SLAB.get(), BlockLoot::createSlabItemTable);
			this.add(CARMINE_SHINGLE_VERTICAL_SLAB.get(), AtmosphericBlockLoot::createVerticalSlabItemTable);
			this.dropSelf(CHISELED_CARMINE_SHINGLES.get());
			this.dropSelf(CARMINE_PAVEMENT.get());
			this.dropSelf(CARMINE_PAVEMENT_STAIRS.get());
			this.dropSelf(CARMINE_PAVEMENT_WALL.get());
			this.add(CARMINE_PAVEMENT_SLAB.get(), BlockLoot::createSlabItemTable);
			this.add(CARMINE_PAVEMENT_VERTICAL_SLAB.get(), AtmosphericBlockLoot::createVerticalSlabItemTable);
			this.dropSelf(FIRETHORN.get());
			this.dropPottedContents(POTTED_FIRETHORN.get());
			this.dropSelf(FORSYTHIA.get());
			this.dropPottedContents(POTTED_FORSYTHIA.get());
			this.dropSelf(DRAGON_FRUIT_CRATE.get());
			this.dropSelf(GOLDEN_DRAGON_FRUIT_CRATE.get());
			this.add(DRAGON_ROOTS.get(), AtmosphericBlockLoot::createDragonRootsDrops);

			this.dropSelf(ARID_SAND.get());
			this.dropSelf(ARID_SANDSTONE.get());
			this.dropSelf(ARID_SANDSTONE_STAIRS.get());
			this.add(ARID_SANDSTONE_SLAB.get(), BlockLoot::createSlabItemTable);
			this.add(ARID_SANDSTONE_VERTICAL_SLAB.get(), AtmosphericBlockLoot::createVerticalSlabItemTable);
			this.dropSelf(ARID_SANDSTONE_WALL.get());
			this.dropSelf(SMOOTH_ARID_SANDSTONE.get());
			this.dropSelf(SMOOTH_ARID_SANDSTONE_STAIRS.get());
			this.add(SMOOTH_ARID_SANDSTONE_SLAB.get(), BlockLoot::createSlabItemTable);
			this.add(SMOOTH_ARID_SANDSTONE_VERTICAL_SLAB.get(), AtmosphericBlockLoot::createVerticalSlabItemTable);
			this.dropSelf(CUT_ARID_SANDSTONE.get());
			this.add(CUT_ARID_SANDSTONE_SLAB.get(), BlockLoot::createSlabItemTable);
			this.add(CUT_ARID_SANDSTONE_VERTICAL_SLAB.get(), AtmosphericBlockLoot::createVerticalSlabItemTable);
			this.dropSelf(CHISELED_ARID_SANDSTONE.get());
			this.dropSelf(ARID_SANDSTONE_BRICKS.get());
			this.dropSelf(ARID_SANDSTONE_BRICK_STAIRS.get());
			this.add(ARID_SANDSTONE_BRICK_SLAB.get(), BlockLoot::createSlabItemTable);
			this.add(ARID_SANDSTONE_BRICK_VERTICAL_SLAB.get(), AtmosphericBlockLoot::createVerticalSlabItemTable);
			this.dropSelf(ARID_SANDSTONE_BRICK_WALL.get());

			this.dropSelf(RED_ARID_SAND.get());
			this.dropSelf(RED_ARID_SANDSTONE.get());
			this.dropSelf(RED_ARID_SANDSTONE_STAIRS.get());
			this.add(RED_ARID_SANDSTONE_SLAB.get(), BlockLoot::createSlabItemTable);
			this.add(RED_ARID_SANDSTONE_VERTICAL_SLAB.get(), AtmosphericBlockLoot::createVerticalSlabItemTable);
			this.dropSelf(RED_ARID_SANDSTONE_WALL.get());
			this.dropSelf(SMOOTH_RED_ARID_SANDSTONE.get());
			this.dropSelf(SMOOTH_RED_ARID_SANDSTONE_STAIRS.get());
			this.add(SMOOTH_RED_ARID_SANDSTONE_SLAB.get(), BlockLoot::createSlabItemTable);
			this.add(SMOOTH_RED_ARID_SANDSTONE_VERTICAL_SLAB.get(), AtmosphericBlockLoot::createVerticalSlabItemTable);
			this.dropSelf(CUT_RED_ARID_SANDSTONE.get());
			this.add(CUT_RED_ARID_SANDSTONE_SLAB.get(), BlockLoot::createSlabItemTable);
			this.add(CUT_RED_ARID_SANDSTONE_VERTICAL_SLAB.get(), AtmosphericBlockLoot::createVerticalSlabItemTable);
			this.dropSelf(CHISELED_RED_ARID_SANDSTONE.get());
			this.dropSelf(RED_ARID_SANDSTONE_BRICKS.get());
			this.dropSelf(RED_ARID_SANDSTONE_BRICK_STAIRS.get());
			this.add(RED_ARID_SANDSTONE_BRICK_SLAB.get(), BlockLoot::createSlabItemTable);
			this.add(RED_ARID_SANDSTONE_BRICK_VERTICAL_SLAB.get(), AtmosphericBlockLoot::createVerticalSlabItemTable);
			this.dropSelf(RED_ARID_SANDSTONE_BRICK_WALL.get());

			this.dropSelf(IVORY_TRAVERTINE.get());
			this.dropSelf(IVORY_TRAVERTINE_STAIRS.get());
			this.add(IVORY_TRAVERTINE_SLAB.get(), BlockLoot::createSlabItemTable);
			this.add(IVORY_TRAVERTINE_VERTICAL_SLAB.get(), AtmosphericBlockLoot::createVerticalSlabItemTable);
			this.dropSelf(IVORY_TRAVERTINE_WALL.get());
			this.dropSelf(CUT_IVORY_TRAVERTINE.get());
			this.dropSelf(CHISELED_IVORY_TRAVERTINE.get());
			this.dropSelf(PEACH_TRAVERTINE.get());
			this.dropSelf(PEACH_TRAVERTINE_STAIRS.get());
			this.add(PEACH_TRAVERTINE_SLAB.get(), BlockLoot::createSlabItemTable);
			this.add(PEACH_TRAVERTINE_VERTICAL_SLAB.get(), AtmosphericBlockLoot::createVerticalSlabItemTable);
			this.dropSelf(PEACH_TRAVERTINE_WALL.get());
			this.dropSelf(CUT_PEACH_TRAVERTINE.get());
			this.dropSelf(CHISELED_PEACH_TRAVERTINE.get());
			this.dropSelf(PERSIMMON_TRAVERTINE.get());
			this.dropSelf(PERSIMMON_TRAVERTINE_STAIRS.get());
			this.add(PERSIMMON_TRAVERTINE_SLAB.get(), BlockLoot::createSlabItemTable);
			this.add(PERSIMMON_TRAVERTINE_VERTICAL_SLAB.get(), AtmosphericBlockLoot::createVerticalSlabItemTable);
			this.dropSelf(PERSIMMON_TRAVERTINE_WALL.get());
			this.dropSelf(CUT_PERSIMMON_TRAVERTINE.get());
			this.dropSelf(CHISELED_PERSIMMON_TRAVERTINE.get());
			this.dropSelf(SAFFRON_TRAVERTINE.get());
			this.dropSelf(SAFFRON_TRAVERTINE_STAIRS.get());
			this.add(SAFFRON_TRAVERTINE_SLAB.get(), BlockLoot::createSlabItemTable);
			this.add(SAFFRON_TRAVERTINE_VERTICAL_SLAB.get(), AtmosphericBlockLoot::createVerticalSlabItemTable);
			this.dropSelf(SAFFRON_TRAVERTINE_WALL.get());
			this.dropSelf(CUT_SAFFRON_TRAVERTINE.get());
			this.dropSelf(CHISELED_SAFFRON_TRAVERTINE.get());

			this.dropSelf(DOLERITE.get());
			this.dropSelf(DOLERITE_STAIRS.get());
			this.add(DOLERITE_SLAB.get(), BlockLoot::createSlabItemTable);
			this.add(DOLERITE_VERTICAL_SLAB.get(), AtmosphericBlockLoot::createVerticalSlabItemTable);
			this.dropSelf(DOLERITE_WALL.get());
			this.dropSelf(POLISHED_DOLERITE.get());
			this.dropSelf(POLISHED_DOLERITE_STAIRS.get());
			this.add(POLISHED_DOLERITE_SLAB.get(), BlockLoot::createSlabItemTable);
			this.add(POLISHED_DOLERITE_VERTICAL_SLAB.get(), AtmosphericBlockLoot::createVerticalSlabItemTable);

			this.dropSelf(ROSEWOOD_PLANKS.get());
			this.dropSelf(VERTICAL_ROSEWOOD_PLANKS.get());
			this.dropSelf(ROSEWOOD_LOG.get());
			this.dropSelf(ROSEWOOD.get());
			this.dropSelf(STRIPPED_ROSEWOOD_LOG.get());
			this.dropSelf(STRIPPED_ROSEWOOD.get());
			this.dropSelf(ROSEWOOD_SIGNS.getFirst().get());
			this.dropSelf(ROSEWOOD_PRESSURE_PLATE.get());
			this.dropSelf(ROSEWOOD_TRAPDOOR.get());
			this.dropSelf(ROSEWOOD_BUTTON.get());
			this.dropSelf(ROSEWOOD_STAIRS.get());
			this.dropSelf(ROSEWOOD_FENCE.get());
			this.dropSelf(ROSEWOOD_FENCE_GATE.get());
			this.dropSelf(ROSEWOOD_BOARDS.get());
			this.dropSelf(ROSEWOOD_POST.get());
			this.dropSelf(STRIPPED_ROSEWOOD_POST.get());
			this.dropSelf(ROSEWOOD_HEDGE.get());
			this.dropSelf(ROSEWOOD_LEAF_CARPET.get());
			this.add(ROSEWOOD_LEAF_PILE.get(), AtmosphericBlockLoot::createLeafPileDrops);
			this.dropSelf(ROSEWOOD_SAPLING.get());
			this.dropPottedContents(POTTED_ROSEWOOD_SAPLING.get());
			this.dropSelf(ROSEWOOD_LADDER.get());
			this.add(ROSEWOOD_SLAB.get(), BlockLoot::createSlabItemTable);
			this.add(ROSEWOOD_VERTICAL_SLAB.get(), AtmosphericBlockLoot::createVerticalSlabItemTable);
			this.add(ROSEWOOD_DOOR.get(), BlockLoot::createDoorTable);
			this.add(ROSEWOOD_BEEHIVE.get(), BlockLoot::createBeeHiveDrop);
			this.add(ROSEWOOD_CHESTS.getFirst().get(), BlockLoot::createNameableBlockEntityTable);
			this.add(ROSEWOOD_CHESTS.getSecond().get(), BlockLoot::createNameableBlockEntityTable);
			this.add(ROSEWOOD_BOOKSHELF.get(), (block) -> createSingleItemTableWithSilkTouch(block, Items.BOOK, ConstantValue.exactly(3.0F)));
			this.add(ROSEWOOD_LEAVES.get(), (block) -> createLeavesDrops(block, ROSEWOOD_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

			this.dropSelf(MORADO_PLANKS.get());
			this.dropSelf(VERTICAL_MORADO_PLANKS.get());
			this.dropSelf(MORADO_LOG.get());
			this.dropSelf(MORADO_WOOD.get());
			this.dropSelf(STRIPPED_MORADO_LOG.get());
			this.dropSelf(STRIPPED_MORADO_WOOD.get());
			this.dropSelf(MORADO_SIGNS.getFirst().get());
			this.dropSelf(MORADO_PRESSURE_PLATE.get());
			this.dropSelf(MORADO_TRAPDOOR.get());
			this.dropSelf(MORADO_BUTTON.get());
			this.dropSelf(MORADO_STAIRS.get());
			this.dropSelf(MORADO_FENCE.get());
			this.dropSelf(MORADO_FENCE_GATE.get());
			this.dropSelf(MORADO_BOARDS.get());
			this.dropSelf(MORADO_POST.get());
			this.dropSelf(STRIPPED_MORADO_POST.get());
			this.dropSelf(MORADO_HEDGE.get());
			this.dropSelf(MORADO_LEAF_CARPET.get());
			this.add(MORADO_LEAF_PILE.get(), AtmosphericBlockLoot::createLeafPileDrops);
			this.dropSelf(MORADO_SAPLING.get());
			this.dropPottedContents(POTTED_MORADO_SAPLING.get());
			this.dropSelf(MORADO_LADDER.get());
			this.add(MORADO_SLAB.get(), BlockLoot::createSlabItemTable);
			this.add(MORADO_VERTICAL_SLAB.get(), AtmosphericBlockLoot::createVerticalSlabItemTable);
			this.add(MORADO_DOOR.get(), BlockLoot::createDoorTable);
			this.add(MORADO_BEEHIVE.get(), BlockLoot::createBeeHiveDrop);
			this.add(MORADO_CHESTS.getFirst().get(), BlockLoot::createNameableBlockEntityTable);
			this.add(MORADO_CHESTS.getSecond().get(), BlockLoot::createNameableBlockEntityTable);
			this.add(MORADO_BOOKSHELF.get(), (block) -> createSingleItemTableWithSilkTouch(block, Items.BOOK, ConstantValue.exactly(3.0F)));
			this.add(MORADO_LEAVES.get(), (block) -> createLeavesDrops(block, MORADO_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

			this.dropSelf(FLOWERING_MORADO_HEDGE.get());
			this.dropSelf(FLOWERING_MORADO_LEAF_CARPET.get());
			this.add(FLOWERING_MORADO_LEAF_PILE.get(), AtmosphericBlockLoot::createLeafPileDrops);
			this.add(FLOWERING_MORADO_LEAVES.get(), block -> createLeavesDrops(block, MORADO_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).when(HAS_NO_SHEARS_OR_SILK_TOUCH).add(applyExplosionDecay(block, LootItem.lootTableItem(AtmosphericItems.YELLOW_BLOSSOMS.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))).when(BonusLevelTableCondition.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, CURRANT_LEAVES_STALK_CHANCES)))));

			this.dropSelf(YUCCA_PLANKS.get());
			this.dropSelf(VERTICAL_YUCCA_PLANKS.get());
			this.dropSelf(YUCCA_LOG.get());
			this.dropSelf(YUCCA_WOOD.get());
			this.dropSelf(STRIPPED_YUCCA_LOG.get());
			this.dropSelf(STRIPPED_YUCCA_WOOD.get());
			this.dropSelf(YUCCA_SIGNS.getFirst().get());
			this.dropSelf(YUCCA_PRESSURE_PLATE.get());
			this.dropSelf(YUCCA_TRAPDOOR.get());
			this.dropSelf(YUCCA_BUTTON.get());
			this.dropSelf(YUCCA_STAIRS.get());
			this.dropSelf(YUCCA_FENCE.get());
			this.dropSelf(YUCCA_FENCE_GATE.get());
			this.dropSelf(YUCCA_BOARDS.get());
			this.dropSelf(YUCCA_POST.get());
			this.dropSelf(STRIPPED_YUCCA_POST.get());
			this.dropSelf(YUCCA_HEDGE.get());
			this.dropSelf(YUCCA_LEAF_CARPET.get());
			this.add(YUCCA_LEAF_PILE.get(), AtmosphericBlockLoot::createLeafPileDrops);
			this.dropSelf(YUCCA_SAPLING.get());
			this.dropPottedContents(POTTED_YUCCA_SAPLING.get());
			this.dropSelf(YUCCA_LADDER.get());
			this.add(YUCCA_SLAB.get(), BlockLoot::createSlabItemTable);
			this.add(YUCCA_VERTICAL_SLAB.get(), AtmosphericBlockLoot::createVerticalSlabItemTable);
			this.add(YUCCA_DOOR.get(), BlockLoot::createDoorTable);
			this.add(YUCCA_BEEHIVE.get(), BlockLoot::createBeeHiveDrop);
			this.add(YUCCA_CHESTS.getFirst().get(), BlockLoot::createNameableBlockEntityTable);
			this.add(YUCCA_CHESTS.getSecond().get(), BlockLoot::createNameableBlockEntityTable);
			this.add(YUCCA_BOOKSHELF.get(), (block) -> createSingleItemTableWithSilkTouch(block, Items.BOOK, ConstantValue.exactly(3.0F)));
			this.add(YUCCA_LEAVES.get(), (block) -> createLeavesDrops(block, YUCCA_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

			this.dropSelf(LAUREL_PLANKS.get());
			this.dropSelf(VERTICAL_LAUREL_PLANKS.get());
			this.dropSelf(LAUREL_LOG.get());
			this.dropSelf(LAUREL_WOOD.get());
			this.dropSelf(STRIPPED_LAUREL_LOG.get());
			this.dropSelf(STRIPPED_LAUREL_WOOD.get());
			this.dropSelf(LAUREL_SIGNS.getFirst().get());
			this.dropSelf(LAUREL_PRESSURE_PLATE.get());
			this.dropSelf(LAUREL_TRAPDOOR.get());
			this.dropSelf(LAUREL_BUTTON.get());
			this.dropSelf(LAUREL_STAIRS.get());
			this.dropSelf(LAUREL_FENCE.get());
			this.dropSelf(LAUREL_FENCE_GATE.get());
			this.dropSelf(LAUREL_BOARDS.get());
			this.dropSelf(LAUREL_POST.get());
			this.dropSelf(STRIPPED_LAUREL_POST.get());
			this.dropSelf(LAUREL_HEDGE.get());
			this.dropSelf(LAUREL_LEAF_CARPET.get());
			this.add(LAUREL_LEAF_PILE.get(), AtmosphericBlockLoot::createLeafPileDrops);
			this.dropSelf(LAUREL_SAPLING.get());
			this.dropPottedContents(POTTED_LAUREL_SAPLING.get());
			this.dropSelf(LAUREL_LADDER.get());
			this.add(LAUREL_SLAB.get(), BlockLoot::createSlabItemTable);
			this.add(LAUREL_VERTICAL_SLAB.get(), AtmosphericBlockLoot::createVerticalSlabItemTable);
			this.add(LAUREL_DOOR.get(), BlockLoot::createDoorTable);
			this.add(LAUREL_BEEHIVE.get(), BlockLoot::createBeeHiveDrop);
			this.add(LAUREL_CHESTS.getFirst().get(), BlockLoot::createNameableBlockEntityTable);
			this.add(LAUREL_CHESTS.getSecond().get(), BlockLoot::createNameableBlockEntityTable);
			this.add(LAUREL_BOOKSHELF.get(), (block) -> createSingleItemTableWithSilkTouch(block, Items.BOOK, ConstantValue.exactly(3.0F)));
			this.add(LAUREL_LEAVES.get(), (block) -> createLeavesDrops(block, LAUREL_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

			this.dropSelf(DRY_LAUREL_HEDGE.get());
			this.dropSelf(DRY_LAUREL_LEAF_CARPET.get());
			this.add(DRY_LAUREL_LEAF_PILE.get(), AtmosphericBlockLoot::createLeafPileDrops);
			this.dropSelf(DRY_LAUREL_SAPLING.get());
			this.dropPottedContents(POTTED_DRY_LAUREL_SAPLING.get());
			this.add(DRY_LAUREL_LEAVES.get(), (block) -> createLeavesDrops(block, DRY_LAUREL_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

			this.dropSelf(ASPEN_PLANKS.get());
			this.dropSelf(VERTICAL_ASPEN_PLANKS.get());
			this.dropSelf(ASPEN_LOG.get());
			this.dropSelf(ASPEN_WOOD.get());
			this.dropSelf(STRIPPED_ASPEN_LOG.get());
			this.dropSelf(STRIPPED_ASPEN_WOOD.get());
			this.add(WATCHFUL_ASPEN_LOG.get(), (block) -> createSingleItemTableWithSilkTouch(block, ASPEN_LOG.get()));
			this.add(WATCHFUL_ASPEN_WOOD.get(), (block) -> createSingleItemTableWithSilkTouch(block, ASPEN_WOOD.get()));
			this.dropSelf(ASPEN_SIGNS.getFirst().get());
			this.dropSelf(ASPEN_PRESSURE_PLATE.get());
			this.dropSelf(ASPEN_TRAPDOOR.get());
			this.dropSelf(ASPEN_BUTTON.get());
			this.dropSelf(ASPEN_STAIRS.get());
			this.dropSelf(ASPEN_FENCE.get());
			this.dropSelf(ASPEN_FENCE_GATE.get());
			this.dropSelf(ASPEN_BOARDS.get());
			this.dropSelf(ASPEN_POST.get());
			this.dropSelf(STRIPPED_ASPEN_POST.get());
			this.dropSelf(ASPEN_HEDGE.get());
			this.dropSelf(ASPEN_LEAF_CARPET.get());
			this.add(ASPEN_LEAF_PILE.get(), AtmosphericBlockLoot::createLeafPileDrops);
			this.dropSelf(ASPEN_SAPLING.get());
			this.dropPottedContents(POTTED_ASPEN_SAPLING.get());
			this.dropSelf(ASPEN_LADDER.get());
			this.add(ASPEN_SLAB.get(), BlockLoot::createSlabItemTable);
			this.add(ASPEN_VERTICAL_SLAB.get(), AtmosphericBlockLoot::createVerticalSlabItemTable);
			this.add(ASPEN_DOOR.get(), BlockLoot::createDoorTable);
			this.add(ASPEN_BEEHIVE.get(), BlockLoot::createBeeHiveDrop);
			this.add(ASPEN_CHESTS.getFirst().get(), BlockLoot::createNameableBlockEntityTable);
			this.add(ASPEN_CHESTS.getSecond().get(), BlockLoot::createNameableBlockEntityTable);
			this.add(ASPEN_BOOKSHELF.get(), (block) -> createSingleItemTableWithSilkTouch(block, Items.BOOK, ConstantValue.exactly(3.0F)));
			this.add(ASPEN_LEAVES.get(), (block) -> createLeavesDrops(block, ASPEN_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

			this.dropSelf(KOUSA_PLANKS.get());
			this.dropSelf(VERTICAL_KOUSA_PLANKS.get());
			this.dropSelf(KOUSA_LOG.get());
			this.dropSelf(KOUSA_WOOD.get());
			this.dropSelf(STRIPPED_KOUSA_LOG.get());
			this.dropSelf(STRIPPED_KOUSA_WOOD.get());
			this.dropSelf(KOUSA_SIGNS.getFirst().get());
			this.dropSelf(KOUSA_PRESSURE_PLATE.get());
			this.dropSelf(KOUSA_TRAPDOOR.get());
			this.dropSelf(KOUSA_BUTTON.get());
			this.dropSelf(KOUSA_STAIRS.get());
			this.dropSelf(KOUSA_FENCE.get());
			this.dropSelf(KOUSA_FENCE_GATE.get());
			this.dropSelf(KOUSA_BOARDS.get());
			this.dropSelf(KOUSA_POST.get());
			this.dropSelf(STRIPPED_KOUSA_POST.get());
			this.dropSelf(KOUSA_HEDGE.get());
			this.dropSelf(KOUSA_LEAF_CARPET.get());
			this.add(KOUSA_LEAF_PILE.get(), AtmosphericBlockLoot::createLeafPileDrops);
			this.dropSelf(KOUSA_SAPLING.get());
			this.dropPottedContents(POTTED_KOUSA_SAPLING.get());
			this.dropSelf(KOUSA_LADDER.get());
			this.add(KOUSA_SLAB.get(), BlockLoot::createSlabItemTable);
			this.add(KOUSA_VERTICAL_SLAB.get(), AtmosphericBlockLoot::createVerticalSlabItemTable);
			this.add(KOUSA_DOOR.get(), BlockLoot::createDoorTable);
			this.add(KOUSA_BEEHIVE.get(), BlockLoot::createBeeHiveDrop);
			this.add(KOUSA_CHESTS.getFirst().get(), BlockLoot::createNameableBlockEntityTable);
			this.add(KOUSA_CHESTS.getSecond().get(), BlockLoot::createNameableBlockEntityTable);
			this.add(KOUSA_BOOKSHELF.get(), (block) -> createSingleItemTableWithSilkTouch(block, Items.BOOK, ConstantValue.exactly(3.0F)));
			this.add(KOUSA_LEAVES.get(), (block) -> createLeavesDrops(block, KOUSA_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

			this.dropSelf(GRIMWOOD_PLANKS.get());
			this.dropSelf(VERTICAL_GRIMWOOD_PLANKS.get());
			this.dropSelf(GRIMWOOD_LOG.get());
			this.dropSelf(GRIMWOOD.get());
			this.dropSelf(STRIPPED_GRIMWOOD_LOG.get());
			this.dropSelf(STRIPPED_GRIMWOOD.get());
			this.dropSelf(GRIMWOOD_SIGNS.getFirst().get());
			this.dropSelf(GRIMWOOD_PRESSURE_PLATE.get());
			this.dropSelf(GRIMWOOD_TRAPDOOR.get());
			this.dropSelf(GRIMWOOD_BUTTON.get());
			this.dropSelf(GRIMWOOD_STAIRS.get());
			this.dropSelf(GRIMWOOD_FENCE.get());
			this.dropSelf(GRIMWOOD_FENCE_GATE.get());
			this.dropSelf(GRIMWOOD_BOARDS.get());
			this.dropSelf(GRIMWOOD_POST.get());
			this.dropSelf(STRIPPED_GRIMWOOD_POST.get());
			this.dropSelf(GRIMWOOD_HEDGE.get());
			this.dropSelf(GRIMWOOD_LEAF_CARPET.get());
			this.add(GRIMWOOD_LEAF_PILE.get(), AtmosphericBlockLoot::createLeafPileDrops);
			this.dropSelf(GRIMWOOD_SAPLING.get());
			this.dropPottedContents(POTTED_GRIMWOOD_SAPLING.get());
			this.dropSelf(GRIMWOOD_LADDER.get());
			this.add(GRIMWOOD_SLAB.get(), BlockLoot::createSlabItemTable);
			this.add(GRIMWOOD_VERTICAL_SLAB.get(), AtmosphericBlockLoot::createVerticalSlabItemTable);
			this.add(GRIMWOOD_DOOR.get(), BlockLoot::createDoorTable);
			this.add(GRIMWOOD_BEEHIVE.get(), BlockLoot::createBeeHiveDrop);
			this.add(GRIMWOOD_CHESTS.getFirst().get(), BlockLoot::createNameableBlockEntityTable);
			this.add(GRIMWOOD_CHESTS.getSecond().get(), BlockLoot::createNameableBlockEntityTable);
			this.add(GRIMWOOD_BOOKSHELF.get(), (block) -> createSingleItemTableWithSilkTouch(block, Items.BOOK, ConstantValue.exactly(3.0F)));
			this.add(GRIMWOOD_LEAVES.get(), (block) -> createLeavesDrops(block, GRIMWOOD_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));
		}

		protected static LootTable.Builder createDragonRootsDrops(Block block) {
			return LootTable.lootTable()
					.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).when(HAS_SHEARS_OR_SILK_TOUCH).add(applyExplosionDecay(block, LootItem.lootTableItem(block).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DragonRootsBlock.TOP, true))))))
					.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).when(HAS_SHEARS_OR_SILK_TOUCH).add(applyExplosionDecay(block, LootItem.lootTableItem(block).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DragonRootsBlock.BOTTOM, true))))));
		}

		protected static LootTable.Builder createBarrelCactusDrops(Block block) {
			return LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(applyExplosionDecay(block, LootItem.lootTableItem(BARREL_CACTUS.get()).apply(List.of(1, 2, 3), (val) -> SetItemCountFunction.setCount(ConstantValue.exactly((float) val)).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(BarrelCactusBlock.AGE, val)))))));
		}

		protected static Builder createLeafPileDrops(Block block) {
			return createMultifaceBlockDrops(block, MatchTool.toolMatches(ItemPredicate.Builder.item().of(Tags.Items.SHEARS)));
		}

		protected static Builder createVerticalSlabItemTable(Block block) {
			return LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(applyExplosionDecay(block, LootItem.lootTableItem(block).apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F)).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(VerticalSlabBlock.TYPE, VerticalSlabType.DOUBLE)))))));
		}

		protected static Builder createTallAloeVeraDrops(Block block) {
			return applyExplosionDecay(block, LootTable.lootTable()
					.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(AtmosphericItems.ALOE_LEAVES.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))).apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE)).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER)))))
					.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(AtmosphericItems.YELLOW_BLOSSOMS.get()).apply(List.of(6, 7, 8), (val) -> SetItemCountFunction.setCount(ConstantValue.exactly((float) val - 5.0F)).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER).hasProperty(AloeVeraTallBlock.AGE, val))))))
					.withPool(LootPool.lootPool().when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(TALL_ALOE_VERA.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(AloeVeraTallBlock.AGE, 8).hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER))).add(LootItem.lootTableItem(AtmosphericItems.ALOE_KERNELS.get())).apply(ApplyBonusCount.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE, 0.5714286F, 3)))
					.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(AtmosphericItems.ALOE_KERNELS.get())).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER))))
			);
		}

		protected static LootTable.Builder createDoublePlantDrops(Block large, Block big) {
			LootPoolEntryContainer.Builder<?> builder = LootItem.lootTableItem(big).apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F)));
			return LootTable.lootTable().withPool(LootPool.lootPool().add(builder).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(large).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER))).when(LocationCheck.checkLocation(LocationPredicate.Builder.location().setBlock(BlockPredicate.Builder.block().of(large).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER).build()).build()), new BlockPos(0, 1, 0)))).withPool(LootPool.lootPool().add(builder).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(large).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER))).when(LocationCheck.checkLocation(LocationPredicate.Builder.location().setBlock(BlockPredicate.Builder.block().of(large).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER).build()).build()), new BlockPos(0, -1, 0))));
		}

		@Override
		public Iterable<Block> getKnownBlocks() {
			return ForgeRegistries.BLOCKS.getValues().stream().filter(block -> ForgeRegistries.BLOCKS.getKey(block).getNamespace().equals(Atmospheric.MOD_ID)).collect(Collectors.toSet());
		}
	}


	private static class AtmosphericChestLoot extends ChestLoot {

		@Override
		public void accept(BiConsumer<ResourceLocation, Builder> consumer) {
			consumer.accept(Atmospheric.location("chests/arid_garden"), LootTable.lootTable()
					.withPool(LootPool.lootPool().setRolls(UniformGenerator.between(0.0F, 1.0F))
							.add(LootItem.lootTableItem(Items.IRON_HORSE_ARMOR))
							.add(LootItem.lootTableItem(Items.GOLDEN_HORSE_ARMOR))
							.add(LootItem.lootTableItem(Items.DIAMOND_HORSE_ARMOR))
					)
					.withPool(LootPool.lootPool().setRolls(UniformGenerator.between(1.0F, 1.0F))
							.add(LootItem.lootTableItem(ROSEWOOD_SAPLING.get()))
							.add(LootItem.lootTableItem(MORADO_SAPLING.get()))
							.add(LootItem.lootTableItem(ASPEN_SAPLING.get()))
							.add(LootItem.lootTableItem(KOUSA_SAPLING.get()))
							.add(LootItem.lootTableItem(GRIMWOOD_SAPLING.get()))
							.add(LootItem.lootTableItem(LAUREL_SAPLING.get()))
					)
					.withPool(LootPool.lootPool().setRolls(UniformGenerator.between(1.0F, 4.0F))
							.add(LootItem.lootTableItem(AtmosphericItems.ALOE_GEL_BOTTLE.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 5.0F))))
							.add(LootItem.lootTableItem(Items.GOLD_INGOT).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 15.0F))))
							.add(LootItem.lootTableItem(YUCCA_BRANCH.get()))
					)
			);

			consumer.accept(Atmospheric.location("chests/kousa_sanctum_fire"), LootTable.lootTable()
					.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
							.add(LootItem.lootTableItem(Items.FLINT_AND_STEEL))));

			consumer.accept(Atmospheric.location("chests/kousa_sanctum_trap"), LootTable.lootTable()
					.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
							.add(LootItem.lootTableItem(Items.SPLASH_POTION)).apply(SetPotionFunction.setPotion(Potions.HARMING))));

			consumer.accept(Atmospheric.location("chests/kousa_sanctum"), LootTable.lootTable()
					.withPool(LootPool.lootPool().setRolls(UniformGenerator.between(3.0F, 5.0F))
							.add(LootItem.lootTableItem(Items.DIAMOND).setWeight(3).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
							.add(LootItem.lootTableItem(Items.IRON_INGOT).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 5.0F))))
							.add(LootItem.lootTableItem(Items.GOLD_INGOT).setWeight(15).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 7.0F))))
							.add(LootItem.lootTableItem(Blocks.BAMBOO).setWeight(15).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
							.add(LootItem.lootTableItem(Items.EMERALD).setWeight(2).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
							.add(LootItem.lootTableItem(Items.BONE).setWeight(20).apply(SetItemCountFunction.setCount(UniformGenerator.between(4.0F, 6.0F))))
							.add(LootItem.lootTableItem(AtmosphericItems.CURRANT.get()).setWeight(16).apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0F, 7.0F))))
							.add(LootItem.lootTableItem(Items.SADDLE).setWeight(3))
							.add(LootItem.lootTableItem(Items.IRON_HORSE_ARMOR))
							.add(LootItem.lootTableItem(Items.GOLDEN_HORSE_ARMOR))
							.add(LootItem.lootTableItem(Items.DIAMOND_HORSE_ARMOR))
							.add(LootItem.lootTableItem(Items.BOOK).apply(EnchantWithLevelsFunction.enchantWithLevels(ConstantValue.exactly(30.0F)).allowTreasure()))
					)
					.withPool(LootPool.lootPool().setRolls(UniformGenerator.between(0.0F, 1.0F))
							.add(LootItem.lootTableItem(Items.SHEARS))
					)
			);

			consumer.accept(Atmospheric.location("chests/village/village_scrubland"), LootTable.lootTable()
					.withPool(LootPool.lootPool().setRolls(UniformGenerator.between(3.0F, 8.0F))
							.add(LootItem.lootTableItem(FIRETHORN.get()).setWeight(1))
							.add(LootItem.lootTableItem(FORSYTHIA.get()).setWeight(1))
							.add(LootItem.lootTableItem(DRY_LAUREL_SAPLING.get()).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))
							.add(LootItem.lootTableItem(MORADO_SAPLING.get()).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))
							.add(LootItem.lootTableItem(BARREL_CACTUS.get()).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F))))
							.add(LootItem.lootTableItem(Items.CARROT).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 7.0F))))
							.add(LootItem.lootTableItem(Items.BREAD).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F))))
							.add(LootItem.lootTableItem(AtmosphericItems.CARMINE_HUSK.get()).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 5.0F))))
							.add(LootItem.lootTableItem(Items.REDSTONE).setWeight(1))
							.add(LootItem.lootTableItem(Items.EMERALD).setWeight(1).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F))))
					)
			);
		}
	}
}