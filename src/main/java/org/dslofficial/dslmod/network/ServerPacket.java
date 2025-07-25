package org.dslofficial.dslmod.network;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import org.dslofficial.dslmod.DSLMod;

public class ServerPacket {
    public static SimpleChannel instance;

    private static int packetId = 0;
    private static int id() {
        return packetId++;
    }

    public static void register() {
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(DSLMod.MODID, "messages"))
                .networkProtocolVersion(() -> DSLMod.VERSION)
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();
        instance = net;

        net.messageBuilder(SpectatorShortcutPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .encoder(SpectatorShortcutPacket::encoder)
                .decoder(SpectatorShortcutPacket::new)
                .consumer(SpectatorShortcutPacket::handle)
                .add();
    }
}
