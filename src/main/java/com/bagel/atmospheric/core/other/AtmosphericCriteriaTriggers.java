package com.bagel.atmospheric.core.other;

import com.bagel.atmospheric.core.Atmospheric;
import com.teamabnormals.abnormals_core.common.advancement.EmptyTrigger;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = Atmospheric.MODID)
public class AtmosphericCriteriaTriggers {
	public static final EmptyTrigger SPIT_PASSIONFRUIT = CriteriaTriggers.register(new EmptyTrigger(prefix("spit_passionfruit")));
	
	public static final EmptyTrigger FINISH_GATEAU = CriteriaTriggers.register(new EmptyTrigger(prefix("finish_gateau")));
	public static final EmptyTrigger PUT_OUT_FIRE = CriteriaTriggers.register(new EmptyTrigger(prefix("put_out_fire")));
	
	public static final EmptyTrigger BARREL_CACTUS_PRICK = CriteriaTriggers.register(new EmptyTrigger(prefix("barrel_cactus_prick")));
	public static final EmptyTrigger ALOE_VERA_PRICK = CriteriaTriggers.register(new EmptyTrigger(prefix("aloe_vera_prick")));
	public static final EmptyTrigger YUCCA_FLOWER_PRICK = CriteriaTriggers.register(new EmptyTrigger(prefix("yucca_flower_prick")));
	public static final EmptyTrigger YUCCA_BRANCH_PRICK = CriteriaTriggers.register(new EmptyTrigger(prefix("yucca_branch_prick")));
	public static final EmptyTrigger YUCCA_LEAVES_PRICK = CriteriaTriggers.register(new EmptyTrigger(prefix("yucca_leaves_prick")));
	
	private static ResourceLocation prefix(String name) {
		return new ResourceLocation(Atmospheric.MODID, name);
	}
}
