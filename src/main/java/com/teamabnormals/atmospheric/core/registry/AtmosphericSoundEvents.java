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

	public static final RegistryObject<SoundEvent> ARID_GLASS_BREAK = HELPER.createSoundEvent("block.arid_glass.break");
	public static final RegistryObject<SoundEvent> ARID_GLASS_FALL = HELPER.createSoundEvent("block.arid_glass.fall");
	public static final RegistryObject<SoundEvent> ARID_GLASS_HIT = HELPER.createSoundEvent("block.arid_glass.hit");
	public static final RegistryObject<SoundEvent> ARID_GLASS_PLACE = HELPER.createSoundEvent("block.arid_glass.place");
	public static final RegistryObject<SoundEvent> ARID_GLASS_STEP = HELPER.createSoundEvent("block.arid_glass.step");

	public static final RegistryObject<SoundEvent> ARID_SPROUTS_BREAK = HELPER.createSoundEvent("block.arid_sprouts.break");
	public static final RegistryObject<SoundEvent> ARID_SPROUTS_FALL = HELPER.createSoundEvent("block.arid_sprouts.fall");
	public static final RegistryObject<SoundEvent> ARID_SPROUTS_HIT = HELPER.createSoundEvent("block.arid_sprouts.hit");
	public static final RegistryObject<SoundEvent> ARID_SPROUTS_PLACE = HELPER.createSoundEvent("block.arid_sprouts.place");
	public static final RegistryObject<SoundEvent> ARID_SPROUTS_STEP = HELPER.createSoundEvent("block.arid_sprouts.step");

	public static final RegistryObject<SoundEvent> CARMINE_BREAK = HELPER.createSoundEvent("block.carmine.break");
	public static final RegistryObject<SoundEvent> CARMINE_FALL = HELPER.createSoundEvent("block.carmine.fall");
	public static final RegistryObject<SoundEvent> CARMINE_HIT = HELPER.createSoundEvent("block.carmine.hit");
	public static final RegistryObject<SoundEvent> CARMINE_PLACE = HELPER.createSoundEvent("block.carmine.place");
	public static final RegistryObject<SoundEvent> CARMINE_STEP = HELPER.createSoundEvent("block.carmine.step");

	public static final RegistryObject<SoundEvent> MONKEY_BRUSH_BREAK = HELPER.createSoundEvent("block.monkey_brush.break");
	public static final RegistryObject<SoundEvent> MONKEY_BRUSH_FALL = HELPER.createSoundEvent("block.monkey_brush.fall");
	public static final RegistryObject<SoundEvent> MONKEY_BRUSH_HIT = HELPER.createSoundEvent("block.monkey_brush.hit");
	public static final RegistryObject<SoundEvent> MONKEY_BRUSH_PLACE = HELPER.createSoundEvent("block.monkey_brush.place");
	public static final RegistryObject<SoundEvent> MONKEY_BRUSH_STEP = HELPER.createSoundEvent("block.monkey_brush.step");

	public static final RegistryObject<SoundEvent> COCHINEAL_DEATH = HELPER.createSoundEvent("entity.cochineal.death");
	public static final RegistryObject<SoundEvent> COCHINEAL_HURT = HELPER.createSoundEvent("entity.cochineal.hurt");
	public static final RegistryObject<SoundEvent> COCHINEAL_SUCKLE = HELPER.createSoundEvent("entity.cochineal.suckle");
	public static final RegistryObject<SoundEvent> COCHINEAL_BOUNCE = HELPER.createSoundEvent("entity.cochineal.bounce");

	public static final RegistryObject<SoundEvent> PASSION_FRUIT_SEED_SPIT = HELPER.createSoundEvent("entity.passion_fruit_seed.spit");

	public static class AtmosphericSoundTypes {
		public static final ForgeSoundType ARID_SAND = new ForgeSoundType(1.0F, 1.0F, ARID_SAND_BREAK, ARID_SAND_STEP, ARID_SAND_PLACE, ARID_SAND_HIT, ARID_SAND_FALL);
		public static final ForgeSoundType ARID_SANDSTONE = new ForgeSoundType(1.0F, 1.0F, ARID_SANDSTONE_BREAK, ARID_SANDSTONE_STEP, ARID_SANDSTONE_PLACE, ARID_SANDSTONE_HIT, ARID_SANDSTONE_FALL);
		public static final ForgeSoundType SUSPICIOUS_ARID_SAND = new ForgeSoundType(1.0F, 1.0F, SUSPICIOUS_ARID_SAND_BREAK, SUSPICIOUS_ARID_SAND_STEP, SUSPICIOUS_ARID_SAND_PLACE, SUSPICIOUS_ARID_SAND_HIT, SUSPICIOUS_ARID_SAND_FALL);
		public static final ForgeSoundType ARID_GLASS = new ForgeSoundType(1.0F, 1.0F, ARID_GLASS_BREAK, ARID_GLASS_STEP, ARID_GLASS_PLACE, ARID_GLASS_HIT, ARID_GLASS_FALL);
		public static final ForgeSoundType ARID_SPROUTS = new ForgeSoundType(1.0F, 1.0F, ARID_SPROUTS_BREAK, ARID_SPROUTS_STEP, ARID_SPROUTS_PLACE, ARID_SPROUTS_HIT, ARID_SPROUTS_FALL);
		public static final ForgeSoundType CARMINE = new ForgeSoundType(1.0F, 1.0F, CARMINE_BREAK, CARMINE_STEP, CARMINE_PLACE, CARMINE_HIT, CARMINE_FALL);
		public static final ForgeSoundType MONKEY_BRUSH = new ForgeSoundType(1.0F, 1.0F, MONKEY_BRUSH_BREAK, MONKEY_BRUSH_STEP, MONKEY_BRUSH_PLACE, MONKEY_BRUSH_HIT, MONKEY_BRUSH_FALL);
	}
}