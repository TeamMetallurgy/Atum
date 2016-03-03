package com.teammetallurgy.atum.client.render.entity;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderBandit extends RenderBiped {

    public RenderBandit(RenderManager renderManager, ModelBiped modelBiped, float shadowSize) {
        super(renderManager, modelBiped, shadowSize);
    }
}