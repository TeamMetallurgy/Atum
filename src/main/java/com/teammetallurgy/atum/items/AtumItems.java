package com.teammetallurgy.atum.items;

import com.teammetallurgy.atum.Atum;
import com.teammetallurgy.atum.blocks.AtumBlocks;
import com.teammetallurgy.atum.items.artifacts.*;
import com.teammetallurgy.atum.utils.Constants;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemSword;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class AtumItems {
    private static final ArmorMaterial mummyEnum = EnumHelper.addArmorMaterial("Mummy", "", 5, new int[]{1, 3, 2, 1}, 15); //TODO TextureName
    private static final ArmorMaterial wandererEnum = EnumHelper.addArmorMaterial("Wanderer", "", 10, new int[]{2, 3, 3, 2}, 15); //TODO TextureName
    private static final ArmorMaterial desertEnum = EnumHelper.addArmorMaterial("Desert", "", 20, new int[]{3, 6, 5, 3}, 15); //TODO TextureName

    public static final Item SCRAP = new Item();
    public static final Item LINEN = new Item();
    public static final Item SCARAB = new ItemScarab();
    public static final Item LOOT = new ItemLoot();
    public static final Item DATE = new ItemFood(4, 0.3F, false);
    public static final Item GOLDEN_DATE = new ItemAtumGoldenDate(5, 1.5F, false);
    public static final Item SCIMITAR = new ItemSword(ToolMaterial.IRON);
    public static final Item GREATSWORD = new ItemGreatsword(ToolMaterial.IRON);
    public static final Item BOW = new ItemAtumBow();
    public static final Item STONESOLDIER_SWORD = new ItemSword(ToolMaterial.IRON);
    public static final Item SCEPTER = new ItemSword(ToolMaterial.GOLD);
    public static final Item PTAHS_PICK = new ItemPtahsDecadence(ToolMaterial.EMERALD);
    public static final Item SOBEKS_RAGE = new ItemSobeksRage(ToolMaterial.EMERALD);
    public static final Item OSIRIS_WILL = new ItemOsirisWill(ToolMaterial.EMERALD);
    public static final Item AKERS_TOIL = new ItemAkersToil(ToolMaterial.EMERALD);
    public static final Item GEBS_BLESSING = new ItemGebsBlessing(ToolMaterial.EMERALD);
    public static final Item ATENS_FURY = new ItemAtensFury();
    public static final Item RAS_GLORY = new ItemRasGlory(ArmorMaterial.DIAMOND, 0, 0).setTextureFile("EgyptianArmor_1");
    public static final Item SEKHMETS_WRATH = new ItemSekhmetsWrath(1, 1).setTextureFile("EgyptianArmor_1");
    public static final Item NUTS_AGILITY = new ItemNutsAgility(ArmorMaterial.DIAMOND, 2, 2).setTextureFile("EgyptianArmor_2");
    public static final Item HORUS_FLIGHT = new ItemHorusFlight(ArmorMaterial.DIAMOND, 3, 3).setTextureFile("EgyptianArmor_1");
    public static final Item MONTHUS_STRIKE = new ItemMonthusStrike(ToolMaterial.EMERALD);
    public static final Item ANHURS_MIGHT = new ItemAnhursMight(ToolMaterial.EMERALD);
    public static final Item HEDETETS_STING = new ItemHedetetsSting(ToolMaterial.EMERALD);
    public static final Item HORUS_SOARING = new ItemHorusSoaring();
    public static final Item SHUS_BREATH = new ItemShusBreath();
    public static final Item PTAHS_DESTRUCTION = new ItemPtahsDestruction(ToolMaterial.EMERALD);
    public static final Item MONTHUS_BLAST = new ItemMonthusBlast();
    public static final Item NUS_FLUX = new ItemNusFlux(ToolMaterial.EMERALD);
    public static final Item MNEVIS_HORNS = new ItemMnevisHorns(ArmorMaterial.DIAMOND, 0, 0).setTextureFile("RubyArtifactArmor_1");
    public static final Item ISIS_EMBRACE = new ItemIsisEmbrace(ArmorMaterial.DIAMOND, 1, 1).setTextureFile("RubyArtifactArmor_1");
    public static final Item MAATS_BALANCE = new ItemMaatsBalance(ArmorMaterial.DIAMOND, 2, 2).setTextureFile("RubyArtifactArmor_2");
    public static final Item HEDETETS_VENOM = new ItemHedetetsVenom();
    public static final Item GEBS_SOLIDARITY = new ItemGebsSolidarity(ArmorMaterial.DIAMOND, 3, 3).setTextureFile("RubyArtifactArmor_1");
    public static final Item NUTS_CALL = new ItemNutsCall();
    public static final Item ANUKETS_BOUNTY = new ItemAnuketsBounty();
    public static final Item MAFDETS_QUICKNESS = new ItemMafdetsQuickness();
    public static final Item ISIS_HEALING = new ItemIsisHealing();
    public static final Item AMUNETS_HOMECOMING = new ItemAmunetsHomecoming();
    public static final Item ANUBIS_MERCY = new ItemAnubisMercy();
    public static final Item LIMESTONE_SHOVEL = new LimestoneShovel(ToolMaterial.STONE);
    public static final Item LIMESTONE_PICKAXE = new LimestonePickaxe(ToolMaterial.STONE);
    public static final Item LIMESTONE_AXE = new LimestoneAxe(ToolMaterial.STONE);
    public static final Item LIMESTONE_SWORD = new LimestoneSword(ToolMaterial.STONE);
    public static final Item LIMESTONE_HOE = new LimestoneHoe(ToolMaterial.STONE);
    public static final Item MUMMY_HELMET = new ItemTexturedArmor(mummyEnum, 0, 0).setRepairItem(SCRAP).setTextureFile("MummyArmor_1");
    public static final Item MUMMY_CHEST = new ItemTexturedArmor(mummyEnum, 0, 1).setRepairItem(SCRAP).setTextureFile("MummyArmor_1");
    public static final Item MUMMY_LEGS = new ItemTexturedArmor(mummyEnum, 0, 2).setRepairItem(SCRAP).setTextureFile("MummyArmor_2");
    public static final Item MUMMY_BOOTS = new ItemTexturedArmor(mummyEnum, 0, 3).setRepairItem(SCRAP).setTextureFile("MummyArmor_1");
    public static final Item WANDERER_HELMET = new ItemTexturedArmor(wandererEnum, 0, 0).setRepairItem(LINEN).setTextureFile("WandererArmor_1");
    public static final Item WANDERER_CHEST = new ItemTexturedArmor(wandererEnum, 0, 1).setRepairItem(LINEN).setTextureFile("WandererArmor_1");
    public static final Item WANDERER_LEGS = new ItemTexturedArmor(wandererEnum, 0, 2).setRepairItem(LINEN).setTextureFile("WandererArmor_2");
    public static final Item WANDERER_BOOTS = new ItemTexturedArmor(wandererEnum, 0, 3).setRepairItem(LINEN).setTextureFile("WandererArmor_1");
    public static final Item DESERT_HELMET = new ItemTexturedArmor(desertEnum, 0, 0).setRepairItem(Items.iron_ingot).setTextureFile("DesertArmor_1");
    public static final Item DESERT_CHEST = new ItemTexturedArmor(desertEnum, 0, 1).setRepairItem(Items.iron_ingot).setTextureFile("DesertArmor_1");
    public static final Item DESERT_LEGS = new ItemTexturedArmor(desertEnum, 0, 2).setRepairItem(Items.iron_ingot).setTextureFile("DesertArmor_2");
    public static final Item DESERT_BOOTS = new ItemTexturedArmor(desertEnum, 0, 3).setRepairItem(Items.iron_ingot).setTextureFile("DesertArmor_1");
    public static final Item PAPYRUS_PLANT = new Item() /*new ItemReed(AtumBlocks.PAPYRUS)*/; //TODO
    public static final Item ECTOPLASM = new Item();
    public static final Item DUSTY_BONE = new Item();
    public static final Item STONE_CHUNK = new Item();
    public static final Item SCROLL = new Item();
    public static final Item WOLF_PELT = new Item();
    public static final Item FLAX = new Item();
    public static final Item FLAX_SEED = new Item() /*new ItemSeeds(AtumBlocks.FLAX, Blocks.farmland)*/; //TODO
    public static final Item FISH = new ItemFish();
    public static final Item NEITHS_AUDACITY = new ItemNeithsAudacity();
    public static final Item PALM_DOOR = new ItemAtumDoor();
    public static final Item DEADWOOD_DOOR = new ItemAtumDoor();
    public static final Item STICK = new ItemAtumStick();
    public static final Item DEADWOOD_BEETLE = new ItemDeadwoodBeetle();

    public static void registerItems() {
        register(SCRAP, "clothScrap");
        register(LINEN, "linen");
        register(SCARAB, "scarab");
        register(LOOT, "loot");
        register(DATE, "date");
        register(GOLDEN_DATE, "goldenDate");
        register(SCIMITAR, "scimitar");
        register(GREATSWORD, "greatsword");
        register(BOW, "bow");
        register(STONESOLDIER_SWORD, "stoneSoldierSword");
        register(SCEPTER, "scepter");
        register(PTAHS_PICK, "ptahsDecadence");
        register(SOBEKS_RAGE, "soteksRage");
        register(OSIRIS_WILL, "osirisWill");
        register(AKERS_TOIL, "akersToil");
        register(GEBS_BLESSING, "gebsBlessing");
        register(ATENS_FURY, "atensFury");
        register(RAS_GLORY, "rasGlory");
        register(SEKHMETS_WRATH, "sekhmetsWrath");
        register(NUTS_AGILITY, "nutsAgility");
        register(HORUS_FLIGHT, "horusFlight");
        register(MONTHUS_STRIKE, "monthusStrike");
        register(ANHURS_MIGHT, "anhursMight");
        register(HEDETETS_STING, "hedetetsSting");
        register(HORUS_SOARING, "horusSoaring");
        register(SHUS_BREATH, "shusBreath");
        register(PTAHS_DESTRUCTION, "ptahsDestruction");
        register(MONTHUS_BLAST, "monthusBlast");
        register(NUS_FLUX, "nusFlux");
        register(MNEVIS_HORNS, "mnevisHorns");
        register(ISIS_EMBRACE, "isisEmbrace");
        register(MAATS_BALANCE, "maatsBalance");
        register(HEDETETS_VENOM, "hedetetsVenom");
        register(GEBS_SOLIDARITY, "gebsSolidarity");
        register(NUTS_CALL, "nutsCall");
        register(ANUKETS_BOUNTY, "anuketsBounty" );
        register(MAFDETS_QUICKNESS, "mafdetsQuickness");
        register(ISIS_HEALING, "isisHealing");
        register(AMUNETS_HOMECOMING, "amunetsHomecoming");
        register(ANUBIS_MERCY, "anubisMercy");
        register(LIMESTONE_SHOVEL, "limestoneShovel");
        register(LIMESTONE_PICKAXE, "limestonePickaxe");
        register(LIMESTONE_AXE, "limestoneAxe");
        register(LIMESTONE_SWORD, "limestoneSword");
        register(LIMESTONE_HOE, "limestoneHoe");
        register(MUMMY_HELMET, "mummyHelmet");
        register(MUMMY_CHEST, "mummyChest");
        register(MUMMY_LEGS, "mummyLegs");
        register(MUMMY_BOOTS, "mummyBoots");
        register(WANDERER_HELMET, "wandererHelmet");
        register(WANDERER_CHEST, "wandererChest");
        register(WANDERER_LEGS, "wandererLegs");
        register(WANDERER_BOOTS, "wandererBoots");
        register(DESERT_HELMET, "desertHelmet");
        register(DESERT_CHEST, "desertChest");
        register(DESERT_LEGS, "desertLegs");
        register(DESERT_BOOTS, "desertBoots");
        //register(PAPYRUS_PLANT, "papyrusPlant"); //TODO
        register(ECTOPLASM, "ectoplasm");
        register(DUSTY_BONE, "dustyBone");
        register(STONE_CHUNK, "stoneChunk");
        register(SCROLL, "scroll");
        register(WOLF_PELT, "wolfPelt");
        register(FLAX, "flax");
        register(FLAX_SEED, "flaxSeeds");
        register(FISH, "fish");
        register(NEITHS_AUDACITY, "neithsAudacity");
        register(PALM_DOOR, "palmItemDoor"); //TODO
        register(DEADWOOD_DOOR, "deadwoodItemDoor"); //TODO
        register(STICK, "stick");
        register(DEADWOOD_BEETLE, "deadwoodBeetle");
    }

    public static Item register(Item item, String name) {
        return register(item, name, Atum.creativeTab);
    }

    private static Item register(Item item, String name, CreativeTabs tab) {
        item.setUnlocalizedName(Constants.MODID + "." + name);
        item.setCreativeTab(tab);

        GameRegistry.registerItem(item, name);
        Atum.proxy.setItemResourceLocation(item, name, tab);
        System.out.println("ItemName: " + name);

        return item;
    }
}