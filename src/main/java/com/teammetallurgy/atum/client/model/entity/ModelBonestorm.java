package com.teammetallurgy.atum.client.model.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelBonestorm extends ModelBase {
    private ModelRenderer[] bonestormSticks = new ModelRenderer[12];
    private ModelRenderer bonestormHead;

    public ModelBonestorm() {
        for (int i = 0; i < this.bonestormSticks.length; ++i) {
            this.bonestormSticks[i] = new ModelRenderer(this, 0, 16);
            this.bonestormSticks[i].addBox(0.0F, 0.0F, 0.0F, 2, 8, 2);
        }

        this.bonestormHead = new ModelRenderer(this, 0, 0);
        this.bonestormHead.addBox(-4.0F, -4.0F, -4.0F, 8, 8, 8);
    }

    @Override
    public void render(Entity entity, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float scale) { //TODO Proper parameters
        this.setRotationAngles(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, scale, entity);
        this.bonestormHead.render(scale);

        for (int i = 0; i < this.bonestormSticks.length; ++i) {
            this.bonestormSticks[i].render(scale);
        }
    }

    @Override
    public void setRotationAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity entity) {
        float f6 = p_78087_3_ * (float) Math.PI * -0.1F;
        int i;

        for (i = 0; i < 4; ++i) {
            this.bonestormSticks[i].rotationPointY = -2.0F + MathHelper.cos(((float) (i * 2) + p_78087_3_) * 0.25F);
            this.bonestormSticks[i].rotationPointX = MathHelper.cos(f6) * 9.0F;
            this.bonestormSticks[i].rotationPointZ = MathHelper.sin(f6) * 9.0F;
            ++f6;
        }

        f6 = ((float) Math.PI / 4F) + p_78087_3_ * (float) Math.PI * 0.03F;

        for (i = 4; i < 8; ++i) {
            this.bonestormSticks[i].rotationPointY = 2.0F + MathHelper.cos(((float) (i * 2) + p_78087_3_) * 0.25F);
            this.bonestormSticks[i].rotationPointX = MathHelper.cos(f6) * 7.0F;
            this.bonestormSticks[i].rotationPointZ = MathHelper.sin(f6) * 7.0F;
            ++f6;
        }

        f6 = 0.47123894F + p_78087_3_ * (float) Math.PI * -0.05F;

        for (i = 8; i < 12; ++i) {
            this.bonestormSticks[i].rotationPointY = 11.0F + MathHelper.cos(((float) i * 1.5F + p_78087_3_) * 0.5F);
            this.bonestormSticks[i].rotationPointX = MathHelper.cos(f6) * 5.0F;
            this.bonestormSticks[i].rotationPointZ = MathHelper.sin(f6) * 5.0F;
            ++f6;
        }

        this.bonestormHead.rotateAngleY = p_78087_4_ / (180F / (float) Math.PI);
        this.bonestormHead.rotateAngleX = p_78087_5_ / (180F / (float) Math.PI);
    }
}