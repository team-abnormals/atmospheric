package com.teamabnormals.atmospheric.core.mixin.client;

import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.atmospheric.core.other.tags.AtmosphericBiomeTags;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import javax.annotation.Nullable;
import java.util.Calendar;

@Mixin(LevelRenderer.class)
public class LevelRendererMixin {
	@Unique
	private static final ResourceLocation TWIN_SUNS_LOCATION = Atmospheric.location("textures/environment/twin_suns.png");

	@Shadow
	@Final
	private Minecraft minecraft;

	@Shadow
	@Nullable
	private ClientLevel level;

	@ModifyArg(method = "renderSky", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;setShaderTexture(ILnet/minecraft/resources/ResourceLocation;)V", ordinal = 0), index = 1)
	private ResourceLocation getTextureLocation(ResourceLocation texture) {
		Calendar calendar = Calendar.getInstance();
		if (calendar.get(Calendar.MONTH) + 1 == 5 && calendar.get(Calendar.DATE) == 4) {
			Player player = this.minecraft.player;
			if (player != null && this.level != null && this.level.getBiome(player.blockPosition()).is(AtmosphericBiomeTags.IS_DUNES)) {
				return TWIN_SUNS_LOCATION;
			}
		}

		return texture;
	}

}
