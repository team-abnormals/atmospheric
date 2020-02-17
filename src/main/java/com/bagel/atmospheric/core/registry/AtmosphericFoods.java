package com.bagel.atmospheric.core.registry;

import com.bagel.atmospheric.common.potion.GoldenSpittingEffect;
import com.bagel.atmospheric.common.potion.SpittingEffect;
import com.bagel.atmospheric.core.Atmospheric;

import net.minecraft.item.Food;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Atmospheric.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class AtmosphericFoods {

	public static Effect SPITTING = new SpittingEffect().setRegistryName("spitting");
    public static Effect GOLDEN_SPITTING = new GoldenSpittingEffect().setRegistryName("golden_spitting");
	
	public static Food PASSIONFRUIT = new Food.Builder().hunger(3).saturation(0.3F).setAlwaysEdible().effect(new EffectInstance(SPITTING, 140, 0, true, false), 1.0F).build();
	public static Food SHIMMERING_PASSIONFRUIT = new Food.Builder().hunger(5).saturation(0.2F).setAlwaysEdible().effect(new EffectInstance(GOLDEN_SPITTING, 160, 0, true, false), 1.0F).build();
	public static Food PASSIONFRUIT_TART = new Food.Builder().hunger(4).saturation(0.6F).build();
	public static Food PASSIONFRUIT_SORBET = new Food.Builder().hunger(15).saturation(0.5F).effect(new EffectInstance(Effects.SLOWNESS, 300, 4, false, false), 1.0F).build();
	
	public static Food YUCCA_FRUIT = new Food.Builder().hunger(3).saturation(0.6F).effect(new EffectInstance(Effects.POISON, 145, 0), 1.0F).build();
	public static Food ROASTED_YUCCA_FRUIT = new Food.Builder().hunger(6).saturation(0.6F).build();
}
