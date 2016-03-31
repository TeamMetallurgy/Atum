package com.teammetallurgy.atum.entity.arrow;

import com.teammetallurgy.atum.utils.Constants;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public class EntityArrowFire extends CustomArrow {

    public EntityArrowFire(World world) {
        super(world);
    }

    public EntityArrowFire(World world, EntityLivingBase shooter) {
        super(world, shooter);
    }

    @Override
    protected void entityInit() {
        this.dataManager.register(CRITICAL, (byte) 0);
    }

    @Override
    public String getTexture() {
        return Constants.MODID + ":" + "textures/projectiles/arrows_fire.png";
    }
}