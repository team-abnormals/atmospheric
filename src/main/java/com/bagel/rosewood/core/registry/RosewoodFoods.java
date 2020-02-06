package com.bagel.rosewood.core.registry;

import com.bagel.rosewood.core.Rosewood;

import net.minecraft.item.Food;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Rosewood.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RosewoodFoods {
	public static Food PASSIONFRUIT = new Food.Builder().hunger(2).saturation(0.3F).build();
	public static Food SHIMMERING_PASSIONFRUIT = new Food.Builder().hunger(5).saturation(1.2F).build();
	public static Food PASSIONFRUIT_TART = new Food.Builder().hunger(3).saturation(0.6F).build();
	public static Food PASSIONFRUIT_SORBET = new Food.Builder().hunger(15).saturation(0.5F).effect(new EffectInstance(Effects.SLOWNESS, 300, 4, false, false), 1.0F).build();
}
