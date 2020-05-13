package com.bagel.atmospheric.core.registry;

import com.bagel.atmospheric.core.Atmospheric;
import com.teamabnormals.abnormals_core.common.advancement.EmptyTrigger;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = Atmospheric.MODID)
public class AtmosphericCriteriaTriggers {
	public static final EmptyTrigger SPIT_PASSIONFRUIT = CriteriaTriggers.register(new EmptyTrigger(prefix("spit_passionfruit")));
	
	private static ResourceLocation prefix(String name) {
		return new ResourceLocation(Atmospheric.MODID, name);
	}
}
