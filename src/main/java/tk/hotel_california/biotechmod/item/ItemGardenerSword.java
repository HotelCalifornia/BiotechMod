package tk.hotel_california.biotechmod.item;
//girl do you know Java?
//because your method body is sexy!

import com.google.common.collect.Multimap;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

import java.util.List;

public class ItemGardenerSword extends Item {
    private final ModToolMaterial theToolMaterial;
    private float damage;

    public ItemGardenerSword(ModToolMaterial toolMaterial) {
        this.theToolMaterial = toolMaterial;
        this.setMaxStackSize(1);
        this.setMaxDamage(toolMaterial.getMaxUses());
        this.setCreativeTab(CreativeTabs.tabCombat);
        this.damage = 4.0F + toolMaterial.getDamageVsEntity();
    }
    public float func_150931_i() {
        return this.theToolMaterial.getDamageVsEntity();
    }
    public float func_150893_a(ItemStack itemStack, Block block) {
        if (block == Blocks.web) {
            return 25.0F;
        }
        else {
            Material material = block.getMaterial();
            return material != Material.plants && material != Material.vine && material != Material.coral && material != Material.leaves && material != Material.gourd ? 1.0F : 1.5F;
        }
    }
    public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase par2EntityLivingBase, EntityLivingBase par3EntityLivingBase) {
        par1ItemStack.damageItem(1, par3EntityLivingBase);
        par2EntityLivingBase.addPotionEffect(new PotionEffect(19, 300));
        return true;
    }
    public boolean onBlockDestroyed(ItemStack p_150894_1_, World p_150894_2_, Block p_150894_3_, int p_150894_4_, int p_150894_5_, int p_150894_6_, EntityLivingBase p_150894_7_) {
        if ((double)p_150894_3_.getBlockHardness(p_150894_2_, p_150894_4_, p_150894_5_, p_150894_6_) != 0.0D) {
            p_150894_1_.damageItem(2, p_150894_7_);
        }

        return true;
    }
    @SideOnly(Side.CLIENT)
    public boolean isFull3D() {
        return true;
    }
    public EnumAction getItemUseAction(ItemStack par1ItemStack) {
        return EnumAction.block;
    }
    public int getMaxItemUseDuration(ItemStack par1ItemStack) {
        return 72000;
    }
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
        return par1ItemStack;
    }
    public boolean func_150897_b(Block p_150897_1_) {
        return p_150897_1_ == Blocks.web;
    }
    public int getItemEnchantability() {
        return this.theToolMaterial.getEnchantability();
    }
    public String getToolMaterialName() {
        return this.theToolMaterial.toString();
    }
    public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack) {
        return this.theToolMaterial.func_150995_f() == par2ItemStack.getItem() ? true : super.getIsRepairable(par1ItemStack, par2ItemStack);
    }
    public Multimap getItemAttributeModifiers() {
        Multimap multimap = super.getItemAttributeModifiers();
        multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Weapon modifier", (double)this.damage, 0));
        return multimap;
    }
    @Override
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        par3List.add(String.format("%sA sword infused with poison.", EnumChatFormatting.DARK_GREEN));
        par3List.add(String.format("%sSeems to be good for clearing away cobwebs.", EnumChatFormatting.DARK_GREEN));
    }
}
