package com.teammetallurgy.atum.blocks;

import com.teammetallurgy.atum.utils.Constants;
import net.minecraft.block.BlockStainedGlassPane;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class BlockAtumPaneStained extends BlockStainedGlassPane {

    private String[] colours = Constants.COLOURS;

    public BlockAtumPaneStained() {
        super();
        this.setHardness(0.3F);
        this.setStepSound(soundTypeGlass);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List<ItemStack> list) {
        for (int meta = 0; meta < colours.length; meta++) {
            list.add(new ItemStack(this, 1, meta));
        }
    }
}