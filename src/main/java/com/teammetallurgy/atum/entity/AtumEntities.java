package com.teammetallurgy.atum.entity;

import com.teammetallurgy.atum.Atum;
import com.teammetallurgy.atum.entity.arrow.*;
import com.teammetallurgy.atum.entity.projectile.EntitySmallBone;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
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
        DESERT_WOLF = new AtumEntity(EntityDesertWolf.class, "desertWolf", 0xE7DBC8, 0xAD9467);
        BANDIT_WARLORD = new AtumEntity(EntityBanditWarlord.class, "banditWarlord", 0x918354, 0x695D37);
        BARBARIAN = new AtumEntity(EntityBarbarian.class, "barbarian", 0x918354, 0x695D37);
        BONESTORM = new AtumEntity(EntityBonestorm.class, "bonestorm", 0xFFFFFF, 0xFFFFFF);

        // Projectiles
        registerEntity(EntityArrowVelocity.class, "arrowVeloctiy", true);
        registerEntity(EntityArrowExplosive.class, "arrowExplosive", true);
        registerEntity(EntityArrowPoison.class, "arrowPoison", true);
        registerEntity(EntityArrowFire.class, "arrowFire", true);
        registerEntity(EntityArrowDoubleShot.class, "arrowDoubleShot", true);
        registerEntity(EntityArrowQuickdraw.class, "arrowQuickDraw", true);
        registerEntity(EntityNutsCall.class, "nutsCall", true);
        registerEntity(EntityAtumFishHook.class, "atumFishHook", false);
        registerEntity(EntitySmallBone.class, "smallBone", true);
    }

    private static void registerEntity(Class<? extends Entity> entityClass, String entityName, boolean sendsVelocityUpdates) {
        int entityID = 0;
        EntityRegistry.registerModEntity(entityClass, entityName, entityID++, Atum.instance, 64, 1, sendsVelocityUpdates);
    }

    private static class AtumEntity {

        private AtumEntity(Class<? extends Entity> entityClass, String entityName) {
            int entityID = 0;
            EntityRegistry.registerModEntity(entityClass, entityName, entityID++, Atum.instance, 64, 1, true);
        }

        private AtumEntity(Class<? extends Entity> entityClass, String entityName, int baseColor, int spotColor) {
            this(entityClass, entityName);
            EntityList.entityEggs.put(entityName, new EntityList.EntityEggInfo(entityName, baseColor, spotColor));
        }
    }
}