package com.teammetallurgy.atum.blocks;

import com.google.common.base.Predicate;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.Random;

public class BlockPalmLog extends BlockLog {
    public static final PropertyEnum<BlockAtumPlank.EnumType> VARIANT = PropertyEnum.create("variant", BlockAtumPlank.EnumType.class, new Predicate<BlockAtumPlank.EnumType>() {
        @Override
        public boolean apply(BlockAtumPlank.EnumType enumType) {
            return enumType.getMetadata() < 4;
        }
    });

    protected BlockPalmLog() {
        super();
        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, BlockAtumPlank.EnumType.PALM).withProperty(LOG_AXIS, BlockLog.EnumAxis.Y));
    }

    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List<ItemStack> list) {
        list.add(new ItemStack(item, 1, BlockAtumPlank.EnumType.PALM.getMetadata()));
        list.add(new ItemStack(item, 1, BlockAtumPlank.EnumType.DEADWOOD.getMetadata()));
    }

    @Override
    public int quantityDropped(Random random) {
        return 1;
    }

    @Override
    public void breakBlock(World par1World, int par2, int par3, int par4, Block par5, int par6) {
        byte b0 = 4;
        int j1 = b0 + 1;
        if (par1World.checkChunksExist(par2 - j1, par3 - j1, par4 - j1, par2 + j1, par3 + j1, par4 + j1)) {
            for (int k1 = -b0; k1 <= b0; ++k1) {
                for (int l1 = -b0; l1 <= b0; ++l1) {
                    for (int i2 = -b0; i2 <= b0; ++i2) {
                        Block j2 = par1World.getBlock(par2 + k1, par3 + l1, par4 + i2);
                        if (j2 != null) {
                            j2.beginLeavesDecay(par1World, par2 + k1, par3 + l1, par4 + i2);
                        }
                    }
                }
            }
        }

    }

    @Override
    public int onBlockPlaced(World par1World, int par2, int par3, int par4, int par5, float par6, float par7, float par8, int par9) {
        int j1 = par9 & 3;
        byte b0 = 0;
        switch (par5) {
            case 0:
            case 1:
                b0 = 0;
                break;
            case 2:
            case 3:
                b0 = 8;
                break;
            case 4:
            case 5:
                b0 = 4;
        }

        return j1 | b0;
    }

    @Override
    public boolean canSustainLeaves(IBlockAccess world, int x, int y, int z) {
        return true;
    }

    @Override
    public boolean isWood(IBlockAccess world, int x, int y, int z) {
        return true;
    }
}