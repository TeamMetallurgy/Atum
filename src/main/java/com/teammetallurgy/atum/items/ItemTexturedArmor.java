package com.teammetallurgy.atum.items;

import com.teammetallurgy.atum.utils.Constants;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class ItemTexturedArmor extends ItemArmor {
    private String textureFile;
    private Item repairItem = null;

    public ItemTexturedArmor(ArmorMaterial material, int renderIndex, EntityEquipmentSlot slot) {
        super(material, renderIndex, slot);
    }

    protected ItemTexturedArmor setRepairItem(Item item) {
        this.repairItem = item;
        return this;
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return repair.getItem() == this.repairItem;
    }

    public ItemTexturedArmor setTextureFile(String filename) {
        this.textureFile = filename;
        return this;
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
        return Constants.MODID + ":" + "textures/armor/" + this.textureFile + ".png";
    }
}