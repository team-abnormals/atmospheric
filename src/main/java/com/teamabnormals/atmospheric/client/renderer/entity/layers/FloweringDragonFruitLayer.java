package com.teamabnormals.atmospheric.client.renderer.entity.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.teamabnormals.atmospheric.client.model.DragonFruitModel;
import com.teamabnormals.atmospheric.common.entity.projectile.DragonFruit;
import com.teamabnormals.atmospheric.core.Atmospheric;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FloweringDragonFruitLayer<T extends DragonFruit> extends RenderLayer<T, DragonFruitModel<T>> {
	private static final RenderType FLOWERING_DRAGON_FRUIT = RenderType.entityTranslucentEmissive(new ResourceLocation(Atmospheric.MOD_ID, "textures/block/flowering_dragon_fruit_emissive.png"));

	public FloweringDragonFruitLayer(RenderLayerParent<T, DragonFruitModel<T>> parent) {
		super(parent);
	}

	public void render(PoseStack stack, MultiBufferSource buffer, int p_116985_, T fruit, float p_116987_, float p_116988_, float p_116989_, float p_116990_, float p_116991_, float p_116992_) {
		if (fruit.isFlowering()) {
			this.getParentModel().renderToBuffer(stack, buffer.getBuffer(FLOWERING_DRAGON_FRUIT), 15728640, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
		}
	}
}