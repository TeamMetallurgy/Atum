package com.teammetallurgy.atum.items;

import com.teammetallurgy.atum.Atum;
import com.teammetallurgy.atum.blocks.AtumBlocks;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDoor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ItemAtumDoor extends Item {
    
    public ItemAtumDoor() {
        super();
        this.setCreativeTab(Atum.creativeTab);
    }
    
    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        if (side != 1) {
            return false;
        }
        
        y++;
        Block block = AtumBlocks.BLOCK_DEADWOOD_DOOR;

        if (this == AtumItems.ITEM_PALM_DOOR) {
            block = AtumBlocks.BLOCK_PALM_DOOR;
        }

        if (player.canPlayerEdit(x, y, z, side, itemStack) && player.canPlayerEdit(x, y + 1, z, side, itemStack)) {
            if (!block.canPlaceBlockAt(world, x, y, z)) {
                return false;
            }
            
            int direction = MathHelper.floor_double(((player.rotationYaw + 180.0D) * 4.0D / 360.0D) - 0.5D) & 3;
            ItemDoor.placeDoorBlock(world, x, y, z, direction, block);
            itemStack.stackSize --;
            return true;
        }
       
        return false;
    }
}
