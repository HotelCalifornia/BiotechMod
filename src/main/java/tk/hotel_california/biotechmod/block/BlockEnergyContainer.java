package tk.hotel_california.biotechmod.block;
//girl do you know Java?
//because your method body is sexy!

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Facing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import tk.hotel_california.biotechmod.BiotechMod;
import tk.hotel_california.biotechmod.item.Items;
import tk.hotel_california.biotechmod.special.EntityItemRender;
import tk.hotel_california.biotechmod.tileentity.TileEntityEnergyContainer;

import java.util.Random;

public class BlockEnergyContainer extends BlockContainer {
    private int capacity;
    private EntityItem p1;
    private EntityItem p2;
    private EntityItem p3;
    protected BlockEnergyContainer(Material material, int capacity) {
        super(material);
        switch(capacity){
            case 100   :   setBlockName("blockEnergyContainer_one");
            case 1000  :   setBlockName("blockEnergyContainer_two");
            case 10000 : setBlockName("blockEnergyContainer_three");
            default    :       setBlockName("blockEnergyContainer");
        }
        setCreativeTab(CreativeTabs.tabAllSearch);
        this.capacity = capacity;
    }
    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int par6) {
        dropItems(world, x, y, z);
        super.breakBlock(world, x, y, z, this, par6);
    }
    private void dropItems(World world, int x, int y, int z) {
        Random rand = new Random();
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if(!(tileEntity instanceof IInventory)) {
            return;
        }
        IInventory inventory = (IInventory) tileEntity;
        for(int i = 0; i < inventory.getSizeInventory() - 1; i++) {
            ItemStack item = inventory.getStackInSlot(i);
            if(item != null && item.stackSize > 0) {
                float rx = rand.nextFloat();
                float ry = rand.nextFloat();
                float rz = rand.nextFloat();
                EntityItem entityItem = new EntityItem(world, x+rx, y+ry, z+rz, new ItemStack(item.getItem(),item.stackSize));
                if(item.hasTagCompound()) {
                    entityItem.getEntityItem().setTagCompound((NBTTagCompound)item.getTagCompound().copy());
                }
                float factor = 0.05F;
                entityItem.motionX = rand.nextGaussian() * factor;
                entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
                entityItem.motionZ = rand.nextGaussian() * factor;
                world.spawnEntityInWorld(entityItem);
                item.stackSize = 0;
            }
        }
    }
    @Override
    public boolean shouldSideBeRendered(IBlockAccess access, int x, int y, int z, int side) {
        return false;
    }
    @Override
    public boolean isOpaqueCube() {
        return false;
    }
    @Override
    public boolean onBlockActivated(World world, int x, int y,int z, EntityPlayer player, int metadata, float hitX, float hitY, float hitZ) {
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if(tileEntity == null || player.isSneaking()) {
            return false;
        }
        player.openGui(BiotechMod.instance, 0, world, x, y, z);
        return true;
    }
    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
        this.p1 = new EntityItemRender(world, (float)x + 0.5f, (float)y + 0.375f, (float)z + 0.31f, new ItemStack(Items.declareItems.bioticBatteryItem) );
        this.p2 = new EntityItemRender(world, (float)x + 0.5f, (float)y + 0.375f, (float)z + 0.5f, new ItemStack(Items.declareItems.bioticBatteryItem)  );
        this.p3 = new EntityItemRender(world, (float)x + 0.5f, (float)y + 0.375f, (float)z + 0.687f, new ItemStack(Items.declareItems.bioticBatteryItem));
    }

    @Override
    public TileEntity createNewTileEntity(World world, int n) {
        return new TileEntityEnergyContainer(capacity);
    }
}
