package com.bagel.rosewood.common.blocks;

import com.bagel.rosewood.core.Rosewood;
import com.bagel.rosewood.core.registry.RosewoodBlocks;

import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.BlockItem;
import net.minecraft.world.FoliageColors;
import net.minecraft.world.biome.BiomeColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = Rosewood.MODID, bus = Bus.MOD)
public class BlockColorManager
{
	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public static void onBlockColorsInit(ColorHandlerEvent.Block event)
	{
		final BlockColors blockColors = event.getBlockColors();

		//registers the colors for blocks that changes colors based on biome
		blockColors.register((p_210225_0_, p_210225_1_, pos, p_210225_3_) ->
		{
			return p_210225_1_ != null && pos != null ? BiomeColors.getFoliageColor(p_210225_1_, pos) : FoliageColors.get(0.5D, 1.0D);
		}, RosewoodBlocks.ROSEWOOD_LEAVES.get());
		if(ModList.get().isLoaded("quark")) {
			blockColors.register((p_210225_0_, p_210225_1_, pos, p_210225_3_) ->
			{
				return p_210225_1_ != null && pos != null ? BiomeColors.getFoliageColor(p_210225_1_, pos) : FoliageColors.get(0.5D, 1.0D);
			}, RosewoodBlocks.ROSEWOOD_LEAF_CARPET.get());
		}
	}
	
	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public static void onItemColorsInit(ColorHandlerEvent.Item event)
	{
		final BlockColors blockColors = event.getBlockColors();
		final ItemColors itemColors = event.getItemColors();

		// Use the Block's colour handler for an ItemBlock
		final IItemColor itemBlockColourHandler = (stack, tintIndex) ->
		{
			final BlockState state = ((BlockItem) stack.getItem()).getBlock().getDefaultState();
			return blockColors.getColor(state, null, null, tintIndex);
		};

		itemColors.register(itemBlockColourHandler, RosewoodBlocks.ROSEWOOD_LEAVES.get());
		if(ModList.get().isLoaded("quark")) {
			itemColors.register(itemBlockColourHandler, RosewoodBlocks.ROSEWOOD_LEAF_CARPET.get());
		}
	}
}
