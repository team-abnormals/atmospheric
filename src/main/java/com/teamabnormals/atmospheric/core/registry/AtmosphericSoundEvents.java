package com.teamabnormals.atmospheric.core.registry;

import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.blueprint.core.util.registry.SoundSubRegistryHelper;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.RegistryObject;

@EventBusSubscriber(modid = Atmospheric.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class AtmosphericSoundEvents {
	public static final SoundSubRegistryHelper HELPER = Atmospheric.REGISTRY_HELPER.getSoundSubHelper();

	public static final RegistryObject<SoundEvent> COCHINEAL_DEATH = HELPER.createSoundEvent("entity.cochineal.death");
	public static final RegistryObject<SoundEvent> COCHINEAL_HURT = HELPER.createSoundEvent("entity.cochineal.hurt");
	public static final RegistryObject<SoundEvent> COCHINEAL_SUCKLE = HELPER.createSoundEvent("entity.cochineal.suckle");
//	public static final RegistryObject<SoundEvent> COCHINEAL_GRAB = HELPER.createSoundEvent("entity.cochineal.grab");
//	public static final RegistryObject<SoundEvent> COCHINEAL_BOUNCE = HELPER.createSoundEvent("entity.cochineal.bounce");

	public static final RegistryObject<SoundEvent> PASSION_FRUIT_SEED_SPIT = HELPER.createSoundEvent("entity.passion_fruit_seed.spit");
}