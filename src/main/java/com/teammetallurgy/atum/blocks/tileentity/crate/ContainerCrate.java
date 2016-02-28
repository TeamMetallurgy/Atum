package com.teammetallurgy.atum.blocks.tileentity.crate;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerCrate extends Container {

    private TileEntityCrate tileEntity;
    public ContainerCrate(InventoryPlayer playerInventory, TileEntityCrate crateTe) {
        
        tileEntity = crateTe;
        
        for (int j = 0; j < 3; ++j)
        {
            for (int k = 0; k < 9; ++k)
            {
                this.addSlotToContainer(new Slot(tileEntity, k + j * 9, 8 + k * 18, 18 + j * 18));
            }
        }

        for (int j = 0; j < 3; ++j)
        {
            for (int k = 0; k < 9; ++k)
            {
                this.addSlotToContainer(new Slot(playerInventory, k + j * 9 + 9, 8 + k * 18, 103 + j * 18 - 18));
            }
        }

        for (int j = 0; j < 9; ++j)
        {
            this.addSlotToContainer(new Slot(playerInventory, j, 8 + j * 18, 161 - 18));
        }
    }
    
    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return tileEntity.isUseableByPlayer(player);
    }
    
    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotId) {
        ItemStack transferedStack = null;
        
        Slot slot = (Slot)inventorySlots.get(slotId);
        
        if (slot == null || !slot.getHasStack())
            return null;
        
        ItemStack slotStack = slot.getStack();
        transferedStack = slotStack.copy();
        
        if (slotId < 27) {
            if(!mergeItemStack(slotStack, 27, inventorySlots.size(), true)) {
                return null;
            }
        } else if (!mergeItemStack(slotStack, 0, 27, false)) {
            return null;
        }
        
        if (slotStack.stackSize <= 0) {
            slot.putStack((ItemStack)null);
        } else {
            slot.onSlotChanged();
        }
        
        return transferedStack;
    }
}
