package com.luffygamerpro77.solasdlss.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.gui.DrawContext;

@Mixin(InGameHud.class)
public class InGameHudMixin {
    @Inject(method = "render", at = @At("TAIL"))
    private void onHudRender(DrawContext context, float tickDelta, CallbackInfo ci) {
        // HUD is rendered via HudRenderCallback, no mixin needed
    }
}
