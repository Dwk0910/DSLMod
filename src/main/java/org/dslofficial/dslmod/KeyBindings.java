package org.dslofficial.dslmod;

import com.mojang.blaze3d.platform.InputConstants;

import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.ClientRegistry;

import org.lwjgl.glfw.GLFW;

public class KeyBindings {
    public static KeyMapping spectatorShortcut = new KeyMapping(
            "key.dslmod.spectator_shortcut",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_V,
            "key.categories.dslmod"
    );

    public static KeyMapping nightvisionShortcut = new KeyMapping(
            "key.dslmod.nightvision_shortcut",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_B,
            "key.categories.dslmod"
    );

    public static void register() {
        ClientRegistry.registerKeyBinding(spectatorShortcut);
        ClientRegistry.registerKeyBinding(nightvisionShortcut);
    }
}
