package tk.hotel_california.biotechmod.energy;
//girl do you know Java?
//because your method body is sexy!

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.Locale;


public class EnergyStack {
    public int energyID;
    public int amount;
    public NBTTagCompound tag;
    public EnergyStack(Energy energy, int amount) {
        this.energyID = energy.getID();
        this.amount = amount;
    }
    public EnergyStack(int energyID, int amount) {
        this.energyID = energyID;
        this.amount = amount;
    }
    public EnergyStack(int energyID, int amount, NBTTagCompound nbt) {
        this(energyID, amount);
        if(nbt != null) {
            tag = (NBTTagCompound)nbt.copy();
        }
    }
    public static EnergyStack loadEnergyStackFromNBT(NBTTagCompound nbt) {
        if(nbt == null) {
            return null;
        }
        String energyName = nbt.getString("EnergyName");
        if(energyName == null) {
            energyName = nbt.hasKey("EnergyName") ? nbt.getString("EnergyName").toLowerCase(Locale.ENGLISH) : null;
        }
        if(energyName == null || EnergyList.getEnergy(energyName) == null) {
            return null;
        }
        EnergyStack stack = new EnergyStack(EnergyList.getEnergyID(energyName), nbt.getInteger("Amount"));
        if(nbt.hasKey("Tag")) {
            stack.tag = nbt.getCompoundTag("Tag");
        }
        else if(nbt.hasKey("extra")) {
            stack.tag = nbt.getCompoundTag("extra");
        }
        return stack;
    }
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        nbt.setString("EnergyName", EnergyList.getEnergyName(energyID));
        nbt.setInteger("Amount", amount);
        if(tag != null) {
            nbt.setTag("Tag", tag);
        }
        return nbt;
    }
    public final Energy getEnergy() {
        return EnergyList.getEnergy(energyID);
    }
    public EnergyStack copy() {
        return new EnergyStack(energyID, amount, tag);
    }
    public boolean isEnergyEqual(EnergyStack other) {
        return other != null && energyID == other.energyID && isEnergyStackTagEqual(other);
    }
    private boolean isEnergyStackTagEqual(EnergyStack other) {
        //don't you hate the ternary operator?
        return tag == null ? other.tag == null : other.tag != null && tag.equals(other.tag);
    }
    public static boolean areEnergyStackTagsEqual(EnergyStack stack1, EnergyStack stack2) {
        return stack1 == null && stack2 == null || (!(stack1 == null || stack2 == null) && stack1.isEnergyStackTagEqual(stack2));
    }
    public boolean containsEnergy(EnergyStack other) {
        return isEnergyEqual(other) && amount >= other.amount;
    }
    public boolean isEnergyStackIdentical(EnergyStack other) {
        return isEnergyEqual(other) && amount == other.amount;
    }
    public boolean isEnergyEqual(ItemStack other) {
        if(other == null) {
            return false;
        }
        else if(other.getItem() instanceof IEnergyContainer) {
            return isEnergyEqual(((IEnergyContainer)other.getItem()).getEnergy(other));
        }
        return isEnergyEqual(EnergyContainerList.getEnergyForFilledItem(other));
    }
    @Override
    public final int hashCode() {
        return energyID;
    }
    @Override
    public final boolean equals(Object o) {
        if(!(o instanceof EnergyStack)) {
            return false;
        }
        return isEnergyEqual((EnergyStack)o);
    }
}
