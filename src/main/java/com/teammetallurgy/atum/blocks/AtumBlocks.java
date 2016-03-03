package com.teammetallurgy.atum.blocks;

import com.teammetallurgy.atum.Atum;
import com.teammetallurgy.atum.blocks.tileentity.TileEntityBurningTrap;
import com.teammetallurgy.atum.blocks.tileentity.chests.TileEntityChestSpawner;
import com.teammetallurgy.atum.blocks.tileentity.chests.TileEntityPharaohChest;
import com.teammetallurgy.atum.blocks.tileentity.crate.TileEntityCrate;
import com.teammetallurgy.atum.blocks.tileentity.furnace.TileEntityLimestoneFurnace;
import com.teammetallurgy.atum.items.ItemBlockBricks;
import com.teammetallurgy.atum.items.ItemBlockCrate;
import com.teammetallurgy.atum.items.ItemBlockStainedGlass;
import com.teammetallurgy.atum.utils.Constants;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGravel;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class AtumBlocks {
    public static BlockPortal PORTAL = new BlockPortal();
    public static Block CURSEDCHEST = new BlockChestSpawner();
    public static Block SAND = new BlockSands();
    public static Block LIMESTONEGRAVEL = new BlockGravel().setHardness(0.6F).setStepSound(Block.soundTypeGravel);
    public static Block LIMESTONE = new BlockAtumStone();
    public static Block LIMESTONE_CRACKED = new BlockAtum();
    public static Block LIMESTONE_BRICK = new BlockAtumBricks();
    public static Block WALL = new BlockWalls(LIMESTONE);
    public static BlockSlab SLABS = new BlockAtumSlab(false);
    public static BlockSlab DOUBLESLAB = new BlockAtumSlab(true);
    public static Block SMOOTHSTAIRS = new BlockAtumStairs(LIMESTONE.getDefaultState());
    public static Block COBBLESTAIRS = new BlockAtumStairs(LIMESTONE_CRACKED.getDefaultState());
    public static Block LARGESTONESTAIRS = new BlockAtumStairs(LIMESTONE.getDefaultState().withProperty(BlockAtumBricks.VARIANT, BlockAtumBricks.EnumType.LARGE));
    public static Block LARGESTONESTAIRSBREAKABLE = new BlockAtumStairs(LIMESTONE.getDefaultState().withProperty(BlockAtumBricks.VARIANT, BlockAtumBricks.EnumType.LARGE)).setHardness(2.0F).setResistance(10.0F);
    public static Block SMALLSTONESTAIRS = new BlockAtumStairs(LIMESTONE.getDefaultState().withProperty(BlockAtumBricks.VARIANT, BlockAtumBricks.EnumType.SMALL));
    public static Block SANDLAYERED = new BlockSandLayered();
    public static Block CRACKEDLARGEBRICK = new BlockAtum();
    public static Block CRYSTALGLASS = new BlockAtumGlass(Material.glass);
    public static Block CRYSTALSTAINEDGLASS = new BlockAtumGlassStained();
    public static Block FRAMEDGLASS = new BlockAtumGlass(Material.glass);
    public static Block FRAMEDSTAINEDGLASS = new BlockAtumGlassStained();
    public static Block PALMSAPLING = new BlockPalmSapling();
    public static Block DATEBLOCK = new BlockDate();
    public static BlockShrub SHRUB = new BlockShrub();
    public static Block WEED = new BlockShrub();
    public static Block PAPYRUS = new BlockPapyrus();
    public static Block FLAX = new BlockFlax();
    public static Block FERTILESOIL = new BlockFertileSoil();
    public static Block FERTILESOILTILLED = new BlockFertileSoilTilled();
    public static Block LOG = new BlockPalmLog();
    public static Block LEAVES = new BlockLeave();
    public static Block PLANKS = new BlockAtumPlank();
    public static Block PALM_DOOR = new BlockAtumDoor().setHardness(3.0F).setStepSound(Block.soundTypeWood);
    public static Block PALM_FENCE = new BlockAtumFence(Material.wood).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundTypeWood);
    public static Block PALM_FENCE_GATE = new BlockAtumFenceGate().setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundTypeWood);
    public static Block PALM_HATCH = new BlockAtumTrapDoor(Material.wood).setHardness(3.0F).setStepSound(Block.soundTypeWood);
    public static Block PALM_LADDER = new BlockAtumLadder().setHardness(0.4F).setStepSound(Block.soundTypeLadder);
    public static Block THINCRYSTALGLASS = new BlockAtumPane();
    public static Block THINCRYSTALSTAINEDGLASS = new BlockAtumPaneStained();
    public static Block THINFRAMEDGLASS = new BlockAtumPane();
    public static Block THINFRAMEDSTAINEDGLASS = new BlockAtumPaneStained();
    public static Block TRAPARROW = new BlockBurningTrap();
    public static Block PHARAOHCHEST = new BlockPharaohChest();
    public static Block REDSTONEORE = new BlockAtumRedstone();
    public static Block COALORE = new BlockAtumOres();
    public static Block IRONORE = new BlockAtumOres();
    public static Block GOLDORE = new BlockAtumOres();
    public static Block LAPISORE = new BlockAtumOres();
    public static Block DIAMONDORE = new BlockAtumOres();
    public static Block FURNACE = new BlockLimeStoneFurnace(false);
    public static Block FURNACE_LIT = new BlockLimeStoneFurnace(true);
    public static Block DEADWOOD_LOG = new BlockDeadwoodLog();
    public static Block DEADWOOD_DOOR = new BlockAtumDoor().setHardness(3.0F).setStepSound(Block.soundTypeWood);
    public static Block DEADWOOD_FENCE = new BlockAtumFence(Material.wood).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundTypeWood);
    public static Block DEADWOOD_FENCE_GATE = new BlockAtumFenceGate().setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundTypeWood);
    public static Block DEADWOOD_HATCH = new BlockAtumTrapDoor(Material.wood).setHardness(3.0F).setStepSound(Block.soundTypeWood);
    public static Block DEADWOOD_LADDER = new BlockAtumLadder().setHardness(0.4F).setStepSound(Block.soundTypeLadder);
    public static BlockAtumWoodSlab WOOD_SLAB = new BlockAtumWoodSlab(false);
    public static BlockAtumWoodSlab WOOD_DOUBLESLAB = new BlockAtumWoodSlab(true);
    public static Block PALM_STAIRS = new BlockAtumStairs(PLANKS.getDefaultState().withProperty(BlockAtumPlank.VARIANT, BlockAtumPlank.EnumType.PALM));
    public static Block DEADWOOD_STAIRS = new BlockAtumStairs(PLANKS.getDefaultState().withProperty(BlockAtumPlank.VARIANT, BlockAtumPlank.EnumType.DEADWOOD));
    public static Block CRATE = new BlockCrate();

    public AtumBlocks() {
        registerBlocks();
    }

    public void registerBlocks() {
        register(PORTAL, "portal", null);
        register(CURSEDCHEST, "chestSpawner");
        register(SAND, "sand");
        register(LIMESTONEGRAVEL, "limestoneGravel");
        register(LIMESTONE, "limestone");
        register(LIMESTONE_CRACKED, "cobble");
        register(LIMESTONE_BRICK, ""); //TODO name
        register(SMOOTHSTAIRS, "smoothStairs");
        register(COBBLESTAIRS, "cobbleStairs");
        register(LARGESTONESTAIRS, "largeStairsUnbreakable");
        register(LARGESTONESTAIRSBREAKABLE, "largeStairs");
        register(SMALLSTONESTAIRS, "smallStairs");
        register(SANDLAYERED, "sandLayer");
        register(CRACKEDLARGEBRICK, "crackedLargeBrick");
        register(CRYSTALGLASS, "crystalGlass");
        register(FRAMEDGLASS, "framedGlass");
        register(PALMSAPLING, "palmSapling");
        register(DATEBLOCK, "date", null);
        register(SHRUB, "shrub");
        register(WEED, "weed");
        register(PAPYRUS, "papyrus", null);
        register(FLAX, "flax", null);
        register(FERTILESOIL, "fertileSoil");
        register(FERTILESOILTILLED, "fertileSoilTilled");
        register(LOG, "palmLog");
        register(LEAVES, "palmLeaves");
        register(PLANKS, "palmPlanks");
        register(PALM_DOOR, "palmDoor");
        register(PALM_FENCE, "palmFence");
        register(PALM_FENCE_GATE, "palmFenceGate");
        register(PALM_HATCH, "palmHatch");
        register(PALM_LADDER, "palmLadder");
        register(THINCRYSTALGLASS, "thinCrystalGlass");
        register(THINFRAMEDGLASS, "thinFramedGlass");
        register(TRAPARROW, "burningTrap");
        register(PHARAOHCHEST, "pharaohChest");
        register(REDSTONEORE, "redstoneOre");
        register(COALORE, "coalOre");
        register(IRONORE, "ironOre");
        register(GOLDORE, "goldOre");
        register(LAPISORE, "lapisOre");
        register(DIAMONDORE, "diamondOre");
        register(FURNACE, "furnaceIdle"); //TODO Name
        register(FURNACE_LIT, "furnaceBurning"); //TODO Name
        register(DEADWOOD_LOG, "deadwoodLog");
        register(DEADWOOD_DOOR, "deadwoodDoor");
        register(DEADWOOD_FENCE, "deadwoodFence");
        register(DEADWOOD_FENCE_GATE, "deadwoodFenceGate");
        register(DEADWOOD_HATCH, "deadwoodHatch");
        register(DEADWOOD_LADDER, "deadwoodLadder");
        register(PALM_STAIRS, "palmStairs");
        register(DEADWOOD_STAIRS, "deadwoodStairs");

        //registerItemBlock(LARGEBRICK, ItemBlockBricks.class, "largeBrick"); //TODO
        registerItemBlock(CRYSTALSTAINEDGLASS, ItemBlockStainedGlass.class, "crystalStainedGlass");
        registerItemBlock(FRAMEDSTAINEDGLASS, ItemBlockStainedGlass.class, "framedStainedGlass");
        registerItemBlock(THINCRYSTALSTAINEDGLASS, ItemBlockStainedGlass.class, "thinCrystalStainedGlass");
        registerItemBlock(THINFRAMEDSTAINEDGLASS, ItemBlockStainedGlass.class, "thinFramedStainedGlass");
        registerItemBlock(SLABS, ItemBlockSlab.class, "slab");
        registerItemBlock(DOUBLESLAB, ItemBlockSlab.class, "doubleSlab");
        GameRegistry.registerBlock(WOOD_SLAB, ItemBlockWoodSlabs.class, "wood_slab", WOOD_SLAB, WOOD_DOUBLESLAB, false); //TODO
        GameRegistry.registerBlock(WOOD_DOUBLESLAB, ItemBlockWoodSlabs.class, "wood_double_slab", WOOD_SLAB, WOOD_DOUBLESLAB, true); //TODO
        registerItemBlock(WALL, ItemBlockWall.class, "walls");
        registerItemBlock(CRATE, ItemBlockCrate.class, "crate");

        //ForgeHooks.canToolHarvestBlock(SAND, 0, new ItemStack(Items.iron_shovel)); //TODO
        SAND.setHarvestLevel("shovel", 0);
        COALORE.setHarvestLevel("pickaxe", 0);
        IRONORE.setHarvestLevel("pickaxe", 1);
        GOLDORE.setHarvestLevel("pickaxe", 2);
        LAPISORE.setHarvestLevel("pickaxe", 1);
        DIAMONDORE.setHarvestLevel("pickaxe", 2);
        REDSTONEORE.setHarvestLevel("pickaxe", 2);

        Blocks.fire.setFireInfo(PLANKS, 5, 20);
        Blocks.fire.setFireInfo(LEAVES, 30, 60);
        Blocks.fire.setFireInfo(LOG, 5, 5);
        Blocks.fire.setFireInfo(DEADWOOD_LOG, 5, 5);
        Blocks.fire.setFireInfo(PALM_FENCE, 5, 20);
        Blocks.fire.setFireInfo(DEADWOOD_FENCE, 5, 20);
        Blocks.fire.setFireInfo(WOOD_SLAB, 5, 20);
        Blocks.fire.setFireInfo(WOOD_DOUBLESLAB, 5, 20);

        GameRegistry.registerTileEntity(TileEntityChestSpawner.class, "CursedChest");
        GameRegistry.registerTileEntity(TileEntityPharaohChest.class, "PharaohChest");
        GameRegistry.registerTileEntity(TileEntityBurningTrap.class, "BurningTrap");
        GameRegistry.registerTileEntity(TileEntityLimestoneFurnace.class, "LimestoneFurnace");
        GameRegistry.registerTileEntity(TileEntityCrate.class, "atum:crate");

        //OreDictionary.registerOre("blockLimestone", STONE);

        OreDictionary.registerOre("logWood", LOG);
        OreDictionary.registerOre("logWood", DEADWOOD_LOG);
        OreDictionary.registerOre("plankWood", PLANKS);
        OreDictionary.registerOre("treeSapling", PALMSAPLING);
        OreDictionary.registerOre("treeLeaves", LEAVES);

        OreDictionary.registerOre("oreGold", GOLDORE);
        OreDictionary.registerOre("oreIron", IRONORE);
        OreDictionary.registerOre("oreLapis", LAPISORE);
        OreDictionary.registerOre("oreDiamond", DIAMONDORE);
        OreDictionary.registerOre("oreRedstone", REDSTONEORE);
        OreDictionary.registerOre("oreCoal", COALORE);

        OreDictionary.registerOre("blockGlass", CRYSTALGLASS);
        OreDictionary.registerOre("blockGlass", new ItemStack(CRYSTALSTAINEDGLASS, 1, OreDictionary.WILDCARD_VALUE));

        OreDictionary.registerOre("paneGlassColorless", THINCRYSTALGLASS);
        OreDictionary.registerOre("paneGlass", THINCRYSTALGLASS);
        OreDictionary.registerOre("paneGlass", new ItemStack(FRAMEDSTAINEDGLASS, 1, OreDictionary.WILDCARD_VALUE));

        String[] oreColours = Constants.ORE_DIC_COLOURS;

        for (int i = 0; i < oreColours.length; i++) {
            ItemStack glass = new ItemStack(CRYSTALSTAINEDGLASS, 1, i);
            ItemStack pane = new ItemStack(FRAMEDSTAINEDGLASS, 1, i);

            OreDictionary.registerOre("blockGlass" + oreColours[i], glass);
            OreDictionary.registerOre("paneGlass" + oreColours[i], pane);
        }
    }

    public static Block registerItemBlock(Block block, Class<? extends ItemBlock> itemBlock, String name) {
        block.setUnlocalizedName(Constants.MODID + ":" + name);
        block.setCreativeTab(Atum.creativeTab);

        GameRegistry.registerBlock(block, itemBlock, name);

        return block;
    }

    public static Block register(Block block, String name) {
        return register(block, name, Atum.creativeTab);
    }

    public static Block register(Block block, String name, CreativeTabs tab) {
        block.setUnlocalizedName(Constants.MODID + ":" + name);
        block.setCreativeTab(tab);

        GameRegistry.registerBlock(block, name);
        Atum.proxy.registerItemVariantModel(Item.getItemFromBlock(block), name, block.getMetaFromState(block.getDefaultState())); //TODO Test meta

        return block;
    }
}