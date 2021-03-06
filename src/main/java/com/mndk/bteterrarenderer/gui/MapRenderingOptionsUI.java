package com.mndk.bteterrarenderer.gui;

import com.mndk.bteterrarenderer.BTETerraRendererConfig;
import com.mndk.bteterrarenderer.gui.sub_ui.DefaultMapRenderingOptionsUI;
import com.mndk.bteterrarenderer.gui.sub_ui.MapAlignerUI;
import com.mndk.bteterrarenderer.gui.sub_ui.MapSelectorUI;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

import javax.annotation.ParametersAreNonnullByDefault;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * UI for map rendering options.
 */
@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class MapRenderingOptionsUI extends GuiScreen {
	
	
	
	public static final int DEFAULT_BUTTON_HEIGHT = 20;
	public static final int DEFAULT_BUTTON_MARGIN_BOTTOM = 5;
	
	
	final DefaultMapRenderingOptionsUI defaultOptions;
	public boolean renderMapSelector;
	final MapSelectorUI mapSelectorUi;
	final MapAlignerUI alignmentToolUi;
	
	
	
	public static void open() {
		Minecraft.getMinecraft().displayGuiScreen(new MapRenderingOptionsUI());
	}
	
	
	
	@Deprecated
	public static void open(long milliseconds) {
		new Timer().schedule(new TimerTask() {
			public void run() { 
				synchronized(Minecraft.getMinecraft()) { open(); }
			}
		}, milliseconds);
		// I could've used a "rendering scheduler" or something instead, but I think it's an overkill to make one.
		// So I'm using "le ol' Timer#schedule" to delay opening the ui.
		// Not a good idea, but it sorta works, right?
		
		// It turned out it doesn't work lmao
		// TODO fix this
	}
	
	
	
	private MapRenderingOptionsUI() {
		this.defaultOptions = new DefaultMapRenderingOptionsUI(this);
		this.mapSelectorUi = new MapSelectorUI(this);
		this.alignmentToolUi = new MapAlignerUI(this);
	}
	
	
	
	@Override
	public void initGui() {
		super.initGui();
		this.defaultOptions.initGui();
		this.mapSelectorUi.initGui();
		this.alignmentToolUi.initGui();
	}
	
	
	
	@Override
	protected void actionPerformed(GuiButton button) {
		this.defaultOptions.actionPerformed(button);
		this.alignmentToolUi.actionPerformed(button);
		if(this.renderMapSelector) this.mapSelectorUi.actionPerformed(button);
	}
	
	
	
	@Override
	public void updateScreen() {
		super.updateScreen();
		this.defaultOptions.updateScreen();
		this.alignmentToolUi.updateScreen();
		if(this.renderMapSelector) this.mapSelectorUi.updateScreen();
	}
	
	
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.defaultOptions.drawScreen(mouseX, mouseY, partialTicks);
		this.alignmentToolUi.drawScreen(mouseX, mouseY, partialTicks);
		if(this.renderMapSelector) this.mapSelectorUi.drawScreen(mouseX, mouseY, partialTicks);
		
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	
	
	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		this.defaultOptions.mouseClicked(mouseX, mouseY, mouseButton);
		this.alignmentToolUi.mouseClicked(mouseX, mouseY, mouseButton);
		if(this.renderMapSelector) this.mapSelectorUi.mouseClicked(mouseX, mouseY, mouseButton);
		super.mouseClicked(mouseX, mouseY, mouseButton);
	}
	
	
	
	@Override
	protected void keyTyped(char key, int keyCode) throws IOException {
		this.defaultOptions.keyTyped(key, keyCode);
		this.alignmentToolUi.keyTyped(key, keyCode);
		if(this.renderMapSelector) this.mapSelectorUi.keyTyped(key, keyCode);
		super.keyTyped(key, keyCode);
	}
	
	
	
	@Override
	protected void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
		this.defaultOptions.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
		this.alignmentToolUi.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
		if(this.renderMapSelector) this.mapSelectorUi.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
	}
	
	
	
	@Override
	public void onGuiClosed() {
		BTETerraRendererConfig.save();
		super.onGuiClosed();
	}
	
	
	
	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
	
	
	
	public FontRenderer getFontRenderer() {
		return this.fontRenderer;
	}
	
	
	
	public <T extends GuiButton> T addButton(T b) {
		return super.addButton(b);
	}
}
