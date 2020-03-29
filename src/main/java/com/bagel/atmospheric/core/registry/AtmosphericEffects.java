package com.bagel.atmospheric.core.registry;

import com.bagel.atmospheric.common.potion.ReliefEffect;
import com.bagel.atmospheric.common.potion.SpittingEffect;
import com.bagel.atmospheric.common.potion.WorseningEffect;
import com.bagel.atmospheric.core.Atmospheric;

import net.minecraft.item.Items;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionBrewing;
import net.minecraft.potion.Potions;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class AtmosphericEffects {
	public static final DeferredRegister<Effect> EFFECTS = new DeferredRegister<>(ForgeRegistries.POTIONS, Atmospheric.MODID);
	public static final DeferredRegister<Potion> POTIONS = new DeferredRegister<>(ForgeRegistries.POTION_TYPES, Atmospheric.MODID);
	
	public static Effect RELIEF = new ReliefEffect().setRegistryName("relief");
	public static Effect WORSENING = new WorseningEffect().setRegistryName("worsening");
    public static Effect SPITTING = new SpittingEffect().setRegistryName("spitting");
    
    public static final RegistryObject<Potion> RELIEF_NORMAL  = POTIONS.register("relief", () -> new Potion(new EffectInstance(RELIEF, 3600)));
	public static final RegistryObject<Potion> RELIEF_STRONG  = POTIONS.register("relief_strong", () -> new Potion(new EffectInstance(RELIEF, 1800, 1)));
	public static final RegistryObject<Potion> RELIEF_LONG    = POTIONS.register("relief_long", () -> new Potion(new EffectInstance(RELIEF, 9600)));
	public static final RegistryObject<Potion> WORSENING_NORMAL = POTIONS.register("worsening", () -> new Potion(new EffectInstance(WORSENING, 3600)));
	public static final RegistryObject<Potion> WORSENING_STRONG = POTIONS.register("worsening_strong", () -> new Potion(new EffectInstance(WORSENING, 1800, 1)));
	public static final RegistryObject<Potion> WORSENING_LONG   = POTIONS.register("worsening_long", () -> new Potion(new EffectInstance(WORSENING, 9600)));
    
    public static void registerBrewingRecipes() {
		PotionBrewing.addMix(Potions.AWKWARD, AtmosphericItems.SHIMMERING_PASSIONFRUIT.get(), RELIEF_NORMAL.get());
		PotionBrewing.addMix(RELIEF_NORMAL.get(), Items.GLOWSTONE_DUST, RELIEF_STRONG.get());
		PotionBrewing.addMix(RELIEF_NORMAL.get(), Items.REDSTONE, RELIEF_LONG.get());
		PotionBrewing.addMix(RELIEF_NORMAL.get(), Items.FERMENTED_SPIDER_EYE, WORSENING_NORMAL.get());
		PotionBrewing.addMix(WORSENING_NORMAL.get(), Items.GLOWSTONE_DUST, WORSENING_STRONG.get());
		PotionBrewing.addMix(WORSENING_NORMAL.get(), Items.REDSTONE, WORSENING_LONG.get());
	}
}
