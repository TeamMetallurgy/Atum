package com.teammetallurgy.atum.entity;

import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityUndeadBase extends EntityMob {
    public EntityUndeadBase(World world) {
        super(world);
    }

    @Override
    public EnumCreatureAttribute getCreatureAttribute() {
        return EnumCreatureAttribute.UNDEAD;
    }

    @Override
    public boolean getCanSpawnHere() {
        int i = MathHelper.floor_double(this.getEntityBoundingBox().minY);
        if (i <= 62) {
            return false;
        } else {
            return super.getCanSpawnHere();
        }
    }
}