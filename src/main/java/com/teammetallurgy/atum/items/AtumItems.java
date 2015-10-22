package com.teammetallurgy.atum.items;

import com.teammetallurgy.atum.Atum;
import com.teammetallurgy.atum.blocks.AtumBlocks;
import com.teammetallurgy.atum.items.artifacts.*;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemSeeds;
import net.minecraftforge.common.util.EnumHelper;

public class AtumItems {

    private static ArmorMaterial mummyEnum = EnumHelper.addArmorMaterial("Mummy", 5, new int[]{1, 3, 2, 1}, 15);
    private static ArmorMaterial wandererEnum = EnumHelper.addArmorMaterial("Wanderer", 10, new int[]{2, 3, 3, 2}, 15);
    private static ArmorMaterial desertEnum = EnumHelper.addArmorMaterial("Desert", 20, new int[]{3, 6, 5, 3}, 15);
    
    public static Item ITEM_SCRAP = new Item().setUnlocalizedName("clothScrap").setTextureName("atum:ClothScrap");
    public static Item ITEM_LINEN = new Item().setUnlocalizedName("linen").setTextureName("atum:Linen");
    public static Item ITEM_SCARAB = new ItemScarab().setUnlocalizedName("scarab");
    public static Item ITEM_LOOT = new ItemLoot().setUnlocalizedName("loot");
    public static Item ITEM_DATE = new ItemFood(5, 1.5F, false).setTextureName("atum:Date").setUnlocalizedName("date");
    public static Item ITEM_SCIMITAR = new ItemScimitar(ToolMaterial.IRON).setUnlocalizedName("scimitar");
    public static Item ITEM_GREATSWORD = new ItemGreatsword(ToolMaterial.IRON).setUnlocalizedName("greatsword").setTextureName("atum:Greatsword");
    public static Item ITEM_BOW = new ItemAtumBow().setUnlocalizedName("bow");
    public static Item ITEM_STONESOLDIER_SWORD = new ItemStoneSoldierSword(ToolMaterial.IRON).setUnlocalizedName("stoneSoldierSword");
    public static Item ITEM_SCEPTER = new ItemScepter(ToolMaterial.GOLD).setUnlocalizedName("scepter");
    public static Item ITEM_PTAHSPICK = new ItemPtahsDecadence(ToolMaterial.EMERALD).setUnlocalizedName("ptahsDecadence");
    public static Item sobeksRage = new ItemSobeksRage(ToolMaterial.EMERALD).setUnlocalizedName("soteksRage");
    public static Item osirisWill = new ItemOsirisWill(ToolMaterial.EMERALD).setUnlocalizedName("osirisWill");
    public static Item akersToil = new ItemAkersToil(ToolMaterial.EMERALD).setUnlocalizedName("akersToil");
    public static Item gebsBlessing = new ItemGebsBlessing(ToolMaterial.EMERALD).setUnlocalizedName("gebsBlessing");
    public static Item atensFury = new ItemAtensFury().setUnlocalizedName("atensFury");
    public static Item rasGlory = new ItemRasGlory(ArmorMaterial.DIAMOND, 0, 0).setTextureFile("EgyptianArmor_1").setUnlocalizedName("rasGlory");
    public static Item sekhmetsWrath = new ItemSekhmetsWrath(1, 1).setTextureFile("EgyptianArmor_1").setUnlocalizedName("sekhmetsWrath");
    public static Item nutsAgility = new ItemNutsAgility(ArmorMaterial.DIAMOND, 2, 2).setTextureFile("EgyptianArmor_2").setUnlocalizedName("nutsAgility");
    public static Item horusFlight = new ItemHorusFlight(ArmorMaterial.DIAMOND, 3, 3).setTextureFile("EgyptianArmor_1").setUnlocalizedName("horusFlight");
    public static Item monthusStrike = new ItemMonthusStrike(ToolMaterial.EMERALD).setUnlocalizedName("monthusStrike");
    public static Item anhursMight = new ItemAnhursMight(ToolMaterial.EMERALD).setUnlocalizedName("anhursMight");
    public static Item hedetetsSting = new ItemHedetetsSting(ToolMaterial.EMERALD).setUnlocalizedName("hedetetsSting");
    public static Item horusSoaring = new ItemHorusSoaring().setUnlocalizedName("horusSoaring");
    public static Item shusBreath = new ItemShusBreath().setUnlocalizedName("shusBreath");
    public static Item ptahsDestruction = new ItemPtahsDestruction(ToolMaterial.EMERALD).setUnlocalizedName("ptahsDestruction");
    public static Item monthusBlast = new ItemMonthusBlast().setUnlocalizedName("monthusBlast");
    public static Item nusFlux = new ItemNusFlux(ToolMaterial.EMERALD).setUnlocalizedName("nusFlux");
    public static Item mnevisHorns = new ItemMnevisHorns(ArmorMaterial.DIAMOND, 0, 0).setTextureFile("RubyArtifactArmor_1").setUnlocalizedName("mnevisHorns");
    public static Item isisEmbrace = new ItemIsisEmbrace(ArmorMaterial.DIAMOND, 1, 1).setTextureFile("RubyArtifactArmor_1").setUnlocalizedName("isisEmbrace");
    public static Item maatsBalance = new ItemMaatsBalance(ArmorMaterial.DIAMOND, 2, 2).setTextureFile("RubyArtifactArmor_2").setUnlocalizedName("maatsBalance");
    public static Item hedetetsVenom = new ItemHedetetsVenom().setUnlocalizedName("hedetetsVenom");
    public static Item gebsSolidarity = new ItemGebsSolidarity(ArmorMaterial.DIAMOND, 3, 3).setTextureFile("RubyArtifactArmor_1").setUnlocalizedName("gebsSolidarity");
    public static Item nutsCall = new ItemNutsCall().setUnlocalizedName("nutsCall");
    public static Item anuketsBounty = new ItemAnuketsBounty().setUnlocalizedName("anuketsBounty");
    public static Item mafdetsQuickness = new ItemMafdetsQuickness().setUnlocalizedName("mafdetsQuickness");
    public static Item isisHealing = new ItemIsisHealing().setUnlocalizedName("isisHealing");
    public static Item amunetsHomecoming = new ItemAmunetsHomecoming().setUnlocalizedName("amunetsHomecoming");
    public static Item anubisMercy = new ItemAnubisMercy().setUnlocalizedName("anubisMercy");
    public static Item limestoneShovel = new LimestoneShovel(ToolMaterial.STONE).setUnlocalizedName("limestoneShovel");
    public static Item limestonePickaxe = new LimestonePickaxe(ToolMaterial.STONE).setUnlocalizedName("limestonePickaxe");
    public static Item limestoneAxe = new LimestoneAxe(ToolMaterial.STONE).setUnlocalizedName("limestoneAxe");
    public static Item limestoneSword = new LimestoneSword(ToolMaterial.STONE).setUnlocalizedName("limestoneSword");
    public static Item limestoneHoe = new LimestoneHoe(ToolMaterial.STONE).setUnlocalizedName("limestoneHoe");
    public static Item mummyHelmet = new ItemTexturedArmor(mummyEnum, 0, 0).setRepairItem(ITEM_SCRAP).setTextureFile("MummyArmor_1").setUnlocalizedName("mummyHelmet").setTextureName("atum:MummyHelmet");
    public static Item mummyChest = new ItemTexturedArmor(mummyEnum, 0, 1).setRepairItem(ITEM_SCRAP).setTextureFile("MummyArmor_1").setUnlocalizedName("mummyChest").setTextureName("atum:MummyChest");
    public static Item mummyLegs = new ItemTexturedArmor(mummyEnum, 0, 2).setRepairItem(ITEM_SCRAP).setTextureFile("MummyArmor_2").setUnlocalizedName("mummyLegs").setTextureName("atum:MummyLegs");
    public static Item mummyBoots = new ItemTexturedArmor(mummyEnum, 0, 3).setRepairItem(ITEM_SCRAP).setTextureFile("MummyArmor_1").setUnlocalizedName("mummyBoots").setTextureName("atum:MummyBoots");
    public static Item wandererHelmet = new ItemTexturedArmor(wandererEnum, 0, 0).setRepairItem(ITEM_LINEN).setTextureFile("WandererArmor_1").setUnlocalizedName("wandererHelmet").setTextureName("atum:WandererHelmet");
    public static Item wandererChest = new ItemTexturedArmor(wandererEnum, 0, 1).setRepairItem(ITEM_LINEN).setTextureFile("WandererArmor_1").setUnlocalizedName("wandererChest").setTextureName("atum:WandererChest");
    public static Item wandererLegs = new ItemTexturedArmor(wandererEnum, 0, 2).setRepairItem(ITEM_LINEN).setTextureFile("WandererArmor_2").setUnlocalizedName("wandererLegs").setTextureName("atum:WandererLegs");
    public static Item wandererBoots = new ItemTexturedArmor(wandererEnum, 0, 3).setRepairItem(ITEM_LINEN).setTextureFile("WandererArmor_1").setUnlocalizedName("wandererBoots").setTextureName("atum:WandererBoots");
    public static Item desertHelmet = new ItemTexturedArmor(desertEnum, 0, 0).setRepairItem(Items.iron_ingot).setTextureFile("DesertArmor_1").setUnlocalizedName("desertHelmet").setTextureName("atum:DesertHelmet");
    public static Item desertChest = new ItemTexturedArmor(desertEnum, 0, 1).setRepairItem(Items.iron_ingot).setTextureFile("DesertArmor_1").setUnlocalizedName("desertChest").setTextureName("atum:DesertChest");
    public static Item desertLegs = new ItemTexturedArmor(desertEnum, 0, 2).setRepairItem(Items.iron_ingot).setTextureFile("DesertArmor_2").setUnlocalizedName("desertLegs").setTextureName("atum:DesertLegs");
    public static Item desertBoots = new ItemTexturedArmor(desertEnum, 0, 3).setRepairItem(Items.iron_ingot).setTextureFile("DesertArmor_1").setUnlocalizedName("desertBoots").setTextureName("atum:DesertBoots");
    public static Item papyrusPlant = new ItemPapyrusPlant(AtumBlocks.BLOCK_PAPYRUS).setUnlocalizedName("papyrusPlant");
    public static Item ITEM_ECTOPLASM = new Item().setUnlocalizedName("ectoplasm").setTextureName("atum:Ectoplasm");
    public static Item ITEM_DUSTYBONE = new Item().setUnlocalizedName("dustyBone").setTextureName("atum:DustyBone");
    public static Item ITEM_STONECHUNK = new Item().setUnlocalizedName("stoneChunk").setTextureName("atum:StoneChunk");
    public static Item ITEM_SCROLL = new Item().setUnlocalizedName("scroll").setTextureName("atum:Scroll");
    public static Item ITEM_PELT = new Item().setUnlocalizedName("wolfPelt").setTextureName("atum:WolfPelt");
    public static Item ITEM_FLAX = new Item().setUnlocalizedName("flax").setTextureName("atum:FlaxItem");
    public static Item ITEM_FLAXSEED = new ItemSeeds(AtumBlocks.BLOCK_FLAX, Blocks.farmland).setUnlocalizedName("flaxSeeds").setTextureName("atum:FlaxSeeds");
    public static Item ITEM_FISH = new ItemFish().setUnlocalizedName("fish");
    public static Item neithsAudacity = new ItemNeithsAudacity().setUnlocalizedName("neithsAudacity");

