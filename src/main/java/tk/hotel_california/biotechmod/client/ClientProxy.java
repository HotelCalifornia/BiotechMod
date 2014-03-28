package tk.hotel_california.biotechmod.client;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.network.NetworkRegistry;
import tk.hotel_california.biotechmod.BiotechMod;
import tk.hotel_california.biotechmod.CommonProxy;
import tk.hotel_california.biotechmod.gui.GuiHandler;
import tk.hotel_california.biotechmod.tileentity.EnergyContainerTESR;
import tk.hotel_california.biotechmod.tileentity.TileEntityEnergyContainer;

public class ClientProxy extends CommonProxy {
    @Override
    public void registerRenderers() {

    }
    @Override
    public void registerTESR() {
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityEnergyContainer.class, new EnergyContainerTESR());
    }
    @Override
    public int addArmour(String armour) {
        return RenderingRegistry.addNewArmourRendererPrefix(armour);
    }
    @Override
    public void registerGui() {
        NetworkRegistry.INSTANCE.registerGuiHandler(BiotechMod.instance, new GuiHandler());
    }
}
