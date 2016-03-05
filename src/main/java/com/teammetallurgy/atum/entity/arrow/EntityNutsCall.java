package com.teammetallurgy.atum.entity.arrow;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IThrowableEntity;

public class EntityNutsCall extends CustomArrow implements IProjectile, IThrowableEntity {
    public ItemStack stack;

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
    public Entity getThrower() {
        return shootingEntity;
    }

    @Override
    public void setThrower(Entity entity) {
        shootingEntity = entity;
    }
}