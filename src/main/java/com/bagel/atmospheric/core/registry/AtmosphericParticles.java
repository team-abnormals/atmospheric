package com.bagel.atmospheric.core.registry;

import com.bagel.atmospheric.common.client.particle.AloeBlossomParticle;
import com.bagel.atmospheric.core.Atmospheric;

import net.minecraft.client.Minecraft;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class AtmosphericParticles {
	public static final DeferredRegister<ParticleType<?>> PARTICLES = new DeferredRegister<>(ForgeRegistries.PARTICLE_TYPES, Atmospheric.MODID);
	
	public static final RegistryObject<BasicParticleType> ALOE_BLOSSOM = createBasicParticleType(true, "aloe_blossom");
	
	private static RegistryObject<BasicParticleType> createBasicParticleType(boolean alwaysShow, String name) {
		RegistryObject<BasicParticleType> particleType = PARTICLES.register(name, () -> new BasicParticleType(alwaysShow));
		return particleType;
	}
	
	@EventBusSubscriber(modid = Atmospheric.MODID, bus = EventBusSubscriber.Bus.MOD)
	public static class RegisterParticleFactories {
		
		@SubscribeEvent(priority = EventPriority.LOWEST)
		public static void registerParticleTypes(ParticleFactoryRegisterEvent event) {
			if(checkForNonNullWithReflectionCauseForgeIsBaby(ALOE_BLOSSOM)) {
				Minecraft.getInstance().particles.registerFactory(ALOE_BLOSSOM.get(), AloeBlossomParticle.Factory::new);
			}
		}
		
	}
	
	private static boolean checkForNonNullWithReflectionCauseForgeIsBaby(RegistryObject<BasicParticleType> registryObject) {
		return ObfuscationReflectionHelper.getPrivateValue(RegistryObject.class, registryObject, "value") != null;
	}
}
