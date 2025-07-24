package org.dslofficial.dslmod.objects;

import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;

import org.jetbrains.annotations.NotNull;

public class HorizontalDirectionalBlock extends Block {
    private static final DirectionProperty facing = BlockStateProperties.HORIZONTAL_FACING;

    public static class Opposite extends HorizontalDirectionalBlock {
        public Opposite(Properties properties) {
            super(properties);
        }

        @Override
        public @NotNull BlockState getStateForPlacement(@NotNull BlockPlaceContext pContext) {
            return this.getStateDefinition().any()
                    .setValue(facing, pContext.getHorizontalDirection().getOpposite());
        }
    }

    public HorizontalDirectionalBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(facing, Direction.NORTH));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(facing);
    }

    @Override
    public @NotNull BlockState getStateForPlacement(@NotNull BlockPlaceContext pContext) {
        return this.defaultBlockState()
                .setValue(facing, pContext.getHorizontalDirection());
    }
}
