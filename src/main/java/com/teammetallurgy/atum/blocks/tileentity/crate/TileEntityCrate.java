package com.teammetallurgy.atum.blocks.tileentity.crate;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.Constants.NBT;

public class TileEntityCrate extends TileEntity implements IInventory {

    private ItemStack inventory[] = new ItemStack[getSizeInventory()];
    private String inventoryName;
    
    @Override
    public boolean canUpdate() {
        return false;
    }

    @Override
    public int getSizeInventory() { 
        return 27;
    }

    @Override
    public ItemStack getStackInSlot(int slotId) {
        if (slotId < 0 || slotId >= inventory.length)
            return null;
        return inventory[slotId];
    }

    @Override
    public ItemStack decrStackSize(int slotId, int ammout) {
        if (slotId < 0 && slotId >= inventory.length || inventory[slotId] == null) {
            return null;
        }

        ItemStack returnedStack = null;
        
        if (inventory[slotId].stackSize <= ammout) {
            returnedStack = inventory[slotId];
            inventory[slotId] = null;
        } else {
            returnedStack = inventory[slotId].splitStack(ammout);
            
            if (inventory[slotId].stackSize <= 0) {
                inventory[slotId] = null;
            }
        }

        return returnedStack;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slotId) {
        ItemStack returnedStack = null;

        if (slotId >= 0 && slotId < inventory.length && inventory[slotId] != null) {
            returnedStack = inventory[slotId];
            inventory[slotId] = null;
        }

        return returnedStack;
    }

    @Override
    public void setInventorySlotContents(int slotId, ItemStack itemStack) {
        
        if (slotId < 0 || slotId >= inventory.length)
            return;
        
        inventory[slotId] = itemStack;
        if (itemStack != null && itemStack.stackSize > getInventoryStackLimit()) {
            itemStack.stackSize = getInventoryStackLimit();
        }
    }

    @Override
    public String getInventoryName() {

        return hasCustomInventoryName() ? inventoryName : getDefaultName();
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
    public boolean hasCustomInventoryName() {
        return inventoryName != null && inventoryName.length() != 0;
    }
    
    public void setInventoryName (String name) {
        inventoryName = name;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : player.getDistanceSq(this.xCoord + 0.5D, this.yCoord + 0.5D, this.zCoord + 0.5D) <= 64.0D;
    }

    @Override
    public void openInventory() {
        // not needed
        
    }

    @Override
    public void closeInventory() {
        // not needed
        
    }

    @Override
    public boolean isItemValidForSlot(int slotId, ItemStack itemStack) {
        return true;
    }
    
    @Override
    public void readFromNBT(NBTTagCompound nbtCompound) {
        super.readFromNBT(nbtCompound);
        
        NBTTagList inventoryNbt = nbtCompound.getTagList("Items", NBT.TAG_COMPOUND);
        inventory = new ItemStack[getInventoryStackLimit()];

        for (int i = 0; i < inventoryNbt.tagCount(); i++) {
            NBTTagCompound slotCompound = inventoryNbt.getCompoundTagAt(i);
            int slotId = slotCompound.getByte("Slot");
            if (slotId >= 0 && slotId < inventory.length) {
                ItemStack itemStack = ItemStack.loadItemStackFromNBT(slotCompound);
                inventory[slotId] = itemStack;
            }
        }
        
        if (nbtCompound.hasKey("CustomName")){
            inventoryName = nbtCompound.getString("CustomName");
        }
    }
    
    @Override
    public void writeToNBT(NBTTagCompound nbtCompound) {
        super.writeToNBT(nbtCompound);
        
        NBTTagList inventoryNbt = new NBTTagList();
        
        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i] != null) {
                NBTTagCompound slotCompound = new NBTTagCompound();
                slotCompound.setByte("Slot", (byte) i);
                inventory[i].writeToNBT(slotCompound);
                inventoryNbt.appendTag(slotCompound);
            }
        }

        nbtCompound.setTag("Items", inventoryNbt);

        if (hasCustomInventoryName()) {
            nbtCompound.setString("CustomName", inventoryName);
        }
    }
}
