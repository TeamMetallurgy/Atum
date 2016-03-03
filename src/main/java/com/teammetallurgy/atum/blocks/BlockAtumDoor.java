package com.teammetallurgy.atum.blocks;

import com.teammetallurgy.atum.items.AtumItems;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class BlockAtumDoor extends BlockDoor {

    protected BlockAtumDoor() {
        super(Material.wood);
        this.disableStats();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Item getItem(World world, BlockPos pos) {
        return this == AtumBlocks.PALM_DOOR ? AtumItems.PALM_DOOR : AtumItems.DEADWOOD_DOOR;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random random, int fortune) {
        return state.getValue(HALF) == BlockDoor.EnumDoorHalf.UPPER ? null : this == AtumBlocks.PALM_DOOR ? AtumItems.PALM_DOOR : AtumItems.DEADWOOD_DOOR;
    }
}