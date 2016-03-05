package com.teammetallurgy.atum.blocks.tileentity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class TileEntityBurningTrap extends TileEntity implements ITickable {

    @Override
    public void update() {
        int x = super.getPos().getX();
        int y = super.getPos().getY();
        int z = super.getPos().getZ();

        EntityPlayer player = super.worldObj.getClosestPlayer((double) x, (double) y, (double) z, 4.0D);
        byte range = 1;
        int xMin = x;
        int xMax = x + range;
        int yMin = y;
        int yMax = y + range;
        int zMin = z;
        int zMax = z + range;
        IBlockState state = super.worldObj.getBlockState(super.pos);
        EnumFacing facing = EnumFacing.getFront(state.getBlock().getMetaFromState(state));
        xMin += facing.getFrontOffsetX() * range;
        xMax += facing.getFrontOffsetX() * range;
        yMin += facing.getFrontOffsetY() * range;
        yMax += facing.getFrontOffsetY() * range;
        zMin += facing.getFrontOffsetZ() * range;
        zMax += facing.getFrontOffsetZ() * range;
        AxisAlignedBB bb = new AxisAlignedBB((double) xMin, (double) yMin, (double) zMin, (double) xMax, (double) yMax, (double) zMax);
        List<EntityMob> list = super.worldObj.getEntitiesWithinAABB(EntityMob.class, bb);
        if (player != null && bb.isVecInside(new Vec3(player.posX, player.posY + 0.5D, player.posZ))) {
            player.setFire(2);
            this.spawnFlames();
        }

        Iterator<EntityMob> i = list.iterator();
        while (i.hasNext()) {
            Entity e = i.next();
            if (e instanceof EntityLiving) {
                e.setFire(2);
            }
        }
    }

    public void spawnFlames() {
        Random random = worldObj.rand;
        IBlockState state = super.worldObj.getBlockState(super.pos);
        int meta = state.getBlock().getMetaFromState(state);
        float f = (float) super.pos.getX() + 0.5F;
        float f1 = (float) super.pos.getY() + 0.1875F + random.nextFloat() * 10.0F / 16.0F;
        float f2 = (float) super.pos.getZ() + 0.5F;
        float f3 = 0.52F;
        float f4 = random.nextFloat() * 0.6F - 0.3F;
        double mx = random.nextDouble() * 0.08D - 0.04D;
        double my = random.nextDouble() * 0.08D - 0.04D;
        double mz = random.nextDouble() * 0.08D - 0.04D;
        if (meta == 4) {
            super.worldObj.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, (double) (f - f3), (double) f1, (double) (f2 + f4), mx - 0.1D, my, mz);
            super.worldObj.spawnParticle(EnumParticleTypes.FLAME, (double) (f - f3), (double) f1, (double) (f2 + f4), mx - 0.1D, my, mz);
        } else if (meta == 5) {
            super.worldObj.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, (double) (f + f3), (double) f1, (double) (f2 + f4), mx + 0.1D, my, mz);
            super.worldObj.spawnParticle(EnumParticleTypes.FLAME, (double) (f + f3), (double) f1, (double) (f2 + f4), mx + 0.1D, my, mz);
        } else if (meta == 2) {
            super.worldObj.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, (double) (f + f4), (double) f1, (double) (f2 - f3), mx, my, mz - 0.1D);
            super.worldObj.spawnParticle(EnumParticleTypes.FLAME, (double) (f + f4), (double) f1, (double) (f2 - f3), mx, my, mz - 0.1D);
        } else if (meta == 3) {
            super.worldObj.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, (double) (f + f4), (double) f1, (double) (f2 + f3), mx, my, mz + 0.1D);
            super.worldObj.spawnParticle(EnumParticleTypes.FLAME, (double) (f + f4), (double) f1, (double) (f2 + f3), mx, my, mz + 0.1D);
        }
    }
}