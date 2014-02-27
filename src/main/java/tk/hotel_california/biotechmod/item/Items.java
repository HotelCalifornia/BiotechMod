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
        //todo add more items as I make 'em
    }
    public static void InitItems() {
        declareItems.gardenerHoeItem = new ItemGardenerHoe(ModToolMaterial.BIOTIC_TIER_ONE)
        .setUnlocalizedName("gardenerHoe")
        .setTextureName("biotechmod:gardenerHoe");
        declareItems.gardenerSwordItem = new ItemGardenerSword(ModToolMaterial.BIOTIC_TIER_ONE)
        .setUnlocalizedName("gardenerSword")
        .setTextureName("biotechMod:gardenerSword");
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

