package com.minecraftabnormals.atmospheric.core.other;

import com.minecraftabnormals.atmospheric.common.data.PassionVineBundleDispenseBehavior;
import com.minecraftabnormals.atmospheric.common.data.PassionVineDispenseBehavior;
import com.minecraftabnormals.atmospheric.core.registry.AtmosphericBlocks;
import com.minecraftabnormals.atmospheric.core.registry.AtmosphericItems;
import com.teamabnormals.abnormals_core.core.utils.DataUtils;

import net.minecraft.block.DispenserBlock;

public class AtmosphericCompat {

    public static void registerDispenserBehaviors() {
        DispenserBlock.registerDispenseBehavior(AtmosphericBlocks.PASSION_VINE_BUNDLE.get().asItem(), new PassionVineBundleDispenseBehavior());
        DispenserBlock.registerDispenseBehavior(AtmosphericBlocks.PASSION_VINE.get().asItem(), new PassionVineDispenseBehavior());
    }

    public static void registerCompostables() {
        DataUtils.registerCompostable(AtmosphericBlocks.ROSEWOOD_LEAVES.get(), 0.3F);
        DataUtils.registerCompostable(AtmosphericBlocks.ROSEWOOD_SAPLING.get(), 0.3F);
        DataUtils.registerCompostable(AtmosphericBlocks.ROSEWOOD_LEAF_CARPET.get(), 0.3F);
        
        DataUtils.registerCompostable(AtmosphericBlocks.MORADO_LEAVES.get(), 0.3F);
        DataUtils.registerCompostable(AtmosphericBlocks.MORADO_SAPLING.get(), 0.3F);
        DataUtils.registerCompostable(AtmosphericBlocks.MORADO_LEAF_CARPET.get(), 0.3F);
        
        DataUtils.registerCompostable(AtmosphericBlocks.FLOWERING_MORADO_LEAVES.get(), 0.3F);
        DataUtils.registerCompostable(AtmosphericItems.YELLOW_BLOSSOMS.get(), 0.3F);
        DataUtils.registerCompostable(AtmosphericBlocks.FLOWERING_MORADO_LEAF_CARPET.get(), 0.3F);

        DataUtils.registerCompostable(AtmosphericBlocks.WARM_MONKEY_BRUSH.get(), 0.65F);
        DataUtils.registerCompostable(AtmosphericBlocks.HOT_MONKEY_BRUSH.get(), 0.65F);
        DataUtils.registerCompostable(AtmosphericBlocks.SCALDING_MONKEY_BRUSH.get(), 0.65F);
        DataUtils.registerCompostable(AtmosphericBlocks.PASSION_VINE_BUNDLE.get(), 0.5F);
        DataUtils.registerCompostable(AtmosphericBlocks.PASSION_VINE.get(), 0.5F);

        DataUtils.registerCompostable(AtmosphericItems.PASSIONFRUIT.get(), 0.65F);
        DataUtils.registerCompostable(AtmosphericItems.YUCCA_FRUIT.get(), 0.65F);
        DataUtils.registerCompostable(AtmosphericItems.ROASTED_YUCCA_FRUIT.get(), 0.65F);

        DataUtils.registerCompostable(AtmosphericBlocks.YUCCA_LEAVES.get(), 0.3F);
        DataUtils.registerCompostable(AtmosphericBlocks.YUCCA_SAPLING.get(), 0.3F);
        DataUtils.registerCompostable(AtmosphericBlocks.YUCCA_LEAF_CARPET.get(), 0.3F);

        DataUtils.registerCompostable(AtmosphericBlocks.YUCCA_FLOWER.get(), 0.65F);
        DataUtils.registerCompostable(AtmosphericBlocks.TALL_YUCCA_FLOWER.get(), 0.65F);
        DataUtils.registerCompostable(AtmosphericBlocks.BARREL_CACTUS.get(), 0.5F);

        DataUtils.registerCompostable(AtmosphericBlocks.GILIA.get(), 0.65F);

        DataUtils.registerCompostable(AtmosphericItems.ALOE_KERNELS.get(), 0.3F);
        DataUtils.registerCompostable(AtmosphericItems.ALOE_LEAVES.get(), 0.65F);
        DataUtils.registerCompostable(AtmosphericBlocks.ALOE_BUNDLE.get(), 1.0F);

        DataUtils.registerCompostable(AtmosphericBlocks.KOUSA_LEAVES.get(), 0.3F);
        DataUtils.registerCompostable(AtmosphericBlocks.KOUSA_SAPLING.get(), 0.3F);
        DataUtils.registerCompostable(AtmosphericBlocks.KOUSA_LEAF_CARPET.get(), 0.3F);

        DataUtils.registerCompostable(AtmosphericBlocks.ASPEN_LEAVES.get(), 0.3F);
        DataUtils.registerCompostable(AtmosphericBlocks.ASPEN_SAPLING.get(), 0.3F);
        DataUtils.registerCompostable(AtmosphericBlocks.ASPEN_LEAF_CARPET.get(), 0.3F);

        DataUtils.registerCompostable(AtmosphericBlocks.GRIMWOOD_LEAVES.get(), 0.3F);
        DataUtils.registerCompostable(AtmosphericBlocks.GRIMWOOD_SAPLING.get(), 0.3F);
        DataUtils.registerCompostable(AtmosphericBlocks.GRIMWOOD_LEAF_CARPET.get(), 0.3F);

        DataUtils.registerCompostable(AtmosphericBlocks.YUCCA_BUNDLE.get(), 0.85F);
        DataUtils.registerCompostable(AtmosphericBlocks.ROASTED_YUCCA_BUNDLE.get(), 0.85F);

        DataUtils.registerCompostable(AtmosphericBlocks.PASSIONFRUIT_CRATE.get(), 1.0F);
        DataUtils.registerCompostable(AtmosphericBlocks.YUCCA_CASK.get(), 1.0F);
        DataUtils.registerCompostable(AtmosphericBlocks.ROASTED_YUCCA_CASK.get(), 1.0F);
        DataUtils.registerCompostable(AtmosphericBlocks.BARREL_CACTUS_BATCH.get(), 1.0F);
    }

