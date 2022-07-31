package com.teamabnormals.atmospheric.core.registry;

import com.teamabnormals.atmospheric.common.entity.projectile.PassionfruitSeed;
import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.blueprint.core.util.registry.EntitySubRegistryHelper;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.RegistryObject;

@EventBusSubscriber(modid = Atmospheric.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class AtmosphericEntityTypes {
	public static final EntitySubRegistryHelper HELPER = Atmospheric.REGISTRY_HELPER.getEntitySubHelper();

	public static final RegistryObject<EntityType<PassionfruitSeed>> PASSIONFRUIT_SEED = HELPER.createEntity("passionfruit_seed", PassionfruitSeed::new, PassionfruitSeed::new, MobCategory.MISC, 0.25F, 0.25F);
}
