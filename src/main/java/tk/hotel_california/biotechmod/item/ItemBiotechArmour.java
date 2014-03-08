package tk.hotel_california.biotechmod.item;
//girl do you know Java?
//because your method body is sexy!

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class ItemBiotechArmour extends ItemArmor {
    @SideOnly(Side.CLIENT)
    private IIcon[] icons;
    public int armourType;
    //for my own use
    public ArmorMaterial theMaterial;
    public ItemBiotechArmour(ArmorMaterial material, int par1, int par2) {
        super(material, par1, par2);
        this.theMaterial = material;
        this.armourType = par2;
        this.setCreativeTab(CreativeTabs.tabCombat);
    }
    public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack) {
        return this.theMaterial.func_151685_b() == net.minecraft.init.Items.bone;
    }
    @SideOnly(Side.CLIENT)
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        String texture = "biotechmod:textures/models/BTA_";
        switch(this.armourType){
            case 0 : texture += "1.png";
            case 1 : texture += "2.png";
            case 2 : texture += "3.png";
            case 3 : texture += "4.png";
        }
        return texture;
    }
    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IIconRegister iconRegister) {
        icons = new IIcon[4];
        icons[0] = iconRegister.registerIcon("biotechmod:gardenerHelmet");
        icons[1] = iconRegister.registerIcon("biotechmod:gardenerChest");
        icons[2] = iconRegister.registerIcon("biotechmod:gardenerLegs");
        icons[3] = iconRegister.registerIcon("biotechmod:gardenerBoots");
    }
    @Override
    public IIcon getIcon(ItemStack stack, int pass) {
        return armourType <= 3 ? icons[armorType] : icons[0];
    }
}