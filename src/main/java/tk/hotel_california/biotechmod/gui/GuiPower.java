package tk.hotel_california.biotechmod.gui;
//girl do you know Java?
//because your method body is sexy!

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;
import tk.hotel_california.biotechmod.container.ContainerPower;
import tk.hotel_california.biotechmod.tileentity.TileEntityEnergyContainer;

public class GuiPower extends GuiContainer {
    public GuiPower(InventoryPlayer inventoryPlayer, TileEntityEnergyContainer tileEntity, int capacity) {
        super(new ContainerPower(inventoryPlayer, tileEntity, capacity));
    }
    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        fontRendererObj.drawString("Power In", 32, 55,   4210752);
        fontRendererObj.drawString("Power Out", 112, 55, 4210742);
        fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 2, 4210752);
    }
    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture(new ResourceLocation("biotechmod:textures/gui/guiPowerContainer.png"));
        int x =  (width - xSize) / 2;
        int y = (height - ySize) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
    }
}
