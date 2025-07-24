package org.dslofficial.dslmod;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import org.dslofficial.dslmod.blocks.ModBlocks;
import org.dslofficial.dslmod.items.Items;

import org.jetbrains.annotations.NotNull;

public class Tabs {
    public static final CreativeModeTab DSLTAB_GENERAL = new CreativeModeTab(DSLMod.MODID + " - 일반") {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(ModBlocks.snowoo_byeongshin.get().asItem());
        }
    };

    public static final CreativeModeTab DSLTAB_BLOCKS = new CreativeModeTab(DSLMod.MODID + " - 블록") {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(ModBlocks.caffeine_block.get().asItem());
        }
    };

    public static final CreativeModeTab DSLTAB_FOODS = new CreativeModeTab(DSLMod.MODID + "- 음식") {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(Items.foodIcon.get().asItem());
        }
    };
}
