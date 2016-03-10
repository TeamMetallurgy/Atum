package com.teammetallurgy.atum.client.gui;

import com.teammetallurgy.atum.handler.AtumConfig;
import com.teammetallurgy.atum.utils.Constants;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;

import java.util.ArrayList;
import java.util.List;

import static net.minecraftforge.common.config.Configuration.CATEGORY_GENERAL;

public class AtumConfigGui extends GuiConfig {
    static Configuration cfg = AtumConfig.config;

    public AtumConfigGui(GuiScreen parent) {
        super(parent, generateConfigList(), Constants.MODID, false, false, GuiConfig.getAbridgedConfigPath(AtumConfig.config.toString()));
    }

    public static List<IConfigElement> generateConfigList() {
        ArrayList<IConfigElement> elements = new ArrayList<IConfigElement>();

        elements.add(new ConfigElement(cfg.getCategory(CATEGORY_GENERAL)));
        elements.add(new ConfigElement(cfg.getCategory(AtumConfig.CATEGORY_WORLDGEN)));
        return elements;
    }
}