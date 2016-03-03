package com.teammetallurgy.atum.items;

import net.minecraft.item.ItemBow;

public class ItemAtumBow extends ItemBow {

    public static final String[] bowPullIconNameArray = new String[]{"bow_pull_0", "bow_pull_1", "bow_pull_2"};

    public ItemAtumBow() {
        super();
    }

    /*@Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister par1IIconRegister) {
        this.iconArray = new IIcon[bowPullIconNameArray.length];

        this.itemIcon = par1IIconRegister.registerIcon("atum:Bow");
        for (int i = 0; i < this.iconArray.length; ++i) {
            this.iconArray[i] = par1IIconRegister.registerIcon("atum:" + bowPullIconNameArray[i]);
        }

    }*/

   /* @Override
    public IIcon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining) {
        if (usingItem != null) {
            int j = this.getMaxItemUseDuration(stack) - useRemaining;
            if (j >= 18) {
                return this.getItemIconForUseDuration(2);
            }

            if (j > 13) {
                return this.getItemIconForUseDuration(1);
            }

            if (j > 0) {
                return this.getItemIconForUseDuration(0);
            }
        }

        return this.getIcon(stack, renderPass);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getItemIconForUseDuration(int par1) {
        return this.iconArray[par1];
    }*/
}