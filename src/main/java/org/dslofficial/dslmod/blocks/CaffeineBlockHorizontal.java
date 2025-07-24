package org.dslofficial.dslmod.blocks;

import net.minecraft.core.BlockPos;

import net.minecraft.sounds.SoundEvents;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import org.jetbrains.annotations.NotNull;

import org.dslofficial.dslmod.objects.HorizontalDirectionalBlock;

public class CaffeineBlockHorizontal extends HorizontalDirectionalBlock {
    public CaffeineBlockHorizontal(Properties properties) {
        super(properties);
    }

    @SuppressWarnings("deprecation")
    @Override
    public @NotNull InteractionResult use(@NotNull BlockState pState, @NotNull Level pLevel, @NotNull BlockPos pPos, @NotNull Player pPlayer, @NotNull InteractionHand pHand, @NotNull BlockHitResult pHit) {
        if (pPlayer instanceof Player) {
            // break block (replace to air)
            pLevel.setBlock(pPos, Blocks.AIR.defaultBlockState(), 3);

            // give effect to player
            pPlayer.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION,  12000, 2, false, false));
            pPlayer.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 12000, 2, false, false));


            // sound play
            pPlayer.playSound(SoundEvents.GENERIC_EAT, 1.0F, 1.0F);
            pPlayer.playSound(SoundEvents.PLAYER_BURP, 1.0F, 1.0F);
            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }
}
