package com.teammetallurgy.atum.items;

import com.teammetallurgy.atum.Atum;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.List;

public class ItemAtumStick extends Item {
    
    private IIcon icons[];
    
    public ItemAtumStick() {
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
        this.setUnlocalizedName("stick");
        this.setCreativeTab(Atum.creativeTab);
    }
    
    @Override
    public String getUnlocalizedName(ItemStack stack) {
        String unlocalizedName = getUnlocalizedName();
        
        switch (stack.getItemDamage()) {
            case 0:
                unlocalizedName += ".palm";
                break;
            case 1:
                unlocalizedName += ".deadwood";
        }
        return unlocalizedName;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister register) {
        icons = new IIcon[2];
        icons[0] = register.registerIcon("atum:palm_stick");
        icons[1] = register.registerIcon("atum:deadwood_stick");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int damage) {
        if (damage < 0 || damage >= icons.length)
            return icons[0];
        return icons[damage];
    }

    @Override
    @SideOnly(Side.CLIENT)
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void getSubItems(Item item, CreativeTabs tab, List list) {
        list.add(new ItemStack(this,1,0));
        list.add(new ItemStack(this,1,1));
    }

}
