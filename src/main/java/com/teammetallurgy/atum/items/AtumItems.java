package com.teammetallurgy.atum.items;

import com.teammetallurgy.atum.Atum;
import com.teammetallurgy.atum.blocks.AtumBlocks;
import com.teammetallurgy.atum.items.artifacts.*;
import com.teammetallurgy.atum.utils.Constants;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.*;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class AtumItems {
    private static ArmorMaterial mummyEnum = EnumHelper.addArmorMaterial("Mummy", "", 5, new int[]{1, 3, 2, 1}, 15); //TODO TextureName
    private static ArmorMaterial wandererEnum = EnumHelper.addArmorMaterial("Wanderer", "", 10, new int[]{2, 3, 3, 2}, 15); //TODO TextureName
    private static ArmorMaterial desertEnum = EnumHelper.addArmorMaterial("Desert", "", 20, new int[]{3, 6, 5, 3}, 15); //TODO TextureName

    public static Item SCRAP = new Item();
    public static Item LINEN = new Item();
    public static Item SCARAB = new ItemScarab();
    public static Item LOOT = new ItemLoot();
    public static Item DATE = new ItemFood(4, 0.3F, false);
    public static Item GOLDEN_DATE = new ItemAtumGoldenDate(5, 1.5F, false);
    public static Item SCIMITAR = new ItemSword(ToolMaterial.IRON);
    public static Item GREATSWORD = new ItemGreatsword(ToolMaterial.IRON);
    public static Item BOW = new ItemAtumBow();
    public static Item STONESOLDIER_SWORD = new ItemSword(ToolMaterial.IRON);
    public static Item SCEPTER = new ItemSword(ToolMaterial.GOLD);
    public static Item PTAHS_PICK = new ItemPtahsDecadence(ToolMaterial.EMERALD);
    public static Item SOBEKS_RAGE = new ItemSobeksRage(ToolMaterial.EMERALD);
    public static Item OSIRIS_WILL = new ItemOsirisWill(ToolMaterial.EMERALD);
    public static Item AKERS_TOIL = new ItemAkersToil(ToolMaterial.EMERALD);
    public static Item GEBS_BLESSING = new ItemGebsBlessing(ToolMaterial.EMERALD);
    public static Item ATENS_FURY = new ItemAtensFury();
    public static Item RAS_GLORY = new ItemRasGlory(ArmorMaterial.DIAMOND, 0, 0).setTextureFile("EgyptianArmor_1");
    public static Item SEKHMETS_WRATH = new ItemSekhmetsWrath(1, 1).setTextureFile("EgyptianArmor_1");
    public static Item NUTS_AGILITY = new ItemNutsAgility(ArmorMaterial.DIAMOND, 2, 2).setTextureFile("EgyptianArmor_2");
    public static Item HORUS_FLIGHT = new ItemHorusFlight(ArmorMaterial.DIAMOND, 3, 3).setTextureFile("EgyptianArmor_1");
    public static Item MONTHUS_STRIKE = new ItemMonthusStrike(ToolMaterial.EMERALD);
    public static Item ANHURS_MIGHT = new ItemAnhursMight(ToolMaterial.EMERALD);
    public static Item HEDETETS_STING = new ItemHedetetsSting(ToolMaterial.EMERALD);
    public static Item HORUS_SOARING = new ItemHorusSoaring();
    public static Item SHUS_BREATH = new ItemShusBreath();
    public static Item PTAHS_DESTRUCTION = new ItemPtahsDestruction(ToolMaterial.EMERALD);
    public static Item MONTHUS_BLAST = new ItemMonthusBlast();
    public static Item NUS_FLUX = new ItemNusFlux(ToolMaterial.EMERALD);
    public static Item MNEVIS_HORNS = new ItemMnevisHorns(ArmorMaterial.DIAMOND, 0, 0).setTextureFile("RubyArtifactArmor_1");
    public static Item ISIS_EMBRACE = new ItemIsisEmbrace(ArmorMaterial.DIAMOND, 1, 1).setTextureFile("RubyArtifactArmor_1");
    public static Item MAATS_BALANCE = new ItemMaatsBalance(ArmorMaterial.DIAMOND, 2, 2).setTextureFile("RubyArtifactArmor_2");
    public static Item HEDETETS_VENOM = new ItemHedetetsVenom();
    public static Item GEBS_SOLIDARITY = new ItemGebsSolidarity(ArmorMaterial.DIAMOND, 3, 3).setTextureFile("RubyArtifactArmor_1");
    public static Item NUTS_CALL = new ItemNutsCall();
    public static Item ANUKETS_BOUNTY = new ItemAnuketsBounty();
    public static Item MAFDETS_QUICKNESS = new ItemMafdetsQuickness();
    public static Item ISIS_HEALING = new ItemIsisHealing();
    public static Item AMUNETS_HOMECOMING = new ItemAmunetsHomecoming();
    public static Item ANUBIS_MERCY = new ItemAnubisMercy();
    public static Item LIMESTONE_SHOVEL = new LimestoneShovel(ToolMaterial.STONE);
    public static Item LIMESTONE_PICKAXE = new LimestonePickaxe(ToolMaterial.STONE);
    public static Item LIMESTONE_AXE = new LimestoneAxe(ToolMaterial.STONE);
    public static Item LIMESTONE_SWORD = new LimestoneSword(ToolMaterial.STONE);
    public static Item LIMESTONE_HOE = new LimestoneHoe(ToolMaterial.STONE);
    public static Item MUMMY_HELMET = new ItemTexturedArmor(mummyEnum, 0, 0).setRepairItem(SCRAP).setTextureFile("MummyArmor_1");
    public static Item MUMMY_CHEST = new ItemTexturedArmor(mummyEnum, 0, 1).setRepairItem(SCRAP).setTextureFile("MummyArmor_1");
    public static Item MUMMY_LEGS = new ItemTexturedArmor(mummyEnum, 0, 2).setRepairItem(SCRAP).setTextureFile("MummyArmor_2");
    public static Item MUMMY_BOOTS = new ItemTexturedArmor(mummyEnum, 0, 3).setRepairItem(SCRAP).setTextureFile("MummyArmor_1");
    public static Item WANDERER_HELMET = new ItemTexturedArmor(wandererEnum, 0, 0).setRepairItem(LINEN).setTextureFile("WandererArmor_1");
    public static Item WANDERER_CHEST = new ItemTexturedArmor(wandererEnum, 0, 1).setRepairItem(LINEN).setTextureFile("WandererArmor_1");
    public static Item WANDERER_LEGS = new ItemTexturedArmor(wandererEnum, 0, 2).setRepairItem(LINEN).setTextureFile("WandererArmor_2");
    public static Item WANDERER_BOOTS = new ItemTexturedArmor(wandererEnum, 0, 3).setRepairItem(LINEN).setTextureFile("WandererArmor_1");
    public static Item DESERT_HELMET = new ItemTexturedArmor(desertEnum, 0, 0).setRepairItem(Items.iron_ingot).setTextureFile("DesertArmor_1");
    public static Item DESERT_CHEST = new ItemTexturedArmor(desertEnum, 0, 1).setRepairItem(Items.iron_ingot).setTextureFile("DesertArmor_1");
    public static Item DESERT_LEGS = new ItemTexturedArmor(desertEnum, 0, 2).setRepairItem(Items.iron_ingot).setTextureFile("DesertArmor_2");
    public static Item DESERT_BOOTS = new ItemTexturedArmor(desertEnum, 0, 3).setRepairItem(Items.iron_ingot).setTextureFile("DesertArmor_1");
    public static Item PAPYRUS_PLANT = new ItemReed(AtumBlocks.PAPYRUS);
    public static Item ECTOPLASM = new Item();
    public static Item DUSTY_BONE = new Item();
    public static Item STONE_CHUNK = new Item();
    public static Item SCROLL = new Item();
    public static Item WOLF_PELT = new Item();
    public static Item FLAX = new Item();
    public static Item FLAX_SEED = new ItemSeeds(AtumBlocks.FLAX, Blocks.farmland);
    public static Item FISH = new ItemFish();
    public static Item NEITHS_AUDACITY = new ItemNeithsAudacity();
    public static Item PALM_DOOR = new ItemAtumDoor();
    public static Item DEADWOOD_DOOR = new ItemAtumDoor();
    public static Item STICK = new ItemAtumStick();
    public static Item DEADWOOD_BEETLE = new ItemDeadwoodBeetle();

    public AtumItems() {
        registerItems();
    }

    public void registerItems() {
        this.register(SCRAP, "clothScrap");
        this.register(LINEN, "linen");
        this.register(SCARAB, "scarab");
        this.register(LOOT, "loot");
        this.register(DATE, "date");
        this.register(GOLDEN_DATE, "goldenDate");
        this.register(SCIMITAR, "scimitar");
        this.register(GREATSWORD, "greatsword");
        this.register(BOW, "bow");
        this.register(STONESOLDIER_SWORD, "stoneSoldierSword");
        this.register(SCEPTER, "scepter");
        this.register(PTAHS_PICK, "ptahsDecadence");
        this.register(SOBEKS_RAGE, "soteksRage");
        this.register(OSIRIS_WILL, "osirisWill");
        this.register(AKERS_TOIL, "akersToil");
        this.register(GEBS_BLESSING, "gebsBlessing");
        this.register(ATENS_FURY, "atensFury");
        this.register(RAS_GLORY, "rasGlory");
        this.register(SEKHMETS_WRATH, "sekhmetsWrath");
        this.register(NUTS_AGILITY, "nutsAgility");
        this.register(HORUS_FLIGHT, "horusFlight");
        this.register(MONTHUS_STRIKE, "monthusStrike");
        this.register(ANHURS_MIGHT, "anhursMight");
        this.register(HEDETETS_STING, "hedetetsSting");
        this.register(HORUS_SOARING, "horusSoaring");
        this.register(SHUS_BREATH, "shusBreath");
        this.register(PTAHS_DESTRUCTION, "ptahsDestruction");
        this.register(MONTHUS_BLAST, "monthusBlast");
        this.register(NUS_FLUX, "nusFlux");
        this.register(MNEVIS_HORNS, "mnevisHorns");
        this.register(ISIS_EMBRACE, "isisEmbrace");
        this.register(MAATS_BALANCE, "maatsBalance");
        this.register(HEDETETS_VENOM, "hedetetsVenom");
        this.register(GEBS_SOLIDARITY, "gebsSolidarity");
        this.register(NUTS_CALL, "nutsCall");
        this.register(ANUKETS_BOUNTY, "anuketsBounty", CreativeTabs.tabTools);
        this.register(MAFDETS_QUICKNESS, "mafdetsQuickness");
        this.register(ISIS_HEALING, "isisHealing");
        this.register(AMUNETS_HOMECOMING, "amunetsHomecoming");
        this.register(ANUBIS_MERCY, "anubisMercy");
        this.register(LIMESTONE_SHOVEL, "limestoneShovel");
        this.register(LIMESTONE_PICKAXE, "limestonePickaxe");
        this.register(LIMESTONE_AXE, "limestoneAxe");
        this.register(LIMESTONE_SWORD, "limestoneSword");
        this.register(LIMESTONE_HOE, "limestoneHoe");
        this.register(MUMMY_HELMET, "mummyHelmet");
        this.register(MUMMY_CHEST, "mummyChest");
        this.register(MUMMY_LEGS, "mummyLegs");
        this.register(MUMMY_BOOTS, "mummyBoots");
        this.register(WANDERER_HELMET, "wandererHelmet");
        this.register(WANDERER_CHEST, "wandererChest");
        this.register(WANDERER_LEGS, "wandererLegs");
        this.register(WANDERER_BOOTS, "wandererBoots");
        this.register(DESERT_HELMET, "desertHelmet");
        this.register(DESERT_CHEST, "desertChest");
        this.register(DESERT_LEGS, "desertLegs");
        this.register(DESERT_BOOTS, "desertBoots");
        this.register(PAPYRUS_PLANT, "papyrusPlant");
        this.register(ECTOPLASM, "ectoplasm");
        this.register(DUSTY_BONE, "dustyBone");
        this.register(STONE_CHUNK, "stoneChunk");
        this.register(SCROLL, "scroll");
        this.register(WOLF_PELT, "wolfPelt");
        this.register(FLAX, "flax");
        this.register(FLAX_SEED, "flaxSeeds");
        this.register(FISH, "fish");
        this.register(NEITHS_AUDACITY, "neithsAudacity", CreativeTabs.tabCombat);
        this.register(PALM_DOOR, "palmItemDoor"); //TODO
        this.register(DEADWOOD_DOOR, "deadwoodItemDoor"); //TODO
        this.register(STICK, "stick");
        this.register(DEADWOOD_BEETLE, "deadwoodBeetle");
    }

    public Item register(Item item, String name) {
        return register(item, name, Atum.creativeTab);
    }

    private Item register(Item item, String name, CreativeTabs tab) {
        item.setUnlocalizedName(Constants.MODID + "." + name);
        item.setCreativeTab(tab);

        GameRegistry.registerItem(item, name);
        Atum.proxy.setItemResourceLocation(item, name, tab);

        return item;
    }
}