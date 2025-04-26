package com.teamabnormals.atmospheric.core.registry;

import com.teamabnormals.atmospheric.client.particle.AloeBlossomParticle;
import com.teamabnormals.atmospheric.client.particle.CochinealTrailParticle;
import com.teamabnormals.atmospheric.client.particle.MoradoBlossomParticle;
import com.teamabnormals.atmospheric.client.particle.OrangeVaporParticle;
import com.teamabnormals.atmospheric.core.Atmospheric;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

@EventBusSubscriber(modid = Atmospheric.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class AtmosphericParticleTypes {
	public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(Registries.PARTICLE_TYPE, Atmospheric.MOD_ID);

	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> ALOE_BLOSSOM = register("aloe_blossom", true);
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> MORADO_BLOSSOM = register("morado_blossom", false);
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> ORANGE_VAPOR = register("orange_vapor", true);
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> BLOOD_ORANGE_VAPOR = register("blood_orange_vapor", true);
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> COCHINEAL_TRAIL = register("cochineal_trail", false);
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> COLD_COCHINEAL_TRAIL = register("cold_cochineal_trail", false);

	private static DeferredHolder<ParticleType<?>, SimpleParticleType> register(String name, boolean alwaysShow) {
		return PARTICLES.register(name, () -> new SimpleParticleType(alwaysShow));
	}

	@SubscribeEvent
	public static void registerParticleTypes(RegisterParticleProvidersEvent event) {
		event.registerSpriteSet(ALOE_BLOSSOM.get(), AloeBlossomParticle.Factory::new);
		event.registerSpriteSet(MORADO_BLOSSOM.get(), MoradoBlossomParticle.Factory::new);
		event.registerSpriteSet(ORANGE_VAPOR.get(), OrangeVaporParticle.Provider::new);
		event.registerSpriteSet(BLOOD_ORANGE_VAPOR.get(), OrangeVaporParticle.Provider::new);
		event.registerSpriteSet(COCHINEAL_TRAIL.get(), CochinealTrailParticle.Provider::new);
		event.registerSpriteSet(COLD_COCHINEAL_TRAIL.get(), CochinealTrailParticle.Provider::new);
	}
}
