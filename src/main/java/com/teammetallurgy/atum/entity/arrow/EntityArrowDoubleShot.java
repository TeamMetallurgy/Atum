package com.teammetallurgy.atum.entity.arrow;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public class EntityArrowDoubleShot extends CustomArrow {

    public EntityArrowDoubleShot(World world) {
        super(world);
    }

    public EntityArrowDoubleShot(World world, EntityLivingBase shooter, float velocity) {
        super(world, shooter, velocity);
    }

    @Override
    protected void entityInit() {
        this.dataWatcher.addObject(16, Byte.valueOf((byte) 0));
    }

    @Override
    public String getTexture() {
        return "Atum:textures/projectiles/arrows_double.png";
    }
}