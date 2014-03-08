package tk.hotel_california.biotechmod.item;

//girl do you know Java?
//because your method body is sexy!

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import tk.hotel_california.biotechmod.client.ClientProxy;

public class Items {
    public static ClientProxy proxy = new ClientProxy();
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
        final ItemArmor.ArmorMaterial BIOTIC_TIER_ONE;
        final ItemHoe.ToolMaterial BIOTIC_TIER_ONE_TOOL;
        BIOTIC_TIER_ONE = EnumHelper.addArmorMaterial("BIOTIC_TIER_ONE", 10, new int[]{2, 5, 4, 1}, 1);
        BIOTIC_TIER_ONE_TOOL = EnumHelper.addToolMaterial("BIOTIC_TIER_ONE_TOOL", 1, 250, 3.0F, 1.0F, 1);
        declareItems.gardenerHoeItem = new ItemGardenerHoe(BIOTIC_TIER_ONE_TOOL)
                .setUnlocalizedName("gardenerHoe")
                .setTextureName("biotechmod:gardenerHoe");
        declareItems.gardenerSwordItem = new ItemGardenerSword(BIOTIC_TIER_ONE_TOOL)
                .setUnlocalizedName("gardenerSword")
                .setTextureName("biotechmod:gardenerSword");
        declareItems.gardenerHelmetItem = new ItemBiotechArmour(BIOTIC_TIER_ONE, proxy.addArmour("gardenerHelmet"), 0)
                .setUnlocalizedName("gardenerHelmet")
                .setTextureName("biotechmod:gardenerHelmet");
        declareItems.gardenerChestplateItem = new ItemBiotechArmour(BIOTIC_TIER_ONE, proxy.addArmour("gardenerChest"), 1)
                .setUnlocalizedName("gardenerChest")
                .setTextureName("biotechmod:gardenerChest");
        declareItems.gardenerLeggingsItem = new ItemBiotechArmour(BIOTIC_TIER_ONE, proxy.addArmour("gardenerLegs"), 2)
                .setUnlocalizedName("gardenerLegs")
                .setTextureName("biotechmod:gardenerLegs");
        declareItems.gardenerBootsItem = new ItemBiotechArmour(BIOTIC_TIER_ONE, proxy.addArmour("gardenerBoots"), 3)
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

