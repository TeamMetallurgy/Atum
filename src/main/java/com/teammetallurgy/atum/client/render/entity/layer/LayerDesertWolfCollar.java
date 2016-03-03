package com.teammetallurgy.atum.client.render.entity.layer;

import com.teammetallurgy.atum.client.render.entity.RenderDesertWolf;
import com.teammetallurgy.atum.entity.EntityDesertWolf;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.ResourceLocation;

public class LayerDesertWolfCollar implements LayerRenderer<EntityDesertWolf> { //TODO Check if the custom collar works as intended
    private static final ResourceLocation DESERT_WOLF_COLLAR = new ResourceLocation("atum:textures/entities/Desertwolf_collar.png");
    //private static final ResourceLocation WOLF_COLLAR = new ResourceLocation("textures/entity/wolf/wolf_collar.png");
    private final RenderDesertWolf wolfRenderer;

    public LayerDesertWolfCollar(RenderDesertWolf renderDesertWolf) {
        this.wolfRenderer = renderDesertWolf;
    }

    @Override
    public void doRenderLayer(EntityDesertWolf entityDesertWolf, float p_177141_2_, float p_177141_3_, float partialTicks, float p_177141_5_, float p_177141_6_, float p_177141_7_, float scale) {
        if (entityDesertWolf.isTamed() && !entityDesertWolf.isInvisible()) {
            this.wolfRenderer.bindTexture(DESERT_WOLF_COLLAR);
            EnumDyeColor enumdyecolor = EnumDyeColor.byMetadata(entityDesertWolf.getCollarColor().getMetadata());
            float[] dyeRGB = EntitySheep.func_175513_a(enumdyecolor);
            GlStateManager.color(dyeRGB[0], dyeRGB[1], dyeRGB[2]);
            this.wolfRenderer.getMainModel().render(entityDesertWolf, p_177141_2_, p_177141_3_, p_177141_5_, p_177141_6_, p_177141_7_, scale);
        }
    }

    @Override
    public boolean shouldCombineTextures() {
        return true;
    }
}