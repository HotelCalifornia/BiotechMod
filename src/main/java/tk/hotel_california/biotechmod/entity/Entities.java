package tk.hotel_california.biotechmod.entity;
//girl do you know Java?
//because your method body is sexy!


import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import tk.hotel_california.biotechmod.BiotechMod;
import tk.hotel_california.biotechmod.client.ClientProxy;
import tk.hotel_california.biotechmod.tileentity.TileEntityEnergyContainer;

public class Entities {
    public static void registerEntities() {
        GameRegistry.registerTileEntity(TileEntityEnergyContainer.class, "tileEntityEnergyContainer");
    }
    public static void fullRegister() {
        registerEntities();
    }
}
