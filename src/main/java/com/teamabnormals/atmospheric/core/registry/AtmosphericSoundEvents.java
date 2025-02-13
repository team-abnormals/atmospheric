package com.teamabnormals.atmospheric.core.registry;

import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.blueprint.core.util.registry.SoundSubRegistryHelper;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.common.util.ForgeSoundType;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.RegistryObject;

@EventBusSubscriber(modid = Atmospheric.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class AtmosphericSoundEvents {
	public static final SoundSubRegistryHelper HELPER = Atmospheric.REGISTRY_HELPER.getSoundSubHelper();

	public static final RegistryObject<SoundEvent> ARID_SAND_BREAK = HELPER.createSoundEvent("block.arid_sand.break");
	public static final RegistryObject<SoundEvent> ARID_SAND_FALL = HELPER.createSoundEvent("block.arid_sand.fall");
	public static final RegistryObject<SoundEvent> ARID_SAND_HIT = HELPER.createSoundEvent("block.arid_sand.hit");
	public static final RegistryObject<SoundEvent> ARID_SAND_PLACE = HELPER.createSoundEvent("block.arid_sand.place");
	public static final RegistryObject<SoundEvent> ARID_SAND_STEP = HELPER.createSoundEvent("block.arid_sand.step");

	public static final RegistryObject<SoundEvent> ARID_SANDSTONE_BREAK = HELPER.createSoundEvent("block.arid_sandstone.break");
	public static final RegistryObject<SoundEvent> ARID_SANDSTONE_FALL = HELPER.createSoundEvent("block.arid_sandstone.fall");
	public static final RegistryObject<SoundEvent> ARID_SANDSTONE_HIT = HELPER.createSoundEvent("block.arid_sandstone.hit");
	public static final RegistryObject<SoundEvent> ARID_SANDSTONE_PLACE = HELPER.createSoundEvent("block.arid_sandstone.place");
	public static final RegistryObject<SoundEvent> ARID_SANDSTONE_STEP = HELPER.createSoundEvent("block.arid_sandstone.step");

	public static final RegistryObject<SoundEvent> SUSPICIOUS_ARID_SAND_BREAK = HELPER.createSoundEvent("block.suspicious_arid_sand.break");
	public static final RegistryObject<SoundEvent> SUSPICIOUS_ARID_SAND_FALL = HELPER.createSoundEvent("block.suspicious_arid_sand.fall");
	public static final RegistryObject<SoundEvent> SUSPICIOUS_ARID_SAND_HIT = HELPER.createSoundEvent("block.suspicious_arid_sand.hit");
	public static final RegistryObject<SoundEvent> SUSPICIOUS_ARID_SAND_PLACE = HELPER.createSoundEvent("block.suspicious_arid_sand.place");
	public static final RegistryObject<SoundEvent> SUSPICIOUS_ARID_SAND_STEP = HELPER.createSoundEvent("block.suspicious_arid_sand.step");

	public static final RegistryObject<SoundEvent> COCHINEAL_DEATH = HELPER.createSoundEvent("entity.cochineal.death");
	public static final RegistryObject<SoundEvent> COCHINEAL_HURT = HELPER.createSoundEvent("entity.cochineal.hurt");
	public static final RegistryObject<SoundEvent> COCHINEAL_SUCKLE = HELPER.createSoundEvent("entity.cochineal.suckle");
//	public static final RegistryObject<SoundEvent> COCHINEAL_GRAB = HELPER.createSoundEvent("entity.cochineal.grab");
//	public static final RegistryObject<SoundEvent> COCHINEAL_BOUNCE = HELPER.createSoundEvent("entity.cochineal.bounce");

	public static final RegistryObject<SoundEvent> PASSION_FRUIT_SEED_SPIT = HELPER.createSoundEvent("entity.passion_fruit_seed.spit");

	public static class AtmosphericSoundTypes {
		public static final ForgeSoundType ARID_SAND = new ForgeSoundType(1.0F, 1.0F, ARID_SAND_BREAK, ARID_SAND_STEP, ARID_SAND_PLACE, ARID_SAND_HIT, ARID_SAND_FALL);
		public static final ForgeSoundType ARID_SANDSTONE = new ForgeSoundType(1.0F, 1.0F, ARID_SANDSTONE_BREAK, ARID_SANDSTONE_STEP, ARID_SANDSTONE_PLACE, ARID_SANDSTONE_HIT, ARID_SANDSTONE_FALL);
		public static final ForgeSoundType SUSPICIOUS_ARID_SAND = new ForgeSoundType(1.0F, 1.0F, SUSPICIOUS_ARID_SAND_BREAK, SUSPICIOUS_ARID_SAND_STEP, SUSPICIOUS_ARID_SAND_PLACE, SUSPICIOUS_ARID_SAND_HIT, SUSPICIOUS_ARID_SAND_FALL);
	}
}