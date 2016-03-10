package com.teammetallurgy.atum.entity;

import com.teammetallurgy.atum.items.AtumItems;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

public class EntityGhost extends EntityMob {
    private int cycleHeight = 0;
    private int cycleTime = 100;

    public EntityGhost(World world) {
        super(world);
        this.isImmuneToFire = true;
        this.experienceValue = 6;
        //this.tasks.addTask(4, new EntityGhost.AIGhostAttack(this, EntityPlayer.class)); //TODO
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));

        cycleTime = (int) ((Math.random() * 40) + 80);
        cycleHeight = (int) (Math.random() * cycleTime);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(16, new Byte((byte) 0));
    }

    @Override
    public EnumCreatureAttribute getCreatureAttribute() {
        return EnumCreatureAttribute.UNDEAD;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (!this.worldObj.isRemote) {
            this.setBesideClimbableBlock(this.isCollidedHorizontally);
        }
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(10.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(1.04D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(2.0D);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(10.0D);
    }

    @Override
    public void onLivingUpdate() {
        cycleHeight = (cycleHeight + 1) % cycleTime;

        super.onLivingUpdate();
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

    @Override
    protected void jump() {
        this.motionY = 0.56999998688697815D;

        if (this.isPotionActive(Potion.jump)) {
            this.motionY += (double) ((float) (this.getActivePotionEffect(Potion.jump).getAmplifier() + 1) * 0.1F);
        }

        if (this.isSprinting()) {
            float f = this.rotationYaw * 0.017453292F;
            this.motionX -= (double) (MathHelper.sin(f) * 0.2F);
            this.motionZ += (double) (MathHelper.cos(f) * 0.2F);
        }

        this.isAirBorne = true;
        ForgeHooks.onLivingJump(this);
    }

    @Override
    public void fall(float distance, float damageMultiplier) {
    }

    @Override
    protected void dropFewItems(boolean recentlyHit, int looting) {
        if (this.rand.nextInt(4) == 0) {
            int amount = rand.nextInt(3) + 1;
            this.dropItem(AtumItems.ECTOPLASM, amount);
        }
    }

    @Override
    public boolean isOnLadder() {
        return this.isBesideClimbableBlock();
    }

    public boolean isBesideClimbableBlock() {
        return (this.dataWatcher.getWatchableObjectByte(16) & 1) != 0;
    }

    public void setBesideClimbableBlock(boolean besideClimbableBlock) {
        byte climb = this.dataWatcher.getWatchableObjectByte(16);

        if (besideClimbableBlock) {
            climb = (byte) (climb | 1);
        } else {
            climb = (byte) (climb & -2);
        }

        this.dataWatcher.updateObject(16, Byte.valueOf(climb));
    }

    /*static class AIGhostAttack extends EntityAIAttackOnCollide {
        private EntityGhost ghost;
        private int attackTime;

        public AIGhostAttack(EntityGhost entityGhost, Class <? extends Entity> targetClass) {
            super(entityGhost, targetClass, 1.0D, true);
            this.setMutexBits(3);
        }

        @Override
        public boolean shouldExecute() {
            EntityLivingBase entitylivingbase = this.ghost.getAttackTarget();
            return entitylivingbase != null && entitylivingbase.isEntityAlive();
        }

        @Override
        public void startExecuting() {
            this.attackTime = 0;
        }

        @Override
        public void updateTask() {
            --this.attackTime;
            EntityLivingBase livingBase = this.ghost.getAttackTarget();
            double distanceSqToEntity = this.ghost.getDistanceSqToEntity(livingBase);

            if (distanceSqToEntity < 4.0D) {
                if (this.attackTime <= 0) {
                    this.attackTime = 20;
                    this.ghost.attackEntityAsMob(livingBase);
                }
                if (Math.random() > 0.75 && livingBase instanceof EntityLiving) {
                    EntityLiving entityLiving = (EntityLiving) livingBase;
                    entityLiving.addPotionEffect(new PotionEffect(2, 100, 2));
                }
            }
            super.updateTask();
        }
    }*/
}