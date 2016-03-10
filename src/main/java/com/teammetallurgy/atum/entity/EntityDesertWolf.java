package com.teammetallurgy.atum.entity;

import com.google.common.base.Predicate;
import com.teammetallurgy.atum.blocks.AtumBlocks;
import com.teammetallurgy.atum.items.AtumItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityDesertWolf extends EntityTameable { //TODO
    private float headRotationCourse;
    private float headRotationCourseOld;
    private boolean isWet;
    private boolean isShaking;
    private float timeWolfIsShaking;
    private float prevTimeWolfIsShaking;

    public EntityDesertWolf(World world) {
        super(world);
        this.setAngry(true);
        this.setTamed(false);
        this.experienceValue = 6;

        this.setSize(0.6F, 0.8F);
        ((PathNavigateGround) this.getNavigator()).setAvoidsWater(true);
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, this.aiSit);
        this.tasks.addTask(3, new EntityAILeapAtTarget(this, 0.4F));
        this.tasks.addTask(4, new EntityAIAttackOnCollide(this, 1.0D, true));
        this.tasks.addTask(5, new EntityAIFollowOwner(this, 1.0D, 10.0F, 2.0F));
        this.tasks.addTask(6, new EntityAIMate(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
        //this.tasks.addTask(8, new EntityAIBeg(this, 8.0F));
        this.tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(9, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
        this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
        this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true, new Class[0]));
        this.targetTasks.addTask(4, new EntityAITargetNonTamed<EntityLivingBase>(this, EntityLivingBase.class, false, new Predicate<Entity>() {
            public boolean apply(Entity entity) {
                return entity instanceof EntitySheep || entity instanceof EntityRabbit || entity instanceof EntityPlayer;
            }
        }));
        //this.targetTasks.addTask(5, new EntityAINearestAttackableTarget(this, EntitySkeleton.class, false));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.40000001192092896D);

        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(20.0D);
        if (this.isTamed()) {
            this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0D);
        } else {
            this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(8.0D);
        }

        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.attackDamage);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(2.0D);
    }

    @Override
    public void setAttackTarget(EntityLivingBase entityLivingBase) {
        super.setAttackTarget(entityLivingBase);

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

        if (!this.worldObj.isRemote && this.isWet && !this.isShaking && !this.hasPath() && this.onGround) {
            this.isShaking = true;
            this.timeWolfIsShaking = 0.0F;
            this.prevTimeWolfIsShaking = 0.0F;
            this.worldObj.setEntityState(this, (byte) 8);
        }

        if (!this.worldObj.isRemote && this.getAttackTarget() == null && this.isAngry()) {
            this.setAngry(false);
        }
    }

    @SideOnly(Side.CLIENT)
    public boolean isWolfWet()
    {
        return this.isWet;
    }

    @SideOnly(Side.CLIENT)
    public float getShadingWhileWet(float shading) {
        return 0.75F + (this.prevTimeWolfIsShaking + (this.timeWolfIsShaking - this.prevTimeWolfIsShaking) * shading) / 2.0F * 0.25F;
    }

    @SideOnly(Side.CLIENT)
    public float getShakeAngle(float p_70923_1_, float p_70923_2_) {
        float f = (this.prevTimeWolfIsShaking + (this.timeWolfIsShaking - this.prevTimeWolfIsShaking) * p_70923_1_ + p_70923_2_) / 1.8F;

        if (f < 0.0F) {
            f = 0.0F;
        } else if (f > 1.0F) {
            f = 1.0F;
        }

        return MathHelper.sin(f * (float) Math.PI) * MathHelper.sin(f * (float) Math.PI * 11.0F) * 0.15F * (float) Math.PI;
    }

    @SideOnly(Side.CLIENT)
    public float getInterestedAngle(float p_70917_1_) {
        return (this.headRotationCourseOld + (this.headRotationCourse - this.headRotationCourseOld) * p_70917_1_) * 0.15F * (float) Math.PI;
    }

    @SideOnly(Side.CLIENT)
    public float getTailRotation() {
        return this.isAngry() ? 1.5393804F : (this.isTamed() ? (0.55F - (20.0F - this.dataWatcher.getWatchableObjectFloat(18)) * 0.02F) * (float) Math.PI : ((float) Math.PI / 5F));
    }

    public boolean isAngry()
    {
        return (this.dataWatcher.getWatchableObjectByte(16) & 2) != 0;
    }

    public void setAngry(boolean angry) {
        byte b0 = this.dataWatcher.getWatchableObjectByte(16);

        if (angry) {
            this.dataWatcher.updateObject(16, Byte.valueOf((byte) (b0 | 2)));
        } else {
            this.dataWatcher.updateObject(16, Byte.valueOf((byte) (b0 & -3)));
        }
    }

    public EnumDyeColor getCollarColor() {
        return EnumDyeColor.byDyeDamage(this.dataWatcher.getWatchableObjectByte(20) & 15);
    }

    public void setCollarColor(EnumDyeColor collarcolor) {
        this.dataWatcher.updateObject(20, Byte.valueOf((byte) (collarcolor.getDyeDamage() & 15)));
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
    public void setTamed(boolean tamed) {
        super.setTamed(tamed);

        if (tamed) {
            this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0D);
        } else {
            this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(8.0D);
        }

        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0D);
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