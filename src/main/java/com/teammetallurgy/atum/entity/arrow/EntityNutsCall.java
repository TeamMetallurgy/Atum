package com.teammetallurgy.atum.entity.arrow;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityNutsCall extends CustomArrow {
    public ItemStack stack;

    public EntityNutsCall(World world) {
        super(world);
    }

    public EntityNutsCall(World world, EntityLivingBase shooter, float velocity) {
        super(world, shooter, velocity);
    }

    public void setStack(ItemStack stack) {
        this.stack = stack;
    }

    @Override
    protected void entityInit() {
        this.dataWatcher.addObject(16, Byte.valueOf((byte) 0));
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound tagCompound) {
        super.writeEntityToNBT(tagCompound);

        NBTTagCompound stackTag = new NBTTagCompound();
        stack.writeToNBT(stackTag);

        tagCompound.setTag("stack", stackTag);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound tagCompound) {
        super.readEntityFromNBT(tagCompound);

        NBTTagCompound stackTag = tagCompound.getCompoundTag("stack");
        stack = ItemStack.loadItemStackFromNBT(stackTag);
    }

    @Override
    public void onCollideWithPlayer(EntityPlayer entityIn) {
        if (!this.worldObj.isRemote && this.inGround && this.arrowShake <= 0) {
            boolean flag = this.canBePickedUp == 1 || this.canBePickedUp == 2 && entityIn.capabilities.isCreativeMode;

            if (this.canBePickedUp == 1 && !entityIn.inventory.addItemStackToInventory(stack)) {
                flag = false;
            }

            if (flag) {
                this.playSound("random.pop", 0.2F, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
                entityIn.onItemPickup(this, 1);
                this.setDead();
            }
        }
    }
}