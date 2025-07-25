package org.dslofficial.dslmod.util;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;

public class PrintHeader {
    public static Component header(String type, String detail) {
        String exitmsg_detail = "";
        if (type.contains("/")) {
            exitmsg_detail = type.split("/")[1];
            type = type.split("/")[0];
        }

        switch (type) {
            case "info" -> {
                return new TextComponent("")
                        .append(new TextComponent("[")
                                .withStyle(ChatFormatting.GRAY))
                        .append(new TextComponent("DSL SERVER")
                                .withStyle(ChatFormatting.GREEN))
                        .append(new TextComponent("/")
                                .withStyle(ChatFormatting.GOLD))
                        .append(new TextComponent("INFO")
                                .withStyle(ChatFormatting.AQUA))
                        .append(new TextComponent("] ")
                                .withStyle(ChatFormatting.GRAY))
                        .append(new TextComponent(detail)
                                .withStyle(ChatFormatting.WHITE));
            }

            case "error" -> {
                return new TextComponent("")
                        .append(new TextComponent("[")
                                .withStyle(ChatFormatting.GRAY))
                        .append(new TextComponent("DSL SERVER")
                                .withStyle(ChatFormatting.GREEN))
                        .append(new TextComponent("/")
                                .withStyle(ChatFormatting.GOLD))
                        .append(new TextComponent("ERROR")
                                .withStyle(ChatFormatting.RED))
                        .append(new TextComponent("] ")
                                .withStyle(ChatFormatting.GRAY))
                        .append(new TextComponent(detail)
                                .withStyle(ChatFormatting.WHITE));
            }

            case "warning" -> {
                return new TextComponent("")
                        .append(new TextComponent("[")
                                .withStyle(ChatFormatting.GRAY))
                        .append(new TextComponent("DSL SERVER")
                                .withStyle(ChatFormatting.GREEN))
                        .append(new TextComponent("/")
                                .withStyle(ChatFormatting.GOLD))
                        .append(new TextComponent("WARNING")
                                .withStyle(ChatFormatting.YELLOW))
                        .append(new TextComponent("] ")
                                .withStyle(ChatFormatting.GRAY))
                        .append(new TextComponent(detail)
                                .withStyle(ChatFormatting.WHITE));
            }

            case "exitmsg" -> {
                Component header = new TextComponent("\n[ ")
                        .withStyle(ChatFormatting.GRAY, ChatFormatting.BOLD)
                        .append(new TextComponent("DSL OFFICIAL SERVER")
                                .withStyle(ChatFormatting.GREEN, ChatFormatting.BOLD))
                        .append(new TextComponent(" ]\n")
                                .withStyle(ChatFormatting.GRAY, ChatFormatting.BOLD));

                Component body;
                switch (exitmsg_detail) {
                    case "kick" -> body = new TextComponent("서버에서 ")
                            .withStyle(ChatFormatting.WHITE, ChatFormatting.BOLD)
                            .append(new TextComponent("추방 ")
                                    .withStyle(ChatFormatting.RED, ChatFormatting.BOLD))
                            .append(new TextComponent("당하셨습니다:\n\n")
                                    .withStyle(ChatFormatting.WHITE, ChatFormatting.BOLD))
                            .append(new TextComponent(detail + "\n")
                                    .withStyle(ChatFormatting.YELLOW));

                    case "ban" -> body = new TextComponent("서버에서 ")
                            .withStyle(ChatFormatting.WHITE, ChatFormatting.BOLD)
                            .append(new TextComponent("차단 ")
                                    .withStyle(ChatFormatting.RED, ChatFormatting.BOLD))
                            .append(new TextComponent("당하셨습니다:\n\n")
                                    .withStyle(ChatFormatting.WHITE, ChatFormatting.BOLD))
                            .append(new TextComponent(detail + "\n")
                                    .withStyle(ChatFormatting.YELLOW));

                    case "stop" -> body = new TextComponent("서버가 ")
                            .withStyle(ChatFormatting.WHITE, ChatFormatting.BOLD)
                            .append(new TextComponent("닫혔습니다.\n\n")
                                    .withStyle(ChatFormatting.BLUE, ChatFormatting.BOLD))
                            .append(new TextComponent(detail + "\n")
                                    .withStyle(ChatFormatting.YELLOW));

                    default -> body = new TextComponent("알 수 없는 detail : " + detail);
                }
                return new TextComponent("").append(header).append(body);
            }

            default -> {
                return new TextComponent("알 수 없는 type : " + type);
            }
        }
    }
}