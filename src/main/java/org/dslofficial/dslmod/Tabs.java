package org.dslofficial.dslmod;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import org.dslofficial.dslmod.blocks.SnowooByeongshin;

import javax.annotation.Nonnull;

public class Tabs {
    public static final CreativeModeTab DSLTAB = new CreativeModeTab(DSLMod.MODID) {
        @Override
        @Nonnull
        public ItemStack makeIcon() {
            return new ItemStack(SnowooByeongshin.SNOWOO_BYEONGSHIN_ITEM.get());
        }
    };
}
