package tk.hotel_california.biotechmod.tileentity;
//girl do you know Java?
//because your method body is sexy!

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import tk.hotel_california.biotechmod.energy.EnergyStack;
import tk.hotel_california.biotechmod.energy.IEnergyContainer;

public class TileEntityEnergyContainer extends TileEntity implements IEnergyContainer, IInventory {
    public ItemStack[] inv;
    protected int capacity;
    protected int maxDrain;
    public TileEntityEnergyContainer(int capacity) {
        inv = new ItemStack[1];
        this.capacity = capacity;
        this.maxDrain = capacity;
    }
    public TileEntityEnergyContainer setCapacity(int capacity) {
        this.capacity = capacity;
        return this;
    }
    public int getCapacity(TileEntityEnergyContainer tileEntityEnergyContainer) {
        return tileEntityEnergyContainer.capacity;
    }
    /*IEnergyContainer*/
    @Override
    public EnergyStack getEnergy(ItemStack container) {
        if(container.stackTagCompound == null || !container.stackTagCompound.hasKey("Energy")) {
            return null;
        }
        return EnergyStack.loadEnergyStackFromNBT(container.stackTagCompound.getCompoundTag("Energy"));
    }

    @Override
    public int getCapacity(ItemStack container) {
        return capacity;
    }

    @Override
    public int fill(ItemStack container, EnergyStack resource, boolean dofill) {
        if(resource == null) {
            return 0;
        }
        if(!dofill) {
            if(container.stackTagCompound == null || container.stackTagCompound.hasKey("Energy")) {
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
    public int drain(ItemStack container, int maxDrain, boolean doDrain) {
        if(container.stackTagCompound == null || !container.stackTagCompound.hasKey("Energy")) {
            return 0;
        }
        int energy = container.stackTagCompound.getInteger("Energy");
        int energyDrained = Math.min(energy, Math.min(this.maxDrain, maxDrain));
        if(!doDrain) {
            energy -= energyDrained;
            container.stackTagCompound.setInteger("Energy", energy);
        }
        return energyDrained;
    }
    /*IInventory*/
    @Override
    public int getSizeInventory() {
        return inv.length;
    }

    @Override
    public ItemStack getStackInSlot(int var1) {
        return inv[var1];
    }

    @Override
    public ItemStack decrStackSize(int var1, int var2) {
        ItemStack stack = getStackInSlot(var1);
        if(stack != null) {
            if(stack.stackSize < var2) {
                setInventorySlotContents(var1, null);
            }
            else {
                stack = stack.splitStack(var2);
                if(stack.stackSize == 0) {
                    setInventorySlotContents(var1, null);
                }
            }
        }
        return stack;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int var1) {
        ItemStack stack = getStackInSlot(var1);
        if(stack != null) {
            setInventorySlotContents(var1, null);
        }
        return stack;
    }

    @Override
    public void setInventorySlotContents(int var1, ItemStack var2) {
        inv[var1] = var2;
        if(var2 != null && var2.stackSize > getInventoryStackLimit()) {
            var2.stackSize = getInventoryStackLimit();
        }
    }

    @Override
    public String getInventoryName() {
        return "biotechmod.tileentityenergycontainer";
    }

    @Override
    public boolean hasCustomInventoryName() {
        return true;
    }

    @Override
    public int getInventoryStackLimit() {
        return 1;
    }

    @Override
    public void markDirty() {

    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer var1) {
        return true;
    }

    @Override
    public void openInventory() {

    }

    @Override
    public void closeInventory() {

    }

    @Override
    public boolean isItemValidForSlot(int var1, ItemStack var2) {
        return false;
    }
}
