package com.teammetallurgy.atum.handler;

import com.teammetallurgy.atum.items.AtumItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class AtumCreativeTab extends CreativeTabs {

    public AtumCreativeTab() {
        super("atum");
    }

    @Override
    public Item getTabIconItem() {
        return AtumItems.SCARAB;
    }
}