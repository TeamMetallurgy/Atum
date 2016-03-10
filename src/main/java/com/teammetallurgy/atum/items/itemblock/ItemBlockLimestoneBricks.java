package com.teammetallurgy.atum.items.itemblock;

import com.teammetallurgy.atum.blocks.BlockLimestoneBricks;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockLimestoneBricks extends ItemBlock {

    public ItemBlockLimestoneBricks(Block block) {
        super(block);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }

    @Override
    public int getMetadata(int meta) {
        return meta;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return super.getUnlocalizedName() + "." + BlockLimestoneBricks.EnumType.byMetadata(stack.getItemDamage());
    }
}