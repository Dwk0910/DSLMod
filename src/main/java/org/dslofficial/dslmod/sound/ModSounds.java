package org.dslofficial.dslmod.sound;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import net.minecraftforge.registries.RegistryObject;
import org.dslofficial.dslmod.DSLMod;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> sounds = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, DSLMod.MODID);
    public static void register(IEventBus eventBus) {
        sounds.register(eventBus);
    }

    public static final RegistryObject<SoundEvent> drink = sounds.register("drink", () -> new SoundEvent(new ResourceLocation(DSLMod.MODID, "drink")));
}
