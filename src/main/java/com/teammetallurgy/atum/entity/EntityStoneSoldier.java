package com.teammetallurgy.atum.entity;

import com.teammetallurgy.atum.items.AtumItems;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityStoneSoldier extends EntityStone {

    public EntityStoneSoldier(World world) {
        super(world);
        this.isImmuneToFire = true;
        this.experienceValue = 8;

        this.setCurrentItemOrArmor(0, new ItemStack(AtumItems.STONE_SOLDIER_SWORD));

        for (int i = 0; i < this.equipmentDropChances.length; ++i) {
            this.equipmentDropChances[i] = 0F;
        }
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(80.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.53000000417232513D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0D);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(10.0D);
    }

    @Override
    protected String getHurtSound()
    {
        return "step.stone";
    }

    @Override
    public boolean getCanSpawnHere() {
        int i = MathHelper.floor_double(this.getEntityBoundingBox().minY);
        if (i >= 62) {
            return false;
        } else {
            return super.getCanSpawnHere();
        }
    }

    @Override
    public EnumCreatureAttribute getCreatureAttribute() {
        return EnumCreatureAttribute.UNDEFINED;
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (super.attackEntityFrom(source, amount)) {
            if (source.getEntity() != null) {
                Entity entity = source.getEntity();
                int j = 0;
                if (entity instanceof EntityLiving) {
                    j += EnchantmentHelper.getKnockbackModifier(this);

                    if (j > 0) {
                        this.motionX /= 0.6D;
                        this.motionZ /= 0.6D;
                        this.addVelocity((double) (MathHelper.sin(entity.rotationYaw * (float) Math.PI / 180.0F) * (float) j * 0.5F), -0.1D, (double) (-MathHelper.cos(entity.rotationYaw * (float) Math.PI / 180.0F) * (float) j * 0.5F));
                    }
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public void knockBack(Entity entity, float par2, double par3, double par5) {
        this.isAirBorne = true;
        float f = MathHelper.sqrt_double(par3 * par3 + par5 * par5);
        float f1 = 0.2F;
        this.motionX /= 2.0D;
        this.motionY /= 2.0D;
        this.motionZ /= 2.0D;
        this.motionX -= par3 / (double) f * (double) f1;
        this.motionZ -= par5 / (double) f * (double) f1;

        if (this.motionY > 0.4000000059604645D) {
            this.motionY = 0.4000000059604645D;
        }
    }

    @Override
    protected void dropFewItems(boolean recentlyHit, int looting) {
        if (this.rand.nextInt(4) == 0) {
            int amount = rand.nextInt(2) + 1;
            this.dropItem(AtumItems.STONE_CHUNK, amount);
        }
    }
}