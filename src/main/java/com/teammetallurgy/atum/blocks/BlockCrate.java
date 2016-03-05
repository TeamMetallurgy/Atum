package com.teammetallurgy.atum.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockCrate extends Block /*extends BlockContainer*/ {//TODO Crate. What is it even used for? (Can't find it in 1.7.10)

    public BlockCrate() {
        super(Material.wood);
    }

    /*private static final String[] TYPES = {"Palm", "Deadwood"}; //TODO Use EnumType for AtumPlanks
    private Random random = new Random();

    protected BlockCrate() {
        super(Material.wood);
        setHardness(3.0F);
        setStepSound(soundTypeWood);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntityCrate();
    }

    @Override
    public int damageDropped(int meta) {
        return meta;
    }

    public String getUnlocalizedName(int meta) {
        if (meta < 0 || meta >= TYPES.length)
            return getUnlocalizedName();

        return getUnlocalizedName() + "." + TYPES[meta].toLowerCase(Locale.US);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        if (world.isRemote)
            return true;

        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if (tileEntity != null && tileEntity instanceof TileEntityCrate) {
            player.openGui(Atum.instance, 1, world, x, y, z);
            return true;
        }

        return false;
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase placer, ItemStack itemStack) {
        TileEntity tileEntity = world.getTileEntity(x, y, z);

        if (tileEntity instanceof TileEntityCrate && itemStack.hasDisplayName()) {
            ((TileEntityCrate) tileEntity).setInventoryName(itemStack.getDisplayName());
        }
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
        IInventory tileEntity = (IInventory) world.getTileEntity(x, y, z);

        if (tileEntity instanceof TileEntityCrate) {
            TileEntityCrate tileEntityCrate = (TileEntityCrate) tileEntity;

            for (int i = 0; i < tileEntityCrate.getSizeInventory(); i++) {
                ItemStack droppedItemstack = tileEntityCrate.getStackInSlot(i);

                if (droppedItemstack != null) {
                    double offsetX = this.random.nextDouble() * 0.8D + 0.1D;
                    double offsetY = this.random.nextDouble() * 0.8F + 0.1D;
                    double offsetZ = this.random.nextDouble() * 0.8F + 0.1D;

                    while (droppedItemstack.stackSize > 0) {
                        int splitSize = this.random.nextInt(21) + 10;

                        if (splitSize > droppedItemstack.stackSize) {
                            splitSize = droppedItemstack.stackSize;
                        }

                        droppedItemstack.stackSize -= splitSize;
                        EntityItem dropedItemstackEntity = new EntityItem(world, x + offsetX, y + offsetY, z + offsetZ, new ItemStack(droppedItemstack.getItem(), splitSize, droppedItemstack.getItemDamage()));

                        if (droppedItemstack.hasTagCompound()) {
                            dropedItemstackEntity.getEntityItem().setTagCompound((NBTTagCompound) droppedItemstack.getTagCompound().copy());
                        }

                        double motionOffset = 0.05D;
                        dropedItemstackEntity.motionX = (random.nextGaussian() * motionOffset);
                        dropedItemstackEntity.motionY = (random.nextGaussian() * motionOffset + 0.2D);
                        dropedItemstackEntity.motionZ = (random.nextGaussian() * motionOffset);
                        world.spawnEntityInWorld(dropedItemstackEntity);
                    }
                }
            }

            // Notify neighboring blocks.
            world.func_147453_f(x, y, z, block);
        }
        super.breakBlock(world, x, y, z, block, meta);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tabs, List<ItemStack> list) {
        for (int i = 0; i < TYPES.length; i++) {
            list.add(new ItemStack(this, 1, i));
        }
    }*/
}