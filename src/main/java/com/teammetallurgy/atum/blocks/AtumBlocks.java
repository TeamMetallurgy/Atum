package com.teammetallurgy.atum.blocks;

import com.teammetallurgy.atum.Atum;
import com.teammetallurgy.atum.blocks.tileentity.TileEntityBurningTrap;
import com.teammetallurgy.atum.blocks.tileentity.chests.TileEntityChestSpawner;
import com.teammetallurgy.atum.blocks.tileentity.chests.TileEntityPharaohChest;
import com.teammetallurgy.atum.blocks.tileentity.crate.TileEntityCrate;
import com.teammetallurgy.atum.blocks.tileentity.furnace.TileEntityLimestoneFurnace;
import com.teammetallurgy.atum.items.AtumItems;
import com.teammetallurgy.atum.utils.AtumUtils;
import com.teammetallurgy.atum.utils.Constants;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGravel;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class AtumBlocks {
    public static final BlockPortal PORTAL = new BlockPortal();
    public static final Block CURSED_CHEST = new BlockChestSpawner();
    public static final Block SAND = new BlockSands().setHardness(0.5F);
    public static final Block LIMESTONE_GRAVEL = new BlockGravel().setHardness(0.6F)/*.setSoundType(SoundType.GROUND)*/;
    public static final Block LIMESTONE = new Block(Material.ROCK).setHardness(1.5F).setResistance(10.0F)/*.setSoundType(SoundType.STONE)*/;
    public static final Block LIMESTONE_CRACKED = new Block(Material.ROCK).setHardness(2.0F).setResistance(10.0F);
    ;
    public static final Block LIMESTONEBRICK = new BlockLimestoneBricks();
    public static final Block WALL = new BlockWalls(LIMESTONE);
    public static final BlockSlab LIMESTONE_SLAB = new BlockLimestoneHalfSlab();
    public static final BlockSlab LIMESTONE_DOUBLE_SLAB = new BlockLimestoneDoubleSlab();
    public static final BlockAtumStairs SMOOTH_STAIRS = new BlockAtumStairs(LIMESTONE.getDefaultState());
    public static final BlockAtumStairs COBBLE_STAIRS = new BlockAtumStairs(LIMESTONE_CRACKED.getDefaultState());
    public static final BlockAtumStairs LARGE_STONE_STAIRS = new BlockAtumStairs(LIMESTONEBRICK.getDefaultState().withProperty(BlockLimestoneBricks.VARIANT, BlockLimestoneBricks.EnumType.LARGE));
    public static final Block LARGE_STONE_STAIRS_BREAKABLE = (new BlockAtumStairs(LIMESTONEBRICK.getDefaultState().withProperty(BlockLimestoneBricks.VARIANT, BlockLimestoneBricks.EnumType.LARGE))).setHardness(2.0F).setResistance(10.0F);
    public static final Block SMALL_STONE_STAIRS = new BlockAtumStairs(LIMESTONEBRICK.getDefaultState().withProperty(BlockLimestoneBricks.VARIANT, BlockLimestoneBricks.EnumType.SMALL));
    public static final Block SAND_LAYERED = new BlockSands().setHardness(0.1F).setLightOpacity(0);
    public static final Block CRYSTAL_GLASS = new BlockAtumGlass(Material.GLASS);
    public static final BlockAtumGlassStained CRYSTAL_STAINED_GLASS = new BlockAtumGlassStained();
    public static final Block FRAMED_GLASS = new BlockAtumGlass(Material.GLASS);
    public static final BlockAtumGlassStained FRAMED_STAINED_GLASS = new BlockAtumGlassStained();
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
    public static final BlockAtumDoor PALM_DOOR = (BlockAtumDoor) new BlockAtumDoor().setHardness(3.0F);
    public static final Block PALM_FENCE = new BlockAtumFence(BlockAtumPlank.EnumType.PALM.getMapColor());
    public static final Block PALM_FENCE_GATE = new BlockAtumFenceGate();
    public static final Block PALM_HATCH = new BlockAtumTrapDoor();
    public static final Block PALM_LADDER = new BlockAtumLadder();
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
    public static final Block REDSTONE_ORE = new BlockAtumRedstoneOre(false);
    public static final Block LIT_REDSTONE_ORE = new BlockAtumRedstoneOre(true);
    public static final Block LIMESTONE_FURNACE = new BlockLimeStoneFurnace(false);
    public static final Block LIMESTONE_FURNACE_LIT = new BlockLimeStoneFurnace(true);
    public static final BlockAtumDoor DEADWOOD_DOOR = (BlockAtumDoor) new BlockAtumDoor().setHardness(3.0F);
    public static final Block DEADWOOD_FENCE = new BlockAtumFence(BlockAtumPlank.EnumType.DEADWOOD.getMapColor());
    public static final Block DEADWOOD_FENCE_GATE = new BlockAtumFenceGate();
    public static final Block DEADWOOD_HATCH = new BlockAtumTrapDoor();
    public static final Block DEADWOOD_LADDER = new BlockAtumLadder();
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
        register(SAND_LAYERED, "sand_layer");
        register(CRYSTAL_GLASS, "crystal_glass");
        register(FRAMED_GLASS, "framed_glass");
        register(DATE_BLOCK, "date_block", null);
        register(SHRUB, "shrub");
        register(WEED, "weed");
        register(PAPYRUS, "papyrus");
        register(FLAX, "flax_block", null);
        register(FERTILE_SOIL, "fertile_soil");
        register(FERTILE_SOIL_TILLED, "fertile_soil_tilled");
        registerDoor(PALM_DOOR, "palm_door", AtumItems.PALM_DOOR);
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
        registerDoor(DEADWOOD_DOOR, "deadwood_door", AtumItems.DEADWOOD_DOOR);
        register(DEADWOOD_FENCE, "deadwood_fence");
        register(DEADWOOD_FENCE_GATE, "deadwood_fence_gate");
        register(DEADWOOD_HATCH, "deadwood_hatch");
        register(DEADWOOD_LADDER, "deadwood_ladder");
        register(PALM_STAIRS, "palm_stairs");
        register(DEADWOOD_STAIRS, "deadwood_stairs");

        register(CRYSTAL_STAINED_GLASS, new ItemCloth(CRYSTAL_STAINED_GLASS), "crystal_stained_glass");
        register(FRAMED_STAINED_GLASS, new ItemCloth(FRAMED_STAINED_GLASS), "framed_stained_glass");
        register(THIN_CRYSTAL_STAINED_GLASS, new ItemCloth(THIN_CRYSTAL_STAINED_GLASS), "thin_crystal_stained_glass");
        register(THIN_FRAMED_STAINED_GLASS, new ItemCloth(THIN_FRAMED_STAINED_GLASS), "thin_framed_stained_glass");
        register(LIMESTONEBRICK, new ItemMultiTexture(LIMESTONEBRICK, LIMESTONEBRICK, new ItemMultiTexture.Mapper() {
            @Override
            public String apply(ItemStack input) {
                return BlockLimestoneBricks.EnumType.byMetadata(input.getMetadata()).getUnlocalizedName();
            }
        }), "limestonebrick");
        register(LIMESTONE_SLAB, new ItemSlab(LIMESTONE_SLAB, LIMESTONE_SLAB, LIMESTONE_DOUBLE_SLAB), "limestone_slab");
        register(LIMESTONE_DOUBLE_SLAB, "limestone_double_slab");
        register(WALL, new ItemMultiTexture(WALL, WALL, new ItemMultiTexture.Mapper() {
            @Override
            public String apply(ItemStack input) {
                return BlockLimestoneBricks.EnumType.byMetadata(input.getMetadata()).getUnlocalizedName();
            }
        }), "walls");
        register(PLANKS, new ItemMultiTexture(PLANKS, PLANKS, new ItemMultiTexture.Mapper() {
            @Override
            public String apply(ItemStack input) {
                return BlockAtumPlank.EnumType.byMetadata(input.getMetadata()).getUnlocalizedName();
            }
        }), "planks");
        register(LOG, new ItemMultiTexture(LOG, LOG, new ItemMultiTexture.Mapper() {
            @Override
            public String apply(ItemStack input) {
                return BlockAtumPlank.EnumType.byMetadata(input.getMetadata()).getUnlocalizedName();
            }
        }), "log");
        register(WOOD_SLAB, new ItemSlab(WOOD_SLAB, WOOD_SLAB, WOOD_DOUBLE_SLAB), "wood_slab");
        register(WOOD_DOUBLE_SLAB, "wood_double_slab");
        register(LEAVES, new ItemMultiTexture(LEAVES, LEAVES, new ItemMultiTexture.Mapper() {
            @Override
            public String apply(ItemStack input) {
                return BlockAtumPlank.EnumType.byMetadata(input.getMetadata()).getUnlocalizedName();
            }
        }), "leave");
        register(SAPLING, new ItemMultiTexture(SAPLING, SAPLING, new ItemMultiTexture.Mapper() {
            @Override
            public String apply(ItemStack input) {
                return BlockAtumPlank.EnumType.byMetadata(input.getMetadata()).getUnlocalizedName();
            }
        }), "sapling");
        register(CRATE, new ItemMultiTexture(CRATE, CRATE, new ItemMultiTexture.Mapper() {
            @Override
            public String apply(ItemStack input) {
                return BlockAtumPlank.EnumType.byMetadata(input.getMetadata()).getUnlocalizedName();
            }
        }), "crate");

        //ForgeHooks.canToolHarvestBlock(SAND, 0, new ItemStack(Items.iron_shovel)); //TODO
        SAND.setHarvestLevel("shovel", 0);
        LIMESTONE_GRAVEL.setHarvestLevel("shovel", 0);
        COAL_ORE.setHarvestLevel("pickaxe", 0);
        IRON_ORE.setHarvestLevel("pickaxe", 1);
        GOLD_ORE.setHarvestLevel("pickaxe", 2);
        LAPIS_ORE.setHarvestLevel("pickaxe", 1);
        DIAMOND_ORE.setHarvestLevel("pickaxe", 2);
        REDSTONE_ORE.setHarvestLevel("pickaxe", 2);

        Blocks.FIRE.setFireInfo(PLANKS, 5, 20);
        Blocks.FIRE.setFireInfo(LEAVES, 30, 60);
        Blocks.FIRE.setFireInfo(LOG, 5, 5);
        Blocks.FIRE.setFireInfo(PALM_FENCE, 5, 20);
        Blocks.FIRE.setFireInfo(DEADWOOD_FENCE, 5, 20);
        Blocks.FIRE.setFireInfo(WOOD_SLAB, 5, 20);
        Blocks.FIRE.setFireInfo(WOOD_DOUBLE_SLAB, 5, 20);

        GameRegistry.registerTileEntity(TileEntityChestSpawner.class, "cursed_chest");
        GameRegistry.registerTileEntity(TileEntityPharaohChest.class, "pharaoh_chest");
        GameRegistry.registerTileEntity(TileEntityBurningTrap.class, "burning_trap");
        GameRegistry.registerTileEntity(TileEntityLimestoneFurnace.class, "limestone_furnace");
        GameRegistry.registerTileEntity(TileEntityCrate.class, "crate");

        //OreDictionary.registerOre("blockLimestone", STONE); //TODO

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

    public static Block registerDoor(BlockAtumDoor door_block, String name, Item door_item) {
        Block block = register(door_block, name + "_block", null);
        door_item = AtumItems.register(new ItemDoor(block), name);
        door_block.setDoorItem(door_item);
        return block;
    }

    protected static Block register(Block block, String name) {
        return register(block, new ItemBlock(block), name);
    }

    protected static Block register(Block block, String name, CreativeTabs tab) {
        return register(block, new ItemBlock(block), name, tab);
    }

    protected static Block register(Block block, Item itemBlock, String name) {
        return register(block, itemBlock, name, Atum.CREATIVE_TAB);
    }

    protected static Block register(Block block, Item itemBlock, String name, CreativeTabs tab) {
        block.setUnlocalizedName(Constants.MODID + "." + AtumUtils.toUnlocalizedName(name));

        block.setCreativeTab(tab);

        GameRegistry.register(block, new ResourceLocation(Constants.MODID, name));
        GameRegistry.register(itemBlock, new ResourceLocation(Constants.MODID, name));

        Atum.proxy.setBlockResourceLocation(Item.getItemFromBlock(block), name, tab);

        return block;
    }
}