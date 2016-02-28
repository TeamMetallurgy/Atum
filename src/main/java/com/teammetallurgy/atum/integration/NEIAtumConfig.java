package com.teammetallurgy.atum.integration;

import com.teammetallurgy.atum.utils.Constants;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import com.teammetallurgy.atum.blocks.AtumBlocks;

import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;

public class NEIAtumConfig implements IConfigureNEI {

    @Override
    public String getName() {
        return "Atum NEI Integration";
    }

    @Override
    public String getVersion() {
        return Constants.VERSION;
    }

    @Override
    public void loadConfig() {
        // Hide not need items from NEI
        API.hideItem(new ItemStack(AtumBlocks.BLOCK_DATEBLOCK, 1, OreDictionary.WILDCARD_VALUE));
        API.hideItem(new ItemStack(AtumBlocks.BLOCK_FLAX, 1, OreDictionary.WILDCARD_VALUE));
        API.hideItem(new ItemStack(AtumBlocks.BLOCK_PAPYRUS, 1, OreDictionary.WILDCARD_VALUE));
        API.hideItem(new ItemStack(AtumBlocks.BLOCK_PORTAL, 1, OreDictionary.WILDCARD_VALUE));
        API.hideItem(new ItemStack(AtumBlocks.BLOCK_FURNACEBURNING));
        API.hideItem(new ItemStack(AtumBlocks.BLOCK_DOUBLESLAB,1, OreDictionary.WILDCARD_VALUE));
        API.hideItem(new ItemStack(AtumBlocks.BLOCK_PALM_DOOR));
        API.hideItem(new ItemStack(AtumBlocks.BLOCK_DEADWOOD_DOOR));
        API.hideItem(new ItemStack(AtumBlocks.BLOCK_WOOD_DOUBLESLAB, 1, OreDictionary.WILDCARD_VALUE));
    }
}