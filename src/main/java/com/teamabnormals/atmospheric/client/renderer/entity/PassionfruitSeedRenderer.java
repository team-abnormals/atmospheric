package com.teamabnormals.atmospheric.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import com.teamabnormals.atmospheric.client.renderer.entity.model.PassionfruitSeedModel;
import com.teamabnormals.atmospheric.common.entity.projectile.PassionfruitSeed;
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
public class PassionfruitSeedRenderer extends EntityRenderer<PassionfruitSeed> {
	private static final ResourceLocation PASSIONFRUIT_SEED_TEXTURE = new ResourceLocation(Atmospheric.MOD_ID, "textures/entity/projectiles/passionfruit_seed.png");
	private final PassionfruitSeedModel<PassionfruitSeed> model;

	public PassionfruitSeedRenderer(EntityRendererProvider.Context context) {
		super(context);
		this.model = new PassionfruitSeedModel<>(context.bakeLayer(AtmosphericModelLayers.PASSIONFRUIT_SEED));
	}

	@Override
	public void render(PassionfruitSeed entityIn, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
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

	@Override
	public ResourceLocation getTextureLocation(PassionfruitSeed entity) {
		return PASSIONFRUIT_SEED_TEXTURE;
	}
}
