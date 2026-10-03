package com.luffygamerpro77.solasdlss;

import com.luffygamerpro77.solasdlss.config.ModConfig;
import com.luffygamerpro77.solasdlss.hud.HudOverlay;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.GameMenuScreen;

public class SolasDlssMod implements ClientModInitializer {
    public static final String MOD_ID = "solas-dlss-mod";
    public static ModConfig config;
    private static boolean configScreenRequested = false;

    @Override
    public void onInitializeClient() {
        config = ModConfig.load();
        HudOverlay.register();

        ClientLifecycleEvents.CLIENT_STARTED.register(client -> {
            ModConfig.initializeDefaults();
        });

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player != null && client.options.openInventory.wasPressed()) {
                // Optional: could bind a key to open config screen
                // For now, config is done through solas-dlss-mod.json file
            }
        });
    }
}
