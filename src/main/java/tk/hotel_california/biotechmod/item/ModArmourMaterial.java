package tk.hotel_california.biotechmod.item;
//girl do you know Java?
//because your method body is sexy!

import net.minecraft.item.Item;
import net.minecraft.init.Items;

public enum ModArmourMaterial {
    BIOTIC_TIER_ONE(10, new int[]{2, 5, 4, 1}, 1);
    //todo add more as I make 'em
    private int maxDamageFactor;
    private int[] damageReductionAmountArray;
    private int enchantability;
    public Item customCraftingMaterial = null;
    private ModArmourMaterial(int par1, int[] par2, int par3) {
        this.maxDamageFactor = par1;
        this.damageReductionAmountArray = par2;
        this.enchantability = par3;
    }
    public int getDurability(int par1) {
        return ItemBiotechArmour.maxDamageArray[par1] * this.maxDamageFactor;
    }
    public int getDamageReductionAmount(int par1) {
        return this.damageReductionAmountArray[par1];
    }
    public int getEnchantability()
    {
        return this.enchantability;
    }
    public Item func_151685_b() {
        switch (this) {
            case BIOTIC_TIER_ONE: return Items.bone;
            default: return customCraftingMaterial;
        }
    }
}
