package tk.hotel_california.biotechmod.client;

import cpw.mods.fml.client.registry.RenderingRegistry;
import tk.hotel_california.biotechmod.CommonProxy;

public class ClientProxy extends CommonProxy {
    @Override
    public void registerRenderers() {

    }
    public int addArmour(String armour) {
        return RenderingRegistry.addNewArmourRendererPrefix(armour);
    }
}