    public static void registerFlammables() {
        DataUtils.registerFlammable(AtmosphericBlocks.PASSION_VINE.get(), 15, 100);
        DataUtils.registerFlammable(AtmosphericBlocks.PASSION_VINE_BUNDLE.get(), 60, 20);
 
        DataUtils.registerFlammable(AtmosphericBlocks.PASSIONFRUIT_CRATE.get(), 5, 20);
        DataUtils.registerFlammable(AtmosphericBlocks.SHIMMERING_PASSIONFRUIT_CRATE.get(), 5, 20);
        DataUtils.registerFlammable(AtmosphericBlocks.YUCCA_CASK.get(), 5, 20);
        DataUtils.registerFlammable(AtmosphericBlocks.ROASTED_YUCCA_CASK.get(), 5, 20);
        
        DataUtils.registerFlammable(AtmosphericBlocks.WARM_MONKEY_BRUSH.get(), 60, 100);
        DataUtils.registerFlammable(AtmosphericBlocks.HOT_MONKEY_BRUSH.get(), 60, 100);
        DataUtils.registerFlammable(AtmosphericBlocks.SCALDING_MONKEY_BRUSH.get(), 60, 100);
 
        DataUtils.registerFlammable(AtmosphericBlocks.YUCCA_FLOWER.get(), 5, 60);
        DataUtils.registerFlammable(AtmosphericBlocks.YUCCA_BRANCH.get(), 5, 60);
        DataUtils.registerFlammable(AtmosphericBlocks.TALL_YUCCA_FLOWER.get(), 5, 60);
        DataUtils.registerFlammable(AtmosphericBlocks.BARREL_CACTUS.get(), 5, 60);

        DataUtils.registerFlammable(AtmosphericBlocks.ROSEWOOD_LEAVES.get(), 30, 60);
        DataUtils.registerFlammable(AtmosphericBlocks.ROSEWOOD_LOG.get(), 5, 5);
        DataUtils.registerFlammable(AtmosphericBlocks.ROSEWOOD.get(), 5, 5);
        DataUtils.registerFlammable(AtmosphericBlocks.STRIPPED_ROSEWOOD_LOG.get(), 5, 5);
        DataUtils.registerFlammable(AtmosphericBlocks.STRIPPED_ROSEWOOD.get(), 5, 5);
        DataUtils.registerFlammable(AtmosphericBlocks.ROSEWOOD_PLANKS.get(), 5, 20);
        DataUtils.registerFlammable(AtmosphericBlocks.ROSEWOOD_SLAB.get(), 5, 20);
        DataUtils.registerFlammable(AtmosphericBlocks.ROSEWOOD_STAIRS.get(), 5, 20);
        DataUtils.registerFlammable(AtmosphericBlocks.ROSEWOOD_FENCE.get(), 5, 20);
        DataUtils.registerFlammable(AtmosphericBlocks.ROSEWOOD_FENCE_GATE.get(), 5, 20);
        DataUtils.registerFlammable(AtmosphericBlocks.VERTICAL_ROSEWOOD_PLANKS.get(), 5, 20);
        DataUtils.registerFlammable(AtmosphericBlocks.ROSEWOOD_LEAF_CARPET.get(), 30, 60);
        DataUtils.registerFlammable(AtmosphericBlocks.ROSEWOOD_VERTICAL_SLAB.get(), 5, 20);
        DataUtils.registerFlammable(AtmosphericBlocks.ROSEWOOD_BOOKSHELF.get(), 30, 20);
        DataUtils.registerFlammable(AtmosphericBlocks.ROSEWOOD_BEEHIVE.get(), 5, 20);
        
        DataUtils.registerFlammable(AtmosphericBlocks.MORADO_LEAVES.get(), 30, 60);
        DataUtils.registerFlammable(AtmosphericBlocks.FLOWERING_MORADO_LEAVES.get(), 30, 60);
        DataUtils.registerFlammable(AtmosphericBlocks.MORADO_LOG.get(), 5, 5);
        DataUtils.registerFlammable(AtmosphericBlocks.MORADO_WOOD.get(), 5, 5);
        DataUtils.registerFlammable(AtmosphericBlocks.STRIPPED_MORADO_LOG.get(), 5, 5);
        DataUtils.registerFlammable(AtmosphericBlocks.STRIPPED_MORADO_WOOD.get(), 5, 5);
        DataUtils.registerFlammable(AtmosphericBlocks.MORADO_PLANKS.get(), 5, 20);
        DataUtils.registerFlammable(AtmosphericBlocks.MORADO_SLAB.get(), 5, 20);
        DataUtils.registerFlammable(AtmosphericBlocks.MORADO_STAIRS.get(), 5, 20);
        DataUtils.registerFlammable(AtmosphericBlocks.MORADO_FENCE.get(), 5, 20);
        DataUtils.registerFlammable(AtmosphericBlocks.MORADO_FENCE_GATE.get(), 5, 20);
        DataUtils.registerFlammable(AtmosphericBlocks.VERTICAL_MORADO_PLANKS.get(), 5, 20);
        DataUtils.registerFlammable(AtmosphericBlocks.MORADO_LEAF_CARPET.get(), 30, 60);
        DataUtils.registerFlammable(AtmosphericBlocks.FLOWERING_MORADO_LEAF_CARPET.get(), 30, 60);
        DataUtils.registerFlammable(AtmosphericBlocks.MORADO_VERTICAL_SLAB.get(), 5, 20);
        DataUtils.registerFlammable(AtmosphericBlocks.MORADO_BOOKSHELF.get(), 30, 20);
        DataUtils.registerFlammable(AtmosphericBlocks.MORADO_BEEHIVE.get(), 5, 20);

        DataUtils.registerFlammable(AtmosphericBlocks.YUCCA_LEAVES.get(), 30, 60);
        DataUtils.registerFlammable(AtmosphericBlocks.YUCCA_LOG.get(), 5, 5);
        DataUtils.registerFlammable(AtmosphericBlocks.YUCCA_WOOD.get(), 5, 5);
        DataUtils.registerFlammable(AtmosphericBlocks.STRIPPED_YUCCA_LOG.get(), 5, 5);
        DataUtils.registerFlammable(AtmosphericBlocks.STRIPPED_YUCCA_WOOD.get(), 5, 5);
        DataUtils.registerFlammable(AtmosphericBlocks.YUCCA_PLANKS.get(), 5, 20);
        DataUtils.registerFlammable(AtmosphericBlocks.YUCCA_SLAB.get(), 5, 20);
        DataUtils.registerFlammable(AtmosphericBlocks.YUCCA_STAIRS.get(), 5, 20);
        DataUtils.registerFlammable(AtmosphericBlocks.YUCCA_FENCE.get(), 5, 20);
        DataUtils.registerFlammable(AtmosphericBlocks.YUCCA_FENCE_GATE.get(), 5, 20);
        DataUtils.registerFlammable(AtmosphericBlocks.VERTICAL_YUCCA_PLANKS.get(), 5, 20);
        DataUtils.registerFlammable(AtmosphericBlocks.YUCCA_LEAF_CARPET.get(), 30, 60);
        DataUtils.registerFlammable(AtmosphericBlocks.YUCCA_VERTICAL_SLAB.get(), 5, 20);
        DataUtils.registerFlammable(AtmosphericBlocks.YUCCA_BOOKSHELF.get(), 30, 20);
        DataUtils.registerFlammable(AtmosphericBlocks.YUCCA_BEEHIVE.get(), 5, 20);

        DataUtils.registerFlammable(AtmosphericBlocks.KOUSA_LEAVES.get(), 30, 60);
        DataUtils.registerFlammable(AtmosphericBlocks.KOUSA_LOG.get(), 5, 5);
        DataUtils.registerFlammable(AtmosphericBlocks.KOUSA_WOOD.get(), 5, 5);
        DataUtils.registerFlammable(AtmosphericBlocks.STRIPPED_KOUSA_LOG.get(), 5, 5);
        DataUtils.registerFlammable(AtmosphericBlocks.STRIPPED_KOUSA_WOOD.get(), 5, 5);
        DataUtils.registerFlammable(AtmosphericBlocks.KOUSA_PLANKS.get(), 5, 20);
        DataUtils.registerFlammable(AtmosphericBlocks.KOUSA_SLAB.get(), 5, 20);
        DataUtils.registerFlammable(AtmosphericBlocks.KOUSA_STAIRS.get(), 5, 20);
        DataUtils.registerFlammable(AtmosphericBlocks.KOUSA_FENCE.get(), 5, 20);
        DataUtils.registerFlammable(AtmosphericBlocks.KOUSA_FENCE_GATE.get(), 5, 20);
        DataUtils.registerFlammable(AtmosphericBlocks.VERTICAL_KOUSA_PLANKS.get(), 5, 20);
        DataUtils.registerFlammable(AtmosphericBlocks.KOUSA_LEAF_CARPET.get(), 30, 60);
        DataUtils.registerFlammable(AtmosphericBlocks.KOUSA_VERTICAL_SLAB.get(), 5, 20);
        DataUtils.registerFlammable(AtmosphericBlocks.KOUSA_BOOKSHELF.get(), 30, 20);
        DataUtils.registerFlammable(AtmosphericBlocks.KOUSA_BEEHIVE.get(), 5, 20);

        DataUtils.registerFlammable(AtmosphericBlocks.ASPEN_LEAVES.get(), 30, 60);
        DataUtils.registerFlammable(AtmosphericBlocks.ASPEN_LOG.get(), 5, 5);
        DataUtils.registerFlammable(AtmosphericBlocks.ASPEN_WOOD.get(), 5, 5);
        DataUtils.registerFlammable(AtmosphericBlocks.STRIPPED_ASPEN_LOG.get(), 5, 5);
        DataUtils.registerFlammable(AtmosphericBlocks.STRIPPED_ASPEN_WOOD.get(), 5, 5);
        DataUtils.registerFlammable(AtmosphericBlocks.WATCHFUL_ASPEN_LOG.get(), 5, 5);
        DataUtils.registerFlammable(AtmosphericBlocks.WATCHFUL_ASPEN_WOOD.get(), 5, 5);
        DataUtils.registerFlammable(AtmosphericBlocks.ASPEN_PLANKS.get(), 5, 20);
        DataUtils.registerFlammable(AtmosphericBlocks.ASPEN_SLAB.get(), 5, 20);
        DataUtils.registerFlammable(AtmosphericBlocks.ASPEN_STAIRS.get(), 5, 20);
        DataUtils.registerFlammable(AtmosphericBlocks.ASPEN_FENCE.get(), 5, 20);
        DataUtils.registerFlammable(AtmosphericBlocks.ASPEN_FENCE_GATE.get(), 5, 20);
        DataUtils.registerFlammable(AtmosphericBlocks.VERTICAL_ASPEN_PLANKS.get(), 5, 20);
        DataUtils.registerFlammable(AtmosphericBlocks.ASPEN_LEAF_CARPET.get(), 30, 60);
        DataUtils.registerFlammable(AtmosphericBlocks.ASPEN_VERTICAL_SLAB.get(), 5, 20);
        DataUtils.registerFlammable(AtmosphericBlocks.ASPEN_BOOKSHELF.get(), 30, 20);
        DataUtils.registerFlammable(AtmosphericBlocks.ASPEN_BEEHIVE.get(), 5, 20);

        DataUtils.registerFlammable(AtmosphericBlocks.GRIMWOOD_LEAVES.get(), 30, 60);
        DataUtils.registerFlammable(AtmosphericBlocks.GRIMWOOD_LOG.get(), 5, 5);
        DataUtils.registerFlammable(AtmosphericBlocks.GRIMWOOD.get(), 5, 5);
        DataUtils.registerFlammable(AtmosphericBlocks.STRIPPED_GRIMWOOD_LOG.get(), 5, 5);
        DataUtils.registerFlammable(AtmosphericBlocks.STRIPPED_GRIMWOOD.get(), 5, 5);
        DataUtils.registerFlammable(AtmosphericBlocks.GRIMWOOD_PLANKS.get(), 5, 20);
        DataUtils.registerFlammable(AtmosphericBlocks.GRIMWOOD_SLAB.get(), 5, 20);
        DataUtils.registerFlammable(AtmosphericBlocks.GRIMWOOD_STAIRS.get(), 5, 20);
        DataUtils.registerFlammable(AtmosphericBlocks.GRIMWOOD_FENCE.get(), 5, 20);
        DataUtils.registerFlammable(AtmosphericBlocks.GRIMWOOD_FENCE_GATE.get(), 5, 20);
        DataUtils.registerFlammable(AtmosphericBlocks.VERTICAL_GRIMWOOD_PLANKS.get(), 5, 20);
        DataUtils.registerFlammable(AtmosphericBlocks.GRIMWOOD_LEAF_CARPET.get(), 30, 60);
        DataUtils.registerFlammable(AtmosphericBlocks.GRIMWOOD_VERTICAL_SLAB.get(), 5, 20);
        DataUtils.registerFlammable(AtmosphericBlocks.GRIMWOOD_BOOKSHELF.get(), 30, 20);
        DataUtils.registerFlammable(AtmosphericBlocks.GRIMWOOD_BEEHIVE.get(), 5, 20);
    }
}
