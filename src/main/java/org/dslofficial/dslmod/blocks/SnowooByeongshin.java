package org.dslofficial.dslmod.blocks;

import net.minecraft.core.Direction;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.Material;

import net.minecraftforge.eventbus.api.IEventBus;

import net.minecraftforge.fml.common.Mod;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import org.dslofficial.dslmod.DSLMod;
import org.dslofficial.dslmod.Tabs;

@Mod.EventBusSubscriber(modid = DSLMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SnowooByeongshin {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, DSLMod.MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, DSLMod.MODID);

    public static final RegistryObject<Block> SNOWOO_BYEONGSHIN = BLOCKS.register("snowoo_byeongshin", () -> new SnowooByeongshinBlock(BlockBehaviour.Properties.of(Material.STONE).strength(1F)));
    public static final RegistryObject<Item> SNOWOO_BYEONGSHIN_ITEM = ITEMS.register("snowoo_byeongshin", () -> new BlockItem(SNOWOO_BYEONGSHIN.get(), new Item.Properties().tab(Tabs.DSLTAB)));
    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
        ITEMS.register(eventBus);
    }
}

class SnowooByeongshinBlock extends Block {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    public SnowooByeongshinBlock(Block.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
        );
    }

    @Override
    protected void createBlockStateDefinition(@NotNull StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(@NotNull BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection());
    }
}
