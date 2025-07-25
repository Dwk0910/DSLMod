package org.dslofficial.dslmod;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.dslofficial.dslmod.blocks.ModBlocks;
import org.dslofficial.dslmod.items.Items;
import org.dslofficial.dslmod.network.ServerPacket;
import org.dslofficial.dslmod.sound.ModSounds;

@Mod("dslmod")
public class DSLMod {
    public static final Logger logger = LogManager.getLogger();
    public static final String MODID = "dslmod";
    public static final String VERSION = "0.0.1";
    public DSLMod() {
        logger.info("DSLMod Loaded");
        logger.info("Made by Dwk0910. Copyright 2025. DSL All rights reserved.");

        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        Items.register(eventBus);
        ModBlocks.register(eventBus);
        ModSounds.register(eventBus);
        KeyBindings.register();
        ServerPacket.register();
    }
}
