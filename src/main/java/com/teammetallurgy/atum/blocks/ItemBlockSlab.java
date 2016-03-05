package com.teammetallurgy.atum.blocks;

public class ItemBlockSlab /*extends ItemSlab*/ { //TODO
    /*public static final String[] types = {"smooth", "cracked", "largeBrick", "smallBrick"};

    public ItemBlockSlab(Block baseBlock) {
        super(baseBlock, AtumBlocks.SLABS, AtumBlocks.DOUBLESLAB);
        this.setHasSubtypes(true);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tab, List<ItemStack> subItems) {
        if (this != Item.getItemFromBlock(AtumBlocks.DOUBLESLAB)) {
            for (int i = 0; i < types.length; i++) {
                subItems.add(new ItemStack(this, 1, i));
            }
        }
    }

    @Override
    public int getMetadata(int damage) {
        return damage;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return "tile.atum." + types[stack.getItemDamage()] + "Slab";
    }*/
}