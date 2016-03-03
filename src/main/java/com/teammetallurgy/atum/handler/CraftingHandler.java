package com.teammetallurgy.atum.handler;

import com.teammetallurgy.atum.blocks.AtumBlocks;
import com.teammetallurgy.atum.items.AtumItems;
import com.teammetallurgy.atum.utils.Constants;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class CraftingHandler {
    public void register() {
        addRecipes();
        addSmeltingRecipes();
        addShapelessRecipes();
    }

    private void addRecipes() {
        GameRegistry.addShapelessRecipe(new ItemStack(AtumBlocks.LARGEBRICK, 1, 2), new ItemStack(AtumBlocks.LARGEBRICK));
        GameRegistry.addShapelessRecipe(new ItemStack(AtumBlocks.LARGEBRICK, 1, 2), new ItemStack(AtumBlocks.LARGEBRICK, 1, 1));
        GameRegistry.addShapelessRecipe(new ItemStack(AtumBlocks.LARGESTONESTAIRSBREAKABLE), new ItemStack(AtumBlocks.LARGESTONESTAIRS));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(AtumBlocks.LARGEBRICK, 4, 2), "XX", "XX", 'X', AtumBlocks.STONE));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(AtumBlocks.SMALLBRICK, 4), "XX", "XX", 'X', AtumBlocks.LIMESTONECOBBLE));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(AtumBlocks.CARVEDBRICK, 1), AtumBlocks.STONE));
        GameRegistry.addRecipe(new ItemStack(AtumBlocks.SMOOTHSTAIRS, 4), "X  ", "XX ", "XXX", 'X', AtumBlocks.STONE);
        GameRegistry.addRecipe(new ItemStack(AtumBlocks.COBBLESTAIRS, 4), "X  ", "XX ", "XXX", 'X', AtumBlocks.LIMESTONECOBBLE);
        GameRegistry.addRecipe(new ItemStack(AtumBlocks.LARGESTONESTAIRSBREAKABLE, 4), "X  ", "XX ", "XXX", 'X', new ItemStack(AtumBlocks.LARGEBRICK, 1, 2));
        GameRegistry.addRecipe(new ItemStack(AtumBlocks.SMALLSTONESTAIRS, 4), "X  ", "XX ", "XXX", 'X', AtumBlocks.SMALLBRICK);
        GameRegistry.addRecipe(new ItemStack(AtumBlocks.SLABS, 6, 0), "XXX", 'X', AtumBlocks.STONE);
        GameRegistry.addRecipe(new ItemStack(AtumBlocks.SLABS, 6, 1), "XXX", 'X', AtumBlocks.LIMESTONECOBBLE);
        GameRegistry.addRecipe(new ItemStack(AtumBlocks.SLABS, 6, 2), "XXX", 'X', new ItemStack(AtumBlocks.LARGEBRICK, 1, 2));
        GameRegistry.addRecipe(new ItemStack(AtumBlocks.SLABS, 6, 3), "XXX", 'X', AtumBlocks.SMALLBRICK);
        GameRegistry.addRecipe(new ItemStack(AtumBlocks.WALL, 6, 0), "XXX", "XXX", 'X', AtumBlocks.STONE);
        GameRegistry.addRecipe(new ItemStack(AtumBlocks.WALL, 6, 1), "XXX", "XXX", 'X', AtumBlocks.LIMESTONECOBBLE);
        GameRegistry.addRecipe(new ItemStack(AtumBlocks.WALL, 6, 2), "XXX", "XXX", 'X', new ItemStack(AtumBlocks.LARGEBRICK, 1, 2));
        GameRegistry.addRecipe(new ItemStack(AtumBlocks.WALL, 6, 3), "XXX", "XXX", 'X', AtumBlocks.SMALLBRICK);
        GameRegistry.addRecipe(new ItemStack(AtumBlocks.FRAMEDGLASS), " X ", "XSX", " X ", 'X', Items.stick, 'S', AtumBlocks.CRYSTALGLASS);
        GameRegistry.addRecipe(new ItemStack(AtumBlocks.CRACKEDLARGEBRICK, 4), "XX", "XX", 'X', AtumItems.STONE_CHUNK);
        GameRegistry.addRecipe(new ItemStack(Items.experience_bottle), " X ", "XBX", " X ", 'X', AtumItems.ECTOPLASM, 'B', Items.potionitem);
        GameRegistry.addRecipe(new ItemStack(AtumItems.LIMESTONE_SWORD), "L", "L", "S", 'L', AtumBlocks.LIMESTONECOBBLE, 'S', Items.stick);
        GameRegistry.addRecipe(new ItemStack(AtumItems.LIMESTONE_SHOVEL), "L", "S", "S", 'L', AtumBlocks.LIMESTONECOBBLE, 'S', Items.stick);
        GameRegistry.addRecipe(new ItemStack(AtumItems.LIMESTONE_PICKAXE), "LLL", " S ", " S ", 'L', AtumBlocks.LIMESTONECOBBLE, 'S', Items.stick);
        GameRegistry.addRecipe(new ItemStack(AtumItems.LIMESTONE_AXE), "LL", "LS", " S", 'L', AtumBlocks.LIMESTONECOBBLE, 'S', Items.stick);
        GameRegistry.addRecipe(new ItemStack(AtumItems.LIMESTONE_HOE), "LL", " S", " S", 'L', AtumBlocks.LIMESTONECOBBLE, 'S', Items.stick);
        GameRegistry.addRecipe(new ItemStack(AtumItems.MUMMY_HELMET), "XXX", "X X", 'X', AtumItems.SCRAP);
        GameRegistry.addRecipe(new ItemStack(AtumItems.MUMMY_CHEST), "X X", "XXX", "XXX", 'X', AtumItems.SCRAP);
        GameRegistry.addRecipe(new ItemStack(AtumItems.MUMMY_LEGS), "XXX", "X X", "X X", 'X', AtumItems.SCRAP);
        GameRegistry.addRecipe(new ItemStack(AtumItems.MUMMY_BOOTS), "X X", "X X", 'X', AtumItems.SCRAP);
        GameRegistry.addRecipe(new ItemStack(AtumItems.WANDERER_HELMET), "XXX", "X X", 'X', AtumItems.LINEN);
        GameRegistry.addRecipe(new ItemStack(AtumItems.WANDERER_CHEST), "X X", "XXX", "XXX", 'X', AtumItems.LINEN);
        GameRegistry.addRecipe(new ItemStack(AtumItems.WANDERER_LEGS), "XXX", "X X", "X X", 'X', AtumItems.LINEN);
        GameRegistry.addRecipe(new ItemStack(AtumItems.WANDERER_BOOTS), "X X", "X X", 'X', AtumItems.LINEN);
        GameRegistry.addRecipe(new ItemStack(AtumItems.LINEN), "XXX", 'X', AtumItems.FLAX);
        GameRegistry.addRecipe(new ItemStack(Items.glass_bottle, 3), "X X", " X ", 'X', AtumBlocks.CRYSTALGLASS);
        GameRegistry.addRecipe(new ItemStack(AtumBlocks.THINCRYSTALGLASS, 16), "XXX", "XXX", 'X', AtumBlocks.CRYSTALGLASS);
        GameRegistry.addRecipe(new ItemStack(AtumBlocks.THINFRAMEDGLASS, 16), "XXX", "XXX", 'X', AtumBlocks.FRAMEDGLASS);
        GameRegistry.addRecipe(new ItemStack(AtumItems.SCROLL), "XXX", "SXS", "XXX", 'X', AtumItems.PAPYRUS_PLANT, 'S', Items.stick);
        GameRegistry.addRecipe(new ItemStack(AtumItems.SCARAB), " G ", "GDG", " G ", 'G', Items.gold_ingot, 'D', Items.diamond);
        GameRegistry.addRecipe(new ItemStack(AtumBlocks.FURNACEIDLE), "XXX", "X X", "XXX", 'X', AtumBlocks.LIMESTONECOBBLE);
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.dye, 1, 15), new ItemStack(AtumItems.DUSTY_BONE)));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(AtumItems.GOLDEN_DATE, 1, 0), "###", "#X#", "###", '#', Items.gold_ingot, 'X', AtumItems.DATE));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(AtumItems.GOLDEN_DATE, 1, 1), "###", "#X#", "###", '#', Blocks.gold_block, 'X', AtumItems.DATE));
        GameRegistry.addShapedRecipe(new ItemStack(AtumItems.PALM_DOOR), "pp ", "pp ", "pp ", 'p', AtumBlocks.PLANKS);
        GameRegistry.addShapedRecipe(new ItemStack(AtumBlocks.PALM_FENCE), "sss", "sss", 's', new ItemStack(AtumItems.STICK, 1, 0));
        GameRegistry.addShapedRecipe(new ItemStack(AtumBlocks.PALM_FENCE_GATE), "sps", "sps", 's', new ItemStack(AtumItems.STICK, 1, 0), 'p', AtumBlocks.PLANKS);
        GameRegistry.addShapedRecipe(new ItemStack(AtumBlocks.PALM_HATCH, 2), "ppp", "ppp", 'p', AtumBlocks.PLANKS);
        GameRegistry.addShapedRecipe(new ItemStack(AtumBlocks.PALM_LADDER, 3), "s s", "sss", "s s", 's', new ItemStack(AtumItems.STICK, 1, 0));
        GameRegistry.addShapedRecipe(new ItemStack(AtumItems.DEADWOOD_DOOR), "pp ", "pp ", "pp ", 'p', AtumBlocks.DEADWOOD_PLANK);
        GameRegistry.addShapedRecipe(new ItemStack(AtumBlocks.DEADWOOD_FENCE), "sss", "sss", 's', new ItemStack(AtumItems.STICK, 1, 1));
        GameRegistry.addShapedRecipe(new ItemStack(AtumBlocks.DEADWOOD_FENCE_GATE), "sps", "sps", 's', new ItemStack(AtumItems.STICK, 1, 1), 'p', AtumBlocks.DEADWOOD_PLANK);
        GameRegistry.addShapedRecipe(new ItemStack(AtumBlocks.DEADWOOD_HATCH, 2), "ppp", "ppp", 'p', AtumBlocks.DEADWOOD_PLANK);
        GameRegistry.addShapedRecipe(new ItemStack(AtumBlocks.DEADWOOD_LADDER, 3), "s s", "sss", "s s", 's', new ItemStack(AtumItems.STICK, 1, 1));
        GameRegistry.addShapedRecipe(new ItemStack(AtumBlocks.WOOD_SLAB, 6), "ppp", 'p', AtumBlocks.PLANKS);
        GameRegistry.addShapedRecipe(new ItemStack(AtumBlocks.WOOD_SLAB, 6, 1), "ppp", 'p', AtumBlocks.DEADWOOD_PLANK);
        GameRegistry.addShapedRecipe(new ItemStack(AtumBlocks.PALM_STAIRS, 4), "p  ", "pp ", "ppp", 'p', AtumBlocks.PLANKS);
        GameRegistry.addShapedRecipe(new ItemStack(AtumBlocks.DEADWOOD_STAIRS, 4), "p  ", "pp ", "ppp", 'p', AtumBlocks.DEADWOOD_PLANK);
        GameRegistry.addShapedRecipe(new ItemStack(AtumBlocks.CRATE), "ppp", "p p", "ppp", 'p', AtumBlocks.PLANKS);
        GameRegistry.addShapedRecipe(new ItemStack(AtumBlocks.CRATE, 1, 1), "ppp", "p p", "ppp", 'p', AtumBlocks.DEADWOOD_PLANK);
        GameRegistry.addShapedRecipe(new ItemStack(AtumItems.STICK, 4), "p", "p", 'p', AtumBlocks.PLANKS);
        GameRegistry.addShapedRecipe(new ItemStack(AtumItems.STICK, 4, 1), "p", "p", 'p', AtumBlocks.DEADWOOD_PLANK);

        String[] oreColours = Constants.ORE_DIC_COLOURS;

        for (int i = 0; i < oreColours.length; i++) {
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(AtumBlocks.CRYSTALSTAINEDGLASS, 8, i), "GGG", "GDG", "GGG", 'G', AtumBlocks.CRYSTALGLASS, 'D', "dye" + oreColours[i]));
            GameRegistry.addRecipe(new ItemStack(AtumBlocks.THINCRYSTALSTAINEDGLASS, 16, i), "GGG", "GGG", 'G', new ItemStack(AtumBlocks.CRYSTALSTAINEDGLASS, 1, i));

            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(AtumBlocks.FRAMEDSTAINEDGLASS, 8, i), "GGG", "GDG", "GGG", 'G', AtumBlocks.FRAMEDGLASS, 'D', "dye" + oreColours[i]));
            GameRegistry.addRecipe(new ItemStack(AtumBlocks.THINFRAMEDSTAINEDGLASS, 16, i), "GGG", "GGG", 'G', new ItemStack(AtumBlocks.FRAMEDSTAINEDGLASS, 1, i));

            GameRegistry.addRecipe(new ItemStack(AtumBlocks.FRAMEDSTAINEDGLASS, 1, i), " S ", "SGS", " S ", 'S', Items.stick, 'G', new ItemStack(AtumBlocks.CRYSTALSTAINEDGLASS, 1, i));
        }
    }

    private void addSmeltingRecipes() {
        GameRegistry.addSmelting(AtumBlocks.IRONORE, new ItemStack(Items.iron_ingot), 0.7F);
        GameRegistry.addSmelting(AtumBlocks.COALORE, new ItemStack(Items.coal), 0.1F);
        GameRegistry.addSmelting(AtumBlocks.REDSTONEORE, new ItemStack(Items.redstone), 0.7F);
        GameRegistry.addSmelting(AtumBlocks.LAPISORE, new ItemStack(Items.dye, 1, 4), 0.2F);
        GameRegistry.addSmelting(AtumBlocks.GOLDORE, new ItemStack(Items.gold_ingot), 1.0F);
        GameRegistry.addSmelting(AtumBlocks.DIAMONDORE, new ItemStack(Items.diamond), 1.0F);
        GameRegistry.addSmelting(AtumBlocks.LOG, new ItemStack(Items.coal, 1, 1), 0.15F);
        GameRegistry.addSmelting(AtumBlocks.LIMESTONECOBBLE, new ItemStack(AtumBlocks.STONE), 0.1F);
        GameRegistry.addSmelting(AtumBlocks.SAND, new ItemStack(AtumBlocks.CRYSTALGLASS), 0.1F);
        GameRegistry.addSmelting(AtumBlocks.SAND, new ItemStack(AtumBlocks.CRYSTALGLASS), 0.1F);
    }

    private void addShapelessRecipes() {
        GameRegistry.addShapelessRecipe(new ItemStack(AtumBlocks.PLANKS, 4), AtumBlocks.LOG);
        GameRegistry.addShapelessRecipe(new ItemStack(AtumItems.DESERT_HELMET), AtumItems.WANDERER_HELMET, Items.iron_helmet);
        GameRegistry.addShapelessRecipe(new ItemStack(AtumItems.DESERT_CHEST), AtumItems.WANDERER_CHEST, Items.iron_chestplate);
        GameRegistry.addShapelessRecipe(new ItemStack(AtumItems.DESERT_LEGS), AtumItems.WANDERER_LEGS, Items.iron_leggings);
        GameRegistry.addShapelessRecipe(new ItemStack(AtumItems.DESERT_BOOTS), AtumItems.WANDERER_BOOTS, Items.iron_boots);
        GameRegistry.addShapelessRecipe(new ItemStack(Blocks.sand), AtumBlocks.SAND);
        GameRegistry.addShapelessRecipe(new ItemStack(Items.string, 3), AtumItems.FLAX);
        GameRegistry.addShapelessRecipe(new ItemStack(AtumBlocks.DEADWOOD_PLANK, 4), AtumBlocks.DEADWOOD_LOG);
    }
}