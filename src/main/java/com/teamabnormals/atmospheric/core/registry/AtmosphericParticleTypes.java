package com.teamabnormals.atmospheric.core.registry;

import com.teamabnormals.atmospheric.client.particle.AloeBlossomParticle;
import com.teamabnormals.atmospheric.client.particle.CochinealTrailParticle;
import com.teamabnormals.atmospheric.client.particle.MoradoBlossomParticle;
import com.teamabnormals.atmospheric.client.particle.OrangeVaporParticle;
import com.teamabnormals.atmospheric.core.Atmospheric;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@EventBusSubscriber(modid = Atmospheric.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class AtmosphericParticleTypes {
	public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, Atmospheric.MOD_ID);

	public static final RegistryObject<SimpleParticleType> ALOE_BLOSSOM = register("aloe_blossom", true);
	public static final RegistryObject<SimpleParticleType> MORADO_BLOSSOM = register("morado_blossom", false);
	public static final RegistryObject<SimpleParticleType> ORANGE_VAPOR = register("orange_vapor", true);
	public static final RegistryObject<SimpleParticleType> BLOOD_ORANGE_VAPOR = register("blood_orange_vapor", true);
	public static final RegistryObject<SimpleParticleType> COCHINEAL_TRAIL = register("cochineal_trail", false);
	public static final RegistryObject<SimpleParticleType> COLD_COCHINEAL_TRAIL = register("cold_cochineal_trail", false);

	private static RegistryObject<SimpleParticleType> register(String name, boolean alwaysShow) {
		return PARTICLES.register(name, () -> new SimpleParticleType(alwaysShow));
	}

	@SubscribeEvent
	public static void registerParticleTypes(RegisterParticleProvidersEvent event) {
		event.register(ALOE_BLOSSOM.get(), AloeBlossomParticle.Factory::new);
		event.register(MORADO_BLOSSOM.get(), MoradoBlossomParticle.Factory::new);
		event.register(ORANGE_VAPOR.get(), OrangeVaporParticle.Provider::new);
		event.register(BLOOD_ORANGE_VAPOR.get(), OrangeVaporParticle.Provider::new);
		event.register(COCHINEAL_TRAIL.get(), CochinealTrailParticle.Provider::new);
		event.register(COLD_COCHINEAL_TRAIL.get(), CochinealTrailParticle.Provider::new);
	}
}
