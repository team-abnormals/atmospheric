package com.teamabnormals.atmospheric.core.other;

import com.mojang.blaze3d.systems.RenderSystem;
import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.atmospheric.core.registry.AtmosphericItems;
import com.teamabnormals.atmospheric.core.registry.datapack.AtmosphericBiomes;
import net.minecraft.ChatFormatting;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.biome.Biome;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ViewportEvent.RenderFog;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber(modid = Atmospheric.MOD_ID, value = Dist.CLIENT)
public class AtmosphericClientEvents {

	@SubscribeEvent
	public static void onRenderFog(RenderFog event) {
		if (event.getCamera().getEntity() instanceof LocalPlayer player) {
			Holder<Biome> holder = player.level().getBiome(player.blockPosition());
			int brightness = player.level().getBrightness(LightLayer.SKY, player.blockPosition());
			if (holder.is(AtmosphericBiomes.GRIMWOODS) && brightness > 0) {
				RenderSystem.setShaderFogStart(0.0F);
				// float inc = (event.getFarPlaneDistance() - 48.0F) / 15.0F;
				// event.getFarPlaneDistance() - (brightness * inc)
				RenderSystem.setShaderFogEnd(Math.min(event.getFarPlaneDistance(), 48.0F));
			}
		}
	}

	@SubscribeEvent
	public static void onPlayerTick(PlayerTickEvent.Pre event) {
		if (AtmosphericEvents.isAprilFools()) {
			Player player = event.getEntity();
			RandomSource random = player.getRandom();
			if (random.nextInt(401) == 0 && player.getInventory().contains(AtmosphericItems.ORANGE.get().getDefaultInstance())) {
				player.displayClientMessage(Component.literal(getMessage(random)).withStyle(ChatFormatting.GOLD), true);
			}
		}
	}

	public static final String[] COMMON = {"Hey!", "Hey! Hey!", "Hey Apple!", "Apple!"};
	public static final String[] RARE = {"Knife!", "Orange you glad I didn't say apple again?", "Can you do ten push-ups in ten seconds?", "Blah blah blah!", "Hey Pear!"};

	public static void registerItemProperties() {
		ItemProperties.register(AtmosphericItems.ORANGE.get(), Atmospheric.location("hey_apple"), (stack, level, entity, hash) -> AtmosphericEvents.isAprilFools() ? 1.0F : 0.0F);
	}

	public static String getMessage(RandomSource random) {
		return random.nextInt(4) == 0 ? RARE[random.nextInt(RARE.length - 1)] : COMMON[random.nextInt(COMMON.length - 1)];
	}
}