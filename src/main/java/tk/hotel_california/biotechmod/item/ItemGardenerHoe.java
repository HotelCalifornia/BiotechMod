package tk.hotel_california.biotechmod.item;


import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.UseHoeEvent;

import java.util.List;

public class ItemGardenerHoe extends ItemHoe {
    protected ToolMaterial theToolMaterial;
    public ItemGardenerHoe(Item.ToolMaterial toolMaterial) {
        super(toolMaterial);
        this.setMaxStackSize(1);
        this.setMaxDamage(toolMaterial.getMaxUses());
        this.setCreativeTab(CreativeTabs.tabTools);
    }
    /**
     * Returns the name of the material this tool is made from as it is declared in Item.ToolMaterial (meaning diamond
     * would return "EMERALD")
     */
    @Override
    public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack) {
        return this.theToolMaterial.func_150995_f() == net.minecraft.init.Items.bone;
    }
    @Override
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        par3List.add(String.format("%sAn unusually efficient hoe.", EnumChatFormatting.DARK_GREEN));
        par3List.add(String.format("%sSeems to be a long-lasting alternative to stone.", EnumChatFormatting.DARK_GREEN));
    }

}
