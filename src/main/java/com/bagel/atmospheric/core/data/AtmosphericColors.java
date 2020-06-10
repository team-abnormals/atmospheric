package com.bagel.atmospheric.core.data;

import java.util.Arrays;

import com.bagel.atmospheric.core.registry.AtmosphericBlocks;
import com.teamabnormals.abnormals_core.core.utils.DataUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.world.FoliageColors;
import net.minecraft.world.biome.BiomeColors;

public class AtmosphericColors
{
	public static void registerBlockColors() {
		
		BlockColors blockColors = Minecraft.getInstance().getBlockColors();
		DataUtils.registerBlockColor(blockColors, (x, world, pos, u) -> world != null && pos != null ? BiomeColors.getFoliageColor(world, pos) : FoliageColors.get(0.5D, 1.0D), Arrays.asList(AtmosphericBlocks.ROSEWOOD_LEAVES));
		DataUtils.registerBlockColor(blockColors, (x, world, pos, u) -> world != null && pos != null ? BiomeColors.getFoliageColor(world, pos) : FoliageColors.get(0.5D, 1.0D), Arrays.asList(AtmosphericBlocks.ROSEWOOD_LEAF_CARPET));
		DataUtils.registerBlockColor(blockColors, (x, world, pos, u) -> world != null && pos != null ? BiomeColors.getFoliageColor(world, pos) : FoliageColors.get(0.5D, 1.0D), Arrays.asList(AtmosphericBlocks.YUCCA_LEAVES));
        DataUtils.registerBlockColor(blockColors, (x, world, pos, u) -> world != null && pos != null ? BiomeColors.getFoliageColor(world, pos) : FoliageColors.get(0.5D, 1.0D), Arrays.asList(AtmosphericBlocks.YUCCA_LEAF_CARPET));

        ItemColors itemColors = Minecraft.getInstance().getItemColors();
        DataUtils.registerBlockItemColor(itemColors, (color, items) -> FoliageColors.get(0.5D, 1.0D), Arrays.asList(AtmosphericBlocks.ROSEWOOD_LEAVES));
        DataUtils.registerBlockItemColor(itemColors, (color, items) -> FoliageColors.get(0.5D, 1.0D), Arrays.asList(AtmosphericBlocks.ROSEWOOD_LEAF_CARPET));
        DataUtils.registerBlockItemColor(itemColors, (color, items) -> FoliageColors.get(0.5D, 1.0D), Arrays.asList(AtmosphericBlocks.YUCCA_LEAVES));
        DataUtils.registerBlockItemColor(itemColors, (color, items) -> FoliageColors.get(0.5D, 1.0D), Arrays.asList(AtmosphericBlocks.YUCCA_LEAF_CARPET));
    }
}
