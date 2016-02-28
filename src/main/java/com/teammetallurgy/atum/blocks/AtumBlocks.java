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
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGravel;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.oredict.OreDictionary;

public class AtumBlocks {
    public static BlockPortal BLOCK_PORTAL = new BlockPortal();
    public static Block BLOCK_CURSEDCHEST = new BlockChestSpawner();
    public static Block BLOCK_SAND = new BlockSands();
    public static Block BLOCK_LIMESTONEGRAVEL = new BlockGravel().setHardness(0.6F).setStepSound(Block.soundTypeGravel).setBlockName("limestoneGravel").setBlockTextureName("atum:AtumLimestoneGravel");
    public static Block BLOCK_STONE = new BlockAtumStone();
    public static Block BLOCK_WALL = new BlockWalls(BLOCK_STONE);
    public static Block BLOCK_LIMESTONECOBBLE = new BlockAtum("cobble").setBlockTextureName("atum:AtumCobble");
    public static Block BLOCK_LARGEBRICK = new BlockAtumBricks("largeBrick").setBlockTextureName("atum:AtumBrickLarge");
    public static Block BLOCK_SMALLBRICK = new BlockAtum("smallBrick").setBlockTextureName("atum:AtumBrickSmall");
    public static Block BLOCK_CARVEDBRICK = new BlockAtum("carvedBrick").setBlockTextureName("atum:AtumBrickCarved");
    public static BlockSlab BLOCK_SLABS = new BlockAtumSlab(false);
    public static BlockSlab BLOCK_DOUBLESLAB = new BlockAtumSlab(true);
    public static Block BLOCK_SMOOTHSTAIRS = new BlockAtumStairs(BLOCK_STONE, 0).setBlockName("smoothStairs");
    public static Block BLOCK_COBBLESTAIRS = new BlockAtumStairs(BLOCK_LIMESTONECOBBLE, 0).setBlockName("cobbleStairs");
    public static Block BLOCK_LARGESTONESTAIRS = new BlockAtumStairs(BLOCK_LARGEBRICK, 0).setBlockName("largeStairs");
    public static Block BLOCK_LARGESTONESTAIRSBREAKABLE = new BlockAtumStairs(BLOCK_LARGEBRICK, 2).setBlockName("largeStairsBreakable").setHardness(2.0F).setResistance(10.0F);
    public static Block BLOCK_SMALLSTONESTAIRS = new BlockAtumStairs(BLOCK_SMALLBRICK, 0).setBlockName("smallStairs");
    public static Block BLOCK_SANDLAYERED = new BlockSandLayered();
    public static Block BLOCK_CRACKEDLARGEBRICK = new BlockAtum("crackedLargeBrick").setBlockTextureName("atum:AtumCrackedLargeBrick");
    public static Block BLOCK_CRYSTALGLASS = new BlockAtumGlass("atum:AtumCrystalGlass").setBlockName("crystalGlass");
    public static Block BLOCK_CRYSTALSTAINEDGLASS = new BlockAtumGlassStained("atum:AtumCrystalGlass").setBlockName("crystalStainedGlass");
    public static Block BLOCK_FRAMEDGLASS = new BlockAtumGlass("atum:AtumFramedGlass").setBlockName("framedGlass");
    public static Block BLOCK_FRAMEDSTAINEDGLASS = new BlockAtumGlassStained("atum:AtumFramedGlass").setBlockName("framedStainedGlass");
    public static Block BLOCK_PALMSAPLING = new BlockPalmSapling();
    public static Block BLOCK_DATEBLOCK = new BlockDate();
    public static Block BLOCK_SHRUB = new BlockShrub().setBlockName("shrub").setBlockTextureName("atum:Shrub");
    public static Block BLOCK_WEED = new BlockShrub().setBlockName("weed").setBlockTextureName("atum:DeadBush");
    public static Block BLOCK_PAPYRUS = new BlockPapyrus();
    public static Block BLOCK_FLAX = new BlockFlax();
    public static Block BLOCK_FERTILESOIL = new BlockFertileSoil();
    public static Block BLOCK_FERTILESOILTILLED = new BlockFertileSoilTilled();
    public static Block BLOCK_LOG = new BlockPalmLog();
    public static Block BLOCK_LEAVES = new BlockLeave();
    public static Block BLOCK_PLANKS = new BlockAtumPlank();
    public static Block BLOCK_PALM_DOOR = new BlockAtumDoor().setHardness(3.0F).setStepSound(Block.soundTypeWood).setBlockName("palmDoor").setBlockTextureName("atum:palm_door");
    public static Block BLOCK_PALM_FENCE = new BlockAtumFence("atum:Planks", Material.wood).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundTypeWood).setBlockName("palmFence");
    public static Block BLOCK_PALM_FENCE_GATE = new BlockAtumFenceGate(BLOCK_PALM_FENCE).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundTypeWood).setBlockName("palmFenceGate");;
    public static Block BLOCK_PALM_HATCH = new BlockAtumTrapDoor(Material.wood).setBlockName("palmHatch").setHardness(3.0F).setBlockTextureName("atum:palm_hatch").setStepSound(Block.soundTypeWood);
    public static Block BLOCK_PALM_LADDER = new BlockAtumLadder().setBlockName("palmLadder").setHardness(0.4F).setBlockTextureName("atum:palm_ladder").setStepSound(Block.soundTypeLadder);
    public static Block BLOCK_THINCRYSTALGLASS = new BlockAtumPane("atum:AtumCrystalGlass", "atum:thinglass_top").setBlockName("thinCrystalGlass");
    public static Block BLOCK_THINCRYSTALSTAINEDGLASS = new BlockAtumPaneStained("atum:AtumCrystalGlass", "atum:thinglass_top").setBlockName("thinCrystalStainedGlass");
    public static Block BLOCK_THINFRAMEDGLASS = new BlockAtumPane("atum:AtumFramedGlass", "atum:thinglass_top").setBlockName("thinFramedGlass");
    public static Block BLOCK_THINFRAMEDSTAINEDGLASS = new BlockAtumPaneStained("atum:AtumFramedGlass", "atum:thinglass_top").setBlockName("thinFramedStainedGlass");
    public static Block BLOCK_TRAPARROW = new BlockBurningTrap();
    public static Block BLOCK_PHARAOHCHEST = new BlockPharaohChest();
    public static Block BLOCK_REDSTONEORE = new BlockAtumRedstone();
    public static Block BLOCK_COALORE = new BlockAtumOres().setBlockName("coalOre").setBlockTextureName("atum:AtumCoal");
    public static Block BLOCK_IRONORE = new BlockAtumOres().setBlockName("ironOre").setBlockTextureName("atum:AtumIron");
    public static Block BLOCK_GOLDORE = new BlockAtumOres().setBlockName("goldOre").setBlockTextureName("atum:AtumGold");
    public static Block BLOCK_LAPISORE = new BlockAtumOres().setBlockName("lapisOre").setBlockTextureName("atum:AtumLapis");
    public static Block BLOCK_DIAMONDORE = new BlockAtumOres().setBlockName("diamondOre").setBlockTextureName("atum:AtumDiamond");
    public static Block BLOCK_FURNACEIDLE = new BlockLimeStoneFurnace(false).setBlockName("furnaceIdle");
    public static Block BLOCK_FURNACEBURNING = new BlockLimeStoneFurnace(true).setBlockName("furnaceBurning");
    public static Block BLOCK_DEADWOOD_LOG = new BlockDeadwoodLog();
    public static Block BLOCK_DEADWOOD_PLANK = new BlockAtumPlank().setBlockName("deadwoodPlank").setBlockTextureName("atum:deadwood_plank");
    public static Block BLOCK_DEADWOOD_DOOR = new BlockAtumDoor().setHardness(3.0F).setStepSound(Block.soundTypeWood).setBlockName("deadwoodDoor").setBlockTextureName("atum:deadwood_door");
    public static Block BLOCK_DEADWOOD_FENCE = new BlockAtumFence("atum:deadwood_plank", Material.wood).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundTypeWood).setBlockName("deadwoodFence");
    public static Block BLOCK_DEADWOOD_FENCE_GATE = new BlockAtumFenceGate(BLOCK_DEADWOOD_FENCE).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundTypeWood).setBlockName("deadwoodFenceGate");;
    public static Block BLOCK_DEADWOOD_HATCH = new BlockAtumTrapDoor(Material.wood).setBlockName("deadwoodHatch").setHardness(3.0F).setBlockTextureName("atum:deadwood_hatch").setStepSound(Block.soundTypeWood);
    public static Block BLOCK_DEADWOOD_LADDER = new BlockAtumLadder().setBlockName("deadwoodLadder").setHardness(0.4F).setBlockTextureName("atum:deadwood_ladder").setStepSound(Block.soundTypeLadder);
    public static BlockAtumWoodSlab BLOCK_WOOD_SLAB = new BlockAtumWoodSlab(false);
    public static BlockAtumWoodSlab BLOCK_WOOD_DOUBLESLAB = new BlockAtumWoodSlab(true);
    public static Block BLOCK_PALM_STAIRS = new  BlockAtumStairs(BLOCK_PLANKS, 0).setBlockName("palmStairs");
    public static Block BLOCK_DEADWOOD_STAIRS = new  BlockAtumStairs(BLOCK_DEADWOOD_PLANK, 0).setBlockName("deadwoodStairs");
    public static Block BLOCK_CRATE = new BlockCrate();

    public AtumBlocks() {
        registerBlocks();
    }

    public void registerBlocks() {
        this.register(BLOCK_PORTAL);
        this.register(BLOCK_CURSEDCHEST);
        this.register(BLOCK_SAND);
        this.register(BLOCK_LIMESTONEGRAVEL);
        this.register(BLOCK_STONE);
        this.register(BLOCK_LIMESTONECOBBLE);
        this.register(BLOCK_SMALLBRICK);
        this.register(BLOCK_CARVEDBRICK);
        this.register(BLOCK_SMOOTHSTAIRS);
        this.register(BLOCK_COBBLESTAIRS);
        this.register(BLOCK_LARGESTONESTAIRS);
        this.register(BLOCK_LARGESTONESTAIRSBREAKABLE);
        this.register(BLOCK_SMALLSTONESTAIRS);
        this.register(BLOCK_SANDLAYERED);
        this.register(BLOCK_CRACKEDLARGEBRICK);
        this.register(BLOCK_CRYSTALGLASS);
        this.register(BLOCK_FRAMEDGLASS);
        this.register(BLOCK_PALMSAPLING);
        this.register(BLOCK_DATEBLOCK);
        this.register(BLOCK_SHRUB);
        this.register(BLOCK_WEED);
        this.register(BLOCK_PAPYRUS);
        this.register(BLOCK_FLAX);
        this.register(BLOCK_FERTILESOIL);
        this.register(BLOCK_FERTILESOILTILLED);
        this.register(BLOCK_LOG);
        this.register(BLOCK_LEAVES);
        this.register(BLOCK_PLANKS);
        this.register(BLOCK_PALM_DOOR);
        this.register(BLOCK_PALM_FENCE);
        this.register(BLOCK_PALM_FENCE_GATE);
        this.register(BLOCK_PALM_HATCH);
        this.register(BLOCK_PALM_LADDER);
        this.register(BLOCK_THINCRYSTALGLASS);
        this.register(BLOCK_THINFRAMEDGLASS);
        this.register(BLOCK_TRAPARROW);
        this.register(BLOCK_PHARAOHCHEST);
        this.register(BLOCK_REDSTONEORE);
        this.register(BLOCK_COALORE);
        this.register(BLOCK_IRONORE);
        this.register(BLOCK_GOLDORE);
        this.register(BLOCK_LAPISORE);
        this.register(BLOCK_DIAMONDORE);
        this.register(BLOCK_FURNACEIDLE);
        this.register(BLOCK_FURNACEBURNING);
        this.register(BLOCK_DEADWOOD_LOG);
        this.register(BLOCK_DEADWOOD_PLANK);
        this.register(BLOCK_DEADWOOD_DOOR);
        this.register(BLOCK_DEADWOOD_FENCE);
        this.register(BLOCK_DEADWOOD_FENCE_GATE);
        this.register(BLOCK_DEADWOOD_HATCH);
        this.register(BLOCK_DEADWOOD_LADDER);
        this.register(BLOCK_PALM_STAIRS);
        this.register(BLOCK_DEADWOOD_STAIRS);

        GameRegistry.registerBlock(BLOCK_LARGEBRICK, ItemBlockBricks.class, BLOCK_LARGEBRICK.getUnlocalizedName());
        
        GameRegistry.registerBlock(BLOCK_CRYSTALSTAINEDGLASS, ItemBlockStainedGlass.class, BLOCK_CRYSTALSTAINEDGLASS.getUnlocalizedName());
        GameRegistry.registerBlock(BLOCK_FRAMEDSTAINEDGLASS, ItemBlockStainedGlass.class, BLOCK_FRAMEDSTAINEDGLASS.getUnlocalizedName());

        GameRegistry.registerBlock(BLOCK_THINCRYSTALSTAINEDGLASS, ItemBlockStainedGlass.class, BLOCK_THINCRYSTALSTAINEDGLASS.getUnlocalizedName());
        GameRegistry.registerBlock(BLOCK_THINFRAMEDSTAINEDGLASS, ItemBlockStainedGlass.class, BLOCK_THINFRAMEDSTAINEDGLASS.getUnlocalizedName());

        GameRegistry.registerBlock(BLOCK_SLABS, ItemBlockSlab.class, "tile.slab");
        GameRegistry.registerBlock(BLOCK_DOUBLESLAB, ItemBlockSlab.class, "tile.doubleSlab");

        GameRegistry.registerBlock(BLOCK_WALL, ItemBlockWall.class, BLOCK_WALL.getUnlocalizedName());

        GameRegistry.registerBlock(BLOCK_WOOD_SLAB, ItemBlockWoodSlabs.class, "wood_slab", BLOCK_WOOD_SLAB, BLOCK_WOOD_DOUBLESLAB, false);
        GameRegistry.registerBlock(BLOCK_WOOD_DOUBLESLAB, ItemBlockWoodSlabs.class, "wood_double_slab", BLOCK_WOOD_SLAB, BLOCK_WOOD_DOUBLESLAB, true);
        
        GameRegistry.registerBlock(BLOCK_CRATE, ItemBlockCrate.class, "crate");

        ForgeHooks.canToolHarvestBlock(BLOCK_SAND, 0, new ItemStack(Items.iron_shovel));
        BLOCK_SAND.setHarvestLevel("shovel", 0);
        BLOCK_COALORE.setHarvestLevel("pickaxe", 0);
        BLOCK_IRONORE.setHarvestLevel("pickaxe", 1);
        BLOCK_GOLDORE.setHarvestLevel("pickaxe", 2);
        BLOCK_LAPISORE.setHarvestLevel("pickaxe", 1);
        BLOCK_DIAMONDORE.setHarvestLevel("pickaxe", 2);
        BLOCK_REDSTONEORE.setHarvestLevel("pickaxe", 2);

        Blocks.fire.setFireInfo(BLOCK_PLANKS, 5, 20);
        Blocks.fire.setFireInfo(BLOCK_LEAVES, 30, 60);
        Blocks.fire.setFireInfo(BLOCK_LOG, 5, 5);
        Blocks.fire.setFireInfo(BLOCK_DEADWOOD_LOG, 5, 5);
        Blocks.fire.setFireInfo(BLOCK_PALM_FENCE, 5, 20);
        Blocks.fire.setFireInfo(BLOCK_DEADWOOD_PLANK, 5, 20);
        Blocks.fire.setFireInfo(BLOCK_DEADWOOD_FENCE, 5, 20);
        Blocks.fire.setFireInfo(BLOCK_WOOD_SLAB, 5, 20);
        Blocks.fire.setFireInfo(BLOCK_WOOD_DOUBLESLAB, 5, 20);

        GameRegistry.registerTileEntity(TileEntityChestSpawner.class, "CursedChest");
        GameRegistry.registerTileEntity(TileEntityPharaohChest.class, "PharaohChest");
        GameRegistry.registerTileEntity(TileEntityBurningTrap.class, "BurningTrap");
        GameRegistry.registerTileEntity(TileEntityLimestoneFurnace.class, "LimestoneFurnace");
        GameRegistry.registerTileEntity(TileEntityCrate.class, "atum:crate");

        OreDictionary.registerOre("blockLimestone", BLOCK_STONE);

        OreDictionary.registerOre("logWood", BLOCK_LOG);
        OreDictionary.registerOre("logWood", BLOCK_DEADWOOD_LOG);
        OreDictionary.registerOre("plankWood", BLOCK_PLANKS);
        OreDictionary.registerOre("plankWood", BLOCK_DEADWOOD_PLANK);
        OreDictionary.registerOre("treeSapling", BLOCK_PALMSAPLING);
        OreDictionary.registerOre("treeLeaves",  BLOCK_LEAVES);

        OreDictionary.registerOre("oreGold", BLOCK_GOLDORE);
        OreDictionary.registerOre("oreIron", BLOCK_IRONORE);
        OreDictionary.registerOre("oreLapis", BLOCK_LAPISORE);
        OreDictionary.registerOre("oreDiamond", BLOCK_DIAMONDORE);
        OreDictionary.registerOre("oreRedstone", BLOCK_REDSTONEORE);
        OreDictionary.registerOre("oreCoal", BLOCK_COALORE);

        OreDictionary.registerOre("blockGlass", BLOCK_CRYSTALGLASS);
        OreDictionary.registerOre("blockGlass", new ItemStack(BLOCK_CRYSTALSTAINEDGLASS, 1, OreDictionary.WILDCARD_VALUE));
        
        OreDictionary.registerOre("paneGlassColorless", BLOCK_THINCRYSTALGLASS);
        OreDictionary.registerOre("paneGlass", BLOCK_THINCRYSTALGLASS);
        OreDictionary.registerOre("paneGlass", new ItemStack(BLOCK_FRAMEDSTAINEDGLASS, 1, OreDictionary.WILDCARD_VALUE));

        String[] oreColours = Constants.ORE_DIC_COLOURS;

        for (int i = 0; i < oreColours.length; i++ ){

            ItemStack glass = new ItemStack(BLOCK_CRYSTALSTAINEDGLASS, 1, i);
            ItemStack pane = new ItemStack(BLOCK_FRAMEDSTAINEDGLASS, 1, i);

            OreDictionary.registerOre("blockGlass" + oreColours[i], glass);
            OreDictionary.registerOre("paneGlass"  + oreColours[i], pane);
        }
        
        // blocks unlocalized name
        BLOCK_STONE = BLOCK_STONE.setBlockName("limestone");
        BLOCK_LARGESTONESTAIRS = BLOCK_LARGESTONESTAIRS.setBlockName("largeStairsUnbreakable");
        BLOCK_LARGESTONESTAIRSBREAKABLE = BLOCK_LARGESTONESTAIRSBREAKABLE.setBlockName("largeStairs");

    }

    private void register(Block block) {
        if (!(block instanceof BlockDate) && !(block instanceof BlockFlax) && !(block instanceof BlockPapyrus) && !(block instanceof BlockPortal) && 
                !(block instanceof BlockAtumDoor)) {
            block.setCreativeTab(Atum.creativeTab);
        }
        GameRegistry.registerBlock(block, block.getUnlocalizedName());
    }
}
