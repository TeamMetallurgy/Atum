package com.teammetallurgy.atum.client.render.entity;

import com.teammetallurgy.atum.entity.EntityPharaoh;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderPharaoh extends RenderBiped<EntityPharaoh> {

    public RenderPharaoh(RenderManager renderManager, ModelBiped modelBiped, float shadowSize) {
        super(renderManager, modelBiped, shadowSize);
    }

    @Override
    public void doRender(EntityPharaoh entityPharaoh, double x, double y, double z, float entityYaw, float partialTicks) {
        BossStatus.setBossStatus(entityPharaoh, true);
        super.doRender(entityPharaoh, x, y, z, entityYaw, partialTicks);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityPharaoh entityPharaoh) {
        return new ResourceLocation("atum", "textures/entities/PharaohBlue.png");
    }
}