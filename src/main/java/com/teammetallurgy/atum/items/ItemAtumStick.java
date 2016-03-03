package com.teammetallurgy.atum.items;

import com.teammetallurgy.atum.Atum;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemAtumStick extends Item {

    public ItemAtumStick() {
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
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
    
    /*@Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister register) {
        icons = new IIcon[2];
        icons[0] = register.registerIcon("atum:palm_stick");
        icons[1] = register.registerIcon("atum:deadwood_stick");
    }*/

    /*@Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int damage) {
        if (damage < 0 || damage >= icons.length)
            return icons[0];
        return icons[damage];
    }*/

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tab, List<ItemStack> subItems) {
        subItems.add(new ItemStack(this, 1, 0));
        subItems.add(new ItemStack(this, 1, 1));
    }
}