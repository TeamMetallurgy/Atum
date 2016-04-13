package com.teammetallurgy.atum.items;

import com.teammetallurgy.atum.Atum;
import com.teammetallurgy.atum.blocks.AtumBlocks;
import com.teammetallurgy.atum.items.artifacts.*;
import com.teammetallurgy.atum.utils.AtumUtils;
import com.teammetallurgy.atum.utils.Constants;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class AtumItems {
    private static final ArmorMaterial mummyEnum = EnumHelper.addArmorMaterial("Mummy", "", 5, new int[]{1, 3, 2, 1}, 15, SoundEvents.item_armor_equip_generic); //TODO TextureName
    private static final ArmorMaterial wandererEnum = EnumHelper.addArmorMaterial("Wanderer", "", 10, new int[]{2, 3, 3, 2}, 15, SoundEvents.item_armor_equip_generic); //TODO TextureName
    private static final ArmorMaterial desertEnum = EnumHelper.addArmorMaterial("Desert", "", 20, new int[]{3, 6, 5, 3}, 15, SoundEvents.item_armor_equip_generic); //TODO TextureName

    public static final Item SCRAP = new Item();
    public static final Item LINEN = new Item();
    public static final Item SCARAB = new ItemScarab();
    public static final Item LOOT = new ItemLoot();
    public static final Item DATE = new ItemFood(4, 0.3F, false);
    public static final Item GOLDEN_DATE = new ItemAtumGoldenDate(5, 1.5F, false);
    public static final Item SCIMITAR = new ItemSword(ToolMaterial.IRON);
    public static final Item GREATSWORD = new ItemGreatsword(ToolMaterial.IRON);
    public static final Item SHORT_BOW = new ItemAtumBaseBow();
    public static final Item STONEGUARD_SWORD = new ItemSword(ToolMaterial.IRON);
    public static final Item SCEPTER = new ItemSword(ToolMaterial.GOLD);
    public static final Item PTAHS_DECADENCE = new ItemPtahsDecadence(ToolMaterial.DIAMOND);
    public static final Item SOBEKS_RAGE = new ItemSobeksRage(ToolMaterial.DIAMOND);
    public static final Item OSIRIS_WILL = new ItemOsirisWill(ToolMaterial.DIAMOND);
    public static final Item AKERS_TOIL = new ItemAkersToil(ToolMaterial.DIAMOND);
    public static final Item GEBS_BLESSING = new ItemGebsBlessing(ToolMaterial.DIAMOND);
    public static final Item ATENS_FURY = new ItemAtensFury();
    public static final Item RAS_GLORY = new ItemRasGlory(ArmorMaterial.DIAMOND, 0, EntityEquipmentSlot.HEAD).setTextureFile("EgyptianArmor_1");
    public static final Item SEKHMETS_WRATH = new ItemSekhmetsWrath(1, EntityEquipmentSlot.CHEST).setTextureFile("EgyptianArmor_1");
    public static final Item NUTS_AGILITY = new ItemNutsAgility(ArmorMaterial.DIAMOND, 2, EntityEquipmentSlot.LEGS).setTextureFile("EgyptianArmor_2");
    public static final Item HORUS_FLIGHT = new ItemHorusFlight(ArmorMaterial.DIAMOND, 3, EntityEquipmentSlot.FEET).setTextureFile("EgyptianArmor_1");
    public static final Item MONTHUS_STRIKE = new ItemMonthusStrike(ToolMaterial.DIAMOND);
    public static final Item ANHURS_MIGHT = new ItemAnhursMight(ToolMaterial.DIAMOND);
    public static final Item HEDETETS_STING = new ItemHedetetsSting(ToolMaterial.DIAMOND);
    public static final Item HORUS_SOARING = new ItemHorusSoaring();
    public static final Item SHUS_BREATH = new ItemShusBreath();
    public static final Item PTAHS_DESTRUCTION = new ItemPtahsDestruction(ToolMaterial.DIAMOND);
    public static final Item MONTHUS_BLAST = new ItemMonthusBlast();
    public static final Item NUS_FLUX = new ItemNusFlux(ToolMaterial.DIAMOND);
    public static final Item MNEVIS_HORNS = new ItemMnevisHorns(ArmorMaterial.DIAMOND, 0, EntityEquipmentSlot.HEAD).setTextureFile("RubyArtifactArmor_1");
    public static final Item ISIS_EMBRACE = new ItemIsisEmbrace(ArmorMaterial.DIAMOND, 1, EntityEquipmentSlot.CHEST).setTextureFile("RubyArtifactArmor_1");
    public static final Item MAATS_BALANCE = new ItemMaatsBalance(ArmorMaterial.DIAMOND, 2, EntityEquipmentSlot.LEGS).setTextureFile("RubyArtifactArmor_2");
    public static final Item HEDETETS_VENOM = new ItemHedetetsVenom();
    public static final Item GEBS_SOLIDARITY = new ItemGebsSolidarity(ArmorMaterial.DIAMOND, 3, EntityEquipmentSlot.FEET).setTextureFile("RubyArtifactArmor_1");
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
    public static final Item MUMMY_HELMET = new ItemTexturedArmor(mummyEnum, 0, EntityEquipmentSlot.HEAD).setRepairItem(SCRAP).setTextureFile("MummyArmor_1");
    public static final Item MUMMY_CHEST = new ItemTexturedArmor(mummyEnum, 0, EntityEquipmentSlot.CHEST).setRepairItem(SCRAP).setTextureFile("MummyArmor_1");
    public static final Item MUMMY_LEGS = new ItemTexturedArmor(mummyEnum, 0, EntityEquipmentSlot.LEGS).setRepairItem(SCRAP).setTextureFile("MummyArmor_2");
    public static final Item MUMMY_BOOTS = new ItemTexturedArmor(mummyEnum, 0, EntityEquipmentSlot.FEET).setRepairItem(SCRAP).setTextureFile("MummyArmor_1");
    public static final Item WANDERER_HELMET = new ItemTexturedArmor(wandererEnum, 0, EntityEquipmentSlot.HEAD).setRepairItem(LINEN).setTextureFile("WandererArmor_1");
    public static final Item WANDERER_CHEST = new ItemTexturedArmor(wandererEnum, 0, EntityEquipmentSlot.CHEST).setRepairItem(LINEN).setTextureFile("WandererArmor_1");
    public static final Item WANDERER_LEGS = new ItemTexturedArmor(wandererEnum, 0, EntityEquipmentSlot.LEGS).setRepairItem(LINEN).setTextureFile("WandererArmor_2");
    public static final Item WANDERER_BOOTS = new ItemTexturedArmor(wandererEnum, 0, EntityEquipmentSlot.FEET).setRepairItem(LINEN).setTextureFile("WandererArmor_1");
    public static final Item DESERT_HELMET = new ItemTexturedArmor(desertEnum, 0, EntityEquipmentSlot.HEAD).setRepairItem(Items.iron_ingot).setTextureFile("DesertArmor_1");
    public static final Item DESERT_CHEST = new ItemTexturedArmor(desertEnum, 0, EntityEquipmentSlot.CHEST).setRepairItem(Items.iron_ingot).setTextureFile("DesertArmor_1");
    public static final Item DESERT_LEGS = new ItemTexturedArmor(desertEnum, 0, EntityEquipmentSlot.LEGS).setRepairItem(Items.iron_ingot).setTextureFile("DesertArmor_2");
    public static final Item DESERT_BOOTS = new ItemTexturedArmor(desertEnum, 0, EntityEquipmentSlot.FEET).setRepairItem(Items.iron_ingot).setTextureFile("DesertArmor_1");
    public static final Item PAPYRUS_PLANT = new ItemBlockSpecial(AtumBlocks.PAPYRUS);
    public static final Item ECTOPLASM = new Item();
    public static final Item DUSTY_BONE = new Item();
    public static final Item STONE_CHUNK = new Item();
    public static final Item SCROLL = new Item();
    public static final Item WOLF_PELT = new Item();
    public static final Item FLAX = new Item();
    public static final Item FLAX_SEED = new ItemSeeds(AtumBlocks.FLAX, Blocks.farmland);
    public static final Item FISH = new ItemFish();
    public static final Item NEITHS_AUDACITY = new ItemNeithsAudacity();
    public static final Item PALM_DOOR = new ItemAtumDoor();
    public static final Item DEADWOOD_DOOR = new ItemAtumDoor();
    public static final Item STICK = new ItemAtumStick();
    public static final Item DEADWOOD_BEETLE = new ItemDeadwoodBeetle();

    public static void registerItems() {
        register(SCRAP, "cloth_scrap");
        register(LINEN, "linen");
        register(SCARAB, "scarab");
        register(LOOT, "loot");
        register(DATE, "date");
        register(GOLDEN_DATE, "golden_date");
        register(SCIMITAR, "scimitar");
        register(GREATSWORD, "greatsword");
        register(SHORT_BOW, "short_bow");
        register(STONEGUARD_SWORD, "stoneguard_sword");
        register(SCEPTER, "scepter");
        register(PTAHS_DECADENCE, "ptahs_decadence");
        register(SOBEKS_RAGE, "soteks_rage");
        register(OSIRIS_WILL, "osiris_will");
        register(AKERS_TOIL, "akers_toil");
        register(GEBS_BLESSING, "gebs_blessing");
        register(ATENS_FURY, "atens_fury");
        register(RAS_GLORY, "ras_glory");
        register(SEKHMETS_WRATH, "sekhmets_wrath");
        register(NUTS_AGILITY, "nuts_agility");
        register(HORUS_FLIGHT, "horus_flight");
        register(MONTHUS_STRIKE, "monthus_strike");
        register(ANHURS_MIGHT, "anhurs_might");
        register(HEDETETS_STING, "hedetets_sting");
        register(HORUS_SOARING, "horus_soaring");
        register(SHUS_BREATH, "shus_breath");
        register(PTAHS_DESTRUCTION, "ptahs_destruction");
        register(MONTHUS_BLAST, "monthus_blast");
        register(NUS_FLUX, "nus_flux");
        register(MNEVIS_HORNS, "mnevis_horns");
        register(ISIS_EMBRACE, "isis_embrace");
        register(MAATS_BALANCE, "maats_balance");
        register(HEDETETS_VENOM, "hedetets_venom");
        register(GEBS_SOLIDARITY, "gebs_solidarity");
        register(NUTS_CALL, "nuts_call");
        register(ANUKETS_BOUNTY, "anukets_bounty");
        register(MAFDETS_QUICKNESS, "mafdets_quickness");
        register(ISIS_HEALING, "isis_healing");
        register(AMUNETS_HOMECOMING, "amunets_homecoming");
        register(ANUBIS_MERCY, "anubis_mercy");
        register(LIMESTONE_SHOVEL, "limestone_shovel");
        register(LIMESTONE_PICKAXE, "limestone_pickaxe");
        register(LIMESTONE_AXE, "limestone_axe");
        register(LIMESTONE_SWORD, "limestone_sword");
        register(LIMESTONE_HOE, "limestone_hoe");
        register(MUMMY_HELMET, "mummy_helmet");
        register(MUMMY_CHEST, "mummy_chest");
        register(MUMMY_LEGS, "mummy_legs");
        register(MUMMY_BOOTS, "mummy_boots");
        register(WANDERER_HELMET, "wanderer_helmet");
        register(WANDERER_CHEST, "wanderer_chest");
        register(WANDERER_LEGS, "wanderer_legs");
        register(WANDERER_BOOTS, "wanderer_boots");
        register(DESERT_HELMET, "desert_helmet");
        register(DESERT_CHEST, "desert_chest");
        register(DESERT_LEGS, "desert_legs");
        register(DESERT_BOOTS, "desert_boots");
        register(PAPYRUS_PLANT, "papyrus_plant");
        register(ECTOPLASM, "ectoplasm");
        register(DUSTY_BONE, "dusty_bone");
        register(STONE_CHUNK, "stone_chunk");
        register(SCROLL, "scroll");
        register(WOLF_PELT, "wolf_pelt");
        register(FLAX, "flax");
        register(FLAX_SEED, "flax_seeds");
        register(FISH, "fish");
        register(NEITHS_AUDACITY, "neiths_audacity");
        register(PALM_DOOR, "palm_item_door"); //TODO
        register(DEADWOOD_DOOR, "deadwood_item_door"); //TODO
        register(STICK, "stick");
        register(DEADWOOD_BEETLE, "deadwood_beetle");
    }

    public static Item register(Item item, String name) {
        return register(item, name, Atum.creativeTab);
    }

    private static Item register(Item item, String name, CreativeTabs tab) {
        item.setUnlocalizedName(Constants.MODID + "." + AtumUtils.toUnlocalizedName(name));
        item.setCreativeTab(tab);

        GameRegistry.register(item, new ResourceLocation(Constants.MODID, name));
        Atum.proxy.setItemResourceLocation(item, name, tab);
        System.out.println("ItemName: " + name);

        return item;
    }
}