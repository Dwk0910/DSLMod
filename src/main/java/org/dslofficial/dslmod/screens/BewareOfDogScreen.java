package org.dslofficial.dslmod.screens;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.OptionsList;

import net.minecraft.network.chat.TextComponent;

import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class BewareOfDogScreen extends Screen {
    // Minecraft 인스턴스
    private final Minecraft minecraft = Minecraft.getInstance();

    public BewareOfDogScreen() {
        super(new TextComponent("개조심 표지 설정"));
    }

    @Override
    protected void init() {

        this.addRenderableWidget(new OptionsList(minecraft, this.width / 2 - 100, this.height / 2 - 10, 200, 20, 5));
        this.addRenderableWidget(new Button(this.width / 2 - 100, this.height - 40, 200, 20, new TextComponent("적용 및 닫기"), (button) -> this.minecraft.setScreen(null)));
    }

    @Override
    public void render(@NotNull PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        this.renderBackground(pPoseStack);

        drawCenteredString(pPoseStack, this.font, this.title, this.width / 2, 20, 0xFFFFFF);
        this.optionsList

        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}

class ColorSelectWidget extends AbstractWidget {
    private int currentIdx = 0;
    private final Consumer<String> onColorSelected;
    public ColorSelectWidget(int x, int y, int width, int height, Consumer<String> onColorSelected) {
        super(x, y, width, height, new TextComponent("Color"));
        this.onColorSelected = onColorSelected;
    }

    @Override
    public void onClick(double pMouseX, double pMouseY) {
        currentIdx
    }

    @Override
    public void updateNarration(NarrationElementOutput pNarrationElementOutput) {
    }
}
