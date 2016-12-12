package com.teammetallurgy.atum.items;

import com.teammetallurgy.atum.blocks.AtumBlocks;
import com.teammetallurgy.atum.blocks.BlockLimestoneBricks;
import com.teammetallurgy.atum.handler.AtumConfig;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemScarab extends Item {

    public ItemScarab() {
        super();
        super.maxStackSize = 1;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack) {
        return true;
    }

    @Override
    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (AtumConfig.ALLOW_CREATION || player.capabilities.isCreativeMode) {
            IBlockState state = world.getBlockState(pos);
            int x = pos.getX();
            int y = pos.getY();
            int z = pos.getZ();
            IBlockState temp = null;
            if (state == Blocks.sandstone.getDefaultState() || state == AtumBlocks.LIMESTONEBRICK.getDefaultState().withProperty(BlockLimestoneBricks.VARIANT, BlockLimestoneBricks.EnumType.LARGE)) {
                temp = state;
            }
            if (temp != null) {
                for (int x1 = -1; x1 < 1; x1++) {
                    for (int z1 = -1; z1 < 1; z1++) {
                        if (world.getBlockState(new BlockPos(x1 + x, y + 1, z1 + z)).getMaterial() == Material.water) {
                            if (AtumBlocks.PORTAL.tryToCreatePortal(world, new BlockPos(x1 + x, y, z1 + z), temp)) {
                                --player.getHeldItem(hand).stackSize;
                                return EnumActionResult.SUCCESS;
                            }
                        }
                    }
                }

                if (player.capabilities.isCreativeMode) {
                    for (int x1 = -2; x1 < 3; x1++) {
                        for (int z1 = -2; z1 < 3; z1++) {
                            for (int y1 = 0; y1 < 2; y1++) {
                                world.setBlockState(new BlockPos(x + x1, y + y1, z + z1), temp);
                            }
                        }
                    }

                    for (int x1 = -1; x1 < 2; x1++) {
                        for (int z1 = -1; z1 < 2; z1++) {
                            world.setBlockToAir(new BlockPos(x + x1, y + 1, z + z1));
                        }
                    }

                    for (int y1 = 2; y1 < 4; y1++) {
                        world.setBlockState(new BlockPos(x - 2, y + y1, z - 2), temp);
                        world.setBlockState(new BlockPos(x + 2, y + y1, z - 2), temp);
                        world.setBlockState(new BlockPos(x - 2, y + y1, z + 2), temp);
                        world.setBlockState(new BlockPos(x + 2, y + y1, z + 2), temp);
                    }
                    AtumBlocks.PORTAL.tryToCreatePortal(world, pos, temp);
                }
            }
        } else {
            player.addChatMessage(new TextComponentString(I18n.translateToLocal("chat.atum.disabled")));
        }
        return EnumActionResult.PASS;
    }
}