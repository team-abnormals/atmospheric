package com.bagel.rosewood.core;

import com.bagel.rosewood.client.renderer.RosewoodBoatRenderer;
import com.bagel.rosewood.common.entity.RosewoodBoatEntity;
import com.bagel.rosewood.core.registry.RosewoodBlocks;
import com.bagel.rosewood.core.registry.RosewoodCompostables;
import com.bagel.rosewood.core.registry.RosewoodEntities;
import com.bagel.rosewood.core.registry.RosewoodItems;
import com.google.common.collect.Maps;

import net.minecraft.block.Block;
import net.minecraft.item.AxeItem;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("rosewood")
public class Rosewood
{
    //private static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "rosewood";

    public Rosewood() {
    	IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
        
        RosewoodBlocks.BLOCKS.register(modEventBus);
        RosewoodItems.ITEMS.register(modEventBus);
        RosewoodEntities.ENTITY_TYPES.register(modEventBus);
        
        MinecraftForge.EVENT_BUS.register(this);
    }
    
    private void setup(final FMLCommonSetupEvent event)
	{
    	RosewoodCompostables.registerCompostables();
		registerStrippable(RosewoodBlocks.ROSEWOOD_LOG.get(), RosewoodBlocks.STRIPPED_ROSEWOOD_LOG.get());
        registerStrippable(RosewoodBlocks.ROSEWOOD.get(), RosewoodBlocks.STRIPPED_ROSEWOOD.get());
    }
    
    private void clientSetup(final FMLClientSetupEvent event) 
    {
    	RenderingRegistry.registerEntityRenderingHandler(RosewoodBoatEntity.class, RosewoodBoatRenderer::new);
    }

    public static void registerStrippable(Block log, Block stripped_log) {
        AxeItem.BLOCK_STRIPPING_MAP = Maps.newHashMap(AxeItem.BLOCK_STRIPPING_MAP);
        AxeItem.BLOCK_STRIPPING_MAP.put(log, stripped_log);
    }
}
