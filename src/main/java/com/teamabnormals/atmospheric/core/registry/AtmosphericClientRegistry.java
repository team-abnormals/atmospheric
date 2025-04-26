package com.teamabnormals.atmospheric.core.registry;

import com.teamabnormals.atmospheric.client.model.CochinealModel;
import com.teamabnormals.atmospheric.client.model.DragonFruitModel;
import com.teamabnormals.atmospheric.client.model.PassionFruitSeedModel;
import com.teamabnormals.atmospheric.client.model.TetraModel;
import com.teamabnormals.atmospheric.client.renderer.entity.CochinealRenderer;
import com.teamabnormals.atmospheric.client.renderer.entity.DragonFruitRenderer;
import com.teamabnormals.atmospheric.client.renderer.entity.PassionFruitSeedRenderer;
import com.teamabnormals.atmospheric.client.renderer.entity.TetraRenderer;
import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.atmospheric.core.other.AtmosphericModelLayers;
import com.teamabnormals.atmospheric.core.registry.datapack.AtmosphericTetraVariants;
import com.teamabnormals.blueprint.client.model.DynamicItemModel;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.renderer.entity.NoopRenderer;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.ModelEvent;

@OnlyIn(Dist.CLIENT)
@EventBusSubscriber(modid = Atmospheric.MOD_ID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class AtmosphericClientRegistry {

	@SubscribeEvent
	public static void registerAdditional(ModelEvent.RegisterAdditional event) {
		DynamicItemModel.register(event, "tetra_bucket");
	}

	@SubscribeEvent
	public static void modifyBakingResult(ModelEvent.ModifyBakingResult event) {
		DynamicItemModel.bake(event, AtmosphericItems.TETRA_BUCKET.getId(), "tetra_bucket", ModelResourceLocation.standalone(AtmosphericTetraVariants.NEON.location().withPrefix("item/tetra_bucket/")), DynamicItemModel.fishBucket());
	}

	@SubscribeEvent
	public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(AtmosphericModelLayers.TETRA, TetraModel::createBodyLayer);
		event.registerLayerDefinition(AtmosphericModelLayers.COCHINEAL, () -> CochinealModel.createBodyLayer(CubeDeformation.NONE));
		event.registerLayerDefinition(AtmosphericModelLayers.COCHINEAL_SADDLE, () -> CochinealModel.createBodyLayer(new CubeDeformation(0.5F)));
		event.registerLayerDefinition(AtmosphericModelLayers.PASSION_FRUIT_SEED, PassionFruitSeedModel::createBodyLayer);
		event.registerLayerDefinition(AtmosphericModelLayers.DRAGON_FRUIT, DragonFruitModel::createBodyLayer);
	}

	@SubscribeEvent
	public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(AtmosphericEntityTypes.TETRA.get(), TetraRenderer::new);
		event.registerEntityRenderer(AtmosphericEntityTypes.COCHINEAL.get(), CochinealRenderer::new);
		event.registerEntityRenderer(AtmosphericEntityTypes.PASSION_FRUIT_SEED.get(), PassionFruitSeedRenderer::new);
		event.registerEntityRenderer(AtmosphericEntityTypes.DRAGON_FRUIT.get(), DragonFruitRenderer::new);
		event.registerEntityRenderer(AtmosphericEntityTypes.ORANGE_VAPOR_CLOUD.get(), NoopRenderer::new);
	}
}