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
import com.teammetallurgy.atum.utils.AtumUtils;
import com.teammetallurgy.atum.utils.Constants;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemFishingRod;
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
        MinecraftForge.EVENT_BUS.register(AtumItems.HORUS_FLIGHT);
        MinecraftForge.EVENT_BUS.register(AtumItems.MAATS_BALANCE);
        MinecraftForge.EVENT_BUS.register(AtumItems.MNEVIS_HORNS);
        MinecraftForge.EVENT_BUS.register(AtumItems.SEKHMETS_WRATH);

        if (AtumConfig.FOG_ENABLED) {
            MinecraftForge.EVENT_BUS.register(new AtumFogEventListener());
        }
    }

    @Override
    public void initRenders() { //TODO Redo in 1.9
        RenderingRegistry.registerEntityRenderingHandler(EntityMummy.class, new IRenderFactory<EntityMummy>() {
            @Override
            public Render<? super EntityMummy> createRenderFor(RenderManager manager) {
                return new RenderLiving<EntityMummy>(manager, new ModelZombie(), 0.5F) {

                    @Override
                    protected ResourceLocation getEntityTexture(EntityMummy entity) {
                        return new ResourceLocation("atum", "textures/entities/mummy.png");
                    }
                };
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityBrigand.class, new IRenderFactory<EntityBrigand>() {
            @Override
            public Render<? super EntityBrigand> createRenderFor(RenderManager manager) {
                return new RenderBiped<EntityBrigand>(manager, new ModelBiped(), 0.5F) {
                    @Override
                    protected ResourceLocation getEntityTexture(EntityBrigand entity) {
                        return new ResourceLocation("atum", "textures/entities/brigand.png");
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
                        return new ResourceLocation("atum", "textures/entities/barbarian.png");
                    }
                };
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityNomad.class, new IRenderFactory<EntityNomad>() {
            @Override
            public Render<? super EntityNomad> createRenderFor(RenderManager manager) {
                return new RenderBiped<EntityNomad>(manager, new ModelBiped(), 0.5F) {
                    @Override
                    protected ResourceLocation getEntityTexture(EntityNomad entity) {
                        return new ResourceLocation("atum", "textures/entities/nomad.png");
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
                        return new ResourceLocation("atum", "textures/entities/bandit_warlord.png");
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

        RenderingRegistry.registerEntityRenderingHandler(EntityForsaken.class, new IRenderFactory<EntityForsaken>() {
            @Override
            public Render<? super EntityForsaken> createRenderFor(RenderManager manager) {
                return new RenderBiped<EntityForsaken>(manager, new ModelDustySkeleton(), 0.5F) {
                    @Override
                    protected ResourceLocation getEntityTexture(EntityForsaken entity) {
                        return new ResourceLocation("atum", "textures/entities/forsaken.png");
                    }
                };
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityWraith.class, new IRenderFactory<EntityWraith>() {
            @Override
            public Render<? super EntityWraith> createRenderFor(RenderManager manager) {
                return new RenderGhost(manager, new ModelZombie(), 0.5F);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityStoneguard.class, new IRenderFactory<EntityStoneguard>() {
            @Override
            public Render<? super EntityStoneguard> createRenderFor(RenderManager manager) {
                return new RenderBiped<EntityStoneguard>(manager, new ModelBiped(), 0.5F) {
                    @Override
                    protected ResourceLocation getEntityTexture(EntityStoneguard entity) {
                        return new ResourceLocation("atum", "textures/entities/stoneguard.png");
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
    public void setBlockResourceLocation(Item item, String name, CreativeTabs tab) {
        if (item != null) {
            if (item.getHasSubtypes()) {
                List<ItemStack> subBlocks = new ArrayList<ItemStack>();
                item.getSubItems(item, tab, subBlocks);
                for (ItemStack stack : subBlocks) {
                    String subBlockName = item.getUnlocalizedName(stack).replace("tile." + Constants.MODID + ".", "").replace(".", "_");

                    ModelLoader.setCustomModelResourceLocation(item, stack.getItemDamage(), new ModelResourceLocation(Constants.MODID + ":" + AtumUtils.toRegistryName(subBlockName), "inventory"));
                    System.out.println("SubBlockName: " + AtumUtils.toRegistryName(subBlockName));
                }
            } else {
                ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(Constants.MODID + ":" + name, "inventory"));
                System.out.println("BlockName: " + name);
            }
        }
    }

    @Override
    public void setItemResourceLocation(Item item, String name, CreativeTabs tab) {
        if (item.getHasSubtypes()) {
            List<ItemStack> subItems = new ArrayList<ItemStack>();
            item.getSubItems(item, tab, subItems);
            for (ItemStack stack : subItems) {
                String subItemName = item.getUnlocalizedName(stack).replace("item." + Constants.MODID + ".", "").replace(".", "_");

                ModelLoader.setCustomModelResourceLocation(item, stack.getItemDamage(), new ModelResourceLocation(Constants.MODID + ":" + AtumUtils.toRegistryName(subItemName), "inventory"));
            }
        } else if (item instanceof ItemBow) {
            ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(Constants.MODID + ":" + name, "inventory"));
            for (int i = 1; i <= 3; i++) {
                ModelLoader.setCustomModelResourceLocation(item, i, new ModelResourceLocation(Constants.MODID + ":" + name + "_pulling_" + (i - 1), "inventory"));

                System.out.println("Damage: " + i + " " + "BowName: " + AtumUtils.toRegistryName(name + "_pulling_" + (i - 1)));
            }
        } else if (item instanceof ItemFishingRod) {
            ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(Constants.MODID + ":" + name, "inventory"));
            ModelLoader.setCustomModelResourceLocation(item, 1, new ModelResourceLocation(Constants.MODID + ":" + name + "_cast", "inventory"));
        } else {
            ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(Constants.MODID + ":" + name, "inventory"));
        }
    }
}