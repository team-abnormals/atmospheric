package com.teamabnormals.atmospheric.core.data.server;

import com.google.common.collect.ImmutableList;
import com.teamabnormals.atmospheric.common.block.*;
import com.teamabnormals.atmospheric.common.block.state.properties.DragonRootsStage;
import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.atmospheric.core.other.AtmosphericLootTables;
import com.teamabnormals.atmospheric.core.registry.AtmosphericEntityTypes;
import com.teamabnormals.atmospheric.core.registry.AtmosphericItems;
import com.teamabnormals.atmospheric.core.registry.AtmosphericMobEffects;
import net.minecraft.advancements.critereon.BlockPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.LocationPredicate;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.HolderLookup.RegistryLookup;
import net.minecraft.core.WritableRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.ProblemReporter;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.storage.loot.*;
import net.minecraft.world.level.storage.loot.LootTable.Builder;
import net.minecraft.world.level.storage.loot.entries.EmptyLootItem;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.*;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.*;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.neoforged.neoforge.common.Tags;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks.*;

public class AtmosphericLootTableProvider extends LootTableProvider {

	public AtmosphericLootTableProvider(PackOutput output, CompletableFuture<Provider> provider) {
		super(output, BuiltInLootTables.all(), ImmutableList.of(
				new LootTableProvider.SubProviderEntry(AtmosphericBlockLoot::new, LootContextParamSets.BLOCK),
				new LootTableProvider.SubProviderEntry(AtmosphericEntityLoot::new, LootContextParamSets.ENTITY),
				new LootTableProvider.SubProviderEntry(AtmosphericChestLoot::new, LootContextParamSets.CHEST),
				new LootTableProvider.SubProviderEntry(AtmosphericArchaeologyLoot::new, LootContextParamSets.ARCHAEOLOGY)
		), provider);
	}

	@Override
	protected void validate(WritableRegistry<LootTable> registry, ValidationContext context, ProblemReporter.Collector collector) {
	}

	private static class AtmosphericBlockLoot extends BlockLootSubProvider {
		private static final Set<Item> EXPLOSION_RESISTANT = Stream.of(Blocks.DRAGON_EGG, Blocks.BEACON, Blocks.CONDUIT, Blocks.SKELETON_SKULL, Blocks.WITHER_SKELETON_SKULL, Blocks.PLAYER_HEAD, Blocks.ZOMBIE_HEAD, Blocks.CREEPER_HEAD, Blocks.DRAGON_HEAD, Blocks.PIGLIN_HEAD, Blocks.SHULKER_BOX, Blocks.BLACK_SHULKER_BOX, Blocks.BLUE_SHULKER_BOX, Blocks.BROWN_SHULKER_BOX, Blocks.CYAN_SHULKER_BOX, Blocks.GRAY_SHULKER_BOX, Blocks.GREEN_SHULKER_BOX, Blocks.LIGHT_BLUE_SHULKER_BOX, Blocks.LIGHT_GRAY_SHULKER_BOX, Blocks.LIME_SHULKER_BOX, Blocks.MAGENTA_SHULKER_BOX, Blocks.ORANGE_SHULKER_BOX, Blocks.PINK_SHULKER_BOX, Blocks.PURPLE_SHULKER_BOX, Blocks.RED_SHULKER_BOX, Blocks.WHITE_SHULKER_BOX, Blocks.YELLOW_SHULKER_BOX).map(ItemLike::asItem).collect(Collectors.toSet());

		private static final LootItemCondition.Builder HAS_SHEARS = MatchTool.toolMatches(ItemPredicate.Builder.item().of(Tags.Items.TOOLS_SHEAR));

		protected LootItemCondition.Builder doesNotHaveSilkTouch() {
			return this.hasSilkTouch().invert();
		}

		private LootItemCondition.Builder hasShearsOrSilkTouch() {
			return HAS_SHEARS.or(this.hasSilkTouch());
		}

		private LootItemCondition.Builder doesNotHaveShearsOrSilkTouch() {
			return this.hasShearsOrSilkTouch().invert();
		}

		private static final float[] GRIMWOOD_LEAVES_SAPLING_CHANCES = new float[]{0.075F, 0.09375F, 0.125F, 0.15F};
		private static final float[] NORMAL_LEAVES_SAPLING_CHANCES = new float[]{0.05F, 0.0625F, 0.083333336F, 0.1F};
		private static final float[] CURRANT_LEAVES_STALK_CHANCES = new float[]{0.04F, 0.044444446F, 0.05F, 0.066666670F, 0.2F};

		protected AtmosphericBlockLoot(Provider provider) {
			super(EXPLOSION_RESISTANT, FeatureFlags.REGISTRY.allFlags(), provider);
		}

