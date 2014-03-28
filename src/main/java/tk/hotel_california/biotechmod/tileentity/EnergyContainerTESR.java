package tk.hotel_california.biotechmod.tileentity;
//girl do you know Java?
//because your method body is sexy!

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;

public class EnergyContainerTESR extends TileEntitySpecialRenderer {
    private IModelCustom model;
    private ResourceLocation texture;
    public EnergyContainerTESR() {
        texture = new ResourceLocation("biotechmod:textures/energyContainer.png");
        model = AdvancedModelLoader.loadModel(new ResourceLocation("biotechmod:models/energyContainer.obj"));
    }
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float f) {
        GL11.glPushMatrix();
        GL11.glTranslated(x, y, z);
        Minecraft.getMinecraft().renderEngine.bindTexture(texture);
        model.renderAll();
        GL11.glPopMatrix();
    }
}
