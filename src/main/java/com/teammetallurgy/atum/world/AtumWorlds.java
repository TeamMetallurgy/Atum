package com.teammetallurgy.atum.world;

import com.teammetallurgy.atum.handler.AtumConfig;
import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;

public class AtumWorlds {
    public static DimensionType ATUM;

    public static void register() {
        ATUM = DimensionType.register("Atum", "_atum", AtumConfig.DIMENSION_ID, WorldProviderAtum.class, true);
        DimensionManager.registerDimension(AtumConfig.DIMENSION_ID, ATUM);
    }
}