package com.teammetallurgy.atum.entity.arrow;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public class EntityArrowQuickdraw extends CustomArrow {

    public EntityArrowQuickdraw(World world) {
        super(world);
    }

    public EntityArrowQuickdraw(World world, EntityLivingBase shooter, float velocity) {
        super(world, shooter, velocity);
    }

    @Override
    protected void entityInit() {
        this.dataWatcher.addObject(16, Byte.valueOf((byte) 0));
    }

    @Override
    public String getTexture() {
        return "Atum:textures/projectiles/arrows_quickdraw.png";
    }
}