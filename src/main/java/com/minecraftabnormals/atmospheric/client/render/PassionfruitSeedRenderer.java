package com.minecraftabnormals.atmospheric.client.render;

import com.minecraftabnormals.atmospheric.client.model.PassionfruitSeedModel;
import com.minecraftabnormals.atmospheric.common.entity.PassionfruitSeedEntity;
import com.minecraftabnormals.atmospheric.core.Atmospheric;
import com.minecraftabnormals.atmospheric.core.other.AtmosphericModelLayers;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PassionfruitSeedRenderer extends EntityRenderer<PassionfruitSeedEntity> {
	private static final ResourceLocation PASSIONFRUIT_SEED_TEXTURE = new ResourceLocation(Atmospheric.MOD_ID, "textures/entity/projectile/passionfruit_seed.png");
	private final PassionfruitSeedModel<PassionfruitSeedEntity> model;

	public PassionfruitSeedRenderer(EntityRendererProvider.Context context) {
		super(context);
		this.model = new PassionfruitSeedModel<>(context.bakeLayer(AtmosphericModelLayers.PASSIONFRUIT_SEED));
	}

	public void render(PassionfruitSeedEntity entityIn, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
		matrixStackIn.pushPose();
		matrixStackIn.translate(0.0D, 0.15F, 0.0D);
		matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(Mth.lerp(partialTicks, entityIn.yRotO, entityIn.getYRot()) - 90.0F));
		matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(Mth.lerp(partialTicks, entityIn.xRotO, entityIn.getXRot())));
		this.model.setupAnim(entityIn, partialTicks, 0.0F, -0.1F, 0.0F, 0.0F);
		VertexConsumer ivertexbuilder = bufferIn.getBuffer(this.model.renderType(PASSIONFRUIT_SEED_TEXTURE));
		this.model.renderToBuffer(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
		matrixStackIn.popPose();
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
	}

	public ResourceLocation getTextureLocation(PassionfruitSeedEntity entity) {
		return PASSIONFRUIT_SEED_TEXTURE;
	}
}
