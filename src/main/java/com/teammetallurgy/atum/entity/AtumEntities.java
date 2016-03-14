package com.teammetallurgy.atum.entity;

import com.teammetallurgy.atum.Atum;
import com.teammetallurgy.atum.entity.arrow.*;
import com.teammetallurgy.atum.entity.projectile.EntitySmallBone;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class AtumEntities {
    public AtumEntity mummy;
    public AtumEntity banditWarrior;
    public AtumEntity banditArcher;
    public AtumEntity pharaoh;
    public AtumEntity dustySkeleton;
    public AtumEntity ghost;
    public AtumEntity stoneSoldier;
    public AtumEntity desertWolf;
    public AtumEntity banditWarlord;
    public AtumEntity barbarian;
    public AtumEntity bonestorm;

    public void register() {
        int entityID = 0;

        // Mobs
        mummy = new AtumEntity(EntityMummy.class, "mummy", entityID++, 64, 1).setSpawnEgg(0x515838, 0x868F6B);
        banditWarrior = new AtumEntity(EntityBanditWarrior.class, "banditWarrior", entityID++, 64, 1).setSpawnEgg(0xC2C2C2, 0x040F85);
        banditArcher = new AtumEntity(EntityBanditArcher.class, "banditArcher", entityID++, 64, 1).setSpawnEgg(0xC2C2C2, 0x7E0C0C);
        pharaoh = new AtumEntity(EntityPharaoh.class, "pharaoh", entityID++, 64, 1).setSpawnEgg(0xD4BC37, 0x3A4BE0);
        dustySkeleton = new AtumEntity(EntityDustySkeleton.class, "dustySkeleton", entityID++, 64, 1).setSpawnEgg(0xB59C7D, 0x6F5C43);
        ghost = new AtumEntity(EntityGhost.class, "desertGhost", entityID++, 64, 1).setSpawnEgg(0xE7DBC8, 0xAD9467);
        stoneSoldier = new AtumEntity(EntityStoneSoldier.class, "stoneSoldier", entityID++, 64, 1).setSpawnEgg(0x918354, 0x695D37);
        desertWolf = new AtumEntity(EntityDesertWolf.class, "desertWolf", entityID++, 64, 1).setSpawnEgg(0xE7DBC8, 0xAD9467);
        banditWarlord = new AtumEntity(EntityBanditWarlord.class, "banditWarlord", entityID++, 64, 1).setSpawnEgg(0x918354, 0x695D37);
        barbarian = new AtumEntity(EntityBarbarian.class, "barbarian", entityID++, 64, 1).setSpawnEgg(0x918354, 0x695D37);
        bonestorm = new AtumEntity(EntityBonestorm.class, "bonestorm", entityID++, 64, 1).setSpawnEgg(0xFFFFFF, 0xFFFFFF);

        // Projectiles
        EntityRegistry.registerModEntity(EntityArrowVelocity.class, "ArrowVeloctiy", entityID++, Atum.instance, 64, 1, true);
        EntityRegistry.registerModEntity(EntityArrowExplosive.class, "ArrowExplosive", entityID++, Atum.instance, 64, 1, true);
        EntityRegistry.registerModEntity(EntityArrowPoison.class, "ArrowPoison", entityID++, Atum.instance, 64, 1, true);
        EntityRegistry.registerModEntity(EntityArrowFire.class, "ArrowFire", entityID++, Atum.instance, 64, 1, true);
        EntityRegistry.registerModEntity(EntityArrowDoubleShot.class, "ArrowDoubleShot", entityID++, Atum.instance, 64, 1, true);
        EntityRegistry.registerModEntity(EntityArrowQuickdraw.class, "ArrowQuickDraw", entityID++, Atum.instance, 64, 1, true);
        EntityRegistry.registerModEntity(EntityNutsCall.class, "EntityNutsCall", entityID++, Atum.instance, 64, 1, true);
        EntityRegistry.registerModEntity(EntityAtumFishHook.class, "EntityAtumFishHook", entityID++, Atum.instance, 64, 1, false);
        EntityRegistry.registerModEntity(EntitySmallBone.class, "SmallBone", entityID++, Atum.instance, 64, 1, true);
    }

    public class AtumEntity {
        private Class<? extends Entity> entityClass;

        public AtumEntity(Class<? extends Entity> entityClass, String entityName, int id, int trackingRange, int updateFrequency) {
            this.entityClass = entityClass;
            EntityRegistry.registerModEntity(entityClass, entityName, id, Atum.instance, trackingRange, updateFrequency, true);
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