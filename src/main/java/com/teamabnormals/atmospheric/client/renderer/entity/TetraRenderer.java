package com.teamabnormals.atmospheric.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.teamabnormals.atmospheric.client.model.TetraModel;
import com.teamabnormals.atmospheric.common.entity.Tetra;
import com.teamabnormals.atmospheric.core.other.AtmosphericModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TetraRenderer extends MobRenderer<Tetra, TetraModel<Tetra>> {

	public TetraRenderer(EntityRendererProvider.Context context) {
		super(context, new TetraModel<>(context.bakeLayer(AtmosphericModelLayers.TETRA)), 0.15F);
	}

	@Override
	public ResourceLocation getTextureLocation(Tetra tetra) {
		return tetra.getVariant().texture().withSuffix(".png");
	}

	@Override
	protected void setupRotations(Tetra tetra, PoseStack stack, float p_116228_, float p_116229_, float p_116230_) {
		super.setupRotations(tetra, stack, p_116228_, p_116229_, p_116230_);
		float f = 4.3F * Mth.sin(0.6F * p_116228_);
		stack.mulPose(Axis.YP.rotationDegrees(f));
		if (!tetra.isInWater()) {
			stack.translate(0.2F, 0.1F, 0.0F);
			stack.mulPose(Axis.ZP.rotationDegrees(90.0F));
		}
	}
}