package com.teamabnormals.atmospheric.core.registry;

import com.teamabnormals.atmospheric.common.entity.Cochineal;
import com.teamabnormals.atmospheric.common.entity.OrangeVaporCloud;
import com.teamabnormals.atmospheric.common.entity.projectile.DragonFruit;
import com.teamabnormals.atmospheric.common.entity.projectile.PassionFruitSeed;
import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.blueprint.core.util.registry.EntitySubRegistryHelper;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent.Operation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.RegistryObject;

@EventBusSubscriber(modid = Atmospheric.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class AtmosphericEntityTypes {
	public static final EntitySubRegistryHelper HELPER = Atmospheric.REGISTRY_HELPER.getEntitySubHelper();

	public static final RegistryObject<EntityType<Cochineal>> COCHINEAL = HELPER.createEntity("cochineal", Cochineal::new, Cochineal::new, MobCategory.CREATURE, 1.3F, 1.1F);
	public static final RegistryObject<EntityType<PassionFruitSeed>> PASSION_FRUIT_SEED = HELPER.createEntity("passion_fruit_seed", PassionFruitSeed::new, PassionFruitSeed::new, MobCategory.MISC, 0.25F, 0.25F);
	public static final RegistryObject<EntityType<DragonFruit>> DRAGON_FRUIT = HELPER.createEntity("dragon_fruit", DragonFruit::new, DragonFruit::new, MobCategory.MISC, 0.375F, 0.375F);
	public static final RegistryObject<EntityType<OrangeVaporCloud>> ORANGE_VAPOR_CLOUD = HELPER.getDeferredRegister().register("orange_vapor_cloud", () -> EntityType.Builder.<OrangeVaporCloud>of(OrangeVaporCloud::new, MobCategory.MISC).fireImmune().sized(3.0F, 3.0F).clientTrackingRange(10).updateInterval(Integer.MAX_VALUE).build("atmospheric:orange_vapor_cloud"));

	@SubscribeEvent
	public static void registerAttributes(EntityAttributeCreationEvent event) {
		event.put(COCHINEAL.get(), Cochineal.createAttributes().build());
	}

	@SubscribeEvent
	public static void registerSpawnPlacements(SpawnPlacementRegisterEvent event) {
		event.register(COCHINEAL.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules, Operation.OR);
	}
}
