package com.mndk.kmap4bte.event;

import com.mndk.kmap4bte.ModReference;
import com.mndk.kmap4bte.renderer.MapTileRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber(modid = ModReference.MODID, value = Side.CLIENT)
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

        if(MapTileRenderer.drawTiles) {
            MapTileRenderer.renderTiles(MapTileRenderer.renderMapSource.getMapRenderer(), px, py, pz);
        }
    }


}
