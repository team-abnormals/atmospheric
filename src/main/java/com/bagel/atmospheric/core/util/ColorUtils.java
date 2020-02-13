package com.bagel.atmospheric.core.util;

import com.bagel.atmospheric.core.registry.AtmosphericBlocks;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.world.FoliageColors;
import net.minecraft.world.biome.BiomeColors;
import net.minecraftforge.fml.ModList;

public class ColorUtils
{
	public static void registerBlockColors() {
        BlockColors blockColors = Minecraft.getInstance().getBlockColors();
        blockColors.register((x, world, pos, u) -> world != null && pos != null ? BiomeColors.getFoliageColor(world, pos) : FoliageColors.get(0.5D, 1.0D), AtmosphericBlocks.ROSEWOOD_LEAVES.get());
        ItemColors itemColors = Minecraft.getInstance().getItemColors();
        itemColors.register((color, items) -> FoliageColors.get(0.5D, 1.0D), AtmosphericBlocks.ROSEWOOD_LEAVES.get());
        
        if (ModList.get().isLoaded("quark")) {
            blockColors.register((x, world, pos, u) -> world != null && pos != null ? BiomeColors.getFoliageColor(world, pos) : FoliageColors.get(0.5D, 1.0D), AtmosphericBlocks.ROSEWOOD_LEAF_CARPET.get());
        	itemColors.register((color, items) -> FoliageColors.get(0.5D, 1.0D), AtmosphericBlocks.ROSEWOOD_LEAF_CARPET.get());
        }
    }
}
