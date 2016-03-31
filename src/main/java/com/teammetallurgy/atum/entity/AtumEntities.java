package com.teammetallurgy.atum.entity;

import com.teammetallurgy.atum.Atum;
import com.teammetallurgy.atum.entity.arrow.*;
import com.teammetallurgy.atum.entity.projectile.EntitySmallBone;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class AtumEntities {
    public AtumEntity mummy;
    public AtumEntity brigand;
    public AtumEntity nomad;
    public AtumEntity pharaoh;
    public AtumEntity forsaken;
    public AtumEntity wraith;
    public AtumEntity stoneguard;
    public AtumEntity desertWolf;
    public AtumEntity banditWarlord;
    public AtumEntity barbarian;
    public AtumEntity bonestorm;

    public void register() {
        int entityID = 0;

        // Mobs
        mummy = new AtumEntity(EntityMummy.class, "mummy", 0x515838, 0x868F6B);
        brigand = new AtumEntity(EntityBrigand.class, "brigand", 0xC2C2C2, 0x040F85);
        nomad = new AtumEntity(EntityNomad.class, "nomad", 0xC2C2C2, 0x7E0C0C);
        pharaoh = new AtumEntity(EntityPharaoh.class, "pharaoh", 0xD4BC37, 0x3A4BE0);
        forsaken = new AtumEntity(EntityForsaken.class, "forsaken", 0xB59C7D, 0x6F5C43);
        wraith = new AtumEntity(EntityWraith.class, "wraith", 0xE7DBC8, 0xAD9467);
        stoneguard = new AtumEntity(EntityStoneguard.class, "stoneguard", 0x918354, 0x695D37);
        desertWolf = new AtumEntity(EntityDesertWolf.class, "desertWolf", 0xE7DBC8, 0xAD9467);
        banditWarlord = new AtumEntity(EntityBanditWarlord.class, "banditWarlord", 0x918354, 0x695D37);
        barbarian = new AtumEntity(EntityBarbarian.class, "barbarian", 0x918354, 0x695D37);
        bonestorm = new AtumEntity(EntityBonestorm.class, "bonestorm", 0xFFFFFF, 0xFFFFFF);

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

    private void registerEntity(Class<? extends Entity> entityClass, String entityName, boolean sendsVelocityUpdates) {
        int entityID = 0;
        EntityRegistry.registerModEntity(entityClass, entityName, entityID++, Atum.instance, 64, 1, sendsVelocityUpdates);
    }

    private class AtumEntity {
        private Class<? extends Entity> entityClass;

        private AtumEntity(Class<? extends Entity> entityClass, String entityName) {
            this.entityClass = entityClass;
            int entityID = 0;
            EntityRegistry.registerModEntity(entityClass, entityName, entityID++, Atum.instance, 64, 1, true);
        }

        private AtumEntity(Class<? extends Entity> entityClass, String entityName, int baseColor, int spotColor) {
            this(entityClass, entityName);
            EntityList.entityEggs.put(entityName, new EntityList.EntityEggInfo(entityName, baseColor, spotColor));
        }
    }
}