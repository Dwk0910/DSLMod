package org.dslofficial.dslmod.items;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Item;

import net.minecraftforge.eventbus.api.IEventBus;

import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import org.dslofficial.dslmod.DSLMod;
import org.dslofficial.dslmod.Tabs;

import java.util.List;
import java.util.function.Supplier;

public class Items {
    private static final DeferredRegister<Item> items = DeferredRegister.create(ForgeRegistries.ITEMS, DSLMod.MODID);

    public static RegistryObject<Item> foodIcon;

    public static void register(IEventBus eventBus) {
        items.register(eventBus);

        // 단순 블록 등록은 이곳에서 처리
        registerItem("wannagohome", () -> new Item(new Item.Properties().tab(Tabs.DSLTAB_GENERAL)));
        registerItem("gold_emerald", () -> new Item(new Item.Properties().tab(Tabs.DSLTAB_GENERAL)));
        registerItem("raw_gold_emerald", () -> new Item(new Item.Properties().tab(Tabs.DSLTAB_GENERAL)));

        // foods
        foodIcon = registerItem("coke", () -> new DrinkItem(
                new Item.Properties().tab(Tabs.DSLTAB_FOODS),
                List.of(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 200, 2, false, false, true)),
                2, 0.2f));
    }

    public static <T extends Item> RegistryObject<T> registerItem(String name, Supplier<T> supplier) {
        return items.register(name, supplier);
    }
}
