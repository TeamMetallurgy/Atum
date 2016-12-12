package com.teammetallurgy.atum.blocks;

import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemBlock;

public class BlockAtumFenceGate extends BlockFenceGate implements IAtumBlock {

    public BlockAtumFenceGate() {
        super(BlockPlanks.EnumType.OAK); //TODO
        this.setHardness(2.0F).setResistance(5.0F);
        this.setSoundType(SoundType.WOOD);
    }

    @Override
    public Class<? extends ItemBlock> getItemClass() {
        return ItemBlock.class;
    }

    @Override
    public IProperty[] getNonRenderingProperties() {
        return new IProperty[]{POWERED};
    }

    @Override
    public String getStateName(IBlockState state) {
        return "";
    }
}