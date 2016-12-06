package com.teammetallurgy.atum.entity;

import com.teammetallurgy.atum.Atum;
import com.teammetallurgy.atum.entity.arrow.*;
import com.teammetallurgy.atum.entity.projectile.EntitySmallBone;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class AtumEntities {
    public static AtumEntity MUMMY;
    public static AtumEntity BRIGAND;
    public static AtumEntity NOMAD;
    public static AtumEntity PHARAOH;
    public static AtumEntity FORSAKEN;
    public static AtumEntity WRAITH;
    public static AtumEntity STONEGUARD;
    public static AtumEntity DESERT_WOLF;
    public static AtumEntity BANDIT_WARLORD;
    public static AtumEntity BARBARIAN;
    public static AtumEntity BONESTORM;

    public static void register() {
        // Mobs
        MUMMY = new AtumEntity(EntityMummy.class, "mummy", 0x515838, 0x868F6B);
        BRIGAND = new AtumEntity(EntityBrigand.class, "brigand", 0xC2C2C2, 0x040F85);
        NOMAD = new AtumEntity(EntityNomad.class, "nomad", 0xC2C2C2, 0x7E0C0C);
        PHARAOH = new AtumEntity(EntityPharaoh.class, "pharaoh", 0xD4BC37, 0x3A4BE0);
        FORSAKEN = new AtumEntity(EntityForsaken.class, "forsaken", 0xB59C7D, 0x6F5C43);
        WRAITH = new AtumEntity(EntityWraith.class, "wraith", 0xE7DBC8, 0xAD9467);
        STONEGUARD = new AtumEntity(EntityStoneguard.class, "stoneguard", 0x918354, 0x695D37);
        DESERT_WOLF = new AtumEntity(EntityDesertWolf.class, "desert_wolf", 0xE7DBC8, 0xAD9467);
        BANDIT_WARLORD = new AtumEntity(EntityBanditWarlord.class, "bandit_warlord", 0x918354, 0x695D37);
        BARBARIAN = new AtumEntity(EntityBarbarian.class, "barbarian", 0x918354, 0x695D37);
        BONESTORM = new AtumEntity(EntityBonestorm.class, "bonestorm", 0xFFFFFF, 0xFFFFFF);

        // Projectiles
        registerEntity("arrow_veloctiy", EntityArrowVelocity.class, true);
        registerEntity("arrow_explosive", EntityArrowExplosive.class, true);
        registerEntity("arrow_poison", EntityArrowPoison.class, true);
        registerEntity("arrow_fire", EntityArrowFire.class, true);
        registerEntity("arrow_double_shot", EntityArrowDoubleShot.class, true);
        registerEntity("arrow_quick_draw", EntityArrowQuickdraw.class, true);
        registerEntity("nuts_call", EntityNutsCall.class, true);
        //registerEntity("atum_fish_hook", EntityAtumFishHook.class, false); //TODO
        registerEntity("small_bone", EntitySmallBone.class, true);
    }

    private static void registerEntity(String registryName, Class<? extends Entity> entityClass, boolean sendsVelocityUpdates) {
        int entityID = 0;
        ResourceLocation resourceLocation = new ResourceLocation(registryName);
        EntityRegistry.registerModEntity(resourceLocation, entityClass, resourceLocation.toString(), entityID++, Atum.instance, 64, 1, sendsVelocityUpdates);
    }


    private static class AtumEntity {
        private AtumEntity(Class<? extends Entity> entityClass, ResourceLocation resourceLocation) {
            int entityID = 0;
            EntityRegistry.registerModEntity(resourceLocation, entityClass, resourceLocation.toString(), entityID++, Atum.instance, 64, 1, true);
        }

        private AtumEntity(Class<? extends Entity> entityClass, ResourceLocation resourceLocation, int eggPrimary, int eggSecondary) {
            this(entityClass, resourceLocation);
            EntityList.ENTITY_EGGS.put(resourceLocation, new EntityList.EntityEggInfo(resourceLocation, eggPrimary, eggSecondary));
        }

        private AtumEntity(Class<? extends Entity> entityClass, String entityName, int eggPrimary, int eggSecondary) {
            this(entityClass, new ResourceLocation(entityName), eggPrimary, eggSecondary);
        }
    }
}