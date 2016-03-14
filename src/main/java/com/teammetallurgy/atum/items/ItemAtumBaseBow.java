package com.teammetallurgy.atum.items;

import com.teammetallurgy.atum.utils.AtumUtils;
import com.teammetallurgy.atum.utils.Constants;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemAtumBaseBow extends ItemBow {

    public ItemAtumBaseBow() {
        this.maxStackSize = 1;
        this.setMaxDamage(384);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ModelResourceLocation getModel(ItemStack stack, EntityPlayer player, int useRemaining) {
        if (stack != null && player.getItemInUse() != null && stack.getItem() == this) {
            int i = stack.getMaxItemUseDuration() - player.getItemInUseCount();

            String bowName = AtumUtils.toRegistryName(AtumUtils.toUnlocalizedName(stack.getUnlocalizedName()));
            if (i >= 18) {
                return new ModelResourceLocation(Constants.MODID + ":" + bowName + "_pulling_2", "inventory");
            } else if (i > 13) {
                return new ModelResourceLocation(Constants.MODID + ":" + bowName + "_pulling_1", "inventory");
            } else if (i > 0) {
                return new ModelResourceLocation(Constants.MODID + ":" + bowName + "_pulling_0", "inventory");
            }
        }
        return super.getModel(stack, player, useRemaining);
    }
}