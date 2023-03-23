package com.teamabnormals.atmospheric.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import com.teamabnormals.atmospheric.client.renderer.entity.model.PassionFruitSeedModel;
import com.teamabnormals.atmospheric.common.entity.projectile.PassionFruitSeed;
import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.atmospheric.core.other.AtmosphericModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PassionFruitSeedRenderer extends EntityRenderer<PassionFruitSeed> {
	private static final ResourceLocation PASSION_FRUIT_SEED_TEXTURE = Atmospheric.location("textures/entity/projectiles/passion_fruit_seed.png");
	private final PassionFruitSeedModel<PassionFruitSeed> model;

	public PassionFruitSeedRenderer(EntityRendererProvider.Context context) {
		super(context);
		this.model = new PassionFruitSeedModel<>(context.bakeLayer(AtmosphericModelLayers.PASSION_FRUIT_SEED));
	}

	@Override
	public void render(PassionFruitSeed entityIn, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
		matrixStackIn.pushPose();
		matrixStackIn.translate(0.0D, 0.15F, 0.0D);
		matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(Mth.lerp(partialTicks, entityIn.yRotO, entityIn.getYRot()) - 90.0F));
		matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(Mth.lerp(partialTicks, entityIn.xRotO, entityIn.getXRot())));
		this.model.setupAnim(entityIn, partialTicks, 0.0F, -0.1F, 0.0F, 0.0F);
		VertexConsumer ivertexbuilder = bufferIn.getBuffer(this.model.renderType(PASSION_FRUIT_SEED_TEXTURE));
		this.model.renderToBuffer(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
		matrixStackIn.popPose();
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
	}

	@Override
	public ResourceLocation getTextureLocation(PassionFruitSeed entity) {
		return PASSION_FRUIT_SEED_TEXTURE;
	}
}
