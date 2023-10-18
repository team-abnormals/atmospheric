package com.teamabnormals.atmospheric.core.other;

import com.teamabnormals.atmospheric.common.dispenser.PassionVineBundleDispenseBehavior;
import com.teamabnormals.atmospheric.common.dispenser.PassionVineDispenseBehavior;
import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import com.teamabnormals.atmospheric.core.registry.AtmosphericItems;
import com.teamabnormals.blueprint.core.util.DataUtil;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.animal.Parrot;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraftforge.common.crafting.CompoundIngredient;

import java.util.Collections;

public class AtmosphericCompat {

	public static void registerCompat() {
		registerDispenserBehaviors();
		registerCompostables();
		registerFlammables();
		registerAnimalFoods();
	}

	public static void registerAnimalFoods() {
		Chicken.FOOD_ITEMS = CompoundIngredient.of(Chicken.FOOD_ITEMS, Ingredient.of(AtmosphericItems.ALOE_KERNELS.get()));
		Collections.addAll(Parrot.TAME_FOOD, AtmosphericItems.ALOE_KERNELS.get());
	}

	public static void registerDispenserBehaviors() {
		DispenserBlock.registerBehavior(AtmosphericBlocks.PASSION_VINE_BUNDLE.get().asItem(), new PassionVineBundleDispenseBehavior());
		DispenserBlock.registerBehavior(AtmosphericBlocks.PASSION_VINE.get().asItem(), new PassionVineDispenseBehavior());
	}

