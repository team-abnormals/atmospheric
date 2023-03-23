package com.teamabnormals.atmospheric.core.registry;

import com.teamabnormals.atmospheric.common.entity.projectile.PassionFruitSeed;
import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.blueprint.core.util.registry.EntitySubRegistryHelper;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.RegistryObject;

@EventBusSubscriber(modid = Atmospheric.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class AtmosphericEntityTypes {
	public static final EntitySubRegistryHelper HELPER = Atmospheric.REGISTRY_HELPER.getEntitySubHelper();

	public static final RegistryObject<EntityType<PassionFruitSeed>> PASSION_FRUIT_SEED = HELPER.createEntity("passion_fruit_seed", PassionFruitSeed::new, PassionFruitSeed::new, MobCategory.MISC, 0.25F, 0.25F);
}
