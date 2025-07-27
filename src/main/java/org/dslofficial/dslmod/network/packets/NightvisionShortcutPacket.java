package org.dslofficial.dslmod.network.packets;

import net.minecraft.ChatFormatting;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraftforge.network.NetworkEvent;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class NightvisionShortcutPacket {
    public NightvisionShortcutPacket() {}
    public NightvisionShortcutPacket(FriendlyByteBuf ignored) {}
    public void encoder(FriendlyByteBuf ignored) {}
    public static void handle(NightvisionShortcutPacket ignored, @NotNull Supplier<NetworkEvent.Context> supplier) {
        ServerPlayer player = supplier.get().getSender();

        if (player != null) {
            MobEffectInstance instance = new MobEffectInstance(MobEffects.NIGHT_VISION, 100000, 100, true, false, true);
            if (!player.hasEffect(MobEffects.NIGHT_VISION)) {
                player.addEffect(instance);
                player.displayClientMessage(new TextComponent("야간투시 모드를 활성화하였습니다.").withStyle(ChatFormatting.GRAY), true);
            } else {
                player.removeEffect(MobEffects.NIGHT_VISION);
                player.displayClientMessage(new TextComponent("야간투시 모드를 비활성화하였습니다.").withStyle(ChatFormatting.GRAY), true);
            }
        }
    }
}
