package com.mndk.kmap4bte.proxy;

import com.mndk.kmap4bte.commands.ModCommands;
import com.mndk.kmap4bte.event.KeyEvent;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import org.lwjgl.input.Keyboard;

public class ClientProxy extends CommonProxy {

    public static KeyBinding mapOptionsKey;

    public static void initializeKeys() {
        mapOptionsKey = new KeyBinding(
                I18n.format("key.kmap4bte.maprenderer.options_ui"),
                Keyboard.KEY_V,
                I18n.format("key.kmap4bte.maprenderer.category"));
        ClientRegistry.registerKeyBinding(mapOptionsKey);
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
        initializeKeys();
        MinecraftForge.EVENT_BUS.register(KeyEvent.class);
    }

    @Override
    public void serverStarting(FMLServerStartingEvent event) {
        ModCommands.registerCommands(event);
    }
}