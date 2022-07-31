package com.teamabnormals.atmospheric.core.other;

import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.atmospheric.core.AtmosphericConfig;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Atmospheric.MOD_ID, value = Dist.CLIENT)
public class AtmosphericClientEvents {

	@SubscribeEvent
	public static void onItemTooltip(ItemTooltipEvent event) {
		Item item = event.getItemStack().getItem();
		ResourceLocation name = item.getRegistryName();
		Player player = event.getPlayer();
		if (name == null || player == null)
			return;

		if (player.getAbilities().instabuild && AtmosphericConfig.CLIENT.showUnobtainableDescription.get() && (name.getNamespace().equals(Atmospheric.MOD_ID) || name.getNamespace().equals("abnormals_delight"))) {
			String id = name.getPath();
			if (id.contains("travertine") || id.contains("kousa") || id.contains("grimwood") || id.contains("crustose"))
				event.getToolTip().add(new TranslatableComponent("tooltip.atmospheric.unobtainable").withStyle(ChatFormatting.GRAY));
		}
	}
}