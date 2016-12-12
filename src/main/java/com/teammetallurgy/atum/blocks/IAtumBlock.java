package com.teammetallurgy.atum.blocks;

import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemBlock;

public interface IAtumBlock {
    Class<? extends ItemBlock> getItemClass();

    IProperty[] getNonRenderingProperties();

    String getStateName(IBlockState state);
}