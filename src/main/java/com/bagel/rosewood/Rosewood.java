package com.bagel.rosewood;

import net.minecraft.block.Block;
import net.minecraft.item.AxeItem;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.collect.Maps;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("rosewood")
public class Rosewood
{
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "rosewood";

    public Rosewood() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        MinecraftForge.EVENT_BUS.register(this);
    }
    
    private void setup(final FMLCommonSetupEvent event)
	{
		registerStrippable(RosewoodBlocks.ROSEWOOD_LOG, RosewoodBlocks.STRIPPED_ROSEWOOD_LOG);
        registerStrippable(RosewoodBlocks.ROSEWOOD, RosewoodBlocks.STRIPPED_ROSEWOOD);
    }

    public static void registerStrippable(Block log, Block stripped_log) {
        AxeItem.BLOCK_STRIPPING_MAP = Maps.newHashMap(AxeItem.BLOCK_STRIPPING_MAP);
        AxeItem.BLOCK_STRIPPING_MAP.put(log, stripped_log);
    }
}
