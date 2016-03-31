package com.teammetallurgy.atum.handler.event;

import com.teammetallurgy.atum.handler.AtumConfig;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class AtumFogEventListener {

    @SubscribeEvent
    public void renderFog(EntityViewRenderEvent.RenderFogEvent event) {
        if (event.getEntity().dimension == AtumConfig.DIMENSION_ID && AtumConfig.FOG_ENABLED) {
            GlStateManager.setFog(GlStateManager.FogMode.EXP);
            GlStateManager.setFogDensity(0.08F);
        }
    }
}