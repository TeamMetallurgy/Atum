package com.teammetallurgy.atum.client.render.entity;

import com.teammetallurgy.atum.entity.EntityGhost;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderGhost extends RendererLivingEntity<EntityGhost> {

    public RenderGhost(RenderManager renderManager, ModelBase modelBase, float shadowSize) {
        super(renderManager, modelBase, shadowSize);
        this.mainModel = modelBase;
        this.shadowSize = shadowSize;
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityGhost EntityGhost) {
        return new ResourceLocation("atum", "textures/entities/desert_ghost.png");
    }
}