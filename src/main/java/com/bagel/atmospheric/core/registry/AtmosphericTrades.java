package com.bagel.atmospheric.core.registry;

import com.bagel.atmospheric.core.Atmospheric;
import com.bagel.atmospheric.core.util.TradeUtils;

import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Atmospheric.MODID)
public class AtmosphericTrades {
	
	@SubscribeEvent
	public static void onWandererTradesEvent(WandererTradesEvent event) {
		event.getGenericTrades().add(new TradeUtils.ItemsForEmeraldsTrade(AtmosphericBlocks.MONKEY_BRUSH.get(), 1, 1, 6, 1));
		event.getGenericTrades().add(new TradeUtils.ItemsForEmeraldsTrade(AtmosphericBlocks.ROSEWOOD_SAPLING.get(), 5, 1, 8, 1));
		event.getGenericTrades().add(new TradeUtils.ItemsForEmeraldsTrade(AtmosphericBlocks.PASSION_VINE.get(), 8, 1, 8, 1));
	}
	
	@SubscribeEvent
	public static void onVillagerTradesEvent(VillagerTradesEvent event) {
		if(event.getType() == VillagerProfession.FARMER) {
			event.getTrades().get(3).add(new TradeUtils.EmeraldsForItemsTrade(AtmosphericItems.PASSIONFRUIT.get(), 8, 1, 12, 20));
			event.getTrades().get(3).add(new TradeUtils.ItemsForEmeraldsTrade(AtmosphericItems.PASSIONFRUIT_TART.get(), 4, 16, 4, 15));	
		}
    }
}
