package org.dslofficial.dslmod.blocks;

import net.minecraft.client.Minecraft;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;

import net.minecraft.util.StringRepresentable;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;

import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Material;

import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.eventbus.api.IEventBus;

import net.minecraftforge.fml.common.Mod;

import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import org.dslofficial.dslmod.DSLMod;
import org.dslofficial.dslmod.Tabs;

import org.dslofficial.dslmod.screens.BewareOfDogScreen;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = DSLMod.MODID)
public class BewareOfDog {
    public static final DeferredRegister<Block> blockRegister = DeferredRegister.create(ForgeRegistries.BLOCKS, DSLMod.MODID);
    public static final DeferredRegister<Item> itemRegister = DeferredRegister.create(ForgeRegistries.ITEMS, DSLMod.MODID);
    public static final RegistryObject<Block> blockRegistry = blockRegister.register("beware_of_dog", () -> new BewareOfDogBlock(BlockBehaviour.Properties.of(Material.WOOD).noOcclusion()));
    public static final RegistryObject<Item> itemRegistry = itemRegister.register("beware_of_dog", () -> new BlockItem(blockRegistry.get(), new Item.Properties().tab(Tabs.DSLTAB)));

    public static void register(IEventBus eventBus) {
        blockRegister.register(eventBus);
        itemRegister.register(eventBus);
    }
}

class BewareOfDogBlock extends Block {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final EnumProperty<Part> PART = EnumProperty.create("part", Part.class);
    public static final EnumProperty<Color> COLOR = EnumProperty.create("color", Color.class);

    // ** Initialize the block **

    public BewareOfDogBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(PART, Part.LEFT)
                .setValue(COLOR, Color.WHITE)
        );
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, PART, COLOR);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        if (context.getClickedFace().getName().equals("up")) {
            Direction facing = context.getHorizontalDirection();
            Level level = context.getLevel();
            BlockPos pos = context.getClickedPos();
            BlockState rightBlock = level.getBlockState(pos.relative(facing.getClockWise()));

            // 오른쪽 자리 확인 -> 없으면 null return;
            if (!rightBlock.canBeReplaced(context)) return null;
            return this.defaultBlockState().setValue(FACING, facing);
        } else {
            // 땅 위에 설치한 것이 아님
            return null;
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public @NotNull VoxelShape getShape(BlockState pState, @NotNull BlockGetter pLevel, @NotNull BlockPos pPos, @NotNull CollisionContext pContext) {
        Direction direction = pState.getValue(FACING);

        // 왼쪽부분
        if (pState.getValue(PART).equals(Part.LEFT)) {
            // shape가 north 고정이므로 바라보는 방향으로 회전시켜야 함
            return rotateShape(direction, Shapes.or(
                    // 표지판 몸통
                    Block.box(0, 6, 6, 16, 16, 10),
                    Block.box(0, 16, 6, 16, 23, 10),

                    //표지판 다리(윗부분)
                    Block.box(1, 2, 7, 4, 6, 9),
                    //표지판 다리(아랫부분)
                    Block.box(1, 0, 1, 4, 2, 15)
            ));
            // 오른쪽 부분
        } else {
            return rotateShape(direction, Shapes.or(
                    //표지판 몸통
                    Block.box(0, 6, 6, 16, 16, 10),
                    Block.box(0, 16, 6, 16, 23, 10),

                    //표지판 다리(윗부분)
                    Block.box(12, 2, 7, 15, 6, 9),
                    //표지판 다리(아랫부분)
                    Block.box(12, 0, 1, 15, 2, 15)
            ));
        }
    }

    private static VoxelShape rotateShape(Direction to, VoxelShape shape) {
        Direction from = Direction.NORTH;
        VoxelShape[] buffer = {shape, Shapes.empty()};

        int times = (to.get2DDataValue() - from.get2DDataValue() + 4) % 4;

        for (int i = 0; i < times; i++) {
            buffer[0].forAllBoxes((minX, minY, minZ, maxX, maxY, maxZ) -> buffer[1] = Shapes.or(buffer[1],
                    Shapes.box(1 - maxZ, minY, minX, 1 - minZ, maxY, maxX)));
            buffer[0] = buffer[1];
            buffer[1] = Shapes.empty();
        }

        return buffer[0];
    }

    // ** HANDLE EVENT **

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, @NotNull ItemStack stack) {
        Direction facing = state.getValue(FACING);
        BlockPos rightPos = pos.relative(facing.getClockWise());
        level.setBlock(rightPos, state.setValue(PART, Part.RIGHT), 3);
    }

    @Override
    public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, boolean willHarvest, FluidState fluid) {
        boolean isRight = state.getValue(PART).equals(Part.RIGHT);
        Direction facing = state.getValue(FACING);
        BlockPos otherPos = isRight ? pos.relative(facing.getCounterClockWise()) : pos.relative(facing.getClockWise());
        if (level.getBlockState(otherPos).getBlock() == this) {
            level.removeBlock(otherPos, false);
        }

        return super.onDestroyedByPlayer(state, level, pos, player, willHarvest, fluid);
    }

    @SuppressWarnings("deprecation")
    @Override
    public @NotNull InteractionResult use(@NotNull BlockState pState, @NotNull Level pLevel, @NotNull BlockPos pPos, @NotNull Player pPlayer, @NotNull InteractionHand pHand, @NotNull BlockHitResult pHit) {
        if (pLevel.isClientSide) Minecraft.getInstance().setScreen(new BewareOfDogScreen());
        return InteractionResult.SUCCESS;
    }
}

enum Part implements StringRepresentable {
    LEFT, RIGHT;
    @Override
    public @NotNull String getSerializedName() {
        return name().toLowerCase();
    }
}

enum Color implements StringRepresentable {
    GREEN, RED, WHITE, YELLOW;

    @Override
    public @NotNull String getSerializedName() {
        return this.name().toLowerCase();
    }
}
