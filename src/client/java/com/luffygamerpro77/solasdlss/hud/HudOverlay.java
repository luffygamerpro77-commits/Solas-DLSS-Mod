package com.luffygamerpro77.solasdlss.hud;

import com.luffygamerpro77.solasdlss.SolasDlssMod;
import com.luffygamerpro77.solasdlss.config.ModConfig;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;

public class HudOverlay {
    private static long lastUpdate = 0;
    private static int frameCount = 0;
    private static double currentFps = 0.0;
    private static long lastFrameTimeNs = System.nanoTime();

    public static void register() {
        HudRenderCallback.EVENT.register(HudOverlay::render);
    }

    private static void render(DrawContext drawContext, float tickDelta) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null || client.player == null) return;

        ModConfig config = SolasDlssMod.config;
        if (!config.showHud) return;

        // Calculate FPS
        long now = System.nanoTime();
        long elapsed = now - lastFrameTimeNs;
        lastFrameTimeNs = now;

        frameCount++;
        if (System.currentTimeMillis() - lastUpdate >= 1000) {
            currentFps = frameCount * 1000.0 / (System.currentTimeMillis() - lastUpdate);
            frameCount = 0;
            lastUpdate = System.currentTimeMillis();
        }

        int x = 12;
        int y = 12;
        int color = 0xFFFFFFFF;
        int warningColor = 0xFFB562;

        drawContext.drawText(client.textRenderer, Text.literal("§6[Solas DLSS Prototype]"), x, y, color, false);
        drawContext.drawText(client.textRenderer, Text.literal("DLSS: " + config.dlssMode.label), x, y + 12, color, false);
        drawContext.drawText(client.textRenderer, Text.literal("FG: " + config.frameGenerationMode.label), x, y + 24, warningColor, false);
        drawContext.drawText(client.textRenderer, Text.literal("Reflex: " + config.reflexMode.label), x, y + 36, color, false);
        drawContext.drawText(client.textRenderer, Text.literal(String.format("FPS: %.1f", currentFps)), x, y + 48, color, false);
        drawContext.drawText(client.textRenderer, Text.literal("Backend: OpenGL"), x, y + 60, color, false);
        drawContext.drawText(client.textRenderer, Text.literal("§cNote: FG requires Vulkan or DX12"), x, y + 72, warningColor, false);
    }
}
