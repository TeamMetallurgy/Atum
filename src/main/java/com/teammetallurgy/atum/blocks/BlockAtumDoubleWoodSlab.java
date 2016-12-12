package com.teammetallurgy.atum.blocks;

import net.minecraft.block.properties.IProperty;

public class BlockAtumDoubleWoodSlab extends BlockAtumWoodSlab {

    @Override
    public IProperty[] getNonRenderingProperties() {
        return new IProperty[] {HALF};
    }

    @Override
    public boolean isDouble() {
        return true;
    }
}