package com.teammetallurgy.atum.items;

import com.teammetallurgy.atum.blocks.BlockAtumPlank;
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
        return super.getUnlocalizedName() + "." + BlockAtumPlank.EnumType.byMetadata(stack.getItemDamage());
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tab, List<ItemStack> subItems) {
        for (BlockAtumPlank.EnumType enumType : BlockAtumPlank.EnumType.values()) {
            subItems.add(new ItemStack(item, 1, enumType.getMetadata()));
        }
    }
}