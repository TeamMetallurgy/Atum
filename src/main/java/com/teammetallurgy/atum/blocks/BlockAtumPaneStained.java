package com.teammetallurgy.atum.blocks;

import com.teammetallurgy.atum.items.AtumItemBlock;

import net.minecraft.block.BlockStainedGlassPane;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemBlock;

public class BlockAtumPaneStained extends BlockStainedGlassPane implements IAtumBlock {

    public BlockAtumPaneStained() {
        super();
        this.setHardness(0.3F);
        this.setSoundType(SoundType.GLASS);
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