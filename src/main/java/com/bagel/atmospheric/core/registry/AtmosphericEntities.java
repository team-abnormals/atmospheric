package com.bagel.atmospheric.core.registry;

import java.util.function.BiFunction;

import com.bagel.atmospheric.client.renderer.AtmosphericBoatRenderer;
import com.bagel.atmospheric.common.entity.AtmosphericBoatEntity;
import com.bagel.atmospheric.core.Atmospheric;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class AtmosphericEntities
{
	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = new DeferredRegister<>(ForgeRegistries.ENTITIES, Atmospheric.MODID);
	
	public static final RegistryObject<EntityType<AtmosphericBoatEntity>> BOAT = ENTITY_TYPES.register("boat", () -> createEntity(AtmosphericBoatEntity::new, null, EntityClassification.MISC, "boat", 1.375F, 0.5625F));
	
    private static <T extends Entity> EntityType<T> createEntity(EntityType.IFactory<T> factory, BiFunction<FMLPlayMessages.SpawnEntity, World, T> clientFactory, EntityClassification entityClassification, String name, float width, float height) {
		ResourceLocation location = new ResourceLocation(Atmospheric.MODID, name);
		EntityType<T> entity = EntityType.Builder.create(factory, entityClassification)
			.size(width, height)
			.setTrackingRange(64)
			.setShouldReceiveVelocityUpdates(true)
			.setUpdateInterval(3)
			.setCustomClientFactory(clientFactory)
			.build(location.toString()
		);
		return entity;
	}
    
    @OnlyIn(Dist.CLIENT)
    public static void registerRendering()
    {
        RenderingRegistry.registerEntityRenderingHandler((EntityType<? extends AtmosphericBoatEntity>)BOAT.get(), AtmosphericBoatRenderer::new);
    }
    
}