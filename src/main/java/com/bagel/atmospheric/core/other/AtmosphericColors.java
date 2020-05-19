package com.bagel.atmospheric.core.other;

import java.util.Arrays;
import java.util.List;

import com.bagel.atmospheric.core.registry.AtmosphericBlocks;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.world.FoliageColors;
import net.minecraft.world.biome.BiomeColors;
import net.minecraftforge.fml.RegistryObject;

public class AtmosphericColors
{
	public static void registerBlockColors() {
		
		BlockColors blockColors = Minecraft.getInstance().getBlockColors();
		registerBlockColor(blockColors, (x, world, pos, u) -> world != null && pos != null ? BiomeColors.getFoliageColor(world, pos) : FoliageColors.get(0.5D, 1.0D), Arrays.asList(AtmosphericBlocks.ROSEWOOD_LEAVES));
		registerBlockColor(blockColors, (x, world, pos, u) -> world != null && pos != null ? BiomeColors.getFoliageColor(world, pos) : FoliageColors.get(0.5D, 1.0D), Arrays.asList(AtmosphericBlocks.ROSEWOOD_LEAF_CARPET));
        registerBlockColor(blockColors, (x, world, pos, u) -> world != null && pos != null ? BiomeColors.getFoliageColor(world, pos) : FoliageColors.get(0.5D, 1.0D), Arrays.asList(AtmosphericBlocks.YUCCA_LEAVES));
        registerBlockColor(blockColors, (x, world, pos, u) -> world != null && pos != null ? BiomeColors.getFoliageColor(world, pos) : FoliageColors.get(0.5D, 1.0D), Arrays.asList(AtmosphericBlocks.YUCCA_LEAF_CARPET));

        ItemColors itemColors = Minecraft.getInstance().getItemColors();
        registerBlockItemColor(itemColors, (color, items) -> FoliageColors.get(0.5D, 1.0D), Arrays.asList(AtmosphericBlocks.ROSEWOOD_LEAVES));
		registerBlockItemColor(itemColors, (color, items) -> FoliageColors.get(0.5D, 1.0D), Arrays.asList(AtmosphericBlocks.ROSEWOOD_LEAF_CARPET));
		registerBlockItemColor(itemColors, (color, items) -> FoliageColors.get(0.5D, 1.0D), Arrays.asList(AtmosphericBlocks.YUCCA_LEAVES));
		registerBlockItemColor(itemColors, (color, items) -> FoliageColors.get(0.5D, 1.0D), Arrays.asList(AtmosphericBlocks.YUCCA_LEAF_CARPET));
    }
	
	private static void registerBlockColor(BlockColors blockColors, IBlockColor color, List<RegistryObject<Block>> blocksIn) {
		List<RegistryObject<Block>> registryObjects = blocksIn;
		registryObjects.removeIf(block -> !block.isPresent());
		if(registryObjects.size() > 0) {
			Block[] blocks = new Block[registryObjects.size()];
			for(int i = 0; i < registryObjects.size(); i++) {
				blocks[i] = registryObjects.get(i).get();
			}
			blockColors.register(color, blocks);
		}
	}
	
	private static void registerBlockItemColor(ItemColors blockColors, IItemColor color, List<RegistryObject<Block>> blocksIn) {
		List<RegistryObject<Block>> registryObjects = blocksIn;
		registryObjects.removeIf(block -> !block.isPresent());
		if(registryObjects.size() > 0) {
			Block[] blocks = new Block[registryObjects.size()];
			for(int i = 0; i < registryObjects.size(); i++) {
				blocks[i] = registryObjects.get(i).get();
			}
			blockColors.register(color, blocks);
		}
	}
}
