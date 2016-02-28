package com.teammetallurgy.atum.blocks;

import com.teammetallurgy.atum.items.AtumItems;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.world.World;

import java.util.Random;

public class BlockAtumDoor extends BlockDoor {

    protected BlockAtumDoor() {
        super(Material.wood);
        this.disableStats();
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public Item getItem(World world, int x, int y, int z) {
        return this == AtumBlocks.BLOCK_PALM_DOOR ? AtumItems.ITEM_PALM_DOOR : AtumItems.ITEM_DEADWOOD_DOOR;
    }
    
    @Override
    public Item getItemDropped(int meta, Random random, int fortune) {
        return (meta & 8) != 0 ? null : this == AtumBlocks.BLOCK_PALM_DOOR ? AtumItems.ITEM_PALM_DOOR : AtumItems.ITEM_DEADWOOD_DOOR;
    }

}
