package com.teammetallurgy.atum.blocks.tileentity.crate;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntityChest;

import javax.annotation.Nonnull;

public class TileEntityCrate extends TileEntityChest {

    public TileEntityCrate() {
    }

    @Override
    @Nonnull
    public String getName() {
        return this.hasCustomName() ? this.customName : getDefaultName(); //TODO fix custom inventory name
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
        return name;
    }

    @Override
    @Nonnull
    public Container createContainer(@Nonnull InventoryPlayer playerInventory, @Nonnull EntityPlayer player) {
        this.fillWithLoot(player);
        return new ContainerCrate(playerInventory, this);
    }
}