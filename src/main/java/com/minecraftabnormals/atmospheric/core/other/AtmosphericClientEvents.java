package com.minecraftabnormals.atmospheric.core.other;

import com.minecraftabnormals.atmospheric.core.Atmospheric;
import com.minecraftabnormals.atmospheric.core.AtmosphericConfig;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
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

		if (name != null && AtmosphericConfig.CLIENT.showUnobtainableDescription.get() && name.getNamespace().equals(Atmospheric.MOD_ID)) {
			String id = name.getPath();
			if (id.contains("travertine") || id.contains("aspen") || id.contains("kousa") || id.contains("grimwood") || id.contains("crustose"))
				event.getToolTip().add(new TranslationTextComponent("tooltip.atmospheric.unobtainable").mergeStyle(TextFormatting.GRAY));
		}
	}
}