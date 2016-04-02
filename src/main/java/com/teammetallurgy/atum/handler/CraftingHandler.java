package com.teammetallurgy.atum.handler;

import com.teammetallurgy.atum.blocks.AtumBlocks;
import com.teammetallurgy.atum.blocks.BlockLimestoneBricks;
import com.teammetallurgy.atum.blocks.BlockAtumPlank;
import com.teammetallurgy.atum.items.AtumItems;
import com.teammetallurgy.atum.utils.Constants;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class CraftingHandler {
    public static void register() {
        addRecipes();
        addSmeltingRecipes();
        addShapelessRecipes();
    }

    private static void addRecipes() {
        GameRegistry.addShapelessRecipe(new ItemStack(AtumBlocks.LIMESTONEBRICK, 1, BlockLimestoneBricks.EnumType.LARGE.getMetadata()), new ItemStack(AtumBlocks.LIMESTONEBRICK));
        GameRegistry.addShapelessRecipe(new ItemStack(AtumBlocks.LIMESTONEBRICK, 1, BlockLimestoneBricks.EnumType.LARGE.getMetadata()), new ItemStack(AtumBlocks.LIMESTONEBRICK, 1, BlockLimestoneBricks.EnumType.LARGE.getMetadata()));
        GameRegistry.addShapelessRecipe(new ItemStack(AtumBlocks.LARGE_STONE_STAIRS_BREAKABLE), new ItemStack(AtumBlocks.LARGE_STONE_STAIRS)); //TODO
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(AtumBlocks.LIMESTONEBRICK, 4, BlockLimestoneBricks.EnumType.LARGE.getMetadata()), "XX", "XX", 'X', AtumBlocks.LIMESTONE));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(AtumBlocks.LIMESTONEBRICK, 4, BlockLimestoneBricks.EnumType.SMALL.getMetadata()), "XX", "XX", 'X', AtumBlocks.LIMESTONE_CRACKED));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(AtumBlocks.LIMESTONEBRICK, 1, BlockLimestoneBricks.EnumType.CHISELED.getMetadata()), AtumBlocks.LIMESTONE));
        GameRegistry.addRecipe(new ItemStack(AtumBlocks.SMOOTH_STAIRS, 4), "X  ", "XX ", "XXX", 'X', AtumBlocks.LIMESTONE);
        GameRegistry.addRecipe(new ItemStack(AtumBlocks.COBBLE_STAIRS, 4), "X  ", "XX ", "XXX", 'X', AtumBlocks.LIMESTONE_CRACKED);
        GameRegistry.addRecipe(new ItemStack(AtumBlocks.LARGE_STONE_STAIRS_BREAKABLE, 4), "X  ", "XX ", "XXX", 'X', new ItemStack(AtumBlocks.LIMESTONEBRICK, 1, BlockLimestoneBricks.EnumType.LARGE.getMetadata()));
        GameRegistry.addRecipe(new ItemStack(AtumBlocks.SMALL_STONE_STAIRS, 4), "X  ", "XX ", "XXX", 'X', new ItemStack(AtumBlocks.LIMESTONEBRICK, 1, BlockLimestoneBricks.EnumType.SMALL.getMetadata()));
        GameRegistry.addRecipe(new ItemStack(AtumBlocks.LIMESTONE_SLAB, 6, 0), "XXX", 'X', AtumBlocks.LIMESTONE);
        GameRegistry.addRecipe(new ItemStack(AtumBlocks.LIMESTONE_SLAB, 6, 1), "XXX", 'X', AtumBlocks.LIMESTONE_CRACKED);
        GameRegistry.addRecipe(new ItemStack(AtumBlocks.LIMESTONE_SLAB, 6, 2), "XXX", 'X', new ItemStack(AtumBlocks.LIMESTONEBRICK, 1, BlockLimestoneBricks.EnumType.LARGE.getMetadata()));
        GameRegistry.addRecipe(new ItemStack(AtumBlocks.LIMESTONE_SLAB, 6, 3), "XXX", 'X', new ItemStack(AtumBlocks.LIMESTONEBRICK, 1, BlockLimestoneBricks.EnumType.SMALL.getMetadata()));
        GameRegistry.addRecipe(new ItemStack(AtumBlocks.WALL, 6, 0), "XXX", "XXX", 'X', AtumBlocks.LIMESTONE);
        GameRegistry.addRecipe(new ItemStack(AtumBlocks.WALL, 6, 1), "XXX", "XXX", 'X', AtumBlocks.LIMESTONE_CRACKED);
        GameRegistry.addRecipe(new ItemStack(AtumBlocks.WALL, 6, 2), "XXX", "XXX", 'X', new ItemStack(AtumBlocks.LIMESTONEBRICK, 1, BlockLimestoneBricks.EnumType.LARGE.getMetadata()));
        GameRegistry.addRecipe(new ItemStack(AtumBlocks.WALL, 6, 3), "XXX", "XXX", 'X', new ItemStack(AtumBlocks.LIMESTONEBRICK, 1, BlockLimestoneBricks.EnumType.SMALL.getMetadata()));
        GameRegistry.addRecipe(new ItemStack(AtumBlocks.FRAMED_GLASS), " X ", "XSX", " X ", 'X', Items.stick, 'S',  AtumBlocks.CRYSTAL_GLASS);
        //GameRegistry.addRecipe(new ItemStack(AtumBlocks.CRACKEDLARGEBRICK, 4), "XX", "XX", 'X', AtumItems.STONE_CHUNK); //TODO
        GameRegistry.addRecipe(new ItemStack(Items.experience_bottle), " X ", "XBX", " X ", 'X', AtumItems.ECTOPLASM, 'B', Items.potionitem);
        GameRegistry.addRecipe(new ItemStack(AtumItems.LIMESTONE_SWORD), "L", "L", "S", 'L', AtumBlocks.LIMESTONE_CRACKED, 'S', Items.stick);
        GameRegistry.addRecipe(new ItemStack(AtumItems.LIMESTONE_SHOVEL), "L", "S", "S", 'L', AtumBlocks.LIMESTONE_CRACKED, 'S', Items.stick);
        GameRegistry.addRecipe(new ItemStack(AtumItems.LIMESTONE_PICKAXE), "LLL", " S ", " S ", 'L', AtumBlocks.LIMESTONE_CRACKED, 'S', Items.stick);
        GameRegistry.addRecipe(new ItemStack(AtumItems.LIMESTONE_AXE), "LL", "LS", " S", 'L', AtumBlocks.LIMESTONE_CRACKED, 'S', Items.stick);
        GameRegistry.addRecipe(new ItemStack(AtumItems.LIMESTONE_HOE), "LL", " S", " S", 'L', AtumBlocks.LIMESTONE_CRACKED, 'S', Items.stick);
        GameRegistry.addRecipe(new ItemStack(AtumItems.MUMMY_HELMET), "XXX", "X X", 'X', AtumItems.SCRAP);
        GameRegistry.addRecipe(new ItemStack(AtumItems.MUMMY_CHEST), "X X", "XXX", "XXX", 'X', AtumItems.SCRAP);
        GameRegistry.addRecipe(new ItemStack(AtumItems.MUMMY_LEGS), "XXX", "X X", "X X", 'X', AtumItems.SCRAP);
        GameRegistry.addRecipe(new ItemStack(AtumItems.MUMMY_BOOTS), "X X", "X X", 'X', AtumItems.SCRAP);
        GameRegistry.addRecipe(new ItemStack(AtumItems.WANDERER_HELMET), "XXX", "X X", 'X', AtumItems.LINEN);
        GameRegistry.addRecipe(new ItemStack(AtumItems.WANDERER_CHEST), "X X", "XXX", "XXX", 'X', AtumItems.LINEN);
        GameRegistry.addRecipe(new ItemStack(AtumItems.WANDERER_LEGS), "XXX", "X X", "X X", 'X', AtumItems.LINEN);
        GameRegistry.addRecipe(new ItemStack(AtumItems.WANDERER_BOOTS), "X X", "X X", 'X', AtumItems.LINEN);
        GameRegistry.addRecipe(new ItemStack(AtumItems.LINEN), "XXX", 'X', AtumItems.FLAX);
        GameRegistry.addRecipe(new ItemStack(Items.glass_bottle, 3), "X X", " X ", 'X', AtumBlocks.CRYSTAL_GLASS);
        GameRegistry.addRecipe(new ItemStack(AtumBlocks.THIN_CRYSTAL_GLASS, 16), "XXX", "XXX", 'X', AtumBlocks.CRYSTAL_GLASS);
        GameRegistry.addRecipe(new ItemStack(AtumBlocks.THIN_FRAMED_GLASS, 16), "XXX", "XXX", 'X', AtumBlocks.FRAMED_GLASS);
        GameRegistry.addRecipe(new ItemStack(AtumItems.SCROLL), "XXX", "SXS", "XXX", 'X', AtumItems.PAPYRUS_PLANT, 'S', Items.stick);
        GameRegistry.addRecipe(new ItemStack(AtumItems.SCARAB), " G ", "GDG", " G ", 'G', Items.gold_ingot, 'D', Items.diamond);
        GameRegistry.addRecipe(new ItemStack(AtumBlocks.LIMESTONE_FURNACE), "XXX", "X X", "XXX", 'X', AtumBlocks.LIMESTONE_CRACKED);
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.dye, 1, 15), new ItemStack(AtumItems.DUSTY_BONE)));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(AtumItems.GOLDEN_DATE, 1, 0), "###", "#X#", "###", '#', Items.gold_ingot, 'X', AtumItems.DATE));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(AtumItems.GOLDEN_DATE, 1, 1), "###", "#X#", "###", '#', Blocks.gold_block, 'X', AtumItems.DATE));
        GameRegistry.addShapedRecipe(new ItemStack(AtumItems.PALM_DOOR), "pp ", "pp ", "pp ", 'p', new ItemStack(AtumBlocks.PLANKS, 1, BlockAtumPlank.EnumType.PALM.getMetadata()));
        GameRegistry.addShapedRecipe(new ItemStack(AtumBlocks.PALM_FENCE), "sss", "sss", 's', new ItemStack(AtumItems.STICK, 1, 0));
        GameRegistry.addShapedRecipe(new ItemStack(AtumBlocks.PALM_FENCE_GATE), "sps", "sps", 's', new ItemStack(AtumItems.STICK, 1, 0), 'p', new ItemStack(AtumBlocks.PLANKS, 1, BlockAtumPlank.EnumType.PALM.getMetadata()));
        GameRegistry.addShapedRecipe(new ItemStack(AtumBlocks.PALM_HATCH, 2), "ppp", "ppp", 'p', new ItemStack(AtumBlocks.PLANKS, 1, BlockAtumPlank.EnumType.PALM.getMetadata()));
        GameRegistry.addShapedRecipe(new ItemStack(AtumBlocks.PALM_LADDER, 3), "s s", "sss", "s s", 's', new ItemStack(AtumItems.STICK, 1, 0));
        GameRegistry.addShapedRecipe(new ItemStack(AtumItems.DEADWOOD_DOOR), "pp ", "pp ", "pp ", 'p', new ItemStack(AtumBlocks.PLANKS, 1, BlockAtumPlank.EnumType.DEADWOOD.getMetadata()));
        GameRegistry.addShapedRecipe(new ItemStack(AtumBlocks.DEADWOOD_FENCE), "sss", "sss", 's', new ItemStack(AtumItems.STICK, 1, 1));
        GameRegistry.addShapedRecipe(new ItemStack(AtumBlocks.DEADWOOD_FENCE_GATE), "sps", "sps", 's', new ItemStack(AtumItems.STICK, 1, 1), 'p', new ItemStack(AtumBlocks.PLANKS, 1, BlockAtumPlank.EnumType.DEADWOOD.getMetadata()));
        GameRegistry.addShapedRecipe(new ItemStack(AtumBlocks.DEADWOOD_HATCH, 2), "ppp", "ppp", 'p', new ItemStack(AtumBlocks.PLANKS, 1, BlockAtumPlank.EnumType.DEADWOOD.getMetadata()));
        GameRegistry.addShapedRecipe(new ItemStack(AtumBlocks.DEADWOOD_LADDER, 3), "s s", "sss", "s s", 's', new ItemStack(AtumItems.STICK, 1, 1));
        GameRegistry.addShapedRecipe(new ItemStack(AtumBlocks.WOOD_SLAB, 6), "ppp", 'p', new ItemStack(AtumBlocks.PLANKS, 1, BlockAtumPlank.EnumType.PALM.getMetadata()));
        GameRegistry.addShapedRecipe(new ItemStack(AtumBlocks.WOOD_SLAB, 6, 1), "ppp", 'p', new ItemStack(AtumBlocks.PLANKS, 1, BlockAtumPlank.EnumType.DEADWOOD.getMetadata()));
        GameRegistry.addShapedRecipe(new ItemStack(AtumBlocks.PALM_STAIRS, 4), "p  ", "pp ", "ppp", 'p', new ItemStack(AtumBlocks.PLANKS, 1, BlockAtumPlank.EnumType.PALM.getMetadata()));
        GameRegistry.addShapedRecipe(new ItemStack(AtumBlocks.DEADWOOD_STAIRS, 4), "p  ", "pp ", "ppp", 'p', new ItemStack(AtumBlocks.PLANKS, 1, BlockAtumPlank.EnumType.DEADWOOD.getMetadata()));
        GameRegistry.addShapedRecipe(new ItemStack(AtumBlocks.CRATE), "ppp", "p p", "ppp", 'p', new ItemStack(AtumBlocks.PLANKS, 1, BlockAtumPlank.EnumType.PALM.getMetadata()));
        GameRegistry.addShapedRecipe(new ItemStack(AtumBlocks.CRATE, 1, 1), "ppp", "p p", "ppp", 'p', new ItemStack(AtumBlocks.PLANKS, 1, BlockAtumPlank.EnumType.DEADWOOD.getMetadata()));
        GameRegistry.addShapedRecipe(new ItemStack(AtumItems.STICK, 4), "p", "p", 'p', new ItemStack(AtumBlocks.PLANKS, 1, BlockAtumPlank.EnumType.PALM.getMetadata()));
        GameRegistry.addShapedRecipe(new ItemStack(AtumItems.STICK, 4, 1), "p", "p", 'p', new ItemStack(AtumBlocks.PLANKS, 1, BlockAtumPlank.EnumType.DEADWOOD.getMetadata()));

        String[] oreColours = Constants.ORE_DIC_COLOURS;

        for (int i = 0; i < oreColours.length; i++) {
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(AtumBlocks.CRYSTAL_STAINED_GLASS, 8, i), "GGG", "GDG", "GGG", 'G', AtumBlocks.CRYSTAL_GLASS, 'D', "dye" + oreColours[i]));
            GameRegistry.addRecipe(new ItemStack(AtumBlocks.THIN_CRYSTAL_STAINED_GLASS, 16, i), "GGG", "GGG", 'G', new ItemStack(AtumBlocks.CRYSTAL_STAINED_GLASS, 1, i));

            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(AtumBlocks.FRAMED_STAINED_GLASS, 8, i), "GGG", "GDG", "GGG", 'G', AtumBlocks.FRAMED_GLASS, 'D', "dye" + oreColours[i]));
            GameRegistry.addRecipe(new ItemStack(AtumBlocks.THIN_FRAMED_STAINED_GLASS, 16, i), "GGG", "GGG", 'G', new ItemStack(AtumBlocks.FRAMED_STAINED_GLASS, 1, i));

            GameRegistry.addRecipe(new ItemStack(AtumBlocks.FRAMED_STAINED_GLASS, 1, i), " S ", "SGS", " S ", 'S', Items.stick, 'G', new ItemStack(AtumBlocks.CRYSTAL_STAINED_GLASS, 1, i));
        }
    }

    private static void addSmeltingRecipes() {
        GameRegistry.addSmelting(AtumBlocks.IRON_ORE, new ItemStack(Items.iron_ingot), 0.7F);
        GameRegistry.addSmelting(AtumBlocks.COAL_ORE, new ItemStack(Items.coal), 0.1F);
        GameRegistry.addSmelting(AtumBlocks.REDSTONE_ORE, new ItemStack(Items.redstone), 0.7F);
        GameRegistry.addSmelting(AtumBlocks.LAPIS_ORE, new ItemStack(Items.dye, 1, 4), 0.2F);
        GameRegistry.addSmelting(AtumBlocks.GOLD_ORE, new ItemStack(Items.gold_ingot), 1.0F);
        GameRegistry.addSmelting(AtumBlocks.DIAMOND_ORE, new ItemStack(Items.diamond), 1.0F);
        GameRegistry.addSmelting(AtumBlocks.LOG, new ItemStack(Items.coal, 1, 1), 0.15F);
        GameRegistry.addSmelting(AtumBlocks.LIMESTONE_CRACKED, new ItemStack(AtumBlocks.LIMESTONE), 0.1F);
        GameRegistry.addSmelting(AtumBlocks.SAND, new ItemStack(AtumBlocks.CRYSTAL_GLASS), 0.1F);
        GameRegistry.addSmelting(AtumBlocks.SAND, new ItemStack(AtumBlocks.CRYSTAL_GLASS), 0.1F);
    }

    private static void addShapelessRecipes() {
        GameRegistry.addShapelessRecipe(new ItemStack(AtumBlocks.PLANKS, 4, BlockAtumPlank.EnumType.PALM.getMetadata()), new ItemStack(AtumBlocks.LOG, 1, BlockAtumPlank.EnumType.PALM.getMetadata()));
        GameRegistry.addShapelessRecipe(new ItemStack(AtumItems.DESERT_HELMET), AtumItems.WANDERER_HELMET, Items.iron_helmet);
        GameRegistry.addShapelessRecipe(new ItemStack(AtumItems.DESERT_CHEST), AtumItems.WANDERER_CHEST, Items.iron_chestplate);
        GameRegistry.addShapelessRecipe(new ItemStack(AtumItems.DESERT_LEGS), AtumItems.WANDERER_LEGS, Items.iron_leggings);
        GameRegistry.addShapelessRecipe(new ItemStack(AtumItems.DESERT_BOOTS), AtumItems.WANDERER_BOOTS, Items.iron_boots);
        GameRegistry.addShapelessRecipe(new ItemStack(Blocks.sand), AtumBlocks.SAND);
        GameRegistry.addShapelessRecipe(new ItemStack(Items.string, 3), AtumItems.FLAX);
        GameRegistry.addShapelessRecipe(new ItemStack(AtumBlocks.PLANKS, 4 , BlockAtumPlank.EnumType.DEADWOOD.getMetadata()), new ItemStack(AtumBlocks.LOG, 1, BlockAtumPlank.EnumType.DEADWOOD.getMetadata()));
    }
}