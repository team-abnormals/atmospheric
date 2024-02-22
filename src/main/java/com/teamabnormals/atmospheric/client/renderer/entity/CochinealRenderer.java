package com.teamabnormals.atmospheric.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.teamabnormals.atmospheric.client.model.CochinealModel;
import com.teamabnormals.atmospheric.common.entity.Cochineal;
import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.atmospheric.core.other.AtmosphericModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.SaddleLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CochinealRenderer<T extends Cochineal> extends MobRenderer<T, CochinealModel<T>> {
	private static final ResourceLocation COCHINEAL_LOCATION = Atmospheric.location("textures/entity/cochineal/cochineal.png");

	public CochinealRenderer(EntityRendererProvider.Context context) {
		super(context, new CochinealModel<>(context.bakeLayer(AtmosphericModelLayers.COCHINEAL)), 0.6F);
		this.addLayer(new SaddleLayer<>(this, new CochinealModel<>(context.bakeLayer(AtmosphericModelLayers.COCHINEAL_SADDLE)), Atmospheric.location("textures/entity/cochineal/cochineal_saddle.png")));
	}

	@Override
	protected void scale(T cochineal, PoseStack stack, float partialTicks) {
		if (cochineal.isBaby()) {
			stack.scale(0.4F, 0.4F, 0.4F);
			this.shadowRadius = 0.4F;
		} else {
			this.shadowRadius = 1.0F;
		}
	}

	@Override
	public ResourceLocation getTextureLocation(T cochineal) {
		return COCHINEAL_LOCATION;
	}
}