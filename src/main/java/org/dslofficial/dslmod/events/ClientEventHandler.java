package org.dslofficial.dslmod.events;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.dslofficial.dslmod.DSLMod;
import org.dslofficial.dslmod.KeyBindings;
import org.dslofficial.dslmod.network.ServerPacket;
import org.dslofficial.dslmod.network.SpectatorShortcutPacket;

@Mod.EventBusSubscriber(modid = DSLMod.MODID, value = Dist.CLIENT)
public class ClientEventHandler {
    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        Minecraft mc = Minecraft.getInstance();
        if (event.phase == TickEvent.Phase.END && mc.player != null) {
            if (KeyBindings.spectatorShortcut.consumeClick()) {
                ServerPacket.instance.sendToServer(new SpectatorShortcutPacket());
            }
        }
    }
}
