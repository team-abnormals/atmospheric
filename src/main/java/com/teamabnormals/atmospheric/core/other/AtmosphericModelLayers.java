package com.teamabnormals.atmospheric.core.other;

import com.teamabnormals.atmospheric.client.model.CochinealModel;
import com.teamabnormals.atmospheric.client.model.DragonFruitModel;
import com.teamabnormals.atmospheric.client.model.PassionFruitSeedModel;
import com.teamabnormals.atmospheric.client.model.TetraModel;
import com.teamabnormals.atmospheric.client.renderer.entity.CochinealRenderer;
import com.teamabnormals.atmospheric.client.renderer.entity.DragonFruitRenderer;
import com.teamabnormals.atmospheric.client.renderer.entity.PassionFruitSeedRenderer;
import com.teamabnormals.atmospheric.client.renderer.entity.TetraRenderer;
import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.atmospheric.core.registry.AtmosphericEntityTypes;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.renderer.entity.NoopRenderer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(modid = Atmospheric.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class AtmosphericModelLayers {
	public static final ModelLayerLocation TETRA = register("tetra");
	public static final ModelLayerLocation COCHINEAL = register("cochineal");
	public static final ModelLayerLocation COCHINEAL_SADDLE = register("cochineal", "saddle");
	public static final ModelLayerLocation DRAGON_FRUIT = register("dragon_fruit");
	public static final ModelLayerLocation PASSION_FRUIT_SEED = register("passion_fruit_seed");

	@SubscribeEvent
	public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(TETRA, TetraModel::createBodyLayer);
		event.registerLayerDefinition(COCHINEAL, () -> CochinealModel.createBodyLayer(CubeDeformation.NONE));
		event.registerLayerDefinition(COCHINEAL_SADDLE, () -> CochinealModel.createBodyLayer(new CubeDeformation(0.5F)));
		event.registerLayerDefinition(PASSION_FRUIT_SEED, PassionFruitSeedModel::createBodyLayer);
		event.registerLayerDefinition(DRAGON_FRUIT, DragonFruitModel::createBodyLayer);
	}

	@SubscribeEvent
	public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(AtmosphericEntityTypes.TETRA.get(), TetraRenderer::new);
		event.registerEntityRenderer(AtmosphericEntityTypes.COCHINEAL.get(), CochinealRenderer::new);
		event.registerEntityRenderer(AtmosphericEntityTypes.PASSION_FRUIT_SEED.get(), PassionFruitSeedRenderer::new);
		event.registerEntityRenderer(AtmosphericEntityTypes.PASSION_VINE_COIL.get(), ThrownItemRenderer::new);
		event.registerEntityRenderer(AtmosphericEntityTypes.DRAGON_FRUIT.get(), DragonFruitRenderer::new);
		event.registerEntityRenderer(AtmosphericEntityTypes.ORANGE_VAPOR_CLOUD.get(), NoopRenderer::new);
	}

	public static ModelLayerLocation register(String name) {
		return register(name, "main");
	}

	public static ModelLayerLocation register(String name, String layer) {
		return new ModelLayerLocation(Atmospheric.location(name), layer);
	}
}
