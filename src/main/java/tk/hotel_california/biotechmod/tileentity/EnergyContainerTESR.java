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
        texture = new ResourceLocation("minecraft:textures/blocks/planks_oak.png");
        model = AdvancedModelLoader.loadModel(new ResourceLocation("biotechmod:models/energyContainer.obj"));
    }
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float f) {
        GL11.glPushMatrix();
        GL11.glTranslated(x, y, z);
        Minecraft.getMinecraft().renderEngine.bindTexture(texture);
        GL11.glDisable(GL11.GL_LIGHTING);
        model.renderAll();
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }
}
