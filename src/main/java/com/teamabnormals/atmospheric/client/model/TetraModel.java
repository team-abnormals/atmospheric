package com.teamabnormals.atmospheric.client.model;

import com.teamabnormals.atmospheric.common.entity.Tetra;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.util.Mth;

public class TetraModel<T extends Tetra> extends HierarchicalModel<T> {
	private final ModelPart root;
	private final ModelPart tail;

	public TetraModel(ModelPart root) {
		super(RenderType::entityTranslucent);
		this.root = root;
		this.tail = root.getChild("tail");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition mesh = new MeshDefinition();
		PartDefinition part = mesh.getRoot();

		PartDefinition body = part.addOrReplaceChild("body", CubeListBuilder.create().texOffs(2, 1).addBox(-0.5F, -3.0F, -3.0F, 1.0F, 2.0F, 5.0F), PartPose.offset(0.0F, 24.0F, 0.0F));
		body.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(9, 12).addBox(-0.5F, -3.0F, -4.0F, 1.0F, 1.0F, 1.0F), PartPose.offset(0.0F, 0.0F, 0.0F));
		part.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(3, 10).addBox(0.0F, -1.0F, 0.0F, 0.0F, 2.0F, 2.0F), PartPose.offset(0.0F, 22.0F, 2.0F));
		part.addOrReplaceChild("right_fin", CubeListBuilder.create().texOffs(6, 9).addBox(-1.5F, -1.0F, 1.0F, 1.0F, 0.0F, 2.0F), PartPose.offset(0.0F, 24.0F, 0.0F));
		part.addOrReplaceChild("left_fin", CubeListBuilder.create().texOffs(9, 9).addBox(0.5F, -1.0F, 1.0F, 1.0F, 0.0F, 2.0F), PartPose.offset(0.0F, 24.0F, 0.0F));
		part.addOrReplaceChild("top_fin", CubeListBuilder.create().texOffs(3, 7).addBox(0.0F, -4.0F, -1.0F, 0.0F, 1.0F, 2.0F), PartPose.offset(0.0F, 24.0F, 0.0F));

		return LayerDefinition.create(mesh, 16, 16);
	}

	@Override
	public ModelPart root() {
		return this.root;
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		float f = 1.0F;
		if (!entity.isInWater()) {
			f = 1.5F;
		}

		this.tail.yRot = -f * 0.45F * Mth.sin(0.6F * ageInTicks);
	}
}