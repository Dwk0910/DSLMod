package org.dslofficial.dslmod.items;

import net.minecraft.world.item.Item;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import org.dslofficial.dslmod.DSLMod;
import org.dslofficial.dslmod.Tabs;

@Mod.EventBusSubscriber(modid = DSLMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Wannagohome {
    public static final DeferredRegister<Item> item = DeferredRegister.create(ForgeRegistries.ITEMS, DSLMod.MODID);
    public static void register(IEventBus eventBus) {
        item.register(eventBus);
        item.register("wannagohome", () -> new Item(new Item.Properties().tab(Tabs.DSLTAB)));
    }
}
