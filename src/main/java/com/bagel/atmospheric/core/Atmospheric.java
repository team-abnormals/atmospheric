package com.bagel.atmospheric.core;

import com.bagel.atmospheric.client.renderer.RosewoodBoatRenderer;
import com.bagel.atmospheric.common.blocks.PassionVineBundleDispenseBehavior;
import com.bagel.atmospheric.common.blocks.PassionVineDispenseBehavior;
import com.bagel.atmospheric.common.entity.RosewoodBoatEntity;
import com.bagel.atmospheric.core.registry.AtmosphericBiomes;
import com.bagel.atmospheric.core.registry.AtmosphericBlockData;
import com.bagel.atmospheric.core.registry.AtmosphericBlocks;
import com.bagel.atmospheric.core.registry.AtmosphericEffects;
import com.bagel.atmospheric.core.registry.AtmosphericEntities;
import com.bagel.atmospheric.core.registry.AtmosphericFoods;
import com.bagel.atmospheric.core.registry.AtmosphericItems;
import com.bagel.atmospheric.core.util.ColorUtils;

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

@Mod("atmospheric")
@Mod.EventBusSubscriber(modid = "atmospheric", bus = Mod.EventBusSubscriber.Bus.MOD)
public class Atmospheric
{
    //private static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "atmospheric";

    public Atmospheric() {
    	IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        
        AtmosphericBlocks.BLOCKS.register(modEventBus);
        AtmosphericItems.ITEMS.register(modEventBus);
    	AtmosphericBiomes.BIOMES.register(modEventBus);
        AtmosphericEntities.ENTITY_TYPES.register(modEventBus);
        AtmosphericEffects.EFFECTS.register(modEventBus);
        AtmosphericEffects.POTIONS.register(modEventBus);
        
        MinecraftForge.EVENT_BUS.register(this);
        
        modEventBus.addListener(this::setup);
        DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
        	modEventBus.addListener(this::clientSetup);
        });
    }
    
    private void setup(final FMLCommonSetupEvent event)
	{    	
        AtmosphericBiomes.registerBiomesToDictionary();
    	AtmosphericBlockData.registerCompostables();
    	AtmosphericBlockData.registerFlammables();
    	AtmosphericBlockData.registerStrippables();
    	AtmosphericEffects.registerBrewingRecipes();
    	DispenserBlock.registerDispenseBehavior(AtmosphericBlocks.PASSION_VINE_BUNDLE.get().asItem(), new PassionVineBundleDispenseBehavior());
    	DispenserBlock.registerDispenseBehavior(AtmosphericBlocks.PASSION_VINE.get().asItem(), new PassionVineDispenseBehavior());
	}
    
    private void clientSetup(final FMLClientSetupEvent event) 
    {
    	ColorUtils.registerBlockColors();
    	RenderingRegistry.registerEntityRenderingHandler(RosewoodBoatEntity.class, RosewoodBoatRenderer::new);
    }
    
    @SubscribeEvent
	public static void onRegisterEffects(RegistryEvent.Register<Effect> event) {
    	event.getRegistry().registerAll(AtmosphericFoods.SPITTING, AtmosphericFoods.GOLDEN_SPITTING);
    }
}
