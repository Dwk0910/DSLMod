package org.dslofficial.dslmod;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.ClientRegistry;
import org.lwjgl.glfw.GLFW;

public class KeyBindings {
    public static KeyMapping spectatorShortcut;

    public static void register() {
        spectatorShortcut = new KeyMapping(
                "key.dslmod.spectator_shortcut",
                InputConstants.Type.KEYSYM,
                GLFW.GLFW_KEY_V,
                "key.categories.dslmod"
        );

        ClientRegistry.registerKeyBinding(spectatorShortcut);
    }
}
