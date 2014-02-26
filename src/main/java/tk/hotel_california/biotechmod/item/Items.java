package tk.hotel_california.biotechmod.item;

//girl do you know Java?
//because your method body is sexy!

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class Items {
    public static class declareItems {
        public static Item gardenerHoeItem;
        //todo add more items as I make 'em
    }
    public static void InitItems() {
        declareItems.gardenerHoeItem = new ItemGardenerHoe(ModToolMaterial.BIOTIC_TIER_ONE)
        .setUnlocalizedName("gardenerHoe")
        .setTextureName("biotechmod:gardenerHoe");
    }
    public static void registerRecipes() {

    }
    public static void registerShapelessRecipes() {

    }
    public static void registerOtherRecipes() {

    }
    public static void registerItems() {
        GameRegistry.registerItem(declareItems.gardenerHoeItem, "gardenerHoeItem");
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

