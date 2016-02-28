package com.teammetallurgy.atum.handler;

import com.teammetallurgy.atum.blocks.AtumBlocks;
import com.teammetallurgy.atum.items.AtumItems;
import com.teammetallurgy.atum.utils.Constants;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class CraftingHandler {
    public void register() {
        addRecipes();
        addSmeltingRecipes();
        addShapelessRecipes();
    }

    private void addRecipes() {
        GameRegistry.addShapelessRecipe(new ItemStack(AtumBlocks.BLOCK_LARGEBRICK, 1, 2), new ItemStack(AtumBlocks.BLOCK_LARGEBRICK));
        GameRegistry.addShapelessRecipe(new ItemStack(AtumBlocks.BLOCK_LARGEBRICK, 1, 2), new ItemStack(AtumBlocks.BLOCK_LARGEBRICK,1,1));
        GameRegistry.addShapelessRecipe(new ItemStack(AtumBlocks.BLOCK_LARGESTONESTAIRSBREAKABLE), new ItemStack(AtumBlocks.BLOCK_LARGESTONESTAIRS));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(AtumBlocks.BLOCK_LARGEBRICK, 4, 2), "XX", "XX", 'X', AtumBlocks.BLOCK_STONE));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(AtumBlocks.BLOCK_SMALLBRICK, 4), "XX", "XX", 'X', AtumBlocks.BLOCK_LIMESTONECOBBLE));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(AtumBlocks.BLOCK_CARVEDBRICK, 1), AtumBlocks.BLOCK_STONE));
        GameRegistry.addRecipe(new ItemStack(AtumBlocks.BLOCK_SMOOTHSTAIRS, 4), "X  ", "XX ", "XXX", 'X', AtumBlocks.BLOCK_STONE);
        GameRegistry.addRecipe(new ItemStack(AtumBlocks.BLOCK_COBBLESTAIRS, 4), "X  ", "XX ", "XXX", 'X', AtumBlocks.BLOCK_LIMESTONECOBBLE);
        GameRegistry.addRecipe(new ItemStack(AtumBlocks.BLOCK_LARGESTONESTAIRSBREAKABLE, 4), "X  ", "XX ", "XXX", 'X', new ItemStack(AtumBlocks.BLOCK_LARGEBRICK, 1, 2));
        GameRegistry.addRecipe(new ItemStack(AtumBlocks.BLOCK_SMALLSTONESTAIRS, 4), "X  ", "XX ", "XXX", 'X', AtumBlocks.BLOCK_SMALLBRICK);
        GameRegistry.addRecipe(new ItemStack(AtumBlocks.BLOCK_SLABS, 6, 0), "XXX", 'X', AtumBlocks.BLOCK_STONE);
        GameRegistry.addRecipe(new ItemStack(AtumBlocks.BLOCK_SLABS, 6, 1), "XXX", 'X', AtumBlocks.BLOCK_LIMESTONECOBBLE);
        GameRegistry.addRecipe(new ItemStack(AtumBlocks.BLOCK_SLABS, 6, 2), "XXX", 'X', new ItemStack(AtumBlocks.BLOCK_LARGEBRICK, 1, 2));
        GameRegistry.addRecipe(new ItemStack(AtumBlocks.BLOCK_SLABS, 6, 3), "XXX", 'X', AtumBlocks.BLOCK_SMALLBRICK);
        GameRegistry.addRecipe(new ItemStack(AtumBlocks.BLOCK_WALL, 6, 0), "XXX", "XXX", 'X', AtumBlocks.BLOCK_STONE);
        GameRegistry.addRecipe(new ItemStack(AtumBlocks.BLOCK_WALL, 6, 1), "XXX", "XXX", 'X', AtumBlocks.BLOCK_LIMESTONECOBBLE);
        GameRegistry.addRecipe(new ItemStack(AtumBlocks.BLOCK_WALL, 6, 2), "XXX", "XXX", 'X', new ItemStack(AtumBlocks.BLOCK_LARGEBRICK, 1, 2));
        GameRegistry.addRecipe(new ItemStack(AtumBlocks.BLOCK_WALL, 6, 3), "XXX", "XXX", 'X', AtumBlocks.BLOCK_SMALLBRICK);
        GameRegistry.addRecipe(new ItemStack(AtumBlocks.BLOCK_FRAMEDGLASS), " X ", "XSX", " X ", 'X', Items.stick, 'S', AtumBlocks.BLOCK_CRYSTALGLASS);
        GameRegistry.addRecipe(new ItemStack(AtumBlocks.BLOCK_CRACKEDLARGEBRICK, 4), "XX", "XX", 'X', AtumItems.ITEM_STONECHUNK);
        GameRegistry.addRecipe(new ItemStack(Items.experience_bottle), " X ", "XBX", " X ", 'X', AtumItems.ITEM_ECTOPLASM, 'B', Items.potionitem);
        GameRegistry.addRecipe(new ItemStack(AtumItems.limestoneSword), "L", "L", "S", 'L', AtumBlocks.BLOCK_LIMESTONECOBBLE, 'S', Items.stick);
        GameRegistry.addRecipe(new ItemStack(AtumItems.limestoneShovel), "L", "S", "S", 'L', AtumBlocks.BLOCK_LIMESTONECOBBLE, 'S', Items.stick);
        GameRegistry.addRecipe(new ItemStack(AtumItems.limestonePickaxe), "LLL", " S ", " S ", 'L', AtumBlocks.BLOCK_LIMESTONECOBBLE, 'S', Items.stick);
        GameRegistry.addRecipe(new ItemStack(AtumItems.limestoneAxe), "LL", "LS", " S", 'L', AtumBlocks.BLOCK_LIMESTONECOBBLE, 'S', Items.stick);
        GameRegistry.addRecipe(new ItemStack(AtumItems.limestoneHoe), "LL", " S", " S", 'L', AtumBlocks.BLOCK_LIMESTONECOBBLE, 'S', Items.stick);
        GameRegistry.addRecipe(new ItemStack(AtumItems.mummyHelmet), "XXX", "X X", 'X', AtumItems.ITEM_SCRAP);
        GameRegistry.addRecipe(new ItemStack(AtumItems.mummyChest), "X X", "XXX", "XXX", 'X', AtumItems.ITEM_SCRAP);
        GameRegistry.addRecipe(new ItemStack(AtumItems.mummyLegs), "XXX", "X X", "X X", 'X', AtumItems.ITEM_SCRAP);
        GameRegistry.addRecipe(new ItemStack(AtumItems.mummyBoots), "X X", "X X", 'X', AtumItems.ITEM_SCRAP);
        GameRegistry.addRecipe(new ItemStack(AtumItems.wandererHelmet), "XXX", "X X", 'X', AtumItems.ITEM_LINEN);
        GameRegistry.addRecipe(new ItemStack(AtumItems.wandererChest), "X X", "XXX", "XXX", 'X', AtumItems.ITEM_LINEN);
        GameRegistry.addRecipe(new ItemStack(AtumItems.wandererLegs), "XXX", "X X", "X X", 'X', AtumItems.ITEM_LINEN);
        GameRegistry.addRecipe(new ItemStack(AtumItems.wandererBoots), "X X", "X X", 'X', AtumItems.ITEM_LINEN);
        GameRegistry.addRecipe(new ItemStack(AtumItems.ITEM_LINEN), "XXX", 'X', AtumItems.ITEM_FLAX);
        GameRegistry.addRecipe(new ItemStack(Items.glass_bottle, 3), "X X", " X ", 'X', AtumBlocks.BLOCK_CRYSTALGLASS);
        GameRegistry.addRecipe(new ItemStack(AtumBlocks.BLOCK_THINCRYSTALGLASS, 16), "XXX", "XXX", 'X', AtumBlocks.BLOCK_CRYSTALGLASS);
        GameRegistry.addRecipe(new ItemStack(AtumBlocks.BLOCK_THINFRAMEDGLASS, 16), "XXX", "XXX", 'X', AtumBlocks.BLOCK_FRAMEDGLASS);
        GameRegistry.addRecipe(new ItemStack(AtumItems.ITEM_SCROLL), "XXX", "SXS", "XXX", 'X', AtumItems.papyrusPlant, 'S', Items.stick);
        GameRegistry.addRecipe(new ItemStack(AtumItems.ITEM_SCARAB), " G ", "GDG", " G ", 'G', Items.gold_ingot, 'D', Items.diamond);
        GameRegistry.addRecipe(new ItemStack(AtumBlocks.BLOCK_FURNACEIDLE), "XXX", "X X", "XXX", 'X', AtumBlocks.BLOCK_LIMESTONECOBBLE);
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.dye, 1, 15), new ItemStack(AtumItems.ITEM_DUSTYBONE)));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(AtumItems.ITEM_GOLDEN_DATE, 1, 0), "###", "#X#", "###", '#', Items.gold_ingot, 'X', AtumItems.ITEM_DATE));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(AtumItems.ITEM_GOLDEN_DATE, 1, 1), "###", "#X#", "###", '#', Blocks.gold_block, 'X', AtumItems.ITEM_DATE));
        GameRegistry.addShapedRecipe(new ItemStack(AtumItems.ITEM_PALM_DOOR), "pp ","pp ", "pp ", 'p', AtumBlocks.BLOCK_PLANKS);
        GameRegistry.addShapedRecipe(new ItemStack(AtumBlocks.BLOCK_PALM_FENCE), "sss", "sss", 's', new ItemStack(AtumItems.ITEM_STICK, 1, 0));
        GameRegistry.addShapedRecipe(new ItemStack(AtumBlocks.BLOCK_PALM_FENCE_GATE), "sps", "sps", 's', new ItemStack(AtumItems.ITEM_STICK, 1, 0), 'p', AtumBlocks.BLOCK_PLANKS);
        GameRegistry.addShapedRecipe(new ItemStack(AtumBlocks.BLOCK_PALM_HATCH, 2), "ppp", "ppp", 'p', AtumBlocks.BLOCK_PLANKS);
        GameRegistry.addShapedRecipe(new ItemStack(AtumBlocks.BLOCK_PALM_LADDER, 3), "s s", "sss", "s s", 's', new ItemStack(AtumItems.ITEM_STICK, 1, 0));
        GameRegistry.addShapedRecipe(new ItemStack(AtumItems.ITEM_DEADWOOD_DOOR), "pp ","pp ", "pp ", 'p', AtumBlocks.BLOCK_DEADWOOD_PLANK);
        GameRegistry.addShapedRecipe(new ItemStack(AtumBlocks.BLOCK_DEADWOOD_FENCE), "sss", "sss", 's', new ItemStack(AtumItems.ITEM_STICK, 1, 1));
        GameRegistry.addShapedRecipe(new ItemStack(AtumBlocks.BLOCK_DEADWOOD_FENCE_GATE), "sps", "sps", 's', new ItemStack(AtumItems.ITEM_STICK, 1, 1), 'p', AtumBlocks.BLOCK_DEADWOOD_PLANK);
        GameRegistry.addShapedRecipe(new ItemStack(AtumBlocks.BLOCK_DEADWOOD_HATCH, 2), "ppp", "ppp", 'p', AtumBlocks.BLOCK_DEADWOOD_PLANK);
        GameRegistry.addShapedRecipe(new ItemStack(AtumBlocks.BLOCK_DEADWOOD_LADDER, 3), "s s", "sss", "s s", 's', new ItemStack(AtumItems.ITEM_STICK, 1, 1));
        GameRegistry.addShapedRecipe(new ItemStack(AtumBlocks.BLOCK_WOOD_SLAB, 6), "ppp", 'p', AtumBlocks.BLOCK_PLANKS);
        GameRegistry.addShapedRecipe(new ItemStack(AtumBlocks.BLOCK_WOOD_SLAB, 6, 1), "ppp", 'p', AtumBlocks.BLOCK_DEADWOOD_PLANK);
        GameRegistry.addShapedRecipe(new ItemStack(AtumBlocks.BLOCK_PALM_STAIRS, 4), "p  ", "pp ", "ppp", 'p', AtumBlocks.BLOCK_PLANKS);
        GameRegistry.addShapedRecipe(new ItemStack(AtumBlocks.BLOCK_DEADWOOD_STAIRS, 4), "p  ", "pp ", "ppp", 'p', AtumBlocks.BLOCK_DEADWOOD_PLANK);
        GameRegistry.addShapedRecipe(new ItemStack(AtumBlocks.BLOCK_CRATE), "ppp", "p p", "ppp", 'p', AtumBlocks.BLOCK_PLANKS);
        GameRegistry.addShapedRecipe(new ItemStack(AtumBlocks.BLOCK_CRATE, 1, 1), "ppp", "p p", "ppp", 'p', AtumBlocks.BLOCK_DEADWOOD_PLANK);
        GameRegistry.addShapedRecipe(new ItemStack(AtumItems.ITEM_STICK, 4), "p", "p", 'p', AtumBlocks.BLOCK_PLANKS);
        GameRegistry.addShapedRecipe(new ItemStack(AtumItems.ITEM_STICK, 4, 1), "p", "p", 'p', AtumBlocks.BLOCK_DEADWOOD_PLANK);

        String [] oreColours = Constants.ORE_DIC_COLOURS;

        for (int i = 0; i < oreColours.length; i++) {
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack (AtumBlocks.BLOCK_CRYSTALSTAINEDGLASS, 8, i), "GGG", "GDG", "GGG", 'G', AtumBlocks.BLOCK_CRYSTALGLASS, 'D', "dye" + oreColours[i]));
            GameRegistry.addRecipe(new ItemStack(AtumBlocks.BLOCK_THINCRYSTALSTAINEDGLASS, 16, i), "GGG", "GGG", 'G', new ItemStack (AtumBlocks.BLOCK_CRYSTALSTAINEDGLASS, 1, i));

            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack (AtumBlocks.BLOCK_FRAMEDSTAINEDGLASS, 8, i), "GGG", "GDG", "GGG", 'G', AtumBlocks.BLOCK_FRAMEDGLASS, 'D', "dye" + oreColours[i]));
            GameRegistry.addRecipe(new ItemStack(AtumBlocks.BLOCK_THINFRAMEDSTAINEDGLASS, 16, i), "GGG", "GGG", 'G', new ItemStack (AtumBlocks.BLOCK_FRAMEDSTAINEDGLASS, 1, i));

            GameRegistry.addRecipe(new ItemStack (AtumBlocks.BLOCK_FRAMEDSTAINEDGLASS, 1, i), " S ", "SGS", " S ", 'S', Items.stick, 'G', new ItemStack (AtumBlocks.BLOCK_CRYSTALSTAINEDGLASS, 1, i));
        }
    }

    private void addSmeltingRecipes() {
        FurnaceRecipes.smelting().func_151393_a(AtumBlocks.BLOCK_IRONORE, new ItemStack(Items.iron_ingot), 0.7F);
        FurnaceRecipes.smelting().func_151393_a(AtumBlocks.BLOCK_COALORE, new ItemStack(Items.coal), 0.1F);
        FurnaceRecipes.smelting().func_151393_a(AtumBlocks.BLOCK_REDSTONEORE, new ItemStack(Items.redstone), 0.7F);
        FurnaceRecipes.smelting().func_151393_a(AtumBlocks.BLOCK_LAPISORE, new ItemStack(Items.dye, 1, 4), 0.2F);
        FurnaceRecipes.smelting().func_151393_a(AtumBlocks.BLOCK_GOLDORE, new ItemStack(Items.gold_ingot), 1.0F);
        FurnaceRecipes.smelting().func_151393_a(AtumBlocks.BLOCK_DIAMONDORE, new ItemStack(Items.diamond), 1.0F);
        FurnaceRecipes.smelting().func_151393_a(AtumBlocks.BLOCK_LOG, new ItemStack(Items.coal, 1, 1), 0.15F);
        FurnaceRecipes.smelting().func_151393_a(AtumBlocks.BLOCK_LIMESTONECOBBLE, new ItemStack(AtumBlocks.BLOCK_STONE), 0.1F);
        FurnaceRecipes.smelting().func_151393_a(AtumBlocks.BLOCK_SAND, new ItemStack(AtumBlocks.BLOCK_CRYSTALGLASS), 0.1F);
    }

    private void addShapelessRecipes() {
        GameRegistry.addShapelessRecipe(new ItemStack(AtumBlocks.BLOCK_PLANKS, 4), AtumBlocks.BLOCK_LOG);
        GameRegistry.addShapelessRecipe(new ItemStack(AtumItems.desertHelmet), AtumItems.wandererHelmet, Items.iron_helmet);
        GameRegistry.addShapelessRecipe(new ItemStack(AtumItems.desertChest), AtumItems.wandererChest, Items.iron_chestplate);
        GameRegistry.addShapelessRecipe(new ItemStack(AtumItems.desertLegs), AtumItems.wandererLegs, Items.iron_leggings);
        GameRegistry.addShapelessRecipe(new ItemStack(AtumItems.desertBoots), AtumItems.wandererBoots, Items.iron_boots);
        GameRegistry.addShapelessRecipe(new ItemStack(Blocks.sand), AtumBlocks.BLOCK_SAND);
        GameRegistry.addShapelessRecipe(new ItemStack(Items.string, 3), AtumItems.ITEM_FLAX);
        GameRegistry.addShapelessRecipe(new ItemStack(AtumBlocks.BLOCK_DEADWOOD_PLANK, 4), AtumBlocks.BLOCK_DEADWOOD_LOG);
    }

}