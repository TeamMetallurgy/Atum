package com.teammetallurgy.atum.handler.event;

import com.teammetallurgy.atum.handler.AtumConfig;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;

public class AtumFogEventListener {

    @SubscribeEvent
    public void renderFog(EntityViewRenderEvent.RenderFogEvent event) {
        if (event.entity.dimension == AtumConfig.DIMENSION_ID && AtumConfig.FOG_ENABLED) {
            GL11.glFogi(GL11.GL_FOG_MODE, GL11.GL_EXP);
            GL11.glFogf(GL11.GL_FOG_DENSITY, 0.08F);
        }
    }
}