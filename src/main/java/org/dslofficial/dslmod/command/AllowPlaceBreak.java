package org.dslofficial.dslmod.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import org.dslofficial.dslmod.DSLMod;

public class AllowPlaceBreak {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("apb").executes(AllowPlaceBreak::execute));
    }

    private static int execute(CommandContext<CommandSourceStack> context) {
        CommandSourceStack source = context.getSource();

        try {
            ServerPlayer player = source.getPlayerOrException();
            if (!player.hasEffect(MobEffects.DIG_SPEED)) player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 100, 10, false, false, true));
            else player.removeEffect(MobEffects.DIG_SPEED);
        } catch (CommandSyntaxException e) {
            DSLMod.logger.warn("이 커맨드는 플레이어(클라이언트)만 사용할 수 있습니다.");
        }

        return 1;
    }
}
