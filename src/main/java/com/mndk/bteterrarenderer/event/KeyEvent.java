package com.mndk.bteterrarenderer.event;

import com.mndk.bteterrarenderer.BTETerraRenderer;
import com.mndk.bteterrarenderer.BTETerraRendererConfig;
import com.mndk.bteterrarenderer.gui.MapRenderingOptionsSidebar;
import com.mndk.bteterrarenderer.gui.MapRenderingOptionsUI;
import com.mndk.bteterrarenderer.proxy.ClientProxy;
import com.mndk.bteterrarenderer.storage.TileMapYamlLoader;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber(modid = BTETerraRenderer.MODID, value = Side.CLIENT)
public class KeyEvent {

	@SideOnly(Side.CLIENT)
	@SubscribeEvent()
	public static void onKeyEvent(InputEvent.KeyInputEvent event) {
		
		if(ClientProxy.mapOptionsKey.isPressed()) {

			try { TileMapYamlLoader.refresh(); } catch (Exception e) {
				BTETerraRenderer.logger.error("Error caught while parsing yaml map files!");
				e.printStackTrace();
			}
			
			MapRenderingOptionsUI.open();
		}
		else if(ClientProxy.mapToggleKey.isPressed()) {
			BTETerraRendererConfig.doRender = !BTETerraRendererConfig.doRender;
			BTETerraRendererConfig.save();
		}

		// TODO Delete these before the release
		if(ClientProxy.sidebarCheck.isPressed()) {
			Minecraft.getMinecraft().displayGuiScreen(new MapRenderingOptionsSidebar());
		}
	}

}
