package org.dslofficial.dslmod.blocks;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

import net.minecraftforge.eventbus.api.IEventBus;

import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import org.dslofficial.dslmod.DSLMod;
import org.dslofficial.dslmod.Tabs;

import java.util.function.Supplier;

public class ModBlocks {
    private static final DeferredRegister<Block> blocks = DeferredRegister.create(ForgeRegistries.BLOCKS, DSLMod.MODID);
    private static final DeferredRegister<Item> items = DeferredRegister.create(ForgeRegistries.ITEMS, DSLMod.MODID);

    public static void register(IEventBus eventBus) {
        blocks.register(eventBus);
        items.register(eventBus);
    }

    public static <T extends Block> RegistryObject<Block> registerWithItem(String name, Supplier<T> supplier, Item.Properties properties) {
        RegistryObject<Block> blockRegistryObject = blocks.register(name, supplier);
        items.register(name, () -> new BlockItem(blockRegistryObject.get(), properties));
        return blockRegistryObject;
    }

    // SnowooByeongshin
    public static final RegistryObject<Block> snowoo_byeongshin = registerWithItem("snowoo_byeongshin", () -> new SnowooByeongshinBlockHorizontal(
            BlockBehaviour.Properties.of(Material.STONE)
    ), new Item.Properties().tab(Tabs.DSLTAB_GENERAL));

    // BewareOfDog
    public static final RegistryObject<Block> beware_of_dog = registerWithItem("beware_of_dog", () -> new BewareOfDogBlock(
            BlockBehaviour.Properties.of(Material.WOOD)
                    .sound(SoundType.WOOD)
                    .lightLevel(state -> 5)),
            new Item.Properties().tab(Tabs.DSLTAB_GENERAL)
    );

    // Gold Emeralds
    public static final RegistryObject<Block> gold_emerald_block = registerWithItem("gold_emerald_block", () -> new GoldEmeralds.GoldEmeraldBlock(
            BlockBehaviour.Properties.of(Material.METAL)
                    .sound(SoundType.METAL)),
            new Item.Properties().tab(Tabs.DSLTAB_BLOCKS));
    public static final RegistryObject<Block> gold_emerald_ore = registerWithItem("gold_emerald_ore", () -> new GoldEmeralds.GoldEmeraldOre(
            BlockBehaviour.Properties.of(Material.STONE)
                    .requiresCorrectToolForDrops()),
            new Item.Properties().tab(Tabs.DSLTAB_BLOCKS));

    // Caffeine Block
    public static final RegistryObject<Block> caffeine_block = registerWithItem("caffeine_block", () -> new CaffeineBlockHorizontal(
            BlockBehaviour.Properties.of(Material.STONE)),
            new Item.Properties().tab(Tabs.DSLTAB_BLOCKS)
    );
}
