package com.teamabnormals.atmospheric.core.other;

import com.google.common.collect.ImmutableMap;
import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import com.teamabnormals.atmospheric.core.registry.AtmosphericItems;
import com.teamabnormals.blueprint.core.util.TradeUtil;
import com.teamabnormals.blueprint.core.util.TradeUtil.BlueprintTrade;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.core.Registry;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.npc.VillagerTrades.ItemListing;
import net.minecraft.world.entity.npc.VillagerType;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = Atmospheric.MOD_ID)
public class AtmosphericTrades {

	@SubscribeEvent
	public static void onWandererTradesEvent(WandererTradesEvent event) {
		TradeUtil.addWandererTrades(event,
				new BlueprintTrade(1, AtmosphericBlocks.WARM_MONKEY_BRUSH.get().asItem(), 1, 6, 1),
				new BlueprintTrade(1, AtmosphericBlocks.HOT_MONKEY_BRUSH.get().asItem(), 1, 6, 1),
				new BlueprintTrade(1, AtmosphericBlocks.SCALDING_MONKEY_BRUSH.get().asItem(), 1, 6, 1),
				new BlueprintTrade(4, AtmosphericBlocks.PASSION_VINE.get().asItem(), 1, 8, 1),
				new BlueprintTrade(1, AtmosphericBlocks.ARID_SAND.get().asItem(), 8, 8, 1),
				new BlueprintTrade(1, AtmosphericBlocks.RED_ARID_SAND.get().asItem(), 4, 6, 1),
				new BlueprintTrade(1, AtmosphericBlocks.YUCCA_FLOWER.get().asItem(), 1, 8, 1),
				new BlueprintTrade(1, AtmosphericBlocks.WATER_HYACINTH.get().asItem(), 1, 8, 1),
				new BlueprintTrade(1, AtmosphericBlocks.GILIA.get().asItem(), 1, 12, 1),
				new BlueprintTrade(1, AtmosphericItems.ALOE_KERNELS.get(), 1, 12, 1),
				new BlueprintTrade(3, AtmosphericBlocks.BARREL_CACTUS.get().asItem(), 1, 8, 1),

				new BlueprintTrade(5, AtmosphericBlocks.ROSEWOOD_SAPLING.get().asItem(), 1, 8, 1),
				new BlueprintTrade(5, AtmosphericBlocks.YUCCA_SAPLING.get().asItem(), 1, 8, 1),
				new BlueprintTrade(5, AtmosphericBlocks.MORADO_SAPLING.get().asItem(), 1, 8, 1),
				new BlueprintTrade(5, AtmosphericBlocks.ASPEN_SAPLING.get().asItem(), 1, 8, 1),
				new BlueprintTrade(5, AtmosphericBlocks.KOUSA_SAPLING.get().asItem(), 1, 8, 1),
				new BlueprintTrade(5, AtmosphericBlocks.GRIMWOOD_SAPLING.get().asItem(), 1, 8, 1),
				new BlueprintTrade(5, AtmosphericBlocks.LAUREL_SAPLING.get().asItem(), 1, 8, 1),
				new BlueprintTrade(5, AtmosphericBlocks.DRY_LAUREL_SAPLING.get().asItem(), 1, 8, 1),
				new BlueprintTrade(5, AtmosphericBlocks.CURRANT_SEEDLING.get().asItem(), 1, 8, 1)
		);
	}

	@SubscribeEvent
	public static void onVillagerTradesEvent(VillagerTradesEvent event) {
		VillagerProfession profession = event.getType();
		if (profession.equals(VillagerProfession.FARMER)) {
			TradeUtil.addVillagerTrades(event, 2,
					new BlueprintTrade(1, AtmosphericItems.PASSION_FRUIT_TART.get(), 4, 12, 5),
					new BlueprintTrade(AtmosphericItems.ALOE_LEAVES.get(), 4, 1, 16, 15),
					new BlueprintTrade(AtmosphericItems.YUCCA_FRUIT.get(), 3, 1, 12, 10)
			);

			TradeUtil.addVillagerTrades(event, 3,
					new BlueprintTrade(AtmosphericItems.PASSION_FRUIT.get(), 8, 1, 12, 10)
			);

			TradeUtil.addVillagerTrades(event, 4,
					new BlueprintTrade(3, AtmosphericItems.YUCCA_GATEAU.get(), 1, 12, 15)
			);
		}

		if (profession.equals(VillagerProfession.MASON)) {
			TradeUtil.addVillagerTrades(event, 3,
					new BlueprintTrade(1, AtmosphericBlocks.IVORY_TRAVERTINE.get().asItem(), 4, 16, 10),
					new BlueprintTrade(1, AtmosphericBlocks.PEACH_TRAVERTINE.get().asItem(), 4, 16, 10),
					new BlueprintTrade(1, AtmosphericBlocks.PERSIMMON_TRAVERTINE.get().asItem(), 4, 16, 10),
					new BlueprintTrade(1, AtmosphericBlocks.SAFFRON_TRAVERTINE.get().asItem(), 4, 16, 10)
			);
		}

		if (profession.equals(VillagerProfession.FISHERMAN)) {
			Int2ObjectMap<List<ItemListing>> trades = event.getTrades();
			for (ItemListing listing : trades.get(TradeUtil.MASTER)) {
				VillagerType dunes = Registry.VILLAGER_TYPE.get(Atmospheric.location("dunes"));
				if (listing instanceof VillagerTrades.EmeraldsForVillagerTypeItem trade && !trade.trades.containsKey(dunes)) {
					trade.trades = ImmutableMap.<VillagerType, Item>builder()
							.putAll(trade.trades)
							.put(dunes, AtmosphericItems.YUCCA_BOAT.getFirst().get())
							.build();
				}
			}
		}
	}
}