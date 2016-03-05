package com.teammetallurgy.atum.entity;

import com.teammetallurgy.atum.items.AtumItems;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;

public class EntityBarbarian extends EntityMob {

    public EntityBarbarian(World world) {
        super(world);
        this.experienceValue = 9;

        this.setCurrentItemOrArmor(0, new ItemStack(AtumItems.GREATSWORD));
        //this.enchantEquipment(); //TODO

        for (int i = 0; i < this.equipmentDropChances.length; ++i) {
            this.equipmentDropChances[i] = 0F;
        }
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(30.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.53000000417232513D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0D);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(10.0D);
    }

    @Override
    public boolean getCanSpawnHere() {
        BlockPos pos = new BlockPos(this.posX, this.getEntityBoundingBox().minY, this.posZ);
        if (pos.getY() <= 62) {
            return false;
        } else {
            return this.worldObj.canBlockSeeSky(pos) && this.isValidLightLevel() &&
                    this.worldObj.checkNoEntityCollision(this.getEntityBoundingBox(), this) && this.worldObj.getCollidingBoundingBoxes(this, this.getEntityBoundingBox()).isEmpty() && !this.worldObj.isAnyLiquid(this.getEntityBoundingBox());
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

    @Override
    public EnumCreatureAttribute getCreatureAttribute() {
        return EnumCreatureAttribute.UNDEFINED;
    }

    @Override
    protected void dropFewItems(boolean recentlyHit, int looting) {
        if (rand.nextInt(20) == 0) {
            int damage = (int) (AtumItems.GREATSWORD.getMaxDamage() - rand.nextInt(AtumItems.GREATSWORD.getMaxDamage()) * 0.5 + 20);
            this.entityDropItem(new ItemStack(AtumItems.GREATSWORD, 1, damage), 0.0F);
        }

        if (rand.nextInt(4) == 0) {
            int amount = rand.nextInt(2) + 1;
            this.dropItem(Items.gold_nugget, amount);
        }
    }

    @Override
    public boolean attackEntityAsMob(Entity entity) {
        if (this.getEquipmentInSlot(0).getItem() == AtumItems.GREATSWORD) {
            float f = (float) this.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue();
            float i = 1.2F;

            if (entity instanceof EntityLivingBase) {
                f += EnchantmentHelper.func_152377_a(this.getHeldItem(), ((EntityLivingBase) entity).getCreatureAttribute());
                i += EnchantmentHelper.getKnockbackModifier(this);
            }

            boolean flag = entity.attackEntityFrom(DamageSource.causeMobDamage(this), f);
            if (flag) {
                if (i > 0) {
                    entity.addVelocity((double) (-MathHelper.sin(this.rotationYaw * (float) Math.PI / 180.0F) * i * 0.5F), 0.1D, (double) (MathHelper.cos(this.rotationYaw * (float) Math.PI / 180.0F) * i * 0.5F));
                    this.motionX *= 0.6D;
                    this.motionZ *= 0.6D;
                }

                int j = EnchantmentHelper.getFireAspectModifier(this);
                if (j > 0) {
                    entity.setFire(j * 4);
                }

                this.applyEnchantments(this, entity);

            }
            return flag;
        }
        return super.attackEntityAsMob(entity);
    }
}