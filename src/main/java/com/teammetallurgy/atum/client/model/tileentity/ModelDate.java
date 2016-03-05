package com.teammetallurgy.atum.client.model.tileentity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelDate extends ModelBase {
    ModelRenderer Fruit;
    ModelRenderer Stem;

    public ModelDate() {
        super.textureWidth = 32;
        super.textureHeight = 32;
        this.Fruit = new ModelRenderer(this, 0, 0);
        this.Fruit.addBox(0.0F, 0.0F, 0.0F, 6, 8, 6);
        this.Fruit.setRotationPoint(-1.0F, 3.0F, -1.0F);
        this.Fruit.setTextureSize(32, 32);
        this.Fruit.mirror = true;
        this.setRotation(this.Fruit, 0.0F, 0.0F, 0.0F);
        this.Stem = new ModelRenderer(this, 0, 14);
        this.Stem.addBox(0.0F, 0.0F, 0.0F, 2, 6, 2);
        this.Stem.setRotationPoint(1.0F, -3.0F, 1.0F);
        this.Stem.setTextureSize(32, 32);
        this.Stem.mirror = true;
        this.setRotation(this.Stem, 0.0F, 0.0F, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float scale) {
        super.render(entity, f, f1, f2, f3, f4, scale);
        this.Fruit.render(scale);
        this.Stem.render(scale);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    }
}