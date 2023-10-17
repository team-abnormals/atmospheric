package com.teamabnormals.atmospheric.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import com.teamabnormals.atmospheric.client.model.DragonFruitModel;
import com.teamabnormals.atmospheric.common.entity.projectile.DragonFruit;
import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.atmospheric.core.other.AtmosphericModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DragonFruitRenderer extends EntityRenderer<DragonFruit> {
	private static final ResourceLocation FRUIT_LOCATION = new ResourceLocation(Atmospheric.MOD_ID, "textures/block/dragon_fruit.png");
	private static final ResourceLocation FLOWERING_FRUIT_LOCATION = new ResourceLocation(Atmospheric.MOD_ID, "textures/block/flowering_dragon_fruit.png");

	private final DragonFruitModel<DragonFruit> model;

	public DragonFruitRenderer(EntityRendererProvider.Context context) {
		super(context);
		this.model = new DragonFruitModel<>(context.bakeLayer(AtmosphericModelLayers.DRAGON_FRUIT));
	}

	@Override
	public void render(DragonFruit entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
		poseStack.pushPose();
		poseStack.mulPose(Vector3f.YP.rotationDegrees(180.0F - entity.getYRot()));
		this.model.setupAnim(entity, partialTicks, 0.0F, entity.tickCount + partialTicks, 0.0F, 0.0F);
		poseStack.popPose();

		VertexConsumer vertexConsumer = buffer.getBuffer(this.model.renderType(this.getTextureLocation(entity)));
		this.model.renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
		super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
	}

	public ResourceLocation getTextureLocation(DragonFruit fruit) {
		return FRUIT_LOCATION;
	}
}