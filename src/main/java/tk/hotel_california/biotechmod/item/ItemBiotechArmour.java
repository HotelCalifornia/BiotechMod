package tk.hotel_california.biotechmod.item;
//girl do you know Java?
//because your method body is sexy!

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockDispenser;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.command.IEntitySelector;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBehaviorDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.List;

public class ItemBiotechArmour extends Item {
    public static final int[] maxDamageArray = new int[] {11, 16, 15, 13};
    private static final String[] TIER_ONE_OVERLAY_NAMES = new String[] {"gardener_helmet_overlay", "gardener_chestplate_overlay", "gardener_leggings_overlay", "gardener_boots_overlay"};
    public static final String[] EMPTY_SLOT_NAMES = new String[] {"empty_armor_slot_helmet", "empty_armor_slot_chestplate", "empty_armor_slot_leggings", "empty_armor_slot_boots"};
    private static final IBehaviorDispenseItem dispenserBehavior = new BehaviorDefaultDispenseItem()
    {
        private static final String __OBFID = "CL_00001767";
        /**
         * Dispense the specified stack, play the dispense sound and spawn particles.
         */
        protected ItemStack dispenseStack(IBlockSource par1IBlockSource, ItemStack par2ItemStack)
        {
            EnumFacing enumfacing = BlockDispenser.func_149937_b(par1IBlockSource.getBlockMetadata());
            int i = par1IBlockSource.getXInt() + enumfacing.getFrontOffsetX();
            int j = par1IBlockSource.getYInt() + enumfacing.getFrontOffsetY();
            int k = par1IBlockSource.getZInt() + enumfacing.getFrontOffsetZ();
            AxisAlignedBB axisalignedbb = AxisAlignedBB.getAABBPool().getAABB((double)i, (double)j, (double)k, (double)(i + 1), (double)(j + 1), (double)(k + 1));
            List list = par1IBlockSource.getWorld().selectEntitiesWithinAABB(EntityLivingBase.class, axisalignedbb, new IEntitySelector.ArmoredMob(par2ItemStack));

            if (list.size() > 0)
            {
                EntityLivingBase entitylivingbase = (EntityLivingBase)list.get(0);
                int l = entitylivingbase instanceof EntityPlayer ? 1 : 0;
                int i1 = EntityLiving.getArmorPosition(par2ItemStack);
                ItemStack itemstack1 = par2ItemStack.copy();
                itemstack1.stackSize = 1;
                entitylivingbase.setCurrentItemOrArmor(i1 - l, itemstack1);

                if (entitylivingbase instanceof EntityLiving)
                {
                    ((EntityLiving)entitylivingbase).setEquipmentDropChance(i1, 2.0F);
                }

                --par2ItemStack.stackSize;
                return par2ItemStack;
            }
            else
            {
                return super.dispenseStack(par1IBlockSource, par2ItemStack);
            }
        }
    };
    public final int theArmourType;
    public final int damageReduceAmount;
    public final int theRenderIndex;
    public final ModArmourMaterial theArmourMaterial;
    @SideOnly(Side.CLIENT)
    private IIcon overlayIcon;
    @SideOnly(Side.CLIENT)
    private IIcon emptySlotIcon;
    public ItemBiotechArmour(ModArmourMaterial material, int renderIndex, int armourType) {
        this.theArmourMaterial = material;
        this.theArmourType = armourType;
        this.theRenderIndex = renderIndex;
        this.damageReduceAmount = material.getDamageReductionAmount(armourType);
    }
    //this method won't ever do anything, it's just here for completeness
    @SideOnly(Side.CLIENT)
    public int getColorFromItemStack(ItemStack par1ItemStack, int par2) {
        if (par2 > 0) {
            return 16777215;
        }
        else {
            int j = this.getColor(par1ItemStack);

            if (j < 0) {
                j = 16777215;
            }

            return j;
        }
    }
    public int getItemEnchantability() {
        return this.theArmourMaterial.getEnchantability();
    }
    public ModArmourMaterial getArmorMaterial() {
        return this.theArmourMaterial;
    }
    public boolean hasColor(ItemStack par1ItemStack) {
        return false;
    }
    //this method won't ever do anything, it's just here for completeness
    public int getColor(ItemStack par1ItemStack) {
        if (this.theArmourMaterial == ModArmourMaterial.BIOTIC_TIER_ONE) {
            return -1;
        }
        else {
            NBTTagCompound nbttagcompound = par1ItemStack.getTagCompound();

            if (nbttagcompound == null) {
                return 10511680;
            }
            else {
                NBTTagCompound nbttagcompound1 = nbttagcompound.getCompoundTag("display");
                return nbttagcompound1 == null ? 10511680 : (nbttagcompound1.hasKey("color", 3) ? nbttagcompound1.getInteger("color") : 10511680);
            }
        }
    }
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamageForRenderPass(int par1, int par2) {
        return par2 == 1 ? this.overlayIcon : super.getIconFromDamageForRenderPass(par1, par2);
    }
    //this won't ever do anything, it's.. you get the idea
    public void removeColor(ItemStack par1ItemStack) {
        if (this.theArmourMaterial != ModArmourMaterial.BIOTIC_TIER_ONE) {
            NBTTagCompound nbttagcompound = par1ItemStack.getTagCompound();

            if (nbttagcompound != null) {
                NBTTagCompound nbttagcompound1 = nbttagcompound.getCompoundTag("display");

                if (nbttagcompound1.hasKey("color")) {
                    nbttagcompound1.removeTag("color");
                }
            }
        }
    }
    //another one of those methods :3
    public void func_82813_b(ItemStack par1ItemStack, int par2)
    {
        if (this.theArmourMaterial == ModArmourMaterial.BIOTIC_TIER_ONE) {
            throw new UnsupportedOperationException("Can\'t dye non-leather!");
        }
        else {
            NBTTagCompound nbttagcompound = par1ItemStack.getTagCompound();

            if (nbttagcompound == null) {
                nbttagcompound = new NBTTagCompound();
                par1ItemStack.setTagCompound(nbttagcompound);
            }

            NBTTagCompound nbttagcompound1 = nbttagcompound.getCompoundTag("display");

            if (!nbttagcompound.hasKey("display", 10)) {
                nbttagcompound.setTag("display", nbttagcompound1);
            }

            nbttagcompound1.setInteger("color", par2);
        }
    }
    public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack) {
        return this.theArmourMaterial.func_151685_b() == par2ItemStack.getItem() || super.getIsRepairable(par1ItemStack, par2ItemStack);
    }
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister par1IconRegister) {
        super.registerIcons(par1IconRegister);

        if (this.theArmourMaterial == ModArmourMaterial.BIOTIC_TIER_ONE) {
            this.overlayIcon = par1IconRegister.registerIcon(TIER_ONE_OVERLAY_NAMES[this.theArmourType]);
        }

        this.emptySlotIcon = par1IconRegister.registerIcon(EMPTY_SLOT_NAMES[this.theArmourType]);
    }
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        int i = EntityLiving.getArmorPosition(par1ItemStack) - 1;
        ItemStack itemstack1 = par3EntityPlayer.getCurrentArmor(i);

        if (itemstack1 == null) {
            par3EntityPlayer.setCurrentItemOrArmor(i + 1, par1ItemStack.copy());  //Forge: Vanilla bug fix associated with fixed setCurrentItemOrArmor indexs for players.
            par1ItemStack.stackSize = 0;
        }

        return par1ItemStack;
    }
    //don't actually know what this one does, but I'm sure I'll find out it does something horrible.
    //(afaik it just returns the empty slot icon for armour, although I'm not sure why)
    //todo after initialising armour pieces in items, add a set here.
    @SideOnly(Side.CLIENT)
    public static IIcon func_94602_b(int par0) {
        switch (par0) {
            /*case 0:
                return net.minecraft.init.Items.diamond_helmet.emptySlotIcon;
            case 1:
                return net.minecraft.init.Items.diamond_chestplate.emptySlotIcon;
            case 2:
                return net.minecraft.init.Items.diamond_leggings.emptySlotIcon;
            case 3:
                return net.minecraft.init.Items.diamond_boots.emptySlotIcon; */
            default:
                return null;
        }
    }

}
