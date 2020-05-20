package com.bagel.atmospheric.core.registry;

import com.bagel.atmospheric.core.Atmospheric;

import net.minecraft.item.Food;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Atmospheric.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
@SuppressWarnings("deprecation")
public class AtmosphericFoods {
	
	public static final Food PASSIONFRUIT = new Food.Builder().hunger(3).saturation(0.3F).setAlwaysEdible().effect(new EffectInstance(AtmosphericEffects.SPITTING, 140, 0, true, false), 1.0F).build();
	public static final Food SHIMMERING_PASSIONFRUIT = new Food.Builder().hunger(5).saturation(0.2F).setAlwaysEdible().effect(new EffectInstance(AtmosphericEffects.SPITTING, 140, 1, true, false), 1.0F).build();
	public static final Food PASSIONFRUIT_TART = new Food.Builder().hunger(4).saturation(0.6F).build();
	public static final Food PASSIONFRUIT_SORBET = new Food.Builder().hunger(15).saturation(0.5F).effect(new EffectInstance(Effects.SLOWNESS, 300, 4, false, false), 1.0F).build();
	
	public static final Food YUCCA_FRUIT = new Food.Builder().hunger(1).saturation(0.3F).build();
	public static final Food ROASTED_YUCCA_FRUIT = new Food.Builder().hunger(6).saturation(0.6F).build();
	public static final Food YUCCA_JUICE = new Food.Builder().hunger(3).saturation(0.6F).effect(new EffectInstance(Effects.SLOWNESS, 65, 2), 1.0F).build();
	
	public static final Food ALOE_LEAVES = new Food.Builder().hunger(2).saturation(0.1F).effect(new EffectInstance(Effects.INSTANT_DAMAGE, 1, 0, false, false), 0.2F).build();
	public static final Food ALOE_GEL = new Food.Builder().hunger(3).saturation(0.2F).setAlwaysEdible().build();
}
