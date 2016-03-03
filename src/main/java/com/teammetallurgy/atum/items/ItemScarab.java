package com.teammetallurgy.atum.items;

import com.teammetallurgy.atum.blocks.AtumBlocks;
import com.teammetallurgy.atum.handler.AtumConfig;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.StatCollector;
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
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (AtumConfig.ALLOW_CREATION || player.capabilities.isCreativeMode) {
            Block block = world.getBlockState(pos).getBlock();
            int x = pos.getX();
            int y = pos.getY();
            int z = pos.getZ();
            IBlockState temp = null;
            if (block == Blocks.sandstone || block == AtumBlocks.LARGEBRICK) {
                temp = block.getDefaultState();
            }
            if (temp != null) {
                for (int x1 = -1; x1 < 1; x1++) {
                    for (int z1 = -1; z1 < 1; z1++) {
                        if (world.getBlockState(new BlockPos(x1 + x, y + 1, z1 + z)).getBlock().getMaterial() == Material.water && block.getMetaFromState(world.getBlockState(new BlockPos(x1 + x, y + 1, z1 + z))) == 0) {
                            if (AtumBlocks.PORTAL.tryToCreatePortal(world, new BlockPos(x1 + x, y, z1 + z), temp.getBlock())) {
                                --player.getCurrentEquippedItem().stackSize;
                                return true;
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
                    AtumBlocks.PORTAL.tryToCreatePortal(world, pos, temp.getBlock());
                }
            }
        } else {
            player.addChatMessage(new ChatComponentText(StatCollector.translateToLocal("chat.atum.disabled")));
        }

        return true;
    }

    /*@Override
    public void registerIcons(IIconRegister IIconRegister) {
        this.itemIcon = IIconRegister.registerIcon("atum:Scarab");
    }*/
}