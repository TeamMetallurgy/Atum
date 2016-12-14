package com.teammetallurgy.atum.blocks;

import com.teammetallurgy.atum.items.AtumItemBlock;

import net.minecraft.block.BlockStainedGlass;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemBlock;

public class BlockAtumGlassStained extends BlockStainedGlass implements IAtumBlock {
    public BlockAtumGlassStained() {
        super(Material.GLASS);
        this.setSoundType(SoundType.GLASS);
        this.setHardness(0.3F);
    }

    @Override
    public Class<? extends ItemBlock> getItemClass() {
        return AtumItemBlock.class;
    }

    @Override
    public IProperty[] getNonRenderingProperties() {
        return null;
    }

    @Override
    public String getStateName(IBlockState state) {
        return EnumDyeColor.byMetadata(state.getBlock().getMetaFromState(state)).getUnlocalizedName();
    }

}