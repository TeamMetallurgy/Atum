package com.teammetallurgy.atum.items;

import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class ItemTexturedArmor extends ItemArmor {
    private String textureFile;
    private Item repairItem = null;

    public ItemTexturedArmor(ArmorMaterial material, int renderIndex, int armorType) {
        super(material, renderIndex, armorType);
    }

    public ItemTexturedArmor setRepairItem(Item item) {
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
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        return "atum:textures/armor/" + this.textureFile + ".png";
    }
}