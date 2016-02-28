package com.teammetallurgy.atum.blocks;

import com.teammetallurgy.atum.Atum;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class BlockAtumWoodSlab extends BlockSlab {

    public BlockAtumWoodSlab(boolean isDoubleSlab) {
        super(isDoubleSlab, Material.wood);
        this.setHardness(2.0F);
        this.setResistance(5.0F);
        this.useNeighborBrightness = true;
        this.setBlockName("woodSlab");
        this.setCreativeTab(Atum.creativeTab);
    }

    @Override
    public Item getItemDropped(int meta, Random random, int fortune) {
        return Item.getItemFromBlock(AtumBlocks.BLOCK_WOOD_SLAB);
    }
    
    @Override
    protected ItemStack createStackedBlock(int meta) {
        return new ItemStack(this, 2, meta & 4);
    }
    
    @Override
    public String func_150002_b(int meta) {
        String unlocalizedName = this.getUnlocalizedName();
        
        switch(meta) {
        case 0:
            unlocalizedName += ".palm";
            break;
        case 1:
            unlocalizedName += ".deadwood";
            break;
        }
        
        return unlocalizedName;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public Item getItem(World world, int x, int y, int z) {
        return Item.getItemFromBlock(AtumBlocks.BLOCK_WOOD_SLAB);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register) {
        // Do nothing 
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        switch (meta % 4) {
        case 0 :
            return AtumBlocks.BLOCK_PLANKS.getIcon(side, meta);
        default:
            return AtumBlocks.BLOCK_DEADWOOD_PLANK.getIcon(side, meta);
        }
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        if (item != Item.getItemFromBlock(AtumBlocks.BLOCK_WOOD_DOUBLESLAB)) {
            for (int i =0 ; i < 2; i++ ){
                list.add(new ItemStack(this,1,i));
            }
        }
    }

}
