package com.mndk.mapdisp4bte.event;

import com.mndk.mapdisp4bte.MapDisplayer4BTE;
import com.mndk.mapdisp4bte.config.ConfigHandler;
import com.mndk.mapdisp4bte.renderer.MapTileRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber(modid = MapDisplayer4BTE.MODID, value = Side.CLIENT)
public class RenderEvent {



    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void onRenderEvent(final RenderWorldLastEvent event) {
        EntityPlayer player = Minecraft.getMinecraft().player;

        // "Smooth" player position
        final float partialTicks = event.getPartialTicks();
        final double px = player.lastTickPosX + ((player.posX - player.lastTickPosX) * partialTicks);
        final double py = player.lastTickPosY + ((player.posY - player.lastTickPosY) * partialTicks);
        final double pz = player.lastTickPosZ + ((player.posZ - player.lastTickPosZ) * partialTicks);

        if(ConfigHandler.getModConfig().isDrawTiles()) {
            try {
                MapTileRenderer.renderTiles(ConfigHandler.getModConfig().getMapSource().getMapRenderer(), px, py, pz);
            } catch(IllegalArgumentException exception) {
                exception.printStackTrace();
            }
        }
    }


}
