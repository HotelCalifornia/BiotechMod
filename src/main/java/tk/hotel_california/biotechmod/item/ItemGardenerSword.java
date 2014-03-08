package tk.hotel_california.biotechmod.item;
//girl do you know Java?
//because your method body is sexy!

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;

import java.util.List;

public class ItemGardenerSword extends ItemSword {
    private final ToolMaterial theToolMaterial;
    private float damage;

    public ItemGardenerSword(ToolMaterial toolMaterial) {
        super(toolMaterial);
        theToolMaterial = toolMaterial;
        this.setMaxStackSize(1);
        this.setMaxDamage(toolMaterial.getMaxUses());
        this.setCreativeTab(CreativeTabs.tabCombat);
        this.damage = 4.0F + toolMaterial.getDamageVsEntity();
    }
    public float func_150893_a(ItemStack itemStack, Block block) {
        Material material = block.getMaterial();
        if (block == Blocks.web) {
            return 25.0F;
        }
        else if(material == Material.plants || material == Material.vine || material == Material.coral || material == Material.leaves) {
            return 25.0F;
        }
        else {
            return material != Material.gourd ? 1.0F : 1.5F;
        }
    }
    public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase par2EntityLivingBase, EntityLivingBase par3EntityLivingBase) {
        par1ItemStack.damageItem(1, par3EntityLivingBase);
        par2EntityLivingBase.addPotionEffect(new PotionEffect(19, 300));
        return true;
    }
    public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack) {
        return this.theToolMaterial.func_150995_f() == par2ItemStack.getItem() ? true : super.getIsRepairable(par1ItemStack, par2ItemStack);
    }
    @Override
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        par3List.add(String.format("%sA sword infused with poison.", EnumChatFormatting.DARK_GREEN));
        par3List.add(String.format("%sSeems to be good for clearing away cobwebs and plants.", EnumChatFormatting.DARK_GREEN));
    }
}
