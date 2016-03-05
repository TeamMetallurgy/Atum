package com.teammetallurgy.atum.integration;

import com.teammetallurgy.atum.blocks.AtumBlocks;
import mezz.jei.api.BlankModPlugin;
import mezz.jei.api.IItemBlacklist;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

@JEIPlugin
public class JEIItemBlacklist extends BlankModPlugin { //TODO
    @Override
    public void register(IModRegistry registry) {
        /*IItemBlacklist blacklist = registry.getJeiHelpers().getItemBlacklist();

        blacklist.addItemToBlacklist(new ItemStack(AtumBlocks.DATEBLOCK, 1, OreDictionary.WILDCARD_VALUE));
        blacklist.addItemToBlacklist(new ItemStack(AtumBlocks.FLAX, 1, OreDictionary.WILDCARD_VALUE));
        blacklist.addItemToBlacklist(new ItemStack(AtumBlocks.PAPYRUS, 1, OreDictionary.WILDCARD_VALUE));
        blacklist.addItemToBlacklist(new ItemStack(AtumBlocks.PORTAL, 1, OreDictionary.WILDCARD_VALUE));
        blacklist.addItemToBlacklist(new ItemStack(AtumBlocks.FURNACE_LIT));
        blacklist.addItemToBlacklist(new ItemStack(AtumBlocks.DOUBLESLAB, 1, OreDictionary.WILDCARD_VALUE));
        blacklist.addItemToBlacklist(new ItemStack(AtumBlocks.PALM_DOOR));
        blacklist.addItemToBlacklist(new ItemStack(AtumBlocks.DEADWOOD_DOOR));
        blacklist.addItemToBlacklist(new ItemStack(AtumBlocks.WOOD_DOUBLESLAB, 1, OreDictionary.WILDCARD_VALUE));*/
    }
}