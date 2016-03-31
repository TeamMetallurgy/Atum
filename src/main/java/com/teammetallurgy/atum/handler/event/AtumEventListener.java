package com.teammetallurgy.atum.handler.event;

import com.teammetallurgy.atum.blocks.AtumBlocks;
import com.teammetallurgy.atum.blocks.BlockAtumLog;
import com.teammetallurgy.atum.blocks.BlockAtumPlank;
import com.teammetallurgy.atum.blocks.BlockAtumSapling;
import com.teammetallurgy.atum.entity.*;
import com.teammetallurgy.atum.handler.AtumConfig;
import com.teammetallurgy.atum.items.AtumItems;
import net.minecraft.block.Block;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.SoundCategory;
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
        if (event.getEntity() instanceof EntityWraith || event.getEntity() instanceof EntityPharaoh) {
            event.setDistance(0.0F);
        }

    }

    @SubscribeEvent
    public void onLivingHurt(LivingHurtEvent event) {
        if (event.getSource().getDamageType().equals("drown") && (event.getEntity() instanceof EntityPharaoh || event.getEntity() instanceof EntityWraith || event.getEntity() instanceof EntityMummy || event.getEntity() instanceof EntityForsaken || event.getEntity() instanceof EntityStoneguard)) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public boolean onBonemeal(BonemealEvent event) {
        if (!event.getWorld().isRemote) {

            Block block = event.getWorld().getBlockState(event.getPos()).getBlock();
            if (block instanceof BlockAtumSapling) {
                if (event.getWorld().rand.nextInt(7) == 0) {
                    ((BlockAtumSapling) AtumBlocks.SAPLING).generateTree(event.getWorld(), event.getPos(), event.getWorld().getBlockState(event.getPos()), new Random());
                }
                event.setResult(Event.Result.ALLOW);
            }
            return false;
        }
        return true;
    }

    @SubscribeEvent
    public void onFishEvent(EntityJoinWorldEvent event) {
        if (event.getEntity().worldObj.provider.getDimension() == AtumConfig.DIMENSION_ID && event.getEntity() instanceof EntityFishHook) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public boolean onHoeEvent(UseHoeEvent event) {
        Block block = event.getWorld().getBlockState(event.getPos()).getBlock();
        if (block == AtumBlocks.FERTILE_SOIL) {
            byte block2 = 0;
            if (event.getCurrent().getItem() == AtumItems.GEBS_BLESSING) {
                block2 = 4;
            }

            event.getWorld().setBlockState(event.getPos(), AtumBlocks.FERTILE_SOIL_TILLED.getStateFromMeta(block2), 2);
            event.setResult(Event.Result.ALLOW);
            event.getWorld().playSound(event.getEntityPlayer(), event.getPos(), SoundEvents.item_hoe_till, SoundCategory.BLOCKS, 1.0F, 1.0F);
            return true;
        } else if ((block == Blocks.dirt || block == Blocks.grass) && event.getCurrent().getItem() == AtumItems.GEBS_BLESSING) {
            event.getWorld().setBlockState(event.getPos(), AtumBlocks.FERTILE_SOIL_TILLED.getStateFromMeta(12), 2);
            event.setResult(Event.Result.ALLOW);
            event.getWorld().playSound(event.getEntityPlayer(), event.getPos(), SoundEvents.item_hoe_till, SoundCategory.BLOCKS, 1.0F, 1.0F);
            return true;
        }
        return false;
    }

    @SubscribeEvent
    public void onPickup(EntityItemPickupEvent pickupEvent) {
        if (pickupEvent.getItem().getEntityItem().isItemEqual(new ItemStack(AtumBlocks.LOG.getDefaultState().withProperty(BlockAtumLog.VARIANT, BlockAtumPlank.EnumType.PALM).getBlock())) || pickupEvent.getItem().getEntityItem().isItemEqual(new ItemStack(AtumBlocks.LOG.getDefaultState().withProperty(BlockAtumLog.VARIANT, BlockAtumPlank.EnumType.DEADWOOD).getBlock()))) {
            pickupEvent.getEntityPlayer().addStat(AchievementList.mineWood);
        }
    }
}