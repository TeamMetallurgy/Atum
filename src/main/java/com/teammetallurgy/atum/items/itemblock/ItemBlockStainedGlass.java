package com.teammetallurgy.atum.items.itemblock;

import com.teammetallurgy.atum.utils.Constants;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockStainedGlass extends ItemBlock {

    public ItemBlockStainedGlass(Block block) {
        super(block);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }

    @Override
    public int getMetadata(int meta) {
        return meta;
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        if (itemStack.getItemDamage() < Constants.COLOURS.length)
            return block.getUnlocalizedName() + Constants.COLOURS[itemStack.getItemDamage()];

        return block.getUnlocalizedName();
    }
}