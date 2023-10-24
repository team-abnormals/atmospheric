package com.teamabnormals.atmospheric.core.other;

import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.blueprint.common.advancement.EmptyTrigger;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = Atmospheric.MOD_ID)
public class AtmosphericCriteriaTriggers {
	public static final EmptyTrigger FINISH_GATEAU = CriteriaTriggers.register(new EmptyTrigger(Atmospheric.location("finish_gateau")));

	public static final EmptyTrigger BARREL_CACTUS_PRICK = CriteriaTriggers.register(new EmptyTrigger(Atmospheric.location("barrel_cactus_prick")));
	public static final EmptyTrigger ALOE_VERA_PRICK = CriteriaTriggers.register(new EmptyTrigger(Atmospheric.location("aloe_vera_prick")));
	public static final EmptyTrigger YUCCA_PRICK = CriteriaTriggers.register(new EmptyTrigger(Atmospheric.location("yucca_prick")));
}
