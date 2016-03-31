package com.teammetallurgy.atum.blocks.tileentity.chests;

import com.teammetallurgy.atum.entity.EntityMummy;
import com.teammetallurgy.atum.entity.EntityPharaoh;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.common.FMLCommonHandler;

import java.util.Iterator;
import java.util.List;

public class TileEntityPharaohChest extends TileEntityChest implements IInventory {
    private boolean hasSpawned = false;
    private boolean isOpenable = false;

    @Override
    public void readFromNBT(NBTTagCompound tagCompound) {
        super.readFromNBT(tagCompound);

        this.hasSpawned = tagCompound.getBoolean("spawned");
        this.isOpenable = tagCompound.getBoolean("openable");
    }

    @Override
    public void writeToNBT(NBTTagCompound tagCompound) {
        super.writeToNBT(tagCompound);

        tagCompound.setBoolean("spawned", this.hasSpawned);
        tagCompound.setBoolean("openable", this.isOpenable);
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return !this.isOpenable ? false : super.isUseableByPlayer(player);
    }

    public void setOpenable() {
        this.isOpenable = true;
        this.markDirty();
    }

    public boolean hasSpawned() {
        return this.hasSpawned;
    }

    public void spawn(EntityPlayer player) {
        EntityPharaoh pharaoh = new EntityPharaoh(super.worldObj);
        pharaoh.setPosition((double) this.pos.getX() + 0.5D, (double) (this.pos.getX() + 1), (double) this.pos.getX() + 0.5D);
        pharaoh.link(this.pos.getX(), this.pos.getY(), this.pos.getZ());
        if (!super.worldObj.isRemote) {
            super.worldObj.spawnEntityInWorld(pharaoh);
        }

        pharaoh.spawnExplosionParticle();
        this.hasSpawned = true;
        EntityMummy mummy1 = new EntityMummy(super.worldObj);
        mummy1.setPosition((double) this.pos.getX() + 0.5D, (double) this.pos.getX(), (double) this.pos.getX() - 0.5D);
        if (!super.worldObj.isRemote) {
            super.worldObj.spawnEntityInWorld(mummy1);
        }

        mummy1.spawnExplosionParticle();
        EntityMummy mummy2 = new EntityMummy(super.worldObj);
        mummy2.setPosition((double) this.pos.getX() + 0.5D, (double) this.pos.getX(), (double) this.pos.getX() + 1.5D);
        if (!super.worldObj.isRemote) {
            super.worldObj.spawnEntityInWorld(mummy2);
        }

        mummy2.spawnExplosionParticle();
        if (!super.worldObj.isRemote) {
            List<EntityPlayerMP> players = FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayerList();
            Iterator<EntityPlayerMP> i = players.iterator();

            while (i.hasNext()) {
                EntityPlayer p = i.next();
                p.addChatMessage(new TextComponentString(pharaoh.getName() + " " + I18n.translateToLocal("chat.atum.summonPharaoh") + " " + player.getGameProfile().getName()));
            }
        }

        if (!super.worldObj.isRemote) {
            //super.worldObj.playSound(pharaoh, "Atum.pharaohspawn", 1.0F, 1.0F); //TODO Fix custom sound
        }

    }

    public void setPharaohDespawned() {
        this.hasSpawned = false;
    }
}