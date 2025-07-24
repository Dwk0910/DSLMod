package org.dslofficial.dslmod.items;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class DrinkItem extends Item {
    public final List<MobEffectInstance> effectList = new ArrayList<>();
    public final int food;
    public final float saturation;

    public DrinkItem(Item.Properties properties, List<MobEffectInstance> effectList, int food, float saturation) {
        super(properties);
        this.effectList.addAll(effectList);
        this.food = food;
        this.saturation = saturation;
    }

    @Override
    public @NotNull UseAnim getUseAnimation(@NotNull ItemStack pStack) {
        return UseAnim.DRINK;
    }

    @Override
    public int getUseDuration(@NotNull ItemStack pStack) {
        return 30;
    }

    @Override
    public @NotNull ItemStack finishUsingItem(@NotNull ItemStack pStack, @NotNull Level pLevel, @NotNull LivingEntity pLivingEntity) {
        if (pLivingEntity instanceof Player pPlayer) {
            // 효과 부여
            for (MobEffectInstance effect : effectList) {
                pPlayer.addEffect(new MobEffectInstance(effect));
            }

            // 배고픔 채우기
            pPlayer.getFoodData().eat(food, saturation);

            // 아이템 사용 처리 (손에서 제거)
            if (!pPlayer.isCreative()) pStack.shrink(1);
        }

        return pStack;
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level pLevel, @NotNull Player pPlayer, @NotNull InteractionHand pUsedHand) {
        pPlayer.startUsingItem(pPlayer.getUsedItemHand());
        return InteractionResultHolder.consume(pPlayer.getItemInHand(pUsedHand));
    }
}