		@Override
		public void generate() {
			RegistryLookup<Enchantment> enchantments = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
			Holder<Enchantment> fortune = enchantments.getOrThrow(Enchantments.FORTUNE);

			this.dropSelf(WARM_MONKEY_BRUSH.get());
			this.dropPottedContents(POTTED_WARM_MONKEY_BRUSH.get());
			this.dropSelf(HOT_MONKEY_BRUSH.get());
			this.dropPottedContents(POTTED_HOT_MONKEY_BRUSH.get());
			this.dropSelf(SCALDING_MONKEY_BRUSH.get());
			this.dropPottedContents(POTTED_SCALDING_MONKEY_BRUSH.get());
			this.add(PASSION_VINE.get(), (block) -> applyExplosionDecay(block, LootTable.lootTable().withPool(LootPool.lootPool().when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(PASSION_VINE.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(AloeVeraBlock.AGE, 4))).add(LootItem.lootTableItem(AtmosphericItems.PASSION_FRUIT.get())).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))).withPool(LootPool.lootPool().add(LootItem.lootTableItem(PASSION_VINE.get())))));
			this.add(PASSION_VINE_BUNDLE.get(), (block) -> LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).when(hasShearsOrSilkTouch()).add(LootItem.lootTableItem(block))));
			this.add(WATER_HYACINTH.get(), (block) -> createSinglePropConditionTable(block, DoublePlantBlock.HALF, DoubleBlockHalf.UPPER));
			this.dropPottedContents(POTTED_WATER_HYACINTH.get());
			this.dropSelf(PASSION_FRUIT_CRATE.get());
			this.dropSelf(SHIMMERING_PASSION_FRUIT_CRATE.get());

			this.add(ARID_SPROUTS.get(), BlockLootSubProvider::createShearsOnlyDrop);
			this.add(ALOE_VERA.get(), block -> applyExplosionDecay(block, LootTable.lootTable()
					.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
							.add(LootItem.lootTableItem(AtmosphericItems.ALOE_LEAVES.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))).apply(ApplyBonusCount.addUniformBonusCount(fortune)))
							.when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(AloeVeraBlock.AGE, 5)))))
					.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
							.add(LootItem.lootTableItem(AtmosphericItems.ALOE_KERNELS.get()))));
			this.add(TALL_ALOE_VERA.get(), this::createTallAloeVeraDrops);
			this.dropSelf(ALOE_BUNDLE.get());
			this.dropSelf(ALOE_GEL_BLOCK.get());
			this.dropPottedContents(POTTED_ALOE_VERA.get());
			this.add(BARREL_CACTUS.get(), this::createBarrelCactusDrops);
			this.add(SNOWY_BARREL_CACTUS.get(), this::createBarrelCactusDrops);
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
			CandleGateauBlock.getCandleGateaus().forEach((block -> this.add(block, createCandleCakeDrops(block.getCandle()))));
			this.add(YUCCA_BRANCH.get(), (block) -> createShearsDispatchTable(block, applyExplosionDecay(block, LootItem.lootTableItem(Items.STICK).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F))))));
			this.add(YUCCA_BUNDLE.get(), (block) -> createSilkTouchOrShearsDispatchTable(block, applyExplosionDecay(block, LootItem.lootTableItem(AtmosphericItems.YUCCA_FRUIT.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 6.0F))).apply(ApplyBonusCount.addUniformBonusCount(fortune)).apply(LimitCount.limitCount(IntRange.upperBound(8))))));
			this.add(ROASTED_YUCCA_BUNDLE.get(), (block) -> createSilkTouchOrShearsDispatchTable(block, applyExplosionDecay(block, LootItem.lootTableItem(AtmosphericItems.ROASTED_YUCCA_FRUIT.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 6.0F))).apply(ApplyBonusCount.addUniformBonusCount(fortune)).apply(LimitCount.limitCount(IntRange.upperBound(8))))));
			this.dropSelf(YUCCA_CASK.get());
			this.dropSelf(ROASTED_YUCCA_CASK.get());

			this.add(AGAVE.get(), BlockLootSubProvider::createShearsOnlyDrop);
			this.dropPottedContents(POTTED_AGAVE.get());
			this.add(GOLDEN_GROWTHS.get(), BlockLootSubProvider::createShearsOnlyDrop);
			this.dropPottedContents(POTTED_GOLDEN_GROWTHS.get());
			this.add(CRUSTOSE.get(), (block) -> createSingleItemTableWithSilkTouch(block, Blocks.DIRT));
			this.add(CRUSTOSE_LOG.get(), (block) -> createSingleItemTableWithSilkTouch(block, ASPEN_LOG.get()));
			this.add(CRUSTOSE_WOOD.get(), (block) -> createSingleItemTableWithSilkTouch(block, ASPEN_WOOD.get()));
			this.dropOther(CRUSTOSE_PATH.get(), Blocks.DIRT);

			this.dropSelf(CURRANT_STALK.get());
			this.dropSelf(CURRANT_STALK_BUNDLE.get());
			this.dropSelf(CURRANT_CRATE.get());
			this.add(HANGING_CURRANT.get(), BlockLootSubProvider::createShearsOnlyDrop);
			this.add(CURRANT_SEEDLING.get(), BlockLootSubProvider::createShearsOnlyDrop);
			this.dropPottedContents(POTTED_CURRANT_SEEDLING.get());
			this.add(CURRANT_LEAF_PILE.get(), this::createLeafPileDrops);
			this.add(CURRANT_LEAVES.get(), block -> createSilkTouchOrShearsDispatchTable(block, applyExplosionDecay(block, LootItem.lootTableItem(CURRANT_STALK.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))).when(BonusLevelTableCondition.bonusLevelFlatChance(fortune, CURRANT_LEAVES_STALK_CHANCES))));
			this.dropOther(SNOWY_BAMBOO.get(), Items.BAMBOO);
			this.dropOther(SNOWY_BAMBOO_SAPLING.get(), Items.BAMBOO);
			this.dropPottedContents(POTTED_SNOWY_BAMBOO.get());

			this.add(GRIMWEB.get(), (block) -> createSilkTouchOrShearsDispatchTable(block, applyExplosionCondition(block, LootItem.lootTableItem(Items.STRING))));

			this.dropSelf(CARMINE_BLOCK.get());
			this.dropSelf(CARMINE_SHINGLES.get());
			this.dropSelf(CARMINE_SHINGLE_STAIRS.get());
			this.dropSelf(CARMINE_SHINGLE_WALL.get());
			this.add(CARMINE_SHINGLE_SLAB.get(), this::createSlabItemTable);
			this.dropSelf(CHISELED_CARMINE_SHINGLES.get());
			this.dropSelf(CARMINE_PAVEMENT.get());
			this.dropSelf(CARMINE_PAVEMENT_STAIRS.get());
			this.dropSelf(CARMINE_PAVEMENT_WALL.get());
			this.add(CARMINE_PAVEMENT_SLAB.get(), this::createSlabItemTable);
			this.dropSelf(FIRETHORN.get());
			this.dropPottedContents(POTTED_FIRETHORN.get());
			this.dropSelf(FORSYTHIA.get());
			this.dropPottedContents(POTTED_FORSYTHIA.get());
			this.dropSelf(DRAGON_FRUIT_CRATE.get());
			this.dropSelf(GOLDEN_DRAGON_FRUIT_CRATE.get());
			this.add(DRAGON_ROOTS.get(), this::createDragonRootsDrops);

			this.dropSelf(ARID_SAND.get());
			this.add(SUSPICIOUS_ARID_SAND.get(), noDrop());
			this.dropSelf(ARID_SANDSTONE.get());
			this.dropSelf(ARID_SANDSTONE_STAIRS.get());
			this.add(ARID_SANDSTONE_SLAB.get(), this::createSlabItemTable);
			this.dropSelf(ARID_SANDSTONE_WALL.get());
			this.dropSelf(SMOOTH_ARID_SANDSTONE.get());
			this.dropSelf(SMOOTH_ARID_SANDSTONE_STAIRS.get());
			this.add(SMOOTH_ARID_SANDSTONE_SLAB.get(), this::createSlabItemTable);
			this.dropSelf(CUT_ARID_SANDSTONE.get());
			this.add(CUT_ARID_SANDSTONE_SLAB.get(), this::createSlabItemTable);
			this.dropSelf(CHISELED_ARID_SANDSTONE.get());

			this.dropSelf(RED_ARID_SAND.get());
			this.add(SUSPICIOUS_RED_ARID_SAND.get(), noDrop());
			this.dropSelf(RED_ARID_SANDSTONE.get());
			this.dropSelf(RED_ARID_SANDSTONE_STAIRS.get());
			this.add(RED_ARID_SANDSTONE_SLAB.get(), this::createSlabItemTable);
			this.dropSelf(RED_ARID_SANDSTONE_WALL.get());
			this.dropSelf(SMOOTH_RED_ARID_SANDSTONE.get());
			this.dropSelf(SMOOTH_RED_ARID_SANDSTONE_STAIRS.get());
			this.add(SMOOTH_RED_ARID_SANDSTONE_SLAB.get(), this::createSlabItemTable);
			this.dropSelf(CUT_RED_ARID_SANDSTONE.get());
			this.add(CUT_RED_ARID_SANDSTONE_SLAB.get(), this::createSlabItemTable);
			this.dropSelf(CHISELED_RED_ARID_SANDSTONE.get());

			this.dropWhenSilkTouch(ARID_GLASS.get());
			this.dropWhenSilkTouch(ARID_GLASS_PANE.get());

			this.dropSelf(IVORY_TRAVERTINE.get());
			this.dropSelf(IVORY_TRAVERTINE_STAIRS.get());
			this.add(IVORY_TRAVERTINE_SLAB.get(), this::createSlabItemTable);
			this.dropSelf(IVORY_TRAVERTINE_WALL.get());
			this.dropSelf(CUT_IVORY_TRAVERTINE.get());
			this.dropSelf(CHISELED_IVORY_TRAVERTINE.get());
			this.dropSelf(PEACH_TRAVERTINE.get());
			this.dropSelf(PEACH_TRAVERTINE_STAIRS.get());
			this.add(PEACH_TRAVERTINE_SLAB.get(), this::createSlabItemTable);
			this.dropSelf(PEACH_TRAVERTINE_WALL.get());
			this.dropSelf(CUT_PEACH_TRAVERTINE.get());
			this.dropSelf(CHISELED_PEACH_TRAVERTINE.get());
			this.dropSelf(PERSIMMON_TRAVERTINE.get());
			this.dropSelf(PERSIMMON_TRAVERTINE_STAIRS.get());
			this.add(PERSIMMON_TRAVERTINE_SLAB.get(), this::createSlabItemTable);
			this.dropSelf(PERSIMMON_TRAVERTINE_WALL.get());
			this.dropSelf(CUT_PERSIMMON_TRAVERTINE.get());
			this.dropSelf(CHISELED_PERSIMMON_TRAVERTINE.get());
			this.dropSelf(SAFFRON_TRAVERTINE.get());
			this.dropSelf(SAFFRON_TRAVERTINE_STAIRS.get());
			this.add(SAFFRON_TRAVERTINE_SLAB.get(), this::createSlabItemTable);
			this.dropSelf(SAFFRON_TRAVERTINE_WALL.get());
			this.dropSelf(CUT_SAFFRON_TRAVERTINE.get());
			this.dropSelf(CHISELED_SAFFRON_TRAVERTINE.get());

			this.dropSelf(DOLERITE.get());
			this.dropSelf(DOLERITE_STAIRS.get());
			this.add(DOLERITE_SLAB.get(), this::createSlabItemTable);
			this.dropSelf(DOLERITE_WALL.get());
			this.dropSelf(POLISHED_DOLERITE.get());
			this.dropSelf(POLISHED_DOLERITE_STAIRS.get());
			this.add(POLISHED_DOLERITE_SLAB.get(), this::createSlabItemTable);
			this.dropSelf(POLISHED_DOLERITE_WALL.get());

			this.dropSelf(ROSEWOOD_PLANKS.get());
			this.dropSelf(ROSEWOOD_LOG.get());
			this.dropSelf(ROSEWOOD.get());
			this.dropSelf(STRIPPED_ROSEWOOD_LOG.get());
			this.dropSelf(STRIPPED_ROSEWOOD.get());
			this.dropSelf(ROSEWOOD_SIGNS.getFirst().get());
			this.dropSelf(ROSEWOOD_HANGING_SIGNS.getFirst().get());
			this.dropSelf(ROSEWOOD_PRESSURE_PLATE.get());
			this.dropSelf(ROSEWOOD_TRAPDOOR.get());
			this.dropSelf(ROSEWOOD_BUTTON.get());
			this.dropSelf(ROSEWOOD_STAIRS.get());
			this.dropSelf(ROSEWOOD_FENCE.get());
			this.dropSelf(ROSEWOOD_FENCE_GATE.get());
			this.dropSelf(ROSEWOOD_BOARDS.get());
			this.add(ROSEWOOD_LEAF_PILE.get(), this::createLeafPileDrops);
			this.dropSelf(ROSEWOOD_SAPLING.get());
			this.dropPottedContents(POTTED_ROSEWOOD_SAPLING.get());
			this.dropSelf(ROSEWOOD_LADDER.get());
			this.add(ROSEWOOD_SLAB.get(), this::createSlabItemTable);
			this.add(ROSEWOOD_DOOR.get(), this::createDoorTable);
			this.add(ROSEWOOD_BEEHIVE.get(), this::createBeeHiveDrop);
			this.add(ROSEWOOD_CHEST.get(), this::createNameableBlockEntityTable);
			this.add(TRAPPED_ROSEWOOD_CHEST.get(), this::createNameableBlockEntityTable);
			this.add(ROSEWOOD_BOOKSHELF.get(), (block) -> createSingleItemTableWithSilkTouch(block, Items.BOOK, ConstantValue.exactly(3.0F)));
			this.dropWhenSilkTouch(CHISELED_ROSEWOOD_BOOKSHELF.get());
			this.add(ROSEWOOD_LEAVES.get(), (block) -> createLeavesDrops(block, ROSEWOOD_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

			this.dropSelf(MORADO_PLANKS.get());
			this.dropSelf(MORADO_LOG.get());
			this.dropSelf(MORADO_WOOD.get());
			this.dropSelf(STRIPPED_MORADO_LOG.get());
			this.dropSelf(STRIPPED_MORADO_WOOD.get());
			this.dropSelf(MORADO_SIGNS.getFirst().get());
			this.dropSelf(MORADO_HANGING_SIGNS.getFirst().get());
			this.dropSelf(MORADO_PRESSURE_PLATE.get());
			this.dropSelf(MORADO_TRAPDOOR.get());
			this.dropSelf(MORADO_BUTTON.get());
			this.dropSelf(MORADO_STAIRS.get());
			this.dropSelf(MORADO_FENCE.get());
			this.dropSelf(MORADO_FENCE_GATE.get());
			this.dropSelf(MORADO_BOARDS.get());
			this.add(MORADO_LEAF_PILE.get(), this::createLeafPileDrops);
			this.dropSelf(MORADO_SAPLING.get());
			this.dropPottedContents(POTTED_MORADO_SAPLING.get());
			this.dropSelf(MORADO_LADDER.get());
			this.add(MORADO_SLAB.get(), this::createSlabItemTable);
			this.add(MORADO_DOOR.get(), this::createDoorTable);
			this.add(MORADO_BEEHIVE.get(), this::createBeeHiveDrop);
			this.add(MORADO_CHEST.get(), this::createNameableBlockEntityTable);
			this.add(TRAPPED_MORADO_CHEST.get(), this::createNameableBlockEntityTable);
			this.add(MORADO_BOOKSHELF.get(), (block) -> createSingleItemTableWithSilkTouch(block, Items.BOOK, ConstantValue.exactly(3.0F)));
			this.dropWhenSilkTouch(CHISELED_MORADO_BOOKSHELF.get());
			this.add(MORADO_LEAVES.get(), (block) -> createLeavesDrops(block, MORADO_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

			this.add(FLOWERING_MORADO_LEAF_PILE.get(), this::createLeafPileDrops);
			this.add(FLOWERING_MORADO_LEAVES.get(), block -> createLeavesDrops(block, MORADO_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).when(doesNotHaveShearsOrSilkTouch()).add(applyExplosionDecay(block, LootItem.lootTableItem(AtmosphericItems.YELLOW_BLOSSOMS.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))).when(BonusLevelTableCondition.bonusLevelFlatChance(fortune, CURRANT_LEAVES_STALK_CHANCES)))));

			this.dropSelf(YUCCA_PLANKS.get());
			this.dropSelf(YUCCA_LOG.get());
			this.dropSelf(YUCCA_WOOD.get());
			this.dropSelf(STRIPPED_YUCCA_LOG.get());
			this.dropSelf(STRIPPED_YUCCA_WOOD.get());
			this.dropSelf(YUCCA_SIGNS.getFirst().get());
			this.dropSelf(YUCCA_HANGING_SIGNS.getFirst().get());
			this.dropSelf(YUCCA_PRESSURE_PLATE.get());
			this.dropSelf(YUCCA_TRAPDOOR.get());
			this.dropSelf(YUCCA_BUTTON.get());
			this.dropSelf(YUCCA_STAIRS.get());
			this.dropSelf(YUCCA_FENCE.get());
			this.dropSelf(YUCCA_FENCE_GATE.get());
			this.dropSelf(YUCCA_BOARDS.get());
			this.add(YUCCA_LEAF_PILE.get(), this::createLeafPileDrops);
			this.dropSelf(YUCCA_SAPLING.get());
			this.dropPottedContents(POTTED_YUCCA_SAPLING.get());
			this.dropSelf(YUCCA_LADDER.get());
			this.add(YUCCA_SLAB.get(), this::createSlabItemTable);
			this.add(YUCCA_DOOR.get(), this::createDoorTable);
			this.add(YUCCA_BEEHIVE.get(), this::createBeeHiveDrop);
			this.add(YUCCA_CHEST.get(), this::createNameableBlockEntityTable);
			this.add(TRAPPED_YUCCA_CHEST.get(), this::createNameableBlockEntityTable);
			this.add(YUCCA_BOOKSHELF.get(), (block) -> createSingleItemTableWithSilkTouch(block, Items.BOOK, ConstantValue.exactly(3.0F)));
			this.dropWhenSilkTouch(CHISELED_YUCCA_BOOKSHELF.get());
			this.add(YUCCA_LEAVES.get(), (block) -> createLeavesDrops(block, YUCCA_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

			this.dropSelf(LAUREL_PLANKS.get());
			this.dropSelf(LAUREL_LOG.get());
			this.dropSelf(LAUREL_WOOD.get());
			this.dropSelf(STRIPPED_LAUREL_LOG.get());
			this.dropSelf(STRIPPED_LAUREL_WOOD.get());
			this.dropSelf(LAUREL_SIGNS.getFirst().get());
			this.dropSelf(LAUREL_HANGING_SIGNS.getFirst().get());
			this.dropSelf(LAUREL_PRESSURE_PLATE.get());
			this.dropSelf(LAUREL_TRAPDOOR.get());
			this.dropSelf(LAUREL_BUTTON.get());
			this.dropSelf(LAUREL_STAIRS.get());
			this.dropSelf(LAUREL_FENCE.get());
			this.dropSelf(LAUREL_FENCE_GATE.get());
			this.dropSelf(LAUREL_BOARDS.get());
			this.add(LAUREL_LEAF_PILE.get(), this::createLeafPileDrops);
			this.dropSelf(LAUREL_SAPLING.get());
			this.dropPottedContents(POTTED_LAUREL_SAPLING.get());
			this.dropSelf(LAUREL_LADDER.get());
			this.add(LAUREL_SLAB.get(), this::createSlabItemTable);
			this.add(LAUREL_DOOR.get(), this::createDoorTable);
			this.add(LAUREL_BEEHIVE.get(), this::createBeeHiveDrop);
			this.add(LAUREL_CHEST.get(), this::createNameableBlockEntityTable);
			this.add(TRAPPED_LAUREL_CHEST.get(), this::createNameableBlockEntityTable);
			this.add(LAUREL_BOOKSHELF.get(), (block) -> createSingleItemTableWithSilkTouch(block, Items.BOOK, ConstantValue.exactly(3.0F)));
			this.dropWhenSilkTouch(CHISELED_LAUREL_BOOKSHELF.get());
			this.add(LAUREL_LEAVES.get(), (block) -> createLeavesDrops(block, LAUREL_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

			this.add(DRY_LAUREL_LEAF_PILE.get(), this::createLeafPileDrops);
			this.dropSelf(DRY_LAUREL_SAPLING.get());
			this.dropPottedContents(POTTED_DRY_LAUREL_SAPLING.get());
			this.add(DRY_LAUREL_LEAVES.get(), (block) -> createLeavesDrops(block, DRY_LAUREL_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

			this.add(ORANGE.get(), block -> createOrangeDrops(block, AtmosphericItems.ORANGE.get()));
			this.add(BLOOD_ORANGE.get(), block -> createOrangeDrops(block, AtmosphericItems.BLOOD_ORANGE.get()));
			this.dropSelf(ORANGE_CRATE.get());
			this.dropSelf(BLOOD_ORANGE_CRATE.get());

			this.dropSelf(ASPEN_PLANKS.get());
			this.dropSelf(ASPEN_LOG.get());
			this.dropSelf(ASPEN_WOOD.get());
			this.dropSelf(STRIPPED_ASPEN_LOG.get());
			this.dropSelf(STRIPPED_ASPEN_WOOD.get());
			this.add(WATCHFUL_ASPEN_LOG.get(), (block) -> createSingleItemTableWithSilkTouch(block, ASPEN_LOG.get()));
			this.add(WATCHFUL_ASPEN_WOOD.get(), (block) -> createSingleItemTableWithSilkTouch(block, ASPEN_WOOD.get()));
			this.dropSelf(ASPEN_SIGNS.getFirst().get());
			this.dropSelf(ASPEN_HANGING_SIGNS.getFirst().get());
			this.dropSelf(ASPEN_PRESSURE_PLATE.get());
			this.dropSelf(ASPEN_TRAPDOOR.get());
			this.dropSelf(ASPEN_BUTTON.get());
			this.dropSelf(ASPEN_STAIRS.get());
			this.dropSelf(ASPEN_FENCE.get());
			this.dropSelf(ASPEN_FENCE_GATE.get());
			this.dropSelf(ASPEN_BOARDS.get());
			this.add(ASPEN_LEAF_PILE.get(), this::createLeafPileDrops);
			this.dropSelf(ASPEN_SAPLING.get());
			this.dropPottedContents(POTTED_ASPEN_SAPLING.get());
			this.dropSelf(ASPEN_LADDER.get());
			this.add(ASPEN_SLAB.get(), this::createSlabItemTable);
			this.add(ASPEN_DOOR.get(), this::createDoorTable);
			this.add(ASPEN_BEEHIVE.get(), this::createBeeHiveDrop);
			this.add(ASPEN_CHEST.get(), this::createNameableBlockEntityTable);
			this.add(TRAPPED_ASPEN_CHEST.get(), this::createNameableBlockEntityTable);
			this.add(ASPEN_BOOKSHELF.get(), (block) -> createSingleItemTableWithSilkTouch(block, Items.BOOK, ConstantValue.exactly(3.0F)));
			this.dropWhenSilkTouch(CHISELED_ASPEN_BOOKSHELF.get());
			this.add(ASPEN_LEAVES.get(), (block) -> createLeavesDrops(block, ASPEN_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));
			this.add(GREEN_ASPEN_LEAVES.get(), (block) -> createLeavesDrops(block, GREEN_ASPEN_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));
			this.add(GREEN_ASPEN_LEAF_PILE.get(), this::createLeafPileDrops);
			this.dropSelf(GREEN_ASPEN_SAPLING.get());
			this.dropPottedContents(POTTED_GREEN_ASPEN_SAPLING.get());

			this.dropSelf(KOUSA_PLANKS.get());
			this.dropSelf(KOUSA_LOG.get());
			this.dropSelf(KOUSA_WOOD.get());
			this.dropSelf(STRIPPED_KOUSA_LOG.get());
			this.dropSelf(STRIPPED_KOUSA_WOOD.get());
			this.dropSelf(KOUSA_SIGNS.getFirst().get());
			this.dropSelf(KOUSA_HANGING_SIGNS.getFirst().get());
			this.dropSelf(KOUSA_PRESSURE_PLATE.get());
			this.dropSelf(KOUSA_TRAPDOOR.get());
			this.dropSelf(KOUSA_BUTTON.get());
			this.dropSelf(KOUSA_STAIRS.get());
			this.dropSelf(KOUSA_FENCE.get());
			this.dropSelf(KOUSA_FENCE_GATE.get());
			this.dropSelf(KOUSA_BOARDS.get());
			this.add(KOUSA_LEAF_PILE.get(), this::createLeafPileDrops);
			this.dropSelf(KOUSA_SAPLING.get());
			this.dropPottedContents(POTTED_KOUSA_SAPLING.get());
			this.dropSelf(KOUSA_LADDER.get());
			this.add(KOUSA_SLAB.get(), this::createSlabItemTable);
			this.add(KOUSA_DOOR.get(), this::createDoorTable);
			this.add(KOUSA_BEEHIVE.get(), this::createBeeHiveDrop);
			this.add(KOUSA_CHEST.get(), this::createNameableBlockEntityTable);
			this.add(TRAPPED_KOUSA_CHEST.get(), this::createNameableBlockEntityTable);
			this.add(KOUSA_BOOKSHELF.get(), (block) -> createSingleItemTableWithSilkTouch(block, Items.BOOK, ConstantValue.exactly(3.0F)));
			this.dropWhenSilkTouch(CHISELED_KOUSA_BOOKSHELF.get());
			this.add(KOUSA_LEAVES.get(), (block) -> createLeavesDrops(block, KOUSA_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

			this.dropSelf(GRIMWOOD_PLANKS.get());
			this.dropSelf(GRIMWOOD_LOG.get());
			this.dropSelf(GRIMWOOD.get());
			this.dropSelf(STRIPPED_GRIMWOOD_LOG.get());
			this.dropSelf(STRIPPED_GRIMWOOD.get());
			this.dropSelf(GRIMWOOD_SIGNS.getFirst().get());
			this.dropSelf(GRIMWOOD_HANGING_SIGNS.getFirst().get());
			this.dropSelf(GRIMWOOD_PRESSURE_PLATE.get());
			this.dropSelf(GRIMWOOD_TRAPDOOR.get());
			this.dropSelf(GRIMWOOD_BUTTON.get());
			this.dropSelf(GRIMWOOD_STAIRS.get());
			this.dropSelf(GRIMWOOD_FENCE.get());
			this.dropSelf(GRIMWOOD_FENCE_GATE.get());
			this.dropSelf(GRIMWOOD_BOARDS.get());
			this.add(GRIMWOOD_LEAF_PILE.get(), this::createLeafPileDrops);
			this.dropSelf(GRIMWOOD_SAPLING.get());
			this.dropPottedContents(POTTED_GRIMWOOD_SAPLING.get());
			this.dropSelf(GRIMWOOD_LADDER.get());
			this.add(GRIMWOOD_SLAB.get(), this::createSlabItemTable);
			this.add(GRIMWOOD_DOOR.get(), this::createDoorTable);
			this.add(GRIMWOOD_BEEHIVE.get(), this::createBeeHiveDrop);
			this.add(GRIMWOOD_CHEST.get(), this::createNameableBlockEntityTable);
			this.add(TRAPPED_GRIMWOOD_CHEST.get(), this::createNameableBlockEntityTable);
			this.add(GRIMWOOD_BOOKSHELF.get(), (block) -> createSingleItemTableWithSilkTouch(block, Items.BOOK, ConstantValue.exactly(3.0F)));
			this.dropWhenSilkTouch(CHISELED_GRIMWOOD_BOOKSHELF.get());
			this.add(GRIMWOOD_LEAVES.get(), (block) -> createLeavesDrops(block, GRIMWOOD_SAPLING.get(), GRIMWOOD_LEAVES_SAPLING_CHANCES));
		}

		protected LootTable.Builder createDragonRootsDrops(Block block) {
			return LootTable.lootTable()
					.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).when(doesNotHaveShearsOrSilkTouch()).add(applyExplosionDecay(block, LootItem.lootTableItem(block).when(InvertedLootItemCondition.invert(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DragonRootsBlock.TOP_STAGE, DragonRootsStage.NONE)))))))
					.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).when(doesNotHaveShearsOrSilkTouch()).add(applyExplosionDecay(block, LootItem.lootTableItem(block).when(InvertedLootItemCondition.invert(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DragonRootsBlock.BOTTOM_STAGE, DragonRootsStage.NONE)))))));
		}

		protected LootTable.Builder createBarrelCactusDrops(Block block) {
			return LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(applyExplosionDecay(block, LootItem.lootTableItem(BARREL_CACTUS.get()).apply(List.of(1, 2, 3, 4), (val) -> SetItemCountFunction.setCount(ConstantValue.exactly((float) val)).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(BarrelCactusBlock.AGE, val - 1)))))));
		}

		protected Builder createLeafPileDrops(Block block) {
			return createMultifaceBlockDrops(block, MatchTool.toolMatches(ItemPredicate.Builder.item().of(Tags.Items.TOOLS_SHEAR)));
		}

		protected Builder createTallAloeVeraDrops(Block block) {
			RegistryLookup<Enchantment> enchantments = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
			Holder<Enchantment> fortune = enchantments.getOrThrow(Enchantments.FORTUNE);

			return applyExplosionDecay(block, LootTable.lootTable()
					.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
							.when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER)))
							.add(LootItem.lootTableItem(AtmosphericItems.ALOE_LEAVES.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))).apply(ApplyBonusCount.addUniformBonusCount(fortune))))
					.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
							.when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER)))
							.add(LootItem.lootTableItem(AtmosphericItems.YELLOW_BLOSSOMS.get()).apply(List.of(6, 7, 8), (val) -> SetItemCountFunction.setCount(ConstantValue.exactly((float) val - 5.0F)).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(AloeVeraTallBlock.AGE, val))))))
					.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
							.when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER)))
							.add(LootItem.lootTableItem(AtmosphericItems.ALOE_KERNELS.get())))
			);
		}

		protected LootTable.Builder createOrangeDrops(Block block, Item item) {
			return applyExplosionDecay(block, LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(item).apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F)).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(OrangeBlock.ORANGES, 2)))))));
		}

		protected static LootTable.Builder createDoublePlantDrops(Block large, Block big) {
			LootPoolEntryContainer.Builder<?> builder = LootItem.lootTableItem(big).apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F)));
			return LootTable.lootTable().withPool(LootPool.lootPool().add(builder).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(large).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER))).when(LocationCheck.checkLocation(LocationPredicate.Builder.location().setBlock(BlockPredicate.Builder.block().of(large).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER))), new BlockPos(0, 1, 0)))).withPool(LootPool.lootPool().add(builder).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(large).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER))).when(LocationCheck.checkLocation(LocationPredicate.Builder.location().setBlock(BlockPredicate.Builder.block().of(large).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER))), new BlockPos(0, -1, 0))));
		}

		@Override
		public Iterable<Block> getKnownBlocks() {
			return BuiltInRegistries.BLOCK.stream().filter(block -> BuiltInRegistries.BLOCK.getKey(block).getNamespace().equals(Atmospheric.MOD_ID)).collect(Collectors.toSet());
		}
	}

	private static class AtmosphericEntityLoot extends EntityLootSubProvider {

		protected AtmosphericEntityLoot(Provider provider) {
			super(FeatureFlags.REGISTRY.allFlags(), provider);
		}

		@Override
		public void generate() {
			this.add(AtmosphericEntityTypes.TETRA.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(Items.TROPICAL_FISH).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F))))).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(Items.BONE_MEAL)).when(LootItemRandomChanceCondition.randomChance(0.05F))));
			this.add(AtmosphericEntityTypes.COCHINEAL.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(AtmosphericItems.CARMINE_HUSK.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(4.0F, 7.0F))).apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 1.0F))))));
		}

		@Override
		public Stream<EntityType<?>> getKnownEntityTypes() {
			return BuiltInRegistries.ENTITY_TYPE.stream().filter(entity -> BuiltInRegistries.ENTITY_TYPE.getKey(entity).getNamespace().equals(Atmospheric.MOD_ID));
		}
	}

	private record AtmosphericChestLoot(Provider registries) implements LootTableSubProvider {

		@Override
		public void generate(BiConsumer<ResourceKey<LootTable>, Builder> consumer) {
			consumer.accept(AtmosphericLootTables.ARID_GARDEN, LootTable.lootTable()
					.withPool(LootPool.lootPool().setRolls(UniformGenerator.between(1.0F, 1.0F))
							.add(LootItem.lootTableItem(GRIMWOOD_SAPLING.get()))
					)
					.withPool(LootPool.lootPool().setRolls(UniformGenerator.between(1.0F, 1.0F))
							.add(LootItem.lootTableItem(AtmosphericItems.GOLDEN_DRAGON_FRUIT.get()).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F))))
							.add(LootItem.lootTableItem(Items.IRON_HOE).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
									.apply(EnchantWithLevelsFunction.enchantWithLevels(registries, UniformGenerator.between(20.0F, 39.0F))))
							.add(LootItem.lootTableItem(Items.POTION).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F)))
									.apply(SetPotionFunction.setPotion(AtmosphericMobEffects.RELIEF_LONG)))
					)
					.withPool(LootPool.lootPool().setRolls(UniformGenerator.between(3.0F, 6.0F))
							.add(LootItem.lootTableItem(Items.IRON_HOE).setWeight(5).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F))))
							.add(LootItem.lootTableItem(Items.FLOWER_POT).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
							.add(LootItem.lootTableItem(Items.HONEY_BOTTLE).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
							.add(LootItem.lootTableItem(Items.BRICK).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 6.0F))))
							.add(LootItem.lootTableItem(Items.STICK).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0F, 8.0F))))

							.add(LootItem.lootTableItem(YUCCA_GATEAU.get()).setWeight(5).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F))))
							.add(LootItem.lootTableItem(YUCCA_BRANCH.get()).setWeight(5).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F))))
							.add(LootItem.lootTableItem(YUCCA_SAPLING.get()).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
							.add(LootItem.lootTableItem(GILIA.get()).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
							.add(LootItem.lootTableItem(AtmosphericItems.ALOE_LEAVES.get()).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
							.add(LootItem.lootTableItem(AtmosphericItems.ALOE_KERNELS.get()).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
							.add(LootItem.lootTableItem(AtmosphericItems.ROASTED_YUCCA_FRUIT.get()).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F))))
							.add(LootItem.lootTableItem(BARREL_CACTUS.get()).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 6.0F))))
							.add(LootItem.lootTableItem(AGAVE.get()).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 6.0F))))
							.add(LootItem.lootTableItem(YUCCA_FLOWER.get()).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 6.0F))))
							.add(LootItem.lootTableItem(AtmosphericItems.YELLOW_BLOSSOMS.get()).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0F, 8.0F))))
							.add(LootItem.lootTableItem(AtmosphericItems.YUCCA_FRUIT.get()).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0F, 8.0F))))

							.add(LootItem.lootTableItem(Items.SUSPICIOUS_STEW).setWeight(5).apply(SetStewEffectFunction.stewEffect()
									.withEffect(AtmosphericMobEffects.RELIEF, UniformGenerator.between(7.0F, 10.0F))
									.withEffect(AtmosphericMobEffects.WORSENING, UniformGenerator.between(5.0F, 7.0F))
									.withEffect(AtmosphericMobEffects.PERSISTENCE, UniformGenerator.between(7.0F, 10.0F))
									.withEffect(MobEffects.POISON, UniformGenerator.between(10.0F, 20.0F))
									.withEffect(MobEffects.SATURATION, UniformGenerator.between(7.0F, 10.0F))
									.withEffect(MobEffects.WEAKNESS, UniformGenerator.between(6.0F, 8.0F))))
					)
					.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
							.add(EmptyLootItem.emptyItem().setWeight(2))
							.add(LootItem.lootTableItem(AtmosphericItems.DRUID_ARMOR_TRIM_SMITHING_TEMPLATE.get()).setWeight(1).apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F)))))
			);

			consumer.accept(AtmosphericLootTables.KOUSA_SANCTUM_FIRE, LootTable.lootTable()
					.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
							.add(LootItem.lootTableItem(Items.FLINT_AND_STEEL))));

			consumer.accept(AtmosphericLootTables.KOUSA_SANCTUM_TRAP, LootTable.lootTable()
					.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
							.add(LootItem.lootTableItem(Items.SPLASH_POTION)).apply(SetPotionFunction.setPotion(Potions.HARMING))));

			consumer.accept(AtmosphericLootTables.KOUSA_SANCTUM, LootTable.lootTable()
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
							.add(LootItem.lootTableItem(Items.BOOK).apply(EnchantWithLevelsFunction.enchantWithLevels(this.registries, ConstantValue.exactly(30.0F))))
					)
					.withPool(LootPool.lootPool().setRolls(UniformGenerator.between(0.0F, 1.0F))
							.add(LootItem.lootTableItem(Items.SHEARS))
					)
					.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
							.add(EmptyLootItem.emptyItem().setWeight(2))
							.add(LootItem.lootTableItem(AtmosphericItems.APOSTLE_ARMOR_TRIM_SMITHING_TEMPLATE.get()).setWeight(1).apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F)))))
			);

			consumer.accept(AtmosphericLootTables.VILLAGE_SCRUBLAND_HOUSE, LootTable.lootTable()
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

			consumer.accept(AtmosphericLootTables.VILLAGE_COCHINEAL_FARM_DROPPER, LootTable.lootTable()
					.withPool(LootPool.lootPool().setRolls(UniformGenerator.between(3.0F, 4.0F))
							.add(LootItem.lootTableItem(AtmosphericItems.DRAGON_FRUIT.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))));
		}
	}


	public record AtmosphericArchaeologyLoot(Provider registries) implements LootTableSubProvider {

		@Override
		public void generate(BiConsumer<ResourceKey<LootTable>, Builder> consumer) {
			consumer.accept(AtmosphericLootTables.ARID_GARDEN_ARCHAEOLOGY_COMMON, LootTable.lootTable()
					.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
							.add(LootItem.lootTableItem(AtmosphericItems.CARMINE_HUSK.get()))
							.add(LootItem.lootTableItem(AtmosphericItems.ROASTED_YUCCA_FRUIT.get()))
							.add(LootItem.lootTableItem(AtmosphericItems.ALOE_KERNELS.get()))
							.add(LootItem.lootTableItem(AtmosphericItems.ALOE_GEL_BOTTLE.get()))
							.add(LootItem.lootTableItem(Items.FLOWER_POT))
							.add(LootItem.lootTableItem(Items.BRICK))
							.add(LootItem.lootTableItem(Items.DEAD_BUSH))
							.add(LootItem.lootTableItem(Items.BONE_MEAL))
							.add(LootItem.lootTableItem(Items.COMPOSTER))
					));

			consumer.accept(AtmosphericLootTables.ARID_GARDEN_ARCHAEOLOGY_RARE, LootTable.lootTable()
					.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
							.add(LootItem.lootTableItem(AtmosphericItems.SCYTHE_POTTERY_SHERD.get()))
							.add(LootItem.lootTableItem(AtmosphericItems.SUCCULENT_POTTERY_SHERD.get()))
							.add(LootItem.lootTableItem(AtmosphericItems.SUN_POTTERY_SHERD.get()))
							.add(LootItem.lootTableItem(AtmosphericItems.PETRIFIED_ARMOR_TRIM_SMITHING_TEMPLATE.get()))
					));
		}
	}
}