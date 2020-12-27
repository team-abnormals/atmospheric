package com.minecraftabnormals.atmospheric.core.registry;

import com.minecraftabnormals.abnormals_core.core.util.registry.EntitySubRegistryHelper;
import com.minecraftabnormals.atmospheric.common.entity.PassionfruitSeedEntity;
import com.minecraftabnormals.atmospheric.core.Atmospheric;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = Atmospheric.MODID, bus = EventBusSubscriber.Bus.MOD)
public class AtmosphericEntities {
    public static final EntitySubRegistryHelper HELPER = Atmospheric.REGISTRY_HELPER.getEntitySubHelper();

	public static final RegistryObject<EntityType<PassionfruitSeedEntity>> PASSIONFRUIT_SEED = HELPER.createEntity("passionfruit_seed", PassionfruitSeedEntity::new, PassionfruitSeedEntity::new, EntityClassification.MISC, 0.25F, 0.25F);
}
