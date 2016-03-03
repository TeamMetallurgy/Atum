package com.teammetallurgy.atum.client.render.entity.arrow;

import com.teammetallurgy.atum.entity.arrow.EntityNutsCall;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderNutsCall extends Render<EntityNutsCall> {

    public RenderNutsCall(RenderManager renderManager) {
        super(renderManager);
    }

    @Override
    public void doRender(EntityNutsCall nutsCall, double x, double y, double z, float entityYaw, float partialTicks) {
        this.bindEntityTexture(nutsCall);
        GlStateManager.pushMatrix();
        GlStateManager.translate((float) x, (float) y, (float) z);
        GlStateManager.rotate(nutsCall.prevRotationYaw + (nutsCall.rotationYaw - nutsCall.prevRotationYaw) * partialTicks - 90.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(nutsCall.prevRotationPitch + (nutsCall.rotationPitch - nutsCall.prevRotationPitch) * partialTicks, 0.0F, 0.0F, 1.0F);
        float f = 0.15625F;
        GlStateManager.enableRescaleNormal();
        float f1 = (float) nutsCall.arrowShake - partialTicks;

        if (f1 > 0.0F) {
            float f12 = -MathHelper.sin(f1 * 3.0F) * f1;
            GL11.glRotatef(f12, 0.0F, 0.0F, 1.0F);
        }

        GlStateManager.scale(2f, 1.5f, 1.5f);
        GlStateManager.translate(-0.85F, 0.0F, 0.0F);
        GL11.glNormal3f(f, 0.0F, 0.0F);

        //ItemRenderer.renderItemIn2D(tessellator, -1f, -10 / 32.0f, -5 / 32.0f, 12 / 32.0f, 32, 32 * 32, 0.0625F); //TODO

        GlStateManager.disableRescaleNormal();
        GlStateManager.popMatrix();
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityNutsCall nutsCall) {
        return new ResourceLocation("Atum:textures/projectiles/nutscall.png");
    }
}