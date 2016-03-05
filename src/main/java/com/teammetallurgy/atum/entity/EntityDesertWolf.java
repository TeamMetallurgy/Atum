package com.teammetallurgy.atum.entity;

import com.teammetallurgy.atum.blocks.AtumBlocks;
import com.teammetallurgy.atum.items.AtumItems;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class EntityDesertWolf extends EntityWolf { //TODO

    public EntityDesertWolf(World world) {
        super(world);
        this.setAngry(true);
        this.setTamed(false);
        this.experienceValue = 6;
    }

    @Override
    protected void applyEntityAttributes() {
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.knockbackResistance);
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.attackDamage);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(2.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.43000000417232513D);

        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(20.0D);
        if (this.isTamed()) {
            this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0D);
        } else {
            this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(8.0D);
        }
    }

    @Override
    public void setAttackTarget(EntityLivingBase entityLivingBase) {
        ((EntityLiving) entityLivingBase).setAttackTarget(entityLivingBase);

        if (entityLivingBase instanceof EntityPlayer) {
            this.setAngry(true);
        }
    }

    @Override
    public boolean getCanSpawnHere() {
        BlockPos pos = new BlockPos(this.posX, this.getEntityBoundingBox().minY, this.posZ);
        if (pos.getY() <= 62) {
            return false;
        } else {
            return this.worldObj.getBlockState(pos.down()) == AtumBlocks.SAND.getDefaultState() && this.worldObj.getLight(pos) > 8 && this.getBlockPathWeight(pos) >= 0.0F && this.worldObj.canBlockSeeSky(pos) &&
                    this.worldObj.checkNoEntityCollision(this.getEntityBoundingBox()) && this.worldObj.getCollidingBoundingBoxes(this, this.getEntityBoundingBox()).isEmpty() && !this.worldObj.isAnyLiquid(this.getEntityBoundingBox());
        }
    }

    @Override
    protected void dropFewItems(boolean recentlyHit, int looting) {
        if (rand.nextInt(4) == 0) {
            int amount = rand.nextInt(2) + 1;
            this.dropItem(AtumItems.WOLF_PELT, amount);
        }
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        if (!this.worldObj.isRemote && this.getAttackTarget() == null && this.isAngry()) {
            this.setAngry(true);
        }
    }

    @Override
    public EntityDesertWolf createChild(EntityAgeable ageable) {
        EntityDesertWolf entityDesertWolf = new EntityDesertWolf(this.worldObj);
        String s = this.getOwnerId();

        if (s != null && s.trim().length() > 0) {
            entityDesertWolf.setOwnerId(s);
            entityDesertWolf.setTamed(true);
        }

        return entityDesertWolf;
    }

    @Override
    public boolean canMateWith(EntityAnimal otherAnimal) {
        if (otherAnimal == this) {
            return false;
        } else if (!this.isTamed()) {
            return false;
        } else if (!(otherAnimal instanceof EntityDesertWolf)) {
            return false;
        } else {
            EntityDesertWolf entityDesertWolf = (EntityDesertWolf) otherAnimal;
            return !entityDesertWolf.isTamed() ? false : (entityDesertWolf.isSitting() ? false : this.isInLove() && entityDesertWolf.isInLove());
        }
    }

    @Override
    public boolean shouldAttackEntity(EntityLivingBase mob, EntityLivingBase player) {
        if (!(mob instanceof EntityCreeper) && !(mob instanceof EntityGhast)) {
            if (mob instanceof EntityWolf) {
                EntityDesertWolf entityDesertWolf = (EntityDesertWolf) mob;

                if (entityDesertWolf.isTamed() && entityDesertWolf.getOwner() == player) {
                    return false;
                }
            }

            return mob instanceof EntityPlayer && player instanceof EntityPlayer && !((EntityPlayer) player).canAttackPlayer((EntityPlayer) mob) ? false : !(mob instanceof EntityHorse) || !((EntityHorse) mob).isTame();
        } else {
            return false;
        }
    }
}