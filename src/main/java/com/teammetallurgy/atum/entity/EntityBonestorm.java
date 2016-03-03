package com.teammetallurgy.atum.entity;

import com.teammetallurgy.atum.entity.projectile.EntitySmallBone;
import com.teammetallurgy.atum.items.AtumItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityBonestorm extends EntityMob {
    private float heightOffset = 0.2F;
    private int heightOffsetUpdateTime;
    private int timer;

    public EntityBonestorm(World world) {
        super(world);
        this.isImmuneToFire = true;
        this.experienceValue = 8;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(2.0D);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(16, new Byte((byte) 0));
    }

    @Override
    protected String getLivingSound() {
        return "mob.horse.skeleton.idle";
    }

    @Override
    protected String getHurtSound() {
        return "mob.skeleton.hurt";
    }

    @Override
    protected String getDeathSound() {
        return "mob.horse.skeleton.death";
    }

    @Override
    public void onLivingUpdate() {
        if (!this.worldObj.isRemote) {
            if (this.isWet()) {
                this.attackEntityFrom(DamageSource.drown, 1.0F);
            }

            --this.heightOffsetUpdateTime;

            if (this.heightOffsetUpdateTime <= 0) {
                this.heightOffsetUpdateTime = 100;
                this.heightOffset = 0.5F + (float) this.rand.nextGaussian() * 3.0F;
            }

            if (this.getEntityToAttack() != null && this.getEntityToAttack().posY + (double) this.getEntityToAttack().getEyeHeight() > this.posY + (double) this.getEyeHeight() + (double) this.heightOffset) {
                this.motionY += (0.30000001192092896D - this.motionY) * 0.23000001192092896D;
            }
        }

        if (!this.onGround && this.motionY < 0.0D) {
            this.motionY *= 0.1D;
        }

        super.onLivingUpdate();
    }

    @Override
    public EnumCreatureAttribute getCreatureAttribute() {
        return EnumCreatureAttribute.UNDEAD;
    }

    @Override
    protected void attackEntity(Entity entity, float p_70785_2_) {
        if (this.attackTime <= 0 && p_70785_2_ < 2.0F && entity.boundingBox.maxY > this.boundingBox.minY && entity.boundingBox.minY < this.boundingBox.maxY) {
            this.attackTime = 20;
            this.attackEntityAsMob(entity);
        } else if (p_70785_2_ < 30.0F) {
            double d0 = entity.posX - this.posX;
            double d1 = entity.boundingBox.minY + (double) (entity.height / 2.0F) - (this.posY + (double) (this.height / 2.0F));
            double d2 = entity.posZ - this.posZ;

            if (this.attackTime == 0) {
                ++this.timer;

                if (this.timer == 1) {
                    this.attackTime = 30;
                    this.func_70844_e(true);
                } else if (this.timer <= 4) {
                    this.attackTime = 3;
                } else {
                    this.attackTime = 50;
                    this.timer = 0;
                    this.func_70844_e(false);
                }

                if (this.timer > 1) {
                    float f1 = MathHelper.sqrt_float(p_70785_2_) * 0.5F;
                    this.worldObj.playSoundAtEntity(entity, "mob.skeleton.hurt", 0.7F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);

                    for (int i = 0; i < 1; ++i) {
                        EntitySmallBone entitySmallBone = new EntitySmallBone(this.worldObj, this, d0 + this.rand.nextGaussian() * (double) f1, d1, d2 + this.rand.nextGaussian() * (double) f1);
                        entitySmallBone.posY = this.posY + (double) (this.height / 2.0F) + 0.5D;
                        this.worldObj.spawnEntityInWorld(entitySmallBone);
                    }
                }
            }
            this.rotationYaw = (float) (Math.atan2(d2, d0) * 180.0D / Math.PI) - 90.0F;
            this.hasAttacked = true;
        }
    }

    @Override
    protected void fall(float fall) {
    }

    @Override
    protected void dropFewItems(boolean recentlyHit, int looting) {
        if (recentlyHit) {
            int j = rand.nextInt(2) + 1 + rand.nextInt(1 + looting);
            for (int k = 0; k < j; ++k) {
                this.dropItem(AtumItems.DUSTY_BONE, 1);
            }
        }
    }

    public void func_70844_e(boolean p_70844_1_) {
        byte b0 = this.dataWatcher.getWatchableObjectByte(16);

        if (p_70844_1_) {
            b0 = (byte) (b0 | 1);
        } else {
            b0 &= -2;
        }

        this.dataWatcher.updateObject(16, Byte.valueOf(b0));
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