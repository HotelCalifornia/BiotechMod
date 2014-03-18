package tk.hotel_california.biotechmod;


import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import tk.hotel_california.biotechmod.block.Blocks;
import tk.hotel_california.biotechmod.gui.GuiHandler;
import tk.hotel_california.biotechmod.gui.packethandling.PacketPipeline;
import tk.hotel_california.biotechmod.item.Items;

@Mod(modid = "biotechmod", name = "Biotech Mod", version = "0.0.1")
public class BiotechMod {
    @Instance(value="biotechmod")
    public static BiotechMod instance;
    @SidedProxy(clientSide = "tk.hotel_california.biotechmod.client.ClientProxy",
            serverSide = "tk.hotel_california.biotechmod.CommonProxy")
    public static CommonProxy proxy;
    public static World world = Minecraft.getMinecraft().theWorld;
    public static final PacketPipeline packetPipeline = new PacketPipeline();
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        Items.fullRegister();
        Blocks.fullRegister();
    }
    @EventHandler
    public void load(FMLInitializationEvent event) {
        packetPipeline.initialise();
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
        proxy.registerRenderers();
    }
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        packetPipeline.postInitialise();
    }
}
