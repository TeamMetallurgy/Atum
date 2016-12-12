package com.teammetallurgy.atum;

import com.teammetallurgy.atum.blocks.AtumBlocks;
import com.teammetallurgy.atum.client.gui.AtumGuiHandler;
import com.teammetallurgy.atum.entity.AtumEntities;
import com.teammetallurgy.atum.handler.AtumConfig;
import com.teammetallurgy.atum.handler.AtumCreativeTab;
import com.teammetallurgy.atum.handler.CraftingHandler;
import com.teammetallurgy.atum.handler.event.AtumEventListener;
import com.teammetallurgy.atum.items.AtumItems;
import com.teammetallurgy.atum.items.AtumLoot;
import com.teammetallurgy.atum.proxy.CommonProxy;
import com.teammetallurgy.atum.utils.Constants;
import com.teammetallurgy.atum.world.AtumWorlds;
import com.teammetallurgy.atum.world.biome.AtumBiomes;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

@Mod(modid = Constants.MODID, name = Constants.MOD_NAME, version = Constants.VERSION, dependencies = Constants.DEPENDENCIES,guiFactory = Constants.FACTORY)
public class Atum {

    @Mod.Instance(Constants.MODID)
    public static Atum instance;

    @SidedProxy(clientSide = Constants.CLIENT, serverSide = Constants.SERVER)
    public static CommonProxy proxy;

    public static final CreativeTabs CREATIVE_TAB = new AtumCreativeTab();

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        Constants.LOG = event.getModLog();
        Constants.LOG.trace("Loading Configuration");
        new AtumConfig(event.getSuggestedConfigurationFile());

        Constants.LOG.trace("Register Item");
        AtumItems.registerItems();

        Constants.LOG.trace("Register Blocks");
        AtumBlocks.registerBlocks();

        Constants.LOG.trace("Renders Init");
        proxy.initRenders();

        MinecraftForge.EVENT_BUS.register(new AtumEventListener());
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        Constants.LOG.trace("Register Crafting Recipes");
        CraftingHandler.register();

        Constants.LOG.trace("Register Biomes");
        AtumBiomes.register();

        Constants.LOG.trace("Register World");
        AtumWorlds.register();

        Constants.LOG.trace("Register Entity");
        AtumEntities.register();

        Constants.LOG.trace("Register Loot");
        AtumLoot.register();

        Constants.LOG.trace("Proxy Init");
        proxy.init();

        NetworkRegistry.INSTANCE.registerGuiHandler(this, new AtumGuiHandler());
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    }
}