package com.bagel.rosewood.core.registry;

import java.util.function.BiFunction;

import com.bagel.rosewood.common.entity.RosewoodBoatEntity;
import com.bagel.rosewood.core.Rosewood;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class RosewoodEntities
{
	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = new DeferredRegister<>(ForgeRegistries.ENTITIES, Rosewood.MODID);
	
	public static final RegistryObject<EntityType<RosewoodBoatEntity>> BOAT = ENTITY_TYPES.register("boat", () -> createEntity(RosewoodBoatEntity::new, RosewoodBoatEntity::new, EntityClassification.MISC, "boat", 1.375F, 0.5625F));
	
    private static <T extends Entity> EntityType<T> createEntity(EntityType.IFactory<T> factory, BiFunction<FMLPlayMessages.SpawnEntity, World, T> clientFactory, EntityClassification entityClassification, String name, float width, float height) {
		ResourceLocation location = new ResourceLocation(Rosewood.MODID, name);
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
    
    
}