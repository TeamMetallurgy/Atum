package com.teammetallurgy.atum.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.init.SoundEvents;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;

public class EntityBanditBase extends EntityMob {
    protected static final DataParameter<Boolean> canShoot = EntityDataManager.createKey(EntityBanditBase.class, DataSerializers.BOOLEAN);
    protected boolean haveBow = false;

    public EntityBanditBase(World world) {
        super(world);
    }

    @Override
    public EnumCreatureAttribute getCreatureAttribute() {
        return EnumCreatureAttribute.UNDEFINED;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.entity_player_breath;
    }

    @Override
    protected SoundEvent getHurtSound() {
        return SoundEvents.entity_player_hurt;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.entity_player_death;
    }

    @Override
    public boolean getCanSpawnHere() {
        BlockPos pos = new BlockPos(this.posX, this.getEntityBoundingBox().minY, this.posZ);
        if (pos.getY() <= 62) {
            return false;
        } else {
            return this.worldObj.canBlockSeeSky(pos) && this.worldObj.getDifficulty() != EnumDifficulty.PEACEFUL && this.isValidLightLevel() &&
                    this.worldObj.checkNoEntityCollision(this.getEntityBoundingBox()) && this.worldObj.getCollisionBoxes(this, this.getEntityBoundingBox()).isEmpty() && !this.worldObj.isAnyLiquid(this.getEntityBoundingBox());
        }
    }

    @Override
    protected boolean isValidLightLevel() {
        BlockPos pos = new BlockPos(this.posX, this.getEntityBoundingBox().minY, this.posZ);
        int bl = this.worldObj.getLightFor(EnumSkyBlock.BLOCK, pos);
        int light = this.worldObj.getLight(pos);

        if (bl >= 7) {
            return false;
        } else if (light > 8) {
            return true;
        } else {
            return false;
        }
    }

    public void attackEntityWithRangedAttack(EntityLivingBase target, float damage) {
    }

    public void startShooting(boolean shouldShoot) {
        if (haveBow) {
            this.dataManager.set(canShoot, shouldShoot);
        }
    }
}