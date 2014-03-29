package tk.hotel_california.biotechmod.special;
//girl do you know Java?
//because your method body is sexy!

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * this class is a spoof of EntityItem:
 * the item will not rotate or hover, and cannot be picked up
 * this class is meant to perform lazy rendering
 * example: see @link BlockEnergyContainer#onBlockAdded
 */
public class EntityItemRender extends EntityItem {

    public EntityItemRender(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
        super.hoverStart = 0;
        super.delayBeforeCanPickup = -1;
        super.motionX = 0;
        super.motionY = 0;
        super.motionZ = 0;
        super.yOffset = 0;
        super.rotationPitch = 0;
        super.rotationYaw = 0;
        super.lifespan = Integer.MAX_VALUE;
        super.age = Integer.MAX_VALUE;
    }
    public EntityItemRender(World par1world, double par2, double par4, double par6, ItemStack par8itemstack) {
        this(par1world, par2, par4, par6);
        super.hoverStart = 0;
        super.delayBeforeCanPickup = -1;
        super.motionX = 0;
        super.motionY = 0;
        super.motionZ = 0;
        super.yOffset = 0;
        super.rotationPitch = 0;
        super.rotationYaw = 0;
        super.lifespan = Integer.MAX_VALUE;
        super.age = Integer.MAX_VALUE;
        super.setEntityItemStack(par8itemstack);
    }
    //this is a static item
    @Override
    public void onUpdate() {
        super.hoverStart = 0;
        super.delayBeforeCanPickup = -1;
        super.motionX = 0;
        super.motionY = 0;
        super.motionZ = 0;
        super.yOffset = 0;
        super.rotationPitch = 0;
        super.rotationYaw = 0;
        super.lifespan = Integer.MAX_VALUE;
        super.age = Integer.MAX_VALUE;
    }
    //this will never clump together
    @Override
    public boolean combineItems(EntityItem par1entityitem) {
        return false;
    }
}
