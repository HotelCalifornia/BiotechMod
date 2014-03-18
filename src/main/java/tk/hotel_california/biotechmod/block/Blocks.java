package tk.hotel_california.biotechmod.block;
//girl do you know Java?
//because your method body is sexy!

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import tk.hotel_california.biotechmod.item.Items;

public class Blocks {
    public static class declareBlocks {
        public static Block tierOneBatBoxBlock;
        //todo add more blocks as I make 'em
    }
    public static void InitBlocks() {
        declareBlocks.tierOneBatBoxBlock = new BlockEnergyContainer(Material.iron, 100)
                .setBlockTextureName("biotechmod:tierOneBatBox");
    }
    public static void registerRecipes() {
        ItemStack tierOneBatBox = new ItemStack(declareBlocks.tierOneBatBoxBlock, 1);
        GameRegistry.addRecipe(tierOneBatBox, "www", "xxx", "www",
                'w', net.minecraft.init.Blocks.planks,
                'x', Items.declareItems.bioticBatteryItem);
    }
    public static void registerShapelessRecipes() {

    }
    public static void registerOtherRecipes() {

    }
    public static void registerBlocks() {
        GameRegistry.registerBlock(declareBlocks.tierOneBatBoxBlock, "tierOneBatBoxBlock");
    }
    public static void fullRegister() {
        InitBlocks();
        registerRecipes();
        registerShapelessRecipes();
        registerOtherRecipes();
        registerBlocks();
    }

}
