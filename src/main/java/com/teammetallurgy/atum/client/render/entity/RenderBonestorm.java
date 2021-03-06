package com.teammetallurgy.atum.client.render.entity;

import com.teammetallurgy.atum.client.model.entity.ModelBonestorm;
import com.teammetallurgy.atum.entity.EntityBonestorm;
import com.teammetallurgy.atum.utils.Constants;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderBonestorm extends RenderLiving<EntityBonestorm> {
    private static final ResourceLocation bonestormTextures = new ResourceLocation(Constants.MODID + ":" + "textures/entities/bonestorm.png");

    public RenderBonestorm(RenderManager renderManager) {
        super(renderManager, new ModelBonestorm(), 0.5F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityBonestorm entity) {
        return bonestormTextures;
    }
}