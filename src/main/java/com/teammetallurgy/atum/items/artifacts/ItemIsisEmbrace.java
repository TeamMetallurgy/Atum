package com.teammetallurgy.atum.items.artifacts;

import com.teammetallurgy.atum.items.ItemTexturedArmor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

import java.util.List;

public class ItemIsisEmbrace extends ItemTexturedArmor {

    public ItemIsisEmbrace(ArmorMaterial material, int renderIndex, int armorType) {
        super(material, renderIndex, armorType);
        this.setRepairItem(Items.diamond);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack) {
        return true;
    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
        super.onArmorTick(world, player, stack);

        if (world.isRemote || stack == null || stack.getItem() != this) {
            return;
        }

        if (world.getTotalWorldTime() % 60L == 0L) {
            player.heal(1);
        }
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
            tooltip.add(TextFormatting.DARK_PURPLE + I18n.translateToLocal(this.getUnlocalizedName() + ".line1"));
            tooltip.add(TextFormatting.DARK_PURPLE + I18n.translateToLocal(this.getUnlocalizedName() + ".line2"));
        } else {
            tooltip.add(I18n.translateToLocal(this.getUnlocalizedName() + ".line3") + " " + TextFormatting.DARK_GRAY + "[SHIFT]");
        }
    }
}