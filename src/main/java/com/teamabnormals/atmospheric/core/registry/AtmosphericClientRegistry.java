package com.teamabnormals.atmospheric.core.registry;

import com.teamabnormals.atmospheric.client.model.TetraBucketModel;
import com.teamabnormals.atmospheric.core.Atmospheric;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@OnlyIn(Dist.CLIENT)
@EventBusSubscriber(modid = Atmospheric.MOD_ID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class AtmosphericClientRegistry {

	@SubscribeEvent
	public static void onEvent(ModelEvent.RegisterAdditional event) {
		for (ResourceLocation location : Minecraft.getInstance().getResourceManager().listResources("models/item/tetra_bucket", s -> s.getPath().endsWith(".json")).keySet())
			event.register(new ResourceLocation(location.getNamespace(), location.getPath().substring("models/".length(), location.getPath().length() - ".json".length())));
	}

	@SubscribeEvent
	public static void onEvent(ModelEvent.ModifyBakingResult event) {
		event.getModels().put(new ModelResourceLocation(AtmosphericItems.TETRA_BUCKET.getId(), "inventory"), new TetraBucketModel(event.getModels()));
	}
}