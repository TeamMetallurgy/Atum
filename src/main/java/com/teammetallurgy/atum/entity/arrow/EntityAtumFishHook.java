package com.teammetallurgy.atum.entity.arrow;

import com.teammetallurgy.atum.handler.AtumConfig;
import com.teammetallurgy.atum.items.AtumFish;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityAtumFishHook extends EntityFishHook { //TODO Check if it works after the changes I made
    private boolean inGround; //TODO
    private int ticksCatchable; //TODO

    public EntityAtumFishHook(World world, EntityPlayer player) {
        super(world, player);
    }

    @Override
    public int handleHookRetraction() {
        if (this.worldObj.isRemote) {
            return 0;
        } else {
            int i = 0;

            if (this.caughtEntity != null) {
                double d0 = this.angler.posX - this.posX;
                double d2 = this.angler.posY - this.posY;
                double d4 = this.angler.posZ - this.posZ;
                double d6 = (double) MathHelper.sqrt_double(d0 * d0 + d2 * d2 + d4 * d4);
                double d8 = 0.1D;
                this.caughtEntity.motionX += d0 * d8;
                this.caughtEntity.motionY += d2 * d8 + (double) MathHelper.sqrt_double(d6) * 0.08D;
                this.caughtEntity.motionZ += d4 * d8;
                i = 3;
            } else if (this.ticksCatchable > 0) {
                EntityItem entityitem = new EntityItem(this.worldObj, this.posX, this.posY, this.posZ, new ItemStack(Items.fish)); //TODO Figure out if custom getFishingResult() would be better.
                if (worldObj.provider.getDimensionId() == AtumConfig.DIMENSION_ID) {
                    entityitem.setEntityItemStack(AtumFish.getRandomFish());
                }
                double d1 = this.angler.posX - this.posX;
                double d3 = this.angler.posY - this.posY;
                double d5 = this.angler.posZ - this.posZ;
                double d7 = (double) MathHelper.sqrt_double(d1 * d1 + d3 * d3 + d5 * d5);
                double d9 = 0.1D;
                entityitem.motionX = d1 * d9;
                entityitem.motionY = d3 * d9 + (double) MathHelper.sqrt_double(d7) * 0.08D;
                entityitem.motionZ = d5 * d9;
                this.worldObj.spawnEntityInWorld(entityitem);
                this.angler.worldObj.spawnEntityInWorld(new EntityXPOrb(this.angler.worldObj, this.angler.posX, this.angler.posY + 0.5D, this.angler.posZ + 0.5D, this.rand.nextInt(6) + 1));
                i = 1;
            }

            if (this.inGround) {
                i = 2;
            }
            this.setDead();
            this.angler.fishEntity = null;
            return i;
        }
    }
}