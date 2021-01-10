package com.mndk.kmap4bte.gui;

import com.mndk.kmap4bte.gui.option.GuiOptionsList;
import com.mndk.kmap4bte.gui.option.GuiBooleanOption;
import com.mndk.kmap4bte.gui.option.GuiEnumOption;
import com.mndk.kmap4bte.map.RenderMapSource;
import com.mndk.kmap4bte.map.RenderMapType;
import com.mndk.kmap4bte.renderer.MapRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import org.lwjgl.input.Mouse;

import java.io.IOException;

public class MapRendererOptionsUI extends GuiScreen {

    // private RenderMapNameToggleButton mapNameToggleButton;
    // private GuiSlider mapYSlider;

    private static final int TITLE_HEIGHT = 8;
    private static final int OPTIONS_LIST_TOP_MARGIN = 24;
    private static final int BUTTON_TOP_MARGIN = 5;

    private static final int BUTTON_WIDTH = 200;
    private static final int BUTTON_HEIGHT = 20;
    private static final int DONE_BUTTON_BOTTOM_MARGIN = 26;

    GuiButton doneButton;
    GuiOptionsList options;

    @Override
    public void initGui() {
        super.initGui();
        Mouse.setGrabbed(false);

        this.options = new GuiOptionsList(this,
                (this.width - BUTTON_WIDTH) / 2, OPTIONS_LIST_TOP_MARGIN,
                BUTTON_WIDTH, BUTTON_HEIGHT, BUTTON_TOP_MARGIN
        );

        this.options.add(new GuiBooleanOption(
                () -> MapRenderer.drawTiles,
                (b) -> MapRenderer.drawTiles = b,
                "Render map"
        ));

        this.options.add(new GuiEnumOption<>(
                () -> MapRenderer.renderMapType,
                (e) -> MapRenderer.renderMapType = e,
                RenderMapType.PLAIN_MAP,
                RenderMapType.AERIAL,
                "Map Type"
        ));

        this.options.add(new GuiEnumOption<>(
                () -> MapRenderer.renderMapSource,
                (e) -> MapRenderer.renderMapSource = e,
                RenderMapSource.KAKAO,
                RenderMapSource.KAKAO,
                "Map Source"
        ));

        for(GuiButton button : options.buttons) {
            this.addButton(button);
        }

        this.addButton(doneButton = new GuiButton(
                0,
                (this.width - BUTTON_WIDTH) / 2, this.height - DONE_BUTTON_BOTTOM_MARGIN,
                BUTTON_WIDTH, BUTTON_HEIGHT,
                I18n.format("gui.done")
        ));
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        if(button.id == 0) { // Done button
            Minecraft.getMinecraft().player.closeScreen();
        }
        options.actionPerformed(button);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRenderer, "Map Options", this.width / 2, TITLE_HEIGHT, 0xFFFFFF);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}
