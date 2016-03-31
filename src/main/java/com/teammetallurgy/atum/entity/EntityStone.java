package com.teammetallurgy.atum.entity;

import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityStone extends EntityMob {

    public EntityStone(World world) {
        super(world);
    }

    @Override
    public EnumCreatureAttribute getCreatureAttribute() {
        return EnumCreatureAttribute.UNDEFINED;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.block_stone_place;
    } //TODO Check how the sounds is

    @Override
    protected SoundEvent getHurtSound() {
        return SoundEvents.block_stone_hit;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.block_stone_break;
    }

    @Override
    protected SoundEvent getFallSound(int height) {
        return height > 4 ? SoundEvents.entity_hostile_big_fall : SoundEvents.block_stone_fall;
    }

    @Override
    protected void playStepSound(BlockPos pos, Block block) {
        this.playSound(SoundEvents.block_stone_step, 0.15F, 1.0F);
    }

    @Override
    public boolean getCanSpawnHere() {
        int i = MathHelper.floor_double(this.getEntityBoundingBox().minY);
        return i <= 62 && super.getCanSpawnHere();
    }

    @Override
    public boolean isPotionApplicable(PotionEffect potioneffect) {
        return potioneffect.getPotion() != MobEffects.poison;
    }
}