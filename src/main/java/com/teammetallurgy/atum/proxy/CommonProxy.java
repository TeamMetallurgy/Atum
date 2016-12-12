package com.teammetallurgy.atum.proxy;

import com.teammetallurgy.atum.handler.event.ServerEvents;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;

public class CommonProxy {

    public void init() {
        MinecraftForge.EVENT_BUS.register(new ServerEvents());
    }

    public void initRenders() {
    }

    public void setBlockResourceLocation(Item item, String name, CreativeTabs tab) {
    }

    public void setItemResourceLocation(Item item, String name, CreativeTabs tab) {
    }
}