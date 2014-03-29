package tk.hotel_california.biotechmod.tileentity;
//girl do you know Java?
//because your method body is sexy!

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;
import tk.hotel_california.biotechmod.block.Blocks;
import tk.hotel_california.biotechmod.item.Items;

public class EnergyContainerTESR extends TileEntitySpecialRenderer {
    private IModelCustom model;
    private ResourceLocation texture;
    private RenderItem potato;
    private EntityItem dummy;

    public EnergyContainerTESR() {
        texture = new ResourceLocation("minecraft:textures/blocks/planks_oak.png");
        model = AdvancedModelLoader.loadModel(new ResourceLocation("biotechmod:models/energyContainer.obj"));
        potato = new RenderItem() {
            public boolean shouldBob() {
                return false;
            }
            public boolean shouldSpreadItems() {
                return false;
            }
        };
        dummy = new EntityItem(null);
        dummy.hoverStart = 0.0f;
        dummy.motionX = 0.0f;
        dummy.motionY = 0.0f;
        dummy.motionZ = 0.0f;
        potato.setRenderManager(RenderManager.instance);
    }
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float f) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x, (float) y, (float) z);
        TileEntityEnergyContainer te = (TileEntityEnergyContainer)tileEntity;
        renderEnergyContainer(te, te.getWorldObj(), te.xCoord, te.yCoord, te.zCoord, Blocks.declareBlocks.tierOneBatBoxBlock);
        GL11.glPopMatrix();
    }
    public void renderEnergyContainer(TileEntityEnergyContainer te, World world, int x, int y, int z, Block block) {
        GL11.glPushMatrix();
        GL11.glTranslated(x, y, z);
        Minecraft.getMinecraft().renderEngine.bindTexture(texture);
        GL11.glDisable(GL11.GL_LIGHTING);
        model.renderAll();
        doRenderItem(new ItemStack(Items.declareItems.bioticBatteryItem, 1), (float)x + 0.500f, (float)y + 0.375f, (float)z + 0.310f, 0);
        doRenderItem(new ItemStack(Items.declareItems.bioticBatteryItem, 1), (float)x + 0.500f, (float)y + 0.375f, (float)z + 0.500f, 0);
        doRenderItem(new ItemStack(Items.declareItems.bioticBatteryItem, 1), (float)x + 0.500f, (float)y + 0.375f, (float)z + 0.687f, 0);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }
    public void doRenderItem(ItemStack stack, float x, float y, float z, int f) {
        if(stack == null) {
            return;
        }
        dummy.setEntityItemStack(stack);
        float renderScale = 4.0f;
        GL11.glPushMatrix();
        GL11.glTranslatef(x, y + .75f, z);
        GL11.glRotatef(180, 1f, 0f, 0f);
        GL11.glRotatef(f % 360L, 0f, 1f, 0f);
        RenderItem.renderInFrame = true;
        GL11.glScalef(renderScale, renderScale, renderScale);
        potato.doRender(dummy, 0, 0, 0, 0, 0);
        GL11.glPopMatrix();
    }
}