	public static void registerCompostables() {
		DataUtil.registerCompostable(AtmosphericBlocks.ROSEWOOD_LEAVES.get(), 0.3F);
		DataUtil.registerCompostable(AtmosphericBlocks.ROSEWOOD_SAPLING.get(), 0.3F);
		DataUtil.registerCompostable(AtmosphericBlocks.ROSEWOOD_LEAF_CARPET.get(), 0.3F);

		DataUtil.registerCompostable(AtmosphericBlocks.MORADO_LEAVES.get(), 0.3F);
		DataUtil.registerCompostable(AtmosphericBlocks.MORADO_SAPLING.get(), 0.3F);
		DataUtil.registerCompostable(AtmosphericBlocks.MORADO_LEAF_CARPET.get(), 0.3F);

		DataUtil.registerCompostable(AtmosphericBlocks.FLOWERING_MORADO_LEAVES.get(), 0.3F);
		DataUtil.registerCompostable(AtmosphericItems.YELLOW_BLOSSOMS.get(), 0.3F);
		DataUtil.registerCompostable(AtmosphericBlocks.FLOWERING_MORADO_LEAF_CARPET.get(), 0.3F);

		DataUtil.registerCompostable(AtmosphericBlocks.WARM_MONKEY_BRUSH.get(), 0.65F);
		DataUtil.registerCompostable(AtmosphericBlocks.HOT_MONKEY_BRUSH.get(), 0.65F);
		DataUtil.registerCompostable(AtmosphericBlocks.SCALDING_MONKEY_BRUSH.get(), 0.65F);
		DataUtil.registerCompostable(AtmosphericBlocks.PASSION_VINE.get(), 0.3F);
		DataUtil.registerCompostable(AtmosphericItems.PASSION_VINE_COIL.get(), 0.85F);
		DataUtil.registerCompostable(AtmosphericBlocks.PASSION_VINE_BUNDLE.get(), 1.0F);
		DataUtil.registerCompostable(AtmosphericBlocks.WATER_HYACINTH.get(), 0.65F);

		DataUtil.registerCompostable(AtmosphericItems.PASSION_FRUIT.get(), 0.65F);
		DataUtil.registerCompostable(AtmosphericItems.PASSION_FRUIT_TART.get(), 0.85F);
		DataUtil.registerCompostable(AtmosphericItems.YUCCA_FRUIT.get(), 0.65F);
		DataUtil.registerCompostable(AtmosphericItems.ROASTED_YUCCA_FRUIT.get(), 0.65F);
		DataUtil.registerCompostable(AtmosphericBlocks.YUCCA_BRANCH.get(), 0.65F);

		DataUtil.registerCompostable(AtmosphericBlocks.YUCCA_LEAVES.get(), 0.3F);
		DataUtil.registerCompostable(AtmosphericBlocks.YUCCA_SAPLING.get(), 0.3F);
		DataUtil.registerCompostable(AtmosphericBlocks.YUCCA_LEAF_CARPET.get(), 0.3F);

		DataUtil.registerCompostable(AtmosphericBlocks.YUCCA_FLOWER.get(), 0.65F);
		DataUtil.registerCompostable(AtmosphericBlocks.TALL_YUCCA_FLOWER.get(), 0.65F);
		DataUtil.registerCompostable(AtmosphericBlocks.BARREL_CACTUS.get(), 0.5F);

		DataUtil.registerCompostable(AtmosphericBlocks.GILIA.get(), 0.65F);
		DataUtil.registerCompostable(AtmosphericBlocks.FIRETHORN.get(), 0.65F);
		DataUtil.registerCompostable(AtmosphericBlocks.FORSYTHIA.get(), 0.65F);

		DataUtil.registerCompostable(AtmosphericBlocks.ARID_SPROUTS.get(), 0.3F);
		DataUtil.registerCompostable(AtmosphericItems.ALOE_KERNELS.get(), 0.3F);
		DataUtil.registerCompostable(AtmosphericItems.ALOE_LEAVES.get(), 0.65F);
		DataUtil.registerCompostable(AtmosphericBlocks.ALOE_BUNDLE.get(), 1.0F);

		DataUtil.registerCompostable(AtmosphericBlocks.KOUSA_LEAVES.get(), 0.3F);
		DataUtil.registerCompostable(AtmosphericBlocks.KOUSA_SAPLING.get(), 0.3F);
		DataUtil.registerCompostable(AtmosphericBlocks.KOUSA_LEAF_CARPET.get(), 0.3F);

		DataUtil.registerCompostable(AtmosphericBlocks.ASPEN_LEAVES.get(), 0.3F);
		DataUtil.registerCompostable(AtmosphericBlocks.ASPEN_SAPLING.get(), 0.3F);
		DataUtil.registerCompostable(AtmosphericBlocks.ASPEN_LEAF_CARPET.get(), 0.3F);
		
		DataUtil.registerCompostable(AtmosphericBlocks.GREEN_ASPEN_LEAVES.get(), 0.3F);
		DataUtil.registerCompostable(AtmosphericBlocks.GREEN_ASPEN_SAPLING.get(), 0.3F);
		DataUtil.registerCompostable(AtmosphericBlocks.GREEN_ASPEN_LEAF_CARPET.get(), 0.3F);

		DataUtil.registerCompostable(AtmosphericBlocks.GRIMWOOD_LEAVES.get(), 0.3F);
		DataUtil.registerCompostable(AtmosphericBlocks.GRIMWOOD_SAPLING.get(), 0.3F);
		DataUtil.registerCompostable(AtmosphericBlocks.GRIMWOOD_LEAF_CARPET.get(), 0.3F);

		DataUtil.registerCompostable(AtmosphericBlocks.LAUREL_LEAVES.get(), 0.3F);
		DataUtil.registerCompostable(AtmosphericBlocks.LAUREL_SAPLING.get(), 0.3F);
		DataUtil.registerCompostable(AtmosphericBlocks.LAUREL_LEAF_CARPET.get(), 0.3F);

		DataUtil.registerCompostable(AtmosphericBlocks.DRY_LAUREL_LEAVES.get(), 0.3F);
		DataUtil.registerCompostable(AtmosphericBlocks.DRY_LAUREL_SAPLING.get(), 0.3F);
		DataUtil.registerCompostable(AtmosphericBlocks.DRY_LAUREL_LEAF_CARPET.get(), 0.3F);

		DataUtil.registerCompostable(AtmosphericBlocks.AGAVE.get(), 0.65F);
		DataUtil.registerCompostable(AtmosphericBlocks.GOLDEN_GROWTHS.get(), 0.3F);
		DataUtil.registerCompostable(AtmosphericItems.CURRANT.get(), 0.3F);
		DataUtil.registerCompostable(AtmosphericBlocks.HANGING_CURRANT.get(), 0.3F);
		DataUtil.registerCompostable(AtmosphericBlocks.CURRANT_LEAVES.get(), 0.3F);
		DataUtil.registerCompostable(AtmosphericBlocks.CURRANT_SEEDLING.get(), 0.3F);
		DataUtil.registerCompostable(AtmosphericBlocks.CURRANT_LEAF_CARPET.get(), 0.3F);
		DataUtil.registerCompostable(AtmosphericBlocks.CURRANT_BASKET.get(), 1.0F);
		DataUtil.registerCompostable(AtmosphericBlocks.CURRANT_STALK.get(), 0.65F);
		DataUtil.registerCompostable(AtmosphericBlocks.CURRANT_STALK_BUNDLE.get(), 1.0F);

		DataUtil.registerCompostable(AtmosphericBlocks.YUCCA_BUNDLE.get(), 0.85F);
		DataUtil.registerCompostable(AtmosphericBlocks.ROASTED_YUCCA_BUNDLE.get(), 0.85F);

		DataUtil.registerCompostable(AtmosphericBlocks.PASSION_FRUIT_CRATE.get(), 1.0F);
		DataUtil.registerCompostable(AtmosphericBlocks.YUCCA_CASK.get(), 1.0F);
		DataUtil.registerCompostable(AtmosphericBlocks.ROASTED_YUCCA_CASK.get(), 1.0F);
		DataUtil.registerCompostable(AtmosphericBlocks.BARREL_CACTUS_BATCH.get(), 1.0F);
		DataUtil.registerCompostable(AtmosphericBlocks.DRAGON_FRUIT_CRATE.get(), 1.0F);
		DataUtil.registerCompostable(AtmosphericBlocks.GOLDEN_DRAGON_FRUIT_CRATE.get(), 1.0F);
	}

