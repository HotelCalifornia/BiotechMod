package tk.hotel_california.biotechmod;


import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import tk.hotel_california.biotechmod.block.Blocks;
import tk.hotel_california.biotechmod.client.ClientProxy;
import tk.hotel_california.biotechmod.entity.Entities;
import tk.hotel_california.biotechmod.gui.packethandling.PacketPipeline;
import tk.hotel_california.biotechmod.item.Items;

@Mod(modid = "biotechmod", name = "Biotech Mod", version = "0.0.1")
public class BiotechMod {
    @Instance(value="biotechmod")
    public static BiotechMod instance;
    @SidedProxy(clientSide = "tk.hotel_california.biotechmod.client.ClientProxy",
            serverSide = "tk.hotel_california.biotechmod.CommonProxy")
    public static CommonProxy proxy;
    public static ClientProxy clientProxy;
    public static World world = Minecraft.getMinecraft().theWorld;
    public static final PacketPipeline packetPipeline = new PacketPipeline();
    @EventHandler
    //called when forge is in the Preinitialization Event
    public void preInit(FMLPreInitializationEvent event) {
        Items.fullRegister();
        Blocks.fullRegister();
        Entities.fullRegister();
    }
    @EventHandler
    //called when forge is in the Initialization Event
    public void load(FMLInitializationEvent event) {
        packetPipeline.initialise();
        proxy.registerRenderers();
        proxy.registerTESR();
        proxy.registerGui();
    }
    @EventHandler
    //called when forge is in the PostInitialization Event
    public void postInit(FMLPostInitializationEvent event) {
        packetPipeline.postInitialise();
    }
}
