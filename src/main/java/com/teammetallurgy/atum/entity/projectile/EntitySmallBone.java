package com.teammetallurgy.atum.entity.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntitySmallBone extends EntityBone {

    public EntitySmallBone(World world, EntityLivingBase shooter, double accelX, double accelY, double accelZ) {
        super(world, shooter, accelX, accelY, accelZ);
        this.setSize(0.3125F, 0.3125F);
    }

    @Override
    protected void onImpact(MovingObjectPosition movingObject) {
        if (!this.worldObj.isRemote) {
            if (movingObject.entityHit != null) {
                boolean flag = movingObject.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.shootingEntity), 2.0F);

                if (flag) {
                    this.applyEnchantments(this.shootingEntity, movingObject.entityHit);
                }
            }
            this.setDead();
        }
    }

    @Override
    public boolean canBeCollidedWith() {
        return false;
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        return false;
    }
}