package com.teamabnormals.atmospheric.core.other;

import com.teamabnormals.atmospheric.common.dispenser.PassionVineBundleDispenseBehavior;
import com.teamabnormals.atmospheric.common.dispenser.PassionVineDispenseBehavior;
import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import com.teamabnormals.blueprint.core.util.DataUtil;
import net.minecraft.world.level.block.DispenserBlock;

import java.util.Calendar;

public class AtmosphericCompat {

	public static void registerCompat() {
		registerDispenserBehaviors();
		registerFlammables();
		setupDates();
	}

	public static void registerDispenserBehaviors() {
		DispenserBlock.registerBehavior(AtmosphericBlocks.PASSION_VINE_BUNDLE.get().asItem(), new PassionVineBundleDispenseBehavior());
		DispenserBlock.registerBehavior(AtmosphericBlocks.PASSION_VINE.get().asItem(), new PassionVineDispenseBehavior());
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
		DataUtil.registerFlammable(AtmosphericBlocks.CURRANT_CRATE.get(), 5, 20);

		DataUtil.registerFlammable(AtmosphericBlocks.GOLDEN_GROWTHS.get(), 60, 100);
		DataUtil.registerFlammable(AtmosphericBlocks.AGAVE.get(), 60, 100);
		DataUtil.registerFlammable(AtmosphericBlocks.DRAGON_ROOTS.get(), 60, 100);
		DataUtil.registerFlammable(AtmosphericBlocks.FIRETHORN.get(), 60, 100);
		DataUtil.registerFlammable(AtmosphericBlocks.FORSYTHIA.get(), 60, 100);
		DataUtil.registerFlammable(AtmosphericBlocks.GILIA.get(), 60, 100);
		DataUtil.registerFlammable(AtmosphericBlocks.WATER_HYACINTH.get(), 60, 100);

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
		DataUtil.registerFlammable(AtmosphericBlocks.ROSEWOOD_BOOKSHELF.get(), 30, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.ROSEWOOD_BEEHIVE.get(), 5, 20);
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
		DataUtil.registerFlammable(AtmosphericBlocks.MORADO_BOOKSHELF.get(), 30, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.MORADO_BEEHIVE.get(), 5, 20);
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
		DataUtil.registerFlammable(AtmosphericBlocks.YUCCA_BOOKSHELF.get(), 30, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.YUCCA_BEEHIVE.get(), 5, 20);
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
		DataUtil.registerFlammable(AtmosphericBlocks.KOUSA_BOOKSHELF.get(), 30, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.KOUSA_BEEHIVE.get(), 5, 20);
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
		DataUtil.registerFlammable(AtmosphericBlocks.ASPEN_BOOKSHELF.get(), 30, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.ASPEN_BEEHIVE.get(), 5, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.ASPEN_LEAF_PILE.get(), 30, 60);
		DataUtil.registerFlammable(AtmosphericBlocks.ASPEN_BOARDS.get(), 5, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.GREEN_ASPEN_LEAVES.get(), 30, 60);
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
		DataUtil.registerFlammable(AtmosphericBlocks.LAUREL_BOOKSHELF.get(), 30, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.LAUREL_BEEHIVE.get(), 5, 20);
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
		DataUtil.registerFlammable(AtmosphericBlocks.GRIMWOOD_BOOKSHELF.get(), 30, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.GRIMWOOD_BEEHIVE.get(), 5, 20);
		DataUtil.registerFlammable(AtmosphericBlocks.GRIMWOOD_LEAF_PILE.get(), 30, 60);
		DataUtil.registerFlammable(AtmosphericBlocks.GRIMWOOD_BOARDS.get(), 5, 20);
	}

	public static boolean IS_APRIL_FOOLS;
	public static boolean IS_STAR_WARS_DAY;

	public static void setupDates() {
		IS_APRIL_FOOLS = setDate(10, 15);
		IS_STAR_WARS_DAY = setDate(5, 4);
	}

	public static boolean setDate(int month, int day) {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.MONTH) + 1 == month && calendar.get(Calendar.DATE) == day;
	}

	public static boolean isAprilFools() {
		return IS_APRIL_FOOLS;
	}

	public static boolean isStarWarsDay() {
		return IS_STAR_WARS_DAY;
	}
}
