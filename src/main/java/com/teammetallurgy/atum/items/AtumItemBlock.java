package com.teammetallurgy.atum.items;

import com.teammetallurgy.atum.blocks.IAtumBlock;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class AtumItemBlock extends ItemBlock {
    public IAtumBlock atumBlock;

    public AtumItemBlock(Block block) {
        super(block);
        if (block instanceof IAtumBlock) {
            this.atumBlock = (IAtumBlock) block;
        } else {
            throw new IllegalArgumentException("AtumItemBlock needs a block implementing IAtumBlock");
        }
        this.setHasSubtypes(true);
    }

    @Override
    public int getMetadata(int metadata) {
        return metadata;
    }

    @Override
    @Nonnull
    public String getUnlocalizedName(@Nonnull ItemStack stack) {
        int meta = stack.getMetadata();
        IBlockState oldState = block.getStateFromMeta(meta);
        String name = atumBlock.getStateName(oldState);

        if (!name.equals("")) {
            return super.getUnlocalizedName() + "." + atumBlock.getStateName(oldState);
        } else {
            return super.getUnlocalizedName(stack);
        }
    }
}