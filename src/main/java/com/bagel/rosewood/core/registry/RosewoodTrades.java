package com.bagel.rosewood.core.registry;

import com.bagel.rosewood.core.Rosewood;
import com.bagel.rosewood.core.util.TradeUtils;

import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Rosewood.MODID)
public class RosewoodTrades {
	
	@SubscribeEvent
	public static void onWandererTradesEvent(WandererTradesEvent event) {
		event.getGenericTrades().add(new TradeUtils.ItemsForEmeraldsTrade(RosewoodBlocks.MONKEY_BRUSH.get(), 1, 1, 6, 1));
		event.getGenericTrades().add(new TradeUtils.ItemsForEmeraldsTrade(RosewoodBlocks.ROSEWOOD_SAPLING.get(), 5, 1, 8, 1));
		event.getGenericTrades().add(new TradeUtils.ItemsForEmeraldsTrade(RosewoodBlocks.PASSION_VINE.get(), 8, 1, 8, 1));
	}
	
	@SubscribeEvent
	public static void onVillagerTradesEvent(VillagerTradesEvent event) {
		if(event.getType() == VillagerProfession.FARMER) {
			event.getTrades().get(3).add(new TradeUtils.EmeraldsForItemsTrade(RosewoodItems.PASSIONFRUIT.get(), 8, 1, 12, 20));
			event.getTrades().get(3).add(new TradeUtils.ItemsForEmeraldsTrade(RosewoodItems.PASSIONFRUIT_TART.get(), 4, 16, 4, 15));	
			event.getTrades().get(5).add(new TradeUtils.ItemsForEmeraldsTrade(RosewoodItems.SHIMMERING_PASSIONFRUIT.get(), 3, 3, 5, 30));	
		}
    }
}
