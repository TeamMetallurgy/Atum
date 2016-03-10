package com.teammetallurgy.atum.items.itemblock;

import com.teammetallurgy.atum.blocks.BlockAtumPlank;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockPlanks extends ItemBlock {

    public ItemBlockPlanks(Block block) {
        super(block);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }

    @Override
    public int getMetadata(int damage) {
        return damage;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return super.getUnlocalizedName() + "." + BlockAtumPlank.EnumType.byMetadata(stack.getItemDamage());
    }
}