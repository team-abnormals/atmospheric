package com.minecraftabnormals.atmospheric.core.other;

import com.minecraftabnormals.atmospheric.core.Atmospheric;
import com.minecraftabnormals.atmospheric.core.registry.AtmosphericBlocks;
import com.minecraftabnormals.atmospheric.core.registry.AtmosphericItems;
import com.teamabnormals.abnormals_core.core.utils.TradeUtils;

import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Atmospheric.MODID)
public class AtmosphericTrades {
	
	@SubscribeEvent
	public static void onWandererTradesEvent(WandererTradesEvent event) {
		event.getGenericTrades().add(new TradeUtils.ItemsForEmeraldsTrade(AtmosphericBlocks.WARM_MONKEY_BRUSH.get(), 1, 1, 6, 1));
		event.getGenericTrades().add(new TradeUtils.ItemsForEmeraldsTrade(AtmosphericBlocks.HOT_MONKEY_BRUSH.get(), 1, 1, 6, 1));
		event.getGenericTrades().add(new TradeUtils.ItemsForEmeraldsTrade(AtmosphericBlocks.SCALDING_MONKEY_BRUSH.get(), 1, 1, 6, 1));
		event.getGenericTrades().add(new TradeUtils.ItemsForEmeraldsTrade(AtmosphericBlocks.PASSION_VINE.get(), 4, 1, 8, 1));
		event.getGenericTrades().add(new TradeUtils.ItemsForEmeraldsTrade(AtmosphericBlocks.ARID_SAND.get(), 1, 8, 8, 1));
		event.getGenericTrades().add(new TradeUtils.ItemsForEmeraldsTrade(AtmosphericBlocks.RED_ARID_SAND.get(), 1, 4, 6, 1));
		event.getGenericTrades().add(new TradeUtils.ItemsForEmeraldsTrade(AtmosphericBlocks.YUCCA_FLOWER.get(), 1, 1, 8, 1));
		event.getGenericTrades().add(new TradeUtils.ItemsForEmeraldsTrade(AtmosphericBlocks.GILIA.get(), 1, 1, 12, 1));
		event.getGenericTrades().add(new TradeUtils.ItemsForEmeraldsTrade(AtmosphericItems.ALOE_KERNELS.get(), 1, 1, 12, 1));
		event.getGenericTrades().add(new TradeUtils.ItemsForEmeraldsTrade(AtmosphericBlocks.BARREL_CACTUS.get(), 3, 1, 8, 1));
		
		event.getGenericTrades().add(new TradeUtils.ItemsForEmeraldsTrade(AtmosphericBlocks.ROSEWOOD_SAPLING.get(), 5, 1, 8, 1));
		event.getGenericTrades().add(new TradeUtils.ItemsForEmeraldsTrade(AtmosphericBlocks.YUCCA_SAPLING.get(), 5, 1, 8, 1));
		//event.getGenericTrades().add(new TradeUtils.ItemsForEmeraldsTrade(AtmosphericBlocks.KOUSA_SAPLING.get(), 5, 1, 8, 1));
		//event.getGenericTrades().add(new TradeUtils.ItemsForEmeraldsTrade(AtmosphericBlocks.ASPEN_SAPLING.get(), 5, 1, 8, 1));
	}
	
	@SubscribeEvent
	public static void onVillagerTradesEvent(VillagerTradesEvent event) {
		if(event.getType() == VillagerProfession.FARMER) {
			event.getTrades().get(3).add(new TradeUtils.EmeraldsForItemsTrade(AtmosphericItems.PASSIONFRUIT.get(), 8, 1, 12, 10));
			event.getTrades().get(3).add(new TradeUtils.ItemsForEmeraldsTrade(AtmosphericItems.PASSIONFRUIT_TART.get(), 16, 4, 4, 15));	
			event.getTrades().get(2).add(new TradeUtils.EmeraldsForItemsTrade(AtmosphericItems.ALOE_LEAVES.get(), 4, 1, 16, 15));
			event.getTrades().get(2).add(new TradeUtils.EmeraldsForItemsTrade(AtmosphericItems.YUCCA_FRUIT.get(), 3, 1, 12, 10));
			event.getTrades().get(2).add(new TradeUtils.ItemsForEmeraldsTrade(AtmosphericItems.YUCCA_GATEAU.get(), 12, 1, 1, 10));	
		}
    }
}
