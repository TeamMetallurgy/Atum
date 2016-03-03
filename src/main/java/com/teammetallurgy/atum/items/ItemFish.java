package com.teammetallurgy.atum.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemFish extends Item {

    public ItemFish() {
        super();
        this.setHasSubtypes(true);
    }

    /*@Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int par1) {
        return this.icons[par1];
    }*/

    @Override
    public String getUnlocalizedName(ItemStack par1ItemStack) {
        return this.getUnlocalizedName() + "." + par1ItemStack.getItemDamage();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tab, List<ItemStack> subItems) {
        for (int i = 0; i < 4; ++i) {
            subItems.add(new ItemStack(item, 1, i));
        }
    }

    /*@Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister par1IIconRegister) {
        this.icons = new IIcon[4];

        for (int i = 0; i < 4; ++i) {
            this.icons[i] = par1IIconRegister.registerIcon("atum:Fish" + i);
        }
    }*/
}