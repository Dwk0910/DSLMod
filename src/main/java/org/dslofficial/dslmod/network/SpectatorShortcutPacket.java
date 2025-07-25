package org.dslofficial.dslmod.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.GameType;
import net.minecraftforge.network.NetworkEvent;
import org.dslofficial.dslmod.util.PrintHeader;

import java.util.function.Supplier;

public class SpectatorShortcutPacket {
    public SpectatorShortcutPacket() {}
    public SpectatorShortcutPacket(FriendlyByteBuf ignored) {

    }

    public static void handle(SpectatorShortcutPacket ignored, Supplier<NetworkEvent.Context> supplier) {
        supplier.get().enqueueWork(() -> {
            ServerPlayer player = supplier.get().getSender();
            if (player != null) {
                if (player.isSpectator()) {
                    player.setGameMode(GameType.CREATIVE);
                    player.displayClientMessage(PrintHeader.header("info", "게임모드가 크리에이티브로 변경되었습니다."), true);
                }
                else if (player.isCreative()) {
                    player.setGameMode(GameType.SPECTATOR);
                    player.displayClientMessage(PrintHeader.header("info", "게임모드가 로 변경되었습니다."), true);
                }
            }
        });
        supplier.get().setPacketHandled(true);
    }

    public void encoder(FriendlyByteBuf ignored) {

    }
}
