package com.teammetallurgy.atum.blocks.tileentity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.Vec3;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class TileEntityBurningTrap extends TileEntity implements ITickable {

    @Override
    public void update() {
        EntityPlayer p = super.worldObj.getClosestPlayer((double) super.xCoord, (double) super.yCoord, (double) super.zCoord, 4.0D);
        byte range = 1;
        int xMin = super.xCoord;
        int xMax = super.xCoord + range;
        int yMin = super.yCoord;
        int yMax = super.yCoord + range;
        int zMin = super.zCoord;
        int zMax = super.zCoord + range;
        EnumFacing facing = EnumFacing.getFront(super.worldObj.getBlockMetadata(super.xCoord, super.yCoord, super.zCoord));
        xMin += facing.getFrontOffsetX() * range;
        xMax += facing.getFrontOffsetX() * range;
        yMin += facing.getFrontOffsetY() * range;
        yMax += facing.getFrontOffsetY() * range;
        zMin += facing.getFrontOffsetZ() * range;
        zMax += facing.getFrontOffsetZ() * range;
        AxisAlignedBB bb = AxisAlignedBB.getBoundingBox((double) xMin, (double) yMin, (double) zMin, (double) xMax, (double) yMax, (double) zMax);
        List list = super.worldObj.getEntitiesWithinAABB(EntityMob.class, bb);
        if (p != null && bb.isVecInside(Vec3.createVectorHelper(p.posX, p.posY + 0.5D, p.posZ))) {
            p.setFire(2);
            this.spawnFlames();
        }

        Iterator i = list.iterator();

        while (i.hasNext()) {
            Entity e = (Entity) i.next();
            if (e instanceof EntityLiving) {
                e.setFire(2);
            }
        }

    }

    public void spawnFlames() {
        Random par5Random = new Random();
        int l = super.worldObj.getBlockMetadata(super.xCoord, super.yCoord, super.zCoord);
        float f = (float) super.xCoord + 0.5F;
        float f1 = (float) super.yCoord + 0.1875F + par5Random.nextFloat() * 10.0F / 16.0F;
        float f2 = (float) super.zCoord + 0.5F;
        float f3 = 0.52F;
        float f4 = par5Random.nextFloat() * 0.6F - 0.3F;
        double mx = par5Random.nextDouble() * 0.08D - 0.04D;
        double my = par5Random.nextDouble() * 0.08D - 0.04D;
        double mz = par5Random.nextDouble() * 0.08D - 0.04D;
        if (l == 4) {
            super.worldObj.spawnParticle("smoke", (double) (f - f3), (double) f1, (double) (f2 + f4), mx - 0.1D, my, mz);
            super.worldObj.spawnParticle("flame", (double) (f - f3), (double) f1, (double) (f2 + f4), mx - 0.1D, my, mz);
        } else if (l == 5) {
            super.worldObj.spawnParticle("smoke", (double) (f + f3), (double) f1, (double) (f2 + f4), mx + 0.1D, my, mz);
            super.worldObj.spawnParticle("flame", (double) (f + f3), (double) f1, (double) (f2 + f4), mx + 0.1D, my, mz);
        } else if (l == 2) {
            super.worldObj.spawnParticle("smoke", (double) (f + f4), (double) f1, (double) (f2 - f3), mx, my, mz - 0.1D);
            super.worldObj.spawnParticle("flame", (double) (f + f4), (double) f1, (double) (f2 - f3), mx, my, mz - 0.1D);
        } else if (l == 3) {
            super.worldObj.spawnParticle("smoke", (double) (f + f4), (double) f1, (double) (f2 + f3), mx, my, mz + 0.1D);
            super.worldObj.spawnParticle("flame", (double) (f + f4), (double) f1, (double) (f2 + f3), mx, my, mz + 0.1D);
        }

    }
}
