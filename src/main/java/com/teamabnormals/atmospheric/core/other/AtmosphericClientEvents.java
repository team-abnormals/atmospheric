package com.teamabnormals.atmospheric.core.other;

import com.mojang.blaze3d.systems.RenderSystem;
import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.atmospheric.core.registry.AtmosphericBiomes;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.Holder;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ViewportEvent.RenderFog;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = Atmospheric.MOD_ID, value = Dist.CLIENT)
public class AtmosphericClientEvents {

	//	@SubscribeEvent
	public static void onRenderFog(RenderFog event) {
		if (event.getCamera().getEntity() instanceof LocalPlayer player) {
			Holder<Biome> holder = player.level.getBiome(player.blockPosition());
			int brightness = player.level.getBrightness(LightLayer.SKY, player.blockPosition());
			if (holder.is(AtmosphericBiomes.GRIMWOODS.getKey()) && brightness > 0) {
				RenderSystem.setShaderFogStart(0.0F);
				// float inc = (event.getFarPlaneDistance() - 48.0F) / 15.0F;
				// event.getFarPlaneDistance() - (brightness * inc)
				RenderSystem.setShaderFogEnd(Math.min(event.getFarPlaneDistance(), 48.0F));
			}
		}
	}
}