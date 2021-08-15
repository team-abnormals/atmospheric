package com.minecraftabnormals.atmospheric.client.render;

import com.minecraftabnormals.atmospheric.client.model.PassionfruitSeedModel;
import com.minecraftabnormals.atmospheric.common.entity.PassionfruitSeedEntity;
import com.minecraftabnormals.atmospheric.core.Atmospheric;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PassionfruitSeedRenderer extends EntityRenderer<PassionfruitSeedEntity> {
	private static final ResourceLocation PASSIONFRUIT_SEED_TEXTURE = new ResourceLocation(Atmospheric.MOD_ID, "textures/entity/projectile/passionfruit_seed.png");
	private final PassionfruitSeedModel<PassionfruitSeedEntity> model = new PassionfruitSeedModel<>();

	public PassionfruitSeedRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn);
	}

	public void render(PassionfruitSeedEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
		matrixStackIn.pushPose();
		matrixStackIn.translate(0.0D, (double) 0.15F, 0.0D);
		matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(MathHelper.lerp(partialTicks, entityIn.yRotO, entityIn.yRot) - 90.0F));
		matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(MathHelper.lerp(partialTicks, entityIn.xRotO, entityIn.xRot)));
		this.model.setupAnim(entityIn, partialTicks, 0.0F, -0.1F, 0.0F, 0.0F);
		IVertexBuilder ivertexbuilder = bufferIn.getBuffer(this.model.renderType(PASSIONFRUIT_SEED_TEXTURE));
		this.model.renderToBuffer(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
		matrixStackIn.popPose();
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
	}

	public ResourceLocation getTextureLocation(PassionfruitSeedEntity entity) {
		return PASSIONFRUIT_SEED_TEXTURE;
	}
}
