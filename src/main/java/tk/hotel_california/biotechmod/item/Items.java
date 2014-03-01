package tk.hotel_california.biotechmod.item;

//girl do you know Java?
//because your method body is sexy!

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class Items {
    public static class declareItems {
        public static Item gardenerHoeItem;
        public static Item gardenerSwordItem;
        public static Item gardenerHelmetItem;
        public static Item gardenerChestplateItem;
        public static Item gardenerLeggingsItem;
        public static Item gardenerBootsItem;
        //todo add more items as I make 'em
    }
    public static void InitItems() {
        declareItems.gardenerHoeItem = new ItemGardenerHoe(ModToolMaterial.BIOTIC_TIER_ONE)
                .setUnlocalizedName("gardenerHoe")
                .setTextureName("biotechmod:gardenerHoe");
        declareItems.gardenerSwordItem = new ItemGardenerSword(ModToolMaterial.BIOTIC_TIER_ONE)
                .setUnlocalizedName("gardenerSword")
                .setTextureName("biotechmod:gardenerSword");
        declareItems.gardenerHelmetItem = new ItemBiotechArmour(ModArmourMaterial.BIOTIC_TIER_ONE, 0, 0)
                .setUnlocalizedName("gardenerHelmet")
                .setTextureName("biotechmod:gardenerHelmet");
        declareItems.gardenerChestplateItem = new ItemBiotechArmour(ModArmourMaterial.BIOTIC_TIER_ONE, 0, 1)
                .setUnlocalizedName("gardenerChest")
                .setTextureName("biotechmod:gardenerChest");
        declareItems.gardenerLeggingsItem = new ItemBiotechArmour(ModArmourMaterial.BIOTIC_TIER_ONE, 0, 2)
                .setUnlocalizedName("gardenerLegs")
                .setTextureName("biotechmod:gardenerLegs");
        declareItems.gardenerBootsItem = new ItemBiotechArmour(ModArmourMaterial.BIOTIC_TIER_ONE,0 , 3)
                .setUnlocalizedName("gardenerBoots")
                .setTextureName("biotechmod:gardenerBoots");

    }
    public static void registerRecipes() {
        ItemStack tierOneHoe = new ItemStack(declareItems.gardenerHoeItem,1);
        GameRegistry.addRecipe(tierOneHoe, "xx ", " y ", "zyz",
                'x', net.minecraft.init.Items.flint,
                'y', net.minecraft.init.Items.bone,
                'z', net.minecraft.init.Items.leather);
        ItemStack tierOneSword = new ItemStack(declareItems.gardenerSwordItem,1);
        GameRegistry.addRecipe(tierOneSword,"wxw", "wxw", "yzy",
                'w', net.minecraft.init.Items.spider_eye,
                'x', net.minecraft.init.Items.flint,
                'y', net.minecraft.init.Items.leather,
                'z', net.minecraft.init.Items.bone);
        ItemStack tierOneHelm = new ItemStack(declareItems.gardenerHelmetItem,1);
        GameRegistry.addRecipe(tierOneHelm, "www", "xyx", "   ",
                'w', net.minecraft.init.Items.bone,
                'x', net.minecraft.init.Items.leather,
                'y', net.minecraft.init.Items.spider_eye);
        ItemStack tierOneChest = new ItemStack(declareItems.gardenerChestplateItem);
        GameRegistry.addRecipe(tierOneChest, "w w", "xxx", "xxx",
                'w', net.minecraft.init.Items.leather,
                'x', net.minecraft.init.Items.bone);
        ItemStack tierOneLegs = new ItemStack(declareItems.gardenerLeggingsItem);
        GameRegistry.addRecipe(tierOneLegs, "www", "x x", "w w",
                'w', net.minecraft.init.Items.bone,
                'x', net.minecraft.init.Items.leather);
        ItemStack tierOneBoots = new ItemStack(declareItems.gardenerBootsItem);
        GameRegistry.addRecipe(tierOneBoots, "w w", "x x", "   ",
                'w', net.minecraft.init.Items.leather,
                'x', net.minecraft.init.Items.bone);
    }
    public static void registerShapelessRecipes() {

    }
    public static void registerOtherRecipes() {

    }
    public static void registerItems() {
        GameRegistry.registerItem(declareItems.gardenerHoeItem, "gardenerHoeItem");
        GameRegistry.registerItem(declareItems.gardenerSwordItem, "gardenerSword");
        //todo add more items as I make 'em
    }
    public static void fullRegister() {
        InitItems();
        registerRecipes();
        registerShapelessRecipes();
        registerOtherRecipes();
        registerItems();
    }
}

