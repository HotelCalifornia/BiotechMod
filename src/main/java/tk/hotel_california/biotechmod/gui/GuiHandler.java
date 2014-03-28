package tk.hotel_california.biotechmod.gui;
//girl do you know Java?
//because your method body is sexy!

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import tk.hotel_california.biotechmod.container.ContainerPower;
import tk.hotel_california.biotechmod.tileentity.TileEntityEnergyContainer;

public class GuiHandler implements IGuiHandler {
    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if(tileEntity != null) {
            switch(id) {
                case 0 : if(tileEntity instanceof TileEntityEnergyContainer) {
                             return new ContainerPower(player.inventory,
                                     (TileEntityEnergyContainer)tileEntity,
                                     ((TileEntityEnergyContainer) tileEntity).getCapacity((TileEntityEnergyContainer)tileEntity));
                }
                default: return null;
            }
        }
        return null;
    }
    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if(tileEntity != null) {
            switch(id) {
                case 0 : if(tileEntity instanceof TileEntityEnergyContainer) {
                        return new GuiPower(player.inventory,
                                (TileEntityEnergyContainer)tileEntity,
                                ((TileEntityEnergyContainer) tileEntity).getCapacity((TileEntityEnergyContainer)tileEntity));
                }
                default: return null;
            }
        }
        return null;
    }
}
