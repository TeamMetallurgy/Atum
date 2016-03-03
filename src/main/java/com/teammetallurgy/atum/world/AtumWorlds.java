package com.teammetallurgy.atum.world;

import com.teammetallurgy.atum.handler.AtumConfig;
import net.minecraftforge.common.DimensionManager;

public class AtumWorlds {
    public void register() {
        int atumID = AtumConfig.DIMENSION_ID;

        DimensionManager.registerProviderType(atumID, AtumWorldProvider.class, true);
        DimensionManager.registerDimension(atumID, atumID);
    }
}