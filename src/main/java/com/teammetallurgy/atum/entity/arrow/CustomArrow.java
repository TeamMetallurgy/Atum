package com.teammetallurgy.atum.entity.arrow;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class CustomArrow extends EntityArrow {

    public float arrowShake = 0;

    public CustomArrow(World world) {
        super(world);
    }

    public CustomArrow(World world, EntityLivingBase shooter, float velocity) {
        super(world, shooter, velocity);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound tagCompund) {
        super.readFromNBT(tagCompund);
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound tagCompund) {
        super.writeToNBT(tagCompund);
    }

    @Override
    protected void entityInit() {
    }

    public String getTexture() {
        return "minecraft:arrow";
    }

    @Override
    public void onUpdate() {
        this.onEntityUpdate();
    }
}