    public AtumItems() {
        registerItems();
    }

    public void registerItems() {
        this.register(ITEM_SCARAB);
        this.register(ITEM_LOOT);
        this.register(ITEM_DATE);
        this.register(ITEM_SCIMITAR);
        this.register(ITEM_GREATSWORD);
        this.register(ITEM_SCEPTER);
        this.register(ITEM_STONESOLDIER_SWORD);
        this.register(ITEM_BOW);
        this.register(ITEM_PTAHSPICK);
        this.register(sobeksRage);
        this.register(osirisWill);
        this.register(akersToil);
        this.register(gebsBlessing);
        this.register(atensFury);
        this.register(rasGlory);
        this.register(sekhmetsWrath);
        this.register(nutsAgility);
        this.register(horusFlight);
        this.register(monthusStrike);
        this.register(anhursMight);
        this.register(hedetetsSting);
        this.register(horusSoaring);
        this.register(shusBreath);
        this.register(ptahsDestruction);
        this.register(monthusBlast);
        this.register(nusFlux);
        this.register(mnevisHorns);
        this.register(isisEmbrace);
        this.register(maatsBalance);
        this.register(hedetetsVenom);
        this.register(gebsSolidarity);
        this.register(nutsCall);
        this.register(anuketsBounty);
        this.register(mafdetsQuickness);
        this.register(isisHealing);
        this.register(amunetsHomecoming);
        this.register(anubisMercy);
        this.register(limestoneShovel);
        this.register(limestonePickaxe);
        this.register(limestoneAxe);
        this.register(limestoneSword);
        this.register(limestoneHoe);
        this.register(mummyHelmet);
        this.register(mummyChest);
        this.register(mummyLegs);
        this.register(mummyBoots);
        this.register(wandererHelmet);
        this.register(wandererChest);
        this.register(wandererLegs);
        this.register(wandererBoots);
        this.register(desertHelmet);
        this.register(desertChest);
        this.register(desertLegs);
        this.register(desertBoots);
        this.register(papyrusPlant);
        this.register(ITEM_ECTOPLASM);
        this.register(ITEM_DUSTYBONE);
        this.register(ITEM_STONECHUNK);
        this.register(ITEM_SCRAP);
        this.register(ITEM_SCROLL);
        this.register(ITEM_PELT);
        this.register(ITEM_LINEN);
        this.register(ITEM_FLAX);
        this.register(ITEM_FLAXSEED);
        this.register(ITEM_FISH);
        this.register(neithsAudacity);
    }

    private void register(Item item) {
        GameRegistry.registerItem(item, item.getUnlocalizedName());
        item.setCreativeTab(Atum.creativeTab);
    }

}
