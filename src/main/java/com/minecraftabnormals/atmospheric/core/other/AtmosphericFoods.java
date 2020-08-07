package com.minecraftabnormals.atmospheric.core.other;

import com.minecraftabnormals.atmospheric.core.Atmospheric;
import com.minecraftabnormals.atmospheric.core.registry.AtmosphericEffects;

import net.minecraft.item.Food;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Atmospheric.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class AtmosphericFoods {
	
	public static final Food PASSIONFRUIT = new Food.Builder().hunger(3).saturation(0.3F).setAlwaysEdible().effect(() -> new EffectInstance(AtmosphericEffects.SPITTING.get(), 140, 0, false, false, true), 1.0F).build();
	public static final Food SHIMMERING_PASSIONFRUIT = new Food.Builder().hunger(5).saturation(0.2F).setAlwaysEdible().effect(() -> new EffectInstance(AtmosphericEffects.SPITTING.get(), 140, 1, false, false, true), 1.0F).build();
	public static final Food PASSIONFRUIT_TART = new Food.Builder().hunger(4).saturation(0.6F).build();
	public static final Food PASSIONFRUIT_SORBET = new Food.Builder().hunger(15).saturation(0.5F).effect(() -> new EffectInstance(Effects.SLOWNESS, 300, 4, false, false, true), 1.0F).build();
	
	public static final Food YUCCA_FRUIT = new Food.Builder().hunger(1).saturation(0.3F).build();
	public static final Food ROASTED_YUCCA_FRUIT = new Food.Builder().hunger(6).saturation(0.5F).effect(() -> new EffectInstance(AtmosphericEffects.PERSISTENCE.get(), 560, 0, false, false, true), 1.0F).build();
	public static final Food YUCCA_JUICE = new Food.Builder().hunger(3).saturation(0.7F).effect(() -> new EffectInstance(AtmosphericEffects.PERSISTENCE.get(), 1080, 0, false, false, true), 1.0F).build();
	
	public static final Food ALOE_LEAVES = new Food.Builder().hunger(2).saturation(0.1F).setAlwaysEdible().effect(() -> new EffectInstance(AtmosphericEffects.GELLED.get(), 280, 0, false, false, true), 1.0F).build();
	public static final Food ALOE_GEL = new Food.Builder().hunger(3).saturation(0.2F).setAlwaysEdible().effect(() -> new EffectInstance(AtmosphericEffects.GELLED.get(), 2800, 0, false, false, true), 1.0F).build();
}
