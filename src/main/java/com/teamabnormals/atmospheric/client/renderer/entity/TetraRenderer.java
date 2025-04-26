package com.teamabnormals.atmospheric.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.teamabnormals.atmospheric.client.model.TetraModel;
import com.teamabnormals.atmospheric.common.entity.Tetra;
import com.teamabnormals.atmospheric.core.other.AtmosphericModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TetraRenderer extends MobRenderer<Tetra, TetraModel<Tetra>> {

	public TetraRenderer(EntityRendererProvider.Context context) {
		super(context, new TetraModel<>(context.bakeLayer(AtmosphericModelLayers.TETRA)), 0.15F);
	}

	@Override
	public ResourceLocation getTextureLocation(Tetra tetra) {
		return tetra.getVariant().value().assetId().withPrefix("textures/").withSuffix(".png");
	}

	@Override
	protected void setupRotations(Tetra tetra, PoseStack poseStack, float bob, float yBodyRot, float partialTick, float scale) {
		super.setupRotations(tetra, poseStack, bob, yBodyRot, partialTick, scale);
		float f = 4.3F * Mth.sin(0.6F * bob);
		poseStack.mulPose(Axis.YP.rotationDegrees(f));
		if (!tetra.isInWater()) {
			poseStack.translate(0.2F, 0.1F, 0.0F);
			poseStack.mulPose(Axis.ZP.rotationDegrees(90.0F));
		}
	}

	@Override
	protected int getBlockLightLevel(Tetra tetra, BlockPos pos) {
		return Mth.clamp(super.getBlockLightLevel(tetra, pos) + 7, 0, 15);
	}
}