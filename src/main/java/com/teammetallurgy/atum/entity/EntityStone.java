package com.teammetallurgy.atum.entity;

import net.minecraft.entity.monster.EntityMob;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class EntityStone extends EntityMob {

    public EntityStone(World world) {
        super(world);
    }

    @Override
    public boolean isPotionApplicable(PotionEffect potioneffect) {
        if (potioneffect.getPotionID() == Potion.poison.id) {
            return false;
        }
        return true;
    }
}