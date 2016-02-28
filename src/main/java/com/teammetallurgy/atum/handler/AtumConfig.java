package com.teammetallurgy.atum.handler;

import com.teammetallurgy.atum.utils.Constants;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static net.minecraftforge.common.config.Configuration.CATEGORY_GENERAL;
import static net.minecraftforge.common.config.Configuration.CATEGORY_SPLITTER;

public class AtumConfig {

    public static Configuration config;

    public static String CATEGORY_WORLDGEN = "worldgen";
    public static boolean ALLOW_CREATION;
    public static boolean FOG_ENABLED;
    public static boolean OASIS_ENABLED;
    public static boolean COAL_ENABLED;
    public static boolean IRON_ENABLED;
    public static boolean GOLD_ENABLED;
    public static boolean REDSTONE_ENABLED;
    public static boolean DIAMOND_ENABLED;
    public static boolean LAPIS_ENABLED;

    public static int DIMENSION_ID;
    public static int COAL_VEIN;
    public static int IRON_VEIN;
    public static int GOLD_VEIN;
    public static int REDSTONE_VEIN;
    public static int DIAMOND_VEIN;
    public static int LAPIS_VEIN;

    public static final int DEFAULT_BIOME_WEIGHT = 20;

    public enum BiomeConfig {
    	SAND_PLAINS(200, "Sand Plains", 2.0F),
    	SAND_DUNES(201, "Sand Dunes", 1.0F),
    	SAND_HILLS(202, "Sand Hills", 0.75F),
    	LIMESTONE_MOUNTAINS(203, "Limestone Mountains", 0.75F),
    	LIMESTONE_CRAGS(204, "Limestone Crags", 0.5F),
    	//OASIS(205, "Oasis", 0.25F),
    	DRIED_RIVER(206, "Dried River", -1F),
    	//DEAD_OASIS(207, "Dead Oasis", 0.25F),
    	RUINED_CITY(208, "Ruined City", 0.5F);
    	    	
    	private final String friendlyName;
    	private int id;
    	private int weight;
    	private BiomeGenBase gen;
    	
    	private BiomeConfig(int defaultID, String friendlyName, float weightMultiplier) {
    		this.id = defaultID;
    		this.friendlyName = friendlyName;
    		this.weight = (int)(DEFAULT_BIOME_WEIGHT * weightMultiplier);
    	}
    	public void setID(int id) {
    		this.id = id;
    	}
    	public int getID() {
    		return this.id;
    	}
    	
    	public void setGen(BiomeGenBase gen) {
    		this.gen = gen;
    	}
    	public BiomeGenBase getGen() {
    		return this.gen;
    	}
    	
    	public String toString() {
    		return friendlyName;
    	}
    	public int getWeight() {
    		return weight;
    	}
    }

    public AtumConfig(File file) {
        this.config = new Configuration(file);

        FMLCommonHandler.instance().bus().register(this);
        syncConfigData();
    }

    @SubscribeEvent
    public void onConfigChange(ConfigChangedEvent.OnConfigChangedEvent event) {

        if (event.modID.equals(Constants.MODID))
            syncConfigData();
    }

    private void syncConfigData() {
        List<String> propOrder = new ArrayList<String>();
        Property prop;

        prop = config.get(CATEGORY_GENERAL, "Atum Portal", true);
        prop.comment = "Can a non-creative user create a portal using the scarab?";
        prop.setLanguageKey("atum.configGui.portalCreation");
        ALLOW_CREATION = prop.getBoolean(true);
        propOrder.add(prop.getName());

        prop = config.get(CATEGORY_GENERAL, "Atum Fog", true);
        prop.comment = "Should clientside fog be rendered?";
        prop.setLanguageKey("atum.configGui.fog");
        FOG_ENABLED = prop.getBoolean(true);
        propOrder.add(prop.getName());

        prop = config.get(CATEGORY_GENERAL, "Atum Dimension ID", 17);
        prop.comment = "The ID of the Atum Dimension";
        prop.setLanguageKey("atum.configGui.dimensionID").setRequiresMcRestart(true);
        DIMENSION_ID = prop.getInt();
        propOrder.add(prop.getName());

        ////////// biomes
        for(BiomeConfig biome : BiomeConfig.values()) {
            prop = config.get(CATEGORY_GENERAL, "Atum "+biome.toString()+" Biome ID", biome.getID());
            prop.comment = "The ID of the Atum Dimension biome " + biome.toString();
            prop.setLanguageKey("atum.configGui.biomeID."+biome.name()).setRequiresMcRestart(true);
            biome.setID(prop.getInt());
            propOrder.add(prop.getName());        	
        }

        ////////// features
        prop = config.get(CATEGORY_WORLDGEN, "Atum Oasis", true);
        prop.comment = "Should oases be generated?";
        prop.setLanguageKey("atum.configGui.oasis");
        OASIS_ENABLED = prop.getBoolean(true);
        propOrder.add(prop.getName());

        COAL_ENABLED = config.getBoolean("Generate Coal", CATEGORY_WORLDGEN + CATEGORY_SPLITTER + "Coal ore", true, "Should coal ore generate in Atum?");
        COAL_VEIN = config.getInt("Size", CATEGORY_WORLDGEN + CATEGORY_SPLITTER + "Coal ore", 16, 0, 64, "Coal vein size");

        IRON_ENABLED = config.getBoolean("Generate Iron", CATEGORY_WORLDGEN + CATEGORY_SPLITTER + "Iron ore", true, "Should iron ore generate in Atum?");
        IRON_VEIN = config.getInt("Size", CATEGORY_WORLDGEN + CATEGORY_SPLITTER + "Iron ore", 8, 0, 64, "Iron vein size");

        GOLD_ENABLED = config.getBoolean("Generate Gold", CATEGORY_WORLDGEN + CATEGORY_SPLITTER + "Gold ore", true, "Should gold ore generate in Atum?");
        GOLD_VEIN = config.getInt("Size", CATEGORY_WORLDGEN + CATEGORY_SPLITTER + "Gold ore", 8, 0, 64, "Gold vein size");

        REDSTONE_ENABLED = config.getBoolean("Generate Redstone", CATEGORY_WORLDGEN + CATEGORY_SPLITTER + "Redstone ore", true, "Should redstone ore generate in Atum?");
        REDSTONE_VEIN = config.getInt("Size", CATEGORY_WORLDGEN + CATEGORY_SPLITTER + "Redstone ore", 7, 0, 64, "Redstone vein size");

        DIAMOND_ENABLED = config.getBoolean("Generate Diamond", CATEGORY_WORLDGEN + CATEGORY_SPLITTER + "Diamond ore", true, "Should diamond ore generate in Atum?");
        DIAMOND_VEIN = config.getInt("Size", CATEGORY_WORLDGEN + CATEGORY_SPLITTER + "Diamond ore", 7, 0, 64, "Diamond vein size");

        LAPIS_ENABLED = config.getBoolean("Generate Lapis", CATEGORY_WORLDGEN + CATEGORY_SPLITTER + "Lapis ore", true, "Should lapis ore generate in Atum?");
        LAPIS_VEIN = config.getInt("Size", CATEGORY_WORLDGEN + CATEGORY_SPLITTER + "Lapis ore", 6, 0, 64, "Lapis vein size");

        config.setCategoryPropertyOrder(CATEGORY_GENERAL, propOrder);

        if (config.hasChanged()) {
            config.save();
        }
    }

}
