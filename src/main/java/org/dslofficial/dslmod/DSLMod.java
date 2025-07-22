package org.dslofficial.dslmod;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import org.dslofficial.dslmod.blocks.BewareOfDog;
import org.dslofficial.dslmod.blocks.SnowooByeongshin;
import org.dslofficial.dslmod.items.Wannagohome;

@Mod("dslmod")
public class DSLMod {
    public static final String MODID = "dslmod";
    public DSLMod() {
        System.out.println("*** DSLMOD LOADED ***");

        // Item register
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        Wannagohome.register(eventBus);

        // Block register
        SnowooByeongshin.register(eventBus);
        BewareOfDog.register(eventBus);
    }
}
