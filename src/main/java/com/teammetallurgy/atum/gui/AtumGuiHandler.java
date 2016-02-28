package com.teammetallurgy.atum.gui;

import com.teammetallurgy.atum.blocks.tileentity.crate.ContainerCrate;
import com.teammetallurgy.atum.blocks.tileentity.crate.GuiCrate;
import com.teammetallurgy.atum.blocks.tileentity.crate.TileEntityCrate;
import com.teammetallurgy.atum.blocks.tileentity.furnace.ContainerLimestoneFurnace;
import com.teammetallurgy.atum.blocks.tileentity.furnace.GuiLimestoneFurnace;
import com.teammetallurgy.atum.blocks.tileentity.furnace.TileEntityLimestoneFurnace;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class AtumGuiHandler implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity te = world.getTileEntity(x, y, z);

        if (te != null) {
            switch (id) {
                case 0:
                    if (te instanceof TileEntityLimestoneFurnace) {
                        return new ContainerLimestoneFurnace(player.inventory, te);
                    }
                    break;
                case 1:
                    if (te instanceof TileEntityCrate) {
                        return new ContainerCrate(player.inventory, (TileEntityCrate)te);
                    }
                    break;
            }
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity te = world.getTileEntity(x, y, z);

        if (te != null) {
            switch (id) {
                case 0:
                    if (te instanceof TileEntityLimestoneFurnace) {
                        return new GuiLimestoneFurnace(player.inventory, te);
                    }
                    break;
                case 1:
                    if (te instanceof TileEntityCrate) {
                        return new GuiCrate(player.inventory, (TileEntityCrate)te);
                    }
                    break;
            }
        }
        return null;
    }

}
