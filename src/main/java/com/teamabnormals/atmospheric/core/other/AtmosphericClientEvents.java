package com.teamabnormals.atmospheric.core.other;

import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.atmospheric.core.AtmosphericConfig;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = Atmospheric.MOD_ID, value = Dist.CLIENT)
public class AtmosphericClientEvents {

	@SubscribeEvent
	public static void onItemTooltip(ItemTooltipEvent event) {
		Item item = event.getItemStack().getItem();
		ResourceLocation name = ForgeRegistries.ITEMS.getKey(item);
		Player player = event.getEntity();
		if (name == null || player == null)
			return;

		if (player.getAbilities().instabuild && AtmosphericConfig.CLIENT.showUnobtainableDescription.get() && (name.getNamespace().equals(Atmospheric.MOD_ID) || name.getNamespace().equals("abnormals_delight"))) {
			String id = name.getPath();
			if (id.contains("travertine") || id.contains("kousa") || id.contains("grimwood") || id.contains("crustose"))
				event.getToolTip().add(Component.translatable("tooltip.atmospheric.unobtainable").withStyle(ChatFormatting.GRAY));
		}
	}
}