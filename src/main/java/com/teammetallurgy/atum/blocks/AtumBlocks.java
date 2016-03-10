package com.teammetallurgy.atum.blocks;

import com.teammetallurgy.atum.Atum;
import com.teammetallurgy.atum.blocks.tileentity.TileEntityBurningTrap;
import com.teammetallurgy.atum.blocks.tileentity.chests.TileEntityChestSpawner;
import com.teammetallurgy.atum.blocks.tileentity.chests.TileEntityPharaohChest;
import com.teammetallurgy.atum.blocks.tileentity.crate.TileEntityCrate;
import com.teammetallurgy.atum.blocks.tileentity.furnace.TileEntityLimestoneFurnace;
import com.teammetallurgy.atum.items.itemblock.*;
import com.teammetallurgy.atum.utils.AtumUtils;
import com.teammetallurgy.atum.utils.Constants;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGravel;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;

public class AtumBlocks {
    public static final BlockPortal PORTAL = new BlockPortal();
    public static final Block CURSED_CHEST = new BlockChestSpawner();
    public static final Block SAND = new BlockSands();
    public static final Block LIMESTONE_GRAVEL = new BlockGravel().setHardness(0.6F).setStepSound(Block.soundTypeGravel);
    public static final Block LIMESTONE = new Block(Material.rock).setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundTypeStone);
    public static final Block LIMESTONE_CRACKED = new BlockAtum();
    public static final Block LIMESTONEBRICK = new BlockLimestoneBricks();
    public static final Block WALL = new BlockWalls(LIMESTONE);
    public static final BlockLimestoneSlab LIMESTONE_SLAB = new BlockLimestoneSlab(false);
    public static final BlockLimestoneSlab LIMESTONE_DOUBLE_SLAB = new BlockLimestoneSlab(true);
    public static final BlockAtumStairs SMOOTH_STAIRS = new BlockAtumStairs(LIMESTONE.getDefaultState());
    public static final BlockAtumStairs COBBLE_STAIRS = new BlockAtumStairs(LIMESTONE_CRACKED.getDefaultState());
    public static final BlockAtumStairs LARGE_STONE_STAIRS = new BlockAtumStairs(LIMESTONEBRICK.getDefaultState().withProperty(BlockLimestoneBricks.VARIANT, BlockLimestoneBricks.EnumType.LARGE));
    public static final Block LARGE_STONE_STAIRS_BREAKABLE = (new BlockAtumStairs(LIMESTONEBRICK.getDefaultState().withProperty(BlockLimestoneBricks.VARIANT, BlockLimestoneBricks.EnumType.LARGE))).setHardness(2.0F).setResistance(10.0F);
    public static final Block SMALL_STONE_STAIRS = new BlockAtumStairs(LIMESTONEBRICK.getDefaultState().withProperty(BlockLimestoneBricks.VARIANT, BlockLimestoneBricks.EnumType.SMALL));
    public static final Block SAND_LAYERED = new BlockSandLayered();
    public static final Block CRYSTAL_GLASS = new BlockAtumGlass(Material.glass);
    public static final Block CRYSTAL_STAINED_GLASS = new BlockAtumGlassStained();
    public static final Block FRAMED_GLASS = new BlockAtumGlass(Material.glass);
    public static final Block FRAMED_STAINED_GLASS = new BlockAtumGlassStained();
    public static final Block SAPLING = new BlockAtumSapling();
    public static final Block DATE_BLOCK = new BlockDate();
    public static final BlockShrub SHRUB = new BlockShrub();
    public static final BlockShrub WEED = new BlockShrub();
    public static final Block PAPYRUS = new BlockPapyrus();
    public static final Block FLAX = new BlockFlax();
    public static final Block FERTILE_SOIL = new BlockFertileSoil();
    public static final Block FERTILE_SOIL_TILLED = new BlockFertileSoilTilled();
    public static final Block PLANKS = new BlockAtumPlank();
    public static final Block LOG = new BlockAtumLog();
    public static final BlockLeaves LEAVES = new BlockLeave();
    public static final Block PALM_DOOR = new BlockAtumDoor().setHardness(3.0F).setStepSound(Block.soundTypeWood);
    public static final Block PALM_FENCE = new BlockAtumFence(Material.wood).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundTypeWood);
    public static final Block PALM_FENCE_GATE = new BlockAtumFenceGate().setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundTypeWood);
    public static final Block PALM_HATCH = new BlockAtumTrapDoor(Material.wood).setHardness(3.0F).setStepSound(Block.soundTypeWood);
    public static final Block PALM_LADDER = new BlockAtumLadder().setHardness(0.4F).setStepSound(Block.soundTypeLadder);
    public static final Block THIN_CRYSTAL_GLASS = new BlockAtumPane();
    public static final Block THIN_CRYSTAL_STAINED_GLASS = new BlockAtumPaneStained();
    public static final Block THIN_FRAMED_GLASS = new BlockAtumPane();
    public static final Block THIN_FRAMED_STAINED_GLASS = new BlockAtumPaneStained();
    public static final Block BURNING_TRAP = new BlockBurningTrap();
    public static final Block PHARAOH_CHEST = new BlockPharaohChest();
    public static final Block GOLD_ORE = new BlockAtumOres();
    public static final Block IRON_ORE = new BlockAtumOres();
    public static final Block COAL_ORE = new BlockAtumOres();
    public static final Block LAPIS_ORE = new BlockAtumOres();
    public static final Block DIAMOND_ORE = new BlockAtumOres();
    public static final Block REDSTONE_ORE = new BlockAtumRedstone(false);
    public static final Block LIT_REDSTONE_ORE = new BlockAtumRedstone(true);
    public static final Block LIMESTONE_FURNACE = new BlockLimestoneFurnace(false);
    public static final Block LIMESTONE_FURNACE_LIT = new BlockLimestoneFurnace(true);
    public static final Block DEADWOOD_DOOR = new BlockAtumDoor().setHardness(3.0F).setStepSound(Block.soundTypeWood);
    public static final Block DEADWOOD_FENCE = new BlockAtumFence(Material.wood).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundTypeWood);
    public static final Block DEADWOOD_FENCE_GATE = new BlockAtumFenceGate().setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundTypeWood);
    public static final Block DEADWOOD_HATCH = new BlockAtumTrapDoor(Material.wood).setHardness(3.0F).setStepSound(Block.soundTypeWood);
    public static final Block DEADWOOD_LADDER = new BlockAtumLadder().setHardness(0.4F).setStepSound(Block.soundTypeLadder);
    public static final BlockSlab WOOD_SLAB = new BlockAtumHalfWoodSlab();
    public static final BlockSlab WOOD_DOUBLE_SLAB = new BlockAtumDoubleWoodSlab();
    public static final Block PALM_STAIRS = new BlockAtumStairs(PLANKS.getDefaultState().withProperty(BlockAtumPlank.VARIANT, BlockAtumPlank.EnumType.PALM));
    public static final Block DEADWOOD_STAIRS = new BlockAtumStairs(PLANKS.getDefaultState().withProperty(BlockAtumPlank.VARIANT, BlockAtumPlank.EnumType.DEADWOOD));
    public static final Block CRATE = new BlockCrate();

    public static void registerBlocks() {
        register(PORTAL, "portal", null);
        register(CURSED_CHEST, "chest_spawner");
        register(SAND, "sand");
        register(LIMESTONE_GRAVEL, "limestone_gravel");
        register(LIMESTONE, "limestone");
        register(LIMESTONE_CRACKED, "limestone_cracked");
        register(SMOOTH_STAIRS, "smooth_stairs");
        register(COBBLE_STAIRS, "cobble_stairs");
        register(LARGE_STONE_STAIRS, "large_stairs_unbreakable");
        register(LARGE_STONE_STAIRS_BREAKABLE, "large_stairs");
        register(SMALL_STONE_STAIRS, "small_stairs");
        register(SAND_LAYERED, "sand_layer"); //TODO Merge into sand block
        register(CRYSTAL_GLASS, "crystal_glass");
        register(FRAMED_GLASS, "framed_glass");
        register(DATE_BLOCK, "date_block", null); //TODO name
        register(SHRUB, "shrub");
        register(WEED, "weed");
        register(PAPYRUS, "papyrus");
        register(FLAX, "flax_block", null); //TODO Name
        register(FERTILE_SOIL, "fertile_soil");
        register(FERTILE_SOIL_TILLED, "fertile_soil_tilled");
        register(PALM_DOOR, "palm_door");
        register(PALM_FENCE, "palm_fence");
        register(PALM_FENCE_GATE, "palm_fence_gate");
        register(PALM_HATCH, "palm_hatch");
        register(PALM_LADDER, "palm_ladder");
        register(THIN_CRYSTAL_GLASS, "thin_crystal_glass");
        register(THIN_FRAMED_GLASS, "thin_framed_glass");
        register(BURNING_TRAP, "burning_trap");
        register(PHARAOH_CHEST, "pharaoh_chest");
        register(GOLD_ORE, "gold_ore");
        register(IRON_ORE, "iron_ore");
        register(COAL_ORE, "coal_ore");
        register(LAPIS_ORE, "lapis_ore");
        register(DIAMOND_ORE, "diamond_ore");
        register(REDSTONE_ORE, "redstone_ore");
        register(LIT_REDSTONE_ORE, "lit_redstone_ore", null);
        register(LIMESTONE_FURNACE, "limestone_furnace");
        register(LIMESTONE_FURNACE_LIT, "limestone_furnace_lit", null);
        register(DEADWOOD_DOOR, "deadwood_door");
        register(DEADWOOD_FENCE, "deadwood_fence");
        register(DEADWOOD_FENCE_GATE, "deadwood_fence_gate");
        register(DEADWOOD_HATCH, "deadwood_hatch");
        register(DEADWOOD_LADDER, "deadwood_ladder");
        register(PALM_STAIRS, "palm_stairs");
        register(DEADWOOD_STAIRS, "deadwood_stairs");

        register(CRYSTAL_STAINED_GLASS, ItemBlockStainedGlass.class, "crystal_stained_glass");
        register(FRAMED_STAINED_GLASS, ItemBlockStainedGlass.class, "framed_stained_glass");
        register(THIN_CRYSTAL_STAINED_GLASS, ItemBlockStainedGlass.class, "thin_crystal_stained_glass");
        register(THIN_FRAMED_STAINED_GLASS, ItemBlockStainedGlass.class, "thin_framed_stained_glass");
        register(LIMESTONEBRICK, ItemBlockLimestoneBricks.class, "limestonebrick");
        register(LIMESTONE_SLAB, ItemBlockLimestoneSlab.class, "limestone_slab");
        register(LIMESTONE_DOUBLE_SLAB, ItemBlockLimestoneSlab.class, "limestone_double_slab");
        register(WALL, ItemBlockWall.class, "walls");
        register(PLANKS, ItemBlockPlanks.class, "planks");
        register(LOG, ItemBlockPlanks.class, "log");
        register(WOOD_SLAB, ItemBlockWoodSlabs.class, "wood_slab");
        register(WOOD_DOUBLE_SLAB, ItemBlockWoodSlabs.class, "wood_double_slab");
        register(LEAVES, ItemBlockPlanks.class, "leave");
        register(SAPLING, ItemBlockPlanks.class, "sapling");
        register(CRATE, ItemBlockPlanks.class, "crate");

        //ForgeHooks.canToolHarvestBlock(SAND, 0, new ItemStack(Items.iron_shovel)); //TODO
        SAND.setHarvestLevel("shovel", 0);
        LIMESTONE_GRAVEL.setHarvestLevel("shovel", 0);
        COAL_ORE.setHarvestLevel("pickaxe", 0);
        IRON_ORE.setHarvestLevel("pickaxe", 1);
        GOLD_ORE.setHarvestLevel("pickaxe", 2);
        LAPIS_ORE.setHarvestLevel("pickaxe", 1);
        DIAMOND_ORE.setHarvestLevel("pickaxe", 2);
        REDSTONE_ORE.setHarvestLevel("pickaxe", 2);

        Blocks.fire.setFireInfo(PLANKS, 5, 20);
        Blocks.fire.setFireInfo(LEAVES, 30, 60);
        Blocks.fire.setFireInfo(LOG, 5, 5);
        Blocks.fire.setFireInfo(PALM_FENCE, 5, 20);
        Blocks.fire.setFireInfo(DEADWOOD_FENCE, 5, 20);
        Blocks.fire.setFireInfo(WOOD_SLAB, 5, 20);
        Blocks.fire.setFireInfo(WOOD_DOUBLE_SLAB, 5, 20);

        GameRegistry.registerTileEntity(TileEntityChestSpawner.class, "cursed_chest");
        GameRegistry.registerTileEntity(TileEntityPharaohChest.class, "pharaoh_chest");
        GameRegistry.registerTileEntity(TileEntityBurningTrap.class, "burning_trap");
        GameRegistry.registerTileEntity(TileEntityLimestoneFurnace.class, "limestone_furnace");
        GameRegistry.registerTileEntity(TileEntityCrate.class, "crate");

        //OreDictionary.registerOre("blockLimestone", STONE);

        OreDictionary.registerOre("logWood", LOG);
        OreDictionary.registerOre("plankWood", PLANKS);
        OreDictionary.registerOre("treeSapling", SAPLING);
        OreDictionary.registerOre("treeLeaves", LEAVES);

        OreDictionary.registerOre("oreGold", GOLD_ORE);
        OreDictionary.registerOre("oreIron", IRON_ORE);
        OreDictionary.registerOre("oreLapis", LAPIS_ORE);
        OreDictionary.registerOre("oreDiamond", DIAMOND_ORE);
        OreDictionary.registerOre("oreRedstone", REDSTONE_ORE);
        OreDictionary.registerOre("oreCoal", COAL_ORE);

        OreDictionary.registerOre("blockGlass", CRYSTAL_GLASS);
        OreDictionary.registerOre("blockGlass", new ItemStack(CRYSTAL_STAINED_GLASS, 1, OreDictionary.WILDCARD_VALUE));

        OreDictionary.registerOre("paneGlassColorless", THIN_CRYSTAL_GLASS);
        OreDictionary.registerOre("paneGlass", THIN_CRYSTAL_GLASS);
        OreDictionary.registerOre("paneGlass", new ItemStack(FRAMED_STAINED_GLASS, 1, OreDictionary.WILDCARD_VALUE));

        String[] oreColours = Constants.ORE_DIC_COLOURS;

        for (int i = 0; i < oreColours.length; i++) {
            ItemStack glass = new ItemStack(CRYSTAL_STAINED_GLASS, 1, i);
            ItemStack pane = new ItemStack(FRAMED_STAINED_GLASS, 1, i);

            OreDictionary.registerOre("blockGlass" + oreColours[i], glass);
            OreDictionary.registerOre("paneGlass" + oreColours[i], pane);
        }
    }

    protected static Block register(Block block, String name) {
        return register(block, ItemBlock.class, name);
    }

    protected static Block register(Block block, String name, CreativeTabs tab) {
        return register(block, ItemBlock.class, name, tab);
    }

    protected static Block register(Block block, Class<? extends ItemBlock> itemBlock, String name) {
        return register(block, itemBlock, name, Atum.creativeTab);
    }

    protected static Block register(Block block, Class<? extends ItemBlock> itemBlockClass, String name, CreativeTabs tab) {
        block.setUnlocalizedName(Constants.MODID + "." + AtumUtils.toUnlocalizedName(name));
        block.setCreativeTab(tab);

        GameRegistry.registerBlock(block, itemBlockClass, name);

        Atum.proxy.registerItemVariantModel(Item.getItemFromBlock(block), name);

        return block;
    }
}