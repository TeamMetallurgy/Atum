package com.teammetallurgy.atum.entity.arrow;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IThrowableEntity;

public class CustomArrow extends EntityArrow implements IThrowableEntity {
    public float arrowShake = 0;

    public CustomArrow(World world) {
        super(world);
    }

    public CustomArrow(World world, EntityLivingBase shooter, float velocity) {
        super(world, shooter, velocity);
    }

    @Override
    protected void entityInit() {
    }

    @Override
    public Entity getThrower() {
        return shootingEntity;
    }

    @Override
    public void setThrower(Entity entity) {
        shootingEntity = entity;
    }

    public String getTexture() {
        return "minecraft:arrow";
    }
}