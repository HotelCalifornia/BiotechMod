package tk.hotel_california.biotechmod.item;
//girl do you know Java?
//because your method body is sexy!

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import tk.hotel_california.biotechmod.energy.EnergyStack;
import tk.hotel_california.biotechmod.energy.IEnergyContainer;

public class ItemEnergyContainer extends Item implements IEnergyContainer {
    protected int capacity;
    public ItemEnergyContainer() {
        super();
    }
    public ItemEnergyContainer(int capacity) {
        super();
        this.capacity = capacity;
    }
    public ItemEnergyContainer setCapacity(int capacity) {
        this.capacity = capacity;
        return this;
    }
    /*IEnergyContainerItem*/
    @Override
    public EnergyStack getEnergy(ItemStack container) {
        if(container.stackTagCompound == null || !container.stackTagCompound.hasKey("Energy")) {
            return null;
        }
        return EnergyStack.loadEnergyStackFromNBT(container.stackTagCompound.getCompoundTag("Energy"));
    }
    @Override
    public int getCapacity(ItemStack container) {
        return this.capacity;
    }
    @Override
    public int fill(ItemStack container, EnergyStack resource, boolean doFill) {
        if(resource == null) {
            return 0;
        }
        if(!doFill) {
            if(container.stackTagCompound == null || !container.stackTagCompound.hasKey("Energy")) {
                return Math.min(capacity, resource.amount);
            }
            EnergyStack stack = EnergyStack.loadEnergyStackFromNBT(container.stackTagCompound.getCompoundTag("Energy"));
            if(stack == null) {
                return Math.min(capacity, resource.amount);
            }
            if(!stack.isEnergyEqual(resource)) {
                return 0;
            }
            return Math.min(capacity - stack.amount, resource.amount);
        }
        if(container.stackTagCompound == null) {
            container.stackTagCompound = new NBTTagCompound();
        }
        if(!container.stackTagCompound.hasKey("Energy")) {
            NBTTagCompound energyTag = resource.writeToNBT(new NBTTagCompound());
            if(capacity < resource.amount) {
                energyTag.setInteger("Amount", capacity);
                container.stackTagCompound.setTag("Energy", energyTag);
                return capacity;
            }
            container.stackTagCompound.setTag("Energy", energyTag);
            return resource.amount;
        }
        NBTTagCompound energyTag = container.stackTagCompound.getCompoundTag("Energy");
        EnergyStack stack = EnergyStack.loadEnergyStackFromNBT(energyTag);
        if(!stack.isEnergyEqual(resource)) {
            return 0;
        }
        int filled = capacity - stack.amount;
        if(resource.amount < filled) {
            stack.amount += resource.amount;
            filled = resource.amount;
        }
        else {
            stack.amount = capacity;
        }
        container.stackTagCompound.setTag("Energy", stack.writeToNBT(energyTag));
        return filled;
    }
    @Override
    public EnergyStack drain(ItemStack container, int maxDrain, boolean doDrain) {
        if(container.stackTagCompound == null || !container.stackTagCompound.hasKey("Energy")) {
            return null;
        }
        EnergyStack stack = EnergyStack.loadEnergyStackFromNBT(container.stackTagCompound.getCompoundTag("Energy"));
        if(stack == null) {
            return null;
        }
        stack.amount = Math.min(stack.amount, maxDrain);
        if(doDrain) {
            if(maxDrain >= capacity) {
                container.stackTagCompound.removeTag("Energy");
                if(container.stackTagCompound.hasNoTags()) {
                    container.stackTagCompound = null;
                }
                return stack;
            }
            NBTTagCompound energyTag = container.getTagCompound().getCompoundTag("Energy");
            energyTag.setInteger("Amount", energyTag.getInteger("Amount") - maxDrain);
            container.stackTagCompound.setTag("Energy", energyTag);
        }
        return stack;
    }
}
