package com.minecraftabnormals.atmospheric.core.other;

import com.minecraftabnormals.atmospheric.core.Atmospheric;
import com.minecraftabnormals.atmospheric.core.AtmosphericConfig;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
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
		PlayerEntity player = event.getPlayer();
		if (name == null || player == null)
			return;

		if (player.abilities.instabuild && AtmosphericConfig.CLIENT.showUnobtainableDescription.get() && (name.getNamespace().equals(Atmospheric.MOD_ID) || name.getNamespace().equals("abnormals_delight"))) {
			String id = name.getPath();
			if (id.contains("travertine") || id.contains("aspen") || id.contains("kousa") || id.contains("grimwood") || id.contains("crustose"))
				event.getToolTip().add(new TranslationTextComponent("tooltip.atmospheric.unobtainable").withStyle(TextFormatting.GRAY));
		}
	}
}