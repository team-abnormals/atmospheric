package com.bagel.rosewood.core.util;

import com.bagel.rosewood.core.registry.RosewoodBlocks;

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
        blockColors.register((x, world, pos, u) -> world != null && pos != null ? BiomeColors.getFoliageColor(world, pos) : FoliageColors.get(0.5D, 1.0D), RosewoodBlocks.ROSEWOOD_LEAVES.get());
        ItemColors itemColors = Minecraft.getInstance().getItemColors();
        itemColors.register((color, items) -> FoliageColors.get(0.5D, 1.0D), RosewoodBlocks.ROSEWOOD_LEAVES.get());
        
        if (ModList.get().isLoaded("quark")) {
            blockColors.register((x, world, pos, u) -> world != null && pos != null ? BiomeColors.getFoliageColor(world, pos) : FoliageColors.get(0.5D, 1.0D), RosewoodBlocks.ROSEWOOD_LEAF_CARPET.get());
        	itemColors.register((color, items) -> FoliageColors.get(0.5D, 1.0D), RosewoodBlocks.ROSEWOOD_LEAF_CARPET.get());
        }
    }
}
