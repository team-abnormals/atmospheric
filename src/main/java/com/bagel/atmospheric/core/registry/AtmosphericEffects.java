package com.bagel.atmospheric.core.registry;

import com.bagel.atmospheric.common.effects.ReliefEffect;
import com.bagel.atmospheric.common.effects.WorseningEffect;
import com.bagel.atmospheric.core.AtmosphericExpansion;

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
	public static final DeferredRegister<Effect> EFFECTS = new DeferredRegister<>(ForgeRegistries.POTIONS, AtmosphericExpansion.MODID);
	public static final DeferredRegister<Potion> POTIONS = new DeferredRegister<>(ForgeRegistries.POTION_TYPES, AtmosphericExpansion.MODID);
	
    public static RegistryObject<Effect> RELIEF = EFFECTS.register("relief", () -> new ReliefEffect());
    public static RegistryObject<Effect> WORSENING = EFFECTS.register("worsening", () -> new WorseningEffect());
    
    public static final RegistryObject<Potion> RELIEF_NORMAL  = POTIONS.register("relief", () -> new Potion(new EffectInstance(RELIEF.get(), 3600)));
	public static final RegistryObject<Potion> RELIEF_STRONG  = POTIONS.register("relief_strong", () -> new Potion(new EffectInstance(RELIEF.get(), 1800, 1)));
	public static final RegistryObject<Potion> RELIEF_LONG    = POTIONS.register("relief_long", () -> new Potion(new EffectInstance(RELIEF.get(), 9600)));
	public static final RegistryObject<Potion> WORSENING_NORMAL = POTIONS.register("worsening", () -> new Potion(new EffectInstance(WORSENING.get(), 3600)));
	public static final RegistryObject<Potion> WORSENING_STRONG = POTIONS.register("worsening_strong", () -> new Potion(new EffectInstance(WORSENING.get(), 1800, 1)));
	public static final RegistryObject<Potion> WORSENING_LONG   = POTIONS.register("worsening_long", () -> new Potion(new EffectInstance(WORSENING.get(), 9600)));
    
    public static void registerBrewingRecipes() {
		PotionBrewing.addMix(Potions.AWKWARD, AtmosphericItems.SHIMMERING_PASSIONFRUIT.get(), RELIEF_NORMAL.get());
		PotionBrewing.addMix(RELIEF_NORMAL.get(), Items.GLOWSTONE_DUST, RELIEF_STRONG.get());
		PotionBrewing.addMix(RELIEF_NORMAL.get(), Items.REDSTONE, RELIEF_LONG.get());
		PotionBrewing.addMix(RELIEF_NORMAL.get(), Items.FERMENTED_SPIDER_EYE, WORSENING_NORMAL.get());
		PotionBrewing.addMix(WORSENING_NORMAL.get(), Items.GLOWSTONE_DUST, WORSENING_STRONG.get());
		PotionBrewing.addMix(WORSENING_NORMAL.get(), Items.REDSTONE, WORSENING_LONG.get());
	}
}
