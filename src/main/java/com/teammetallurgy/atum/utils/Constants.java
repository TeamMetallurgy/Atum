package com.teammetallurgy.atum.utils;

import org.apache.logging.log4j.Logger;

public class Constants {

    public static final String MODID = "atum";
    public static final String MOD_NAME = "Atum";
    public static final String VERSION = "0.6";
    public static final String CLIENT = "com.teammetallurgy.atum.proxy.ClientProxy";
    public static final String SERVER = "com.teammetallurgy.atum.proxy.CommonProxy";
    public static final String FACTORY = "com.teammetallurgy.atum.client.gui.AtumGuiFactory";
    public static final String DEPENDENCIES = "required-after:forge@[13.19.1,);";

    public static Logger LOG;

    public static final String[] ORE_DIC_COLOURS = {"Black", "Red", "Green", "Brown", "Blue", "Purple", "Cyan", "LightGray", "Gray", "Pink", "Lime", "Yellow", "LightBlue", "Magenta", "Orange", "White"};
}