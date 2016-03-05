package com.teammetallurgy.atum.proxy;

import com.teammetallurgy.atum.client.model.entity.ModelDesertWolf;
import com.teammetallurgy.atum.client.model.entity.ModelDustySkeleton;
import com.teammetallurgy.atum.client.render.entity.RenderBonestorm;
import com.teammetallurgy.atum.client.render.entity.RenderDesertWolf;
import com.teammetallurgy.atum.client.render.entity.RenderGhost;
import com.teammetallurgy.atum.client.render.entity.RenderPharaoh;
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
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.IRenderFactory;
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

        RenderingRegistry.registerEntityRenderingHandler(EntityMummy.class, new IRenderFactory<EntityMummy>() {
            @Override
            public Render<? super EntityMummy> createRenderFor(RenderManager manager) {
                return new RenderLiving<EntityMummy>(manager, new ModelZombie(), 0.5F) {

                    @Override
                    protected ResourceLocation getEntityTexture(EntityMummy entity) {
                        return new ResourceLocation("atum", "textures/entities/Mummy.png");
                    }
                };
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityBanditWarrior.class, new IRenderFactory<EntityBanditWarrior>() {
            @Override
            public Render<? super EntityBanditWarrior> createRenderFor(RenderManager manager) {
                return new RenderBiped<EntityBanditWarrior>(manager, new ModelBiped(), 0.5F) {
                    @Override
                    protected ResourceLocation getEntityTexture(EntityBanditWarrior entity) {
                        return new ResourceLocation("atum", "textures/entities/BanditWarrior.png");
                    }
                };
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityBarbarian.class, new IRenderFactory<EntityBarbarian>() {
            @Override
            public Render<? super EntityBarbarian> createRenderFor(RenderManager manager) {
                return new RenderBiped<EntityBarbarian>(manager, new ModelBiped(), 0.5F) {
                    @Override
                    protected ResourceLocation getEntityTexture(EntityBarbarian entity) {
                        return new ResourceLocation("atum", "textures/entities/Barbarian.png");
                    }
                };
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityBanditArcher.class, new IRenderFactory<EntityBanditArcher>() {
            @Override
            public Render<? super EntityBanditArcher> createRenderFor(RenderManager manager) {
                return new RenderBiped<EntityBanditArcher>(manager, new ModelBiped(), 0.5F) {
                    @Override
                    protected ResourceLocation getEntityTexture(EntityBanditArcher entity) {
                        return new ResourceLocation("atum", "textures/entities/BanditArcher.png");
                    }
                };
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityBanditWarlord.class, new IRenderFactory<EntityBanditWarlord>() {
            @Override
            public Render<? super EntityBanditWarlord> createRenderFor(RenderManager manager) {
                return new RenderBiped<EntityBanditWarlord>(manager, new ModelBiped(), 0.5F) {
                    @Override
                    protected ResourceLocation getEntityTexture(EntityBanditWarlord entity) {
                        return new ResourceLocation("atum", "textures/entities/BanditWarlord.png");
                    }
                };
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityPharaoh.class, new IRenderFactory<EntityPharaoh>() {
            @Override
            public Render<? super EntityPharaoh> createRenderFor(RenderManager manager) {
                return new RenderPharaoh(manager, new ModelBiped(), 0.5F);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityDustySkeleton.class, new IRenderFactory<EntityDustySkeleton>() {
            @Override
            public Render<? super EntityDustySkeleton> createRenderFor(RenderManager manager) {
                return new RenderBiped<EntityDustySkeleton>(manager, new ModelDustySkeleton(), 0.5F) {
                    @Override
                    protected ResourceLocation getEntityTexture(EntityDustySkeleton entity) {
                        return new ResourceLocation("atum", "textures/entities/DustySkeleton.png");
                    }
                };
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityGhost.class, new IRenderFactory<EntityGhost>() {
            @Override
            public Render<? super EntityGhost> createRenderFor(RenderManager manager) {
                return new RenderGhost(manager, new ModelZombie(), 0.5F);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityStoneSoldier.class, new IRenderFactory<EntityStoneSoldier>() {
            @Override
            public Render<? super EntityStoneSoldier> createRenderFor(RenderManager manager) {
                return new RenderBiped<EntityStoneSoldier>(manager, new ModelBiped(), 0.5F) {
                    @Override
                    protected ResourceLocation getEntityTexture(EntityStoneSoldier entity) {
                        return new ResourceLocation("atum", "textures/entities/StoneSoldier.png");
                    }
                };
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityDesertWolf.class, new IRenderFactory<EntityDesertWolf>() {
            @Override
            public Render<? super EntityDesertWolf> createRenderFor(RenderManager manager) {
                return new RenderDesertWolf(manager, new ModelDesertWolf(), 0.5F);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityBonestorm.class, new IRenderFactory<EntityBonestorm>() {
            @Override
            public Render<? super EntityBonestorm> createRenderFor(RenderManager manager) {
                return new RenderBonestorm(manager);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(CustomArrow.class, new IRenderFactory<CustomArrow>() {
            @Override
            public Render<? super CustomArrow> createRenderFor(RenderManager manager) {
                return new RenderCustomArrow(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntitySmallBone.class, new IRenderFactory<EntitySmallBone>() {
            @Override
            public Render<? super EntitySmallBone> createRenderFor(RenderManager manager) {
                return new RenderBone(manager, 0.35F);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityNutsCall.class, new IRenderFactory<EntityNutsCall>() {
            @Override
            public Render<? super EntityNutsCall> createRenderFor(RenderManager manager) {
                return new RenderNutsCall(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityAtumFishHook.class, new IRenderFactory<EntityAtumFishHook>() {
            @Override
            public Render<? super EntityAtumFishHook> createRenderFor(RenderManager manager) {
                return new RenderFish(manager);
            }
        });
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