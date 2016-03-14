package com.teammetallurgy.atum.entity;

import com.teammetallurgy.atum.Atum;
import com.teammetallurgy.atum.entity.arrow.*;
import com.teammetallurgy.atum.entity.projectile.EntitySmallBone;
import com.teammetallurgy.atum.utils.Constants;
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
        mummy = new AtumEntity(EntityMummy.class, "mummy", entityID++).setSpawnEgg(0x515838, 0x868F6B);
        brigand = new AtumEntity(EntityBrigand.class, "brigand", entityID++).setSpawnEgg(0xC2C2C2, 0x040F85);
        nomad = new AtumEntity(EntityNomad.class, "nomad", entityID++).setSpawnEgg(0xC2C2C2, 0x7E0C0C);
        pharaoh = new AtumEntity(EntityPharaoh.class, "pharaoh", entityID++).setSpawnEgg(0xD4BC37, 0x3A4BE0);
        forsaken = new AtumEntity(EntityForsaken.class, "forsaken", entityID++).setSpawnEgg(0xB59C7D, 0x6F5C43);
        wraith = new AtumEntity(EntityWraith.class, "wraith", entityID++).setSpawnEgg(0xE7DBC8, 0xAD9467);
        stoneguard = new AtumEntity(EntityStoneguard.class, "stoneguard", entityID++).setSpawnEgg(0x918354, 0x695D37);
        desertWolf = new AtumEntity(EntityDesertWolf.class, "desertWolf", entityID++).setSpawnEgg(0xE7DBC8, 0xAD9467);
        banditWarlord = new AtumEntity(EntityBanditWarlord.class, "banditWarlord", entityID++).setSpawnEgg(0x918354, 0x695D37);
        barbarian = new AtumEntity(EntityBarbarian.class, "barbarian", entityID++).setSpawnEgg(0x918354, 0x695D37);
        bonestorm = new AtumEntity(EntityBonestorm.class, "bonestorm", entityID++).setSpawnEgg(0xFFFFFF, 0xFFFFFF);

        // Projectiles
        registerEntity(EntityArrowVelocity.class, "arrowVeloctiy", entityID++, true);
        registerEntity(EntityArrowExplosive.class, "arrowExplosive", entityID++, true);
        registerEntity(EntityArrowPoison.class, "arrowPoison", entityID++, true);
        registerEntity(EntityArrowFire.class, "arrowFire", entityID++, true);
        registerEntity(EntityArrowDoubleShot.class, "arrowDoubleShot", entityID++, true);
        registerEntity(EntityArrowQuickdraw.class, "arrowQuickDraw", entityID++, true);
        registerEntity(EntityNutsCall.class, "nutsCall", entityID++, true);
        registerEntity(EntityAtumFishHook.class, "atumFishHook", entityID++, false);
        registerEntity(EntitySmallBone.class, "smallBone", entityID++, true);
    }

    public void registerEntity(Class<? extends Entity> entityClass, String entityName, int id, boolean sendsVelocityUpdates) {
        EntityRegistry.registerModEntity(entityClass, entityName, id, Atum.instance, 64, 1, sendsVelocityUpdates);
    }

    public class AtumEntity {
        private Class<? extends Entity> entityClass;

        public AtumEntity(Class<? extends Entity> entityClass, String entityName, int id) {
            this.entityClass = entityClass;
            EntityRegistry.registerModEntity(entityClass, entityName, id, Atum.instance, 64, 1, true);
        }

        public AtumEntity setSpawnEgg(int backgroundEggColour, int foregroundEggColour) {
            int eggID = getUniqueEggId();
            EntityList.idToClassMapping.put(eggID, entityClass);
            EntityList.entityEggs.put(eggID, new EntityList.EntityEggInfo(eggID, backgroundEggColour, foregroundEggColour));
            return this;
        }

        private int getUniqueEggId() {
            int eggID = 120;
            do {
                ++eggID;
            } while (EntityList.getStringFromID(eggID) != null);
            return eggID;
        }
    }
}