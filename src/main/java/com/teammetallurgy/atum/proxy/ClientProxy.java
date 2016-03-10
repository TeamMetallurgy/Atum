package com.teammetallurgy.atum.proxy;

import com.teammetallurgy.atum.blocks.BlockAtumPlank;
import com.teammetallurgy.atum.blocks.BlockLimestoneBricks;
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
import com.teammetallurgy.atum.items.itemblock.ItemBlockLimestoneBricks;
import com.teammetallurgy.atum.items.itemblock.ItemBlockPlanks;
import com.teammetallurgy.atum.items.itemblock.ItemBlockWoodSlabs;
import com.teammetallurgy.atum.utils.AtumUtils;
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
        RenderingRegistry.registerEntityRenderingHandler(EntityBanditWarrior.class, new IRenderFactory<EntityBanditWarrior>() {
            @Override
            public Render<? super EntityBanditWarrior> createRenderFor(RenderManager manager) {
                return new RenderBiped<EntityBanditWarrior>(manager, new ModelBiped(), 0.5F) {
                    @Override
                    protected ResourceLocation getEntityTexture(EntityBanditWarrior entity) {
                        return new ResourceLocation("atum", "textures/entities/bandit_warrior.png");
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
        RenderingRegistry.registerEntityRenderingHandler(EntityBanditArcher.class, new IRenderFactory<EntityBanditArcher>() {
            @Override
            public Render<? super EntityBanditArcher> createRenderFor(RenderManager manager) {
                return new RenderBiped<EntityBanditArcher>(manager, new ModelBiped(), 0.5F) {
                    @Override
                    protected ResourceLocation getEntityTexture(EntityBanditArcher entity) {
                        return new ResourceLocation("atum", "textures/entities/bandit_archer.png");
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

        RenderingRegistry.registerEntityRenderingHandler(EntityDustySkeleton.class, new IRenderFactory<EntityDustySkeleton>() {
            @Override
            public Render<? super EntityDustySkeleton> createRenderFor(RenderManager manager) {
                return new RenderBiped<EntityDustySkeleton>(manager, new ModelDustySkeleton(), 0.5F) {
                    @Override
                    protected ResourceLocation getEntityTexture(EntityDustySkeleton entity) {
                        return new ResourceLocation("atum", "textures/entities/dusty_skeleton.png");
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
                        return new ResourceLocation("atum", "textures/entities/stone_soldier.png");
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
    public void registerItemVariantModel(Item item, String name) { //TODO Make a proper solution
        if (item != null) {
            if (item instanceof ItemBlockPlanks || item instanceof ItemBlockWoodSlabs) {
                for (BlockAtumPlank.EnumType enumType : BlockAtumPlank.EnumType.values()) {
                    String plankName = BlockAtumPlank.EnumType.byMetadata(enumType.getMetadata()) + "_" + name;
                    ModelLoader.setCustomModelResourceLocation(item, enumType.getMetadata(), new ModelResourceLocation(Constants.MODID + ":" + plankName, "inventory"));
                    System.out.println("VariantPlank: " + plankName);
                }
            } else if (item instanceof ItemBlockLimestoneBricks) {
                for (BlockLimestoneBricks.EnumType enumType : BlockLimestoneBricks.EnumType.values()) {
                    String limestonebrickName = name + "_" + BlockLimestoneBricks.EnumType.byMetadata(enumType.getMetadata());
                    ModelLoader.setCustomModelResourceLocation(item, enumType.getMetadata(), new ModelResourceLocation(Constants.MODID + ":" + limestonebrickName, "inventory"));
                    System.out.println("VariantBrick: " + limestonebrickName);
                }
            } else {
                ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(Constants.MODID + ":" + name, "inventory"));
                System.out.print(" - " + name);
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
                System.out.println("SubItemName: " + AtumUtils.toRegistryName(subItemName));
            }
        } else {
            ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(Constants.MODID + ":" + name, "inventory"));
        }
    }
}