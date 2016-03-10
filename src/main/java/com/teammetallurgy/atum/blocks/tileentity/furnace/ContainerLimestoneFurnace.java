package com.teammetallurgy.atum.blocks.tileentity.furnace;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerLimestoneFurnace extends Container {
    private final IInventory tileLimestoneFurnace;
    private int field_178152_f;
    private int field_178153_g;
    private int field_178154_h;
    private int field_178155_i;

    public ContainerLimestoneFurnace(InventoryPlayer playerInventory, IInventory furnaceInventory) {
        this.tileLimestoneFurnace = furnaceInventory;
        this.addSlotToContainer(new Slot(furnaceInventory, 0, 56, 17));
        this.addSlotToContainer(new SlotFurnaceFuel(furnaceInventory, 1, 56, 53));
        this.addSlotToContainer(new SlotFurnaceOutput(playerInventory.player, furnaceInventory, 2, 116, 35));

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (int k = 0; k < 9; ++k) {
            this.addSlotToContainer(new Slot(playerInventory, k, 8 + k * 18, 142));
        }
    }

    @Override
    public void onCraftGuiOpened(ICrafting listener) {
        super.onCraftGuiOpened(listener);
        listener.sendAllWindowProperties(this, this.tileLimestoneFurnace);
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        for (int i = 0; i < this.crafters.size(); ++i) {
            ICrafting icrafting = this.crafters.get(i);

            if (this.field_178152_f != this.tileLimestoneFurnace.getField(2)) {
                icrafting.sendProgressBarUpdate(this, 2, this.tileLimestoneFurnace.getField(2));
            }

            if (this.field_178154_h != this.tileLimestoneFurnace.getField(0)) {
                icrafting.sendProgressBarUpdate(this, 0, this.tileLimestoneFurnace.getField(0));
            }

            if (this.field_178155_i != this.tileLimestoneFurnace.getField(1)) {
                icrafting.sendProgressBarUpdate(this, 1, this.tileLimestoneFurnace.getField(1));
            }

            if (this.field_178153_g != this.tileLimestoneFurnace.getField(3)) {
                icrafting.sendProgressBarUpdate(this, 3, this.tileLimestoneFurnace.getField(3));
            }
        }

        this.field_178152_f = this.tileLimestoneFurnace.getField(2);
        this.field_178154_h = this.tileLimestoneFurnace.getField(0);
        this.field_178155_i = this.tileLimestoneFurnace.getField(1);
        this.field_178153_g = this.tileLimestoneFurnace.getField(3);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int data) {
        this.tileLimestoneFurnace.setField(id, data);
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return this.tileLimestoneFurnace.isUseableByPlayer(player);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int index) {
        ItemStack stack = null;
        Slot slot = this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            ItemStack slotStack = slot.getStack();
            stack = slotStack.copy();

            if (index == 2) {
                if (!this.mergeItemStack(slotStack, 3, 39, true)) {
                    return null;
                }

                slot.onSlotChange(slotStack, stack);
            } else if (index != 1 && index != 0) {
                if (FurnaceRecipes.instance().getSmeltingResult(slotStack) != null) {
                    if (!this.mergeItemStack(slotStack, 0, 1, false)) {
                        return null;
                    }
                } else if (TileEntityLimestoneFurnace.isItemFuel(slotStack)) {
                    if (!this.mergeItemStack(slotStack, 1, 2, false)) {
                        return null;
                    }
                } else if (index >= 3 && index < 30) {
                    if (!this.mergeItemStack(slotStack, 30, 39, false)) {
                        return null;
                    }
                } else if (index >= 30 && index < 39 && !this.mergeItemStack(slotStack, 3, 30, false)) {
                    return null;
                }
            } else if (!this.mergeItemStack(slotStack, 3, 39, false)) {
                return null;
            }

            if (slotStack.stackSize == 0) {
                slot.putStack(null);
            } else {
                slot.onSlotChanged();
            }

            if (slotStack.stackSize == stack.stackSize) {
                return null;
            }
            slot.onPickupFromSlot(player, slotStack);
        }
        return stack;
    }
}