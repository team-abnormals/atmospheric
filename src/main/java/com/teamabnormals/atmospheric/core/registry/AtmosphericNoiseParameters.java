package com.teamabnormals.atmospheric.core.registry;

import com.teamabnormals.atmospheric.core.Atmospheric;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class AtmosphericNoiseParameters {
	public static final DeferredRegister<NormalNoise.NoiseParameters> NOISE_PARAMETERS = DeferredRegister.create(Registry.NOISE_REGISTRY, Atmospheric.MOD_ID);

	public static final RegistryObject<NormalNoise.NoiseParameters> FLOOR_RAISE_RADIUS_OFFSET = NOISE_PARAMETERS.register("floor_raise_radius_offset", () -> new NormalNoise.NoiseParameters(-4, 1.0D, 1.0D));
}
