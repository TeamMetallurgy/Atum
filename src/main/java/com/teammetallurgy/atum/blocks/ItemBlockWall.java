package com.teammetallurgy.atum.blocks;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import java.util.List;

public class ItemBlockWall extends ItemBlock {
    public static final String[] types = {"smooth", "cracked", "largeBrick", "smallBrick"};

    public ItemBlockWall(Block block) {
        super(block);
        setHasSubtypes(true);
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tab, List<ItemStack> list) {
        for (int i = 0; i < types.length; i++) {
            list.add(new ItemStack(this, 1, i));
        }
    }

    @Override
    public int getMetadata(int meta) {
        return meta;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return "tile." + types[stack.getItemDamage()] + "Wall";
    }
}