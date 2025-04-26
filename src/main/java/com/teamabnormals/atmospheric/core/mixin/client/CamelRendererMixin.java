package com.teamabnormals.atmospheric.core.mixin.client;

import com.teamabnormals.atmospheric.common.entity.CamelVariant;
import net.minecraft.client.renderer.entity.CamelRenderer;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.VariantHolder;
import net.minecraft.world.entity.animal.camel.Camel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CamelRenderer.class)
public abstract class CamelRendererMixin {

	@Inject(method = "getTextureLocation(Lnet/minecraft/world/entity/animal/camel/Camel;)Lnet/minecraft/resources/ResourceLocation;", at = @At("RETURN"), cancellable = true)
	private void getTextureLocation(Camel camel, CallbackInfoReturnable<ResourceLocation> cir) {
		cir.setReturnValue(((VariantHolder<Holder<CamelVariant>>) camel).getVariant().value().assetId().withPrefix("textures/").withSuffix(".png"));
	}
}