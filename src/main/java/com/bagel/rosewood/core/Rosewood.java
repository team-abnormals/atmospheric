package com.bagel.rosewood.core;

import com.bagel.rosewood.client.renderer.RosewoodBoatRenderer;
import com.bagel.rosewood.common.blocks.PassionVineBundleDispenseBehavior;
import com.bagel.rosewood.common.blocks.PassionVineDispenseBehavior;
import com.bagel.rosewood.common.entity.RosewoodBoatEntity;
import com.bagel.rosewood.core.registry.RosewoodBiomes;
import com.bagel.rosewood.core.registry.RosewoodBlockData;
import com.bagel.rosewood.core.registry.RosewoodBlocks;
import com.bagel.rosewood.core.registry.RosewoodEffects;
import com.bagel.rosewood.core.registry.RosewoodEntities;
import com.bagel.rosewood.core.registry.RosewoodFoods;
import com.bagel.rosewood.core.registry.RosewoodItems;
import com.bagel.rosewood.core.util.ColorUtils;

import net.minecraft.block.DispenserBlock;
import net.minecraft.potion.Effect;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("rosewood")
@Mod.EventBusSubscriber(modid = "rosewood", bus = Mod.EventBusSubscriber.Bus.MOD)
public class Rosewood
{
    //private static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "rosewood";

    public Rosewood() {
    	IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        
        RosewoodBlocks.BLOCKS.register(modEventBus);
        RosewoodItems.ITEMS.register(modEventBus);
    	RosewoodBiomes.BIOMES.register(modEventBus);
        RosewoodEntities.ENTITY_TYPES.register(modEventBus);
        RosewoodEffects.EFFECTS.register(modEventBus);
        RosewoodEffects.POTIONS.register(modEventBus);
        
        MinecraftForge.EVENT_BUS.register(this);
        
        modEventBus.addListener(this::setup);
        DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
        	modEventBus.addListener(this::clientSetup);
        });
    }
    
    private void setup(final FMLCommonSetupEvent event)
	{    	
        RosewoodBiomes.registerBiomesToDictionary();
    	RosewoodBlockData.registerCompostables();
    	RosewoodBlockData.registerFlammables();
    	RosewoodBlockData.registerStrippables();
    	RosewoodEffects.registerBrewingRecipes();
    	DispenserBlock.registerDispenseBehavior(RosewoodBlocks.PASSION_VINE_BUNDLE.get().asItem(), new PassionVineBundleDispenseBehavior());
    	DispenserBlock.registerDispenseBehavior(RosewoodBlocks.PASSION_VINE.get().asItem(), new PassionVineDispenseBehavior());
	}
    
    private void clientSetup(final FMLClientSetupEvent event) 
    {
    	ColorUtils.registerBlockColors();
    	RenderingRegistry.registerEntityRenderingHandler(RosewoodBoatEntity.class, RosewoodBoatRenderer::new);
    }
    
    @SubscribeEvent
	public static void onRegisterEffects(RegistryEvent.Register<Effect> event) {
    	event.getRegistry().registerAll(RosewoodFoods.SPITTING, RosewoodFoods.GOLDEN_SPITTING);
    }
}
