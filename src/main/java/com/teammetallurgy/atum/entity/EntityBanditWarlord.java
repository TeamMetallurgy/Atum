package com.teammetallurgy.atum.entity;

import com.teammetallurgy.atum.items.AtumItems;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class EntityBanditWarlord extends EntityBanditBase {

    public EntityBanditWarlord(World world) {
        super(world);
        this.experienceValue = 16;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(250.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.53000000417232513D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(10.0D);
    }

    @Override
    protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty) {
        super.setEquipmentBasedOnDifficulty(difficulty);
        this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(AtumItems.SCIMITAR));
    }

    @Override
    protected void setEnchantmentBasedOnDifficulty(DifficultyInstance difficulty) {
        float f = difficulty.getClampedAdditionalDifficulty();

        if (this.getHeldItemMainhand() != null && this.rand.nextFloat() < 0.25F * f) {
            EnchantmentHelper.addRandomEnchantment(this.rand, this.getHeldItemMainhand(), (int) (5.0F + f * (float) this.rand.nextInt(6)), false);
        }

        for (EntityEquipmentSlot entityequipmentslot : EntityEquipmentSlot.values()) {
            if (entityequipmentslot.getSlotType() == EntityEquipmentSlot.Type.ARMOR) {
                ItemStack itemstack = this.getItemStackFromSlot(entityequipmentslot);

                if (itemstack != null && this.rand.nextFloat() < 0.5F * f) {
                    EnchantmentHelper.addRandomEnchantment(this.rand, itemstack, (int) (5.0F + f * (float) this.rand.nextInt(18)), false);
                }
            }
        }
    }

    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData livingdata) {
        livingdata = super.onInitialSpawn(difficulty, livingdata);

        this.setEquipmentBasedOnDifficulty(difficulty);
        this.setEnchantmentBasedOnDifficulty(difficulty);

        for (int i = 0; i < this.inventoryArmorDropChances.length; ++i) {
            this.inventoryArmorDropChances[i] = 0.05F;
        }

        this.setCanPickUpLoot(this.rand.nextFloat() < 0.55F * difficulty.getClampedAdditionalDifficulty());

        return livingdata;
    }

    @Override
    public int getMaxSpawnedInChunk() {
        return 1;
    }

    @Override
    protected void dropFewItems(boolean recentlyHit, int looting) {
        if (rand.nextInt(20) == 0) {
            int damage = (int) (AtumItems.SCIMITAR.getMaxDamage() - rand.nextInt(AtumItems.SCIMITAR.getMaxDamage()) * 0.5 + 20);
            this.entityDropItem(new ItemStack(AtumItems.SCIMITAR, 1, damage), 0.0F);
        }

        if (rand.nextInt(4) == 0) {
            int amount = rand.nextInt(3) + 3;
            this.dropItem(Items.GOLD_NUGGET, amount);
        }

        if (rand.nextInt(4) == 0) {
            int choice = rand.nextInt(4);
            if (choice == 0) {
                this.dropItem(AtumItems.WANDERER_HELMET, 1);
            } else if (choice == 1) {
                this.dropItem(AtumItems.WANDERER_CHEST, 1);
            } else if (choice == 2) {
                this.dropItem(AtumItems.WANDERER_LEGS, 1);
            } else if (choice == 3) {
                this.dropItem(AtumItems.WANDERER_BOOTS, 1);
            }
        }
    }
}