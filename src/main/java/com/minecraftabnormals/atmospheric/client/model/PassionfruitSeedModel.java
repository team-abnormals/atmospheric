package com.minecraftabnormals.atmospheric.client.model;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PassionfruitSeedModel<T extends Entity> extends SegmentedModel<T> {
	private final ModelRenderer main = new ModelRenderer(this);

	public PassionfruitSeedModel() {
		this(0.0F);
	}

	public PassionfruitSeedModel(float size) {
		this.main.texOffs(0, 0).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, size);
		this.main.setPos(0.0F, 0.0F, 0.0F);
	}

	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}

	public Iterable<ModelRenderer> parts() {
		return ImmutableList.of(this.main);
	}
}