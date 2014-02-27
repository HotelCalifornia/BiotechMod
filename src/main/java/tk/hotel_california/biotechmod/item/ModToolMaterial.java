package tk.hotel_california.biotechmod.item;

import net.minecraft.item.Item;
import net.minecraft.init.Items;

//Girl do you know Java?
//Because your method body is sexy ;)
public enum ModToolMaterial {
    BIOTIC_TIER_ONE(1, 250, 3.0F, 1.0F, 1);
    //todo add more tiers as I make 'em
    private final int harvestLevel;
    private final int maxUses;
    private final float efficiencyOnProperMaterial;
    private final float damageVsEntity;
    private final int enchantability;
    public Item customCraftingMaterial = null;
    private ModToolMaterial(int par3, int par4, float par5, float par6, int par7)
    {
        this.harvestLevel = par3;
        this.maxUses = par4;
        this.efficiencyOnProperMaterial = par5;
        this.damageVsEntity = par6;
        this.enchantability = par7;
    }
    public int getMaxUses()
    {
        return this.maxUses;
    }

    /**
     * The strength of this tool material against blocks which it is effective against.
     */
    public float getEfficiencyOnProperMaterial()
    {
        return this.efficiencyOnProperMaterial;
    }

    /**
     * Damage versus entities.
     */
    public float getDamageVsEntity()
    {
        return this.damageVsEntity;
    }

    /**
     * The level of material this tool can harvest (3 = DIAMOND, 2 = IRON, 1 = STONE, 0 = IRON/GOLD)
     */
    public int getHarvestLevel()
    {
        return this.harvestLevel;
    }

    /**
     * Return the natural enchantability factor of the material.
     */
    public int getEnchantability()
    {
        return this.enchantability;
    }

    public Item func_150995_f() {
        switch (this) {
            case BIOTIC_TIER_ONE: return Items.bone;
            default: return customCraftingMaterial;
        }
    }
}
