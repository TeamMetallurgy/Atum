package com.teammetallurgy.atum.handler.event;

import com.teammetallurgy.atum.blocks.AtumBlocks;
import com.teammetallurgy.atum.blocks.BlockPalmSapling;
import com.teammetallurgy.atum.entity.*;
import com.teammetallurgy.atum.handler.AtumConfig;
import com.teammetallurgy.atum.items.AtumItems;
import net.minecraft.block.Block;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.AchievementList;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.UseHoeEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Random;

public class AtumEventListener {

    @SubscribeEvent
    public void onFallDamage(LivingFallEvent event) {
        if (event.entity instanceof EntityGhost || event.entity instanceof EntityPharaoh) {
            event.distance = 0.0F;
        }

    }

    @SubscribeEvent
    public void onLivingHurt(LivingHurtEvent event) {
        if (event.source.getDamageType().equals("drown") && (event.entity instanceof EntityPharaoh || event.entity instanceof EntityGhost || event.entity instanceof EntityMummy || event.entity instanceof EntityDustySkeleton || event.entity instanceof EntityStoneSoldier)) {
            event.setCanceled(true);
        }

    }

    @SubscribeEvent
    public boolean onBonemeal(BonemealEvent event) {
        if (!event.world.isRemote) {

            Block block = event.world.getBlockState(event.pos).getBlock();
            if (block == AtumBlocks.PALMSAPLING) {
                if (event.world.rand.nextInt(7) == 0) {
                    ((BlockPalmSapling) AtumBlocks.PALMSAPLING).growTree(event.world, event.pos, new Random());
                }
                event.setResult(Event.Result.ALLOW);
            }

            return false;
        }
        return true;
    }

    @SubscribeEvent
    public void onFishEvent(EntityJoinWorldEvent event) {
        if (event.entity.worldObj.provider.getDimensionId() == AtumConfig.DIMENSION_ID && event.entity instanceof EntityFishHook) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public boolean onHoeEvent(UseHoeEvent event) {
        Block block = event.world.getBlockState(event.pos).getBlock();
        if (block == AtumBlocks.FERTILESOIL) {
            byte block2 = 0;
            if (event.current.getItem() == AtumItems.GEBS_BLESSING) {
                block2 = 4;
            }

            event.world.setBlockState(event.pos, AtumBlocks.FERTILESOILTILLED.getStateFromMeta(block2), 2);
            event.setResult(Event.Result.ALLOW);
            event.world.playSoundEffect((double) event.pos.getX(), (double) event.pos.getY(), (double) event.pos.getZ(), block.stepSound.getStepSound(), (block.stepSound.getVolume() + 1.0F) / 2.0F, block.stepSound.getFrequency() * 0.8F);
            return true;
        } else if ((block == Blocks.dirt || block == Blocks.grass) && event.current.getItem() == AtumItems.GEBS_BLESSING) {
            event.world.setBlockState(event.pos, AtumBlocks.FERTILESOILTILLED.getStateFromMeta(12), 2);
            event.setResult(Event.Result.ALLOW);
            event.world.playSoundEffect((double) event.pos.getX(), (double) event.pos.getZ(), (double) event.pos.getZ(), block.stepSound.getStepSound(), (block.stepSound.getVolume() + 1.0F) / 2.0F, block.stepSound.getFrequency() * 0.8F);
            return true;
        }
        return false;
    }

    @SubscribeEvent
    public void onPickup(EntityItemPickupEvent pickupEvent) {
        if (pickupEvent.item.getEntityItem().isItemEqual(new ItemStack(AtumBlocks.LOG)) || pickupEvent.item.getEntityItem().isItemEqual(new ItemStack(AtumBlocks.DEADWOOD_LOG))) {
            pickupEvent.entityPlayer.triggerAchievement(AchievementList.mineWood);
        }
    }
}