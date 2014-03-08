package tk.hotel_california.biotechmod;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import tk.hotel_california.biotechmod.item.Items;

@Mod(modid = "biotechmod", name = "Biotech Mod", version = "0.0.1")
public class BiotechMod {
    @Instance(value="biotechmod")
    public static BiotechMod instance;
    @SidedProxy(clientSide = "tk.hotel_california.biotechmod.client.ClientProxy",
    serverSide = "tk.hotel_california.biotechmod.CommonProxy")
    public static CommonProxy proxy;
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        Items.fullRegister();
        //for later when more tiers are added:
        //use Loader.instance().isModLoaded; to check for TE or BC or IC or w/e
        //if(TE==loaded){don't load power makers} else{load power makers}
    }
    @EventHandler
    public void load(FMLInitializationEvent event) {
        proxy.registerRenderers();
    }
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        //stub
    }
}
