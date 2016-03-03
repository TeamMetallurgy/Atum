package com.teammetallurgy.atum.blocks;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemBlockSlab extends ItemSlab {
    public static final String[] types = {"smooth", "cracked", "largeBrick", "smallBrick"};

    public ItemBlockSlab(Block baseBlock) {
        super(baseBlock, AtumBlocks.SLABS, AtumBlocks.DOUBLESLAB);
        this.setHasSubtypes(true);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tab, List<ItemStack> subItems) {
        if (this != Item.getItemFromBlock(AtumBlocks.DOUBLESLAB)) {
            for (int i = 0; i < types.length; i++) {
                subItems.add(new ItemStack(this, 1, i));
            }
        }
    }

    @Override
    public int getMetadata(int damage) {
        return damage;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return "tile." + types[stack.getItemDamage()] + "Slab";
    }
}