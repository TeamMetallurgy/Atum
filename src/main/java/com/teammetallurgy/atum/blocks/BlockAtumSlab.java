package com.teammetallurgy.atum.blocks;

import com.teammetallurgy.atum.Atum;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class BlockAtumSlab extends BlockSlab {

    public BlockAtumSlab(boolean isDoubleSlab) {
        super(isDoubleSlab, Material.rock);
        this.setHardness(2.0F);
        this.useNeighborBrightness = true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int par1, int par2) {
        par2 %= 4;
        if (par2 == 0)
            return AtumBlocks.STONE.getIcon(par1, par2 & 0x7);
        if (par2 == 1)
            return AtumBlocks.LIMESTONECOBBLE.getIcon(par1, par2 & 0x7);
        if (par2 == 2) {
            return AtumBlocks.LARGEBRICK.getIcon(par1, par2 & 0x7);
        }
        return AtumBlocks.SMALLBRICK.getIcon(par1, par2 & 0x7);
    }

    @Override
    public Item getItemDropped(int par1, Random random, int par3) {
        return Item.getItemFromBlock(AtumBlocks.SLABS);
    }

    @Override
    protected ItemStack createStackedBlock(int par1) {
        return new ItemStack(this, 2, par1 & 4);
    }

    @Override
    @SideOnly(Side.CLIENT)
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void getSubBlocks(Item par1, CreativeTabs tab, List subItems) {
        if (this != AtumBlocks.DOUBLESLAB) {
            for (int i = 0; i < 4; i++)
                subItems.add(new ItemStack(this, 1, i));
        }
    }

    @Override
    public String func_150002_b(int var1) {
        return this.getUnlocalizedName();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister p_149651_1_) {
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Item getItem(World world, int x, int y, int z) {
        return AtumBlocks.SLABS == this ? Item.getItemFromBlock(this) : (this == AtumBlocks.DOUBLESLAB ? Item.getItemFromBlock(AtumBlocks.SLABS) : Item.getItemFromBlock(AtumBlocks.SLABS));
    }
}