package com.teammetallurgy.atum.entity;

import com.teammetallurgy.atum.entity.ai.EntityAIAttackRangedBowBandit;
import com.teammetallurgy.atum.items.AtumItems;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class EntityNomad extends EntityBanditBase implements IRangedAttackMob {
    protected static final DataParameter<Boolean> canShoot = EntityDataManager.createKey(EntityBanditBase.class, DataSerializers.BOOLEAN);
    private EntityAIAttackRangedBowBandit aiArrowAttack = new EntityAIAttackRangedBowBandit(this, 1.0D, 20, 15.0F);
    private final EntityAIAttackMelee aiAttackOnCollide = new EntityAIAttackMelee(this, 1.2D, false) {
        @Override
        public void resetTask() {
            super.resetTask();
            EntityNomad.this.startShooting(false);
        }

        @Override
        public void startExecuting() {
            super.startExecuting();
            EntityNomad.this.startShooting(true);
        }
    };

    public EntityNomad(World world) {
        super(world);
        this.experienceValue = 6;

        this.setCombatTask();
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(25.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(10.0D);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(canShoot, false);
    }

    @Override
    protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty) {
        super.setEquipmentBasedOnDifficulty(difficulty);
        this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(AtumItems.SHORT_BOW));
    }

    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData livingdata) {
        livingdata = super.onInitialSpawn(difficulty, livingdata);

        this.tasks.addTask(4, this.aiArrowAttack);
        this.setEquipmentBasedOnDifficulty(difficulty);
        this.setEnchantmentBasedOnDifficulty(difficulty);

        this.setCanPickUpLoot(this.rand.nextFloat() < 0.55F * difficulty.getClampedAdditionalDifficulty());

        return livingdata;
    }

    public void setCombatTask() {
        if (this.worldObj != null && !this.worldObj.isRemote) {
            this.tasks.removeTask(this.aiAttackOnCollide);
            this.tasks.removeTask(this.aiArrowAttack);
            ItemStack itemstack = this.getHeldItemMainhand();

            if (itemstack != null && itemstack.getItem() == AtumItems.SHORT_BOW) {
                this.tasks.addTask(4, this.aiArrowAttack);
            } else {
                this.tasks.addTask(4, this.aiAttackOnCollide);
            }
        }
    }

    @Override
    public void attackEntityWithRangedAttack(EntityLivingBase target, float damage) {
        EntityArrow entityarrow = new EntityTippedArrow(this.worldObj, this);
        double d0 = target.posX - this.posX;
        double d1 = target.getEntityBoundingBox().minY + (double) (target.height / 3.0F) - entityarrow.posY;
        double d2 = target.posZ - this.posZ;
        double d3 = (double) MathHelper.sqrt_double(d0 * d0 + d2 * d2);
        entityarrow.setThrowableHeading(d0, d1 + d3 * 0.20000000298023224D, d2, 1.6F, (float) (14 - this.worldObj.getDifficulty().getDifficultyId() * 4));
        int i = EnchantmentHelper.getMaxEnchantmentLevel(Enchantments.power, this);
        int j = EnchantmentHelper.getMaxEnchantmentLevel(Enchantments.punch, this);
        entityarrow.setDamage((double) (damage * 2.0F) + this.rand.nextGaussian() * 0.25D + (double) ((float) this.worldObj.getDifficulty().getDifficultyId() * 0.11F));

        if (i > 0) {
            entityarrow.setDamage(entityarrow.getDamage() + (double) i * 0.5D + 0.5D);
        }

        if (j > 0) {
            entityarrow.setKnockbackStrength(j);
        }

        if (EnchantmentHelper.getMaxEnchantmentLevel(Enchantments.flame, this) > 0) {
            entityarrow.setFire(100);
        }

        this.playSound(SoundEvents.entity_arrow_shoot, 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
        this.worldObj.spawnEntityInWorld(entityarrow);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);

        this.setCombatTask();
    }

    @Override
    public void setItemStackToSlot(EntityEquipmentSlot slot, ItemStack stack) {
        super.setItemStackToSlot(slot, stack);

        if (!this.worldObj.isRemote && slot == EntityEquipmentSlot.MAINHAND) {
            this.setCombatTask();
        }
    }

    @Override
    public float getEyeHeight() {
        return 1.74F;
    }

    @Override
    public double getYOffset() {
        return -0.35D;
    }

    @Override
    public void startShooting(boolean shouldShoot) {
        this.dataManager.set(canShoot, shouldShoot);
    }

    @Override
    protected void dropFewItems(boolean recentlyHit, int looting) {
        if (rand.nextInt(20) == 0) {
            int damage = (int) (AtumItems.SHORT_BOW.getMaxDamage() - rand.nextInt(AtumItems.SHORT_BOW.getMaxDamage()) * 0.5 + 20);
            this.entityDropItem(new ItemStack(AtumItems.SHORT_BOW, 1, damage), 0.0F);
        }

        if (rand.nextInt(10) == 0) {
            int amount = rand.nextInt(2) + 1;
            this.dropItem(Items.gold_nugget, amount);
        }

        if (rand.nextInt(4) == 0) {
            int amount = rand.nextInt(3) + 1;
            this.dropItem(Items.arrow, amount);
        }
    }
}