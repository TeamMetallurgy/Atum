package com.teammetallurgy.atum.items;

import com.teammetallurgy.atum.blocks.AtumBlocks;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDoor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ItemAtumDoor extends Item {

    public ItemAtumDoor() {
        super();
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (side != EnumFacing.UP) {
            return false;
        }

        Block block = AtumBlocks.DEADWOOD_DOOR;

        if (this == AtumItems.PALM_DOOR) {
            block = AtumBlocks.PALM_DOOR;
        }

        if (player.canPlayerEdit(pos, side, stack) && player.canPlayerEdit(pos.up(), side, stack)) {
            if (!block.canPlaceBlockAt(world, pos)) {
                return false;
            }

            ItemDoor.placeDoor(world, pos, EnumFacing.fromAngle((double) player.rotationYaw), block);
            stack.stackSize--;
            return true;
        }
        return false;
    }
}