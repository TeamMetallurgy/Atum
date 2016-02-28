package com.teammetallurgy.atum.blocks;

import com.teammetallurgy.atum.Atum;
import com.teammetallurgy.atum.blocks.tileentity.crate.TileEntityCrate;
import com.teammetallurgy.atum.utils.Constants;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.List;
import java.util.Locale;
import java.util.Random;

public class BlockCrate extends BlockContainer {
    
    private static final String[] TYPES = {"Palm", "Deadwood"};
    private IIcon icons[];
    private Random random = new Random();

    protected BlockCrate() {
        super(Material.wood);
        setCreativeTab(Atum.creativeTab);
        setBlockName("crate");
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
        
        TileEntity tileEntity = world.getTileEntity(x,y,z);
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
            ((TileEntityCrate)tileEntity).setInventoryName(itemStack.getDisplayName());
        }
    }
    
    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
        IInventory tileEntity = (IInventory)world.getTileEntity(x, y, z);

        if (tileEntity instanceof TileEntityCrate) {
            TileEntityCrate tileEntityCrate = (TileEntityCrate)tileEntity;

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
                            dropedItemstackEntity.getEntityItem().setTagCompound((NBTTagCompound)droppedItemstack.getTagCompound().copy());
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
    public void registerBlockIcons(IIconRegister register) {
        icons = new IIcon[TYPES.length];
        for (int i = 0; i < TYPES.length; i++) {
            icons[i] = register.registerIcon(Constants.MODID.toLowerCase(Locale.US) + ":" + TYPES[i].toLowerCase(Locale.US) + "_crate");
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        if (meta < 0 || meta >= icons.length)
            return icons[0];

        return icons[meta];
    }

    @Override
    @SideOnly(Side.CLIENT)
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void getSubBlocks(Item item, CreativeTabs creativeTab, List list) {
        for (int i = 0; i < TYPES.length ;i++){
            list.add(new ItemStack(this, 1, i));
        }
    }    
}