	public static void registerFlammables() {
		DataUtil.registerFlammable(AtmosphericBlocks.PASSION_VINE.get(), 15, 100);
		DataUtil.registerFlammable(AtmosphericBlocks.PASSION_VINE_BUNDLE.get(), 60, 20);

		DataUtil.registerFlammable(AtmosphericBlocks.PASSION_FRUIT_CRATE.get(), 5, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.SHIMMERING_PASSION_FRUIT_CRATE.get(), 5, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.YUCCA_CASK.get(), 5, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.ROASTED_YUCCA_CASK.get(), 5, 20);

		DataUtil.registerFlammable(AtmosphericBlocks.WARM_MONKEY_BRUSH.get(), 60, 100);
		DataUtil.registerFlammable(AtmosphericBlocks.HOT_MONKEY_BRUSH.get(), 60, 100);
		DataUtil.registerFlammable(AtmosphericBlocks.SCALDING_MONKEY_BRUSH.get(), 60, 100);

		DataUtil.registerFlammable(AtmosphericBlocks.ARID_SPROUTS.get(), 60, 100);
		DataUtil.registerFlammable(AtmosphericBlocks.YUCCA_FLOWER.get(), 5, 60);
		DataUtil.registerFlammable(AtmosphericBlocks.YUCCA_BRANCH.get(), 5, 60);
		DataUtil.registerFlammable(AtmosphericBlocks.TALL_YUCCA_FLOWER.get(), 5, 60);
		DataUtil.registerFlammable(AtmosphericBlocks.BARREL_CACTUS.get(), 5, 60);

		DataUtil.registerFlammable(AtmosphericBlocks.SNOWY_BAMBOO.get(), 60, 60);
		DataUtil.registerFlammable(AtmosphericBlocks.CURRANT_SEEDLING.get(), 60, 100);
		DataUtil.registerFlammable(AtmosphericBlocks.CURRANT_STALK.get(), 5, 60);
		DataUtil.registerFlammable(AtmosphericBlocks.CURRANT_STALK_BUNDLE.get(), 5, 60);
		DataUtil.registerFlammable(AtmosphericBlocks.CURRANT_BASKET.get(), 5, 20);

		DataUtil.registerFlammable(AtmosphericBlocks.GOLDEN_GROWTHS.get(), 60, 100);
		DataUtil.registerFlammable(AtmosphericBlocks.AGAVE.get(), 60, 100);

		DataUtil.registerFlammable(AtmosphericBlocks.ROSEWOOD_LEAVES.get(), 30, 60);
		DataUtil.registerFlammable(AtmosphericBlocks.ROSEWOOD_LOG.get(), 5, 5);
		DataUtil.registerFlammable(AtmosphericBlocks.ROSEWOOD.get(), 5, 5);
		DataUtil.registerFlammable(AtmosphericBlocks.STRIPPED_ROSEWOOD_LOG.get(), 5, 5);
		DataUtil.registerFlammable(AtmosphericBlocks.STRIPPED_ROSEWOOD.get(), 5, 5);
		DataUtil.registerFlammable(AtmosphericBlocks.ROSEWOOD_PLANKS.get(), 5, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.ROSEWOOD_SLAB.get(), 5, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.ROSEWOOD_STAIRS.get(), 5, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.ROSEWOOD_FENCE.get(), 5, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.ROSEWOOD_FENCE_GATE.get(), 5, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.VERTICAL_ROSEWOOD_PLANKS.get(), 5, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.ROSEWOOD_LEAF_CARPET.get(), 30, 60);
		DataUtil.registerFlammable(AtmosphericBlocks.ROSEWOOD_VERTICAL_SLAB.get(), 5, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.ROSEWOOD_BOOKSHELF.get(), 30, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.ROSEWOOD_BEEHIVE.get(), 5, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.ROSEWOOD_POST.get(), 5, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.STRIPPED_ROSEWOOD_POST.get(), 5, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.ROSEWOOD_HEDGE.get(), 5, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.ROSEWOOD_LEAF_PILE.get(), 30, 60);
		DataUtil.registerFlammable(AtmosphericBlocks.ROSEWOOD_BOARDS.get(), 5, 20);

		DataUtil.registerFlammable(AtmosphericBlocks.MORADO_LEAVES.get(), 30, 60);
		DataUtil.registerFlammable(AtmosphericBlocks.FLOWERING_MORADO_LEAVES.get(), 30, 60);
		DataUtil.registerFlammable(AtmosphericBlocks.MORADO_LOG.get(), 5, 5);
		DataUtil.registerFlammable(AtmosphericBlocks.MORADO_WOOD.get(), 5, 5);
		DataUtil.registerFlammable(AtmosphericBlocks.STRIPPED_MORADO_LOG.get(), 5, 5);
		DataUtil.registerFlammable(AtmosphericBlocks.STRIPPED_MORADO_WOOD.get(), 5, 5);
		DataUtil.registerFlammable(AtmosphericBlocks.MORADO_PLANKS.get(), 5, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.MORADO_SLAB.get(), 5, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.MORADO_STAIRS.get(), 5, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.MORADO_FENCE.get(), 5, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.MORADO_FENCE_GATE.get(), 5, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.VERTICAL_MORADO_PLANKS.get(), 5, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.MORADO_LEAF_CARPET.get(), 30, 60);
		DataUtil.registerFlammable(AtmosphericBlocks.FLOWERING_MORADO_LEAF_CARPET.get(), 30, 60);
		DataUtil.registerFlammable(AtmosphericBlocks.MORADO_VERTICAL_SLAB.get(), 5, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.MORADO_BOOKSHELF.get(), 30, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.MORADO_BEEHIVE.get(), 5, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.MORADO_POST.get(), 5, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.STRIPPED_MORADO_POST.get(), 5, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.MORADO_HEDGE.get(), 5, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.FLOWERING_MORADO_HEDGE.get(), 5, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.MORADO_LEAF_PILE.get(), 30, 60);
		DataUtil.registerFlammable(AtmosphericBlocks.FLOWERING_MORADO_LEAF_PILE.get(), 30, 60);
		DataUtil.registerFlammable(AtmosphericBlocks.MORADO_BOARDS.get(), 5, 20);

		DataUtil.registerFlammable(AtmosphericBlocks.YUCCA_LEAVES.get(), 30, 60);
		DataUtil.registerFlammable(AtmosphericBlocks.YUCCA_LOG.get(), 5, 5);
		DataUtil.registerFlammable(AtmosphericBlocks.YUCCA_WOOD.get(), 5, 5);
		DataUtil.registerFlammable(AtmosphericBlocks.STRIPPED_YUCCA_LOG.get(), 5, 5);
		DataUtil.registerFlammable(AtmosphericBlocks.STRIPPED_YUCCA_WOOD.get(), 5, 5);
		DataUtil.registerFlammable(AtmosphericBlocks.YUCCA_PLANKS.get(), 5, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.YUCCA_SLAB.get(), 5, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.YUCCA_STAIRS.get(), 5, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.YUCCA_FENCE.get(), 5, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.YUCCA_FENCE_GATE.get(), 5, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.VERTICAL_YUCCA_PLANKS.get(), 5, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.YUCCA_LEAF_CARPET.get(), 30, 60);
		DataUtil.registerFlammable(AtmosphericBlocks.YUCCA_VERTICAL_SLAB.get(), 5, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.YUCCA_BOOKSHELF.get(), 30, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.YUCCA_BEEHIVE.get(), 5, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.YUCCA_POST.get(), 5, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.STRIPPED_YUCCA_POST.get(), 5, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.YUCCA_HEDGE.get(), 5, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.YUCCA_LEAF_PILE.get(), 30, 60);
		DataUtil.registerFlammable(AtmosphericBlocks.YUCCA_BOARDS.get(), 5, 20);

		DataUtil.registerFlammable(AtmosphericBlocks.KOUSA_LEAVES.get(), 30, 60);
		DataUtil.registerFlammable(AtmosphericBlocks.KOUSA_LOG.get(), 5, 5);
		DataUtil.registerFlammable(AtmosphericBlocks.KOUSA_WOOD.get(), 5, 5);
		DataUtil.registerFlammable(AtmosphericBlocks.STRIPPED_KOUSA_LOG.get(), 5, 5);
		DataUtil.registerFlammable(AtmosphericBlocks.STRIPPED_KOUSA_WOOD.get(), 5, 5);
		DataUtil.registerFlammable(AtmosphericBlocks.KOUSA_PLANKS.get(), 5, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.KOUSA_SLAB.get(), 5, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.KOUSA_STAIRS.get(), 5, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.KOUSA_FENCE.get(), 5, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.KOUSA_FENCE_GATE.get(), 5, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.VERTICAL_KOUSA_PLANKS.get(), 5, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.KOUSA_LEAF_CARPET.get(), 30, 60);
		DataUtil.registerFlammable(AtmosphericBlocks.KOUSA_VERTICAL_SLAB.get(), 5, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.KOUSA_BOOKSHELF.get(), 30, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.KOUSA_BEEHIVE.get(), 5, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.KOUSA_POST.get(), 5, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.STRIPPED_KOUSA_POST.get(), 5, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.KOUSA_HEDGE.get(), 5, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.KOUSA_LEAF_PILE.get(), 30, 60);
		DataUtil.registerFlammable(AtmosphericBlocks.KOUSA_BOARDS.get(), 5, 20);

		DataUtil.registerFlammable(AtmosphericBlocks.ASPEN_LEAVES.get(), 30, 60);
		DataUtil.registerFlammable(AtmosphericBlocks.ASPEN_LOG.get(), 5, 5);
		DataUtil.registerFlammable(AtmosphericBlocks.ASPEN_WOOD.get(), 5, 5);
		DataUtil.registerFlammable(AtmosphericBlocks.STRIPPED_ASPEN_LOG.get(), 5, 5);
		DataUtil.registerFlammable(AtmosphericBlocks.STRIPPED_ASPEN_WOOD.get(), 5, 5);
		DataUtil.registerFlammable(AtmosphericBlocks.WATCHFUL_ASPEN_LOG.get(), 5, 5);
		DataUtil.registerFlammable(AtmosphericBlocks.WATCHFUL_ASPEN_WOOD.get(), 5, 5);
		DataUtil.registerFlammable(AtmosphericBlocks.ASPEN_PLANKS.get(), 5, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.ASPEN_SLAB.get(), 5, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.ASPEN_STAIRS.get(), 5, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.ASPEN_FENCE.get(), 5, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.ASPEN_FENCE_GATE.get(), 5, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.VERTICAL_ASPEN_PLANKS.get(), 5, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.ASPEN_LEAF_CARPET.get(), 30, 60);
		DataUtil.registerFlammable(AtmosphericBlocks.ASPEN_VERTICAL_SLAB.get(), 5, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.ASPEN_BOOKSHELF.get(), 30, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.ASPEN_BEEHIVE.get(), 5, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.ASPEN_POST.get(), 5, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.STRIPPED_ASPEN_POST.get(), 5, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.ASPEN_HEDGE.get(), 5, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.ASPEN_LEAF_PILE.get(), 30, 60);
		DataUtil.registerFlammable(AtmosphericBlocks.ASPEN_BOARDS.get(), 5, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.GREEN_ASPEN_LEAVES.get(), 30, 60);
		DataUtil.registerFlammable(AtmosphericBlocks.GREEN_ASPEN_LEAF_CARPET.get(), 30, 60);
		DataUtil.registerFlammable(AtmosphericBlocks.GREEN_ASPEN_HEDGE.get(), 5, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.GREEN_ASPEN_LEAF_PILE.get(), 30, 60);

		DataUtil.registerFlammable(AtmosphericBlocks.LAUREL_LEAVES.get(), 30, 60);
		DataUtil.registerFlammable(AtmosphericBlocks.DRY_LAUREL_LEAVES.get(), 30, 60);
		DataUtil.registerFlammable(AtmosphericBlocks.LAUREL_LOG.get(), 5, 5);
		DataUtil.registerFlammable(AtmosphericBlocks.LAUREL_WOOD.get(), 5, 5);
		DataUtil.registerFlammable(AtmosphericBlocks.STRIPPED_LAUREL_LOG.get(), 5, 5);
		DataUtil.registerFlammable(AtmosphericBlocks.STRIPPED_LAUREL_WOOD.get(), 5, 5);
		DataUtil.registerFlammable(AtmosphericBlocks.LAUREL_PLANKS.get(), 5, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.LAUREL_SLAB.get(), 5, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.LAUREL_STAIRS.get(), 5, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.LAUREL_FENCE.get(), 5, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.LAUREL_FENCE_GATE.get(), 5, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.VERTICAL_LAUREL_PLANKS.get(), 5, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.LAUREL_LEAF_CARPET.get(), 30, 60);
		DataUtil.registerFlammable(AtmosphericBlocks.DRY_LAUREL_LEAF_CARPET.get(), 30, 60);
		DataUtil.registerFlammable(AtmosphericBlocks.LAUREL_VERTICAL_SLAB.get(), 5, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.LAUREL_BOOKSHELF.get(), 30, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.LAUREL_BEEHIVE.get(), 5, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.LAUREL_POST.get(), 5, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.STRIPPED_LAUREL_POST.get(), 5, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.LAUREL_HEDGE.get(), 5, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.DRY_LAUREL_HEDGE.get(), 5, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.LAUREL_LEAF_PILE.get(), 30, 60);
		DataUtil.registerFlammable(AtmosphericBlocks.DRY_LAUREL_LEAF_PILE.get(), 30, 60);
		DataUtil.registerFlammable(AtmosphericBlocks.LAUREL_BOARDS.get(), 5, 20);

		DataUtil.registerFlammable(AtmosphericBlocks.GRIMWOOD_LEAVES.get(), 30, 60);
		DataUtil.registerFlammable(AtmosphericBlocks.GRIMWOOD_LOG.get(), 5, 5);
		DataUtil.registerFlammable(AtmosphericBlocks.GRIMWOOD.get(), 5, 5);
		DataUtil.registerFlammable(AtmosphericBlocks.STRIPPED_GRIMWOOD_LOG.get(), 5, 5);
		DataUtil.registerFlammable(AtmosphericBlocks.STRIPPED_GRIMWOOD.get(), 5, 5);
		DataUtil.registerFlammable(AtmosphericBlocks.GRIMWOOD_PLANKS.get(), 5, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.GRIMWOOD_SLAB.get(), 5, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.GRIMWOOD_STAIRS.get(), 5, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.GRIMWOOD_FENCE.get(), 5, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.GRIMWOOD_FENCE_GATE.get(), 5, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.VERTICAL_GRIMWOOD_PLANKS.get(), 5, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.GRIMWOOD_LEAF_CARPET.get(), 30, 60);
		DataUtil.registerFlammable(AtmosphericBlocks.GRIMWOOD_VERTICAL_SLAB.get(), 5, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.GRIMWOOD_BOOKSHELF.get(), 30, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.GRIMWOOD_BEEHIVE.get(), 5, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.GRIMWOOD_POST.get(), 5, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.STRIPPED_GRIMWOOD_POST.get(), 5, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.GRIMWOOD_HEDGE.get(), 5, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.GRIMWOOD_LEAF_PILE.get(), 30, 60);
		DataUtil.registerFlammable(AtmosphericBlocks.GRIMWOOD_BOARDS.get(), 5, 20);
	}
}
