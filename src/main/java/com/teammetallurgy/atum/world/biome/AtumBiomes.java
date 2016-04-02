package com.teammetallurgy.atum.world.biome;

import com.google.common.collect.Lists;
import com.teammetallurgy.atum.world.biome.AtumBiomeGenBase.AtumBiomeProperties;

import java.util.List;

public class AtumBiomes {
    static List<BiomeType> biomeRegistry = Lists.newArrayList();
    static final int DEFAULT_BIOME_WEIGHT = 20;

    public static void register() { //TODO Make it register depending on the biometype amount. Similiar to enum types for items in Culinary cultivation
        registerBiome(BiomeType.SAND_PLAINS);
        registerBiome(BiomeType.SAND_DUNES);
        registerBiome(BiomeType.SAND_HILLS);
        registerBiome(BiomeType.LIMESTONE_MOUNTAINS);
        registerBiome(BiomeType.LIMESTONE_CRAGS);
        // TODO: Disabled until fixed
        // registerBiome(BiomeType.OASIS);
        // registerBiome(BiomeType.DEAD_OASIS);
        registerBiome(BiomeType.DRIED_RIVER);
        registerBiome(BiomeType.RUINED_CITY);
    }

    // in case we want to do anything extra at registration time...
    private static void registerBiome(BiomeType biomeType) {
        biomeRegistry.add(biomeType);
    }

    public enum BiomeType {
        SAND_PLAINS(0, "Sand Plains", 2.0F, new BiomeGenSandPlains(new AtumBiomeProperties("Sand Plains").setBaseHeight(0.125F * 0.8F).setHeightVariation(0.05F * 0.6F))),
        SAND_DUNES(1, "Sand Dunes", 1.0F, new BiomeGenSandDunes(new AtumBiomeProperties("Sand Dunes").setBaseHeight(0.225F).setHeightVariation(0.25F))),
        SAND_HILLS(2, "Sand Hills", 0.75F, new BiomeGenSandHills(new AtumBiomeProperties("Sand Hills").setBaseHeight(0.45F).setHeightVariation(0.3F))),
        LIMESTONE_MOUNTAINS(3, "Limestone Mountains", 0.75F, new BiomeGenLimestoneMountains(new AtumBiomeProperties("Limestone Mountains").setBaseHeight(1.0F).setHeightVariation(0.5F))),
        LIMESTONE_CRAGS(4, "Limestone Crags", 0.5F, new BiomeGenLimestoneCrags(new AtumBiomeProperties("Limestone Crags").setBaseHeight(0.425F).setHeightVariation(0.45000002F))),
        OASIS(5, "Oasis", 0.25F, new BiomeGenOasis(new AtumBiomeProperties("Oasis").setBaseHeight(-0.5F).setHeightVariation(0.0F))),
        DEAD_OASIS(6, "Dead Oasis", 0.25F, new BiomeGenDeadOasis(new AtumBiomeProperties("Dead Oasis").setBaseHeight(-0.5F).setHeightVariation(0.0F))),
        DRIED_RIVER(7, "Dried River", -1F, new BiomeGenDriedRiver(new AtumBiomeProperties("Dried River").setBaseHeight(0.1F).setHeightVariation(0.8F))),
        RUINED_CITY(8, "Ruined City", 0.5F, new BiomeGenRuinedCity(new AtumBiomeProperties("Ruined City").setBaseHeight(0.125F).setHeightVariation(0.05F)));

        private static final BiomeType[] META_LOOKUP = new BiomeType[values().length];
        private int id;
        private final String friendlyName;
        private AtumBiomeGenBase atumBiomeGenBase;
        private int weight;

        private BiomeType(int defaultID, String friendlyName, float weightMultiplier, AtumBiomeGenBase atumBiomeGenBase) {
            this.id = defaultID;
            this.friendlyName = friendlyName;
            this.atumBiomeGenBase = atumBiomeGenBase;
            this.weight = (int) (DEFAULT_BIOME_WEIGHT * weightMultiplier);
        }

        public void setID(int id) {
            this.id = id;
        }

        public int getID() {
            return this.id;
        }

        public void setGen(AtumBiomeGenBase gen) {
            this.atumBiomeGenBase = gen;
        }

        public AtumBiomeGenBase getGen() {
            return this.atumBiomeGenBase;
        }

        @Override
        public String toString() {
            return friendlyName;
        }

        public int getWeight() {
            return weight;
        }

        public static BiomeType byID(int id) {
            if (id < 0 || id >= META_LOOKUP.length) {
                id = 0;
            }
            return META_LOOKUP[id];
        }

        static {
            for (BiomeType biomeType : values()) {
                META_LOOKUP[biomeType.getID()] = biomeType;
            }
        }
    }
}