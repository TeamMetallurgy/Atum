package com.teammetallurgy.atum.items.artifacts;

import com.teammetallurgy.atum.entity.EntityPharaoh;
import com.teammetallurgy.atum.entity.EntityStoneSoldier;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

import java.util.Iterator;
import java.util.List;

public class ItemMonthusStrike extends ItemAxe {

    public ItemMonthusStrike(ToolMaterial material) {
        super(material);
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return true;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return 7200;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.BOW;
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World world, EntityPlayer player, int timeLeft) {
        int j = this.getMaxItemUseDuration(stack) - timeLeft;
        if (j > 21) {
            AxisAlignedBB bb = player.getEntityBoundingBox().expand(3.0D, 3.0D, 3.0D);
            List list = world.getEntitiesWithinAABB(EntityLiving.class, bb);
            Iterator i = list.iterator();

            while (i.hasNext()) {
                Entity entity = (Entity) i.next();
                if (entity != player && !(entity instanceof EntityStoneSoldier) && !(entity instanceof EntityPharaoh)) {
                    double dx = entity.posX - player.posX;
                    double dz = entity.posZ - player.posZ;
                    double magnitude = Math.sqrt(dx * dx + dz * dz);
                    dx /= magnitude;
                    dz /= magnitude;
                    entity.isAirBorne = true;
                    entity.addVelocity(dx * 2.5D, 0.3D, dz * 2.5D);
                    if (entity.motionY > 0.4000000059604645D) {
                        entity.motionY = 0.4000000059604645D;
                    }

                    if (world.isRemote) {
                        this.spawnParticle(entity);
                    }
                }
            }
            stack.damageItem(4, player);
        }
    }

    @SideOnly(Side.CLIENT)
    public void spawnParticle(Entity entity) {
        Minecraft.getMinecraft().effectRenderer.emitParticleAtEntity(entity, EnumParticleTypes.CRIT);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        player.setItemInUse(stack, this.getMaxItemUseDuration(stack));
        return stack;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public EnumRarity getRarity(ItemStack stack) {
        return EnumRarity.RARE;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced) {
        if (Keyboard.isKeyDown(42)) {
            tooltip.add(EnumChatFormatting.DARK_PURPLE + StatCollector.translateToLocal(this.getUnlocalizedName() + ".line1"));
            tooltip.add(EnumChatFormatting.DARK_PURPLE + StatCollector.translateToLocal(this.getUnlocalizedName() + ".line2"));
        } else {
            tooltip.add(StatCollector.translateToLocal(this.getUnlocalizedName() + ".line3") + " " + EnumChatFormatting.DARK_GRAY + "[SHIFT]");
        }
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return repair.getItem() == Items.diamond;
    }
}