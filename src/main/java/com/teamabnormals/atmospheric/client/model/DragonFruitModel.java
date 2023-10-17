package com.teamabnormals.atmospheric.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.teamabnormals.atmospheric.common.entity.projectile.DragonFruit;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class DragonFruitModel<T extends DragonFruit> extends EntityModel<T> {
	private final ModelPart fruit;

	public DragonFruitModel(ModelPart root) {
		this.fruit = root.getChild("fruit");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		partdefinition.addOrReplaceChild("fruit", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -3.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.0F, 0.0F, (float) (Math.PI), 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 16, 16);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		fruit.yRot = 0;
		fruit.xRot = 0;
		fruit.zRot = 0;
		float yRot = entity.getYRot();
		float amount = ((float) Math.PI + ageInTicks / 3);
		if (yRot < 0) {
			amount *= -1;
		}

		if (Math.abs(yRot) == 90.0F) {
			fruit.zRot = amount;
		} else {
			fruit.xRot = amount;
		}
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		fruit.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}