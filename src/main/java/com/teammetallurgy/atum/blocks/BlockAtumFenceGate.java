package com.teammetallurgy.atum.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.util.IIcon;

public class BlockAtumFenceGate extends BlockFenceGate {

    private Block baseBlock;
    public BlockAtumFenceGate(Block block) {
        super();
        this.baseBlock = block;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        return this.baseBlock.getBlockTextureFromSide(side);
    }
}
