package com.teammetallurgy.atum.proxy;

import com.teammetallurgy.atum.client.model.entity.ModelDesertWolf;
import com.teammetallurgy.atum.client.model.entity.ModelDustySkeleton;
import com.teammetallurgy.atum.client.render.entity.*;
import com.teammetallurgy.atum.client.render.entity.arrow.RenderBone;
import com.teammetallurgy.atum.client.render.entity.arrow.RenderCustomArrow;
import com.teammetallurgy.atum.client.render.entity.arrow.RenderNutsCall;
import com.teammetallurgy.atum.entity.*;
import com.teammetallurgy.atum.entity.arrow.CustomArrow;
import com.teammetallurgy.atum.entity.arrow.EntityAtumFishHook;
import com.teammetallurgy.atum.entity.arrow.EntityNutsCall;
import com.teammetallurgy.atum.entity.projectile.EntitySmallBone;
import com.teammetallurgy.atum.handler.AtumConfig;
import com.teammetallurgy.atum.handler.event.AtumFogEventListener;
import com.teammetallurgy.atum.handler.event.ClientEvents;
import com.teammetallurgy.atum.items.AtumItems;
import com.teammetallurgy.atum.utils.Constants;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderFish;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

import java.util.ArrayList;
import java.util.List;

public class ClientProxy extends CommonProxy {

    @Override
    public void init() {
        MinecraftForge.EVENT_BUS.register(new ClientEvents());

        MinecraftForge.EVENT_BUS.register(AtumItems.ANUBIS_MERCY);
        MinecraftForge.EVENT_BUS.register(AtumItems.GEBS_SOLIDARITY);
        MinecraftForge.EVENT_BUS.register(AtumItems.HORUS_FLIGHT);
        MinecraftForge.EVENT_BUS.register(AtumItems.MAATS_BALANCE);
        MinecraftForge.EVENT_BUS.register(AtumItems.MNEVIS_HORNS);
        MinecraftForge.EVENT_BUS.register(AtumItems.SEKHMETS_WRATH); //TODO Check if all these Items have a SubscribeEvent

        if (AtumConfig.FOG_ENABLED) {
            MinecraftForge.EVENT_BUS.register(new AtumFogEventListener());
        }
    }

    @Override
    public void initRenders() { //TODO Redo in 1.9
        /*MinecraftForgeClient.registerItemRenderer(AtumItems.BOW, new RendererItemBow());
        MinecraftForgeClient.registerItemRenderer(AtumItems.atensFury, new RendererItemBow());
        MinecraftForgeClient.registerItemRenderer(AtumItems.horusSoaring, new RendererItemBow());
        MinecraftForgeClient.registerItemRenderer(AtumItems.neithsAudacity, new RendererItemBow());
        MinecraftForgeClient.registerItemRenderer(AtumItems.shusBreath, new RendererItemBow());
        MinecraftForgeClient.registerItemRenderer(AtumItems.hedetetsVenom, new RendererItemBow());
        MinecraftForgeClient.registerItemRenderer(AtumItems.monthusBlast, new RendererItemBow());
        RenderingRegistry.registerBlockHandler(((BlockPapyrus) AtumBlocks.PAPYRUS).renderID, new RenderPapyrus());
        RenderingRegistry.registerBlockHandler(((BlockDate) AtumBlocks.DATEBLOCK).renderID, new RenderDate());*/

        RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();

        RenderingRegistry.registerEntityRenderingHandler(EntityMummy.class, new RenderLiving(renderManager, new ModelZombie(), 0.5F) {

            @Override
            protected ResourceLocation getEntityTexture(Entity entity) {
                return new ResourceLocation("atum", "textures/entities/Mummy.png");
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityBanditWarrior.class, new RenderBiped(renderManager, new ModelBiped(), 0.5F) {
            @Override
            protected ResourceLocation getEntityTexture(Entity entity) {
                return new ResourceLocation("atum", "textures/entities/BanditWarrior.png");
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityBarbarian.class, new RenderBiped(renderManager, new ModelBiped(), 0.5F) {
            @Override
            protected ResourceLocation getEntityTexture(Entity entity) {
                return new ResourceLocation("atum", "textures/entities/Barbarian.png");
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityBanditArcher.class, new RenderBandit(renderManager, new ModelBiped(), 0.5F) {
            @Override
            protected ResourceLocation getEntityTexture(Entity entity) {
                return new ResourceLocation("atum", "textures/entities/BanditArcher.png");
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityBanditWarlord.class, new RenderBiped(renderManager, new ModelBiped(), 0.5F) {
            @Override
            protected ResourceLocation getEntityTexture(Entity entity) {
                return new ResourceLocation("atum", "textures/entities/BanditWarlord.png");
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityPharaoh.class, new RenderPharaoh(renderManager, new ModelBiped(), 0.5F));

        RenderingRegistry.registerEntityRenderingHandler(EntityDustySkeleton.class, new RenderBiped(renderManager, new ModelDustySkeleton(), 0.5F) {
            @Override
            protected ResourceLocation getEntityTexture(Entity entity) {
                return new ResourceLocation("atum", "textures/entities/DustySkeleton.png");
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityGhost.class, new RenderGhost(renderManager, new ModelZombie(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(EntityStoneSoldier.class, new RenderBiped(renderManager, new ModelBiped(), 0.5F) {
            @Override
            protected ResourceLocation getEntityTexture(Entity entity) {
                return new ResourceLocation("atum", "textures/entities/StoneSoldier.png");
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityDesertWolf.class, new RenderDesertWolf(renderManager, new ModelDesertWolf(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(EntityBonestorm.class, new RenderBonestorm(renderManager));

        RenderingRegistry.registerEntityRenderingHandler(CustomArrow.class, new RenderCustomArrow(renderManager));
        RenderingRegistry.registerEntityRenderingHandler(EntitySmallBone.class, new RenderBone(renderManager, 0.35F));
        RenderingRegistry.registerEntityRenderingHandler(EntityNutsCall.class, new RenderNutsCall(renderManager));
        RenderingRegistry.registerEntityRenderingHandler(EntityAtumFishHook.class, new RenderFish(renderManager));
    }

    @Override
    public void registerItemVariantModel(Item item, String name, int metadata) {
        if (item != null) {
            //ModelBakery.registerItemVariants(item, new ResourceLocation(Constants.MODID + ":" + name));
            ModelLoader.setCustomModelResourceLocation(item, metadata, new ModelResourceLocation(Constants.MODID + ":" + name, "inventory"));
        }
    }

    @Override
    public void setItemResourceLocation(Item item, String name, CreativeTabs tab) {
        if (item.getHasSubtypes()) {
            List<ItemStack> subItems = new ArrayList<ItemStack>();
            item.getSubItems(item, tab, subItems);
            for (ItemStack stack : subItems) {
                String subItemName = item.getUnlocalizedName(stack).replace("item." + Constants.MODID + ":", "");

                ModelLoader.setCustomModelResourceLocation(item, stack.getItemDamage(), new ModelResourceLocation(Constants.MODID + ":" + subItemName, "inventory"));
            }
        } else {
            ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(Constants.MODID + ":" + name, "inventory"));
        }
    }
}