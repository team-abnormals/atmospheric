package com.minecraftabnormals.atmospheric.core.registry;

import com.minecraftabnormals.atmospheric.client.particle.AloeBlossomParticle;
import com.minecraftabnormals.atmospheric.client.particle.MoradoBlossomParticle;
import com.minecraftabnormals.atmospheric.core.Atmospheric;
import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.particles.ParticleType;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class AtmosphericParticleTypes {
	public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, Atmospheric.MOD_ID);

	public static final RegistryObject<SimpleParticleType> ALOE_BLOSSOM = register("aloe_blossom", true);
	public static final RegistryObject<SimpleParticleType> MORADO_BLOSSOM = register("morado_blossom", false);

	private static RegistryObject<SimpleParticleType> register(String name, boolean alwaysShow) {
		return PARTICLES.register(name, () -> new SimpleParticleType(alwaysShow));
	}

	@EventBusSubscriber(modid = Atmospheric.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
	public static class RegisterParticleFactories {

		@SubscribeEvent
		public static void registerParticleTypes(ParticleFactoryRegisterEvent event) {
			Minecraft.getInstance().particleEngine.register(ALOE_BLOSSOM.get(), AloeBlossomParticle.Factory::new);
			Minecraft.getInstance().particleEngine.register(MORADO_BLOSSOM.get(), MoradoBlossomParticle.Factory::new);
		}
	}
}
