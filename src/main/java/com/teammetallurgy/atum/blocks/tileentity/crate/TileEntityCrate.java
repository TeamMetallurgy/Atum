package com.teammetallurgy.atum.blocks.tileentity.crate;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityLockable;

public class TileEntityCrate extends TileEntityLockable implements IInventory {
    private ItemStack[] inventory = new ItemStack[getSizeInventory()];
    private String crateName;

    @Override
    public int getSizeInventory() {
        return 27;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        if (index < 0 || index >= inventory.length) {
            return null;
        }
        return inventory[index];
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        if (index < 0 && index >= inventory.length || inventory[index] == null) {
            return null;
        }

        ItemStack returnedStack;
        if (inventory[index].stackSize <= count) {
            returnedStack = inventory[index];
            inventory[index] = null;
        } else {
            returnedStack = inventory[index].splitStack(count);
            if (inventory[index].stackSize <= 0) {
                inventory[index] = null;
            }
        }
        return returnedStack;
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        ItemStack returnedStack = null;

        if (index >= 0 && index < inventory.length && inventory[index] != null) {
            returnedStack = inventory[index];
            inventory[index] = null;
        }
        return returnedStack;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        if (index < 0 || index >= inventory.length)
            return;

        inventory[index] = stack;
        if (stack != null && stack.stackSize > getInventoryStackLimit()) {
            stack.stackSize = getInventoryStackLimit();
        }
    }

    @Override
    public String getName() {
        return this.hasCustomName() ? this.crateName : getDefaultName(); //TODO fix custom inventory name
    }

    private String getDefaultName() {
        int meta = getBlockMetadata();
        String name = "container.crate.";
        switch (meta) {
            case 0:
                name += "palm";
                break;
            case 1:
                name += "deadwood";
                break;
            default:
                name += "invaild";
        }

        return name ;
    }

    @Override
    public boolean hasCustomName() {
        return this.crateName != null && this.crateName.length() > 0;
    }

    public void setCustomName(String name) {
        this.crateName = name;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return this.worldObj.getTileEntity(this.pos) != this ? false : player.getDistanceSq((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D, (double) this.pos.getZ() + 0.5D) <= 64.0D;
    }

    @Override
    public void openInventory(EntityPlayer player) {
    }

    @Override
    public void closeInventory(EntityPlayer player) {
    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return true;
    }

    @Override
    public void readFromNBT(NBTTagCompound tagCompound) {
        super.readFromNBT(tagCompound);

        NBTTagList nbttaglist = tagCompound.getTagList("Items", 10);
        this.inventory = new ItemStack[this.getSizeInventory()];

        if (tagCompound.hasKey("CustomName", 8)) {
            this.crateName = tagCompound.getString("CustomName");
        }

        for (int i = 0; i < nbttaglist.tagCount(); ++i) {
            NBTTagCompound nbttagcompound = nbttaglist.getCompoundTagAt(i);
            int j = nbttagcompound.getByte("Slot") & 255;

            if (j >= 0 && j < this.inventory.length) {
                this.inventory[j] = ItemStack.loadItemStackFromNBT(nbttagcompound);
            }
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound tagCompound) {
        super.writeToNBT(tagCompound);

        NBTTagList inventoryNbt = new NBTTagList();
        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i] != null) {
                NBTTagCompound slotCompound = new NBTTagCompound();
                slotCompound.setByte("Slot", (byte) i);
                inventory[i].writeToNBT(slotCompound);
                inventoryNbt.appendTag(slotCompound);
            }
        }

        tagCompound.setTag("Items", inventoryNbt);

        if (this.hasCustomName()) {
            tagCompound.setString("CustomName", this.crateName);
        }
    }

    @Override
    public String getGuiID() {
        return "minecraft:chest";
    }

    @Override
    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer player) {
        return new ContainerCrate(playerInventory, this);
    }

    @Override
    public int getField(int id) {
        return 0;
    }

    @Override
    public void setField(int id, int value) {
    }

    @Override
    public int getFieldCount() {
        return 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i < inventory.length; ++i) {
            inventory[i] = null;
        }
    }
}