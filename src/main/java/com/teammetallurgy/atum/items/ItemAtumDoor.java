package com.teammetallurgy.atum.items;

import com.teammetallurgy.atum.blocks.AtumBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDoor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class ItemAtumDoor extends Item {

    public ItemAtumDoor() {
        super();
    }

    @Override
    @Nonnull
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack stack = player.getHeldItem(hand);
        if (facing != EnumFacing.UP) {
            return EnumActionResult.FAIL;
        }

        Block block = AtumBlocks.DEADWOOD_DOOR;

        if (this == AtumItems.PALM_DOOR) {
            block = AtumBlocks.PALM_DOOR;
        }

        if (!block.isReplaceable(world, pos)) {
            pos = pos.offset(facing);
        }

        if (player.canPlayerEdit(pos, facing, stack) && block.canPlaceBlockAt(world, pos)) {
            EnumFacing enumfacing = EnumFacing.fromAngle((double) player.rotationYaw);
            int i = enumfacing.getFrontOffsetX();
            int j = enumfacing.getFrontOffsetZ();
            boolean flag = i < 0 && hitZ < 0.5F || i > 0 && hitZ > 0.5F || j < 0 && hitX > 0.5F || j > 0 && hitX < 0.5F;
            ItemDoor.placeDoor(world, pos, enumfacing, block, flag);
            SoundType soundtype = block.getSoundType(world.getBlockState(pos), world, pos, null);
            world.playSound(player, pos, soundtype.getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
            stack.shrink(1);
            return EnumActionResult.SUCCESS;
        } else {
            return EnumActionResult.FAIL;
        }
    }
}